package com.wpa.test.employee;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;
import java.util.ArrayList;
import java.util.List;

public class EmployeeHandler {

    private List<Employee> employees = new ArrayList<>();
    private WebEngine webEngine;

    public EmployeeHandler(WebEngine webEngine) {
        this.webEngine = webEngine;
        // 初始化一些员工数据
        employees.add(new Employee("John Doe", "Software Engineer", 30));
        employees.add(new Employee("Jane Smith", "Project Manager", 35));
    }

    public String getEmployees() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Employee employee : employees) {
            sb.append("{\"name\":\"").append(employee.getName()).append("\",")
                    .append("\"position\":\"").append(employee.getPosition()).append("\",")
                    .append("\"age\":").append(employee.getAge()).append("},");
        }
        if (employees.size() > 0) sb.deleteCharAt(sb.length() - 1); // 去掉最后一个逗号
        sb.append("]");
        return sb.toString();
    }

    public void submitEmployee(String name, String position, int age) {
        employees.add(new Employee(name, position, age));
        webEngine.executeScript("updateEmployeeList()");
    }

    class Employee {
        private String name;
        private String position;
        private int age;

        public Employee(String name, String position, int age) {
            this.name = name;
            this.position = position;
            this.age = age;
        }

        public String getName() { return name; }
        public String getPosition() { return position; }
        public int getAge() { return age; }
    }
}
