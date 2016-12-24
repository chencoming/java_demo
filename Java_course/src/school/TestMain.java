package school;

public class TestMain {
	public static void main(String[] args) {
		Student[] stuarray = new Student[5];
		int i = 0;
		for(; i < 3; i++){
			if(i == 2){
				stuarray[i + 2] = new Undergraduate();
				break;
			}
			stuarray[i] = new Student();
			stuarray[i + 2] = new Postgraduate();
		}
		for(i = 0; i < 3; i++){
			if(i == 2){
				((Undergraduate)stuarray[i + 2]).Print();
				break;
			}   
			stuarray[i].Print();
			((Postgraduate)stuarray[i + 2]).Print();			
		}
		Student stu = new Postgraduate();
		stu.Print();
		((Postgraduate)stu).Print();		
	}

}
