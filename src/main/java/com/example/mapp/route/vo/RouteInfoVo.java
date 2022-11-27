package com.example.mapp.route.vo;

import com.example.mapp.route.dto.NaverRouteResponseDto;

import java.util.List;
import java.util.Objects;

import com.example.mapp.route.model.RouteInfo;
import com.example.mapp.route.model.RouteInfoPerDuration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RouteInfoVo {
    private CoordinateVo from;
    private CoordinateVo to;
    private NaverRouteResponseDto info;

    public RouteInfoVo(List<RouteInfoPerDuration> duration) {
        int size = duration.size();
        for (int i = 0; i < size - 1; i++) {
            RouteInfoPerDuration routeInfoPerDuration = duration.get(i);
            RouteInfoPerDuration routeInfoPerDuration1 = duration.get(i + 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteInfoVo that = (RouteInfoVo) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
