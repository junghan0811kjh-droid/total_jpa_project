package com.my.total_jpa_back.repository;

import com.my.total_jpa_back.entity.Gender;
import com.my.total_jpa_back.entity.Users;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원 전체 조회")
    void findAllTest() {
        // given

        // when
        List<Users> users = userRepository.findAll();
        // then
        assertThat(users.size()).isEqualTo(500);
    }

    @Test
    @DisplayName("성별 조회")
    void 성별_조회() {
        List<Users> users = userRepository.findByGender(Gender.Male);
        for (Users user : users) {
            log.info("name = {}, gender = {}", user.getName(), user.getGender());
        }
    }

    @Test
    @DisplayName("이름에 kim 을 포함하는 자료 검색")
    void 킴찾기() {
        List<Users> users = userRepository.findByNameContaining("kim");
        for (Users user : users) {
            log.info("name = {}", user.getName());
        }
    }

    @Test
    @DisplayName("findByLikeColor")
    void findByLikeColor() {
        List<Users> users = userRepository.findByLikeColor("blue");
        for (Users user : users) {
            log.info("name = {}, color = {}", user.getName(), user.getLikeColor());
        }
    }

    @Test
    @DisplayName("findByLikeColorAndGender")
    void findByLikeColorAndGender() {
        List<Users> users = userRepository.findByLikeColorAndGender("blue", Gender.Female);
        for (Users user : users) {
            log.info("name = {}, gender= {}, color = {}", user.getName(), user.getGender(), user.getLikeColor());
        }
    }
}