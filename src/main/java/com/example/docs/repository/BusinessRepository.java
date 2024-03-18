package com.example.docs.repository;

import com.example.docs.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
    @Query("select b from Business b order by b.businessId")
    List<Business> findAllOrderById();


}
