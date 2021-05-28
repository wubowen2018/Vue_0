-- ----------------------------
-- Table structure for user
-- ----------------------------

```SQL
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

-- ----------------------------
-- Records of user
-- ----------------------------

```SQL
INSERT INTO `user` VALUES ('1', 'admin', '123');
```

-- ----------------------------
-- Table structure for book
-- ----------------------------

```SQL
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cover` varchar(255) DEFAULT '',
  `title` varchar(255) NOT NULL DEFAULT '',
  `author` varchar(255) DEFAULT '',
  `date` varchar(20) DEFAULT '',
  `press` varchar(255) DEFAULT '',
  `abs` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_category_on_cid` (`cid`),
  CONSTRAINT `fk_book_category_on_cid` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
```


-- ----------------------------
-- Table structure for category
-- ----------------------------
```SQL
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



tomcat的支持
```XML
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <version>8.5.23</version>
</dependency>
```
---
####JPA(Java Persistence API) DAO（Data Access Object）
可以使用框架的，也可以自己写。并且无需手动创建SQL语句，需要按规范书写方法名
如 `findByUsername`，就是通过 `username` 字段查询到对应的行，并返回给 User 类。

自己创建
```java
package com.evan.wj.dao;

import com.evan.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);
}
```

---
####三层架构（DAO + Service + Controller）
- DAO 用于与数据库的直接交互，定义增删改查等操作
- Service 负责业务逻辑，跟功能相关的代码一般写在这里，编写、调用各种方法对 DAO 取得的数据进行操作
- Controller 负责数据交互，即接收前端发送的数据，通过调用 Service 获得处理后的数据并返回

---
####前后端结合原理
在开发的时候，前端用前端的服务器（Nginx），后端用后端的服务器（Tomcat），当我开发前端内容的时候，可以把前端的请求通过前端服务器转发给后端（称为`反向代理`），这样就能实时观察结果，并且不需要知道后端怎么实现，而只需要知道接口提供的功能，两边的开发人员（两个我）就可以各司其职啦。

1. 反向代理

    你访问了一个网站，你以为它是“谷弟弟”，但其实它是“谷姐”，“谷姐”知道你其实是想找她弟，就取回“谷弟弟”的内容给你看。作为用户的你，是不知道有这个过程的，这么做是为了保护服务器，不暴露服务器的真实地址。        

2. 正向代理

    你要访问一个网站，比如“谷弟弟”，然后发现访问不到，于是你访问了一个能访问到“谷弟弟”的代理服务器，让它帮你拿到你想浏览的页面。

---
#### 每次运行时初始化数据库，如不需要可以注释掉

直接运行项目就可以生成表结构（数据库需要手动创建）。关闭此功能，可以把 application.properties 中的如下代码注释掉

```properties
spring.datasource.initialization-mode=always
```



 






