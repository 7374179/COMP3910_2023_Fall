package ca.bcit.infosys.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class EmployeeManager implements Serializable {
	private static final long serialVersionUID = 1L;
	


	private ArrayList<Employee> employees = new ArrayList<Employee>(
			Arrays.asList(new Employee("John Doe", 1001, "johndoe"), new Employee("Jane Doe", 1002, "janedoe"),
					new Employee("hahaha", 1003, "jsmith"), new Employee("MEME MEMEM", 1004, "jsmith"),
					new Employee("YUYU YUY", 1005, "jsmith"), new Employee("KAD EDSF", 1006, "jsmith"),
					new Employee("ABD Egdfe", 1007, "jsmith"), new Employee("WESD SDF", 1008, "jsmith"),
					new Employee("admin", 1000, "admin")));

	private Map<String, String> loginCombos = new HashMap<>(); // 로그인 정보를 저장

	@Inject
	private EmployeeList employeeList;

	private Employee currentEmployee;

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee emp) {
//		employees.add(emp);
    	employees.add(new Employee());

	}

	public void editEmployee(Employee emp) {
		int index = employees.indexOf(emp);
		if (index != -1) {
			employees.set(index, emp);
		}
	}

	public void loginEmployee(String userName) {
		for (Employee emp : employees) {
			if (emp.getUserName().equals(userName)) {
				this.currentEmployee = emp;
				break;
			}
		}
	}

	public boolean isAdmin(String username) {
		return "admin".equals(username);
	}

	public void deleteEmployee(Employee userToDelete) {
//        if (userToDelete != null) {
//            employees.remove(userToDelete);
//            loginCombos.remove(userToDelete.getUserName());
//        }
		employees.remove(userToDelete);
	}

    private PropertiesLoader propertiesLoader = new PropertiesLoader("login.properties");

    public String getPasswordForUser(String username) {
        return propertiesLoader.getProperty(username + ".password");
    }
    
    public void setPasswordForUser(String username, String newPassword) {
        propertiesLoader.setProperty(username + ".password", newPassword);
        
    }
    
    private Map<Employee, String> passwordMap = new HashMap<>();
    public String getPassword(Employee employee) {
        return passwordMap.get(employee);
    }

    public void setPassword(Employee employee, String password) {
        passwordMap.put(employee, password);
    }
    
    public String logout() {
        currentEmployee = null;
        return "index";
    }



}
