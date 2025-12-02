package com.example.tasksystem.attachment.model;

import com.example.tasksystem.task.model.Task;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "attachments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE attachments SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(nullable = false)
    private String dropBoxFileId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private LocalDateTime uploadDate;

    @Column(nullable = false)
    private Long size;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
