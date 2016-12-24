package thesecondpart;
import javax.swing.JOptionPane;
public class P203_7_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fan fan1 = new Fan();
		Fan fan2 = new Fan();
		
		fan1.setSpeed(3);
		fan1.setRadius(10);
		fan1.setColor("yellow");
		fan1.setOn(true);
		
		fan2.setSpeed(2);
		fan2.setRadius(5);
		fan2.setColor("blue");
		
		JOptionPane.showMessageDialog(null, "This is a Fan !\n" + fan1.toString());

		JOptionPane.showMessageDialog(null, "This is a Fan !\n" + fan2.toString());
		
	}
	
	

}

class Fan{
	final int SLOW = 1,MEDIUM = 2, FAST = 3;
	int speed;
	boolean on;
	double radius;
	String color;
	
	Fan(){
		speed = SLOW;
		on = false;
		radius = 5.0;
		color = "blue";
	}
	
	void setSpeed(int s){
		this.speed = s;
	}
	
	void setOn(boolean o){
		this.on = o;
	}
	
	void setRadius(double r){
		this.radius = r;
	}
	
	void setColor(String str){
		this.color = str;
	}
	
	int getSpeed(){
		return this.speed;
	}
	
	boolean getOn(){
		return this.on;
	}
	
	double getRadius(){
		return this.radius;
	}
	
	String getColor(){
		return this.color;
	}
	
	public String toString(){
		String output;
		if(on){
			output = "Speed:   ";
			if(this.getSpeed() == SLOW)
				output += "Slow";
			if(this.getSpeed() == MEDIUM)
				output += "Medium";
			if(this.getSpeed()== FAST)
				output += "Fast";
				
			 output +="\ncolor:   " + this.getColor() 
			+ "\nradius:   " + this.getRadius();
			return output;
		}
		else{
			output = "fan is off !\n" + "color:   " + this.getColor() +
			"\nradius:   " + this.getRadius();
			return output;
		}
			
	}
}
