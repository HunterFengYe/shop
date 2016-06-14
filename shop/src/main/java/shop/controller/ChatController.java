package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.service.ChatService;
import shop.utils.ParamUtils;
import shop.utils.StringUtils;

@Controller
@RequestMapping("/chat/")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @ResponseBody
    @RequestMapping("chatInfo")
    public void chatInfo(HttpServletRequest request,HttpServletResponse response){
    	Integer from=(Integer)request.getSession().getAttribute("userId");
    	Integer to=ParamUtils.paramInt(request, "to");
    	List<Map<String,Object>> chatList=chatService.chatInfo(from,to);
    	String json=StringUtils.json(chatList);
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
