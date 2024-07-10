package com.system.billingSystem.repository;

import com.system.billingSystem.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepoTest {

    @Autowired
    private UserRepository userRepo;

    private static User user;

    @BeforeAll
    public static void setUp() {
        user = User.builder()
                .id(1L)
                .name("user name")
                .email("user@hotmail.com")
                .password("userPassword")
                .company(null)
                .build();
    }

    @AfterEach
    public void tearDown() {
        userRepo.deleteAll();
    }

    @Test
    public void testSaveUser() {
        User newUser = userRepo.save(user);
        assertNotNull(newUser);
        assertEquals(user.getEmail(), newUser.getEmail());
    }

    @Test
    public void testFindById() {
        User newUser = userRepo.save(user);
        assertNotNull(newUser);
        User foundUser = userRepo.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getId(), foundUser.getId());
    }

    @Test
    public void testDeleteUser() {
        User newUser = userRepo.save(user);
        assertNotNull(newUser);
        User foundUser = userRepo.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getId(), foundUser.getId());
        userRepo.deleteById(newUser.getId());
        assertNull(userRepo.findById(newUser.getId()).orElse(null));
    }

    @Test
    public void testUpdateUser() {
        User newUser = userRepo.save(user);
        assertNotNull(newUser);
        newUser.setName("new name");
        userRepo.save(newUser);
        User foundUser = userRepo.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getName(), foundUser.getName());
    }
}
