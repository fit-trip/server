package com.example.mapp.route.dto;

import com.example.mapp.route.model.RouteInfo;
import com.example.mapp.route.model.RouteInfoPerDuration;
import com.example.mapp.route.model.RouteInfoPerFare;
import com.example.mapp.route.vo.CoordinateVo;
import com.example.mapp.route.vo.RouteInfoVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RouteInfoResponseDto {
    private RouteInfoPerDurationDto duration;
    private RouteInfoPerFareDto fare;

    public RouteInfoResponseDto(List<RouteInfoPerDuration> duration, List<RouteInfoPerFare> fare) {
        this.duration = new RouteInfoPerDurationDto(duration);
        this.fare = new RouteInfoPerFareDto(fare);
    }
    @Data
    public static class RouteInfoPerDurationDto {
        private List<CoordinateVo> locations = new ArrayList<>();
        private List<Integer> durationForNextPlace = new ArrayList<>();

        RouteInfoPerDurationDto(List<RouteInfoPerDuration> routeInfoPerDuration) {
            routeInfoPerDuration.forEach(routeInfo -> {
                locations.add(new CoordinateVo(routeInfo.getLocation()));
                durationForNextPlace.add(routeInfo.getDurationForNextPlace());
            });
        }
    }

    @Data
    public static class RouteInfoPerFareDto {
        private List<CoordinateVo> locations = new ArrayList<>();
        private List<Integer> fareForNextPlace = new ArrayList<>();

        RouteInfoPerFareDto(List<RouteInfoPerFare> routeInfoPerDuration) {
            routeInfoPerDuration.forEach(routeInfo -> {
                locations.add(new CoordinateVo(routeInfo.getLocation()));
                fareForNextPlace.add(routeInfo.getFareForNextLocation());
            });
        }
    }
}