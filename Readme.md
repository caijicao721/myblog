# 个人博客系统

建库

```sql
CREATE DATABASE myblog;
USE myblog;
CREATE TABLE `category`(
`id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(512)  NOT NULL COMMENT '标题',
  `content` text  COMMENT '内容描述',
  `summary` text ,
  `icon` varchar(128)  DEFAULT NULL COMMENT '图标',
  `post_count` int(11) unsigned DEFAULT '0' COMMENT '该分类的内容数量',
  `order_num` int(11) DEFAULT NULL COMMENT '排序编码',
  `parent_id` bigint(32) unsigned DEFAULT NULL COMMENT '父级分类的ID',
  `meta_keywords` varchar(256) DEFAULT NULL COMMENT 'SEO关键字',
  `meta_description` varchar(256)  DEFAULT NULL COMMENT 'SEO描述内容',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `modified` datetime DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论的内容',
  `parent_id` bigint(32) DEFAULT NULL COMMENT '回复的评论ID',
  `post_id` bigint(32) NOT NULL COMMENT '评论的内容ID',
  `user_id` bigint(32) NOT NULL COMMENT '评论的用户ID',
  `vote_up` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '“顶”的数量',
  `vote_down` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '“踩”的数量',
  `level` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '置顶等级',
  `status` tinyint(2) DEFAULT NULL COMMENT '评论的状态',
  `created` datetime NOT NULL COMMENT '评论的时间',
  `modified` datetime DEFAULT NULL COMMENT '评论的更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(128)  NOT NULL COMMENT '标题',
  `content` longtext  NOT NULL COMMENT '内容',
  `edit_mode` varchar(32)  NOT NULL DEFAULT '0' COMMENT '编辑模式：html可视化，markdown ..',
  `category_id` bigint(32) DEFAULT NULL,
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户ID',
  `vote_up` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '支持人数',
  `vote_down` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '反对人数',
  `view_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '访问量',
  `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '评论数量',
  `recommend` tinyint(1) DEFAULT NULL COMMENT '是否为精华',
  `level` tinyint(2) NOT NULL DEFAULT '0' COMMENT '置顶等级',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `modified` datetime DEFAULT NULL COMMENT '最后更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(128)  NOT NULL COMMENT '昵称',
  `password` varchar(128)  NOT NULL COMMENT '密码',
  `email` varchar(64)  DEFAULT NULL COMMENT '邮件',
  `mobile` varchar(32)  DEFAULT NULL COMMENT '手机电话',
  `point` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '积分',
  `sign` varchar(255)  DEFAULT NULL COMMENT '个性签名',
  `gender` varchar(16)  DEFAULT NULL COMMENT '性别',
  `wechat` varchar(32)  DEFAULT NULL COMMENT '微信号',
  `vip_level` int(32) DEFAULT NULL COMMENT 'vip等级',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `avatar` varchar(256)  NOT NULL COMMENT '头像',
  `post_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '内容数量',
  `comment_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '评论数量',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `lasted` datetime DEFAULT NULL COMMENT '最后的登陆时间',
  `created` datetime NOT NULL COMMENT '创建日期',
  `modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_action` (
  `id` varchar(32)  NOT NULL DEFAULT '',
  `user_id` varchar(32)  DEFAULT NULL COMMENT '用户ID',
  `action` varchar(32)  DEFAULT NULL COMMENT '动作类型',
  `point` int(11) DEFAULT NULL COMMENT '得分',
  `post_id` varchar(32)  DEFAULT NULL COMMENT '关联的帖子ID',
  `comment_id` varchar(32)  DEFAULT NULL COMMENT '关联的评论ID',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `post_user_id` bigint(20) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

插入一些数据做测试用

```sql 
INSERT INTO `user` VALUES ('1', 'MarkerHub', '96e79218965eb72c92a549dd5a330112', '11111@qq.com', null, '0', '关注公众号：MarkerHub，一起学Java！！', '0', null, '0', null, '/res/images/avatar/default.png', '0', '0', '0', '2020-04-28 14:54:11', '2020-04-28 14:37:24', null);
INSERT INTO `user` VALUES ('2', 'test007', '96e79218965eb72c92a549dd5a330112', '1111@qq.com', null, '0', null, '0', null, '0', null, '/res/images/avatar/default.png', '0', '0', '0', null, '2020-04-28 14:45:07', null);
INSERT INTO `m_user` VALUES ('3', 'test004', '96e79218965eb72c92a549dd5a330112', '144d11@qq.com', null, '0', null, '0', null, '0', null, '/res/images/avatar/default.png', '0', '0', '0', null, '2020-04-28 14:48:26', null);
INSERT INTO `user` VALUES ('4', 'test005', '96e79218965eb72c92a549dd5a330112', '144d15@qq.com', null, '0', null, '0', null, '0', null, '/res/images/avatar/default.png', '0', '0', '0', null, '2020-04-28 14:48:26', null);
INSERT INTO `user` VALUES ('5', 'test00756', '96e79218965eb72c92a549dd5a330112', '45454541@qq.com', null, '0', null, '0', null, '0', null, '/res/images/avatar/default.png', '0', '0', '0', null, '2020-04-28 14:53:49', null);

INSERT INTO `post` VALUES ('1', 'Github上最值得学习的100个Java开源项目，涵盖各种技术栈！', ' 你有多久没好好学习一个开源项目了？\n\n你是否经常为找不到好的开源项目而烦恼？\n\n你是否为不知道怎么入手去看一个开源项目？\n\n你是否想看别人的项目学习笔记？\n\n你是否想跟着别人的项目搭建过程一步一步跟着做项目？\n\n为了让更多Java的开发者能更容易找到值得学习的开源项目，我搭建了这个Java开源学习网站，宗旨梳理Java知识，共享开源项目笔记。来瞧一瞧：\n\nimg[//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200414/473f73a3eb6f471e8154620a9c1d5306.png] \n\n网站截图中可以看出，点击筛选条件组合之后，再点击搜索就会搜索出对应的开源项目。\n\n比如打开renren-fast项目，可以看到具体项目的信息，以及模块解析。\n\nimg[//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200414/f74740692dab4d9c937cd56336ead1b4.png]\n\n这样，学习一个开源项目就不再费劲，每天学习一个开源项目，在不知不觉中提升技术水平！目前网站已经收录了近100个开源项目，让Java不再难懂！\n\n直接扫公众号下方的二维码，回复关键字【网站】即可获得网站的域名地址！\n\nimg[//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200414/f58f7c6d038c4dfb99bd9cf40935392e.png]\n\n关注上方的公众号二维码\n\n回复【网站】即可获取项目域名地址。\n', '0', '1', '1', '0', '0', '5', '0', '1', '1', null, '2020-04-28 14:41:41', '2020-04-28 14:41:41');
INSERT INTO `post` VALUES ('2', '关注公众号：MarkerHub，一起学习Java', '关注学习：\n\nimg[//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200414/f58f7c6d038c4dfb99bd9cf40935392e.png] ', '0', '1', '1', '0', '0', '3', '0', '1', '1', null, '2020-04-28 14:55:16', '2020-04-28 14:55:16');
INSERT INTO `post` VALUES ('3', '111111111111', '1222222222222222', '0', '1', '1', '0', '0', '1', '0', '0', '0', null, '2020-04-28 14:55:48', '2020-04-28 14:55:48');

INSERT INTO `category` VALUES ('1', '提问', null, null, null, '0', null, null, null, null, '2020-04-28 22:36:48', null, '0');
INSERT INTO `category` VALUES ('2', '分享', null, null, null, '0', null, null, null, null, '2020-04-28 22:36:48', null, '0');
INSERT INTO `category` VALUES ('3', '讨论', null, null, null, '0', null, null, null, null, '2020-04-28 22:36:48', null, '0');
INSERT INTO `category` VALUES ('4', '建议', null, null, null, '0', null, null, null, null, '2020-04-28 22:36:48', null, '0');

```

新建springboot项目，先勾上spring-web dev-tool mysql-driver,freemarker,mybatis-plus等依赖,java选1.8，springboot选择2.1.2版本,

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.cao</groupId>
    <artifactId>myeblog</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>myeblog</name>
    <description>myeblog</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!--代码生成器-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.2.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

```

现在可以启动项目了，访问http://localhost:8080。

第一步先连接数据库,在配置文件里输入

```yam
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 123456
    url: jdbc:mysql://localhost/myblog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC

```

监控sql查询语句,导入p6spy依赖

```xml
        <dependency>
            <groupId>p6spy</groupId>
            <artifactId>p6spy</artifactId>
            <version>3.8.6</version>
        </dependency>
```

```yml
  datasource:
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    username: root
    password: 123456
    url: jdbc:p6spy:mysql://localhost/myblog?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC
```



**==freemarker 前端渲染不熟悉，只能照抄（不会vue前后端分离）==**

mybatis-plus 代码生成器的超类 basecontroller和baseentity要自己创建

basecontroller 定义一些通用的参数

```java
public class BaseController {

    @Autowired
    HttpServletRequest req;
}
```

BaseEntity定义通用字段

```java
@Data
public class BaseEntity {

    private Long id;

    private Date created;

    private Date modified;
}
```

定义mybatisplus配置，扫描mapper包路径,导入分页插件

```java
@Configuration
@MapperScan("com.cao.myeblog.mapper")
public class MybatisPlusConfig {
    
}

```

项目启动加载分类信息

```java
package com.cao.myeblog.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.myeblog.entity.Category;
import com.cao.myeblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;

@Component
public class ContextStartUp implements ApplicationRunner, ServletContextAware {

    @Autowired
    CategoryService categoryService;

    ServletContext servletContext;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Category> categories = categoryService.list(new QueryWrapper<Category>()
                .eq("status", 0)
        );
        servletContext.setAttribute("categories",categories);

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
}

```

分页信息是一个联表查询，根据// 1分页信息 2分类 3用户 4置顶  5精选 6排序这些信息进行联表查询

并且返回VO类型

**view object 视图对象**

定义PostVo

```java
@Data
public class PostVo extends Post{

    private Long authorId;
    private String authorName;
    private String authorAvatar;

    private String categoryName;
}
```

==自定义条件构造器  加上三表联合查询(难点)==

```java
        if (level==null) level=-1;
        QueryWrapper wrapper=new QueryWrapper<Post>()
                .eq(categoryId != null,  "category_id", categoryId)
                .eq(userId != null,  "user_id", userId)
                .eq(level == 0,  "level", 0)
                .gt(level > 0,  "level", 0)
                .orderByDesc(order != null, order);
        return postMapper.selectPosts(page, wrapper);
```

Mapper接口定义方法，xml写sql语句

```java
@Component
public interface PostMapper extends BaseMapper<Post> {
    IPage<PostVo>  selectPosts(Page page,@Param(Constants.WRAPPER) QueryWrapper wrapper);
}
```

联表查询语句

```sql
    <select id="selectPosts" resultType="com.cao.myeblog.vo.PostVo">
        SELECT
            p.*,
            u.id AS authorId,
            u.username AS authorName,
            u.avatar AS authorAvatar,
            c.id AS categoryId,
            c.name AS categoryName
        FROM
            post p
        LEFT JOIN user u ON p.user_id = u.id
        LEFT JOIN category c ON p.category_id = c.id
        ${ew.customSqlSegment}
    </select>
```

**==注意如果要用条件构造器自定义sql需要添加@Param(Constants.WRAPPER)在wrapper ，sql中加${ew.customSqlSegment}==**

分页渲染很复杂，freemarker可以自定义标签

很复杂，套用模板+配置

```java
@Configuration
public class FreemarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;


    @PostConstruct
    public void setUp() {
        configuration.setSharedVariable("timeAgo", new TimeAgoMethod());
    }

}
```

分页渲染用的layui的插件，不多赘述

freemarker自定义标签

```java
package com.cao.myeblog.template;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.common.templates.DirectiveHandler;
import com.cao.myeblog.common.templates.TemplateDirective;
import com.cao.myeblog.service.PostService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class PostsTemplate extends TemplateDirective {
    @Autowired
    PostService postService;

    @Override
    public String getName() {
        return "posts";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Integer level = handler.getInteger("level");
        Integer pn = handler.getInteger("pn", 1);
        Integer size = handler.getInteger("size", 2);
        Long categoryId = handler.getLong("categoryId");
        IPage page = postService.paging(new Page(pn, size), categoryId, null, level, null, "created");
        handler.put(RESULTS,page).render();
    }
}

