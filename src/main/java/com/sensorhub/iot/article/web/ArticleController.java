package com.sensorhub.iot.article.web;

import com.sensorhub.iot.article.manager.ArticleManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2015/5/6.
 */
@Controller
@RequestMapping("article")
public class ArticleController
{
    private ArticleManager articleManager;

    @Resource
    public void setArticleManager(ArticleManager articleManager)
    {
        this.articleManager = articleManager;
    }

    @RequestMapping("post")
    @ResponseBody
    Map postArticle(@RequestParam(value = "file", required = true) MultipartFile file,
                    @RequestParam(value = "userId", required =true) String userId,
                    @RequestParam(value = "title", required = true) String title,
                    @RequestParam(value = "body", required = true) String body)
    {

        boolean result = articleManager.saveArticle(title, body, file, Long.parseLong(userId));
        Map map = new HashMap<String, Object>();
        if (result == true)
        {
            map.put("success", true);
            map.put("message","发帖成功");
        }
        else
        {
            map.put("success", false);
            map.put("message","发帖失败");
        }
        return map;


    }


}
