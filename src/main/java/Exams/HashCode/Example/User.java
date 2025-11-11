package Exams.HashCode.Example;


import java.util.*;

/*
对于基本数据类型，int等小写开头的和
String不用new出来的，
==比较的是值，new出来的比较的是地址。equals默认比较地址。
 */
/*
修改User
 */
public class User {
    int id ;
    String name;
    Stu stu;

    public User(int id, String name){
        this.id = id;
        this.name =name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return (id == user.id && name== user.name);
    }

    @Override
    public int hashCode() {
        return id*name.hashCode()* stu.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String s1 = "666";
        String s2 = "666";
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        System.out.println("demo2");
        String s3 = new String("666");
        String s4 = new String("666");

        System.out.println(s3==s4); // false ,地址

        System.out.println(s3.equals(s4)); //ture


        System.out.println(s3.hashCode()==s4.hashCode());//ture
 // hashcode（）地址的转换值
        //基本包装类（都重写了hashCode()）
        //集合类“内容相同 ⇒ hashCode 相同”
        //记录类（Record）和枚举类（Enum）
        System.out.println("User TTTTTT");
        User u1 = new User(1,"HuangWeiqi");
        User u2 = new User(1,"HuangWeiqi");

        System.out.println(u1==u2); //== 比较地址对于new的

        System.out.println(u1.equals(u2));


        System.out.println(u1.hashCode()==u2.hashCode());

        Set<User> hm = new HashSet<>();
        hm.add(u1);
        hm.add(u2);
        for(User u:hm){
            System.out.println(u);
        }

    }
}

