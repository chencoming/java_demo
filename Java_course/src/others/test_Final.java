package others;

public class test_Final {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		
		final StringBuffer a = new StringBuffer("immutable");
		StringBuffer b = new StringBuffer("what");
		//执行如下语句将报告编译期错误：
		//a = new StringBuffer("");
		//但是，执行如下语句则可以通过编译：
		
		a.append(" broken!");
		System.out.println(a);
		
		final int a1 = 10;
		int ab = 20;

		test_interface aface;
		

	}

}


