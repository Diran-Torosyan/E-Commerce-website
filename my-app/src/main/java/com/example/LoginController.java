package com.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private static final String USER_CREDS_FILE_PATH = "userCreds.txt";

    public static boolean login(String username, String password) {
        Map<String, String> credentials = loadCredentials();
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }

    private static Map<String, String> loadCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_CREDS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    credentials.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

}
