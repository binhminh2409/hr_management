package com.exam.repositories.impl;

import ch.qos.logback.core.model.conditional.ElseModel;
import com.exam.config.DatasourceProperties;
import com.exam.models.Employee;
import com.exam.repositories.EmployeeRepository;
import com.exam.utils.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private Connection conn = DatabaseHelper.getConnection();

    private String SQL_QUERY_ALL = "select * from employees limit 1000";

    private String SQL_QUERY_BY_ID = "select * from employees where id= ?";

    private String SQL_QUERY_BY_EMAIL = "select * from employees where email= ?";

    private String SQL_INSERT =
            "insert into employees (first_name, last_name, birthday, address, email, department, position) values(?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(SQL_QUERY_ALL)){
            ResultSet rs = ps.executeQuery();
            System.out.println(ps);
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getLong("id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setDepartment(rs.getString("department"));
                e.setAddress(rs.getString("address"));
                e.setEmail(rs.getString("email"));
                e.setPosition(rs.getString("position"));
                e.setBirthday(rs.getDate("birthday"));
                employees.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = new Employee();
        try (PreparedStatement ps = conn.prepareStatement(SQL_QUERY_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee1 = new Employee();
                employee1.setId(rs.getLong("id"));
                employee1.setFirstName(rs.getString("first_name"));
                employee1.setLastName(rs.getString("last_name"));
                employee1.setDepartment(rs.getString("department"));
                employee1.setAddress(rs.getString("address"));
                employee1.setEmail(rs.getString("email"));
                employee1.setPosition(rs.getString("position"));
                employee1.setBirthday(rs.getDate("birthday"));
                employee = employee1;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        try (PreparedStatement ps = conn.prepareStatement(SQL_QUERY_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee1 = new Employee();
                employee1.setId(rs.getLong("id"));
                employee1.setFirstName(rs.getString("first_name"));
                employee1.setLastName(rs.getString("last_name"));
                employee1.setDepartment(rs.getString("department"));
                employee1.setAddress(rs.getString("address"));
                employee1.setEmail(rs.getString("email"));
                employee1.setPosition(rs.getString("position"));
                employee1.setBirthday(rs.getDate("birthday"));
                employee = employee1;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Employee create(Employee employee) {
        Employee result = new Employee();
        String emailCheck = employee.getEmail();
        if (this.findByEmail(emailCheck).getId() != null) {
            return null;
        };
        try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
            // Set the parameters
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setDate(3, employee.getBirthday());
            ps.setString(4, employee.getAddress());
            ps.setString(5, employee.getEmail());
            ps.setString(6, employee.getDepartment());
            ps.setString(7, employee.getPosition());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long employeeId = generatedKeys.getLong(1);
                    result = this.findById(Long.valueOf(employeeId));
                }
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    public static void main(String[] args) {
        EmployeeRepository er = new EmployeeRepositoryImpl();
        System.out.println(er.findByEmail("test@gmail.com"));
        Employee employee = new Employee(
                Long.valueOf(0), "Harry", "Potter"
                , new Date(2000, 12, 11), "111 NY","test@gmail.com", "HR", "Employee");

        System.out.println(er.create(employee));
    }
}
