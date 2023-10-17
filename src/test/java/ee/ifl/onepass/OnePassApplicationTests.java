package ee.ifl.onepass;

import ee.ifl.onepass.controller.PasswordController;
import ee.ifl.onepass.model.PasswordSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnePassApplicationTests {
    @Autowired
    PasswordController passwordController;

    @Test
    void getPwd() {
        PasswordSource passwordSource = new PasswordSource();
        passwordSource.setCorePass("zy");
        passwordSource.setIdCode("qq");
        String pwd = passwordController.generatePassword(passwordSource);
        System.out.println(pwd);
    }

}
