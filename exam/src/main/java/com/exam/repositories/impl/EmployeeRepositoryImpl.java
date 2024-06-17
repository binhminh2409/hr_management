package com.exam.repositories.impl;

import com.exam.config.DatasourceProperties;
import com.exam.utils.DatabaseHelper;

import java.sql.Connection;

public class EmployeeRepositoryImpl {
    private Connection conn = DatabaseHelper.getConnection();

    private String SQL_QUERY_ALL = "select * from employees limit 1000";


}
