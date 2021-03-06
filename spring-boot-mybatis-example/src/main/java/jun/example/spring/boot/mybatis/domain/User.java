package jun.example.spring.boot.mybatis.domain;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String sex;
    private int age;

    public User() {}

    public User(long id, String name, String sex, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder {

        private final User user = new User();

        public Builder withName(String name) {
            this.user.setName(name);
            return this;
        }

        public Builder withSex(String sex) {
            this.user.setSex(sex);
            return this;
        }

        public Builder withAge(int age) {
            this.user.setAge(age);
            return this;
        }

        public User getResult() {
            return this.user;
        }
    }

}
