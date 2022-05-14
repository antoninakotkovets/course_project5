package com.ajuncodes.coursework;

import com.ajuncodes.coursework.controller.ComparisonController;
import com.ajuncodes.coursework.controller.PersonalInfController;
import com.ajuncodes.coursework.controller.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestingWebApplicationTests {

    @Autowired
    private ComparisonController comparisonController;

    @Autowired
    private PersonalInfController personalInfController;

    @Autowired
    private UsersController usersController;

    @Test
    public void testUserControllerLoadContext() {
        assertThat(usersController).isNotNull();
    }

    @Test
    public void testComparisonControllerLoadContext() {
        assertThat(comparisonController).isNotNull();
    }

    @Test
    public void testPersonalInfControllerLoadContext() {
        assertThat(personalInfController).isNotNull();
    }
}
