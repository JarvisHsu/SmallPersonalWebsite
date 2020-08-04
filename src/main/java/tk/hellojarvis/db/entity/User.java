package tk.hellojarvis.db.entity;

import java.io.Serializable;

/**
 * @author Lenovo
 */
public class User implements Serializable {
    private int userId;
    private String userName;
    private String userPwd;

    public User() {

    }

    public User(String name, String pwd) {
        this.userName = name;
        this.userPwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
