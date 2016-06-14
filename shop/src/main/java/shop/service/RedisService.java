package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.RedisDao;
import shop.entity.User;

@Service
public class RedisService {
	
	@Autowired
	private RedisDao redisDao;
	
	public String addYes(String arId){
		if(redisDao.get(arId)==null){
			User user=new User(arId,"1");
			  if(redisDao.add(user)){
				  return "1";
			  }
		}
		String acount=redisDao.get(arId).getAcount();
		Integer ac=Integer.parseInt(acount)+1;
		User user=new User(arId,ac.toString());
		 if( redisDao.update(user)){
			 return user.getAcount();
		 }
		return "-1";
	}
	
	public String getYes(String arId){
		User user=redisDao.get(arId);
		if(user==null){
			return "-1";
		}else{
			return user.getAcount();
		}
	}
	

}
