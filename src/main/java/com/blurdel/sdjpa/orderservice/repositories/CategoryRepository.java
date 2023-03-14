package com.blurdel.sdjpa.orderservice.repositories;

import com.blurdel.sdjpa.orderservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
