package com.example.mapp.schedule.service;

import com.example.mapp.route.dto.RouteInfoDto;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerDuration;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerFare;
import com.example.mapp.route.repository.RouteInfoPerDurationRepository;
import com.example.mapp.route.service.NaverRouteService;
import com.example.mapp.route.service.RouteInfoService;
import com.example.mapp.route.vo.CoordinateVo;
import com.example.mapp.schedule.dto.ScheduleResponseDto;
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

    public List<ScheduleResponseDto> getAllMySchedule(String userId) {
        List<Schedule> schedules = scheduleRepository.findAllByAppUser_Id(userId);
        return ScheduleResponseDto.fromList(schedules);
    }

    @Transactional
    public void addSchedule(String userId, List<CoordinateVo> coordinates) {
        RouteInfoDto routeInfo = naverRouteService.route(coordinates);
        OptRoutePerDuration durationInfo = routeInfo.getDurationInfo();
        OptRoutePerFare fareInfo = routeInfo.getFareInfo();

        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("아이디 없음"));

        Schedule schedule = Schedule.builder()
                .appUser(user)
                .totalDuration(durationInfo.getTotalDuration())
                .totalFare(fareInfo.getTotalFare())
                .build();

        scheduleRepository.save(schedule);
        routeInfoService.addRouteInfosOnSchedule(schedule.getId(), routeInfo);
    }
}
