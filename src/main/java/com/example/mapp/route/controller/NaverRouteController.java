package com.example.mapp.route.controller;

import com.example.mapp.route.vo.CoordinateVo;
import com.example.mapp.route.service.NaverRouteService;
import com.example.mapp.route.vo.RouteInfoVo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class NaverRouteController {

    private final NaverRouteService naverRouteService;

    @PostMapping
    public RouteInfoVo[][] route(@RequestBody List<CoordinateVo> dto) {
        return naverRouteService.route(dto);
    }
}
