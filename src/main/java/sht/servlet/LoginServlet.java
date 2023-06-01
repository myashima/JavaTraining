package sht.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sht.beans.UserBean;
import sht.dao.DAOException;
import sht.dao.loginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			//リクエストパラメータ取得
			//文字コード指定
			request.setCharacterEncoding("UTF-8");
			//文字コードの指定
			response.setContentType("text/html; charset=UTF-8");
			String action = request.getParameter("action");

			//インスタンス化
			loginDAO dao = new loginDAO();

			if(action.equals("login")) {

				if(request.getParameter("id") == null 
						|| request.getParameter("id").length() == 0
						|| request.getParameter("pass") == null
						|| request.getParameter("pass").length() == 0) {
					request.setAttribute("message","IDとパスワードを入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
					rd.forward(request, response);
					return;
				}
				//ログイン時はユーザ名とパスワードを取得する
				String id = request.getParameter("id");
				String pass = request.getParameter("pass");

				UserBean user = dao.findUser(id,pass);

				if( user.getId() == null || user.getId().length() == 0
						|| user.getPassword() == null || user.getPassword().length() == 0) {
					request.setAttribute("message","ユーザ情報が見つかりません");
					RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
					rd.forward(request, response);
					return;
				}

				//セッション管理を行う
				HttpSession session = request.getSession();
				//ログイン済みの属性を設定する
				session.setAttribute("isLogin","true");
				session.setAttribute("userinfo",user);

				gotoPage(request,response,"/login-result.jsp");

			}
			//add
			else if(action.equals("add")) {
				if(request.getParameter("name") == null || request.getParameter("name").length() == 0
						|| request.getParameter("id") == null || request.getParameter("id").length() <= 3
						|| request.getParameter("pass") == null || request.getParameter("pass").length() <= 3) {
					request.setAttribute("message","正しく入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
					rd.forward(request, response);
					return;
				}
				String name = request.getParameter("name");
				String id = request.getParameter("id");
				String pass = request.getParameter("pass");
				dao.addUser(name,id,pass);
				//追加後、全レコード表示
				UserBean user = dao.findUser(id,pass);
				//beanをリクエストスコープに入れてJSPへフォワードする
				request.setAttribute("User",user);
				gotoPage(request,response,"/donemembership.jsp");
			}
			//delete
			else if(action.equals("delete")) {//delete

				//セッション管理を行う
				HttpSession session = request.getSession(false);
				//ログイン済みの属性を設定する
				UserBean user = (UserBean)session.getAttribute("userinfo");

				String id = user.getId();
				String pass = user.getPassword();

				dao.deleteByPrimaryKey(id,pass);

				gotoPage(request,response,"/donedelete.jsp");
			}
			//update
			else if(action.equals("update")) {
				if(request.getParameter("name") == null
						||request.getParameter("name").length() == 0
						||request.getParameter("id") == null 
						|| request.getParameter("id").length() == 0
						|| request.getParameter("pass") == null
						|| request.getParameter("pass").length() == 0) {
					request.setAttribute("message","お名前とIDとパスワードを入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
					rd.forward(request, response);
					return;
				}
				//セッション管理を行う
				HttpSession session = request.getSession(false);
				//ログイン済みの属性を設定する
				UserBean user = (UserBean)session.getAttribute("userinfo");

				String name = request.getParameter("name");
				String id = request.getParameter("id");
				String pass = request.getParameter("pass");

				//追加後、全レコード表示
				dao.updateUser(name,id,pass,user);
				//beanをリクエストスコープに入れてJSPへフォワードする
				request.setAttribute("User",user);
				gotoPage(request,response,"/doneupdate.jsp");
			}
			else if(action.equals("random")){

				//地域と季節未選択
				if( request.getParameter("area") == null ) {
					request.setAttribute("message", "※地域を選択してください。");
					gotoPage(request,response,"/login-result.jsp");
					return;
				}

				// リクエストパラメータの読み込み
				// 春夏秋冬
				String spring = request.getParameter("spring");
				String summer = request.getParameter("summer");
				String autumn = request.getParameter("autumn");
				String winter = request.getParameter("winter");
				// エリア
				String area = request.getParameter("area");

				// 選択された季節とエリアを条件にして旅行先を抽出する
				// daoクラスのrandomメソッドを呼び出して
				// 戻り値をListに設定する
				List<UserBean> list = dao.random(area,spring, summer, autumn, winter);

				// リクエストスコープにListを設定
				request.setAttribute("show",list);

				gotoPage(request,response,"/showspot.jsp");
			}
			else if(action.equals("select")){

				String spot = request.getParameter("spot");
				String pref = request.getParameter("pref");

				List<UserBean> list = dao.select(spot, pref);
				//リクエストスコープにListを設定
				request.setAttribute("show", list);

				gotoPage(request,response,"/showspot2.jsp");

			}

		}catch(DAOException e) {
			e.printStackTrace();
			request.setAttribute("message","内部エラーが発生しました。");
			gotoPage(request,response,"/errInternal.jsp");
		}

	}
	//転送用のメソッド
	protected void gotoPage(HttpServletRequest request, HttpServletResponse response,String page) throws ServletException, IOException {
		//転送先の指定と転送準備
		//page = /-----.jsp
		RequestDispatcher rd = request.getRequestDispatcher(page);
		//転送実行
		rd.forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}

