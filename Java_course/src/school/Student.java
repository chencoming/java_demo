package school;
import javax.swing.JOptionPane;

public class Student {
	private String Name;
	private int Age;
	private long Idnumber;
	private String Sex;
	private int Grade;
	
	public Student(){
		SetName();
		SetAge();
		SetIdnumber();
		SetSex();
		SetGrade();
	}
	
	public String GetName(){
		return Name;		
	}
	public void SetName(){
		this.Name = JOptionPane.showInputDialog("Enter the name of the student:");		
	}
	public int GetAge(){
		return Age;
	}
	public void SetAge(){
		String AgeString = JOptionPane.showInputDialog("Enter the age of the student:");
		this.Age = Integer.parseInt(AgeString);
	}
	public long GetIdnumber(){
		return Idnumber;
	}
	public void SetIdnumber(){
		String IDString = JOptionPane.showInputDialog("Enter the idnumber of the student:");
		this.Idnumber = Long.parseLong(IDString);
	}
	public String GetSex(){
		return Sex;
	}
	public void SetSex(){
		this.Sex = JOptionPane.showInputDialog("Enter the sex of the student(man or woman):");
	}
	public int GetSGrade(){
		return Grade;
	}
	public void SetGrade(){
		String GradeString = JOptionPane.showInputDialog("Enter the grade of the student:");
		this.Grade = Integer.parseInt(GradeString);
	}
	public String GetMessage(){
		String output = "Name:  " + this.Name + "\nAge:  " + this.Age + "\nIdnumber:  " + this.Idnumber 
		+ "\nSex:  " + this.Sex + "\nGrade:  " + this.Grade; 
		return output;
	}
	public void Print(){
		JOptionPane.showMessageDialog(null, GetMessage());
	}
}
