package usersmanagement;

public class LoginUser {
    private String email;
    private String password;
    private String confirmpassword;
    private int id;


    public LoginUser(String email, String password, String confirmpassword) {
        this.email=email;
        this.password=password;
        this.confirmpassword=confirmpassword;



    }
    public LoginUser() {}

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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    @Override

    public String toString() {
        return "LoginUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                ", id=" + id +
                '}';
    }
}


