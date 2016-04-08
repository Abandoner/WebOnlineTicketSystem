package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.train;

public class trainsvlt extends HttpServlet {

	public trainsvlt() {
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
		String trainid = req.getParameter("trainid");
		int success = 0;
		String action = req.getParameter("action");
		train t = null;
		String message = "";

		if ("new".equalsIgnoreCase(action)) {
			flag = true;
			t = doNew(req, res);

			if (flag)
				sendBean(req, res, t, "/traininfo.jsp");
		}
		if ("update".equalsIgnoreCase(action)) {
			flag = true;
			t = doUpdate(req, res, trainid);
			if (flag)
				sendBean(req, res, t, "/traininfo.jsp");

		}

		if ("delete".equalsIgnoreCase(action)) {
			try {
				success = doDelete(trainid);
			} catch (SQLException e) {
			}
			System.out.print(success);
			if (success != 1) {
				doError(req, res,
						"trainsvlt: Delete unsuccessful. Rows affected: "
								+ success);
			} else {
				res.sendRedirect("traininfo.jsp");
			}

		}
	}

	public train doNew(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		train t = new train();
		String trainid = req.getParameter("trainid");
		String startstation = req.getParameter("startplace");
		String deststation = req.getParameter("destination");
		String starttime = req.getParameter("starttime");
		String type = req.getParameter("type");
		int speed = Integer.parseInt(req.getParameter("speed"));
		int ticketnum = Integer.parseInt(req.getParameter("ticketnum"));
		double unitprice = Double.parseDouble(req.getParameter("unitprice"));

		if (hasLogin(req, res, trainid, startstation, deststation)) {
			t.setStartstation(startstation);
			t.setDeststation(deststation);
			t.setStarttime(starttime);
			t.setType(type);
			t.setTrainid(trainid);
			t.setSpeed(speed);
			t.setTicketnum(ticketnum);
			t.setUnitprice(unitprice);
			t.addTrain();
		}
		return t;
	}

	public train doUpdate(HttpServletRequest req, HttpServletResponse res,
			String trainid) throws ServletException, IOException {
		train t = new train();

		String startstation = req.getParameter("startplace");
		String deststation = req.getParameter("destination");
		String starttime = req.getParameter("starttime");
		String type = req.getParameter("type");
		int speed = Integer.parseInt(req.getParameter("speed"));
		int ticketnum = Integer.parseInt(req.getParameter("ticketnum"));
		double unitprice = Double.parseDouble(req.getParameter("unitprice"));
		t.setTrainid(trainid);
		t.setStartstation(startstation);
		t.setDeststation(deststation);
		t.setStarttime(starttime);
		t.setSpeed(speed);
		t.setTicketnum(ticketnum);
		t.setUnitprice(unitprice);
		t.setType(type);

		t.updateTrain();
		return t;
	}

	public int doDelete(String tid) throws SQLException {
		int num = 0;
		train t = new train();
		num = t.deleteTrain(tid);
		return num;
	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			train tt, String target) throws ServletException, IOException {
		req.setAttribute("train", tt);
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
			String trainid, String sp, String des) throws ServletException,
			IOException {
		boolean f = true;
		String message = "对不起，该车次已经存在!";
		train t = new train();
		f = t.hasLogin(trainid, sp, des);
		if (f == false) {
			doError(req, res, message);
		}
		return f;
	}

	public boolean isTrue(HttpServletRequest req, HttpServletResponse res,
			String startplace, String destination, String starttime,
			String type, String next, String trainid) throws ServletException,
			IOException {
		boolean f = true;
		String message = "";

		if (next == null || next.equals("")) {
			f = false;
			message = "下一站不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}

		if (startplace == null || startplace.equals("")) {
			f = false;
			message = "初始站不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}

		if (destination == null || destination.equals("")) {
			f = false;
			message = "终点站不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}

		if (starttime == null || starttime.equals("")) {
			f = false;
			message = "发车时间不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}
		if (type == null || type.equals("")) {
			f = false;
			message = "车次类型不能为空，请重新填写！";
			if (flag)
				doError(req, res, message);
		}
		if (trainid == null || trainid.equals("")) {
			f = false;
			message = "车次不能为空，请重新填写！";
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
