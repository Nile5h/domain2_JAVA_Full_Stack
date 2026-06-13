package com.task.model;
import java.io.Serializable;

public class Contact implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String email;
    private String phone;

    public Contact(String id, String name, String email, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    };
    
    public Contact(){};


    public String getid(){ return id; }
    public void setid(String id){ this.id = id; }

    public String getname(){ return name; }
    public void setname(String name){ this.name = name; }
    
    public String getemail(){ return email; }
    public void setemail(String email){ this.email = email; }

    public String getphone(){ return phone; }
    public void setphone(String phone){ this.phone = phone; }

    
}