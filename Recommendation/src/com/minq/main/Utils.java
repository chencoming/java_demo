package com.minq.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.minq.domain.Enterprise;
import com.minq.domain.Pathrecord;
import com.minq.domain.Project;

public class Utils {
	
	public static String tableAllinone = "allinone";
	public static String tablePathrecord = "pathrecord";
	public static String tableEnterprise = "enterprise";
	
	public static String allinone_Id = "id";
	public static String allinone_Title = "title";
	public static String allinone_Url = "url";
	public static String allinone_Source = "source";
	public static String allinone_Ptime = "ptime";
	public static String allinone_Catalogy = "catalogy";
	public static String allinone_Keyword = "keyword";
	public static String allinone_useful = "useful";
	public static String allinone_ifpublish = "ifpublish";
	public static String allinone_isnew = "isnew";
	public static String allinone_iswrong = "iswrong";
	
	public static String allinone_usefulDataCondition = allinone_useful + " =1 and " 
																							+ allinone_ifpublish + " =1 and "
																							+ allinone_isnew + " =1 and "
																							+ allinone_iswrong + "=0 "; 
	
	public static String pathrecord_Id = "id";
	public static String pathrecord_Enterpriseid = "enterpriseid";
	public static String pathrecord_Targetname = "targetname";
	public static String pathrecord_Targeturl = "targeturl";
	public static String pathrecord_Accesstime = "accesstime";
	
	
	public static Sqlhelper sqlhelper = new Sqlhelper();

	/**
	 * 说明：得到点击率最高的maxnum条数据
	 * */
	public ArrayList<Object>  getMostpopular(int maxnum){

		//从记录表中抽取maxnum条记录
		ArrayList<Object> objlist = sqlForPopular(tablePathrecord,maxnum);
		
		//当记录表为空或不够maxnum条记录时，从项目表中取最新的项目数据补充
		if(objlist.size() < 5){
			System.out.println("为用户推荐最新添加的项目.....");
			objlist.addAll(sqlForPopular(tableAllinone, maxnum-objlist.size()));
		}
		return objlist;		
	}
	
	
	/**
	 * 说明：在记录表中找到最近一段时间访问最多的5个项目
	 * 或从项目表中找出最近添加的5个项目
	 * */
	public ArrayList<Object> sqlForPopular(String tableNameString , int Maxnum ){ 
		//select title,url  from allinone order by ptime desc limit 0,5; 
		//select Distinct targetname from (select * from pathrecord order by accesstime desc limit 0,50) u limit 0 ,5

		String sql = null;
		ArrayList<Object> objList = new ArrayList<Object>();
		if(tablePathrecord.equals(tableNameString)  ){
			/*
			 * sql = "select count(*) 'num',targetname,targeturl  from 	(select * from pathrecord where accesstime > (CURDATE()- INTERVAL 3 MONTH)) p1 " 
			 * + "group by targetname order by num desc limit 0 ,"  + Maxnum +";";
			 * */
			
			String subSqlString =sqlhelper.fillSelectParams(null);
			subSqlString = sqlhelper.fillTableParam(subSqlString , tableNameString , false, null);
			String []parameters ={pathrecord_Accesstime +"> (CURDATE()- INTERVAL 3 MONTH)"}; 
			subSqlString = sqlhelper.fillWhereParams(subSqlString, parameters);
			
			
			String []parameters2 ={"count(*) 'num'",pathrecord_Targetname,pathrecord_Targeturl}; 
			sql = sqlhelper.fillSelectParams(parameters2);
			sql =sqlhelper.fillTableParam(sql , subSqlString ,true, "p1") ;
			sql = sqlhelper.fillOtherParams(sql, pathrecord_Targetname, "num desc", Maxnum);
		
		}else if(tableAllinone.equals(tableNameString)  ){	
			/*
			 * sql = "select title , url  from allinone order by ptime desc limit 0,"+Maxnum+";" ;
			 */			
			String []parameters = {allinone_Title,allinone_Url};
			sql = sqlhelper.fillSelectParams(parameters);
			sql =sqlhelper.fillTableParam(sql , tableNameString ,false, null) ;
			String [] parameters2={allinone_usefulDataCondition};
			sql = sqlhelper.fillWhereParams(sql, parameters2);
			sql = sqlhelper.fillOtherParams(sql , null , allinone_Ptime+" desc" , Maxnum);
		}

		if(null !=sql && !"".equals(sql)){
			objList = sqlhelper.executeQuery(sql);
		}
		return objList;
	}

