package com.example.priziv.repository;

import com.example.priziv.model.PrizivMonthYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizivMonthYearRepository extends JpaRepository<PrizivMonthYear, Integer> {
    PrizivMonthYear findFirstByOrderByIdDesc();
}
