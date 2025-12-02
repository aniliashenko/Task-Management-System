package com.example.tasksystem.comment.repository;

import com.example.tasksystem.comment.model.Comment;
import com.example.tasksystem.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTask(Task task);
}
