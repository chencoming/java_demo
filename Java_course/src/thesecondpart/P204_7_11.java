package thesecondpart;
import javax.swing.JOptionPane;
public class P204_7_11 {

	public static void main(String[] args) {

		Mypoint p1 = new Mypoint();
		Mypoint p2 = new Mypoint(10,30.5);
		
		JOptionPane.showMessageDialog(null, "The distance of (" + p1.getx() + "," +
				p1.gety() + ") and (" + p2.getx() + "," + p2.gety() + ") \nis  " +
				(int)(Mypoint.distance(p1,p2) * 100) / 100.0);		
	}

}

class Mypoint{
	double x,y;
	
	Mypoint(){
		this.x = 0;
		this.y = 0;
	}
	
	Mypoint(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	double getx(){
		return this.x;
	}
	
	double gety(){
		return this.y;
	}
	
	public static double distance(Mypoint p1,Mypoint p2){
		return Math.sqrt(  Math.pow(( p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2) );
	}
	
	public double distance(Mypoint p){
		return Math.sqrt(  Math.pow((this.x - p.x),2) + Math.pow((this.y - p.y),2) );
	}
}
