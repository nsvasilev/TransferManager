package ru.vasilyev.transfermanager.livecoding;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Department department = new Department();
        department.addEmployee(new Employee("f1", "l1", 100000));
        department.addEmployee(new Employee("f2", "l2", 10000));
        department.addEmployee(new Employee("f3", "l3", 1000));

        //1. Собрать статистику по зарплатам сотрудников в заданном подразделении. count, sum, max, min, avg
//        DoubleSummaryStatistics collect = department.getEmployees().stream().collect(Collectors.summarizingDouble(Employee::getSalary));
//        System.out.println(collect);

        DoubleSummaryStatistics summaryStatistics = department.getEmployees().stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(summaryStatistics);
    }


    @Data
    static class Department{
        private String name;
        private List<Employee> employees = new ArrayList<>();
        public void addEmployee(Employee employee){
            this.employees.add(employee);
        }
    }
    @AllArgsConstructor
    @Data
    static class Employee{
        private String firstname;
        private String lastname;
        private int salary;
    }
}
