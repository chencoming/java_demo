import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JOptionPane;


public class TSP {

	public static int LENGTH = 52;
	//public static int POPSIZE = 500;
	
	public static double[][]CityDistance = new double[LENGTH][LENGTH];
	public static double Citypos[][]={//52�����е�����
			{565.0 ,  575.0},
			{25.0 ,   185.0},
			{345.0 ,  750.0},
			{945.0 ,  685.0},
			{845.0 ,  655.0},
			{880.0 ,  660.0},
			{25.0 ,   230.0},
			{525.0 ,  1000.0},
			{580.0 ,  1175.0},
			{650.0 ,  1130.0},
			{1605.0 , 620.0},
			{1220.0 , 580.0}, 
			{1465.0 , 200.0},
			{1530.0 , 5.0},
			{845.0 ,  680.0},
			{725.0 ,  370.0},
			{145.0 ,  665.0},
			{415.0 ,  635.0},
			{510.0 ,  875.0},
			{560.0 ,  365.0},
			{300.0 ,  465.0},
			{520.0 ,  585.0},
			{480.0 ,  415.0},
			{835.0 ,  625.0},
			{975.0 ,  580.0},
			{1215.0 , 245.0},
			{1320.0 , 315.0},
			{1250.0 , 400.0},
			{660.0 ,  180.0},
			{410.0 ,  250.0},
			{420.0 ,  555.0},
			{575.0 ,  665.0},
			{1150.0 , 1160.0},
			{700.0 ,  580.0},
			{685.0 ,  595.0},
			{685.0 ,  610.0},
			{770.0 ,  610.0},
			{795.0 ,  645.0},
			{720.0 ,  635.0},
			{760.0 ,  650.0},
			{475.0 ,  960.0},
			{95.0 ,   260.0},
			{875.0 ,  920.0},
			{700.0 ,  500.0},
			{555.0 ,  815.0},
			{830.0 ,  485.0},
			{1170.0 , 65.0},
			{830.0 ,  610.0},
			{605.0 ,  625.0},
			{595.0 ,  360.0},
			{1340.0 , 725.0},
			{1740.0 , 245.0},
			};
	
	public static int populationsize ;                        //��Ⱥ��С
	public static int MaxGeneration;                   //���������
	public static double crossrate = 0.0;                  //������
	public static double variationrate = 0.0;              //������
	
	
	
	
	
	
	/*����ÿһ��*/
	public static chromosome currentbest;                        //��������õ�Ⱦɫ�����
	
	/*����n��������*/
	public static chromosome bestchromosome;     //���Ⱦɫ�����
	public static int bestgeneration;					  //���Ⱦɫ���������Ĵ���
	public static double shortest  = 999999999;					  //���Ⱦɫ��������Ӧֵ

	
	public static void main(String[] args)throws Exception {
		
		String input = JOptionPane.showInputDialog(null,"������Ⱥ��С(50-500֮���ż��):");
		populationsize = Integer.parseInt(input);
		if((populationsize%2) != 0){
			populationsize++;
		}
		
		/*for(int i = 0; i < populationsize;i++){
			oldpop[i] = chromosome();    //��Ⱥ����
			newpop[i] = chromosome();    //����Ⱥ����
		}*/
		chromosome []oldpop ;    //��Ⱥ����
		chromosome []newpop ;    //����Ⱥ����
		oldpop = new chromosome[populationsize];    //��Ⱥ����
		newpop = new chromosome[populationsize] ;    //����Ⱥ����

		input = JOptionPane.showInputDialog(null,"�������������(100-10000):");
		MaxGeneration = Integer.parseInt(input);
		if(MaxGeneration<100){
			MaxGeneration = 100;
		}
		else if(MaxGeneration >10000){
			MaxGeneration = 10000;
		}
		
		input = JOptionPane.showInputDialog(null,"���뽻����(0.5-0.99):");
		crossrate = Double.parseDouble(input);
		if(crossrate<0.5){
			crossrate = 0.5;
		}
		else if(crossrate >0.99){
			crossrate = 0.99;
		}
		
		input = JOptionPane.showInputDialog(null,"���������(0.001-0.1)��");
		variationrate = Double.parseDouble(input);
		if(variationrate<0.001){
			variationrate = 0.001;
		}
		else if(variationrate >0.1){
			variationrate = 0.1;
		}
		
		setdistancematrix();
		initialoldpop(oldpop);
		calfitness(oldpop);
				
		File file = new File("result.txt");
		if(file.exists()){
			System.out.println("File already exists");
		}		
		PrintWriter writer = new PrintWriter(file);
		writer.println("���TSP����֮52�����е�����·��");
		writer.println("\n******************************************************************************\n");
		
		for(int generation = 1; generation <= MaxGeneration; generation++){//ִ���Ŵ�����
			
			writer.println("��ǰΪ��" + generation + "��");
			writer.println("��������õ�Ⱦɫ��Ϊ:");
			for(int j = 0;j < LENGTH;j++){
				writer.print(currentbest.cityorder[j]+"��");
			}
			writer.println("\n�ܳ���Ϊ"+(1 /currentbest.fitness));
			writer.println("\n******************************************************************************\n");
			//��ʷ����õ�Ⱦɫ��Ƚ�
			if(        (1/currentbest.fitness) < shortest ){
				shortest = 1/currentbest.fitness;
				bestchromosome.cityorder = currentbest.cityorder.clone(); //��ǰ��õ�Ⱦɫ����� currentbest
				bestchromosome.fitness = currentbest.fitness;
				bestgeneration = generation;
			}
		}
		
		writer.close();
		System.out.println("over");
		
		
		// TODO Auto-generated method stub

	}

	

	



