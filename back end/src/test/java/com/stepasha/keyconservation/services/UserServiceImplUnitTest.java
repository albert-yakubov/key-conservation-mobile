package com.stepasha.keyconservation.services;

import com.stepasha.keyconservation.KeyConservationApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KeyConservationApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplUnitTest {

    // todo 2T set up repos that are used for restaurants
    @Autowired
    private UserService userService;

    //todo 3T set up Mockito
    @Before
    public void AsetUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void BtearDown() throws Exception {
    }

    //todo 4 expected 3 restaurants to return
    @Test
    public void CfindAll() {
        assertEquals(5, userService.findAll(Pageable.unpaged()).size());
    }

    @Test
    public void DfindUserById() {
        assertEquals("admin123", userService.findUserById(37L).getUsername());
    }

    @Test
    public void EfindUserByName() {
        assertEquals("admin2", userService.findByName("admin2").getUsername());
    }
}