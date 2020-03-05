package com.practice.repo;

import com.practice.entity.President;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresidentRepo extends JpaRepository<President, Long> {


}
