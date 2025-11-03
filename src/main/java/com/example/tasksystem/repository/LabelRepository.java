package com.example.tasksystem.repository;

import com.example.tasksystem.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
