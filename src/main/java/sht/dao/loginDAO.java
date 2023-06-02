package sht.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sht.beans.UserBean;

public class loginDAO {
	//フィールド変数
	//このクラス内であればどこでも使用可能
	private Connection con;

	public loginDAO() throws DAOException{
		//DBへの接続を行う（コネクションの取得）
		getConnection();
	}

	public UserBean findUser(String id,String pass) throws DAOException{
		//DBの接続が出来ていない場合、DBへの接続を行う
		if(con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//SQL文の作成
			String sql = "SELECT * FROM userinfo WHERE ID=? AND PASS=?";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//プレースホルダ
			st.setString(1,id);
			st.setString(2,pass);
			//SQLの実行
			rs = st.executeQuery();
			//インスタンス化
			UserBean bean = new UserBean();

			//1レコードずつ取得
			while(rs.next()) {
				String strid = rs.getString("id");
				String strpass = rs.getString("pass");
				String strname = rs.getString("name");
				//beanに代入
				bean = new UserBean(strname,strid,strpass);				

			}
			//商品一覧をbeanとして返す
			return bean;

		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}finally {
			try {
				//リソースの開放
				if(st != null)st.close();
				close();
			}catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int addUser(String name,String id,String pass) throws DAOException{
		if(con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			//SQL文の作成
			String sql = "INSERT INTO userinfo(name,id,pass)VALUES(?,?,?)";
			//PrearedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//名前とログインID,パスワードを指定
			st.setString(1,name);
			st.setString(2,id);
			st.setString(3,pass);
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}finally {
			try {
				//リソースの開放
				if(st != null)st.close();
				close();
			}catch(Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int deleteByPrimaryKey(String id,String pass) throws DAOException{
		if(con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			//SQL文の作成
			String sql = "DELETE FROM userinfo WHERE id = ? AND pass = ?";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//主キーの指定
			st.setString(1,id);
			st.setString(2,pass);
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}finally {
			try {
				//リリースの開放
				if(st != null)st.close();
				close();
			}catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int updateUser(String name,String id, String pass,UserBean bean) throws DAOException{
		if(con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			//SQL文の作成
			String sql = "UPDATE userinfo SET name = ? , id = ? , pass = ? WHERE name = ? AND id = ? AND pass = ?";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//主キーの指定
			st.setString(1,name);
			st.setString(2,id);
			st.setString(3,pass);
			st.setString(4,bean.getName());
			st.setString(5,bean.getId());
			st.setString(6,bean.getPassword());

			//SQLの実行
			int rows = st.executeUpdate();
			return rows;
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}finally {
			try {
				//リリースの開放
				if(st != null)st.close();
				close();
			}catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
	public List<UserBean> random( String area, String spring, String summer, String autumn, String winter ) throws DAOException{
		//DBへの接続が出来ていない場合にDBへ接続を行う
		if(con == null){
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = null;
		try {

			// 季節未選択の場合
			if ( spring == null
					&& summer == null
					&& autumn == null
					&& winter == null ) {
				//SQL文の作成
				sql = "SELECT * FROM kankouchi WHERE area = ? ORDER BY RAND() LIMIT 10";
				st = con.prepareStatement(sql);
				st.setString(1, area);
			}
			
			//地域未選択の場合
			else if ( area == null) {
				//SQL文の作成
				sql = "SELECT * FROM kankouchi a WHERE a.spring = ? or a.summer = ? or a.autumn = ? or a.winter = ? ORDER BY RAND() LIMIT 10";
				//PreparedStatementオブジェクトの取得
				st = con.prepareStatement(sql);
				//各変数を設定
				st.setString(1, spring);
				st.setString(2, summer);
				st.setString(3, autumn);
				st.setString(4, winter);
			
			//SQLの実行
			rs = st.executeQuery();
			//インスタンス化
			List<UserBean> list = new ArrayList<UserBean>();
			//1レコードずつ取得
			while(rs.next()){
				String strspot = rs.getString("spot");
				String strpref = rs.getString("pref");
				
				UserBean bean = new UserBean();
				//beanに代入
				bean.setSpot(strspot);
				bean.setPref(strpref);
				// 返却用のリストに抽出した旅行先を追加
				list.add(bean);
			}
			//旅行先をlistで返す
			return list;
			}
			else {
				//SQL文の作成
				sql = "SELECT * FROM (SELECT * FROM kankouchi WHERE area = ?) a WHERE a.spring = ? or a.summer = ? or a.autumn = ? or a.winter = ? ORDER BY RAND() LIMIT 10";
				//PreparedStatementオブジェクトの取得
				st = con.prepareStatement(sql);

				//各変数を設定
				st.setString(1, area);
				st.setString(2, spring);
				st.setString(3, summer);
				st.setString(4, autumn);
				st.setString(5, winter);
			}
			//SQLの実行
			rs = st.executeQuery();
			//インスタンス化
			List<UserBean> list = new ArrayList<UserBean>();
			//1レコードずつ取得
			while(rs.next()){
				String strspot = rs.getString("spot");
				String strpref = rs.getString("pref");

				UserBean bean = new UserBean();
				//beanに代入
				bean.setSpot(strspot);
				bean.setPref(strpref);
				// 返却用のリストに抽出した旅行先を追加
				list.add(bean);
			}
			//旅行先をlistで返す
			// ※今回は10件選出するためList
			return list;
	
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}finally{
			try{
				//リソースの開放
				if(st != null)st.close();
				close();
			}catch(Exception e){
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<UserBean> select(String spot, String pref) throws DAOException{
		//DBへの接続が出来ていない場合にDBへ接続を行う
		if(con == null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM kankouchi ORDER BY RAND() LIMIT 10";
			//preparedStatementeオブジェクトの取得
			st = con.prepareStatement(sql);
			//SQLの実行
			rs = st.executeQuery();
			//インスタンス化
			List<UserBean> list = new ArrayList<UserBean>();
			//1レコードずつ取得
			while(rs.next()){
				String strspot = rs.getString("spot");
				String strpref = rs.getString("pref");

				UserBean bean = new UserBean();
				//beanに代入
				bean.setSpot(strspot);
				bean.setPref(strpref);
				// 返却用のリストに抽出した旅行先を追加
				list.add(bean);
			}
			//旅行先をlistで返す
			// ※今回は10件選出するためList
			return list;

		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}finally{
			try{
				//リソースの開放
				if(st != null)st.close();
				close();
			}catch(Exception e){
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
	
	
	public void random(String spot, String pref) {
		// TODO 自動生成されたメソッド・スタブ

	}
	private void getConnection() throws DAOException{
		try {
			//DBへの接続情報
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/ensyu?serverTimezone=UTC&useSSL=false";
			String user = "root";
			String pass = "P@ssw0rd";

			//接続出来たらconに情報が入る
			con = DriverManager.getConnection(url,user,pass);
			//情報が間違っていた時
		}catch(Exception e) {
			throw new DAOException("接続に失敗しました");
		}
	}
	private void close() throws SQLException{
		if(con != null) {
			con.close();
			con = null;
		}
	}
}
