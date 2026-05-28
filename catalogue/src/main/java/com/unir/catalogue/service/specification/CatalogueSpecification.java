package com.unir.catalogue.service.specification;

import com.unir.catalogue.service.model.Book;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CatalogueSpecification {

    public static Specification<Book> filter(
            String title,
            String author,
            String category,
            String isbn,
            Integer rating,
            Boolean visible,
            LocalDate publicationDate
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (title != null)
                predicates.add(cb.like(root.get("title"), "%" + title + "%"));

            if (author != null)
                predicates.add(cb.like(root.get("author"), "%" + author + "%"));

            if (category != null)
                predicates.add(cb.equal(root.get("category"), category));

            if (isbn != null)
                predicates.add(cb.equal(root.get("isbn"), isbn));

            if (rating != null)
                predicates.add(cb.equal(root.get("rating"), rating));

            if (visible != null)
                predicates.add(cb.equal(root.get("visible"), visible));

            if (publicationDate != null)
                predicates.add(cb.equal(root.get("publicationDate"), publicationDate));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
