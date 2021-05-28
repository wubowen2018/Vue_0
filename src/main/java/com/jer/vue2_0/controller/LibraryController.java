package com.jer.vue2_0.controller;

import com.jer.vue2_0.pojo.Book;
import com.jer.vue2_0.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    public List<Book> list() throws Exception{
        return bookService.list();
    }

    @PostMapping("/api/book")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }

    @GetMapping("/api/categoraries/{cid}/books")
    public List<Book> listByCategrory(@PathVariable("cid") int cid) throws Exception{
        if (0 != cid){
            return bookService.listByCategory(cid);
        }else {
            return list();
        }
    }

  


}
