package dev.nasim.utiltest;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.nasim.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTests {

    @Test
    void create_jwt(){
        String jwt = JwtUtil.generate(1,"employee","jaja");
        System.out.println(jwt);
    }

    @Test
    void decode_jwt(){
        DecodedJWT jwt = JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiZW1wbG95ZWUiLCJlbXBOYW1lIjoiamFqYSIsImlkIjoxfQ.A-n9l6yhHxaDaRynJuKYbiVAXjg-5DlruisALVnJweQ");
        String role = jwt.getClaim("role").asString();
        Assertions.assertEquals("employee", role);
        System.out.println(role);
    }

    @Test
    void edited_jwt(){
        try {
            DecodedJWT jwt = JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoibWFuYWdlciIsImVtcE5hbWUiOiJNYXJ5IFN1ZSJ9.LbhPASg_C7uVA8JHqDObLqqzxEWpmr-XmNe3KC3h0CQ");
            String role = jwt.getClaim("role").asString();
            System.out.println(role);
        }
        catch (SignatureVerificationException e){
            Assertions.assertTrue(true);
        }

    }
}
