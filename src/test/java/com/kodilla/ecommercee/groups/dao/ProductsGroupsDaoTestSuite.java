package com.kodilla.ecommercee.groups.dao;

import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.domain.ProductsGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductsGroupsDaoTestSuite {

    @Autowired
    private ProductsGroupDao productsGroupDao;

    @Test
    public void testProductsGroupsDaoSave() {
        //Given
        ProductsGroup productsGroup = new ProductsGroup("&&&& NEW Group &&&&");
        //When
        productsGroupDao.save(productsGroup);
        //Then
        int id = (int) productsGroup.getId();
        Optional<ProductsGroup> readProductsGroup = productsGroupDao.findById((long) id);
        assertTrue(readProductsGroup.isPresent());
        System.out.println(productsGroup.getName());
        //CleanUp
        //productsGroupDao.deleteById((long) id);
    }
}
