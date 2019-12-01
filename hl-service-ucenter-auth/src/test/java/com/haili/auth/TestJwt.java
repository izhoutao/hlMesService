package com.haili.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {

    //创建jwt令牌
    @Test
    public void testCreateJwt(){
        //密钥库文件
        String keystore = "hl.keystore";
        //密钥库的密码
        String keystore_password = "hailistorepass";

        //密钥库文件路径
        ClassPathResource classPathResource = new ClassPathResource(keystore);
        //密钥别名
        String alias  = "hlkey";
        //密钥的访问密码
        String key_password = "hailikeypass";
        //密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource,keystore_password.toCharArray());
        //密钥对（公钥和私钥）
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, key_password.toCharArray());
        //获取私钥
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        //jwt令牌的内容
        Map<String,String> body = new HashMap<>();
        body.put("name","haili");
        String bodyString = JSON.toJSONString(body);
        //生成jwt令牌
        Jwt jwt = JwtHelper.encode(bodyString, new RsaSigner(aPrivate));
        //生成jwt令牌编码
        String encoded = jwt.getEncoded();
        System.out.println(encoded);

    }

    //校验jwt令牌
    @Test
    public void testVerify(){
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhtkxOczne250bzvH0hUA9nvMbeahwetyD1gGwOjoTvuK0Xv7ziHniqD9gqHNWJiedw+x7ficv0vdpFMqId/RQHP9XlLhZtp1gzmj4O/Vql65IMpeYc575QMPBBDQT1225y4h4hAstVcLBjjuLg9GaY66JTtyYxryjL6wVSct+VKDk5A9R56JyaAT2PAD8EvPJMpZHmv/E8EtAk+/moi07uoEQoAzrpLvi9D5qNdpLYrQevF1/bg/VbaecMJRvuUiiJLvGnhpwnXw0YC/8IsZN1rcaRA0gd2FmYNpJgyX5iLCgCwYtH4cuLWmQWtkZTi2vXeuY1KzQmqh/L9C6425AwIDAQAB-----END PUBLIC KEY-----";
        //jwt令牌
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiaGFpbGkifQ.IwhtHb2H8axMbti2AdgY1SxMMHgUI7MTLoDv6t0yG8LDNIviNaAwrDHnQnCGwvQvZDtijBARjh_1mRrnyX5uwfz-ws1UlIA1lUWYH2TDSQJjFAHHBiCsmsOP49E6BfkF921-hmxKV0ZoOzLzJz3EUZ4yIoSY_cyqbjFVbxqmJpZOMXq0ohwJSM8GC5E0OAY1vVz6yHFtmV73UddGX1OXQSPXhkfa_VJ4LcTtF2EhE_EX5JhMRwEYyRVDvyeUopCYYwaz0VbMLsahDylsBgZXxAK5C3-VKHP9ZK6y90VsXb8BZeuA-bF4WuCyolYvRXj8j79LFls3W6lqncD222DmUw";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
