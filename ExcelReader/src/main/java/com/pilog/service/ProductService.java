package com.pilog.service;

import com.pilog.entity.Product;
import com.pilog.helper.Helper;
import com.pilog.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public class ProductService {

    @Autowired
    private IProductRepo productRepo;
    public String upLoadExcel(MultipartFile file) {
        boolean isExcelFile = Helper.isExcelFile(file);
        if(!isExcelFile)
            return ("Please Return Excel File");
        List<Product> productList = Helper.convertExcelToList(file);
        productRepo.saveAll(productList);
        return "Excel File Uploaded Successfully";
    }
    public List<Product> getExcelData(){
        return productRepo.findAll();

    }
}
