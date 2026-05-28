package com.unir.catalogue.service.service;

import com.unir.catalogue.service.dto.BookDTO;
import com.unir.catalogue.service.exception.ResourceNotFoundException;
import com.unir.catalogue.service.model.Book;
import com.unir.catalogue.service.repository.CatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.unir.catalogue.service.specification.CatalogueSpecification;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogueService {

    private final CatalogueRepository catalogueRepository;

    public List<Book> getAll() {
        return catalogueRepository.findAll();
    }

    public Book getById(Long id) {
        return catalogueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
    }

    public Book create(BookDTO dto) {
        return catalogueRepository.save(mapToEntity(dto));
    }

    public Book update(Long id, BookDTO dto) {
        Book book = getById(id);
        updateFields(book, dto);
        return catalogueRepository.save(book);
    }

    public Book partialUpdate(Long id, BookDTO dto) {
        Book book = getById(id);
        updateFields(book, dto);
        return catalogueRepository.save(book);
    }

    public void delete(Long id) {
        catalogueRepository.deleteById(id);
    }

    public List<Book> search(
            String title,
            String author,
            String category,
            String isbn,
            Integer rating,
            Boolean visible,
            LocalDate publicationDate
    ) {
        Specification<Book> spec = CatalogueSpecification.filter(
                title, author, category, isbn, rating, visible, publicationDate
        );
        return catalogueRepository.findAll(spec);
    }

    private Book mapToEntity(BookDTO dto) {
        Book book = new Book();
        updateFields(book, dto);
        return book;
    }

    private void updateFields(Book book, BookDTO dto) {
        if (dto.getTitle() != null) book.setTitle(dto.getTitle());
        if (dto.getAuthor() != null) book.setAuthor(dto.getAuthor());
        if (dto.getCategory() != null) book.setCategory(dto.getCategory());
        if (dto.getIsbn() != null) book.setIsbn(dto.getIsbn());
        if (dto.getRating() != null) book.setRating(dto.getRating());
        if (dto.getVisible() != null) book.setVisible(dto.getVisible());
        if (dto.getPublicationDate() != null) book.setPublicationDate(dto.getPublicationDate());
        if (dto.getStock() != null) book.setStock(dto.getStock());
        if (dto.getPrice() != null) book.setPrice(dto.getPrice());
    }
}
