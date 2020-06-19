package jun.example.spring.boot.mybatis;

import jun.example.spring.boot.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class SpringBootMybatisExampleApplication {

    private static final Logger logger = LogManager.getLogger(
            SpringBootMybatisExampleApplication.class);

    private SqlSessionFactory sessionFactory;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisExampleApplication.class, args);
    }

    @PostConstruct
    public void doMyBatisTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        this.sessionFactory = new SqlSessionFactoryBuilder().build(is);

        doDeleteAll();
        doInsert();
        doSelectOne();
        doUpdate();
        doSelectAll();
    }

    private void doInsert() {
        logger.info("doInsert ...");
        try (SqlSession session = this.sessionFactory.openSession()) {
            User user = new User.Builder()
                    .withName("admin")
                    .withSex("男")
                    .withAge(99)
                    .getResult();
            session.insert("mybatis.mapper.UserMapper.save", user);
            session.commit();
        }
    }

    private void doSelectAll() {
        logger.info("doSelectAll ...");
        try (SqlSession session = this.sessionFactory.openSession()) {
            List<User> users = session.selectList("mybatis.mapper.UserMapper.findAll");
            logger.info("count of users: " + users.size());
            users.forEach(user -> {
                logger.info("    user:" + user.toString());
            });
        }
    }

    private void doSelectOne() {
        logger.info("doSelectOne ...");
        try (SqlSession session = this.sessionFactory.openSession()) {
            Map<String, Object> resultMap = session.selectOne(
                    "mybatis.mapper.UserMapper.findOneWithMap", "admin");
            resultMap.forEach((k, v) -> logger.info("  k:" + k + " v:" + v));
        }
    }

    private void doUpdate() {
        logger.info("doUpdate ...");
        try (SqlSession session = this.sessionFactory.openSession()) {
            User user = session.selectOne(
                    "mybatis.mapper.UserMapper.findOneWithObject", "admin");
            user.setAge(18);
            logger.info("new user:" + user.toString());
            session.update("mybatis.mapper.UserMapper.updateOne", user);
            session.commit();
        }
    }

    private void doDeleteAll() {
        logger.info("doDeleteAll ...");
        try (SqlSession session = this.sessionFactory.openSession()) {
            session.delete("mybatis.mapper.UserMapper.deleteAll");
            session.commit();
        }
    }

}
