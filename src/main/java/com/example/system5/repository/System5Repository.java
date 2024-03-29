package com.example.system5.repository;

import com.example.system5.model.System5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface System5Repository extends JpaRepository<System5, Integer> {
    List<System5> findAllByUserIdAndYear(Integer user_id, Integer year);
    List<System5> findAllByUserId(Integer user_id);
    List<System5> findByUserIdOrderBySystem5Id(int id);
    List<System5> findAllByMonthAndYear(String month, Integer year);
    System5 findByMonthAndUserIdAndYear(String month, Integer id, Integer year);
    System5 findByMonthAndYearAndUserId(String month, Integer year, Integer id);

    @Query(nativeQuery = true,
    value = "SELECT DISTINCT year FROM sys_system5")
    List<Integer> getYears();
}
