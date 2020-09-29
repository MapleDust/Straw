package cn.tedu.straw.api;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@SpringBootTest
class StrawApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    DataSource dataSource;

    @Test
    void getConnection() throws Exception {
        System.out.println(dataSource.getConnection());
    }

    @Test
    void md5Tests() {
        // 关于DigestUtils工具类
        // 在spring系列包中的DigestUtils工具类只有md5算法的api
        // 在commons-code系列包中的DigestUtils工具类中有md系列和sha家族的多种算法的api
        String password = "1234";
        String encodePassword = DigestUtils.md5Hex(password);
        System.out.println("[md5] encode password=" + encodePassword);
    }

    @Test
    void sha256Tests() {
        String password = "1234";
        String encodePassword = DigestUtils.sha256Hex(password);
        System.out.println("[sha-256] encode password=" + encodePassword);
    }

    @Test
    void sha384Tests() {
        String password = "1234";
        String encodePassword = DigestUtils.sha384Hex(password);
        System.out.println("[sha-384] encode password=" + encodePassword);
    }

    @Test
    void sha512Tests() {
        String password = "1234";
        String encodePassword = DigestUtils.sha512Hex(password);
        System.out.println("[sha-512] encode password=" + encodePassword);
    }

    @Test
    void bcryptTests() {
        String password = "1234";
        String encodePassword = new BCryptPasswordEncoder().encode(password);
        System.out.println("[bcrypt] encode password=" + encodePassword);
    }

}
