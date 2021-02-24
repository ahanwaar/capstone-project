package com.worldnavigator.webapp.repositories;

import com.worldnavigator.webapp.entities.MazeTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MazeTemplateRepository extends JpaRepository<MazeTemplate, Long> {}
