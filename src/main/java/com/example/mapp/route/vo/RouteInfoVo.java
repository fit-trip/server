package com.example.mapp.route.vo;

import com.example.mapp.route.dto.NaverRouteResponseDto;
import java.util.Objects;
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
