import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JOptionPane;


public class TSP {

	public static int LENGTH = 52;
	//public static int POPSIZE = 500;
	
	public static double[][]CityDistance = new double[LENGTH][LENGTH];
	public static double Citypos[][]={//52座城市的坐标
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
	
	public static int populationsize ;                        //种群大小
	public static int MaxGeneration;                   //最大世代数
	public static double crossrate = 0.0;                  //交叉率
	public static double variationrate = 0.0;              //变异率
	
	
	
	
	
	
	/*对于每一代*/
	public static chromosome currentbest;                        //本世代最好的染色体个体
	
	/*对于n代迭代后*/
	public static chromosome bestchromosome;     //最佳染色体个体
	public static int bestgeneration;					  //最好染色体个体产生的代数
	public static double shortest  = 999999999;					  //最好染色体个体的适应值

	
	public static void main(String[] args)throws Exception {
		
		String input = JOptionPane.showInputDialog(null,"输入种群大小(50-500之间的偶数):");
		populationsize = Integer.parseInt(input);
		if((populationsize%2) != 0){
			populationsize++;
		}
		
		/*for(int i = 0; i < populationsize;i++){
			oldpop[i] = chromosome();    //种群数组
			newpop[i] = chromosome();    //新种群数组
		}*/
		chromosome []oldpop ;    //种群数组
		chromosome []newpop ;    //新种群数组
		oldpop = new chromosome[populationsize];    //种群数组
		newpop = new chromosome[populationsize] ;    //新种群数组

		input = JOptionPane.showInputDialog(null,"输入最大世代数(100-10000):");
		MaxGeneration = Integer.parseInt(input);
		if(MaxGeneration<100){
			MaxGeneration = 100;
		}
		else if(MaxGeneration >10000){
			MaxGeneration = 10000;
		}
		
		input = JOptionPane.showInputDialog(null,"输入交叉率(0.5-0.99):");
		crossrate = Double.parseDouble(input);
		if(crossrate<0.5){
			crossrate = 0.5;
		}
		else if(crossrate >0.99){
			crossrate = 0.99;
		}
		
		input = JOptionPane.showInputDialog(null,"输入变异率(0.001-0.1)：");
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
		writer.println("求解TSP问题之52个城市的最优路径");
		writer.println("\n******************************************************************************\n");
		
		for(int generation = 1; generation <= MaxGeneration; generation++){//执行遗传迭代
			
			writer.println("当前为第" + generation + "代");
			writer.println("本世代最好的染色体为:");
			for(int j = 0;j < LENGTH;j++){
				writer.print(currentbest.cityorder[j]+"—");
			}
			writer.println("\n总长度为"+(1 /currentbest.fitness));
			writer.println("\n******************************************************************************\n");
			//与史上最好的染色体比较
			if(        (1/currentbest.fitness) < shortest ){
				shortest = 1/currentbest.fitness;
				bestchromosome.cityorder = currentbest.cityorder.clone(); //当前最好的染色体个体 currentbest
				bestchromosome.fitness = currentbest.fitness;
				bestgeneration = generation;
			}
		}
		
		writer.close();
		System.out.println("over");
		
		
		// TODO Auto-generated method stub

	}

	

	



	private static void setdistancematrix() {//计算并设置城市间距离矩阵
		// TODO Auto-generated method stub
		int i,j;
		for(i = 0 ;i < LENGTH ; i++)
			for(j = 0; j < LENGTH ; j++)
				//求城市i,j之间的距离存放在distance[i][j]
			{	
				CityDistance[i][j]=Math.floor(Math.sqrt( Math.pow( Citypos[i][0]-Citypos[j][0] ,2) +
					Math.pow( Citypos[i][1]-Citypos[j][1] ,2) ));
			}
	}
	
	private static void initialoldpop(chromosome [] oldpop) {//初始化第一个种群中的所有染色体
		// TODO Auto-generated method stub
		int i,j,k,m;
		int []Temp  =new int [52];
		
		for (i=0;i<populationsize; i++)//对oldpop[i]染色体生成随机序列
		{
			
			for(j = 0;j <LENGTH; j++){	//得到一个数组a[]={52,52,52,52,......}
				Temp[j] = LENGTH;
				//System.out.print(Temp[j]);
			}
			//System.out.println();
			for(k = 0 ; k <LENGTH; k++ ){	//为52个城市找到随机下标
				Random random = new Random();
				int pos = random.nextInt(52);
				//System.out.println("pos:"+pos);
				while(Temp[pos] < LENGTH){//说明此下标已经赋值为某城市
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
				oldpop[i].cityorder[m]= a[m] ;    //种群数组
			}*/
			/*for(m = 0 ; m <LENGTH ; m++ ){
				System.out.print(Temp[m]+"\t") ;
			}
			System.out.println();
			System.out.print("长度为"+oldpop[i]);*/
			System.out.print("长度为"+oldpop[i].cityorder.length);
			oldpop[i].cityorder= Temp.clone();
		}
	}

	private static void calfitness(chromosome [] oldpop) {//计算种群中的所有染色体的适应性
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
			
			oldpop[i].fitness = 1/sumfitness;//每个染色体（路径）的适应度定义为路径长度（总开销）的倒数
			
			if(sumfitness <= maxsumfitness){
				currentbest.cityorder = oldpop[i].cityorder.clone(); //当前最好的染色体个体 currentbest
				currentbest.fitness = oldpop[i].fitness;
			}
		}
		
	}
}
class chromosome{
	public static int LENGTH = 52;

	public int cityorder[] =  new  int[LENGTH];   //定义染色体二进制表达形式,即城市的顺序
	public double fitness;            //染色体的适应值
	/*chromosome(){
		cityorder =  new  int[LENGTH];
	}*/
	
}
