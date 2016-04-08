package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import bean.tra_sta_sta;

public class tra_sta_stasvlt extends HttpServlet {

	public tra_sta_stasvlt() {
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
		String stationid = req.getParameter("stationid");
		String trainid = req.getParameter("trainid");
		int success = 0;
		String action = req.getParameter("action");
		tra_sta_sta t = null;
		String message = "";

		if ("new".equalsIgnoreCase(action)) {
			flag = true;
			t = doNew(req, res);

			if (flag)
				sendBean(req, res, t, "/station.jsp");
		}
		if ("update".equalsIgnoreCase(action)) {
			flag = true;
			t = doUpdate(req, res, stationid, trainid);
			if (flag)
				sendBean(req, res, t, "/station.jsp");

		}

		if ("delete".equalsIgnoreCase(action)) {
			try {
				success = doDelete(stationid, trainid);
			} catch (SQLException e) {
			}
			System.out.print(success);
			if (success != 1) {
				doError(req, res,
						"tra_sta_stasvlt: Delete unsuccessful. Rows affected: "
								+ success);
			} else {

				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/station.jsp");
				rd.forward(req, res);
			}

		}
	}

	public tra_sta_sta doNew(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		tra_sta_sta t = new tra_sta_sta();
		String stationid = req.getParameter("stationid");
		String trainid = req.getParameter("trainid");
		String waittime = req.getParameter("waittime");
		String starttime = req.getParameter("starttime");
		int shunxu = Integer.parseInt(req.getParameter("shunxu"));

		if (hasLogin(req, res, trainid, stationid)) {
			t.setStationid(stationid);
			t.setStarttime(starttime);
			t.setTrainid(trainid);
			t.setTrainid(trainid);
			t.setWaittime(waittime);
			t.setShunxu(shunxu);
			t.addTra_Sta();
		}
		return t;

	}

	public tra_sta_sta doUpdate(HttpServletRequest req,
			HttpServletResponse res, String stationid, String trainid)
			throws ServletException, IOException {
		tra_sta_sta t = new tra_sta_sta();
		String waittime = req.getParameter("waittime");
		String starttime = req.getParameter("starttime");
		int shunxu = Integer.parseInt(req.getParameter("shunxu"));
		t.setStationid(stationid);
		t.setStarttime(starttime);
		t.setTrainid(trainid);
		t.setTrainid(trainid);
		t.setWaittime(waittime);
		t.setShunxu(shunxu);
		t.updateTra_Sta();
		return t;
	}

	public int doDelete(String sid, String tid) throws SQLException {
		int num = 0;
		tra_sta_sta t = new tra_sta_sta();
		num = t.deleteTra_Sta(sid, tid);
		return num;
	}

	public void sendBean(HttpServletRequest req, HttpServletResponse res,
			tra_sta_sta tt, String target) throws ServletException, IOException {
		req.setAttribute("tra_sta_sta", tt);
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
			String tid, String sid) throws ServletException, IOException {
		boolean f = true, g = true;
		String message = "对不起，该车次的该站点已经存在!";
		tra_sta_sta t = new tra_sta_sta();
		f = t.hasLogin(tid, sid);
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
