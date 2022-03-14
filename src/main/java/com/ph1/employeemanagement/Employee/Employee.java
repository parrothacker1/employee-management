package com.ph1.employeemanagement.Employee;

import java.util.ArrayList;
import com.ph1.employeemanagement.Employee.Empl;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Employee {
    private Statement stmt;
    public Employee(String user,String pass,String socket) {
        try { 
            Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection("jdbc:postgresql://"+socket+"/employee",user,pass);
            stmt=con.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employee(EMPID INTEGER,EMPNAME VARCHAR(25));");
        } catch (SQLException e) {
            System.err.println("Failed to create the table");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to find class");
            e.printStackTrace();
        } 
    }
    public Boolean insert(Empl empl) {
        String query="INSERT INTO employee VALUES ("+Integer.toString(empl.getID)+",'"+empl.getName+"');";
        Boolean ret;
        try {
            Integer res=stmt.executeUpdate(query);
            if (res > 0) {
                ret=true;
            } else {
                ret=false;
            }
        } catch (SQLException e) {
            ret=false;
            e.printStackTrace();
        }
        return ret;
    }
    public Boolean edit(Empl empl) {
        String query="UPDATE employee SET SET EMPID="+Integer.toString(empl.getID)+",EMPNAME='"+empl.getName+"' WHERE EMPID="+Integer.toString(empl.getID)+";";
        Boolean ret;
        try {
            Integer res=stmt.executeUpdate(query);
            if (res > 0) {
                ret=true;
            } else {
                ret=false;
            }
        } catch (SQLException e) {
            ret=false;
            e.printStackTrace();
        }
        return ret;
    }
    public Boolean delete(Integer id) {
        String query="DELETE FROM employee WHERE EMPID="+Integer.toString(id)+";";
        Boolean ret;
        try {
            Integer res=stmt.executeUpdate(query);
            if (res > 0) {
                ret=true;
            } else {
                ret=false;
            }
        } catch (SQLException e) {
            ret=false;
            e.printStackTrace();
        }
        return ret;
    }
    public ArrayList<Empl> list() {
        ArrayList arr=new ArrayList();
        try {
            ResultSet res=stmt.executeQuery("SELECT * FROM employee");
            while (res.next()) {
                Empl emp=new Empl(res.getInt(1),res.getString(2));
                arr.add(emp);
            }
        } catch (SQLException e) {
            arr=new ArrayList();
        }
        return arr;
    }
}
