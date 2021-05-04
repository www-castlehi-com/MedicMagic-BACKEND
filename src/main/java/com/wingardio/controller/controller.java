package com.wingardio.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public class controller {
    @RequestMapping("/android3")
    public void androidTestWithRequest(HttpServletRequest request){
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("memo"));
    }
}
