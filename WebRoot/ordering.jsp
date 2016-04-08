<%@ page language="java"
	import="java.sql.*,java.util.Date,java.text.SimpleDateFormat"
	pageEncoding="UTF-8" errorPage="errorpage.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cs" lang="cs">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta http-equiv="content-language" content="cs" />
		<meta name="author" lang="cs" content="..." />
		<meta name="copyright" lang="cs" content="..." />
		<meta name="description" content="..." />
		<meta name="keywords" content="..." />
		<meta name="robots" content="all,follow" />
		<link href="css/screen.css" type="text/css" rel="stylesheet"
			media="screen,projection" />
		<!--[if lte IE 6]>
    <link href="css/msie.css" type="text/css" rel="stylesheet" media="screen,projection" />
    <![endif]-->
		<link rel="stylesheet" media="print" type="text/css"
			href="css/print.css" />
		<%
			Date today = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = f.format(today);
			request.setAttribute("ff", dateStr);
			System.out.println(dateStr);
		%>

		<title>火车售票 &ndash;</title>
		<script language="javascript" type="text/javascript"
			src="My97DatePicker/WdatePicker.js">
</script>
		<script language=javascript>
//保证时间3天之内
function strDateTime(str) {
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d
			.getDate() == r[4]);
}

function btnValidate() {
	var strfrom = document.getElementById("startdate").value;
	var strto = document.getElementById("startdate_hide").value;
	if (!strDateTime(strfrom) && strDateTime(strto)) {
		alert("the date format is not valid");
	} else {
		if (DateDiff(strto, strfrom) > 11) {//180 days
			alert("只能订购10天之内的票:" + DateDiff(strto, strfrom));
			return false;
		} else {

			return true;
		}
	}
}

function DateDiff(sDate1, sDate2) {
	var aDate, oDate1, oDate2, iDays;
	aDate = sDate1.split("-");
	oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
	aDate = sDate2.split("-");
	oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
	iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24);
	return iDays;
}

