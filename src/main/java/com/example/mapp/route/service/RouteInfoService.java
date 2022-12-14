package com.example.mapp.route.service;

import com.example.mapp.route.dto.RouteInfoDto;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerDuration;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerFare;
import com.example.mapp.route.dto.RouteInfoResponseDto;
import com.example.mapp.route.model.Location;
import com.example.mapp.route.model.RouteInfoPerDuration;
import com.example.mapp.route.model.RouteInfoPerFare;
import com.example.mapp.route.model.id.LocationId;
import com.example.mapp.route.model.id.RouteInfoId;
import com.example.mapp.route.repository.LocationRepository;
import com.example.mapp.route.repository.RouteInfoPerDurationRepository;
import com.example.mapp.route.repository.RouteInfoPerFareRepository;
import com.example.mapp.route.vo.CoordinateVo;
import com.example.mapp.schedule.model.Schedule;
import com.example.mapp.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteInfoService {

    private final ScheduleRepository scheduleRepository;
    private final RouteInfoPerFareRepository fareRepository;
    private final RouteInfoPerDurationRepository durationRepository;
    private final LocationRepository locationRepository;

    public RouteInfoResponseDto getRouteInfo(Integer scheduleId, String userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("스케줄 없음"));

        if (!Objects.equals(schedule.getAppUser().getId(), userId)) {
            throw new IllegalStateException("사용자 정보 불일치");
        }

        List<RouteInfoPerDuration> duration = durationRepository.findAllByScheduleIdOrderByOrder(scheduleId);
        List<RouteInfoPerFare> fare = fareRepository.findAllByScheduleIdOrderByOrder(scheduleId);

        return new RouteInfoResponseDto(duration, fare);
    }

    @Transactional
    public void addRouteInfosOnSchedule(Integer scheduleId, RouteInfoDto routeInfo) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("스케줄 없음"));
        StringBuilder locationsName = new StringBuilder();

        OptRoutePerDuration durationInfo = routeInfo.getDurationInfo();
        OptRoutePerFare fareInfo = routeInfo.getFareInfo();

        CoordinateVo[] durationCoords = durationInfo.getCoordinates();
        Integer[] durations = durationInfo.getDurations();

        CoordinateVo[] fareCoords = fareInfo.getCoordinates();
        Integer[] fares = fareInfo.getFares();

        for (int i = 0; i < routeInfo.getCoordinateNum(); i++) {
            Location duLocation;
            Location faLocation;

            CoordinateVo durationCoord = durationCoords[i];
            Integer durationForNextLocation = durations[i];

            CoordinateVo fareCoord = fareCoords[i];
            Integer fareForNextLocation = fares[i];

            LocationId duLocationId = new LocationId(durationCoord.getX(), durationCoord.getY());
            duLocation = Location.builder()
                    .id(duLocationId)
                    .name(durationCoord.getName())
                    .build();
            LocationId faLocationId = new LocationId(fareCoord.getX(), fareCoord.getY());
            faLocation = Location.builder()
                    .id(faLocationId)
                    .name(fareCoord.getName())
                    .build();

            if (!locationRepository.existsById(duLocationId)) {
                locationRepository.save(duLocation);
            }

            if (!locationRepository.existsById(faLocationId)) {
                locationRepository.save(faLocation);
            }

            locationsName.append(duLocation.getName());
            locationsName.append(",");

            RouteInfoPerFare infoPerFare = RouteInfoPerFare.builder()
                    .id(new RouteInfoId(scheduleId, faLocationId))
                    .fareForNextLocation(fareForNextLocation)
                    .schedule(schedule)
                    .location(faLocation)
                    .build();

            RouteInfoPerDuration infoPerDuration = RouteInfoPerDuration.builder()
                    .id(new RouteInfoId(scheduleId, duLocationId))
                    .durationForNextPlace(durationForNextLocation)
                    .schedule(schedule)
                    .location(duLocation)
                    .build();

            infoPerFare.updateOrder(i);
            infoPerDuration.updateOrder(i);

            fareRepository.save(infoPerFare);
            durationRepository.save(infoPerDuration);
        }
        schedule.updateLocationsName(locationsName.toString());
    }
}