package com.example.employeemanagementsystem;
import java.util.Date;
public class Employee implements Comparable {
    // ATTRIBUTES
    private Long id;
    private String name;
    private Double salary;
    private String department;
    private String dob;
    // SETTERS
    public boolean setId(Long id) {
        if(id > 0) this.id = id;
        else System.out.println("INVALID ID 'NEGATIVE'");
        return id > 0;
    }
    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    public boolean setSalary(Double salary) {
        if(salary >= 0) this.salary = salary;
        else System.out.println("INVALID SALARY 'NEGATIVE'");
        return salary >= 0;
    }
    public boolean setDepartment(String department) {
        this.department = department;
        return true;
    }
    public boolean setDob(int day, int month, int year) {
        boolean conflict = false;
        if(day <= 0) {System.out.println("INVALID DAY 'NEGATIVE'"); conflict = true;}
        else if(day > 31) {System.out.println("INVALID DAY 'MORE THAN 31'"); conflict = true;}

        if(month <= 0) {System.out.println("INVALID MONTH 'NEGATIVE'"); conflict = true;}
        else if(month > 12) {System.out.println("INVALID MONTH 'MORE THAN 12'"); conflict = true;}

        String[] splitArray = new Date().toString().split(" ");
        int currentYear = Integer.parseInt(splitArray[splitArray.length - 1]);
        if(year <= currentYear - 60) {System.out.println("INVALID MONTH 'MORE THAN 60 YEAR'"); conflict = true;}
        else if(year > currentYear) {System.out.println("INVALID MONTH 'FUTURE YEAR'"); conflict = true;}

        if(!conflict) this.dob = day + "/" + month + "/" + year;
        return !conflict;
    }
    public boolean setDob(String dob) {
        this.dob = dob;
        return true;
    }
    // GETTERS
    public Long getId() {
        if(id != null) return id.longValue();
        return null;
    }
    public String getName() {
        if(name != null) return String.valueOf(name.toCharArray());
        return null;
    }
    public Double getSalary() {
        if(salary != null) return salary.doubleValue();
        return null;
    }
    public String getDepartment() {
        if(department != null) return String.valueOf(department.toCharArray());
        return null;
    }
    public String getDob() {
        if(dob != null) return String.valueOf(dob.toCharArray());
        return null;
    }
    // COMPARABLE IMPLEMENTATION
    @Override
    public int compareTo(Object object) {
        try {
            Employee that = (Employee) object;
            return this.name.compareTo(that.name);
        } catch (Exception e) {
            System.out.println("ERROR 'PASSED OBJECT IS NOT CASTABLE TO EMPLOYEE CLASS'");
            return 512;
        }
    }
    // equals() METHOD
    @Override
    public boolean equals(Object object) {
        try {
            Employee that  = (Employee) object;
            return  this.id.equals(that.id) &&
                    this.name.equals(that.name) &&
                    this.salary.equals(that.salary) &&
                    this.department.equals(that.department) &&
                    this.dob.equals(that.dob);
        } catch (Exception e) {
            System.out.println("ERROR 'PASSED OBJECT IS NOT CASTABLE TO EMPLOYEE CLASS'");
            System.out.println(e.getMessage());
            return false;
        }
    }
    // toLine() METHOD
    public String toLine() {
        return  id + "," +
                name + "," +
                salary + "," +
                department + "," +
                dob;
    }
    public static Employee toEmployee(String line) {
        try {
            Employee employee = new Employee();
            String[] split = line.strip().split(",");
            employee.id = Long.parseLong(split[0].strip());
            employee.name = split[1].strip();
            employee.salary = Double.parseDouble(split[2].strip());
            employee.department = split[3].strip();
            employee.dob = split[4].strip();
            return employee;
        } catch (Exception e) {
            System.out.println("ERROR GETTING EMPLOYEE");
            System.out.println(e.getMessage());
            return null;
        }
    }
}