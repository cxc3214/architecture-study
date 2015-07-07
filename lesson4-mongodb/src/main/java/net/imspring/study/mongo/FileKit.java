package net.imspring.study.mongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 读写文件的工具
 * @author cxc
 *
 */
public class FileKit {
	private Config config = new Config();
	private String dbName = "local";
	
	private String basepath = "" ;
	
	public FileKit() {
		this.basepath = otainPath();
	}
	
	public FileKit(String dbname){
		this.dbName = dbname;
		this.basepath = otainPath();
	}
	/**
	 * 读取数据库表
	 * @param collectionName
	 * @return
	 */
	public String read(String collectionName){
		String restr ="";
		String path  = this.basepath + collectionName;
		try {
			restr = readFile(path);
		} catch (Exception e) {
			restr = e.getMessage();
		}
		return restr;
	}
	/**
	 * 写入数据库表
	 * @param collectionName
	 * @param content
	 * @return
	 */
	public boolean write(String collectionName,String content){
		String path  = this.basepath + collectionName;
		return writeFile(path,content);
	}
	
	private String otainPath(){
		String spilter = "\\";
		if(basepath.endsWith("\\")||basepath.endsWith("/")){
			spilter ="" ;
		}
		return  config.getDbpath() + spilter + dbName +"\\";
	}
	
	
	private String readFile(String path) throws IOException{
		File file = new File(path);
        if (!file.exists()) {  
            throw new IOException("文件未找到:" + path);  
        }           
        BufferedReader in = new BufferedReader(new FileReader(file));  
        StringBuffer sb = new StringBuffer();  
        String str = "";  
        while ((str = in.readLine()) != null) {  
            sb.append(str);  
        }  
        in.close();  
        return sb.toString(); 
	}
	
	private boolean writeFile(String path,String data){
		boolean flag = false ;
		try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(path, true);
            writer.write(data);
            writer.close();
            flag =true ;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return flag;
	}
	
	private static void mkdir(String dir) {  
        String dirTemp = dir;  
        File dirPath = new File(dirTemp); 
        if(!dirPath.isDirectory()) dirPath = dirPath.getParentFile();
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }  
    } 

}
