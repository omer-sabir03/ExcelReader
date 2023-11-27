package com.pilog.controller;

import com.pilog.entity.Product;
import com.pilog.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/uploadexcel")
    public ResponseEntity<String> upLoadExcel(@Param("file") MultipartFile file){

        String s = productService.upLoadExcel(file);
//        return ResponseEntity.ok(s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> getExcelData(){
        return new ResponseEntity<>(productService.getExcelData(),HttpStatus.OK);
    }
}
