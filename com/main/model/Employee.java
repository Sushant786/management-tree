package com.main.model;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private int managerId;

    public Employee(int id, String name, int managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getManagerId() {
        return managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() &&
                getManagerId() == employee.getManagerId() &&
                Objects.equals(getName(), employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getManagerId());
    }
}
