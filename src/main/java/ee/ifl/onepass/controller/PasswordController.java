package ee.ifl.onepass.controller;

import ee.ifl.onepass.model.PasswordSource;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class PasswordController {

    @PostMapping("/getPassword")
    public String generatePassword(@RequestBody PasswordSource PasswordSource) {
        String corePass = PasswordSource.getCorePass();
        String idCode = PasswordSource.getIdCode();
        long hash = (corePass + idCode).hashCode();
        Random rand = new Random(hash);

        StringBuilder sb = new StringBuilder();
        sb.append((char) ('A' + rand.nextInt(26)));
        sb.append((char) ('a' + rand.nextInt(26)));
        sb.append((char) ('0' + rand.nextInt(10)));

        for (int i = 3; i < 16; i++) {
            int nextChar = '0' + rand.nextInt(62);
            if (nextChar > '9') {
                nextChar += 7;
                if (nextChar > 'Z') {
                    nextChar += 6;
                }
            }
            sb.append((char) nextChar);
        }
        replaceChars(sb);
        return sb.toString();
    }

    private void replaceChars(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (ch == 'i' || ch == 'I' || ch == 'l' || ch == 'L') {
                sb.setCharAt(i, 'Z');
            }
        }
    }
}
