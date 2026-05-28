package com.unir.catalogue.service.repository;

import com.unir.catalogue.service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
