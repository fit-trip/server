package com.example.mapp.place.controller;

import com.example.mapp.place.dto.KakaoPlaceRecommendRequestDto;
import com.example.mapp.place.service.KakaoPlaceRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceRecommendController {

    private final KakaoPlaceRecommendService kakaoPlaceRecommendService;

    @GetMapping("/recommend")
    public Mono<?> recommend(KakaoPlaceRecommendRequestDto dto) {
        return kakaoPlaceRecommendService.recommend(dto);
    }
}
