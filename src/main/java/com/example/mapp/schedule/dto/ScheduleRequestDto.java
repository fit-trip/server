package com.example.mapp.schedule.dto;

import com.example.mapp.route.vo.CoordinateVo;
import java.util.List;
import lombok.Data;

@Data
public class ScheduleRequestDto {
    private String name;
    private List<CoordinateVo> locations;
}
