package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.type;

public class typesvlt extends HttpServlet {

	public typesvlt() {
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
		String typeid = req.getParameter("typeid");
		int success = 0;
		String action = req.getParameter("action");
		type t = null;
		String message = "";

		if ("new".equalsIgnoreCase(action)) {
			flag = true;
			t = doNew(req, res);

			if (flag)
				sendBean(req, res, t, "/type.jsp");
		}
		if ("update".equalsIgnoreCase(action)) {
			flag = true;
			t = doUpdate(req, res, typeid);
			if (flag)
				sendBean(req, res, t, "/type.jsp");

		}

		if ("delete".equalsIgnoreCase(action)) {
			try {
				success = doDelete(typeid);
			} catch (SQLException e) {
			}
			System.out.print(success);
			if (success != 1) {
				doError(req, res,
						"typesvlt: Delete unsuccessful. Rows affected: "
								+ success);
			} else {
				res.sendRedirect("type.jsp");
			}

		}
	}

	public type doNew(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		type t = new type();
		String typeid = req.getParameter("typeid");
		String name = req.getParameter("name");

		if (hasLogin(req, res, typeid)) {
			t.setTypeid(typeid);
			t.setTypename(name);
			t.addType();
		}
		return t;

	}

	public type doUpdate(HttpServletRequest req, HttpServletResponse res,
			String typeid) throws ServletException, IOException {
		type t = new type();
		String name = req.getParameter("name");
		t.setTypeid(typeid);
		t.setTypename(name);
		t.updateType();
		return t;
	}

	public int doDelete(String tid) throws SQLException {
		int num = 0;
		type t = new type();
		num = t.deleteType(tid);
		return num;
	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			type tt, String target) throws ServletException, IOException {
		req.setAttribute("type", tt);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(target);
		rd.forward(req, res);
	}

	public void doError(HttpServletRequest req, HttpServletResponse res,
			String str) throws ServletException, IOException {

		flag = false;
		req.setAttribute("problem", str);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/adminerror.jsp");
		rd.forward(req, res);
	}

	public boolean hasLogin(HttpServletRequest req, HttpServletResponse res,
			String typeid) throws ServletException, IOException {
		boolean f = true;
		String message = "对不起，该车次类型已经存在!";
		type t = new type();
		f = t.hasLogin(typeid);
		if (f == false) {
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
