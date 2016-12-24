package clone;

/**
 *
 * @author coming  2016-7-7
 */
public class Student implements Cloneable{

	private String name;
	
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	protected Object clone() {
		Student stu = null;
		try {
			stu = (Student) super.clone();
			stu.address = (Address)stu.address.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} 
		return stu;
	}
	
	
	
}
