package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Book;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@EnableAutoConfiguration
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
// why the a book object is created here
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "Admin/addBook";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book) {
		bookService.save(book);

		return "redirect:/book/bookList";
	}
	
	@RequestMapping("/bookInfo")
	public String bookInfo(@RequestParam("id") Long id, Model model) {
		Optional<Book> book = bookService.findOne(id);
		model.addAttribute("book", book);
		
		return "bookInfo";
	}
	
	@RequestMapping("/updateBook")
	public String updateBook(@RequestParam("id") Long id, Model model) {
		Optional<Book> book = bookService.findOne(id);
		model.addAttribute("book", book);
		
		return "updateBook";
	}
	
	@RequestMapping(value="/updateBook", method=RequestMethod.POST)
	public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) {
		bookService.save(book);

		
		return "redirect:/book/bookInfo?id="+book.getId();
	}
	
	@RequestMapping("/bookList")
	public String bookList(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);		
		return "/bookList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String remove(@RequestParam("id") Long id, Model model
			) {
//		bookService.removeOne(Long.parseLong(id.substring(8)));
		bookService.deleteById(id);
//		List<Book> bookList = bookService.findAll();
//		model.addAttribute("bookList", bookList);
		
		return "redirect:/book/bookList";
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Book> bookList()
	 {
//		bookService.removeOne(Long.parseLong(id.substring(8)));
		//bookService.deleteById(id);
//		List<Book> bookList = bookService.findAll();
//		model.addAttribute("bookList", bookList);
		return bookService.findAll();
	}


}
