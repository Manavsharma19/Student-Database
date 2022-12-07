package application;

public class module {
	private  String name;
    private  String Module;
    private  String Grade;
   
    public module (String name,String Module,String Grade) {
    	//Constructor
    	this.name = name;
    	this.Module = Module;
    	this.Grade = Grade;
    }
    
    public module() {}
    
   

	public String getName() {
    	return this.name;
    }
    
   
    public void setName(String name ){
		this.name = name;
	}
    
    public String getModule() {
    	return this.Module;
    }
    
   
    public void setModule(String Module ){
		this.Module = Module;
	}
    
    public String getGrade() {
    	return this.Grade;
    }
    
   
    public void setGrade(String Grade ){
		this.Grade = Grade;
	}
}
