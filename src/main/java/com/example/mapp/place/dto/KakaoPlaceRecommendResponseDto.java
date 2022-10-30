package com.example.mapp.place.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * "https://developers.kakao.com/docs/latest/ko/local/dev-guide", 카테고리로 장소 검색하기 참고
 */
@Data
public class KakaoPlaceRecommendResponseDto implements Serializable, PlaceRecommendResponseDto{

    String place_name;
    String address_name;
    String road_address_name;
    String id;
    String x;
    String y;
    String category_name;

    @Data
    public static class KakaoPlaceRecommendResponseListDto implements PlaceRecommendResponseDto{
        List<KakaoPlaceRecommendResponseDto> documents;
    }
}
