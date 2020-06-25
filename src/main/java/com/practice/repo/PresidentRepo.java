package com.practice.repo;

import com.practice.entity.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresidentRepo extends JpaRepository<President, Long> {

    @Query(value = "SELECT p FROM President p")
    List<President> findAllPresidentsWithQuery();

    @Query(value = "SELECT result FROM President result where result.id = 1")
    President findOnePresidentWithQuery();
}
