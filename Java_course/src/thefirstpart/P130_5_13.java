package thefirstpart;

public class P130_5_13 {
	public static void main(String[] args) {
		System.out.printf("%c%10s\n",'i',"m(i)");
		for(int i = 1;i <= 20;i++)
			System.out.printf("%-5d%7.4f\n",i,method(i));
	}
	
	public static float method(int n){
		float sum = 0;
		for(float i = 1;i <= n;i++)
		sum = sum + i / (i + 1);
		return sum;
	}
}
