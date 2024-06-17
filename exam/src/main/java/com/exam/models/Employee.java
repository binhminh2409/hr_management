package com.exam.models;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    public Long id;
    public String firstName;
    public String lastName;
    public Date birthday;
    public String address;
    public String email;
    public String department;
    public String position;
}
