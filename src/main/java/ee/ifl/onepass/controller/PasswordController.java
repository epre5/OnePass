package ee.ifl.onepass.controller;

import ee.ifl.onepass.model.PasswordSource;
import ee.ifl.onepass.service.PasswordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class PasswordController {
    @Resource
    PasswordService passwordService;

    @PostMapping("/getPassword")
    public String generatePassword(@RequestBody PasswordSource PasswordSource) {
        String corePass = PasswordSource.getCorePass();
        String idCode = PasswordSource.getIdCode();
        return passwordService.SimpleHash(corePass, idCode);
    }
}
