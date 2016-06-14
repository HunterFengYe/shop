package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.service.CommentService;
import shop.service.UserService;
import shop.utils.ParamUtils;
import shop.utils.StringUtils;

@Controller
@RequestMapping("/comment/")
public class CommentController {
    @Autowired
    private CommentService comService;
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("comment")
    public void comment(HttpServletRequest request,HttpServletResponse response){
    	Integer userId=(Integer) request.getSession().getAttribute("userId");
    	Integer arId=ParamUtils.paramInt(request, "arId");
    	Integer to=ParamUtils.paramInt(request, "to");
    	Integer reply=ParamUtils.paramInt(request, "reply");
    	String body=request.getParameter("body");
    	Map<String,Object> comMap=new HashMap<String,Object>();
    	comMap.put("userId", userId);
    	comMap.put("arId",arId );
    	comMap.put("to",to );
    	comMap.put("reply",reply );
    	comMap.put("body",body );
    	comMap.put("date",new Date() );
    	comService.addCom(comMap);
    	String fName=userService.getUserName(userId);
    	String tName=userService.getUserName(reply);
    	comMap.clear();
    	comMap.put("fName", fName);
    	comMap.put("tName", tName);
    	String json=StringUtils.json(comMap);
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
    
    @ResponseBody
    @RequestMapping("aboutMe")
    public void aboutMe(HttpServletRequest request,HttpServletResponse response){
    	Integer userId=(Integer) request.getSession().getAttribute("userId");
    	List<Map<String,Object>> comList=comService.aboutMe(userId);
    	String json=StringUtils.json(comList);
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
