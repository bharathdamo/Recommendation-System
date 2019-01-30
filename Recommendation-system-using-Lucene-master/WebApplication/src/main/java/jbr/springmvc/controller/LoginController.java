package jbr.springmvc.controller;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import jbr.springmvc.model.Posts;
import jbr.springmvc.model.Login;

import jbr.springmvc.dao.UserDaoImpl;

@Controller
public class LoginController {
  
  
	
  @Autowired
  UserDaoImpl userService;


  
  @RequestMapping(value = "/recommendations", method = RequestMethod.POST)
  public ModelAndView recommendations(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) throws IOException, ParseException
  {
	 System.out.println("post"+login.getUsername());  // get post number
     ModelAndView mav = null;
     String[] ans=userService.fetch_recommendation("post"+login.getUsername());
     mav = new ModelAndView("home");
     mav.addObject("question",ans[0]);
     mav.addObject("code",ans[1]);
     
     mav.addObject("rec1",ans[2]);
     mav.addObject("rec2",ans[3]);
     mav.addObject("rec3",ans[4]);
     mav.addObject("rec4",ans[5]);
     mav.addObject("rec5",ans[6]);
     mav.addObject("rec6",ans[7]);
     mav.addObject("rec7",ans[8]);
     mav.addObject("rec8",ans[9]);
     mav.addObject("rec9",ans[10]); 
     mav.addObject("rec10",ans[11]);
    
     
      /* String[] ans=userService.fetch_recommendation(post_ip);
     mav = new ModelAndView("home");
     mav.addObject("question","ab");
     mav.addObject("code","ab");
     mav.addObject("rec1","ab");
     mav.addObject("rec2","ab");
     mav.addObject("rec3","ab");
     mav.addObject("rec4","ab");
     mav.addObject("rec5","ab");
     mav.addObject("rec6","ab");
     mav.addObject("rec7","ab");
     mav.addObject("rec8","ab");
     mav.addObject("rec9","ab"); */
    return mav;
  }
  
  
  
}