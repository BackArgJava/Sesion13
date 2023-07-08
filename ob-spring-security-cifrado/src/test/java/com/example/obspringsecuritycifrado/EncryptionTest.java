package com.example.obspringsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class EncryptionTest {
    /* BCrypt que se genera su propio salt de 16 bytes
     *
     * El resultado de cifrar con bcrypt sera un string de 60 caracteres
     *
     *
     * $a version
     *$10 fuerza en(valor que va  de 4 a 31 , por defecto vale 10)
     *
     * los 22 siguientes caracteres son el salt  generado
     *
     *
     *
     */

    @Test
    void bcryptTest() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("admin");
        System.out.println(hashedPassword);

        boolean matches = passwordEncoder.matches("admin", hashedPassword);
        System.out.println(matches);
    }

    @Test
    void bcryptCheckMultiplePassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }

    }
    @Test
    void pbkdf2() {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        for (int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }

    }
    @Test
    void scrypt (){

        SCryptPasswordEncoder passwordEncoder = new SCryptPasswordEncoder();
        for (int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));

        }

    }
    @Test
    void springPasswordEncoder(){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("argon2", new Argon2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
//        encoders.put("noop", NoOpPasswordEncoder.getInstance());

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);


        String hashedPassword = passwordEncoder.encode("admin");
        System.out.println(hashedPassword);

    }
    }