function check() {

	if ((document.test.countnumber.value == null)
			|| (document.test.countnumber.value.length == 0)) {
		alert("订票数为空，请重新输入");
		document.test.countnumber.focus();
		return false;
	}
	if ((document.test.startdate.value == null)
			|| (document.test.startdate.value.length == 0)) {
		alert("车票日期为空，请重新输入");
		document.test.startdate.focus();
		return false;
	}
	var b = btnValidate();
	if (b == false) {
		return false;
	}
	if ((document.test.dingjin.value == null)
			|| (document.test.dingjin.value.length == 0)) {
		alert("订金为空，请重新输入");
		document.test.dingjin.focus();
		return false;
	}
	var txt = test.countnumber.value;
	var txt1 = test.dingjin.value;
	if (checknumber(txt1)) {
		alert("订金只允许输入数字！");
		return false;
	}
	if (checknumber(txt)) {
		alert("订票数只允许输入数字！");
		return false;
	}
	function checknumber(String) {
		var Letters = "1234567890";
		var i;
		var c;
		for (i = 0; i < String.length; i++) {
			c = String.charAt(i);
			if (Letters.indexOf(c) == -1) {
				return true;
			}
		}
		return false;
	}
	return true;
}
</script>

	</head>
	<jsp:useBean id="ticket" scope="request" class="bean.ticket" />
	<jsp:useBean id="query" scope="request" class="bean.query" />
	<body>

		<div id="layout">

			<div id="header">

				<h1 id="logo">
					<a href="./" title="Company">火车<span class="light">售票</span><span
						class="leaf">&nbsp;</span>
					</a>
				</h1>
				<hr class="noscreen" />

				<p class="noscreen noprint">
					<em>Skip to <a href="#obsah">content</a>, <a href="#nav">navigation</a>.</em>
				</p>

				<hr class="noscreen" />

				<div id="nav" class="box">

					<hr class="noscreen" />
				</div>

				<div id="container" class="box">

					<div id="obsah" class="content box">
						<div class="in">

							<h2>
								订票
							</h2>
							<p>

								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<%
										String user_id = (String) session.getAttribute("name1");
										if (user_id == null) {
											response.sendRedirect("hello.jsp");
										}
										String t_id = request.getParameter("trainid");
										String s_id = new String(request.getParameter("start").getBytes(
												"ISO-8859-1"), "UTF-8");
										String s_id1 = new String(request.getParameter("dest").getBytes(
												"ISO-8859-1"), "UTF-8");
										int maxseat = 0;
										ResultSet rs = query.getMaxseat(t_id);

										try {
											while (rs.next()) {
												maxseat = rs.getRow();
											}
										} catch (SQLException ex) {
										}

										if (maxseat <= 0) {
									%>
									<p>
										该车次的票已经售完
									</p>
									<%
										} else {
									%>


									<br />
									<form method="post" action="ticketsvlt"
										onsubmit="return check()" name=test>
										<input type="hidden" name="action" value="order"></input>
										<input type="hidden" name="userid" value="<%=user_id%>"></input>
										<table width="49%" height="50" border="0" align="center"
											cellpadding="0" cellspacing="0">
											<br />
											<tr>
												<td width="12%">
													<img alt="" src="image/file.gif">
												</td>
												<td width="36%">
													车次
												</td>
												<td width="52%">
													<input name="trainid" type="text" id="trainid"
														value="<%=t_id%>">
												</td>
											</tr>
											<tr>
												<td width="12%">
													<img alt="" src="image/file.gif">
												</td>
												<td width="36%">
													起始站
												</td>
												<td width="52%">
													<input name="startplace" type="text" id="startplace"
														value="<%=s_id%>">
												</td>
											</tr>
											<tr>
												<td width="12%">
													<img alt="" src="image/file.gif">
												</td>
												<td width="36%">
													终点站
												</td>
												<td width="52%">
													<input name="destination" type="text" id="destination"
														value="<%=s_id1%>">
												</td>
											</tr>
											<tr>
												<td width="12%">
													<img alt="" src="image/file.gif">
												</td>
												<td width="36%">
													订票数目
												</td>
												<td width="52%">
													<input name="countnumber" type="text" id="countnumber">
												</td>
											</tr>
											<tr>
												<td width="12%">
													<img alt="" src="image/file.gif">
													<td width="36%">
														车票日期
													</td>
													<td width="52%">
														<input name="startdate" type="text" id="startdate"
															onClick="WdatePicker()">
													</td>
													<td>
														<input name="startdate_hide" type="hidden"
															value="<%=request.getAttribute("ff")%>" />
													</td>
											</tr>
											<tr>
												<td width="12%">
													<img alt="" src="image/file.gif">
												</td>
												<td width="36%">
													订金
												</td>
												<td width="52%">
													<input name="dingjin" type="text" id="dingjin">
												</td>
											</tr>
											<tr>
												<td width="12%">
													<img alt="" src="image/file.gif">
												</td>
												<td width="36%">
													火车类型
												</td>
												<td>
													<select name="typeid" size="1" id="typeid">
														<option value=1>
															普快
														</option>
														<option value=2>
															特快
														</option>
														<option value=3>
															动车
														</option>
													</select>
												</td>
											</tr>
										</table>
										<p align="center">
											<input type="submit" name="Submit" value="提交">
										</p>
										<%
											}
										%>
									</form>

								</table>
						</div>
					</div>

					<div id="panel-left" class="box panel">
						<div class="in">
							<table width="150" border="0" cellspacing="0" cellpadding="0">

								<tr>
									<td>

										<table width="120" border="0" cellspacing="0" cellpadding="0"
											height="20">
											<tr>
												<td height="25">
													<div align="center">
														<b><a href="changepass.jsp">修改密码</a>
														</b>
													</div>
												</td>
											</tr>
										</table>


										<table width="120" border="0" cellspacing="0" cellpadding="0"
											height="20">
											<tr>
												<td>
													<div align="center">
														<b> <a href="orderstatus.jsp"> <!--Button6-->订票情况</a>
														</b>
													</div>
												</td>
											</tr>
										</table>


										<table width="120" border="0" cellspacing="0" cellpadding="0"
											height="20">
											<tr>
												<td>
													<div align="center">
														<b> <a href="trainquery1.jsp"> 按车次查询</a>
														</b>
													</div>
												</td>
											</tr>
										</table>


										<table width="120" border="0" cellspacing="0" cellpadding="0"
											height="20">
											<tr>
												<td>
													<div align="center">
														<b> <a href="addressquery1.jsp"> 按地点查询</a>
														</b>
													</div>
												</td>
											</tr>
										</table>


										<table width="120" border="0" cellspacing="0" cellpadding="0"
											height="20">
											<tr>
												<td>
													<div align="center">
														<b> <a href="stationquery1.jsp"> 按站点查询</a>
														</b>
													</div>
												</td>
											</tr>
										</table>


										<table width="120" border="0" cellspacing="0" cellpadding="0"
											height="20">
											<tr>
												<td>
													<div align="center">
														<b> <a href="logincheck?action=logout"> 退出登录</a> </b>
													</div>
												</td>
											</tr>
										</table>


										<table width="120" border="0" cellspacing="0" cellpadding="0"
											height="20">
										</table>
									</td>
								</tr>
							</table>


						</div>
					</div>

				</div>

				<div id="footer">
					<span class="f-left"> <a href="/"> </a>
					</span>
					<span class="f-right"> <a href="http://www.davidkohout.cz">
					</a> <a href="http://www.efektim.com"
						title="Efektim - Real Estate Consulting"> </a>
					</span>
				</div>
			</div>
		</div>
	</body>
</html>









