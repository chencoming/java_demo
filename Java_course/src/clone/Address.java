package clone;

/**
 *
 * @author coming  2016-7-7
 */
public class Address implements Cloneable{

	private String addr;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	protected Object clone()  {
		Address address = null;
		try {
			return address = (Address)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return address;
	}
	
	
}
