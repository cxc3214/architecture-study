package net.imspring.study.mongo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * collean 开源做内存缓存 提高效率
 * @author cxc
 *
 */
public class Api {
	private FileKit filekit; 
	private final static String KEY_NAME = "_id";
	
	private Map<String,JSONArray> collections;
	
	public void createdb(String dbname){
		filekit = new FileKit(dbname);
	}
	/**
	 * 切换DB 放弃当前所有的collections
	 * @param dbname
	 */
	public void usedb(String dbname){
		filekit = new FileKit(dbname);
		collections = new HashMap<String,JSONArray>();				
	}
	
	private JSONArray getTable(String tableName){
		if(tableName == null) return null;
		JSONArray table = collections.get(tableName);
		if(table == null) {
			table =  new JSONArray();
			collections.put(tableName, table);
		}
		return table ;
	}
	/**
	 * 生成id
	 * @return
	 */
	private String uudid(){
		return UUID.randomUUID().toString();
	}
	
	//	return collect  json 转成map
	public JSONObject insert(String collectionName,String json){
		JSONArray table = getTable(collectionName);
		JSONObject obj  = JSON.parseObject(json);
		obj.put(KEY_NAME, uudid());
		table.add(obj);
		return obj ;
	}
	// id 号
	/**
	 * 根据iD 删除
	 * @param collectionName
	 * @param id
	 * @return 删除标志
	 */
	public boolean delete(String collectionName,String id){
		boolean flag =false ;
		JSONArray table = getTable(collectionName);
		for(Object obj  :  table.toArray()){
			JSONObject obj1 = (JSONObject)obj;
			if(obj1.getString(KEY_NAME).equals(id)){
				table.remove(obj);
				flag =true;
				break;
			}
		}		
		return flag;		
	}
	
	/**
	 * 更新
	 * @param collectName
	 * @param json
	 * @return 更新影响的行数
	 */
	public int update(String collectionName,String json){
		JSONArray table = getTable(collectionName);
		int count = 0;
		for(Object obj  :  table.toArray()){
			JSONObject obj1 = (JSONObject)obj;
			JSONObject obj2 = JSON.parseObject(json);
			if(equal(obj2,obj1)){
				obj1.putAll(objToMap(obj2));
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 数据存储到文件
	 * @param collectionName
	 */
	public void flushToDb(String collectionName){
		JSONArray table = getTable(collectionName);
		filekit.write(collectionName, table.toJSONString());
	}
	
	/**
	 * json 转 map
	 * @param obj
	 * @return
	 */
	private Map<String, Object> objToMap(JSONObject obj){
		Map<String ,Object> tmap = new HashMap<String ,Object>();
		for (Entry<String, Object> entry : obj.entrySet()) {
			tmap.put(entry.getKey(), entry.getValue());
		}
		return tmap;
	}
	
	/**
	 * 查询判断对象是否符合
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private boolean equal(JSONObject obj1 , JSONObject obj2){
		boolean flag = false;
		for (Entry<String, Object> entry : obj1.entrySet()) {
			Object obj = obj2.get(entry.getKey());
			if(obj.equals( entry.getValue())){
				flag=true;
				
			}else{
				flag=false;
				break;				
			}
		}
		return flag ;
	}
}
