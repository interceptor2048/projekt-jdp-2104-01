package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.ProductsGroupDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsGroupDaoTestSuite {

    @Autowired
    private ProductsGroupDao groupDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void testSaveGroup(){
        //Given
        ProductsGroup group = new ProductsGroup("TestGroup");
        //When
        groupDao.save(group);
        long id = group.getId();
        List<ProductsGroup> groupList = (List<ProductsGroup>) groupDao.findAll();
        //Then
        assertEquals(1,groupList.size());
        //Clean up
        try{
            groupDao.deleteById(id);
        } catch(Exception e){

        }
    }

    @Test
    public void testFindById(){
        //Given
        ProductsGroup group = new ProductsGroup("TestGroup");
        ProductsGroup group2 = new ProductsGroup("TestGroup");
        //When
        groupDao.save(group);
        groupDao.save(group2);
        long id = group.getId();
        long id2 = group2.getId();
        Optional<ProductsGroup> foundGroup = groupDao.findById(id);
        //Then
        assertTrue(foundGroup.isPresent());
        //Clean up
        try{
            groupDao.deleteById(id);
            groupDao.deleteById(id2);
        } catch(Exception e){

        }
    }

    @Test
    public void testFindAll(){
        //Given
        ProductsGroup group = new ProductsGroup("TestGroup");
        ProductsGroup group2 = new ProductsGroup("TestGroup");
        //When
        groupDao.save(group);
        groupDao.save(group2);
        long id = group.getId();
        long id2 = group2.getId();
        List<ProductsGroup> foundList = (List<ProductsGroup>) groupDao.findAll();
        //Then
        assertEquals(2,foundList.size());
        //Clean-up
        try{
            groupDao.deleteById(id);
            groupDao.deleteById(id2);
        } catch(Exception e){

        }
    }
}
