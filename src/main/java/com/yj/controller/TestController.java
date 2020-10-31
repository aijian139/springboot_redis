package com.yj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {

    @GetMapping("test")
    public void redisSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> list = (List<String>) request.getSession().getAttribute("list");
        if(list==null){
            list = new ArrayList<>();
        }
        list.add("1234");
        request.getSession().setAttribute("list", list);
        response.getWriter().print("size:"+list.size());
        response.getWriter().print("sessionId:"+request.getSession().getId());
    }

}
