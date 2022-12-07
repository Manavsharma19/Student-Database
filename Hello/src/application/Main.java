package application;
	
import java.sql.Connection;
import java.time.LocalDate;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Label sid;
			TextField sidtf;
			
			Label first_nmlbl;
			TextField first_nmtf;
			
			Label chsid;//change first name 
			TextField chsidtf;
			
			Label middle_nmlbl;
			TextField middle_nmtf;
			
			Label last_nmlbl;
			TextField last_nmtf;
			
			Label date;
			TextField datetf;
			
			TextField Text_A = new TextField("Data");
			
			Button delete = new Button("Delete");
			
			Button showall = new Button("Show All");
			
			Button update = new Button("Update");
	
			Button submit = new Button("Submit");
			
			Button search = new Button("Search");
			
			Button Exit = new Button("Exit");
			
			
			sid = new Label(" Enter your Student ID : ");
			sidtf = new TextField();
			
			
			first_nmlbl = new Label(" Enter Your First Name : ");
			first_nmtf = new TextField();
			
			chsid = new Label(" Change Your sID: ");
			chsidtf = new TextField();
			
			middle_nmlbl = new Label(" Enter Your Middle Name :  ");
			middle_nmtf = new TextField();
			
			last_nmlbl = new Label(" Enter Your Last Name : ");
			last_nmtf = new TextField();
			
			date = new Label(" Enter Your DOB : ");
			datetf = new TextField();
			
			/**
			* Submit EventHandler to take care of sending the data from the java fx GUI to the mysql database
			* A mysql connector driver was installed and added to my classpath to allow this
			* The DriverManager gets access to the port 3306 at localhost and is able to access the records database
			* try catch in place to try see if its successfull and if not to catch the error
			* MYSql statement is allowed to be use because of the executeUpdate which executes given statements which may be DELETE,UPDATE OR INSERT
			*A new Student is created then submitted to the database
			*
			*/
			
			
			submit.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					student s = new student(sidtf.getText(),first_nmtf.getText(),middle_nmtf.getText(),last_nmtf.getText(),datetf.getText());
					
					try {
						Connection con = null;
						Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
						con =DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","");
						System.out.println("Connection is succesful");
						Statement stmt = con.createStatement();
						//stmt.executeUpdate("CREATE DATABASE records");
						//stmt.executeUpdate("ALTER TABLE student ADD COLUMN sID INT");
						stmt.executeUpdate("insert into student values" + " ('" +s.getName() +"'," + "'" +s.getMiddlenm() +"'," + "'" +s.getLastnm() +"',"+"'"+s.getDate()+"','"+s.getSid()+"');");
					System.out.print("Record Inserted Succesfully");
						stmt.close();
					con.close();
					}
				
					catch(SQLException e){
						System.out.println(e.getMessage());
						
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
		});
			
			
			/**
			* Delete MYSql statement is executed in the
			* EventHandler for delete
			*The exectuteUpdate look for a student id which is in the database
			*It queries for a student with the exact same id and when this is located 
			*It then deletes this sID from the records database
			*/
			
delete.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//student s = new student(first_nmtf.getText(),middle_nmtf.getText(),last_nmtf.getText());
					student s = new student(sidtf.getText(),first_nmtf.getText(),middle_nmtf.getText(),last_nmtf.getText(),date.getText());

					try {
						Connection con = null;
						Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
						con =DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","");
						System.out.println("Connection is succesful");
						Statement stmt = con.createStatement();
						stmt.executeUpdate("DELETE FROM student WHERE sID="+"'"+s.getSid()+"'");
					

						System.out.print("Record Deleted Succesfully");
						stmt.close();
					con.close();
					}
				
					catch(SQLException e){
						System.out.println(e.getMessage());
						
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block	
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
		});


/**
* Update EventHandler
* In the Update EventHandler a prepared statement is used to query a student id 
* Then the student ID so know the data that the student contains can now be modified and changed
* The current id is entered into "Enter your Student ID:" then the id we want to change it to is entered into "Change sID :" and then you hit update and the changes are made
*
*/
update.setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//student s = new student(first_nmtf.getText(),middle_nmtf.getText(),last_nmtf.getText());
		
		
		try {
			Connection con = null;
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","");
			System.out.println("Connection is succesful");
			String sql1 = "UPDATE student SET sID=? WHERE sID= '"+sidtf.getText()+"'";
			 
			PreparedStatement statement = con.prepareStatement(sql1);
			//statement.setString(1, "Lucas");
			//statement.setString(2, "Moran");
			statement.setString(1,chsidtf.getText());
			
			 
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			
			
		System.out.print("Record Updated Succesfully");
			
		
		}
	
		catch(SQLException e){
			System.out.println(e.getMessage());
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

});


