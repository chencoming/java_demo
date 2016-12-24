package others;
/**
 *
 * @author coming  2016-7-7
 */
public class TestObjectClone {
	
	protected void protectedMethod() {
		System.out.println("Call protected method");
	}

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		TestObjectClone t = new TestObjectClone();
		try {
			t.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
