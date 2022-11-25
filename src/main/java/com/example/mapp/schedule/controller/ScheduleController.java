package com.example.mapp.schedule.controller;

import com.example.mapp.schedule.dto.ScheduleRequestDto;
import com.example.mapp.schedule.dto.ScheduleResponseDto;
import com.example.mapp.schedule.service.ScheduleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public void createSchedule(@RequestBody ScheduleRequestDto request) {
        String userId = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        scheduleService.addSchedule(userId, request.getLocations());
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllMySchedule() {
        String userId = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return scheduleService.getAllMySchedule(userId);
    }
}
