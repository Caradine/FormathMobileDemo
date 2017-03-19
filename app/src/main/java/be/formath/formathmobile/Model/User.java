package be.formath.formathmobile.Model;

import java.util.ArrayList;

/**
 * Created by v-dawagne on 30-08-16.
 */
public class User {
    private String userName, firstName, lastName, hashedPassword, distantIdentifier;
    private ArrayList<Medal> medalList;

    public ArrayList<Medal> getMedalList() {
        return medalList;
    }

    public void setMedalList(ArrayList<Medal> medalList) {
        this.medalList = medalList;
    }

    public String getDistantIdentifier() {
        return distantIdentifier;
    }

    public void setDistantIdentifier(String distantIdentifier) {
        this.distantIdentifier = distantIdentifier;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User() {
        this.medalList = new ArrayList<>();
    }
}