/**
* ShowaLL EventHandler
* Shows all data contained in the table
* This is mainly done through use of ResultSet
*  
*
*/

showall.setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//student s = new student(first_nmtf.getText(),middle_nmtf.getText(),last_nmtf.getText());
		
		
		try {
			Connection con = null;
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","");
			System.out.println("Connection is succesful");
			Statement smt=con.createStatement();
			
			
			String sql="Select * from student";
			
			//to execute query
			ResultSet rs=smt.executeQuery(sql);
			
			//to print the resultset on console
			if(rs.next()){ 
				do{
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5));
				}while(rs.next());
			
		System.out.print("All Records");
			
		
			}
		}
	
		catch(SQLException e){
			System.out.println(e.getMessage());
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

});


/**
* Search EventHandler
*Query using result set to find a certain student with the use of ResultSet again
* We query the sID and then find the student who has the exact same student id 
* 
*
*/
search.setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//student s = new student(first_nmtf.getText(),middle_nmtf.getText(),last_nmtf.getText());
		String sql = "SELECT * FROM student WHERE sID = "+"'"+sidtf.getText()+"'";
		
		try {
			Connection con = null;
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","");
			
			
			PreparedStatement smt = con.prepareStatement(sql);
			ResultSet rs=smt.executeQuery(sql);
			if(rs.next()){ 
				do{
				System.out.println("The Student You Searched For Is --> "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5));
				Text_A.setText(rs.getString(1));
				}while(rs.next());

			
		
			
		
			
			}
		}
	
		catch(SQLException e){
			System.out.println(e.getMessage());
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

});


Exit.setOnAction(e -> System.exit(0)

		);
		
		
			


		
			
			GridPane root = new GridPane();
			
			root.setHgap(5);
			root.setVgap(5);
			
			root.add(sid, 0, 1);
			root.add(sidtf, 1, 1);
			
			root.add(first_nmlbl,0,3);
			root.add(first_nmtf,1,3);
			
			root.add(chsid,2,1);
			root.add(chsidtf,3,1);
			
			
			root.add(middle_nmlbl,0,5);
			root.add(middle_nmtf,1,5);
			
			root.add(last_nmlbl,0,7);
			root.add(last_nmtf,1,7);
			
			root.add(date,0,9);
			root.add(datetf,1,9);
			
			root.add(Text_A, 1, 11);
			
			
			root.add(submit,0,12);
			root.add(delete,1,12);
			root.add(update,2,12);
			root.add(showall,3,12);
			root.add(search,4,12);
			root.add(Exit,1,20);
			

			Scene scene = new Scene(root,650,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		launch(args);
		//connecting java program to database
		//opening the connection
		
	
	}
}

//class name{
//	private String first;
//	private String middle;
//	private String last;
//	
//	
//	
//	public name(String first,String middle,String last) {
//		this.first =first;
//		this.middle =middle;
//		this.last = last;
//		
//	}

//class student{
//	private String first_nm;
//	private String middle_nm;
//	private String last_nm;
//	private String date;
//	LocalDate birthday = LocalDate.of(1987, 06, 24);
//	
//	
//	public student(String first_nm,String middle_nm,String last_nm) {
//		this.first_nm =first_nm;
//		this.middle_nm =middle_nm;
//		this.last_nm = last_nm;
//		
//	}
//	
//	public void insertStudentRecord() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
//		try {
//			Connection con = null;
//			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","");
//			System.out.println("Connection is succesful");
//			Statement stmt = con.createStatement();
//			stmt.executeUpdate("insert into student values ('Js','Boffo','Nekorggo',34);");
//			System.out.print("Record Inserted Succesfully");
//			stmt.close();
//			con.close();
//		}
//		
//		catch(SQLException e){
//			System.out.println(e.getMessage());
//			
//		}
//	}
	
	class dbmsconnection{
		String url;
		String username;
		String password;
		public dbmsconnection(String url,String username,String password) {
			this.url =url;
			this.username =username;
			this.password = password;
		}
		
		
		
	//	public Connection  getConnection() throws InstantiationException, IllegalAccessException,
	//}
	
	
	}

