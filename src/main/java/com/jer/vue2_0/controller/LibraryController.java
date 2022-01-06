package com.jer.vue2_0.controller;

import com.jer.vue2_0.config.annotation.LogPrint;
import com.jer.vue2_0.pojo.Book;
import com.jer.vue2_0.service.BookService;
import com.jer.vue2_0.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    @CrossOrigin
    @GetMapping("/api/books")
    @LogPrint(description ="获取所有的书")
    public List<Book> list() throws Exception{
        return bookService.list();
    }

    /*
    * @Description
    * @Params {link }
    * @Return {type }
    * @CreateTime 2021/7/27
    */
    @CrossOrigin
    @PostMapping("/api/book")
    @LogPrint(description ="更新或者添加一本书")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        bookService.addOrUpdate(book);
        return book;
    }

    @CrossOrigin
    @PostMapping("/api/delete")
    @LogPrint(description ="删除一本书")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }

    @CrossOrigin
    @GetMapping("/api/categories/{cid}/books")
    @LogPrint(description ="按照分类查询书")
    public List<Book> listByCategrory(@PathVariable("cid") int cid) throws Exception{
        if (0 != cid){
            return bookService.listByCategory(cid);
        }else {
            return list();
        }
    }

    @CrossOrigin
    @GetMapping("/api/search")
    @LogPrint(description ="模糊搜索")
    public List<Book> searchResult(@RequestParam("keywords") String keywords) {
        // 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return bookService.list();
        } else {
            return bookService.Search(keywords);
        }
    }

    @CrossOrigin
    @PostMapping("api/covers")
    @LogPrint(description ="上传封面")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}
