package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.ProductsGroupDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupDaoTestSuite {

    @Autowired
    private ProductsGroupDao groupDao;

    @Test
    public void testSaveGroup(){
        //Given
        ProductsGroup group = makeGroup("TestGroup");
        List<Product> products = new ArrayList<>();
        Product product = makeProduct("test1","test2",BigDecimal.valueOf(12.1));
        product.setProductsGroup(group);
        products.add(product);
        group.setProducts(products);
        long id = group.getId();
        //When
        groupDao.save(group);
        List<ProductsGroup> groupList = (List<ProductsGroup>) groupDao.findAll();
        //Then
        assertEquals(1,groupList.size());
        //Clean up
        try{
            groupDao.deleteById(id);
        } catch(Exception e){

        }
    }

    private ProductsGroup makeGroup(String name){
        ProductsGroup group = new ProductsGroup();
        group.setName(name);
        return group;
    }

    private Product makeProduct(String name, String description, BigDecimal price){
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
