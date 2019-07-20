package com.main;

import com.main.model.Employee;
import com.main.util.FileUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Application {
    Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        new Application().displayManagementTree();
    }

    private void displayManagementTree() {
        Map<Integer, Employee> employees = getEmployees();

        Map<Employee, List<Employee>> employeesGroupedByManager = groupEmployeesByManager(employees);

        printToConsole(employeesGroupedByManager);

    }

    private Map<Employee, List<Employee>> groupEmployeesByManager(Map<Integer, Employee> employees) {

        Map<Integer, List<Employee>> employeesGroupedByManager =
            employees
                .values()
                .stream()
                .sorted(Comparator.comparing(Employee::getId))
                .collect(groupingBy(Employee::getManagerId));

        if (employeesGroupedByManager.get(0).size() > 1) {
            logger.log(Level.SEVERE, "There should only be 1 root manager");
            System.exit(1);
        }

        Map<Employee, List<Employee>> perManagerEmployees = new HashMap<>();
        for (Map.Entry<Integer, List<Employee>> mgmtTree: employeesGroupedByManager
                .entrySet()
                .stream()
                .filter(e -> e.getKey() != 0)
                .collect(Collectors.toSet())) {
            perManagerEmployees.put(employees.get(mgmtTree.getKey()),
                mgmtTree
                    .getValue()
                    .stream()
                    .filter(e -> e.getId() != mgmtTree.getKey() && !employeesGroupedByManager.containsKey(e.getId()))
                    .collect(Collectors.toList()));
        }

        return perManagerEmployees;
    }

    private void printToConsole(Map<Employee, List<Employee>> perManagerEmployees) {
        for (Map.Entry<Employee, List<Employee>> data: perManagerEmployees.entrySet()) {
            System.out.println(String.join("", Collections.nCopies(data.getKey().getId(), "->")) + data.getKey().getName());

            for (Employee emp : data.getValue()) {
                System.out.println(String.join("", Collections.nCopies(emp.getManagerId() + 1, "->")) + emp.getName());
            }
        }
    }

    private Map<Integer, Employee> getEmployees() {

        List<Employee> employees = FileUtils.loadEmployeesFromCsvFile("src/resources/dummyData.csv");

        return employees.stream().collect(Collectors.toMap(emp -> emp.getId(), emp -> emp));
    }
}
