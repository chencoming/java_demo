package school;
import javax.swing.JOptionPane;

public class Postgraduate extends Student{
	private String Studydirection;
	
	public Postgraduate(){
		SetStudydirection();
	}
	public String GetStudydirection(){
		return Studydirection;
	}
	public void SetStudydirection(){
		this.Studydirection = JOptionPane.showInputDialog("Enter the Studydirection of the student:");	
	}
	public void Print(){
		JOptionPane.showMessageDialog(null, super.GetMessage() + "\nStudydirection:  " + Studydirection);
	}  
}
