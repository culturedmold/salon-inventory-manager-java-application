package com.example.saloninventorymanager.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class PasswordHasher {
    public static byte[] hashPassword(String s) {
        try {
            byte[] hashed;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            hashed = md.digest(s.getBytes(StandardCharsets.UTF_8));
            return hashed;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