```

评论查询分页与post分页相似

建立commentvo

```java
package com.cao.myeblog.vo;

import com.cao.myeblog.entity.Comment;
import lombok.Data;

@Data
public class CommentVo extends Comment {
    private Long authorId;
    private String authorName;
    private String authorAvatar;
}

```

建立自定义sql

```xml
    <select id="selectComments" resultType="com.cao.myeblog.vo.CommentVo">
        SELECT
            c.*,
            u.id as authorId,
            u.username as authorName,
            u.avatar as authorAvatar
        FROM
            `comment` AS c
                LEFT JOIN `user` AS u ON c.user_id = u.id
            ${ew.customSqlSegment}
    </select>
```

自定义条件构造器

```java
    @Autowired
    CommentMapper commentMapper;
    @Override
    public IPage<CommentVo> paing(Page page, Long postId, Long userId, String order) {

        return commentMapper.selectComments(page,new QueryWrapper<CommentVo>()
                .eq(postId!=null,"post_id",postId)
                .eq(userId != null, "user_id", userId)
                .orderByDesc(order != null, order));
    }
```

查询分页结果放进req

```java
        // 1分页，2文章id，3用户id，排序
        IPage<CommentVo> results = commentService.paing(getPage(), vo.getId(), null, "created");
        req.setAttribute("currentCategoryId", vo.getCategoryId());
        req.setAttribute("post",vo);
        req.setAttribute("pageData",results);
