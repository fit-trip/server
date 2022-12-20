package com.example.mapp.schedule.model;

import com.example.mapp.route.model.RouteInfoPerDuration;
import com.example.mapp.user.model.AppUser;
import java.util.List;
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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private AppUser appUser;

    @Column(name = "name")
    private String name;

    @Column(name = "totalDuration")
    private Integer totalDuration;

    @Column(name = "totalFare")
    private Integer totalFare;

    @Column(name = "description")
    private String description;

    private Boolean sharedStatus = false;

    private String locationsName;

    @Builder
    public Schedule(Integer id, AppUser appUser, String name, Integer totalDuration, Integer totalFare) {
        this.id = id;
        this.appUser = appUser;
        this.name = name;
        this.totalDuration = totalDuration;
        this.totalFare = totalFare;
    }

    public void updateTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void updateTotalFare(Integer totalFare) {
        this.totalFare = totalFare;
    }

    public void updateSharedStatus(boolean sharedStatus) {
        this.sharedStatus = sharedStatus;
    }
    public void updateLocationsName(String locationsName) {
        this.locationsName = locationsName;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateUserInfo(AppUser appUser) {
        this.appUser = appUser;
    }
}
