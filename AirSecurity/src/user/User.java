
package user;

import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;


public class User {
    protected String userId;
    protected String username,password,fullname,email,mobile,address,usertype;
    protected LocalDate dob;

    public User() {
    }

   
    public User(String userId, String username, String password, String fullname, String email, String mobile, String address, String usertype, LocalDate dob) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.usertype = usertype;
        this.dob = dob;
    }

    public User(String userId, String fullname, String email, String mobile,LocalDate dob, String address) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob;
        this.address = address;
    }
    
    public User(String userId, String fullname, String email, String mobile,LocalDate dob) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob;
        
    }
    
    
    
//    Employee e = new Employee();
//    Customer c = new Customer();

//    public void loginHelper(ActionEvent event, String type) throws IOException{
//        if(type.equals("Employee")){
//            e.performLogin(event);
//        }
//        else if(type.equals("Customer")){
//            c.performLogin(event);
//        }
//            
//    }
   

    @Override
    public String toString() {
        return userId + "," + username + "," + password + "," + fullname + "," + email + "," + mobile + "," + address + "," + usertype + "," + dob+"\n";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
    
    
    

    
    
}
