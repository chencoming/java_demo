package com.minq.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.minq.domain.Enterprise;
import com.minq.domain.Pathrecord;
import com.minq.domain.Project;


public class Myrecommendation {

	public static int num_recommendation = 5;	//需要推荐的项目数量
	public static int num_samecatalogy = 3;	//需要推荐的同一个目录下的项目数量
	public static int num_otheruser = 2;	//需要推荐的其他用户浏览记录中的项目数量
	
	
	//用户的类型：无访问记录和有访问记录
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
		//System.out.println("start：" + startTime);
		
		
		/*此处控制用户类型*/
		int token_UserType = token_userWithRecord;
		
		switch (token_UserType) {
		case 0:
			/*
			 * 游客或新用户
			 * */
			System.out.println("用户无浏览记录，将对其推荐热门商品......");
			utils.tablePathrecord = "pathrecord";
			setRecommProjects(utils.getMostpopular(num_recommendation), num_recommendation);
			break;
		case 1:
			/*
			 * 老用户
			 * */
			//1.首先根据用户ID拿到该用户最近3个月的五条浏览记录进行分析
			@SuppressWarnings("rawtypes")
			Map<String, ArrayList> resultMap = new HashMap<String, ArrayList>(); 
			resultMap = utils.analysisPathrecord(
					utils.getPathrecordsByEnterpriseid(enterprise.getId(),3,true));
			
				
			if(resultMap.containsKey("CB")){
				/*
				 * 当分析的结果有两条浏览记录。
				 * 此处使用CB算法
				 * */
				ArrayList<Project> projectsforCB  = new ArrayList<Project>();
				projectsforCB.addAll(resultMap.get("CB"));
				System.out.println("\n 使用CB算法对其进行推荐......\n");
				int closestRecord =1;
				System.out.println("算法输入的浏览记录为：" );
				for(Project project  : projectsforCB){
					int numRecom = 3;
					if(closestRecord ==1){
						numRecom=num_recommendation/2 + 1;
						closestRecord = 0;
						//System.out.println("CB第一次__" + numRecom);
					}else{
						numRecom=num_recommendation - myRecommProjects.size();
						//System.out.println("CB第二次__" + numRecom);
					}
					
					/*//得到浏览记录中对应的项目
					Project project = utils.getProjectByUrl(pathrecord.getTargeturl());*/
					
					
					project.out();
					//根据同一目录—>关键字     顺序条件进行查找类似记录
					ArrayList<Project> projectList =new ArrayList<Project>();
					if(null!=project.getCatalogy() || null!=project.getKeyword()){
						projectList = utils.getProjectsByConditions(project);
						
					}
					
					//从取出满足条件的结果中随机抽取numRecom条数据
					for(Project p: utils.getRandomProjects(projectList, numRecom)){
						myRecommProjects.add(p);
					}					
				}
				
				
				
				
			}else if(resultMap.containsKey("CF")){
				/*
				 * 当分析的结果只有一条浏览记录。
				 * 此处先使用CF算法推荐项目
				 */
				System.out.println("\n 使用CF算法对其进行推荐......\n ");
				//取出该条记录
				ArrayList<Pathrecord> pathrecordsforCF  = new ArrayList<Pathrecord>();
				pathrecordsforCF.addAll(resultMap.get("CF"));
				System.out.println("算法输入的浏览记录为：" );
				/*Pathrecord pathrecord = pathrecordsforCF.get(0);
				pathrecord .out();*/
				System.out.println(pathrecordsforCF.size());
				
				//取出近三个月的浏览记录
				ArrayList<Pathrecord> pathrecordOfTime = utils.getPathrecordsByMonths(3);
				
				for(Pathrecord pathrecord:pathrecordsforCF){
					pathrecord.out();
					
					//从最近三个月的浏览记录中取出有相同url的”朋友“企业
					ArrayList<Enterprise> enterpriseList = utils.getEnterprisesIdBySameUrl(pathrecord, pathrecordOfTime);
					
					if(enterpriseList.size()!=0){
						//得到这些”朋友“企业的最近三个月内其他所有浏览记录
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
				
				
				
				
				
				
				
				//其次根据该浏览记录推荐相同目录的项目
				
				int sameCatalogyRecomNum = num_recommendation - myRecommProjects.size() ;
				
				
				if(sameCatalogyRecomNum>0){
					System.out.println("CB 算法进行支援！" + sameCatalogyRecomNum +"个");
					Pathrecord mostRecendPathrecord = pathrecordsforCF.get(0);
					
					Project project = utils.getProjectByUrl(mostRecendPathrecord.getTargeturl());
					//project.out();
					
					//根据同一目录—>关键字     顺序条件进行查找类似记录
					ArrayList<Project> projectList =new ArrayList<Project>();
					if(null!=project.getCatalogy() || null!=project.getKeyword()){
						projectList = utils.getProjectsByConditions(project);
					}
					
					//从取出满足条件的结果中随机抽取sameCatalogyRecomNum条数据
					for(Project p: utils.getRandomProjects(projectList, sameCatalogyRecomNum)){
						myRecommProjects.add(p);
					}
				
				}
				
				
				
			}else{
				System.out.println("无记录");
			}

			break;
		default:
			System.out.println("no such records!");
			break;
		}
		long stopTime = System.currentTimeMillis();
		//System.out.println("stop：" + stopTime );
		long sumTime = (stopTime - startTime);
		System.out.println("\n 系统响应时间为：" +sumTime + "ms\n ");
		show();
	}
	


	public static void setRecommProjects(ArrayList<Object> objlist  ,int maxnum){
		//此处判断有多少个项目可以返回
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
		System.out.println("*\n***********************推荐结果************************\n*");
		for(Project p : myRecommProjects){
			System.out.println("Title：" +p.getTitle()  +  "\n");    //Url：" + p.getUrl()
		}
	}

}
