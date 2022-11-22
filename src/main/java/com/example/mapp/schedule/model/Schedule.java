package com.example.mapp.schedule.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @GeneratedValue
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "totalTime")
    private Integer totalTime;

    @Column(name = "totalMoney")
    private Integer totalMoney;

    @Builder
    public Schedule(String id, String name, Integer totalTime, Integer totalMoney) {
        this.id = id;
        this.name = name;
        this.totalTime = totalTime;
        this.totalMoney = totalMoney;
    }
}