	/**
	 * 说明：根据企业ID获取该企业的访问记录
	 * */
	public ArrayList<Pathrecord> getPathrecordsByEnterpriseid(int enterpriseId,int limitMonths, boolean limitNums){
		
		ArrayList<Pathrecord>  pathrecords = new ArrayList<Pathrecord>();  
		//select * from pathrecord where enterpriseid =? and accesstime =半年;
		String [] parameter1 = {pathrecord_Id,pathrecord_Enterpriseid,pathrecord_Targetname,pathrecord_Targeturl};
		String sqlString = sqlhelper.fillSelectParams(parameter1);
		sqlString = sqlhelper.fillTableParam(sqlString, tablePathrecord, false, null);
		String [] parameter2 = {pathrecord_Enterpriseid +"=" +enterpriseId, pathrecord_Accesstime+"> (CURDATE()- INTERVAL "+limitMonths+" MONTH)"};
		sqlString = sqlhelper.fillWhereParams(sqlString, parameter2);
		
		//此处限制只取最近的五条记录
		if(limitNums){
			sqlString = sqlhelper.fillOtherParams(sqlString, null, pathrecord_Accesstime + " desc", 5);
		}
		
		
		ArrayList<Object> objList  = new ArrayList<Object>();
		if(null !=sqlString && !"".equals(sqlString)){
			objList = sqlhelper.executeQuery(sqlString);
		}
		
		for(int i =0; i <objList.size();i++){
			Object objectarray[] = (Object []) objList.get(i);
			Pathrecord pathrecord = new Pathrecord();
			
			pathrecord.setId(Integer.parseInt(objectarray[0].toString()));
			pathrecord.setEnterpriseid(Integer.parseInt(objectarray[1].toString()));
			pathrecord.setTargetname(objectarray[2].toString());
			pathrecord.setTargeturl(objectarray[3].toString());
			
			pathrecords.add(pathrecord);
		}
		
		return pathrecords;
	}
	
	/**
	 * 说明：分析最近三个月的5条记录，从而决定使用CB或CF算法
	 * */
	@SuppressWarnings("rawtypes")
	public Map<String, ArrayList>  analysisPathrecord(ArrayList<Pathrecord> pList){

		//用于CB算法的输入
		ArrayList<Project> projectsForCB = new ArrayList<Project>();
		
		//拿到5条浏览记录中的项目信息
		ArrayList<Project> projectsofFive  = new ArrayList<Project>();
		for(Pathrecord ptemp:pList){
			Project project = getProjectByUrl(ptemp.getTargeturl());
			project.out();
			projectsofFive.add(project);
		}
		
		Map<String, Integer>  map = new HashMap<String, Integer>(); 
		

    	
    	String algorithmString="CF";
		/*
    	 * 此处把浏览记录中的项目的目录作为key值分别放入map中
    	 * map中的每一个key所对应的值为该属于该目录下的项目数
    	 */    	
    	for(Project ptemp:projectsofFive){
    		String catalogyString = ptemp.getCatalogy();
    		if(!map.containsKey(catalogyString)){
    			map.put(catalogyString, 1);
    		}else{
    			int num = map.get(catalogyString) + 1;
    			if(num>2){
    				algorithmString="CB";
    				projectsForCB.add(projectsofFive.get(0));
    				projectsForCB.add(ptemp);			
    				break;
    			}
    			map.put(catalogyString, num);			
    		}		
    	}

    	
    	/*Set<String> key = map.keySet();
    	Iterator<String> it = key.iterator();
        while(it.hasNext()) {
        	String index = (String) it.next();
        	int num = map.get(index);
        	if(num >2){
        		algorithmString="CB";
        		break;
        	}
        }*/
		
    	Map<String, ArrayList>  resultMap = new HashMap<String, ArrayList>(); 
    	
        
        if("CB".equals(algorithmString)){        	
        	resultMap.put(algorithmString, projectsForCB);
        }else if("CF".equals(algorithmString)){	
        	resultMap.put(algorithmString, pList);
        	
        }   
        return resultMap;
	}
	
	
	
