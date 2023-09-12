package com.example.lab01;

import java.io.Serializable;
import jakarta.inject.Named; 
import jakarta.enterprise.context.SessionScoped; 

@Named("user") 
@SessionScoped
public class UserBean implements Serializable {
   private String firstName;
   private String lastName;

   public String getFirstName() { return firstName; }   
   public void setFirstName(String newValue) { firstName = newValue; }

   public String getLastName() { return lastName; }
   public void setLastName(String newValue) { lastName = newValue; }   
}
