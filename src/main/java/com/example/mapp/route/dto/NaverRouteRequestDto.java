package com.example.mapp.route.dto;

import com.example.mapp.route.vo.CoordinateVo;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NaverRouteRequestDto implements Serializable {
    @NotNull
    private CoordinateVo startLocation;
    @NotNull
    private CoordinateVo goalLocation;

    public String getStartParam() {
        return startLocation.getX() + "," + startLocation.getY();
    }
    public String getGoalParam() {
        return goalLocation.getX() + "," + goalLocation.getY();
    }
}
