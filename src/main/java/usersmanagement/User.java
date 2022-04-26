package usersmanagement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reminder")
public class User extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        System.out.println("action is:" + action);
        boolean succes = false;

        if (action != null && action.equalsIgnoreCase("New")) {

            succes = newUser(req, resp);

            if (succes) {
                RequestDispatcher rd = req.getRequestDispatcher("login.html");

                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("error while trying to create this user, pls try again");
                error(resp, "error while trying to create this user, pls try again");

            }
            //daca l-a creat dute la login

        }

    else if (action != null && action.equalsIgnoreCase("LOGIN")) {
            //afisare
            succes = loginUser(req, resp);
            if(succes) // in
            {
                RequestDispatcher rd=req.getRequestDispatcher("listMyStuff.jsp");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                RequestDispatcher rd=req.getRequestDispatcher("login.html");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (action != null && action.equalsIgnoreCase("OUT")) {
            HttpSession s = req.getSession();
            s.invalidate();
            try {
                resp.sendRedirect("login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else {
            System.out.println("nu a venit action, deci nu fac nimic ");
            error(resp, "error on ui side");
        }

    }


 private boolean newUser(HttpServletRequest req, HttpServletResponse resp) {

     String email = req.getParameter("username");
     String password = req.getParameter("password");
     String confirmpassword = req.getParameter("confirmpassword");
     if(!password.equals(confirmpassword))
     {
         error(resp, "password is not the same as confirm password");
         return false;
     }


     {

         DBUsers dbuser = new DBUsers();
         LoginUser u = new LoginUser(email, password, confirmpassword);
         boolean inserted = dbuser.newUser(u) ;

         return inserted;

     }
 }
 private boolean loginUser(HttpServletRequest req, HttpServletResponse resp) {
     User u = null;
     String email = req.getParameter("email");
     String password= req.getParameter("password");
     boolean isLoggedIn=false;

     DBUsers dbUser = new DBUsers();
     u = dbUser.login(email, password);

    if (u != null) // i am in
     {

         HttpSession s = req.getSession();
         s.setAttribute("id", u.getmail());
         s.setAttribute("email", u.getEmail());
         isLoggedIn=true;
     }
     return isLoggedIn;
 }

    private Object getEmail() {
        return null;
    }

    private Object getmail() {
        return null;
    }

    private void error(HttpServletResponse resp, String mesaj) {
     try {
         PrintWriter pw = resp.getWriter();
         pw.println(mesaj);
         pw.close();
     } catch (IOException e) {
         e.printStackTrace();

     }
 }
 private void returnJsonResponse(HttpServletResponse response, String jsonResponse){
        response.setContentType("aplication/json");
        PrintWriter pr = null;
        try{
            pr = response.getWriter();

            }  catch (IOException e){
                    e.printStackTrace();

            }
                assert pr != null;
        pr.write(jsonResponse);
        pr.close();}

    public void setId(int id) {
    }

    public void setEmail(String username) {
    }

    public static void main(String[] args) {
        User o =new User();
        User l =new User();
    }
}







