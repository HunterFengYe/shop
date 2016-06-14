package shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.service.ClassService;
import shop.service.UserService;
import shop.utils.ParamUtils;

@Controller
@RequestMapping("/common/")
public class CommonController {
	@Autowired
	private UserService userService;
	@Autowired
	private ClassService classService;
	@RequestMapping("toIndex")
    public String toIndex(HttpServletRequest request){
		request.setAttribute("curNav", 1);
    	return "index";
    }
	@RequestMapping("toMsg")
    public String toMsg(HttpServletRequest request){
		request.setAttribute("curNav", 2);
		Integer userId=(Integer)request.getSession().getAttribute("userId");
		if(userId==null){
			return "user/notSession";
		}
    	return "common/message";
    }
	@RequestMapping("toMy")
    public String toMy(HttpServletRequest request){
		request.setAttribute("curNav", 3);
		Integer userId=(Integer)request.getSession().getAttribute("userId");
		if(userId!=null){
			Map<String,Object> userMap=userService.getUserById(userId);
			request.setAttribute("userMap", userMap);
		}
    	return "common/my";
    }
	@RequestMapping("talk")
	public String talk(HttpServletRequest request){
		Integer to=ParamUtils.paramInt(request, "to");
		Integer userId=(Integer)request.getSession().getAttribute("userId");
		String userName=userService.getUserName(userId);
		String toName=userService.getUserName(to);
		request.setAttribute("to", to);
		request.setAttribute("toName", toName);
		request.setAttribute("userName", userName);
		return "socket/talk";
	}
	@RequestMapping("toLogin")
    public String toLogin(){
    	return "user/login";
    }
	@RequestMapping("toRegister")
	public String toRegister(){
		return "user/register";
	}
	
	@RequestMapping("toPublish")
	public String toPublish(HttpServletRequest request){
		Integer userId=(Integer)request.getSession().getAttribute("userId");
		if(userId==null){
			return "user/notSession";
		}
		Integer classId=ParamUtils.paramInt(request, "classId");
		request.setAttribute("classId", classId);
		return "user/publish";
	}
	@RequestMapping("toClass")
	public String toClass(HttpServletRequest request){
		Integer classId=ParamUtils.paramInt(request, "classId");
		request.setAttribute("classId", classId);
		String className=classService.getCla(classId);
		request.setAttribute("className", className);
		return "article/class";
	}
	@RequestMapping("toArticle")
	public String toArticle(HttpServletRequest request){
		Integer arId=ParamUtils.paramInt(request, "arId");
		Integer userId=(Integer)request.getSession().getAttribute("userId");
		if(userId==null){
			return "user/notSession";
		}
		request.setAttribute("arId", arId);
		return "article/article";
	}

}