```

然后在freemarker进行渲染。

到此，首页相关的大概界面做完了。

第二步，完成本周热议以及文章的缓存，登录的认证。

本周热议功能

利用redis有序集合 去重加排序

zadd key score member

zadd day:1 10 post:1 12 post:2 ...

...

zadd day:7 10 post:1 12 post:2 ...



合并这7个有序集合。再倒序输出



zunionstore 目标key 合并集合数量 合并的key

zunionstore week:rank 7 day:1 day:2 ... day:7



合并后 倒序（从大到小）

zrevrange key start stop

zrevrange week:rank 0 10



引入hutools工具以及redis

```xml
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.1.17</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId><!--netty-->
        </dependency>
```

配置redis

```yaml
  redis:
    host: localhost
    port: 6379
    password:
```

设置redis序列化方式

```java
package com.cao.myeblog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        return template;
    }

}
```



初始化本周热议内容 并缓存查到的文章

```java
/**
     * 本周热议初始化
     */
    @Override
    public void initWeekRank() {
        // 获取7天内发表的文章
        List<Post> posts = this.list(new QueryWrapper<Post>()
                .ge("created", DateUtil.offsetDay(new Date(), -7))
                .select("id, title, user_id, comment_count, view_count, created"));

        // 初始化文章的总评论量
        for (Post post : posts) {
            String key = "day:" + DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_FORMAT);
            // 工具类是先member再score，反过来要
            redisUtil.zSet(key, post.getId(), post.getCommentCount());

            // 7天后自动过期(15号发表，7-（18-15）=4)
            long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
            long expireTime = (7 - between) * 24 * 60 * 60; // 有效时间

            redisUtil.expire(key, expireTime);


            // 缓存文章的一些基本信息（id，标题，评论数量，作者）
            this.hashCachePostIdAndTitle(post, expireTime);
        }
        // 做并集
        this.zunionAndStoreLast7DayForWeekRank();
    }

    /**
     * 本周合并每日评论数量操作
     */
    private void zunionAndStoreLast7DayForWeekRank() {
        String  currentKey = "day:" + DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);

        String destKey = "week:rank";
        List<String> otherKeys = new ArrayList<>();
        for(int i=-6; i < 0; i++) {
            String temp = "day:" +
                    DateUtil.format(DateUtil.offsetDay(new Date(), i), DatePattern.PURE_DATE_FORMAT);

            otherKeys.add(temp);
        }

        redisUtil.zUnionAndStore(currentKey, otherKeys, destKey);
    }

    /**
     * 缓存文章的基本信息
     * @param post
     * @param expireTime
     */
    private void hashCachePostIdAndTitle(Post post, long expireTime) {
        String key = "rank:post:" + post.getId();
        boolean hasKey = redisUtil.hasKey(key);
        if(!hasKey) {

            redisUtil.hset(key, "post:id", post.getId(), expireTime);
            redisUtil.hset(key, "post:title", post.getTitle(), expireTime);
            redisUtil.hset(key, "post:commentCount", post.getCommentCount(), expireTime);
            redisUtil.hset(key, "post:viewCount", post.getViewCount(), expireTime);
        }

    }
```

自定义宏标签 显示本周热议内容, 类似与postTemplate

区别是要从redis里获取相关缓存

```java
package com.cao.myeblog.template;

import com.cao.myeblog.common.templates.DirectiveHandler;
import com.cao.myeblog.common.templates.TemplateDirective;
import com.cao.myeblog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 本周热议
 */
@Component
public class HotsTemplate extends TemplateDirective {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public String getName() {
        return "hots";
    }



    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        String weekRankKey = "week:rank";

        Set<ZSetOperations.TypedTuple> typedTuples = redisUtil.getZSetRank(weekRankKey, 0, 6);

        List<Map> hotPosts = new ArrayList<>();

        for (ZSetOperations.TypedTuple typedTuple : typedTuples) {
            Map<String, Object> map = new HashMap<>();

            Object value = typedTuple.getValue(); // post的id
            String postKey = "rank:post:" + value;

            map.put("id", value);
            map.put("title", redisUtil.hget(postKey, "post:title"));
            map.put("commentCount", typedTuple.getScore());

            hotPosts.add(map);
        }

        handler.put(RESULTS, hotPosts).render();

    }
}

```

此外 在添加评论时，还需要更新缓存和重新做并集

```java
@Override
    public void incrCommentCountAndUnionForWeekRank(long postId, boolean isIncr) {
        String  currentKey = "day:" + DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
        redisUtil.zIncrementScore(currentKey, postId, isIncr? 1: -1);
        Post post = this.getById(postId);
        // 缓存更新
        long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
        long expireTime = (7 - between) * 24 * 60 * 60; // 有效时间
        this.hashCachePostIdAndTitle(post, expireTime);

        //重新并集
        this.zunionAndStoreLast7DayForWeekRank();
    }
```

文章阅读量  先存在缓存里 定时刷新到数据库里 

在点击博客的时候就要增加浏览量

postController中post/{id}添加业务

```java
@Override
    public void putViewCount(PostVo vo) {
        String key = "rank:post:" + vo.getId();
        // 1、从缓存中获取viewcount
        Integer viewCount = (Integer) redisUtil.hget(key, "post:viewCount");
        // 2.如果没有viewcount，就加1
        if (viewCount!=null){
            vo.setViewCount(viewCount+1);
        }else{
            vo.setViewCount(vo.getViewCount()+1);
        }
        // 同步到缓存
        redisUtil.hset(key,"post:viewCount",vo.getViewCount());
    }
```

设置定时任务，一定时间更新浏览量到数据库中

注意用到@Scheduled注解 还有cron = "0/5 * * * * *" 这种时间表达式

```java
package com.cao.myeblog.schedules;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.myeblog.entity.Post;
import com.cao.myeblog.service.PostService;
import com.cao.myeblog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ViewCountSyncTask {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    PostService postService;


    @Scheduled(cron = "0/5 * * * * *") //每半分钟同步
    public void task() {
        //获取所有文章缓存key
        Set<String> keys = redisTemplate.keys("rank:post:*");
        //获取所有有viewcount的id
        List<String> ids = new ArrayList<>();
        for (String key : keys) {
            if(redisUtil.hHasKey(key, "post:viewCount")){
                ids.add(key.substring("rank:post:".length()));
            }
        }
        // 如果空就直接返回
        if(ids.isEmpty()) return;

        // 需要更新阅读量
        List<Post> posts = postService.list(new QueryWrapper<Post>().in("id", ids));



        posts.forEach((post) ->{
            Integer viewCount = (Integer) redisUtil.hget("rank:post:" + post.getId(), "post:viewCount");
            post.setViewCount(viewCount);
        });

        //文章可能已经删除，如果没有直接跳过
        if(posts.isEmpty()) return;

        //mybatis-plus根据多个id进行更新
        boolean isSucc = postService.updateBatchById(posts);

        if(isSucc) {
            ids.forEach((id) -> {
                redisUtil.hdel("rank:post:" + id, "post:viewCount");
                System.out.println(id + "---------------------->同步成功");
            });
        }
    }

}

