package com.example.mapp.schedule.dto;

import lombok.Data;

@Data
public class ScheduleUpdateDto {
    private String userId;
    private Integer scheduleId;
    private Boolean sharedStatus;
    private String description;
}
