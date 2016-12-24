package thefirstpart;

public class P126_5_8 {
	public static void main(String[] args) {
		method1(3,4);
		int max = 0;
		max(1,2,max);
		System.out.println(max);
		System.out.println(1 % 3);
	}
	
	public static void method1(int n,int m){
		n += m;
		xMethod(3);
	}
	
	public static int xMethod( int n ){
		if(n > 0) return 1;
		else if(n == 0) return 0;
		else  return -1;
	}
	
	public static void max(int v1,int v2,int max){
		if(v1 > v2)
			max = v1;
		else
			max = v2;
		
	}
}