```

对于定时任务，需要在启动项目类上添加@EnableScheduling注解才有效。更新完之后将缓存中的viewcount删掉，下次从数据库中获取。

下一步实现登录功能，定义认证的AuthController,引入图形验证码kaptcha，引入shiro框架以及自定义标签。

```xml
        <!--验证码-->
        <dependency>
            <groupId>com.github.axet</groupId>
            <artifactId>kaptcha</artifactId>
            <version>0.0.9</version>
        </dependency>

        <!--shiro权限框架-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.0</version>
        </dependency>

        <dependency>
            <groupId>net.mingsoft</groupId>
            <artifactId>shiro-freemarker-tags</artifactId>
            <version>0.1</version>
        </dependency>
```

配置图片验证码

```java
package com.cao.myeblog.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer () {
        Properties propertis = new Properties();
        propertis.put("kaptcha.border", "no");
        propertis.put("kaptcha.image.height", "38");
        propertis.put("kaptcha.image.width", "150");
        propertis.put("kaptcha.textproducer.font.color", "black");
        propertis.put("kaptcha.textproducer.font.size", "32");
        Config config = new Config(propertis);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

}
```

定义验证码controller

```java
    @GetMapping("/capthca.jpg")
    public void kaptcha(HttpServletResponse resp) throws IOException {

        // 验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        req.getSession().setAttribute(KAPTCHA_SESSION_KEY, text);

        resp.setHeader("Cache-Control", "no-store, no-cache");
        resp.setContentType("image/jpeg");
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }
```

写一个统一结果方便返回(状态码)Result类

```java
package com.cao.myeblog.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    // 0成功，-1失败
    private int status;
    private String msg;
    private Object data;
    private String action;

    public static Result success() {
        return Result.success("操作成功", null);
    }

    public static Result success(Object data) {
        return Result.success("操作成功", data);
    }

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.status = 0;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.status = -1;
        result.data = null;
        result.msg = msg;
        return result;
    }

    public Result action(String action){
        this.action = action;
        return this;
    }


}
```

添加校验工具类ValidationUtil

```java
package com.cao.myeblog.util;

import lombok.Data;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationUtil {

    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();
    /**
     * 校验对象
     *
     * @param t bean
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> ValidResult validateBean(T t,Class<?>...groups) {
        ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validate(t,groups);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(violation.getPropertyPath().toString(), violation.getMessage());
            }
        }
        return result;
    }
    /**
     * 校验bean的某一个属性
     *
     * @param obj          bean
     * @param propertyName 属性名称
     * @return ValidResult
     */
    public static <T> ValidResult validateProperty(T obj, String propertyName) {
        ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(propertyName, violation.getMessage());
            }
        }
        return result;
    }
    /**
     * 校验结果类
     */
    @Data
    public class ValidResult {

        /**
         * 是否有错误
         */
        private boolean hasErrors;

        /**
         * 错误信息
         */
        private List<ErrorMessage> errors;

        public ValidResult() {
            this.errors = new ArrayList<>();
        }
        public boolean hasErrors() {
            return hasErrors;
        }

        public void setHasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
        }

        /**
         * 获取所有验证信息
         * @return 集合形式
         */
        public List<ErrorMessage> getAllErrors() {
            return errors;
        }
        /**
         * 获取所有验证信息
         * @return 字符串形式
         */
        public String getErrors(){
            StringBuilder sb = new StringBuilder();
            for (ErrorMessage error : errors) {
                sb.append(error.getPropertyPath()).append(":").append(error.getMessage()).append(" ");
            }
            return sb.toString();
        }

        public void addError(String propertyName, String message) {
            this.errors.add(new ErrorMessage(propertyName, message));
        }
    }

    @Data
    public class ErrorMessage {

        private String propertyPath;

        private String message;

        public ErrorMessage() {
        }

        public ErrorMessage(String propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }
    }

}
```

在user上添加判断注解

```java
package com.cao.myeblog.entity;

import com.cao.myeblog.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author caojiaming
 * @since 2022-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码名不能为空")
    private String password;

    /**
     * 邮件
     */
    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 手机电话
     */
    private String mobile;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 性别
     */
    private String gender;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * vip等级
     */
    private Integer vipLevel;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 内容数量
     */
    private Integer postCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 最后的登陆时间
     */
    private LocalDateTime lasted;


}

```

实现userservice的注册功能

```java
@Override
    public Result register(User user) {
        int count=this.count(new QueryWrapper<User>()
                .eq("email", user.getEmail())
                .or()
                .eq("username", user.getUsername()));
        if(count > 0) return Result.fail("用户名或邮箱已被占用");
        User temp = new User();
        temp.setUsername(user.getUsername());
        // 密码md5加密处理
        temp.setPassword(SecureUtil.md5(user.getPassword()));
        temp.setEmail(user.getEmail());
        temp.setAvatar("/res/images/avatar/default.png");

        temp.setCreated(new Date());
        temp.setPoint(0);
        temp.setVipLevel(0);
        temp.setCommentCount(0);
        temp.setPostCount(0);
        temp.setGender("0");
        this.save(temp);
        
        return Result.success();
    }
```

集成shiro框架

```xml
        <!--shiro权限框架-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.0</version>
        </dependency>
```





定义shiro用户的通用信息类Profile

```java
package com.cao.myeblog.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;
    private String email;
    private String sign;

    private String avatar;
    private String gender;
    private Date created;

    public String getSex() {
        return "0".equals(gender) ? "女" : "男";
    }

}

```

shiro登录是通过realm,定义realm继承AuthorizingRealm,分为认证和授权

授权方法

```java
@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        AccountProfile profile = userService.login(usernamePasswordToken.getUsername(), String.valueOf( usernamePasswordToken.getPassword()));

        SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());
        return info;
    }
```

UserService实现登录功能方法

```java
@Override
    public AccountProfile login(String email, String password) {
        User user = this.getOne(new QueryWrapper<User>().eq("email", email));
        if(user == null) {
            throw new UnknownAccountException();
        }
        if(!user.getPassword().equals(password)){
            throw new IncorrectCredentialsException();
        }

        user.setLasted(new Date());
        this.updateById(user);

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);

        return profile;
    }
