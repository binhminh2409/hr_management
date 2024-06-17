package com.exam.service;

import com.exam.service.impl.EmployeeServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceContainer {
    public static EmployeeService getEmployeeService() {
        return new EmployeeServiceImpl();
    }
}
