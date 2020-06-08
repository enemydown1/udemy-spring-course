package com.udemy.spring.course.resources;

import com.udemy.spring.course.domain.Category;
import com.udemy.spring.course.domain.Product;
import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.dto.CategoryDTO;
import com.udemy.spring.course.dto.ProductDTO;
import com.udemy.spring.course.resources.utils.URL;
import com.udemy.spring.course.services.ProductService;
import com.udemy.spring.course.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id){

        Product obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="categories", defaultValue="") String categories,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction){
        String nameDecoded =  URL.decodeParameter(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDto);
    }

}
