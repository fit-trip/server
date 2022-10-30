package com.example.mapp.place.service;

import com.example.mapp.place.dto.PlaceRecommendRequestDto;
import reactor.core.publisher.Mono;

public interface PlaceRecommendService {

    Mono<?> recommend(PlaceRecommendRequestDto dto);
}
