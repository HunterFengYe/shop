package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Ionic")
public class IonicController {
	@ResponseBody
	@RequestMapping("/test")
	public String test(){
		return "111";
	}
	

}
