package others;
/**
 * 
 * @author coming 2016-7-17
 */
public class TestStringBuffer {

	/**
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operat(a, b);
		System.out.println(a + "," + b);
	}

	public static void operat(StringBuffer x, StringBuffer y) {
		x.append(y);
		y = x;
	}

}
