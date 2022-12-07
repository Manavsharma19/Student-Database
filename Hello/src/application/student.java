package application;

import java.time.LocalDate;


/**
* Student Class 
* Where the fields of the student are created and declared private
* The constructor is also created here 
* And the data is returned with the use of this. keyword
* The Getters and Setters are used as values when sending the executeUpdate data to  the mysql database
*/

public class student {
		private String sid;
		private String first_nm;
		private String middle_nm;
		private String last_nm;
		private String date;
		//LocalDate birthday = LocalDate.of(1987, 06, 24);
		
		
		public student(String sid,String first_nm,String middle_nm,String last_nm,String date) {
			this.sid =sid;
			this.first_nm =first_nm;
			this.middle_nm =middle_nm;
			this.last_nm = last_nm;
			
		
}
		 public String getLastnm() {
			    return last_nm ;
			  }

			  // Setter
			  public void setLastnm(String newName) {
			    this.last_nm = newName;
		
}
			  
			  public String getMiddlenm() {
				    return middle_nm ;
				  }

				  // Setter
				  public void setMiddlenm(String newName) {
				    this.middle_nm = newName;
			
	}
			  
			  
			  public String getName() {
				    return first_nm;
				  }

				  // Setter
				  public void setName(String newName) {
				    this.first_nm = newName;
			
	}
				  
				  
				  public String getSid() {
					    return sid;
					  }

					  // Setter
					  public void setSid(String id) {
					    this.sid = id;
				
		}
					  
					  public String getDate() {
						    return date;
						  }

						  // Setter
						  public void setDate(String newdate) {
						    this.date = newdate;
					
			}
						
					  
				  
				  
				  
}