```

shiro配置类

```java
package com.cao.myeblog.config;

import cn.hutool.core.map.MapUtil;
import com.cao.myeblog.shiro.AccountRealm;
import com.cao.myeblog.shiro.AuthFilter;
import com.example.shiro.AccountRealm;
import com.example.shiro.AuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(AccountRealm accountRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);

        log.info("------------------>securityManager注入成功");

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        // 配置登录的url和登录成功的url
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/user/center");
        // 配置未授权跳转页面
        filterFactoryBean.setUnauthorizedUrl("/error/403");

        filterFactoryBean.setFilters(MapUtil.of("auth", authFilter()));

        Map<String, String> hashMap = new LinkedHashMap<>();

        hashMap.put("/res/**", "anon");

        hashMap.put("/user/home", "auth");
        hashMap.put("/user/set", "auth");
        hashMap.put("/user/upload", "auth");
        hashMap.put("/user/index", "auth");
        hashMap.put("/user/public", "auth");
        hashMap.put("/user/collection", "auth");
        hashMap.put("/user/mess", "auth");
        hashMap.put("/msg/remove/", "auth");
        hashMap.put("/message/nums/", "auth");

        hashMap.put("/collection/remove/", "auth");
        hashMap.put("/collection/find/", "auth");
        hashMap.put("/collection/add/", "auth");

        hashMap.put("/post/edit", "auth");
        hashMap.put("/post/submit", "auth");
        hashMap.put("/post/delete", "auth");
        hashMap.put("/post/reply/", "auth");
        
        hashMap.put("/login", "anon");
        filterFactoryBean.setFilterChainDefinitionMap(hashMap);

        return filterFactoryBean;

    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}

```

自定义shiro标签

```java
configuration.setSharedVariable("shiro", new ShiroTags());
```

登录post方法

```java
@ResponseBody
    @PostMapping("/login")
    public Result doLogin(String email, String password) {
        if(StrUtil.isEmpty(email) || StrUtil.isBlank(password)) {
            return Result.fail("邮箱或密码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(email, SecureUtil.md5(password));
        try {
            SecurityUtils.getSubject().login(token);

        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                return Result.fail("用户不存在");
            } else if (e instanceof LockedAccountException) {
                return Result.fail("用户被禁用");
            } else if (e instanceof IncorrectCredentialsException) {
                return Result.fail("密码错误");
            } else {
                return Result.fail("用户认证失败");
            }
        }

        return Result.success().action("/");
    }
```

登出方法

```java
    @RequestMapping("/user/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }
```

下一步做user的几个页面

获取当前用户id

```java
    protected AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getProfileId() {
        return getProfile().getId();
    }
```

主页方法

```java
@GetMapping("/user/home")
    public String home() {

        User user = userService.getById(getProfileId());

        List<Post> posts = postService.list(new QueryWrapper<Post>()
                .eq("user_id", getProfileId())
                // 30天内
                //.gt("created", DateUtil.offsetDay(new Date(), -30))
                .orderByDesc("created")
        );
		// 放入request进行展示
        req.setAttribute("user", user);
        req.setAttribute("posts", posts);
        return "/user/home";
    }
```

基本设置界面以及设置提交表单

```java
@GetMapping("/user/set")
    public String set() {
        User user = userService.getById(getProfileId());
        req.setAttribute("user", user);

        return "/user/set";
    }

    @ResponseBody
    @PostMapping("/user/set")
    public Result doSet(User user) {

        if(StrUtil.isNotBlank(user.getAvatar())) {

            User temp = userService.getById(getProfileId());
            temp.setAvatar(user.getAvatar());
            userService.updateById(temp);

            AccountProfile profile = getProfile();
            profile.setAvatar(user.getAvatar());

            SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

            return Result.success().action("/user/set#avatar");
        }

        if(StrUtil.isBlank(user.getUsername())) {
            return Result.fail("昵称不能为空");
        }
        int count = userService.count(new QueryWrapper<User>()
                .eq("username", getProfile().getUsername())
                .ne("id", getProfileId()));
        if(count > 0) {
            return Result.fail("改昵称已被占用");
        }

        User temp = userService.getById(getProfileId());
        temp.setUsername(user.getUsername());
        temp.setGender(user.getGender());
        temp.setSign(user.getSign());
        userService.updateById(temp);

        AccountProfile profile = getProfile();
        profile.setUsername(temp.getUsername());
        profile.setSign(temp.getSign());
        SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

        return Result.success().action("/user/set#info");
    }
```

上传头像请求接口，上传工具类以及常量定义，yaml配置属性

```java
package com.cao.myeblog.common.lang;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Consts {

    @Value("${file.upload.dir}")
    private String uploadDir;

//    public static final Long IM_DEFAULT_USER_ID = 999L;
//
//    public final static Long IM_GROUP_ID = 999L;
//    public final static String IM_GROUP_NAME = "e-group-study";
//
//    //消息类型
//    public final static String IM_MESS_TYPE_PING = "pingMessage";
//    public final static String IM_MESS_TYPE_CHAT = "chatMessage";
//
//    public static final String IM_ONLINE_MEMBERS_KEY = "online_members_key";
//    public static final String IM_GROUP_HISTROY_MSG_KEY = "group_histroy_msg_key";

}

```

上传工具类UploadUtil

```java
package com.cao.myeblog.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.cao.myeblog.common.lang.Consts;
import com.cao.myeblog.common.lang.Result;
import com.cao.myeblog.shiro.AccountProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class UploadUtil {

    @Autowired
    Consts consts;

    public final static String type_avatar = "avatar";

    public Result upload(String type, MultipartFile file) throws IOException {

        if(StrUtil.isBlank(type) || file.isEmpty()) {
            return Result.fail("上传失败");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = consts.getUploadDir();

        if ("avatar".equalsIgnoreCase(type)) {
            AccountProfile profile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
            fileName = "/avatar/avatar_" + profile.getId() + suffixName;

        } else if ("post".equalsIgnoreCase(type)) {
            fileName = "/post/post_" + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_MS_PATTERN) + suffixName;
        }

        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            log.info("上传成功后的文件路径未：" + filePath + fileName);

            String path = filePath + fileName;
            String url = "/upload" + fileName;

            log.info("url ---> {}", url);

            return Result.success(url);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(null);

    }

}

```

上传接口方法

```java
    @ResponseBody
    @PostMapping("/user/upload")
    public Result uploadAvatar(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return uploadUtil.upload(UploadUtil.type_avatar, file);
    }
```

添加静态目录配置MvcConfig

```java
package com.cao.myeblog.config;

import com.cao.myeblog.common.lang.Consts;
import com.example.common.lang.Consts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    Consts consts;

//    @Bean
//    ModelMapper modelMapper() {
//        return new ModelMapper();
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/avatar/**")
                .addResourceLocations("file:///" + consts.getUploadDir() + "/avatar/");
    }

}

```

修改密码方法

```java
@ResponseBody
    @PostMapping("/user/repass")
    public Result repass(String nowpass, String pass, String repass) {
        if(!pass.equals(repass)) {
            return Result.fail("两次密码不相同");
        }

        User user = userService.getById(getProfileId());

        String nowPassMd5 = SecureUtil.md5(nowpass);
        if(!nowPassMd5.equals(user.getPassword())) {
            return Result.fail("密码不正确");
        }

        user.setPassword(SecureUtil.md5(pass));
        userService.updateById(user);

        return Result.success().action("/user/set#pass");

    }
```

usercontroller一些其他方法

```java
    @GetMapping("/user/index")
    public String index() {
        return "/user/index";
    }
```

mybatis-plus支持嵌套sql

```java
    @ResponseBody
    @GetMapping("/user/collection")
    public Result collection() {
        IPage page = postService.page(getPage(), new QueryWrapper<Post>()
                .inSql("id", "SELECT post_id FROM user_collection where user_id = " + getProfileId())
        );
        return Result.success(page);
    }
```

用户中心其他方法

```java
@GetMapping("/user/mess")
    public String mess() {

        IPage<UserMessageVo> page = messageService.paging(getPage(), new QueryWrapper<UserMessage>()
                .eq("to_user_id", getProfileId())
                .orderByDesc("created")
        );

        // 把消息改成已读状态
        List<Long> ids = new ArrayList<>();
        for(UserMessageVo messageVo : page.getRecords()) {
            if(messageVo.getStatus() == 0) {
                ids.add(messageVo.getId());
            }
        }
        // 批量修改成已读
        messageService.updateToReaded(ids);

        req.setAttribute("pageData", page);
        return "/user/mess";
    }

    @ResponseBody
    @PostMapping("/message/remove/")
    public Result msgRemove(Long id,
                            @RequestParam(defaultValue = "false") Boolean all) {

        boolean remove = messageService.remove(new QueryWrapper<UserMessage>()
                .eq("to_user_id", getProfileId())
                .eq(!all, "id", id));

        return remove ? Result.success(null) : Result.fail("删除失败");
    }

    @ResponseBody
    @RequestMapping("/message/nums/")
    public Map msgNums() {

        int count = messageService.count(new QueryWrapper<UserMessage>()
                .eq("to_user_id", getProfileId())
                .eq("status", "0")
        );
        return MapUtil.builder("status", 0)
                .put("count", count).build();
    }
```

**问题：代码生成器的自动生成controller是@RestController  需要修改！！！！！！！！！！！！！！！！！**

在我们使用MySQL8.0的，连接数据库会存在一定的问题
当提示。Public Key Retrieval is not allowed 错误的时候，我们可以在连接数据库的配置文件中加上

**allowPublicKeyRetrieval=true**



添加Ajax异步请求的过滤器，防止未登录出现请求异常的情况

```java
package com.cao.myeblog.shiro;

import cn.hutool.json.JSONUtil;
import com.cao.myeblog.common.lang.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * shiro过滤器，判断请求是否是ajax请求
 */
public class AuthFilter extends UserFilter {

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // ajax 弹窗显示未登录
        String header = httpServletRequest.getHeader("X-Requested-With");
        if(header != null  && "XMLHttpRequest".equals(header)) {
            boolean authenticated = SecurityUtils.getSubject().isAuthenticated();
            if(!authenticated) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(JSONUtil.toJsonStr(Result.fail("请先登录！")));
            }
        } else {
            // web 重定向到登录页面
            super.redirectToLogin(request, response);
        }
    }
}

