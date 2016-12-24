package thefirstpart;

public class P97_4_7 {
	public static void main(String[] args) {
		int tuition = 10000;
		for(int i = 0;i < 10;i++){
			tuition *= 1.05; 
		}
		//System.out.println("The tuition of ten years later is :" + tuition);
		int sum = 0;
		for(int j = 0;j < 4;j++){
			sum += tuition; 
			tuition *= 1.05; 
		}
			
		System.out.println("The summary of tuition of four years after ten years is :" + sum);
		
	}

}