	private static void setdistancematrix() {//���㲢���ó��м�������
		// TODO Auto-generated method stub
		int i,j;
		for(i = 0 ;i < LENGTH ; i++)
			for(j = 0; j < LENGTH ; j++)
				//�����i,j֮��ľ�������distance[i][j]
			{	
				CityDistance[i][j]=Math.floor(Math.sqrt( Math.pow( Citypos[i][0]-Citypos[j][0] ,2) +
					Math.pow( Citypos[i][1]-Citypos[j][1] ,2) ));
			}
	}
	
	private static void initialoldpop(chromosome [] oldpop) {//��ʼ����һ����Ⱥ�е�����Ⱦɫ��
		// TODO Auto-generated method stub
		int i,j,k,m;
		int []Temp  =new int [52];
		
		for (i=0;i<populationsize; i++)//��oldpop[i]Ⱦɫ�������������
		{
			
			for(j = 0;j <LENGTH; j++){	//�õ�һ������a[]={52,52,52,52,......}
				Temp[j] = LENGTH;
				//System.out.print(Temp[j]);
			}
			//System.out.println();
			for(k = 0 ; k <LENGTH; k++ ){	//Ϊ52�������ҵ�����±�
				Random random = new Random();
				int pos = random.nextInt(52);
				//System.out.println("pos:"+pos);
				while(Temp[pos] < LENGTH){//˵�����±��Ѿ���ֵΪĳ����
					pos++;
					if(pos > 51) {
						pos = 0;
					}
				}
				Temp[pos] = k;
				//System.out.print(Temp[pos]);

			}
			//System.out.println();

			/*for(m = 0 ; m <LENGTH ; m++ ){
				oldpop[i].cityorder[m]= a[m] ;    //��Ⱥ����
			}*/
			/*for(m = 0 ; m <LENGTH ; m++ ){
				System.out.print(Temp[m]+"\t") ;
			}
			System.out.println();
			System.out.print("����Ϊ"+oldpop[i]);*/
			System.out.print("����Ϊ"+oldpop[i].cityorder.length);
			oldpop[i].cityorder= Temp.clone();
		}
	}

	private static void calfitness(chromosome [] oldpop) {//������Ⱥ�е�����Ⱦɫ�����Ӧ��
		// TODO Auto-generated method stub
		int i,j,k;
		double sumfitness = 0;
		double maxsumfitness = 0;
		for (i=0;i<populationsize; i++)
		{
			sumfitness = 0;
			for(j = 0,k =1 ;k < LENGTH; j++ , k++){
				sumfitness += CityDistance[     oldpop[i].cityorder[j]      ][       oldpop[i].cityorder[k]     ]; 
			}
			
			oldpop[i].fitness = 1/sumfitness;//ÿ��Ⱦɫ�壨·��������Ӧ�ȶ���Ϊ·�����ȣ��ܿ������ĵ���
			
			if(sumfitness <= maxsumfitness){
				currentbest.cityorder = oldpop[i].cityorder.clone(); //��ǰ��õ�Ⱦɫ����� currentbest
				currentbest.fitness = oldpop[i].fitness;
			}
		}
		
	}
}
class chromosome{
	public static int LENGTH = 52;

	public int cityorder[] =  new  int[LENGTH];   //����Ⱦɫ������Ʊ����ʽ,�����е�˳��
	public double fitness;            //Ⱦɫ�����Ӧֵ
	/*chromosome(){
		cityorder =  new  int[LENGTH];
	}*/
	
}
