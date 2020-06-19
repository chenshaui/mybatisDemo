package entity;

import java.util.Date;

/**
 * @ClassName User
 * @Description 基本类，对应数据库的相应字段
 * @Author chenshuai
 * @Date 2020/6/18 16:36
 * @Version 1.0
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String emailStatus;
    private Date registerTime;
    private String code;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", emailStatus='" + emailStatus + '\'' +
                ", registerTime=" + registerTime +
                ", code='" + code + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
