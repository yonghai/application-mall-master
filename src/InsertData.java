import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;
import com.mall.ssm.po.City;
import com.mall.ssm.po.County;
import com.mall.ssm.po.Province;


public class InsertData {

	static Connection conn = null;
	static int counter1 = 1;
	static int counter2 = 1;
	static int counter3 = 1;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//加载驱动
        
        String jdbc="jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF8&useServerPrepStmts=true&prepStmtCacheSqlLimit=256&cachePrepStmts=true&prepStmtCacheSize=256&rewriteBatchedStatements=true";
        try {
			conn=DriverManager.getConnection(jdbc, "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//链接到数据库
        
       
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		
		
		
		
		File file = new File(System.getProperty("user.dir"),"city.json");
		String content = FileUtils.readFileToString(file, "utf-8");
		System.out.println(content);
		List<Province> list = JSONObject.parseArray(content, Province.class);
		
		for (Province province : list) {
			inserProvince(province);
			
			if(province.getCityList()!=null){
				for (City city : province.getCityList()) {
					insertCity(city);
					
					if(city.getDistrictList()!=null){
						for (County county : city.getDistrictList()) {
							insertCountry(county);
						}
					}
					
					
				}
			}
			
		}
		
		conn.close();//关闭通道
		System.out.println(counter1 +"  "+counter2+"  "+counter3);
		System.out.println("结束!!!!!!!!!!");
	}

	private static void insertCountry(County county) throws SQLException{
		 Statement state=conn.createStatement();   //容器
	     String sql="insert into tb_county values("+county.getId()+",'"+county.getName()+"',"+county.getPid()+")";   //SQL语句
	     state.executeUpdate(sql);         //将sql语句上传至数据库执行
	     System.out.println(counter3+"插入县"+county.getName());
	     state.close();
	     counter3++;
	}

	private static void insertCity(City city) throws SQLException{
		 Statement state=conn.createStatement();   //容器
	     String sql="insert into tb_city values("+city.getId()+",'"+city.getName()+"',"+city.getPid()+")";   //SQL语句
	     state.executeUpdate(sql);         //将sql语句上传至数据库执行
	     System.out.println(counter2+"插入市"+city.getName());
	     state.close();
	     counter2++;
	}

	private static void inserProvince(Province province) throws SQLException{
		 Statement state=conn.createStatement();   //容器
	     String sql="insert into tb_province values("+province.getId()+",'"+province.getName()+"')";   //SQL语句
	     state.executeUpdate(sql);         //将sql语句上传至数据库执行
	     System.out.println(counter1+"插入省"+province.getName());
	     state.close();
	     counter1++;
	}

}
