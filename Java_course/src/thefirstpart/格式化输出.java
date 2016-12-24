package thefirstpart;

public class 格式化输出 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.printf("%5d%-8s\n",1234,"Java");
		System.out.printf("%-6d%-8s\n",1234,"Java");
		System.out.printf("%10.3f\n",12345.2132);
		System.out.printf("%10.2e", 1.2e+2);

	}

}
