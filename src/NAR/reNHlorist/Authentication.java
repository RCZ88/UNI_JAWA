package NAR.reNHlorist;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

public class Authentication {
    private final Map<String, User> registeredUsers;

    public Authentication() {
        // TODO: Initialize the map.
        registeredUsers = new HashMap<>();
    }

    private boolean isLetter(char c){
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    private boolean isNumber(char c){
        return c >= '0' && c<= '9';
    }

    private boolean isValidChar(char c){
        return isLetter(c) || isNumber(c) || c == '.' || c == '_';
    }

    public boolean isValidUsername(String username) {
        if (username.isEmpty() || username.length() > 16) return false;
        if (!isLetter(username.charAt(0))) return false;
        for (char c : username.toCharArray()) {
            if(!isValidChar(c)) return false;
        }
        return true;
    }

    public boolean isValidPassword(String password) {
        if(password.length() < 6) return false;
        boolean containDigit = false;
        boolean containLetter = false;
        boolean uppercaseLetter = false;
        for (char c : password.toCharArray()) {
            if(isLetter(c)){
                containLetter = true;
                if(c >= 'A' && c <= 'Z'){
                    uppercaseLetter = true;
                }
            }else if(isNumber(c)){
                containDigit = true;
            }
            if(containDigit && containLetter && uppercaseLetter) return true;
        }
        return false;
    }

    public boolean usernameExists(String username) {
        return registeredUsers.containsKey(username);
    }

    public User register(String username, String password, String fullName, String address) {
        if(usernameExists(username) || !isValidUsername(username)) {
            System.out.println("INVALID USERNAME or ALREADY EXIST");
            return null;
        }
        if(!isValidPassword(password)) {
            System.out.println("INVALID PASSWORD STRENGTH!");
            return null;
        }

        return new User(username, password, fullName, address);
    }

    public User login(String username, String password) {
        if(!usernameExists(username)){
            System.out.println("Username not found!");
            return null;
        }
        if(!hashPassword(registeredUsers.get(username).getPasswordHash()).equals(password)){
            System.out.println("Invalid Password");
            return null;
        }
        return registeredUsers.get(username);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, User> getRegisteredUsers() {
        return Map.copyOf(registeredUsers);
    }

    public void loadUsers() {
        FileStorage fs = new FileStorage();
        try {
            ArrayList<String> lines = (ArrayList<String>) fs.readLines(fs.usersFile);
            for (String line : lines) {
                String[]items = line.split(",");
                registeredUsers.put(items[0], new User(items[0], items[1], items[2], items[3]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(User user) {
        FileStorage fs=  new FileStorage();
        try {
            fs.appendLine(fs.usersFile, user.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
