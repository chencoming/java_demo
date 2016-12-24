package thefirstpart;

public class testbreakÑ­»· {
	public static void main(String[] args) {
		/*outer:
			for(int i = 1;i < 10; i++){
				inner:
					for(int j = 1;j < 10;j++){
						if(i * j > 10)
							break outer;
						System.out.println(i + "  " + j + "  "+i * j);					}
			}
		//outer:
			for(int i = 1; i < 10;i++){
			//	inner:
					for(int j = 1;j < 10; j++){
						if(i * j > 10)
							//continue outer;
							//break inner;
							//continue;
							break;
						System.out.println(i + "  " + j + "  "+i * j);							
					}
			}*/
		int b = 1000;
		while(true){
			if(b < 9)
				break;
			b = b - 9;
		}
		System.out.println("balance is " + b);
		
	}

}
