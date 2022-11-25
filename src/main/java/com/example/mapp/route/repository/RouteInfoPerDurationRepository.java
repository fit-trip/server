package com.example.mapp.route.repository;

import com.example.mapp.route.model.RouteInfoPerDuration;
import com.example.mapp.route.model.id.RouteInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteInfoPerDurationRepository extends JpaRepository<RouteInfoPerDuration, RouteInfoId> {
    List<RouteInfoPerDuration> findAllByScheduleIdOrderByOrder(Integer scheduleId);
}
