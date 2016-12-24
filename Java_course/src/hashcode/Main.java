package hashcode;

import java.util.HashSet;
import java.util.Set;

public class Main{

    public static void main(String[] args) {
    	System.out.println("a".hashCode());
    	System.out.println(new String("abc").hashCode());
    	System.out.println(new String("abc").hashCode());
    	
        /*Set<Person> set = new HashSet<Person>();
        
        Person p1 = new Person(11, 1, "张三");
        Person p2 = new Person(12, 1, "李四");
        Person p3 = new Person(11, 1, "张三");
        Person p4 = new Person(11, 1, "李四");
        
        //只验证p1、p3
        System.out.println("p1 == p3? :" + (p1 == p3));
        System.out.println("p1.equals(p3)?:"+p1.equals(p3));
        System.out.println("-----------------------分割线--------------------------");
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        System.out.println("set.size()="+set.size());*/
    }
}