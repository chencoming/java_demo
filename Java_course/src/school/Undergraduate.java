package school;
import javax.swing.JOptionPane;

public class Undergraduate extends Student{
	private String Specialty;
	
	public Undergraduate(){
		SetSpecialty();
	}
	public String GetSpecialty(){ 
		return Specialty;
	}
	public void SetSpecialty(){
		this.Specialty = JOptionPane.showInputDialog("Enter the specialty of the student:");	
	}
	public void Print(){
		JOptionPane.showMessageDialog(null, super.GetMessage() + "\nSpecialty:  " + Specialty);
	}
}