```

更新shiro过滤配置

```java
filterFactoryBean.setFilters(MapUtil.of("auth", authFilter()));

        Map<String, String> hashMap = new LinkedHashMap<>();

        hashMap.put("/res/**", "anon");

        hashMap.put("/user/home", "auth");
        hashMap.put("/user/set", "auth");
        hashMap.put("/user/upload", "auth");
        hashMap.put("/user/index", "auth");
        hashMap.put("/user/public", "auth");
        hashMap.put("/user/collection", "auth");
        hashMap.put("/user/mess", "auth");
        hashMap.put("/msg/remove/", "auth");
        hashMap.put("/message/nums/", "auth");

        hashMap.put("/collection/remove/", "auth");
        hashMap.put("/collection/find/", "auth");
        hashMap.put("/collection/add/", "auth");

        hashMap.put("/post/edit", "auth");
        hashMap.put("/post/submit", "auth");
        hashMap.put("/post/delete", "auth");
        hashMap.put("/post/reply/", "auth");
```

添加全局异常处理，@ControllerAdvice注解

@ExceptionHandler(value = Exception.class)

```java
package com.cao.myeblog.common.exception;

import cn.hutool.json.JSONUtil;
import com.cao.myeblog.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExcepitonHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handler(HttpServletRequest req, HttpServletResponse resp, Exception e) throws IOException {

        // ajax 处理
        String header = req.getHeader("X-Requested-With");
        if(header != null  && "XMLHttpRequest".equals(header)) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().print(JSONUtil.toJsonStr(Result.fail(e.getMessage())));
            return null;
        }

        if(e instanceof NullPointerException) {
            // ...
        }

        // web处理
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

}

```

添加错误界面

```java
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>错误提示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="欢迎关注公众号：MarkerHub">
    <meta name="description" content="更多开源项目可以关注公众号：MarkerHub">
    <link rel="stylesheet" href="/res/layui/css/layui.css">
    <link rel="stylesheet" href="/res/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
  <div class="layui-container">
    <a class="fly-logo" href="/">
      <img src="https://www.markerhub.com/dist/images/logo/markerhub-logo.png" alt="MarkerHub" style="height: 41px;">
    </a>
    <ul class="layui-nav fly-nav layui-hide-xs">
      <li class="layui-nav-item layui-this">
        <a href="/"><i class="iconfont icon-jiaoliu"></i>主页</a>
      </li>
      <li class="layui-nav-item">
        <a target="_blank" href="https://mp.weixin.qq.com/s/lR5LC5GnD2Gs59ecV5R0XA"><i class=""></i>最新企业面试题</a>
      </li>
    </ul>

    <ul class="layui-nav fly-nav-user">

    </ul>
  </div>
</div>

<div class="layui-container fly-marginTop">
    <div class="fly-panel">
        <div class="fly-none">
            <h2><i class="iconfont icon-tishilian"></i></h2>
            <p>${message}</p>
        </div>
    </div>
</div>

</body>
</html>


