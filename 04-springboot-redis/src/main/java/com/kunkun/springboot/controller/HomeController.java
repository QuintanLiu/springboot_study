package com.kunkun.springboot.controller;

import com.kunkun.springboot.model.News;
import com.kunkun.springboot.service.NewsService;
import com.kunkun.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    //遇到的坑，如果不加method，页面启动不起来。
    @RequestMapping(value = "/home",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String index(Model model){
        //这边是可以读出数据来的

        //线程池------缓存穿透问题的复现
        ExecutorService executorService = Executors.newFixedThreadPool(8*2);

        for(int i = 0;i < 50000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    List<News> newsList = newsService.getLatestNews(0,0,10);
                }
            });
        }

        List<News> newsList = newsService.getLatestNews(0,0,10);
        News news=newsList.get(0);
        return news.getImage();
    }
}
