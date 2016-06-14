package shop.dao;

import java.util.List;

import shop.entity.User;

public interface RedisDao {
	 boolean add(User user);  
     
	    /** 
	     * �������� ʹ��pipeline��ʽ 
	     * <br>------------------------------<br> 
	     * @param list 
	     * @return 
	     */  
	    boolean add(List<User> list);  
	      
	    /** 
	     * ɾ�� 
	     * <br>------------------------------<br> 
	     * @param key 
	     */  
	    void delete(String key);  
	      
	    /** 
	     * ɾ����� 
	     * <br>------------------------------<br> 
	     * @param keys 
	     */  
	    void delete(List<String> keys);  
	      
	    /** 
	     * �޸� 
	     * <br>------------------------------<br> 
	     * @param user 
	     * @return  
	     */  
	    boolean update(User user);  
	  
	    /** 
	     * ͨ��key��ȡ 
	     * <br>------------------------------<br> 
	     * @param keyId 
	     * @return  
	     */  
	    User get(String keyId);  

}
