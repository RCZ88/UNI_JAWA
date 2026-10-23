package NAR.reNHlorist;

public class User {
    public static int totalUsers = 0;

    private final String username;
    private String passwordHash;
    private String fullName;
    private String address;

    public User(String username, String passwordHash, String fullName, String address) {
        // TODO: Validate and initialize fields.
        // TODO: Update totalUsers only when creating a genuinely new account,
        // not when reconstructing an existing account from a file.
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.address = address;
    }

    public static int getTotalUsers() {
        return totalUsers;
    }

    public static void setTotalUsers(int totalUsers) {
        User.totalUsers = totalUsers;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        return String.format("%s,%s,%s,%s", username, passwordHash, fullName, address);
    }
}
