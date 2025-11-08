package com.example.tasksystem.project.filter;

import com.example.tasksystem.project.dto.ProjectRequestDto;
import com.example.tasksystem.project.model.Project;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {

    public static Specification<Project> filterProjects(ProjectRequestDto filter) {
        return ((root, query, builder) -> {
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

            if (filter.startDate() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), filter.startDate()));
            }

            if (filter.endDate() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("endDate"), filter.endDate()));
            }

            if (filter.status() != null) {
                predicates.add(builder.equal(root.get("status"), filter.status()));
            }

            if (filter.search() != null && !filter.search().isEmpty()) {
                final String likePattern = "%" + filter.search().toLowerCase() + "%";
                final Predicate namePredicate = builder.like(builder.lower(root.get("name")), likePattern);
                final Predicate descPredicate = builder.like(builder.lower(root.get("description")), likePattern);
                predicates.add(builder.or(namePredicate, descPredicate));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
