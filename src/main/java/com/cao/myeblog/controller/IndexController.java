package com.cao.myeblog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController{

    @RequestMapping({"","/","index"})
    public String index(){

        // 1分页信息 2分类 3用户 4置顶  5精选 6排序
        IPage result = postService.paging(getPage(),null,null,null,null,"created");
        req.setAttribute("pageData",result);
        req.setAttribute("currentCategoryId",0);
        return "index";
    }

    @RequestMapping("/search")
    public String search(String q) {

        IPage pageData = searchService.search(getPage(), q);

        req.setAttribute("q", q);
        req.setAttribute("pageData", pageData);
        return "search";
    }
}
