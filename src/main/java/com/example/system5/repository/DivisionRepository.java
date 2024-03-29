package com.example.system5.repository;

import com.example.system5.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface DivisionRepository extends JpaRepository<Division, Integer> {
    @Override
    List<Division> findAll();
}
