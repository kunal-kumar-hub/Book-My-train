import java.util.Map;
import java.util.HashMap;

public class UserService {

    private Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;

    public boolean registerUser(String username, String password, String fullname, String contact) {

        if (userMap.containsKey(username)) {
            System.out.println("Username already taken, please choose another");
            return false;
        }

        User user = new User(username, password, fullname, contact);
        userMap.put(username, user);

        System.out.println("Registration successful");
        return true;
    }

    public boolean loginUser(String username, String password) {

        if (!userMap.containsKey(username)) {
            System.out.println("No user found with this username");
            return false;
        }

        User user = userMap.get(username);

        if (password == null || !password.equals(user.getPassword())) {
            System.out.println("Incorrect password");
            return false;
        }

        currentUser = user;
        System.out.println("Welcome: " + currentUser.getFullname() + "!");
        return true;
    }

    public void logOutUser() {
        if (currentUser != null) {
            System.out.println("Logged out: " + currentUser.getFullname());
        }
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}