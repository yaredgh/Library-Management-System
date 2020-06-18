package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service;

import java.util.List;
import java.util.Optional;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    List<Book> findAll();

    Optional<Book> findOne(Long id);

    List<Book> findByCategory(String category);

    List<Book> blurrySearch(String title);

    void save(Book book);


    void removeOne(Long id);

    void deleteById(Long id);
}
