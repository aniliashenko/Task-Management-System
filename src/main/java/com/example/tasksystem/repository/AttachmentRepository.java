package com.example.tasksystem.repository;

import com.example.tasksystem.model.Attachment;
import com.example.tasksystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAllByTask(Task task);
}
