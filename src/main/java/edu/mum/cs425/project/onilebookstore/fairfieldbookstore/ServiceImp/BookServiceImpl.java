package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.ServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Book;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.BookRepository;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }

    @Override
    public Optional<Book> findOne(Long id) {
        return bookRepository.findById(id);
    }


    public List<Book> findByCategory(String category) {
        List<Book> bookList = bookRepository.findByCategory(category);

        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }

    public List<Book> blurrySearch(String title) {
        List<Book> bookList = bookRepository.findByTitleContaining(title);
        List<Book> activeBookList = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isActive()) {
                activeBookList.add(book);
            }
        }

        return activeBookList;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void removeOne(Long id) {
        bookRepository.removeById(id);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
