package com.example.priziv.repository;

import com.example.priziv.model.Priziv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizivRepository extends JpaRepository<Priziv, Integer> {
    List<Priziv> findAllByMonthYearId(Integer m);
}
