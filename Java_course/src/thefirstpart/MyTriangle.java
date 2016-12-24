package thefirstpart;
import javax.swing.JOptionPane;
public class MyTriangle {
	public static void main(String[] args) {
		String inputs1 = JOptionPane.showInputDialog("Enter the first side of Triangle:");
		double side1 = Double.parseDouble(inputs1);
		String inputs2 = JOptionPane.showInputDialog("Enter the second side of Triangle:");
		double side2 = Double.parseDouble(inputs2);
		String inputs3 = JOptionPane.showInputDialog("Enter the third side of Triangle:");
		double side3 = Double.parseDouble(inputs3);
		
		boolean choose = isValid(side1, side2, side3);
		if(!choose){
			JOptionPane.showMessageDialog(null, "Error input!");
			System.exit(0);
		}
		
		JOptionPane.showMessageDialog(null ,"The area of Triangle is" + area(side1, side2, side3));
	}

	public static boolean isValid(double side1,double side2,double side3){
		if( (side1 + side2 > side3) && (side2 + side3 > side1) && (side1 + side3 > side2) )
			return true;
		else
			return false;		
	}
	
	public static double area(double side1,double side2,double side3){
		double s = 0, area = 0;
		s = (side1 + side2 + side3) / 2;
		area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
		return area;
		
	}
}
