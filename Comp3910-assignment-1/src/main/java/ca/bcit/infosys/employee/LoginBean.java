package ca.bcit.infosys.employee;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private Credentials credentials = new Credentials();

    @Inject
    private EmployeeListImpl employeeList;  // EmployeeListImpl을 주입합니다.
    
    @Inject
    private EmployeeManager employeeManager;  // EmployeeManager를 주입합니다.


    public Credentials getCredentials() {
        return credentials;
    }

    public String login() {
        boolean isValid = employeeList.verifyUser(credentials);  // EmployeeListImpl의 verifyUser 메서드를 호출하여 유효성 검사를 수행합니다.

        if (isValid) {
            // 로그인 성공시, EmployeeManager의 loginEmployee 메서드를 호출하여 currentEmployee를 설정합니다.
            employeeManager.loginEmployee(credentials.getUserName());

            // 로그인 성공시, 다음 페이지로 이동합니다.
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", credentials.getUserName());
            if (employeeManager.isAdmin(credentials.getUserName())) {
            	// 관리자 전용 페이지로 리다이렉션 또는 관리자 전용 기능 허용
            	return "adminPage?faces-redirect=true";

            } else {
            	// 일반 사용자로 처리
            	return "timesheet?faces-redirect=true";
            }
        } else {
            // 로그인 실패시, 로그인 페이지에 머무릅니다.
        	System.out.println("fail");

            return null;
        }
    }
    

}
