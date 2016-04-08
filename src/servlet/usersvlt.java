package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.user;

public class usersvlt extends HttpServlet {

	public usersvlt() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	boolean flag;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userid = req.getParameter("userid");
		int success = 0;
		String action = req.getParameter("action");
		user u = null;
		String message = "";

		if ("delete".equalsIgnoreCase(action)) {
			try {
				success = doDelete(userid);
			} catch (SQLException e) {
			}
			if (success != 1) {
				doadminError(req, res,
						"usersvlt: Delete unsuccessful. Rows affected: "
								+ success);
			} else {
				res.sendRedirect("userinfo.jsp");
			}

		}
	}

	public int doDelete(String userid) throws SQLException {
		int num = 0;
		user u = new user();
		num = u.deleteUser(userid);
		return num;
	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			user uu, String target) throws ServletException, IOException {
		req.setAttribute("user", uu);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(target);
		rd.forward(req, res);
	}

	public void doError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/errorpage.jsp");
		rd.forward(req, res);
	}

	public void doadminError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/adminerror.jsp");
		rd.forward(req, res);
	}

	public boolean hasLogin(HttpServletRequest req, HttpServletResponse res,
			String userid) throws ServletException, IOException {
		boolean f = true;
		String message = "对不起，该用户已经被注册过了!";
		user u = new user();
		f = u.hasLogin(userid);
		if (f == false) {
			doError(req, res, message);
		}
		return f;
	}

	public boolean isTrue(HttpServletRequest req, HttpServletResponse res,
			String name, String email, String id, String phone, String address)
			throws ServletException, IOException {
		boolean f = true;
		String message = "";

		if (name == null || name.equals("")) {
			f = false;
			message = "姓名不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}

		if (email == null || email.equals("")) {
			f = false;
			message = "电子邮箱不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}

		if (id == null || id.equals("")) {
			f = false;
			message = "身份证号不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}
		if (phone == null || phone.equals("")) {
			f = false;
			message = "电话不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}
		if (address == null || address.equals("")) {
			f = false;
			message = "联系地址不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}
		return f;

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doGet(req, res);

	}

}
