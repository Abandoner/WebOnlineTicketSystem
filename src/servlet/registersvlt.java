package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.register;

public class registersvlt extends HttpServlet {

	public registersvlt() {
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
		int success = 0;
		String action = req.getParameter("action");
		register u = null;
		String message = "";

		if ("register".equalsIgnoreCase(action)) {
			flag = true;
			u = doNew(req, res);
			if (flag) {
				doError(req, res, "ע��ɹ���");
				System.out.println("ע��ɹ�");
			}

		}

	}

	public register doNew(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		register r = new register();
		String userid = req.getParameter("userid");
		System.out.print(userid);

		String username = req.getParameter("username");
		System.out.print(userid);

		String sex = req.getParameter("sex");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");

		if (hasLogin(req, res, userid)) {

			r.setuserId(userid);
			r.setUserName(username);
			r.setSex(sex);
			r.setId(id);

			r.setPassword(password);
			r.setEmail(email);
			r.setPhone(phone);
			r.setAddress(address);

			r.addUser();
		}
		return r;

	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			register r, String target) throws ServletException, IOException {
		req.setAttribute("register", r);
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
		System.out.println("ע��ʧ��");
	}

	public boolean hasLogin(HttpServletRequest req, HttpServletResponse res,
			String userid) throws ServletException, IOException {
		boolean f = true;
		String message = "�Բ��𣬸��û��Ѿ���ע�����!";
		System.out.println("�Բ��𣬸��û��Ѿ���ע�����!");
		register u = new register();
		f = u.hasLogin(userid);
		if (f == false) {
			doError(req, res, message);
		}
		return f;
	}

	// ��ע����Ϣ��У��
	public boolean isTrue(HttpServletRequest req, HttpServletResponse res,
			String name, String password, String id) throws ServletException,
			IOException {
		boolean f = true;
		String message = "";

		if (name == null || name.equals("")) {
			f = false;
			message = "��������Ϊ�գ���������д��";
			if (flag)
				doError(req, res, message);
			System.out.println(message);
		}

		if (password == null || password.equals("")) {
			f = false;
			message = "���벻��Ϊ�գ���������д��";
			if (flag)
				doError(req, res, message);
			System.out.println(message);
		}

		if (id == null || id.equals("")) {
			f = false;
			message = "���֤�Ų���Ϊ�գ���������д��";
			if (flag)
				doError(req, res, message);
			System.out.println(message);
		}
		return f;

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doGet(req, res);

	}

}