	/**
	 * 说明：从记录表中取出最近 limitMonths 个月内的访问记录集合
	 * */
	public ArrayList<Pathrecord> getPathrecordsByMonths(int limitMonths){
		ArrayList<Pathrecord> pathrecordList = new ArrayList<Pathrecord>();
		
		String [] parameters1 ={pathrecord_Enterpriseid,pathrecord_Targeturl};
		String sqlString = sqlhelper.fillSelectParams(parameters1);
		sqlString = sqlhelper.fillTableParam(sqlString, tablePathrecord, false, null);
		String [] parameters2 = {pathrecord_Accesstime+"> (CURDATE()- INTERVAL "+limitMonths+" MONTH)"};
		sqlString =sqlhelper.fillWhereParams(sqlString, parameters2);
		
		ArrayList<Object> objList  = new ArrayList<Object>();
		if(null !=sqlString && !"".equals(sqlString)){
			objList = sqlhelper.executeQuery(sqlString);
		}
		
		for(int i =0; i <objList.size();i++){
			Object objectarray[] = (Object []) objList.get(i);
			Pathrecord pathrecord = new Pathrecord();

			pathrecord.setEnterpriseid(Integer.parseInt(objectarray[0].toString()));
			pathrecord.setTargeturl(objectarray[1].toString());

			pathrecordList.add(pathrecord);
		}

		return pathrecordList;
	}
	
	/**
	 * 说明：根据URL获取该项目的信息
	 * */
	public Project getProjectByUrl(String targeturl){
		/*
		 * select id,title from allinone where url ='targeturl';
		 **/
		String [] parameter1 = {allinone_Id,allinone_Catalogy,allinone_Keyword};	
		String sqlString = sqlhelper.fillSelectParams(parameter1);
		sqlString = sqlhelper.fillTableParam(sqlString, tableAllinone, false, null);
		String [] parameter2={"url='"+targeturl+"'"};
		sqlString = sqlhelper.fillWhereParams(sqlString, parameter2);
		
		ArrayList<Object> objList  = new ArrayList<Object>();
		if(null !=sqlString && !"".equals(sqlString)){
			objList = sqlhelper.executeQuery(sqlString);
		}

		Project  project= new Project();  
		for(int i =0; i <objList.size();i++){
			Object objectarray[] = (Object []) objList.get(i);			
			project.setId(Integer.parseInt(objectarray[0].toString()));
			project.setCatalogy(objectarray[1].toString());
			project.setKeyword(objectarray[2].toString());
		}
		return project;
	}

	/**
	 * 说明：从项目表中根据某些筛选条件取出对应的项目
	 * */
	public ArrayList<Project> getProjectsByConditions(Project p){
		
		ArrayList<Project> projectList = new ArrayList<Project>();
		ArrayList<Object> objList  = new ArrayList<Object>();
		
		
		String [] parameters1 ={"id","title","url"};
		String sqlString = sqlhelper.fillSelectParams(parameters1);
		sqlString = sqlhelper.fillTableParam(sqlString, tableAllinone, false, null);
		
		if(null!=p.getCatalogy()&&null!=p.getKeyword()){
			String []parameters2 ={"catalogy='" + p.getCatalogy() + "'", "keyword='" + p.getKeyword() +"'" , allinone_usefulDataCondition, "id<>" + p.getId()};
			sqlString = sqlhelper.fillWhereParams(sqlString, parameters2);
			
			
			if(null !=sqlString && !"".equals(sqlString)){
				objList = sqlhelper.executeQuery(sqlString);
			}
		}
		

		//如果得到的结果数目少于2，则去掉关键字的匹配，只保留同一目录
		if(objList.size()<2 && null!= p.getCatalogy()){
			sqlString=null;
			sqlString = sqlhelper.fillSelectParams(parameters1);
			sqlString = sqlhelper.fillTableParam(sqlString, tableAllinone, false, null);
			String []parameters3 ={"catalogy='" + p.getCatalogy() + "'", allinone_usefulDataCondition, "id<>" + p.getId()};
			sqlString = sqlhelper.fillWhereParams(sqlString, parameters3);
			if(null !=sqlString && !"".equals(sqlString)){
				objList = sqlhelper.executeQuery(sqlString);
			}
		}
		
		for(int i =0; i <objList.size();i++){
			Object objectarray[] = (Object []) objList.get(i);		
			Project project = new Project();
			project.setId(Integer.parseInt(objectarray[0].toString()));
			project.setTitle(objectarray[1].toString());
			project.setUrl(objectarray[2].toString());
			
			projectList.add(project);
		}
	
		return projectList;
	}
	
