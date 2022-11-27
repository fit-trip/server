package com.example.mapp.schedule.repository;

import com.example.mapp.schedule.model.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAllBySharedStatus(boolean sharedStatus);
    List<Schedule> findAllByAppUser_Id(String userId);
}
