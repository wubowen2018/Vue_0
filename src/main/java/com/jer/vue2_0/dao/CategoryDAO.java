package com.jer.vue2_0.dao;

import com.jer.vue2_0.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {

}
