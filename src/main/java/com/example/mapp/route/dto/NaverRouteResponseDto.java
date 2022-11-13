package com.example.mapp.route.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NaverRouteResponseDto implements Serializable {
    private Integer distance;
    private Integer duration;
    private Integer tollFare;
    private Integer taxiFare;
    private Integer fuelPrice;
}
