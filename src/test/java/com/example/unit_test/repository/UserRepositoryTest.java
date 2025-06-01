package com.example.unit_test.repository;

import com.example.unit_test.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setName("John Doe");
        List<User> users1 = userRepository.findAll();
        userRepository.save(user);

        List<User> users2 = userRepository.findAll();

        assertThat(users2).hasSize(users1.size()+1);
        assertThat(users2.get(users2.size()-1).getName()).isEqualTo("John Doe");
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setName("Jane Doe");
        userRepository.save(user);

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("John Smith");
        userRepository.save(user);

        userRepository.delete(user);
        List<User> users = userRepository.findAll();
        assertThat(users).doesNotContain(user);
    }
}