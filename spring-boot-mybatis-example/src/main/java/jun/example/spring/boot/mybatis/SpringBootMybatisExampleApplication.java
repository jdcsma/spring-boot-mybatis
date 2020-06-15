package jun.example.spring.boot.mybatis;

import jun.example.spring.boot.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class SpringBootMybatisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisExampleApplication.class, args);
    }

    @PostConstruct
    public void doMyBatisTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        User user = new User("admin", "男", 99);
        session.insert("mybatis.mapper.UserMapper.save", user);
        session.commit();
        session.close();
    }

}
