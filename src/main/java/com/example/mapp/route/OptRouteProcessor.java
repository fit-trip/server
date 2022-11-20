package com.example.mapp.route;

import com.example.mapp.route.vo.RouteInfoVo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class OptRouteProcessor {
    static int N;
    static int[][] dp; // dp[N][visited]: N번 -> visited에서 방문 X한 지점 -> 0번 지점(시작 지점) 경로 저장한다고 생각하면 쉬움
    static final int INF = 987654321;

    static int VISITED_ALL = 0;

    public RouteInfoVo[][] calcOptRoute(RouteInfoVo[][] map, int size) {
        // map: 2차원 배열로, 각 위치간의 비용 정보를 담고 있음 (양방향 비용 동일)
        // size: 위치의 개수

        // TSP 알고리즘을 활용한 최적 경로 계산 후, 거리가 가장 짧은 경로를 반환
        dp = new int[size][(1 << size)]; // row: 현재 지점, col: 지금까지 방문한 지점 2진수
        N = size;
        VISITED_ALL = (1 << size) - 1;

        // DP 배열 초기화
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], INF);
        }

        // 0번째 위치에서 출발
        int result = tsp(0, 1 << 0, map);
        System.out.println(result);

        // 최적 경로를 순서대로 구하기
        ArrayList<Integer> path = calcPath(result, map);
        path.add(0, 0); // 시작지점 추가
        path.add(0); // 왕복 경로이므로 끝에도 시작지점 추가
        System.out.println(path);

        // 구한 최적 경로 리스트를 기반으로 RouteInfoVo[1][N] 객체 생성 후 값 저장
        RouteInfoVo[][] resultRoute = new RouteInfoVo[1][N];
        for (int i = 0; i < N; i++) {
            resultRoute[0][i] = map[path.get(i)][path.get(i + 1)];
        }

        // resultRoute 값 확인
        for (int i = 0; i < N; i++) {
            System.out.println(resultRoute[0][i].getFrom() + " -> " + resultRoute[0][i].getTo() + " : " + resultRoute[0][i].getInfo().getDistance());
        }

        // resultRoute 반환
        return resultRoute;

        // TODO: 거리가 아니라 시간과 비용을 고려하여 최적 경로 계산하기
        // TODO: resultRoute[][]에서 from과 to 이어지도록 맞추기
        // TODO: 코드 정리
        // TODO: 반환 형태 상의 후 수정

    }

    public int tsp(int last, int visited, RouteInfoVo[][] map) {
        // 마지막 지점 -> 0번째 지점 사이에 경로 존재 시 경로 값 반환
        if (visited == VISITED_ALL) {
            dp[last][visited] = map[last][0].getInfo().getDistance();
            return map[last][0].getInfo().getDistance();
        }

        // DP 적용 -> DP 캐시 값이 존재한다면 last, visited에 대한 연산이 이미 계산된 적 있다는 뜻
        // 따라서, 중복 계산하지 않고 캐시 값을 반환
        if (dp[last][visited] != INF) {
            return dp[last][visited];
        }

        // 모든 지점을 방문하지 않았다면, 다음 방문할 지점을 탐색
        int tmp = INF;
        for (int i = 0; i < N; i++) {
            // i번째 지점을 방문하지 않았다면, i번째 지점을 방문
            if (((visited & (1 << i)) == 0) && (map[last][i] != null)) {
                if (tsp(i, visited | (1 << i), map) + map[last][i].getInfo().getDistance() < tmp) {
                    tmp = tsp(i, visited | (1 << i), map) + map[last][i].getInfo().getDistance(); // 최솟값 업데이트
                }
            }
        }

        dp[last][visited] = tmp; // 최솟값 저장
        return tmp;
    }

    public ArrayList<Integer> calcPath(int distance, RouteInfoVo[][] map) {
        ArrayList<Integer> path = new ArrayList<>();
        int piv = 0;
        int masking = 1;

        // DP 배열 탐색하면서 다음 경로 찾기
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                if ((masking & (1 << k)) != 0) continue;

                if (dp[k][masking + (1 << k)] != INF) {
                    if (distance - map[piv][k].getInfo().getDistance() == dp[k][masking + (1 << k)]) {
                        path.add(k);
                        distance = dp[k][masking + (1 << k)];
                        piv = k;
                        masking += (1 << k);
                    }
                }

            }
        }

        return path;

    }
}
