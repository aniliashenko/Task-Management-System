package com.example.tasksystem.model;

import jakarta.persistence.*;
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

    @Column(nullable = false, length = 1000)
    private String fileName;

    @Column(nullable = false)
    private LocalDateTime uploadDate;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
