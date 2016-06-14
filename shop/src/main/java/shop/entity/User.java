package shop.entity;

import java.io.Serializable;

public class User implements Serializable {
	private String userId;
	
	private String acount;
	
	public User() {  
        
    }  
      
    /** 
     * <br>------------------------------<br> 
     */  
    public User(String userId,String acount) {  
        super();  
        this.userId=userId; 
        this.acount=acount;  
    }  

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}
	
}
