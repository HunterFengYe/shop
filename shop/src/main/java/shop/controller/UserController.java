package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.service.UserService;
import shop.utils.ParamUtils;
import shop.utils.StringUtils;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired 
    private UserService userService;
    /**登录*/
	 @RequestMapping("login")
	 public String login(HttpServletRequest request){
		 String phone=request.getParameter("phone");
		 String password=request.getParameter("pwd");
		 Map<String,Object> userMap=userService.getUser(phone);
		 Integer userId=(Integer)userMap.get("user_id");
		 String pwd=(String) userMap.get("user_pwd");
		 if(password.equals(userMap.get("user_pwd"))){
			 HttpSession session=request.getSession();
			 session.setAttribute("userId", userId);
			 request.setAttribute("userMap",userMap);
			 return "common/my";
		 }
		request.setAttribute("errormsg", "用户名与密码不匹配");
		return "user/login";
    }

	 /**检查验证码是否正确,检测手机是否存在*/
     @ResponseBody
     @RequestMapping("check")
     public String check(HttpServletRequest request){
    	 String code=request.getParameter("code");
    	 String phone=request.getParameter("phone");
    	 HttpSession session=request.getSession();
    	 String vcode=(String) session.getAttribute("validateCode");
    	 if(userService.checkPhone(phone)<1){
    		 return "3";
    	 }
    	 if(!code.equalsIgnoreCase(vcode)){
    		 return "2";
    	 }
    	 return "1";
     }
    /**注册*/
    @ResponseBody
    @RequestMapping("register")
    public String register(HttpServletRequest request){
     String code=request.getParameter("code");
   	 String phone=request.getParameter("phone");
   	 String pwd=request.getParameter("pwd");
   	 HttpSession session=request.getSession();
   	 String vcode=(String) session.getAttribute("validateCode");
   	 if(userService.checkPhone(phone)>0){
   		 return "3";
   	 }
   	 if(!code.equalsIgnoreCase(vcode)){
   		 return "2";
   	 }
      Map<String,Object> userMap=new HashMap<String,Object>();
      userMap.put("phone",phone );
      userMap.put("pwd", pwd);
      userMap.put("date", new Date());
      userService.insertUser(userMap);
      userMap.clear();
      userMap=userService.getUser(phone);
      Integer userId=(Integer) userMap.get("user_id");
      session.setAttribute("userId", userId);
      return "1";
    }
    /**显示朋友列表*/
    @ResponseBody
    @RequestMapping("frList")
    public void frList(HttpServletRequest request,HttpServletResponse response){
    	Integer uid=ParamUtils.paramInt(request,"userId");
    	List<Map<String,Object>> frList=new ArrayList<Map<String,Object>>();
    	String fr=userService.getFr(uid);
    	List<Integer> list=StringUtils.intList(fr);
    	frList=userService.getFrList(list);
    	String json=StringUtils.json(frList);
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out;
    	try {
			out = response.getWriter();
			out.print(json);
			out.flush();
		     out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
