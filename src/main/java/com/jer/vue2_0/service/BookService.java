package com.jer.vue2_0.service;

import com.jer.vue2_0.dao.BookDAO;
import com.jer.vue2_0.pojo.Book;
import com.jer.vue2_0.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDAO bookDAO;
    @Autowired
    CategoryService categoryService;


    public List<Book> list(){
        return  bookDAO.findAll();
    }

    
    public void addOrUpdate(Book book) {
        //这里注意一下 save() 方法的作用是，当主键存在时更新数据，当主键不存在时插入数据。
        bookDAO.save(book);
    }

    public void deleteById(int id) {
        bookDAO.deleteById(id);
    }

    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }

    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }


}
