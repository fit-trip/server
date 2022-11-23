package com.example.mapp.schedule.controller;

import com.example.mapp.schedule.dto.ScheduleRequestDto;
import com.example.mapp.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
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
        scheduleService.addSchedule(request.getUserId(), request.getLocations());
    }
}
