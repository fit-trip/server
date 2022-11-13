package com.example.mapp.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class KakaoPlaceRecommendRequestDto implements Serializable , PlaceRecommendRequestDto{
    private String x;
    private String y;
    private Integer radius;
    private String category;
}
