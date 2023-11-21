package ee.ifl.onepass.service.impl;

import ee.ifl.onepass.service.PasswordService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class PasswordServiceImpl implements PasswordService {
    public String SimpleHash(String input1, String input2) {
        long hash = (input1 + input2).hashCode();
        SecureRandom rand = new SecureRandom();
        rand.setSeed(hash);
//        Random rand = new Random(hash);
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
