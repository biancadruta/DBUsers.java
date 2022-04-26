package usersmanagement;

import java.sql.*;

public class DBUsers {
    public boolean newUser( LoginUser u){

        System.out.println(u);


        boolean isInserted= false;
        try {
            // 1. ma conectez la db
            final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ourcommonddb";
            final String USERNAME = "ftuser";

            final String PASSWORD = System.getenv("PWDDB");

            System.out.println("parola:"+PASSWORD);

           Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. creez un prepared ststement si il populez cu date
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO users (email , password, confirmpassword) VALUES(?,?,?)");
            pSt.setString(1, u.getEmail());
            pSt.setString(2, u.getPassword());
            pSt.setString(3, u.getConfirmpassword());



            // 3. executie
           int insert= pSt.executeUpdate();
           if (insert!=-1)
               isInserted=true;
            System.out.println(isInserted);

            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            isInserted=false;

        }

        return isInserted;


    }
    public User login (String username, String password) {

        User u = null;
        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ourcommonddb";
        final String USERNAME = "ftuser";
        final String PASSWORD = System.getenv("PWDDB");
        int id =-1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. fac un query pe o tabela , intai creez obiectul



            PreparedStatement pSt = conn.prepareStatement("select id, username from users where username=? and password=?");

            pSt.setString(1,username);
            pSt.setString(2,password);


            // 3. executie
            ResultSet rs = pSt.executeQuery();




            // atata timp cat am randuri
            while (rs.next()) {

                u = new User();
             u.setId(rs.getInt("id"));
             u.setEmail(rs.getString("username"));


            }

            rs.close();
            pSt.close();
            conn.close();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return u;
    }


    public static void main(String[] args) {
        DBUsers dbuser= new DBUsers();
        User u = new User();
    }
}


