package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;
import com.mysql.cj.xdevapi.Statement;

public class studentDButil {

	private DataSource dataSource;
	
	public studentDButil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	public List<student> getStudents() throws Exception{
		List<student> students = new ArrayList<>();
		
		Connection myConn =null;
		Statement  myStm = null;
		ResultSet myRs = null;
		
		try {
		//load Driver
		myConn=dataSource.getConnection();
		//Create sql Statement
		String sql = "select * from student order by last_name";
		myStm = (Statement) myConn.createStatement();
		//Execute Query
		myRs = ((java.sql.Statement) myStm).executeQuery(sql);
		//process resultSet
		while (myRs.next()) {
			//Retrieve the data result set row
	    int id =myRs.getInt("id");
	    String firstname = myRs.getString("first_name");
	    String lastname = myRs.getString("last_name");
	    String email = myRs.getString("email");
	    
	       //create new object of student
	    student tempStudent = new student(id,firstname, lastname, email);
	       //add it to the list of Students
	    students.add(tempStudent);
		}
		return students;
		}

		finally {
			//Close Connection
         Close(myConn,myRs,myStm);
		}
	}

	private void Close(Connection myConn, ResultSet myRs, Statement myStm) {
		// TODO Auto-generated method stub
		try {
			if(myRs != null) {
				myRs.close();
			}
			
			if(myConn!=null) {
				myConn.close();
			}
			
			if(myStm != null){
				((Connection) myStm).close();
			}
		}
		catch (Exception exe) {
			exe.printStackTrace();
		}
	}
}
