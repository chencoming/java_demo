package clone;

/**
 *
 * @author coming  2016-7-7
 */
public class TestMain {

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		
		Student stu1 = new Student();
		stu1.setName("Ğ¡Ã÷");
		Address address = new Address();
		address.setAddr("shenzhen");
		stu1.setAddress(address);
		Student stu2 = (Student) stu1.clone();
		stu1.setName("Ğ¡ºì");
		address.setAddr("guangzhou");
		
		System.out.println(stu1.getName() + stu1.getAddress().getAddr());
		System.out.println(stu2.getName() + stu2.getAddress().getAddr());

	}

}
