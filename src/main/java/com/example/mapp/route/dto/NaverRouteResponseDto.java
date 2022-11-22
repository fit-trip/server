package com.example.mapp.route.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NaverRouteResponseDto implements Serializable {
    private Integer distance; // 이동 거리
    private Integer duration; // 이동 시간
    private Integer tollFare; // 톨게이트 비용
    private Integer taxiFare; // 택시비(톨비 제외)
    private Integer fuelPrice; // 자가용으로 움직일 경우 연료비

    public Integer getCost(String norm) {
        if (norm.equals("duration")) {
            return this.duration;
        }
        else if (norm.equals("Fare")) {
            return this.taxiFare + this.tollFare;
        }
        else {
            return 0;
        }
    }
}
