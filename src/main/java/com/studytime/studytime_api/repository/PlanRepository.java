package com.studytime.studytime_api.repository;

import com.studytime.studytime_api.entity.Plan;
import com.studytime.studytime_api.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    boolean existsByTeacherAndTitle(Teacher teacher, String title);

    List<Plan> findAllByTeacherId(Long teacherId);
}
