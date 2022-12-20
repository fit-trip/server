package com.example.mapp.schedule.controller;

import com.example.mapp.schedule.dto.ScheduleRequestDto;
import com.example.mapp.schedule.dto.ScheduleResponseDto;
import com.example.mapp.schedule.dto.ScheduleUpdateDto;
import com.example.mapp.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
@Tag(name = "스케줄(일정)")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Operation(summary = "일정 생성")
    @PostMapping
    public void createSchedule(@RequestBody ScheduleRequestDto request) {
        scheduleService.addSchedule(getUserIdFromSecurityContext(), request);
    }

    @Operation(summary = "일정 공유 상태 변경")
    @PutMapping
    public void updateScheduleSharedStatus(@RequestBody ScheduleUpdateDto dto) {
        dto.setUserId(getUserIdFromSecurityContext());
        scheduleService.updateSharedStatus(dto);
    }

    @Operation(summary = "공유 일정 조회")
    @GetMapping("/shared")
    public List<ScheduleResponseDto> getAllSharedSchedule() {
        return scheduleService.getAllSharedSchedule();
    }

    private String getUserIdFromSecurityContext() {
        return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Operation(summary = "내 일정 조회")
    @GetMapping
    public List<ScheduleResponseDto> getAllMySchedule() {
        String userId = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return scheduleService.getAllMySchedule(userId);
    }
}
