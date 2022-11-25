package com.example.mapp.route.dto;

import com.example.mapp.route.vo.CoordinateVo;
import com.example.mapp.route.vo.RouteInfoVo;
import lombok.Data;

@Data
public class RouteInfoDto {
    private OptRoutePerDuration durationInfo;
    private OptRoutePerFare fareInfo;
    private int coordinateNum;

    public RouteInfoDto(RouteInfoVo[][] optRouteResults, int coordinateNum) {
        this.durationInfo = new OptRoutePerDuration(optRouteResults[0], coordinateNum);
        this.fareInfo = new OptRoutePerFare(optRouteResults[1], coordinateNum);
        this.coordinateNum = coordinateNum;
    }

    @Data
    public static class OptRoutePerDuration {
        private CoordinateVo[] coordinates;
        private Integer[] durations;
        private int totalDuration = 0;

        private OptRoutePerDuration(RouteInfoVo[] optRouteInfo, int coordinateNum) {
            coordinates = new CoordinateVo[coordinateNum];
            durations = new Integer[coordinateNum];

            for (int i = 0; i < coordinateNum - 1; i++) {
                RouteInfoVo routeInfo = optRouteInfo[i];
                CoordinateVo from = routeInfo.getFrom();
                Integer duration = routeInfo.getInfo().getDuration();

                totalDuration += duration;
                durations[i] = duration;
                coordinates[i] = CoordinateVo.builder()
                        .x(from.getX())
                        .y(from.getY())
                        .name(from.getName())
                        .build();

                if (i == coordinateNum-2) {
                    CoordinateVo to = routeInfo.getTo();
                    coordinates[i+1] = CoordinateVo.builder()
                            .x(to.getX())
                            .y(to.getY())
                            .name(to.getName())
                            .build();
                }
            }
        }
    }

    @Data
    public static class OptRoutePerFare {
        private CoordinateVo[] coordinates;
        private int totalFare = 0;
        private Integer[] fares;

        private OptRoutePerFare(RouteInfoVo[] optRouteInfo, int coordinateNum) {
            coordinates = new CoordinateVo[coordinateNum];
            fares = new Integer[coordinateNum];

            for (int i = 0; i < coordinateNum - 1; i++) {
                RouteInfoVo routeInfo = optRouteInfo[i];
                CoordinateVo from = routeInfo.getFrom();
                Integer fare = routeInfo.getInfo().getFuelPrice() + routeInfo.getInfo().getTollFare();

                totalFare += fare;
                fares[i] = fare;
                coordinates[i] = CoordinateVo.builder()
                        .x(from.getX())
                        .y(from.getY())
                        .name(from.getName())
                        .build();

                if (i == coordinateNum-2) {
                    CoordinateVo to = routeInfo.getTo();
                    coordinates[i+1] = CoordinateVo.builder()
                            .x(to.getX())
                            .y(to.getY())
                            .name(to.getName())
                            .build();
                }
            }
        }
    }
}