```

下一步做文章的一些属性以及发帖等等。

判断是否收藏,添加收藏以及取消收藏

```java
/**
     * 判断用户是否收藏了文章
     * @param pid
     * @return
     */
    @ResponseBody
    @PostMapping("/collection/find/")
    public Result collectionFind(Long pid) {
        int count = collectionService.count(new QueryWrapper<UserCollection>()
                .eq("user_id", getProfileId())
                .eq("post_id", pid)
        );
        return Result.success(MapUtil.of("collection", count > 0 ));
    }

    @ResponseBody
    @PostMapping("/collection/add/")
    public Result collectionAdd(Long pid) {
        Post post = postService.getById(pid);

        Assert.isTrue(post != null, "改帖子已被删除");
        int count = collectionService.count(new QueryWrapper<UserCollection>()
                .eq("user_id", getProfileId())
                .eq("post_id", pid)
        );
        if(count > 0) {
            return Result.fail("你已经收藏");
        }

        UserCollection collection = new UserCollection();
        collection.setUserId(getProfileId());
        collection.setPostId(pid);
        collection.setCreated(new Date());
        collection.setModified(new Date());

        collection.setPostUserId(post.getUserId());

        collectionService.save(collection);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/collection/remove/")
    public Result collectionRemove(Long pid) {
        Post post = postService.getById(pid);
        Assert.isTrue(post != null, "改帖子已被删除");

        collectionService.remove(new QueryWrapper<UserCollection>()
                .eq("user_id", getProfileId())
                .eq("post_id", pid));

        return Result.success();
    }
```

编辑文章的方法 

```java
    @GetMapping("/post/edit")
    public String edit(){
        String id = req.getParameter("id");
        if(!StringUtils.isEmpty(id)) {
            Post post = postService.getById(id);
            Assert.isTrue(post != null, "改帖子已被删除");
            Assert.isTrue(post.getUserId().longValue() == getProfileId().longValue(), "没权限操作此文章");
            req.setAttribute("post", post);
        }
        req.setAttribute("categories", categoryService.list());
        return "/post/edit";
    }

    @ResponseBody
    @PostMapping("/post/submit")
    public Result submit(Post post) {
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(post);
        if(validResult.hasErrors()) {
            return Result.fail(validResult.getErrors());
        }

        if(post.getId() == null) {
            post.setUserId(getProfileId());

            post.setModified(new Date());
            post.setCreated(new Date());
            post.setCommentCount(0);
            post.setEditMode(null);
            post.setLevel(0);
            post.setRecommend(false);
            post.setViewCount(0);
            post.setVoteDown(0);
            post.setVoteUp(0);
            postService.save(post);

        } else {
            Post tempPost = postService.getById(post.getId());
            Assert.isTrue(tempPost.getUserId().longValue() == getProfileId().longValue(), "无权限编辑此文章！");

            tempPost.setTitle(post.getTitle());
            tempPost.setContent(post.getContent());
            tempPost.setCategoryId(post.getCategoryId());
            postService.updateById(tempPost);
        }

//        // 通知消息给mq，告知更新或添加
//        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,
//                new PostMqIndexMessage(post.getId(), PostMqIndexMessage.CREATE_OR_UPDATE));

        return Result.success().action("/post/" + post.getId());
    }

    @ResponseBody
    @Transactional
    @PostMapping("/post/delete")
    public Result delete(Long id) {
        Post post = postService.getById(id);

        Assert.notNull(post, "该帖子已被删除");
        Assert.isTrue(post.getUserId().longValue() == getProfileId().longValue(), "无权限删除此文章！");

        postService.removeById(id);

        // 删除相关消息、收藏等
        messageService.removeByMap(MapUtil.of("post_id", id));
        collectionService.removeByMap(MapUtil.of("post_id", id));

//        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,
//                new PostMqIndexMessage(post.getId(), PostMqIndexMessage.REMOVE));

        return Result.success().action("/user/index");
    }

@ResponseBody
    @Transactional
    @PostMapping("/post/reply/")
    public Result reply(Long jid, String content) {
        Assert.notNull(jid, "找不到对应的文章");
        Assert.hasLength(content, "评论内容不能为空");
        Post post = postService.getById(jid);
        Assert.isTrue(post != null, "该文章已被删除");

        Comment comment = new Comment();
        comment.setPostId(jid);
        comment.setContent(content);
        comment.setUserId(getProfileId());
        comment.setCreated(new Date());
        comment.setModified(new Date());
        comment.setLevel(0);
        comment.setVoteDown(0);
        comment.setVoteUp(0);
        commentService.save(comment);

        // 评论数量加一
        post.setCommentCount(post.getCommentCount() + 1);
        postService.updateById(post);

        // 本周热议数量加一
        postService.incrCommentCountAndUnionForWeekRank(post.getId(), true);

        // 通知作者，有人评论了你的文章
        // 作者自己评论自己文章，不需要通知
        if(comment.getUserId() != post.getUserId()) {
            UserMessage message = new UserMessage();
            message.setPostId(jid);
            message.setCommentId(comment.getId());
            message.setFromUserId(getProfileId());
            message.setToUserId(post.getUserId());
            message.setType(1);
            message.setContent(content);
            message.setCreated(new Date());
            message.setStatus(0);
            messageService.save(message);
            
        }

        // 通知被@的人，有人回复了你的文章
        if(content.startsWith("@")) {
            String username = content.substring(1, content.indexOf(" "));
            System.out.println(username);

            User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
            if(user != null) {
                UserMessage message = new UserMessage();
                message.setPostId(jid);
                message.setCommentId(comment.getId());
                message.setFromUserId(getProfileId());
                message.setToUserId(user.getId());
                message.setType(2);
                message.setContent(content);
                message.setCreated(new Date());
                message.setStatus(0);
                messageService.save(message);

                // 即时通知被@的用户
            }
        }
        return Result.success().action("/post/" + post.getId());
    }
```

需要保证post实体类的一些属性验证

```java
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 编辑模式：html可视化，markdown ..
     */
    private String editMode;

    @NotNull(message = "分类不能为空")
    private Long categoryId;
```

设置管理员属性

```java
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountProfile profile = (AccountProfile) principals.getPrimaryPrincipal();

        // 给id为1的admin赋予admin角色
        if(profile.getId() == 1) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("admin");
            return info;
        }

        return null;
    }
```

设置管理员操作的一些方法（加精）

```java
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    /**
     *
     * @param id
     * @param rank 0表示取消，1表示操作
     * @param field
     * @return
     */
    @ResponseBody
    @PostMapping("/jie-set")
    public Result jetSet(Long id, Integer rank, String field) {

        Post post = postService.getById(id);
        Assert.notNull(post, "该帖子已被删除");

        if("delete".equals(field)) {
            postService.removeById(id);
            return Result.success();

        } else if("status".equals(field)) {
            post.setRecommend(rank == 1);

        }  else if("stick".equals(field)) {
            post.setLevel(rank);
        }
        postService.updateById(post);
        return Result.success();
    }
