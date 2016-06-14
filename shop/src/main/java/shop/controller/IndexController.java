package shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.dao.RedisDao;
import shop.entity.User;



@Controller
public class IndexController {
	@Autowired
	private RedisDao redisDao;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		request.setAttribute("curNav", 1);
		return "index";
	}
	@RequestMapping("/web")
	public String web(){
		return "common/web";
	}
	@RequestMapping("/redis")
	public String redis(){
		return "redis";
	}
	@RequestMapping("/redisTest")
	public String redisTest(HttpServletRequest request){
		String userId=request.getParameter("id");
		String acount=request.getParameter("acount");
		User user=new  User(userId,acount);
		boolean isAdd=redisDao.add(user);
		if(isAdd){
			return "user/login";
		}else{
			return "user/notSession";
		}
			
	}
}
