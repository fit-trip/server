package com.example.mapp.place.service;

import com.example.mapp.place.dto.KakaoPlaceRecommendRequestDto;
import com.example.mapp.place.dto.KakaoPlaceRecommendResponseDto.*;
import com.example.mapp.place.dto.PlaceRecommendRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class KakaoPlaceRecommendService implements PlaceRecommendService {

    @Value("${kakao.rest-key}")
    private String KAKAO_REST_KEY;

    @Override
    public Mono<KakaoPlaceRecommendResponseListDto> recommend(PlaceRecommendRequestDto dto) {
        WebClient webClient = getWebClient("https://dapi.kakao.com");
        String uri = "/v2/local/search/category.json";


        // parameters
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("category_group_code", ((KakaoPlaceRecommendRequestDto) dto).getCategory());
        params.set("y", ((KakaoPlaceRecommendRequestDto) dto).getY());
        params.set("x", ((KakaoPlaceRecommendRequestDto) dto).getX());
        params.set("radius", ((KakaoPlaceRecommendRequestDto) dto).getRadius().toString());

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .queryParams(params)
                        .build()
                )
                .retrieve()
                .bodyToMono(KakaoPlaceRecommendResponseListDto.class);
    }

    private WebClient getWebClient(String baseUrl) {
        return WebClient.builder()
                .defaultHeader("Authorization", "KakaoAK " + KAKAO_REST_KEY)
                .baseUrl(baseUrl).build();
    }
}
