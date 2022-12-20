package com.example.mapp.schedule.service;

import com.example.mapp.route.dto.RouteInfoDto;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerDuration;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerFare;
import com.example.mapp.route.dto.RouteInfoResponseDto;
import com.example.mapp.route.model.RouteInfo;
import com.example.mapp.route.model.RouteInfoPerDuration;
import com.example.mapp.route.model.RouteInfoPerFare;
import com.example.mapp.route.repository.RouteInfoPerDurationRepository;
import com.example.mapp.route.repository.RouteInfoPerFareRepository;
import com.example.mapp.route.service.NaverRouteService;
import com.example.mapp.route.service.RouteInfoService;
import com.example.mapp.schedule.dto.ScheduleCopyRequestDto;
import com.example.mapp.schedule.dto.ScheduleRequestDto;
import com.example.mapp.schedule.dto.ScheduleResponseDto;
import com.example.mapp.schedule.dto.ScheduleUpdateDto;
import com.example.mapp.schedule.model.Schedule;
import com.example.mapp.schedule.repository.ScheduleRepository;
import com.example.mapp.user.model.AppUser;
import com.example.mapp.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    private final NaverRouteService naverRouteService;
    private final RouteInfoService routeInfoService;

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    private final RouteInfoPerDurationRepository routeInfoPerDurationRepository;
    private final RouteInfoPerFareRepository routeInfoPerFareRepository;

    @Transactional
    public void addSchedule(String userId, ScheduleRequestDto req) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("아이디 없음"));

        RouteInfoDto routeInfo = naverRouteService.route(req.getLocations());
        OptRoutePerDuration durationInfo = routeInfo.getDurationInfo();
        OptRoutePerFare fareInfo = routeInfo.getFareInfo();

        Schedule schedule = Schedule.builder()
                .appUser(user)
                .name(req.getName())
                .totalDuration(durationInfo.getTotalDuration())
                .totalFare(fareInfo.getTotalFare())
                .build();

        scheduleRepository.save(schedule);
        routeInfoService.addRouteInfosOnSchedule(schedule.getId(), routeInfo);
    }

    public List<ScheduleResponseDto> getAllMySchedule(String userId) {
        List<Schedule> schedules = scheduleRepository.findAllByAppUser_Id(userId);
        return ScheduleResponseDto.fromList(schedules);
    }

    public List<ScheduleResponseDto> getAllSharedSchedule() {
        List<Schedule> schedules = scheduleRepository.findAllBySharedStatus(true);
        return ScheduleResponseDto.fromList(schedules);
    }

    @Transactional
    public void updateSharedStatus(ScheduleUpdateDto dto) {
        Schedule schedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new IllegalStateException("아이디 없음"));

        if (!schedule.getAppUser().getId().equals(dto.getUserId())) {
            throw new IllegalStateException("사용자 정보 불일치");
        }

        schedule.updateSharedStatus(dto.getSharedStatus());

        // 스케줄 설명 저장
        if (dto.getDescription() != null) {
            schedule.updateDescription(dto.getDescription());
        }
    }

    @Transactional
    public void copySchedule(String userId, ScheduleCopyRequestDto dto) {
        Schedule sharedSchedule = scheduleRepository.findById(dto.getScheduleId())
                .orElseThrow(() -> new IllegalStateException("스케줄 아이디 없음"));

        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("사용자 아이디 없음"));

        List<RouteInfoPerFare> sharedRouteInfoPerFare = routeInfoPerFareRepository.findAllByScheduleIdOrderByOrder(dto.getScheduleId());
        List<RouteInfoPerDuration> sharedRouteInfoPerDuration = routeInfoPerDurationRepository.findAllByScheduleIdOrderByOrder(dto.getScheduleId());

        Schedule newSchedule = Schedule.builder()
                .appUser(user)
                .name(sharedSchedule.getName())
                .totalDuration(sharedSchedule.getTotalDuration())
                .totalFare(sharedSchedule.getTotalFare())
                .build();
        scheduleRepository.save(newSchedule);


        for (RouteInfoPerFare routeInfoPerFare : sharedRouteInfoPerFare) {
            RouteInfoPerFare newRouteInfoPerFare = RouteInfoPerFare.builder()
                    .schedule(newSchedule)
                    .location(routeInfoPerFare.getLocation())
                    .fareForNextLocation(routeInfoPerFare.getFareForNextLocation())
                    .build();
            newRouteInfoPerFare.updateOrder(routeInfoPerFare.getOrder());

            routeInfoPerFareRepository.save(newRouteInfoPerFare);
        }

        for (RouteInfoPerDuration routeInfoPerDuration : sharedRouteInfoPerDuration) {
            RouteInfoPerDuration newRouteInfoPerDuration = RouteInfoPerDuration.builder()
                    .schedule(newSchedule)
                    .location(routeInfoPerDuration.getLocation())
                    .durationForNextPlace(routeInfoPerDuration.getDurationForNextPlace())
                    .build();
            newRouteInfoPerDuration.updateOrder(routeInfoPerDuration.getOrder());

            routeInfoPerDurationRepository.save(newRouteInfoPerDuration);
        }
    }
}
