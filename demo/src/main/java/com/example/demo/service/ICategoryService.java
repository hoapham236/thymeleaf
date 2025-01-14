package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.CategoryEntity;

public interface ICategoryService {
	Page<CategoryEntity> findByNameContaining(String name, Pageable pageable);

	List<CategoryEntity> findByNameContaining(String name);

	void deleteAll();

	void delete(CategoryEntity entity);

	void deleteById(Long id);

	long count();

	<S extends CategoryEntity> Optional<S> findOne(Example<S> example);

	Optional<CategoryEntity> findById(Long id);

	List<CategoryEntity> findAllById(Iterable<Long> ids);

	List<CategoryEntity> findAll(Sort sort);

	Page<CategoryEntity> findAll(Pageable pageable);

	List<CategoryEntity> findAll();

	<S extends CategoryEntity> S save(S entity);
	
}
