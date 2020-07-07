/**  
 * @Title: ThreadLocalConnPool.java
 * @Package neu.work.fourth
 * @author daluosi
 * @date 2020年3月31日
 * @version V1.0  
 */
package neu.work.seventh.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* *
  * @ClassName: DButil
  * @Description: 数据库操作工具类
  * @author Fuyang Wang
  * @date 2020/4/25
  * @since JDK 1.8
 */
public class DButil {

	// local
	// variable（线程局部变量）。为每一个使用该变量的线程都提供一个变量值的副本，是每一个线程都可以独立地改变自己的副本，而不会和其它线程的副本冲突。
	public static final ThreadLocal<Connection> CONNECTIONS_POOL = new ThreadLocal<>();

	/**
	 * 
	 * @Title: getConnection
	 * @Description: 获取数据库连接
	 * @return Connection
	 */
	public static Connection getConnection() {
		//从线程局部变量中获取连接
		if (isValid(CONNECTIONS_POOL.get())) {
			return CONNECTIONS_POOL.get();
		}else{
			Connection connection = ConnectionPool.newInstance().getNewConnection();
			CONNECTIONS_POOL.set(connection);
			return connection;
		}
	}
/**
 *
 * @Title: createPreparedstatement
 * @Description: 创建Preparedstatement
 * @param conn, sql, params]
 * @return PreparedStatement
 */

	public static PreparedStatement createPreparedstatement(Connection conn,String sql,Object[] params){
		PreparedStatement preparedStatement= null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if(params!=null)
			for (int i = 0; i <params.length ; i++) {
				preparedStatement.setObject(i+1, params[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	/**
	 *
	 * @Title: executeUpdate
	 * @Description: 执行更新操作
	 * @param ps
	 * @return int
	 */

	public static int executeUpdate(PreparedStatement ps)
	{
		int count = 0;
		try {

			 count =ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}
/**
 *
 * @Title: executeQuery
 * @Description: 查询操作
 * @param ps
 * @return ResultSet
 */

	public static ResultSet executeQuery(PreparedStatement ps){
		ResultSet rs=null;
		try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return rs;
	}
	/**
	 * 
	 * @Title: releaseConnection
	 * @Description: 释放数据库连接
	 * @param conn
	 * @param st

	 * @param rs
	 */
	public static void releaseConnection(Connection conn, Statement st, ResultSet rs) {

		// 判断 ResultSet对象是否为空
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				rs = null;
			}
		}

		// 判断 Statement对象是否为空
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				st = null;
			}
		}
		// 判断数据库连接是否为空
		if (isValid(conn)) {
			ConnectionPool.newInstance().returnConnection(conn);
			CONNECTIONS_POOL.remove();
			conn = null;
		}
	}

	/**
	 * 
	 * @Title: isValid
	 * @Description: 是否可用
	 * @param conn
	 * @return boolean
	 */
	private static boolean isValid(Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
