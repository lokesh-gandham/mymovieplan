package com.nmscinemas.controller;

import java.io.IOException;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmscinemas.services.*;
import com.nmscinemas.config.ImageUtil;
import com.nmscinemas.entities.Product;
import com.nmscinemas.entities.ProductImage;
import com.nmscinemas.services.ProductService;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ObjectMapper objectMapper;
//add new product
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/add/product")
	public ResponseEntity<?> addNewMovie(@RequestParam("product") String product, 
										   @RequestParam("image") MultipartFile file) throws IOException{
		
		ProductImage img = new ProductImage();
		img.setName(file.getOriginalFilename());
		img.setType(file.getContentType());
		img.setImageData(ImageUtil.compressImage(file.getBytes()));
		Product p = null;
		try {
			p = objectMapper.readValue(product,Product.class);
			p.setProductImage(img);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		}
		Product saveProduct = this.productService.addMovie(p);
		return ResponseEntity.ok(saveProduct);
	}
	
	//update existing product
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/update/product/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") Long id,@Valid @RequestBody Product product){
		Product updateProduct = this.productService.findMovie(id);
		updateProduct.setName(product.getName());
		updateProduct.setCategory(product.getCategory());
		updateProduct.setDescription(product.getDescription());
		updateProduct.setTotalAvailable(product.getTotalAvailable());
		updateProduct.setPrice(product.getPrice());
		this.productService.addMovie(updateProduct);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	//find product by id
	@GetMapping("get-product/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") Long id){
		Product product = this.productService.findMovie(id);
		ProductImage img =  new ProductImage();
		img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
		img.setImgId(product.getProductImage().getImgId());
		img.setName(product.getProductImage().getName());
		img.setType(product.getProductImage().getType());
		product.setProductImage(img);
		return ResponseEntity.ok(product);
	}
	
	//find all products
	@GetMapping("/get/all-products")
	public ResponseEntity<?> getAllMovies(){
		List<Product> allProducts = this.productService.findAllMovies();
		allProducts.forEach(product -> {
			ProductImage img =  new ProductImage();
			img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
			img.setImgId(product.getProductImage().getImgId());
			img.setName(product.getProductImage().getName());
			img.setType(product.getProductImage().getType());
			product.setProductImage(img);
		});
		if(allProducts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(allProducts);
		}
	}
	
	@GetMapping(value = {"/get/products/{name}"})
	public ResponseEntity<?> getMovieByName(@PathVariable("name") String name,@PathVariable("name") String salt){
		List<Product> products = this.productService.findByName(name);
		products.forEach(product -> {
			ProductImage img =  new ProductImage();
			img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
			img.setImgId(product.getProductImage().getImgId());
			img.setName(product.getProductImage().getName());
			img.setType(product.getProductImage().getType());
			product.setProductImage(img);
		});
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(products);
		}
	}
	@GetMapping("/get/products-by-category/{category}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable("category") String category){
		List<Product> products = this.productService.findMovieByCategory(category);
		products.forEach(product -> {
			ProductImage img =  new ProductImage();
			img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
			img.setImgId(product.getProductImage().getImgId());
			img.setName(product.getProductImage().getName());
			img.setType(product.getProductImage().getType());
			product.setProductImage(img);
		});
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(products);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
		this.productService.deleteMovieById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/set-availability/product/{id}")
	public ResponseEntity<?> setAvailability(@PathVariable("id") Long id, @RequestBody Product product){
		Product updateProduct = this.productService.findMovie(id);
		updateProduct.setAvailable(product.isAvailable());
		this.productService.addMovie(updateProduct);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/get/{name}")
	public ResponseEntity<?> getAvailable(@PathVariable("name") String name){
		List<Product> products = this.productService.findTrueMovie(name);
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(products);
		}
	}
	
	
	
}
