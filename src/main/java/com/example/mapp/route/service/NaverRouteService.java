package com.example.mapp.route.service;

import com.example.mapp.route.OptRouteProcessor;
import com.example.mapp.route.vo.CoordinateVo;
import com.example.mapp.route.dto.NaverRouteRequestDto;
import com.example.mapp.route.dto.NaverRouteResponseDto;
import com.example.mapp.route.vo.RouteInfoVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class NaverRouteService implements RouteService{
    private final OptRouteProcessor optRouteProcessor;

    private static final String ID_HEADER = "X-NCP-APIGW-API-KEY-ID";
    private static final String KEY_HEADER = "X-NCP-APIGW-API-KEY";
    @Value("${naver.id}")
    private String NAVER_ID;
    @Value("${naver.rest-key}")
    private String NAVER_REST_KEY;

    private WebClient getWebClient(String baseUrl) {
        return WebClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set(ID_HEADER, NAVER_ID);
                    httpHeaders.set(KEY_HEADER, NAVER_REST_KEY);
                })
                .baseUrl(baseUrl).build();
    }

    public RouteInfoVo[][] route(List<CoordinateVo> locations) {
        RouteInfoVo[][] map = createRouteMap(locations);
        return optRouteProcessor.calcOptRoute(map, locations.size());
    }


    private RouteInfoVo[][] createRouteMap(List<CoordinateVo> locations) {
        RouteInfoVo[][] map = new RouteInfoVo[locations.size()][locations.size()];

        for (int i = 0; i < locations.size(); i++) {
            for (int j = i+1; j < locations.size(); j++) {
                CoordinateVo start = locations.get(i);
                CoordinateVo goal = locations.get(j);
                NaverRouteRequestDto routeRequest = createRouteRequest(start, goal);
                RouteInfoVo routeInfo = getRouteInfo(routeRequest);

                map[i][j] = routeInfo;
                map[j][i] = routeInfo;
            }
        }

        return map;
    }
    private NaverRouteRequestDto createRouteRequest(CoordinateVo start, CoordinateVo goal) {
        return NaverRouteRequestDto.builder()
                .startLocation(start)
                .goalLocation(goal)
                .build();
    }

    private RouteInfoVo getRouteInfo(NaverRouteRequestDto dto) {
        WebClient webClient = getWebClient("https://naveropenapi.apigw.ntruss.com");
        String baseUri = "/map-direction/v1/driving";
        String option = "trafast";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("start", dto.getStartParam());
        params.set("goal", dto.getGoalParam());
        params.set("option", option);

        Map result = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(baseUri)
                                .queryParams(params)
                                .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map route = (Map)result.get("route");
        List optionResults = (List)route.get(option);
        Map optionResult = (Map)optionResults.get(0);
        Map summary = (Map) optionResult.get("summary");

        NaverRouteResponseDto response = NaverRouteResponseDto.builder()
                .duration((Integer) summary.get("duration"))
                .fuelPrice((Integer) summary.get("fuelPrice"))
                .distance((Integer) summary.get("distance"))
                .tollFare((Integer) summary.get("tollFare"))
                .taxiFare((Integer) summary.get("taxiFare"))
                .build();

        return RouteInfoVo.builder()
                .from(dto.getStartLocation())
                .to(dto.getGoalLocation())
                .info(response)
                .build();
    }
}
