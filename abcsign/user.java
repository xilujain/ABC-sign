package abcsign;

import javax.persistence.*;

@Entity
@Table(name="user")

public class user implements java.io.Serializable {
    
   @Id 
   @Column(name="User_name")
   private String userName;
   
   @Column(name="Password")
   private String password;
   
   @Column(name="Email")
   private String email;
   
   @Column (name="gender")
   private String gender;

    public user() {
    }

    public user(String userName, String password, String email, String gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}