package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CategoryEntity;



public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	<S extends CategoryEntity> S save(S entity);

	List<CategoryEntity> findAll();

	List<CategoryEntity> findByNameContaining(String name);

	Page<CategoryEntity> findByNameContaining(String name, Pageable pageable);

	
}