package com.example.tasksystem.label.repository;

import com.example.tasksystem.label.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
