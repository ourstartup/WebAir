package com.sensorhub.iot.comment.rs;

import com.sensorhub.iot.article.domain.Article;
import com.sensorhub.iot.article.manager.ArticleManager;
import com.sensorhub.iot.comment.domain.Comment;
import com.sensorhub.iot.comment.manager.CommentManager;
import com.sensorhub.iot.comment.support.CommentWrapper;
import com.sensorhub.iot.user.domain.UserInfo;
import com.sensorhub.iot.user.manager.UserInfoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/1/15.
 */
@Component
@Path("article")
public class CommentResource
{
    private static Logger logger = LoggerFactory.getLogger(CommentResource.class);
    private CommentManager commentManager;
    private ArticleManager articleManager;
    private UserInfoManager userInfoManager;

    @Resource
    public void setUserInfoManager(UserInfoManager userInfoManager)
    {
        this.userInfoManager = userInfoManager;
    }

    @Resource
    public void setArticleManager(ArticleManager articleManager)
    {
        this.articleManager = articleManager;
    }

    @Resource
    public void setCommentManager(CommentManager commentManager)
    {
        this.commentManager = commentManager;
    }


    @POST
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Map listComment(@FormParam("articleId") String articleId,
                           @FormParam("userId") String userId,
                           @FormParam("page") String page,
                           @FormParam("rows") String rows)
    {
        Map map = new HashMap<String, Object>();
        try
        {
            UserInfo userInfo = userInfoManager.get(Long.parseLong(userId));
            Article article = articleManager.get(Long.parseLong(articleId));
            if (userInfo == null || article == null)
            {
                map.put("success", false);
                map.put("message", "评论获取失败");
            }

            List comments =  commentManager.queryComment(article, Integer.parseInt(page),
                 Integer.parseInt(rows));

            map.put("success", true);
            map.put("message", comments);
        }
        catch (NumberFormatException e)
        {
            map.put("success", false);
            map.put("message", e.getMessage());
        }
        return  map;
    }
}
