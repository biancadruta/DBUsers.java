package usersmanagement;



public class myMemoList {
    private int id;
     private String menoname;

     private int iduser;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenoname() {
        return menoname;
    }

    public void setMenoname(String menoname) {
        this.menoname = menoname;
    }





    @Override
    public String toString() {
        return "myMemoList{" +
                "id=" + id +
                ", menoname='" + menoname + '\'' +
                ", iduser=" + iduser +
                '}';
    }


    }

