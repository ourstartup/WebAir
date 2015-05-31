package com.sensorhub.iot.comment.web;

import com.sensorhub.iot.comment.manager.CommentManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2015/5/7.
 */
@Controller
@RequestMapping("comment")
public class CommentController
{
    private CommentManager commentManager;

    @Resource
    public void setCommentManager(CommentManager commentManager)
    {
        this.commentManager = commentManager;
    }
    @RequestMapping("post")
    @ResponseBody
    Map postComment(@RequestParam(value = "file", required = true) MultipartFile file,
                    @RequestParam(value = "articleId", required =true) String articleId,
                    @RequestParam(value = "message", required = true) String message,
                    @RequestParam(value = "userId", required = true) String userId,
                    @RequestParam(value = "cid", required = false) String cid)

    {
        boolean result = commentManager.addComment(Long.parseLong(articleId),
                message, Long.parseLong(userId),
                file, Long.parseLong(cid));
        Map map = new HashMap<String, Object>();
        if (result == true)
        {
            map.put("success", true);
            map.put("message", "评论成功");
        } else
        {
            map.put("success", false);
            map.put("message", "评论失败");
        }
        return map;

    }
}
