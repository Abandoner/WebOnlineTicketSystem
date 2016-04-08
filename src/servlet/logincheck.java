package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.sqlBean;

public class logincheck extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String message = null;
		String id = null;
		id=req.getParameter("name1");//��ȡ�û���
		HttpSession session = req.getSession(true);//ͨ�������ȡsession
		session.setAttribute("name1", String.valueOf(id));
		String password = null;
		password = req.getParameter("name2");//��ȡ����

		String kind = null;
		kind = req.getParameter("name4");
		String temp = getPassword(req, res, id, kind);
		System.out.println("--------------------------");
		System.out.println(temp);
		System.out.println("--------------------------");
		if (password.equals(temp))
			goo(req, res, kind);//
		else {
			message = "�û�������������";
			doError(req, res, message);
		}
	}

	public void goo(HttpServletRequest req, HttpServletResponse res, String kind)
			throws ServletException, IOException {

		if (kind.equals("user")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/user.jsp");
			rd.forward(req, res);
			System.out.println("�û���¼�ɹ���");
		}

		if (kind.equals("admin")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/admin.jsp");
			rd.forward(req, res);
			System.out.println("ϵͳ����Ա��¼�ɹ���");
		}
	}

	public String getPassword(HttpServletRequest req, HttpServletResponse res,
			String id, String kind) throws ServletException, IOException {
		String Driver=getServletConfig().getInitParameter("Driver");
	
		String user=getServletConfig().getInitParameter("user");
		String password=getServletConfig().getInitParameter("password");
		sqlBean db = new sqlBean(Driver,user,password);
		String pw = "";
		if (kind.equals("user")) {
			String sql = "select password from " + kind + " where user_id='"
					+ id + "'";
			try {
				ResultSet rs = db.executeQuery(sql);
				if (rs.next()) {
					pw = rs.getString("password");
					System.out.println("password="+pw);
				}
			} catch (Exception e) {
				System.out.print(e.toString());
			}
			
		}
		if (kind.equals("admin")) {
			String sql = "select password from " + kind + " where admin_id='"
					+ id + "'";
			try {
				ResultSet rs = db.executeQuery(sql);
				if (rs.next()) {
					pw = rs.getString("password");
					System.out.println("password="+pw);
				}
			} catch (Exception e) {
				System.out.print(e.toString());
				e.printStackTrace();
			}
		}

		return pw;
	}

	public void doError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/errorpage.jsp");// ��ת��������ʾҳ��
		rd.forward(req, res);
		System.out.println("�����ʻ����󣬵�¼ʧ��");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String action = action = req.getParameter("action");
		if ("logout".equalsIgnoreCase(action)) {
			HttpSession session = req.getSession(true);
			session.invalidate();
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/hello.jsp");
			rd.forward(req, res);
		}
	}

}
