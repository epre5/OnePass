package ee.ifl.onepass.service;

public interface PasswordService {
    String SimpleHash(String corePass, String idCode);
}
