package com.wpa.repository;

import com.wpa.entity.BusinessKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessKnowledgeRepository extends JpaRepository<BusinessKnowledge, Long> {
    @Query(value = "SELECT * FROM business_knowledge WHERE type=%:type%", nativeQuery = true)
    List<BusinessKnowledge> findAllByType(@Param("type") String type);
}
