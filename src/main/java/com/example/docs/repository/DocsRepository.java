package com.example.docs.repository;

import com.example.docs.model.Docs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocsRepository extends JpaRepository<Docs, Integer> {
}
