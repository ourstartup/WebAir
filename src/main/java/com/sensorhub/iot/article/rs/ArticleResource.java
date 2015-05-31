package com.sensorhub.iot.article.rs;

import com.sensorhub.iot.article.manager.ArticleManager;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * Created by admin on 2015/1/15.
 */
@Component
@Path("article")
public class ArticleResource
{
    private static Logger logger = LoggerFactory.getLogger(ArticleResource.class);
    private ArticleManager articleManager;

    @POST
    @Path("upload")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({ MediaType.MULTIPART_FORM_DATA})
    /*
    public Map publicArticle(@FormParam("title") String title,
                             @FormParam("body") String body,
                             @FormParam("userId") String userId,
                             @FormParam("file") MultipartFile file)
                             */
    public void publicArticle(FormDataMultiPart title
                             )
    {
        int a=0;
       /*
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
        return map;*/
    }


    @Resource
    public void setArticleManager(ArticleManager articleManager)
    {
        this.articleManager = articleManager;
    }
}
