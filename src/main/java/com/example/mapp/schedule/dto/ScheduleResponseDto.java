package com.example.mapp.schedule.dto;

import com.example.mapp.schedule.model.Schedule;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class ScheduleResponseDto {
    private Integer id;
    private String name;
    private Integer totalDuration;
    private Integer totalFare;
    private Boolean sharedStatus;
    private String description;
    private List<String> locationsName;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.totalDuration = schedule.getTotalDuration();
        this.totalFare = schedule.getTotalFare();
        this.sharedStatus = schedule.getSharedStatus();
        this.description = schedule.getDescription();
        String[] locationsName = schedule.getLocationsName().split(",");

        this.locationsName = Arrays.stream(locationsName).collect(Collectors.toList());
    }

    public static List<ScheduleResponseDto> fromList(List<Schedule> schedules) {
        return schedules.stream().map(ScheduleResponseDto::new).collect(Collectors.toList());
    }
}
