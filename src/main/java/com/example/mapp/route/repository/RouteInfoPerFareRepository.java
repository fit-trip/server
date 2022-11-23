package com.example.mapp.route.repository;

import com.example.mapp.route.model.RouteInfoPerFare;
import com.example.mapp.route.model.id.RouteInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteInfoPerFareRepository extends JpaRepository<RouteInfoPerFare, RouteInfoId> {
}
