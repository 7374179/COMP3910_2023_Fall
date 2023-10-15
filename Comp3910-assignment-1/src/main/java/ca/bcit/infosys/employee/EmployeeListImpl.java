package ca.bcit.infosys.employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class EmployeeListImpl implements EmployeeList {

    private List<Employee> employees = new ArrayList<>();
    private Map<String, String> loginCombos = new HashMap<>();
    private Employee currentEmployee;
    private Employee administrator;
    
    @Inject
    private EmployeeManager employeeManager;

    @PostConstruct
    public void init() {
        this.employees = new ArrayList<>(employeeManager.getEmployees());
        
        Properties properties = new Properties();
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("login.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();  // 적절한 예외 처리를 추가해야 합니다.
        }

        for (Employee emp : employees) {
            String username = emp.getUserName();
            String password = properties.getProperty(username + ".password");  // 해당 사용자의 비밀번호를 가져옵니다.
            System.out.println(username + ",  " + password);
            loginCombos.put(username, password);
        }
    }
    
    @Override
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(String name) {
        for (Employee emp : employees) {
            if (emp.getName().equals(name)) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public Map<String, String> getLoginCombos() {
        return loginCombos;
    }

    @Override
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    @Override
    public Employee getAdministrator() {
        return administrator;
    }
    
    public void setLoginCombos(Map<String, String> loginCombos) {
        this.loginCombos = loginCombos;
    }


    @Override
    public boolean verifyUser(Credentials credential) {
    	System.out.println(credential.getUserName()+", " + credential.getPassword());
        String inputUsername = credential.getUserName();
        String inputPassword = credential.getPassword();
        String storedPassword = loginCombos.get(inputUsername);
        return storedPassword != null && storedPassword.equals(inputPassword);
    }

    @Override
    public String logout(Employee employee) {
        currentEmployee = null;
        return "index";
    }

    @Override
    public void deleteEmployee(Employee userToDelete) {
        employees.remove(userToDelete);
        loginCombos.remove(userToDelete.getUserName());
    }
  
    @Override
    public void addEmployee(Employee newEmployee) {
        employees.add(newEmployee);
    }

    @Override
    public void addEmployee(Employee newEmployee, String password) { // 패스워드를 추가로 받도록 변경
        employees.add(newEmployee);
        loginCombos.put(newEmployee.getUserName(), password);
    }
    
    public void changePassword(String username, String newPassword) {
        if(loginCombos.containsKey(username)) {
            loginCombos.put(username, newPassword);
        }
    }

}