```

实时通知消息就不做了websocket 



搜索引擎开发，利用rabbitmq同步，先导入依赖，modelmap用于bean的拷贝

```java
        <!-- es 6.4.3版本 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId><!--netty-->
            <version>2.1.1.RELEASE</version>
        </dependency>

        <!--整合rabbitmq-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
            
                    <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>1.1.0</version>
        </dependency>
```

es和rabbitmq的设置

```yaml
  data:
    elasticsearch:
      cluster-name: elasticsearch
      cluster-nodes: localhost:9300
      repositories:
        enabled: true
  rabbitmq:
    username: guest
    password: guest
    host: localhost
    port: 5672
```

定义search接口方法

```java
    @RequestMapping("/search")
    public String search(String q) {

        IPage pageData = searchService.search(getPage(), q);

        req.setAttribute("q", q);
        req.setAttribute("pageData", pageData);
        return "search";
    }
```

定义es搜索对应索引的实体类

```java
package com.cao.myeblog.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName="post", type="post", createIndex=true)
public class PostDocment implements Serializable {

    @Id
    private Long id;

    // ik分词器
    @Field(type = FieldType.Text, searchAnalyzer="ik_smart", analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Long)
    private Long authorId;

    @Field(type = FieldType.Keyword)
    private String authorName;
    private String authorAvatar;

    private Long categoryId;
    @Field(type = FieldType.Keyword)
    private String categoryName;

    private Integer level;
    private Boolean recommend;

    private Integer commentCount;
    private Integer viewCount;

    @Field(type = FieldType.Date)
    private Date created;


}

```

启动问题

```
// 解决elasticsearch启动保存问题
System.setProperty("es.set.netty.runtime.available.processors", "false");
```

**存在es里面需要分词**

定义搜索服务层

```java
package com.cao.myeblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.search.mq.PostMqIndexMessage;
import com.cao.myeblog.vo.PostVo;

import java.util.List;

public interface SearchService {

    IPage search(Page page, String keyword);

    int initEsData(List<PostVo> records);

    void createOrUpdateIndex(PostMqIndexMessage message);

    void removeIndex(PostMqIndexMessage message);
}

```

实现类

```java
package com.cao.myeblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cao.myeblog.entity.Post;
import com.cao.myeblog.search.model.PostDocument;
import com.cao.myeblog.search.mq.PostMqIndexMessage;
import com.cao.myeblog.search.repository.PostRepository;
import com.cao.myeblog.service.PostService;
import com.cao.myeblog.service.SearchService;
import com.cao.myeblog.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostService postService;

    @Override
    public IPage search(Page page, String keyword) {
        // 分页信息 mybatis plus的page 转成 jpa的page
        Long current = page.getCurrent() - 1;
        Long size = page.getSize();
        Pageable pageable = PageRequest.of(current.intValue(), size.intValue());

        // 搜索es得到pageData
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                "title", "authorName", "categoryName");

        org.springframework.data.domain.Page<PostDocument> documents = postRepository.search(multiMatchQueryBuilder, pageable);

        // 结果信息 jpa的pageData转成mybatis plus的pageData
        IPage pageData = new Page(page.getCurrent(), page.getSize(), documents.getTotalElements());
        pageData.setRecords(documents.getContent());
        return pageData;
    }

    @Override
    public int initEsData(List<PostVo> records) {
        if(records == null || records.isEmpty()) {
            return 0;
        }

        List<PostDocument> documents = new ArrayList<>();
        for(PostVo vo : records) {
            // 映射转换
            PostDocument postDocument = modelMapper.map(vo, PostDocument.class);
            documents.add(postDocument);
        }
        postRepository.saveAll(documents);
        return documents.size();
    }

    @Override
    public void createOrUpdateIndex(PostMqIndexMessage message) {
        Long postId = message.getPostId();
        PostVo postVo = postService.selectOnePost(new QueryWrapper<Post>().eq("p.id", postId));

        PostDocument postDocument = modelMapper.map(postVo, PostDocument.class);

        postRepository.save(postDocument);

        log.info("es 索引更新成功！ ---> {}", postDocument.toString());
    }

    @Override
    public void removeIndex(PostMqIndexMessage message) {
        Long postId = message.getPostId();

        postRepository.deleteById(postId);
        log.info("es 索引删除成功！ ---> {}", message.toString());
    }
}

```

mq配置

```java
package com.cao.myeblog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public final static String es_queue = "es_queue";
    public final static String es_exchage = "es_exchage";
    public final static String es_bind_key = "es_exchage";

    @Bean
    public Queue exQueue() {
        return new Queue(es_queue);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(es_exchage);
    }

    @Bean
    Binding binding(Queue exQueue, DirectExchange exchange) {
        return BindingBuilder.bind(exQueue).to(exchange).with(es_bind_key);
    }

}

```

mq消息

```java
package com.cao.myeblog.search.mq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PostMqIndexMessage implements Serializable {

    // 两种type
    public final static String CREATE_OR_UPDATE = "create_update";
    public final static String REMOVE = "remove";

    private Long postId;
    private String type;

}

```

mq监听者

```java
package com.cao.myeblog.search.mq;

import com.cao.myeblog.config.RabbitConfig;
import com.cao.myeblog.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = RabbitConfig.es_queue)
public class MqMessageHandler {

    @Autowired
    SearchService searchService;

    @RabbitHandler
    public void handler(PostMqIndexMessage message) {

        log.info("mq 收到一条消息： {}", message.toString());

        switch (message.getType()) {
            case PostMqIndexMessage.CREATE_OR_UPDATE:
                searchService.createOrUpdateIndex(message);
                break;
            case PostMqIndexMessage.REMOVE:
                searchService.removeIndex(message);
                break;
            default:
                log.error("没找到对应的消息类型，请注意！！ --》 {}", message.toString());
                break;
        }
    }

}

```

在更新post操作的时候同时发送消息给队列

```java
        // 通知消息给mq，告知更新或添加
        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,
                new PostMqIndexMessage(post.getId(), PostMqIndexMessage.CREATE_OR_UPDATE));

        amqpTemplate.convertAndSend(RabbitConfig.es_exchage, RabbitConfig.es_bind_key,
                new PostMqIndexMessage(post.getId(), PostMqIndexMessage.REMOVE));
```



初始化es的接口，第一次同步项目

```java
    @ResponseBody
    @PostMapping("/initEsData")
    public Result initEsData() {

        int size = 10000;
        Page page = new Page();
        page.setSize(size);

        long total = 0;

        for (int i = 1; i < 1000; i ++) {
            page.setCurrent(i);

            IPage<PostVo> paging = postService.paging(page, null, null, null, null, null);

            int num = searchService.initEsData(paging.getRecords());

            total += num;

            // 当一页查不出10000条的时候，说明是最后一页了
            if(paging.getRecords().size() < size) {
                break;
            }
        }

        return Result.success("ES索引初始化成功，共 " + total + " 条记录！", null);
    }
```

