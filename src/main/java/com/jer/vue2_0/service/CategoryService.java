package com.jer.vue2_0.service;

import com.jer.vue2_0.dao.CategoryDAO;
import com.jer.vue2_0.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list(){
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll();
    }

    public Category get(int id) {
        Category c = categoryDAO.findById(id).orElse(null);
        return c;
    }

}
