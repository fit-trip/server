package com.example.mapp.place.controller;

import com.example.mapp.place.dto.KakaoPlaceRecommendRequestDto;
import com.example.mapp.place.service.KakaoPlaceRecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "명소 추천")
@RestController
@RequestMapping("/api/v1/place")
@RequiredArgsConstructor
public class PlaceRecommendController {

    private final KakaoPlaceRecommendService kakaoPlaceRecommendService;

    @Operation(summary = "추천 받기")
    @GetMapping("/recommend")
    public Mono<?> recommend(KakaoPlaceRecommendRequestDto dto) {
        return kakaoPlaceRecommendService.recommend(dto);
    }
}
