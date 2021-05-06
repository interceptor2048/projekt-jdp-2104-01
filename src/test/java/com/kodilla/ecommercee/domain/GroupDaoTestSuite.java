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
        Group group = new Group("test name");
        List<Product> products = new ArrayList<>();
        Product product = new Product("test1","test2",12.1);
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
}
