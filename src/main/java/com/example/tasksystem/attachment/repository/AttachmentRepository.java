package com.example.tasksystem.attachment.repository;

import com.example.tasksystem.attachment.model.Attachment;
import com.example.tasksystem.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAllByTask(Task task);
}
