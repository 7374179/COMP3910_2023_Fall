package ca.bcit.infosys.employee;

import java.io.Serializable;
import java.util.Properties;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Named
@SessionScoped
public class ChangeUserPassword implements Serializable {
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;

	private Properties userProperties = new Properties();
	
    @Inject
    private EmployeeListImpl employeeList;
    
    @Inject
    private EmployeeManager employeeManager;

	// Getters and Setters...
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

    public String changePassword() {
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");

        if(username == null || username.trim().isEmpty()) {
            System.out.println("No username in session.");
            return "index";
        }

        Credentials credential = new Credentials();
        credential.setUserName(username);
        credential.setPassword(currentPassword);
        System.out.println("Stored username: " + username);
        System.out.println("Input Password: " + currentPassword);
        System.out.println("Input Password: " + credential);



        if(employeeList.verifyUser(credential)) {
            if(newPassword != null && newPassword.equals(confirmPassword)) {
                employeeList.changePassword(username, newPassword);
                return "timesheet";
            } else {
                System.out.println("Passwords do not match or invalid new password");
            }
        } else {
            System.out.println("Invalid current password");
        }
        return null;
    }
    
    
}