	/**
	 * 说明：从ArrayList<Project>中随机返回numrecom个项目
	 * @param projects 提供的ArrayList参数
	 * @param numrecom 返回numrecom个项目
	 *  */
	public ArrayList<Project> getRandomProjects(ArrayList<Project> projects , int numrecom){
		
		ArrayList<Project> projectList = new ArrayList<Project>();
		
		if(numrecom==projects.size() || numrecom>projects.size()){
			return projects;
		}
		
		int numOfProjectlist = projects.size();
		Random random = new Random();
		int []indexarray = new int[numrecom]; 
		indexarray[0]= random.nextInt(numOfProjectlist);
		for(int i = 1;i<indexarray.length;i++){
			indexarray[i]= (indexarray[i-1]+1)%numOfProjectlist;
		}
		for(int index:indexarray){
			projectList.add(projects.get(index));
		}
		return projectList;
	}
	
	
	 /**
     * 改进的32位FNV算法1
     * 
     * @param data 字符串
     *            
     * @return int值
     */
    public int FNVHash(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++)
            hash = (hash ^ data.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return hash;
    }

    /**
     * 说明：从记录表中得到访问过相同项目的“朋友”企业集合
     * @param pathrecord 提供的浏览记录
     * @param pList “朋友”企业集合
     * */
    public ArrayList<Enterprise> getEnterprisesIdBySameUrl(Pathrecord pathrecord, ArrayList<Pathrecord> pList){
    	ArrayList<Enterprise> enterpriseList = new ArrayList<Enterprise>();
    	int targetUrlHash = FNVHash(pathrecord.getTargeturl());
    	
    	for(Pathrecord p:pList){
    		if(FNVHash(p.getTargeturl())==targetUrlHash){
    			if(pathrecord.getEnterpriseid()!=p.getEnterpriseid()){
					Enterprise enterprise = new Enterprise();
					enterprise.setId(p.getEnterpriseid());
					enterpriseList.add(enterprise);
    			}
    		}
    	}
    	
    	return enterpriseList;
    }
    
    
    
    /**
     * 说明：基于用户的协同过滤算法
     * 得到企业朋友的访问记录，用于推荐
     * */
    public ArrayList<Pathrecord> getRecordOfFriends(ArrayList<Pathrecord> recordOfFriends , int numOfFriends){
    	ArrayList<Pathrecord> pathrecordList = new ArrayList<Pathrecord>();
    	Map<Integer, ArrayList<Pathrecord>>  map = new HashMap<Integer, ArrayList<Pathrecord>>(); 
    	
    	
    	/*
    	 * 此处把朋友的浏览记录按照url的hash值分别放入map中
    	 * map中的每一个key所对应的值为有相同hash的浏览记录
    	 */    	
    	for(Pathrecord p:recordOfFriends){
    		int hashvalue = FNVHash(p.getTargeturl());
    		if(!map.containsKey(hashvalue)){
    			ArrayList<Pathrecord> pathrecordSubList = new ArrayList<Pathrecord>();
    			pathrecordSubList.add(p);
    			map.put(hashvalue, pathrecordSubList);
    		}else{
    			map.get(hashvalue).add(p);
    		}		
    	}
    	
    	/*遍历map中的每一个值Arraylist，如果该ArrayList长度大于basenum，表示
    	 * 可能存在basenum个不同的朋友企业
    	 *  则检查是否符合“相同url，不同朋友”的条件。
    	 *  */
    	
    	//定义一个标记，当有大于basenum个的朋友都浏览过某项目才推荐该项目
    	int basenum = 0;
    	if(numOfFriends!=0){
    		basenum = numOfFriends/2 + 1;
    	}
    	
    	Set<Integer> key = map.keySet();
    	Iterator<Integer> it = key.iterator();
        while(it.hasNext()) {
        	Integer index = (Integer) it.next();
        	int listsize = map.get(index).size();

            if(listsize>=basenum){
            	ArrayList<Pathrecord> pList = map.get(index);//得到hash(url)相同值的list
            	
				//临时变量，存放"相同url,不同企业id“的浏览记录	 
            	ArrayList<Pathrecord> tempList = new ArrayList<Pathrecord>();            	
            	tempList.add(pList.get(0));
            	
            	for(int i =1 ;i<pList.size();i++){
            		for(int j=0;j<tempList.size();j++){
            			if(pList.get(i).getEnterpriseid()!=tempList.get(j).getEnterpriseid()){
            				tempList.add(pList.get(i));
            			}
            		}
            	} 
            	if(tempList.size()>=basenum){
            		pathrecordList.add(tempList.get(0));
            	}
            }
        }	
    	
    	return pathrecordList;
    }

    
    
}
