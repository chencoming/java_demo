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
	 * ˵�����õ��������ߵ�maxnum������
	 * */
	public ArrayList<Object>  getMostpopular(int maxnum){

		//�Ӽ�¼���г�ȡmaxnum����¼
		ArrayList<Object> objlist = sqlForPopular(tablePathrecord,maxnum);
		
		//����¼��Ϊ�ջ򲻹�maxnum����¼ʱ������Ŀ����ȡ���µ���Ŀ���ݲ���
		if(objlist.size() < 5){
			System.out.println("Ϊ�û��Ƽ�������ӵ���Ŀ.....");
			objlist.addAll(sqlForPopular(tableAllinone, maxnum-objlist.size()));
		}
		return objlist;		
	}
	
	
	/**
	 * ˵�����ڼ�¼�����ҵ����һ��ʱ���������5����Ŀ
	 * �����Ŀ�����ҳ������ӵ�5����Ŀ
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
	 * ˵����������ҵID��ȡ����ҵ�ķ��ʼ�¼
	 * */
	public ArrayList<Pathrecord> getPathrecordsByEnterpriseid(int enterpriseId,int limitMonths, boolean limitNums){
		
		ArrayList<Pathrecord>  pathrecords = new ArrayList<Pathrecord>();  
		//select * from pathrecord where enterpriseid =? and accesstime =����;
		String [] parameter1 = {pathrecord_Id,pathrecord_Enterpriseid,pathrecord_Targetname,pathrecord_Targeturl};
		String sqlString = sqlhelper.fillSelectParams(parameter1);
		sqlString = sqlhelper.fillTableParam(sqlString, tablePathrecord, false, null);
		String [] parameter2 = {pathrecord_Enterpriseid +"=" +enterpriseId, pathrecord_Accesstime+"> (CURDATE()- INTERVAL "+limitMonths+" MONTH)"};
		sqlString = sqlhelper.fillWhereParams(sqlString, parameter2);
		
		//�˴�����ֻȡ�����������¼
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
	 * ˵����������������µ�5����¼���Ӷ�����ʹ��CB��CF�㷨
	 * */
	@SuppressWarnings("rawtypes")
	public Map<String, ArrayList>  analysisPathrecord(ArrayList<Pathrecord> pList){

		//����CB�㷨������
		ArrayList<Project> projectsForCB = new ArrayList<Project>();
		
		//�õ�5�������¼�е���Ŀ��Ϣ
		ArrayList<Project> projectsofFive  = new ArrayList<Project>();
		for(Pathrecord ptemp:pList){
			Project project = getProjectByUrl(ptemp.getTargeturl());
			project.out();
			projectsofFive.add(project);
		}
		
		Map<String, Integer>  map = new HashMap<String, Integer>(); 
		

    	
    	String algorithmString="CF";
		/*
    	 * �˴��������¼�е���Ŀ��Ŀ¼��Ϊkeyֵ�ֱ����map��
    	 * map�е�ÿһ��key����Ӧ��ֵΪ�����ڸ�Ŀ¼�µ���Ŀ��
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
	 * ˵�����Ӽ�¼����ȡ����� limitMonths �����ڵķ��ʼ�¼����
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
	 * ˵��������URL��ȡ����Ŀ����Ϣ
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
	 * ˵��������Ŀ���и���ĳЩɸѡ����ȡ����Ӧ����Ŀ
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
		

		//����õ��Ľ����Ŀ����2����ȥ���ؼ��ֵ�ƥ�䣬ֻ����ͬһĿ¼
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
	 * ˵������ArrayList<Project>���������numrecom����Ŀ
	 * @param projects �ṩ��ArrayList����
	 * @param numrecom ����numrecom����Ŀ
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
     * �Ľ���32λFNV�㷨1
     * 
     * @param data �ַ���
     *            
     * @return intֵ
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
     * ˵�����Ӽ�¼���еõ����ʹ���ͬ��Ŀ�ġ����ѡ���ҵ����
     * @param pathrecord �ṩ�������¼
     * @param pList �����ѡ���ҵ����
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
     * ˵���������û���Эͬ�����㷨
     * �õ���ҵ���ѵķ��ʼ�¼�������Ƽ�
     * */
    public ArrayList<Pathrecord> getRecordOfFriends(ArrayList<Pathrecord> recordOfFriends , int numOfFriends){
    	ArrayList<Pathrecord> pathrecordList = new ArrayList<Pathrecord>();
    	Map<Integer, ArrayList<Pathrecord>>  map = new HashMap<Integer, ArrayList<Pathrecord>>(); 
    	
    	
    	/*
    	 * �˴������ѵ������¼����url��hashֵ�ֱ����map��
    	 * map�е�ÿһ��key����Ӧ��ֵΪ����ͬhash�������¼
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
    	
    	/*����map�е�ÿһ��ֵArraylist�������ArrayList���ȴ���basenum����ʾ
    	 * ���ܴ���basenum����ͬ��������ҵ
    	 *  �����Ƿ���ϡ���ͬurl����ͬ���ѡ���������
    	 *  */
    	
    	//����һ����ǣ����д���basenum�������Ѷ������ĳ��Ŀ���Ƽ�����Ŀ
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
            	ArrayList<Pathrecord> pList = map.get(index);//�õ�hash(url)��ֵͬ��list
            	
				//��ʱ���������"��ͬurl,��ͬ��ҵid���������¼	 
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
