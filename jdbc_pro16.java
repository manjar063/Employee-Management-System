package comm.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;



//1.retrieve all row
//2.insert the data
//3.Retrieve specific row(based on empid)
//4.updating the specific row(based on empid)
//5.deleting the specific row(based on empid) L-9

public class jdbc_pro16 {
	private String driver="oracle.jdbc.OracleDriver";
	private String db_url="jdbc:oracle:thin:@localhost:1521:orcl";
	// Add your database credentials here
	private String db_uname="YOUR_DB_USERNAME";
	private String db_pwd="YOUR_DB_PASSWORD";
	Scanner sc=new Scanner(System.in);
	String sqlQuery="select *from "; 
	String sqlQuery2="insert into employee values('151','Vidhata','Akansha',35000,'Jsr')";
	String sqlQuery3="select *from employee where eid='101'";
	String sqlQuery4="update employee set esal=8500 where eid='111'";
	String sqlQuery5="delete from employee where eid='151'";
	
	Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(db_url,db_uname,db_pwd);
			System.out.println("Connection created");
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return con;
	}
	void method1()
	{
		System.out.println("Retrieving the data\n");
		System.out.println("From which table you want to view the data");
		String t_name=sc.nextLine();
		try
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sqlQuery.concat(t_name));
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			}
			System.out.println("Data retrieved successfully");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void method2()
	{
		System.out.println("Insert the data into the table\n");
		
		
		System.out.println("Enter Employee Id:");
		String emp_id=sc.nextLine();
		System.out.println("Enter Employee FirstName:");
		String emp_fname=sc.nextLine();
		System.out.println("Enter Employee LastName:");
		String emp_lname=sc.nextLine();
		System.out.println("Enter Employee salary:");
		int emp_sal=Integer.parseInt(sc.nextLine());
		System.out.println("Enter Employee Address:");
		String emp_addr=sc.nextLine();
		
		try
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			int rowCount=stmt.executeUpdate("insert into employee values('"+emp_id+"','"+emp_fname+"','"+emp_lname+"',+"+emp_sal+",+'"+emp_addr+"')");
			if(rowCount>0)
			{
				System.out.println("Data inserted");
				method1();
			}
		}
		catch(SQLIntegrityConstraintViolationException sicve)
		{
			System.out.println("Duplicate Employee id are not allowed");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void method3()
	{
		System.out.println("Retrieving the specific row based on empid");
		System.out.println("Enter emp id");
		String e_id=sc.nextLine();
		try
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select *from employee where eid='"+e_id+"'");
			if(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			}
			else
			{
				throw new SQLIntegrityConstraintViolationException("There is no record with emp id : "+e_id);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void method4()
	{
		System.out.println("Update empsal based on the emp-id : \n");
		
		System.out.println("Enter Employee Id : ");
		String e_id=sc.nextLine();
		
		System.out.println("Enter updated Emp salary : ");
		int e_sal=Integer.parseInt(sc.nextLine());
		try
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			int rowCount=stmt.executeUpdate("update employee set esal="+e_sal+" where eid='"+e_id+"'");
			if(rowCount>0)
			{
				System.out.println("Data updated");	
			}
			else
			{
				System.out.println("Data not updated !!!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
	void method5()
	{
		System.out.println("Delete emp-id that you want to delete : \n");
		
		System.out.println("Enter Employee Id : ");
		String e_id=sc.nextLine();
		
		try
		{
			Connection con=connect();
			Statement stmt=con.createStatement();
			int rowCount=stmt.executeUpdate("delete from employee where eid='"+e_id+"'");
			if(rowCount>0)
			{
				System.out.println("Data deleted");	
			}
			else
			{
				System.out.println("Data not updated !!!");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
	
	public static void main(String[] args)
	{
		jdbc_pro16 obj=new jdbc_pro16();
		obj.method5();
	}

}
