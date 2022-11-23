package com.example.mapp.schedule.service;

import com.example.mapp.route.dto.RouteInfoDto;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerDuration;
import com.example.mapp.route.dto.RouteInfoDto.OptRoutePerFare;
import com.example.mapp.route.service.NaverRouteService;
import com.example.mapp.route.service.RouteInfoService;
import com.example.mapp.route.vo.CoordinateVo;
import com.example.mapp.schedule.model.Schedule;
import com.example.mapp.schedule.repository.ScheduleRepository;
import com.example.mapp.user.model.User;
import com.example.mapp.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public void addSchedule(Integer userId, List<CoordinateVo> coordinates) {
        RouteInfoDto routeInfo = naverRouteService.route(coordinates);
        OptRoutePerDuration durationInfo = routeInfo.getDurationInfo();
        OptRoutePerFare fareInfo = routeInfo.getFareInfo();

        Optional<User> byId = userRepository.findById(userId);

        Schedule schedule = Schedule.builder()
                .user(byId.get())
                .totalDuration(durationInfo.getTotalDuration())
                .totalFare(fareInfo.getTotalFare())
                .build();

        scheduleRepository.save(schedule);
        routeInfoService.addRouteInfosOnSchedule(schedule.getId(), routeInfo);
    }
}
