package com.kunkun.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

  @RequestMapping("/jsp")
  public String jsp(Model model){
    model.addAttribute("msg","你好，jsp！");
    return "index";
  }
}
