package com.training.product.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.training.product.entity.ProductEntity;
import com.training.product.model.ProductModel;
import com.training.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductEntity> save(@RequestBody ProductModel data) {
		ProductEntity save = productService.save(data);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public ProductEntity findById(@PathVariable Long id) {
		Optional<ProductEntity> findById = productService.findById(id);
		return findById.isPresent()? findById.get(): null;
	}
	
	@GetMapping
	public ArrayList<ProductEntity> getAll() {
		ArrayList<ProductEntity> list = new ArrayList();
		Iterable<ProductEntity> findAll = productService.findAll();
		findAll.forEach(item -> list.add(item));
		return list;
	}
	
	
}
