package com.example.coachticketbookingapp.data;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<String, String> userData = new HashMap<>();

    public static boolean isEmailOrPhoneExists(String email, String phoneNumber) {
        return userData.containsKey(email) || userData.containsKey(phoneNumber);
    }

    public static void addUser(String email, String phoneNumber, String password, String fullName) {
        userData.put(email, fullName);
        userData.put(phoneNumber, fullName);
    }
}
