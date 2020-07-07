/**  
 * @Title: ConnectionPool.java
 * @Package neu.work.fourth
 * @author daluosi
 * @date 2020年3月31日
 * @version V1.0  
 */
package neu.work.seventh.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
/* *
  * @ClassName: ConnectionPool
  * @Description: 数据库连接池
  * @author Fuyang Wang
  * @date 2020/4/25
  * @since JDK 1.8
 */
public class ConnectionPool {

	private static String driverClass = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;

	//单例
	private  final static ConnectionPool CONNECTION_POOL = new ConnectionPool();
	
	private ConnectionPool() {
		
	}
	
	public static ConnectionPool newInstance() {

		return CONNECTION_POOL;
	}
	/**
	 * 空闲连接池
	 */
	private final static List<Connection> FREE_CONNECTION_LIST = new ArrayList<Connection>();

	/**
	 * 活动连接池
	 */
	private final static List<Connection> ACTIVE_CONNECTION_LIST = new ArrayList<Connection>();
	private static int size;

	static {
		Properties p = new Properties();
		try {
			
			p.loadFromXML(new FileInputStream(ConnectionPool.class.getClassLoader().getResource("connection.xml").getPath()));
			driverClass = p.getProperty("jdbc.driverClass");
			url = p.getProperty("jdbc.url");
			username = p.getProperty("jdbc.username");
			password = p.getProperty("jdbc.password");
			size = Integer.parseInt(p.getProperty("jdbc.size"));
			// 加载驱动
			Class.forName(driverClass);
			initConnection();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: initConnection
	 * @Description: 初始化数据库连接对象
	 */
	private static void initConnection() {
		
		Connection c = null;

		try {
			// 获取数据库连接保存至集合中
			for (int i = 0; i < size; i++) {
				c = DriverManager.getConnection(url, username, password);
				FREE_CONNECTION_LIST.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: getConnection
	 * @Description: 获取连接对象
	 * @return
	 */
	public synchronized Connection getConnection() {
		while (FREE_CONNECTION_LIST.isEmpty()) {
			return null;
		}
		Connection c = FREE_CONNECTION_LIST.remove(0);
		ACTIVE_CONNECTION_LIST.add(c);
		return c;
	}

	/**
	 * 
	 * @Title: getConnection
	 * @Description: 获取连接对象
	 * @return
	 */
	public  synchronized Connection getNewConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * 
	 * @Title: returnConnection
	 * @Description: 返回连接对象
	 * @param c
	 */
	public void returnConnection(Connection c) {
		ACTIVE_CONNECTION_LIST.remove(c);
		FREE_CONNECTION_LIST.add(c);
	}
}
