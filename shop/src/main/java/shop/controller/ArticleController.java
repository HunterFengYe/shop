package shop.controller;

import java.io.File;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import shop.service.ArticleService;
import shop.service.CommentService;
import shop.service.RedisService;
import shop.utils.ParamUtils;
import shop.utils.StringUtils;

@Controller
@RequestMapping("/ar/")
public class ArticleController {
    @Autowired
    private ArticleService arService;
    @Autowired 
    private CommentService comService;
    @Autowired
    private RedisService redisService;
    /**查询是否可以删除*/
    @RequestMapping("arDelete")
    public String arDelete(HttpServletRequest request){
    	Integer userId=(Integer)request.getSession().getAttribute("userId");
    	Integer arId=ParamUtils.paramInt(request, "arId");
    	if(userId.equals(arService.getArUser(arId))){
    		
    	}
    	return "";
    }
    /**多文件上传*/
    @RequestMapping("publish")
    public String pubish(@RequestParam("select-photo")MultipartFile[] files, HttpServletRequest request, ModelMap model) throws IOException{
  	  String essay=request.getParameter("p-area");
  	  Integer classId=ParamUtils.paramInt(request, "classId");
  	  String imgName="";
  	  Integer userId=(Integer)request.getSession().getAttribute("userId");
  	  Map<String,Object> uploadMap=new HashMap<String,Object>();
  	  if(files!=null&&files.length>0){
  		  if(files.length==1&&files[0].isEmpty()){
  			  uploadMap.put("classId", classId);
  			  uploadMap.put("essay", essay);
  			  uploadMap.put("imgName", null);
  			  uploadMap.put("userId", userId);
  			  uploadMap.put("date",new Date());
  			  arService.publish(uploadMap);
  			  return "index";  
  		  }
  			for(int i=0;i<files.length;i++){
  				MultipartFile file=files[i];
  				if (!file.isEmpty()) {  
  	                try {  
  	                    // 文件保存路径  
  	                    String filePath = request.getSession().getServletContext().getRealPath("/image/essay/");  
  	                    // 转存文件  
  	                    file.transferTo(new File(filePath,file.getOriginalFilename())); 
  	                    imgName+=file.getOriginalFilename()+",";
  	                } catch (Exception e) {  
  	                    e.printStackTrace();  
  	                }
  	                
  	            }  
  			}
  			uploadMap.put("classId", classId);
  			uploadMap.put("essay", essay);
  			uploadMap.put("imgName", imgName.substring(0, imgName.lastIndexOf(",")));
  			uploadMap.put("userId", userId);
  			uploadMap.put("date",new Date());
  			arService.publish(uploadMap);
  			return "index";
  		} 
  	  return "common/error";
    }
    /**文章分类显示*/
    @ResponseBody
    @RequestMapping("classes")
    public void classes(HttpServletRequest request,HttpServletResponse response){
    	Integer classId=ParamUtils.paramInt(request, "classId");
    	List<Map<String,Object>> arList=arService.getArByClass(classId);
    	for(Map<String,Object> arMap:arList){
    		String arId= arMap.get("ar_id").toString();
    		String acount=redisService.getYes(arId);
    		arMap.put("yes",acount);
    	}
    	String json=StringUtils.json(arList);
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
    /**点赞*/
    @ResponseBody
    @RequestMapping("addYes")
    public String addYes(HttpServletRequest request){
    	String arId=request.getParameter("arId");
    	String acount=redisService.addYes(arId);
    	if(acount.equals("-1")){
    		return "-1";
    	}
    	return acount;
    }
    /**获取赞数量*/
    @ResponseBody
    @RequestMapping("getYes")
    public String getYes(HttpServletRequest request){
		return null;
    	
    }
    /**文章详情*/
    @ResponseBody
    @RequestMapping("article")
    public void article(HttpServletRequest request,HttpServletResponse response){
    	Integer arId=ParamUtils.paramInt(request, "arId");
    	Map<String,Object> arMap=arService.getArById(arId);
    	List<Map<String,Object>> comList=comService.getCom(arId);
    	comList.add(arMap);
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
    /**评论*/
    @ResponseBody
    @RequestMapping("comment")
    public void comment(HttpServletRequest request,HttpServletResponse response){
    	Integer arId=ParamUtils.paramInt(request, "arId");
    	List<Map<String,Object>> reList=arService.getReply(arId);
    	String json=StringUtils.json(reList);
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
