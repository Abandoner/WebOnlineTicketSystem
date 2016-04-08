package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.user;
import bean.sqlBean;

public class passwordsvlt extends HttpServlet {
	boolean flag;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String oldpw = null;
		String pw1 = null;
		String pw2 = null;

		String action = req.getParameter("action");

		ResultSet rs = null;

		if ("changeadminpass".equalsIgnoreCase(action)) {
			flag = true;
			user p = new user();
			id = req.getParameter("id");
			oldpw = req.getParameter("oldpass");
			pw1 = req.getParameter("password1");
			pw2 = req.getParameter("password2");
			rs = p.equaladminpass(id);
			String str1 = "";
			try {
				if (rs.next()) {
					try {
						str1 = rs.getString("password");
						System.out.println("str1=" + str1);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (pw1.equals("") || pw2.equals("") || pw1 == null || pw2 == null
					|| oldpw.equals("") || oldpw == null)
				doError(req, res, "���벻��Ϊ�գ�");
			else if (!oldpw.equals(str1))
				doError(req, res, "ԭ���벻��ȷ");
			else {
				doUpdate(req, res, pw1, pw2, id);
				if (flag)
					doError(req, res, "�޸�����ɹ��������µ�¼��");
			}
		}

		if ("changepass".equalsIgnoreCase(action)) {
			flag = true;
			user p = new user();
			id = req.getParameter("id");
			oldpw = req.getParameter("oldpass");
			pw1 = req.getParameter("password1");
			pw2 = req.getParameter("password2");
			rs = p.equalpass(id);
			String str1 = "";
			try {
				if (rs.next()) {
					try {
						str1 = rs.getString("password");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (pw1.equals("") || pw2.equals("") || pw1 == null || pw2 == null
					|| oldpw.equals("") || oldpw == null) {
				doError(req, res, "���벻��Ϊ�գ�");
				System.out.println("���벻��Ϊ�գ�");
			} else if (!oldpw.equals(str1)) {
				doError(req, res, "ԭ���벻��ȷ");
				System.out.println("ԭ���벻��ȷ");
			} else {
				doUpdates(req, res, pw1, pw2, id);
				if (flag) {
					doError(req, res, "�޸�����ɹ��������µ�¼��");
					System.out.println("�޸�����ɹ��������µ�¼��");
				}

			}
		}
	}

	// �޸Ĺ���Ա����
	public void doUpdate(HttpServletRequest req, HttpServletResponse res,
			String pw1, String pw2, String id) throws ServletException,
			IOException {
		int num = 0;
		if (!pw1.equals(pw2))
			if (flag) {
				doError(req, res, "���벻һ�£������䣡");
				System.out.println("���벻һ�£�����������");
			}

		user p = new user();
		String Driver = getServletConfig().getInitParameter("Driver");

		String user = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		sqlBean db = new sqlBean(Driver, user, password);
		num = p.updateadmin(id, pw1);

		if (num == 0)
			if (flag) {
				doError(req, res, "����ʧ��");
				System.out.println("����ʧ��");
			}
	}

	// �޸��û�����
	public void doUpdates(HttpServletRequest req, HttpServletResponse res,
			String pw1, String pw2, String id) throws ServletException,
			IOException {
		int num = 0;
		if (!pw1.equals(pw2))
			if (flag)
				doError(req, res, "���벻һ�£������䣡");
		user p = new user();
		String Driver = getServletConfig().getInitParameter("Driver");

		String user = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		sqlBean db = new sqlBean(Driver, user, password);
		num = p.updatepass(id, pw1);
		if (num == 0)
			if (flag)
				doError(req, res, "����ʧ��");
	}

	public void doError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/errorpage.jsp");
		rd.forward(req, res);
		System.out.println("������Ϣ����");
	}

	public void sendResultSet(HttpServletRequest req, HttpServletResponse res,
			java.sql.ResultSet rs, String target) throws ServletException,
			IOException {
		req.setAttribute("rs", rs);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(target);
		rd.forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doGet(req, res);

	}

}
