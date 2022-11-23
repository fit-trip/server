package com.example.mapp.route.repository;

import com.example.mapp.route.model.Location;
import com.example.mapp.route.model.id.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
