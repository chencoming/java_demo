package thefirstpart;

public class TestStyleChange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char c = 'A';
		int i = (int)c;
		System.out.println(i);
		float f = 1000.34f;
		i = (int) f; 
		System.out.println(i);
		double d = 1000.34;
		i = (int) d;
		System.out.println(i);
		i = 97;
		c = (char)i;
		System.out.println(c);
		
	}

}
