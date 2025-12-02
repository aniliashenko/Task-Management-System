package com.example.tasksystem.task.filter;

import com.example.tasksystem.task.dto.TaskRequestDto;
import com.example.tasksystem.task.model.Task;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    public static Specification<Task> filterTasks(TaskRequestDto filter) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null && !filter.name().isEmpty()) {
                predicates.add(builder.like(
                        builder.lower(root.get("name")),
                        "%" + filter.name().toLowerCase() + "%"
                ));
            }

            if (filter.description() != null && !filter.description().isEmpty()) {
                predicates.add(builder.like(
                        builder.lower(root.get("description")),
                        "%" + filter.description().toLowerCase() + "%"
                ));
            }

            if (filter.projectId() != null) {
                predicates.add(builder.equal(root.get("project").get("id"), filter.projectId()));
            }

            if (filter.userId() != null) {
                predicates.add(builder.equal(root.get("user").get("id"), filter.userId()));
            }

            if (filter.priority() != null) {
                predicates.add(builder.equal(root.get("priority"), filter.priority()));
            }

            if (filter.status() != null) {
                predicates.add(builder.equal(root.get("status"), filter.status()));
            }

            if (filter.dueDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dueDate"), filter.dueDate()));
            }

            if (filter.search() != null && !filter.search().isEmpty()) {
                final String likePattern = "%" + filter.search().toLowerCase() + "%";
                final Predicate namePredicate = builder.like(builder.lower(root.get("name")), likePattern);
                final Predicate descPredicate = builder.like(builder.lower(root.get("description")), likePattern);
                predicates.add(builder.or(namePredicate, descPredicate));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
