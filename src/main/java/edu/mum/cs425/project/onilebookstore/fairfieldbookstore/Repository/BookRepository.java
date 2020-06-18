package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByCategory(String category);

    List<Book> findByTitleContaining(String title);

    Optional<Book> findById(Long id);

    void removeById(Long id);

}