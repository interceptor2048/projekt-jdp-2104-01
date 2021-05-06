package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.GroupRepository;
import com.kodilla.ecommercee.dao.GroupDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupDaoTestSuite {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void testSaveGroup(){
        //Given
        Group group = makeGroup("TestGroup");
        List<Product> products = new ArrayList<>();
        Product product = makeProduct("test1","test2",12.1);
        product.setGroup(group);
        products.add(product);
        group.setProducts(products);
        long id = group.getId();
        //When
        groupDao.save(group);
        List<Group> groupList = (List<Group>) groupDao.findAll();
        //Then
        assertEquals(1,groupList.size());
        //Clean up
        try{
            groupDao.deleteById(id);
        } catch(Exception e){

        }
    }

    private Group makeGroup(String name){
        Group group = new Group();
        group.setName(name);
        return group;
    }

    private Product makeProduct(String name,String description,double price){
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
