package com.pilog.helper;

import com.pilog.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {
    
    public static boolean isExcelFile(MultipartFile file){
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")?true:false;

    }
    public static List<Product> convertExcelToList(MultipartFile file){
        List<Product> productList=new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int rowNumber = 0;
            Iterator<Row> rows = sheet.iterator();
            
            while(rows.hasNext()){
                Row row = rows.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                Product product = new Product();
                int cid=0;

                while(cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0 -> product.setProductId((int) cell.getNumericCellValue());
                        case 1 -> product.setProductName(cell.getStringCellValue());
                        case 2 -> product.setProductDesc(cell.getStringCellValue());
                        case 3 -> product.setProductPrice(cell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cid++;
                }
                productList.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    return  productList;
    }
}
