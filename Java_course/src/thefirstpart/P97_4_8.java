package thefirstpart;
import javax.swing.JOptionPane;
public class P97_4_8 {
	public static void main(String[] args) {
		double score,high = 0;
		String highname = null;
		String inputnum = JOptionPane.showInputDialog("Enter the number of students:");
		int num = Integer.parseInt(inputnum);
		for(int i = 0;i < num;i++){
			String name = JOptionPane.showInputDialog("Enter the No." + (i + 1) +" student's name:");
			String inputscore = JOptionPane.showInputDialog("Enter the student's score:");
			score = Double.parseDouble(inputscore);
			if(score > high){
				high = score;
				highname = name;
			}
		}
		JOptionPane.showMessageDialog(null, "The highest score is " + high + "\nThe student's "
				+ "name is " + highname);
	}
}
