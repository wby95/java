package java反射机制;

import java.util.HashMap;

/**
 * Created by wby on 2018/2/9.
 */
public class Person {
    String name;
    int age;
    HashMap hashMap;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
