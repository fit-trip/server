package com.example.mapp.route.api;

import com.example.mapp.route.dto.RouteInfoResponseDto;
import com.example.mapp.route.service.RouteInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/route")
public class RouteInfoController {

    private final RouteInfoService routeInfoService;

    @GetMapping
    public RouteInfoResponseDto getRouteInfo(Integer scheduleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // userId
        String principal = (String)authentication.getPrincipal();

        return routeInfoService.getRouteInfo(scheduleId, principal);
    }
}
