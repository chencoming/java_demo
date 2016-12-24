package thefirstpart;
import javax.swing.JOptionPane;
public class P164_6_25 {
	public static void main(String[] args) {
		
		String Fline = JOptionPane.showInputDialog("Enter the line of first matrix:");
		int firstLine = Integer.parseInt(Fline);
		String Frow = JOptionPane.showInputDialog("Enter the row of first matrix:");
		int firstRow = Integer.parseInt(Frow);
				
		String Sline = JOptionPane.showInputDialog("Enter the line of second matrix:");
		int secondLine = Integer.parseInt(Sline);
		String Srow = JOptionPane.showInputDialog("Enter the row of second matrix:");
		int secondRow = Integer.parseInt(Srow);
		
		if(firstRow != secondLine){
			JOptionPane.showMessageDialog(null,"the row of first matrix and\n" + 
					" the line of second matrix\n"  + "  are of unequal sizes!");
			System.exit(0);
		}
		
		int [][] FirstArray = new int [firstLine][firstRow];
		int [][] SecondArray = new int [secondLine][secondRow];
		
		
		int i,j;
		for(i = 0; i < firstLine; i++){
			for(j = 0; j < firstRow; j++){
				String num = JOptionPane.showInputDialog("Enter FirstArray[" + i +
						"][" + j + "]:");
				FirstArray[i][j] = Integer.parseInt(num);
			}
		}
		
		for( i = 0; i < secondLine; i++){
			for( j = 0; j < secondRow; j++){
				String num = JOptionPane.showInputDialog("Enter SecondArray[" + i +
						"][" + j + "]:");
				SecondArray[i][j] = Integer.parseInt(num);
			}
		}
		int [][] ThirdArray = multiplyMatrix(FirstArray,SecondArray);
		String output = "";
		for(i = 0;i < firstLine;i++){
			for(j = 0;j < secondRow;j++){
				output += ThirdArray[i][j] + "     ";
			}
			output += "\n";
		}
		JOptionPane.showMessageDialog(null,"The result of FirstArray * SecondArray is\n" + output);
			
	}
	
	
	public static int[][] multiplyMatrix(int [][] a,int [][] b){
		int firstRow = a[0].length;
		int firstLine = a.length;
		int secondRow = b[0].length;
		
		int [][] ThirdArray = new int [firstLine][secondRow];
		
		int i,j;
		for(i = 0; i < firstLine; i++){
			for(j = 0; j < secondRow; j++){
				for(int k = 0;k < firstRow;k++)
					ThirdArray[i][j] += a[i][k] * b[k][j];				
			}
		}
		return ThirdArray;
	}

	
}


