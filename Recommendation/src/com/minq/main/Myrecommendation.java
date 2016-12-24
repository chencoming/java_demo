package com.minq.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.minq.domain.Enterprise;
import com.minq.domain.Pathrecord;
import com.minq.domain.Project;


public class Myrecommendation {

	public static int num_recommendation = 5;	//��Ҫ�Ƽ�����Ŀ����
	public static int num_samecatalogy = 3;	//��Ҫ�Ƽ���ͬһ��Ŀ¼�µ���Ŀ����
	public static int num_otheruser = 2;	//��Ҫ�Ƽ��������û������¼�е���Ŀ����
	
	
	//�û������ͣ��޷��ʼ�¼���з��ʼ�¼
	public static int token_userWithoutRecord = 0;
	public static int token_userWithRecord= 1;
	
	public static Utils utils = new Utils();
	
	public static ArrayList<Project> myRecommProjects = new ArrayList<Project>();
	
	/**
	 * @param args 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Enterprise enterprise = new Enterprise();
		enterprise.setId(1487);
		long startTime = System.currentTimeMillis();
		//System.out.println("start��" + startTime);
		
		
		/*�˴������û�����*/
		int token_UserType = token_userWithRecord;
		
		switch (token_UserType) {
		case 0:
			/*
			 * �οͻ����û�
			 * */
			System.out.println("�û��������¼���������Ƽ�������Ʒ......");
			utils.tablePathrecord = "pathrecord";
			setRecommProjects(utils.getMostpopular(num_recommendation), num_recommendation);
			break;
		case 1:
			/*
			 * ���û�
			 * */
			//1.���ȸ����û�ID�õ����û����3���µ����������¼���з���
			@SuppressWarnings("rawtypes")
			Map<String, ArrayList> resultMap = new HashMap<String, ArrayList>(); 
			resultMap = utils.analysisPathrecord(
					utils.getPathrecordsByEnterpriseid(enterprise.getId(),3,true));
			
				
			if(resultMap.containsKey("CB")){
				/*
				 * �������Ľ�������������¼��
				 * �˴�ʹ��CB�㷨
				 * */
				ArrayList<Project> projectsforCB  = new ArrayList<Project>();
				projectsforCB.addAll(resultMap.get("CB"));
				System.out.println("\n ʹ��CB�㷨��������Ƽ�......\n");
				int closestRecord =1;
				System.out.println("�㷨����������¼Ϊ��" );
				for(Project project  : projectsforCB){
					int numRecom = 3;
					if(closestRecord ==1){
						numRecom=num_recommendation/2 + 1;
						closestRecord = 0;
						//System.out.println("CB��һ��__" + numRecom);
					}else{
						numRecom=num_recommendation - myRecommProjects.size();
						//System.out.println("CB�ڶ���__" + numRecom);
					}
					
					/*//�õ������¼�ж�Ӧ����Ŀ
					Project project = utils.getProjectByUrl(pathrecord.getTargeturl());*/
					
					
					project.out();
					//����ͬһĿ¼��>�ؼ���     ˳���������в������Ƽ�¼
					ArrayList<Project> projectList =new ArrayList<Project>();
					if(null!=project.getCatalogy() || null!=project.getKeyword()){
						projectList = utils.getProjectsByConditions(project);
						
					}
					
					//��ȡ�����������Ľ���������ȡnumRecom������
					for(Project p: utils.getRandomProjects(projectList, numRecom)){
						myRecommProjects.add(p);
					}					
				}
				
				
				
				
			}else if(resultMap.containsKey("CF")){
				/*
				 * �������Ľ��ֻ��һ�������¼��
				 * �˴���ʹ��CF�㷨�Ƽ���Ŀ
				 */
				System.out.println("\n ʹ��CF�㷨��������Ƽ�......\n ");
				//ȡ��������¼
				ArrayList<Pathrecord> pathrecordsforCF  = new ArrayList<Pathrecord>();
				pathrecordsforCF.addAll(resultMap.get("CF"));
				System.out.println("�㷨����������¼Ϊ��" );
				/*Pathrecord pathrecord = pathrecordsforCF.get(0);
				pathrecord .out();*/
				System.out.println(pathrecordsforCF.size());
				
				//ȡ���������µ������¼
				ArrayList<Pathrecord> pathrecordOfTime = utils.getPathrecordsByMonths(3);
				
				for(Pathrecord pathrecord:pathrecordsforCF){
					pathrecord.out();
					
					//����������µ������¼��ȡ������ͬurl�ġ����ѡ���ҵ
					ArrayList<Enterprise> enterpriseList = utils.getEnterprisesIdBySameUrl(pathrecord, pathrecordOfTime);
					
					if(enterpriseList.size()!=0){
						//�õ���Щ�����ѡ���ҵ��������������������������¼
						ArrayList<Pathrecord> pathrecordOfFriends = new ArrayList<Pathrecord>();
						for(Enterprise e:enterpriseList){
							pathrecordOfFriends.addAll(utils.getPathrecordsByEnterpriseid(e.getId(),3,false));
						}
						
						for(Pathrecord p: utils.getRecordOfFriends(pathrecordOfFriends, enterpriseList.size())){
							if(!p.getTargeturl().equals(pathrecord.getTargeturl())){
								Project pro = new Project();
								pro.setTitle(p.getTargetname());
								pro.setUrl(p.getTargeturl());
								myRecommProjects.add(pro);
							}
						}
					}
				}
				
				
				
				
				
				
				
				//��θ��ݸ������¼�Ƽ���ͬĿ¼����Ŀ
				
				int sameCatalogyRecomNum = num_recommendation - myRecommProjects.size() ;
				
				
				if(sameCatalogyRecomNum>0){
					System.out.println("CB �㷨����֧Ԯ��" + sameCatalogyRecomNum +"��");
					Pathrecord mostRecendPathrecord = pathrecordsforCF.get(0);
					
					Project project = utils.getProjectByUrl(mostRecendPathrecord.getTargeturl());
					//project.out();
					
					//����ͬһĿ¼��>�ؼ���     ˳���������в������Ƽ�¼
					ArrayList<Project> projectList =new ArrayList<Project>();
					if(null!=project.getCatalogy() || null!=project.getKeyword()){
						projectList = utils.getProjectsByConditions(project);
					}
					
					//��ȡ�����������Ľ���������ȡsameCatalogyRecomNum������
					for(Project p: utils.getRandomProjects(projectList, sameCatalogyRecomNum)){
						myRecommProjects.add(p);
					}
				
				}
				
				
				
			}else{
				System.out.println("�޼�¼");
			}

			break;
		default:
			System.out.println("no such records!");
			break;
		}
		long stopTime = System.currentTimeMillis();
		//System.out.println("stop��" + stopTime );
		long sumTime = (stopTime - startTime);
		System.out.println("\n ϵͳ��Ӧʱ��Ϊ��" +sumTime + "ms\n ");
		show();
	}
	


	public static void setRecommProjects(ArrayList<Object> objlist  ,int maxnum){
		//�˴��ж��ж��ٸ���Ŀ���Է���
		int numRecomen = objlist.size() >maxnum?maxnum:objlist.size();
		
		for(int i =0; i <numRecomen;i++){
			Object objectarray[] = (Object []) objlist.get(i);
			Project project = new Project();
			if(objectarray.length == 2){
				project.setTitle(objectarray[0].toString());
				project.setUrl( objectarray[1].toString());
			}else if(objectarray.length == 3){
				project.setTitle(objectarray[1].toString());
				project.setUrl( objectarray[2].toString());
			}
			
			//System.out.println("\ntitle :"+project.getTitle()+" \nurl: " + project.getUrl());
			myRecommProjects.add(project);
			
			
		}
	}
	
	
	public static void show(){
		System.out.println("*\n***********************�Ƽ����************************\n*");
		for(Project p : myRecommProjects){
			System.out.println("Title��" +p.getTitle()  +  "\n");    //Url��" + p.getUrl()
		}
	}

}
