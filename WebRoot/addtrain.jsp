<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"
	errorPage="errorpage.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>更新车票</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" href="image/CSS.CSS" type="text/css">
		<script language=javascript>
function check() {
	if ((document.test.trainid.value == null)
			|| (document.test.trainid.value.length == 0)) {
		alert("车次为空，请重新输入");
		document.test.trainid.focus();
		return false;
	}
	if ((document.test.startplace.value == null)
			|| (document.test.startplace.value.length == 0)) {
		alert("起始站为空，请重新输入");
		document.test.startplace.focus();
		return false;
	}
	if ((document.test.destination.value == null)
			|| (document.test.destination.value.length == 0)) {
		alert("终点站为空，请重新输入");
		document.test.destination.focus();
		return false;
	}
	if ((document.test.starttime.value == null)
			|| (document.test.starttime.value.length == 0)) {
		alert("发车时间为空，请重新输入");
		document.test.starttime.focus();
		return false;
	}
	if ((document.test.type.value == null)
			|| (document.test.type.value.length == 0)) {
		alert("火车类型为空，请重新输入");
		document.test.type.focus();
		return false;
	}
	if ((document.test.speed.value == null)
			|| (document.test.speed.value.length == 0)) {
		alert("车速为空，请重新输入");
		document.test.speed.focus();
		return false;
	}
	if ((document.test.ticketnum.value == null)
			|| (document.test.ticketnum.value.length == 0)) {
		alert("票数为空，请重新输入");
		document.test.starttime.focus();
		return false;
	}
	if ((document.test.unitprice.value == null)
			|| (document.test.unitprice.value.length == 0)) {
		alert("单价为空，请重新输入");
		document.test.unitprice.focus();
		return false;
	}
	var txt = test.speed.value;
	var txt1 = test.ticketnum.value;
	var txt2 = test.unitprice.value;
	if (checknumber(txt)) {
		alert("车速只允许输入数字！");
		return false;
	}
	if (checknumber(txt1)) {
		alert("票数只允许输入数字！");
		return false;
	}
	if (checknumber(txt2)) {
		alert("单价只允许输入数字！");
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
	<jsp:useBean id="train" scope="request" class="bean.train" />
	<BODY background="image/bg1.gif" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0">
		<table width="778" border="0" cellspacing="0" cellpadding="0"
			align="center" height="245">
			<tr>
				<td colspan="3" height="160" height="90">
					<img src="image/log.png" width="778" height="220">
				</td>
			</tr>
			<tr>
				<td background="image/bg2.gif">
					<font size=3 color=yellow face="黑体"> <marquee width="360"
							scrolldelay="5" scrollamount="1" onmouseover="this.stop()"
							onmouseout="this.start()">
							<img alt="" src="image/train_1.gif">
							欢迎光临列车售票系统
							<img alt="" src="image/train_2.gif">
							<img alt="" src="image/train_3.gif">
						</marquee> </font>
				</td>
				<td background="image/bg2.gif">
					<font size=3 color=yellow> <marquee width="360"
							scrolldelay="5" scrollamount="1" onmouseover="this.stop()"
							onmouseout="this.start()">
							<img alt="" src="image/train_3.gif">
							最新动车组信息：
							<img alt="" src="image/train_1.gif">

							<img alt="" src="image/train_2.gif">
						</marquee> </font>
				</td>
			</tr>
		</table>
		<table width="778" border="0" cellspacing="0" cellpadding="0"
			align="center" background="image/bg.gif">
			<tr>
				<td valign="top">
					<table width="280" border="0" cellspacing="0" cellpadding="0"
						background="image/bg.gif">
						<tr>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b><a href="changeadminpass.jsp">修改密码</a> </b>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td height="30">
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b> <a href="userinfo.jsp"> <!--Button1-->用户信息</a> </b>
											</div>
										</td>
									</tr>
								</table>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b> <a href="orderinfo.jsp"> 订票信息</a> </b>
											</div>
										</td>
									</tr>
								</table>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b> <a href="traininfo.jsp"> 车次信息</a> </b>
											</div>
										</td>
									</tr>
								</table>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b> <a href="type.jsp"> <!--Button1-->火车类型</a> </b>
											</div>
										</td>
									</tr>
								</table>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b> <a href="station.jsp"> <!--Button1-->站点管理</a> </b>
											</div>
										</td>
									</tr>
								</table>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b> <a href="sta_sta.jsp"> <!--Button1-->站站管理</a> </b>
											</div>
										</td>
									</tr>
								</table>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">
									<tr>
										<td>
											<div align="center">
												<b> <a href="logincheck?action=logout"> 退出登录</a> </b>
											</div>
										</td>
									</tr>
								</table>
								<table width="250" border="0" cellspacing="0" cellpadding="0"
									height="40" background="image/index_lm.gif">

								</table>
							</td>
							<td width="30" height="30">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<img src="image/bg.gif" width="280" height="100">
							</td>
						</tr>
					</table>
					<p>
						&nbsp;
					</p>
				</td>
				<td width="468" valign="top">
					<table width="458" border="0" cellspacing="0" cellpadding="0"
						style="width: 458px; height: 340px;" height="340">
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									align="center">


									<tr>
										<td bgcolor="#000000">
											<img src="image/Spacer.gif" width="1" height="1">
										</td>
									</tr>
									<tr>
										<td>
											<div align="right">

											</div>
										</td>
									</tr>


									<%
										String admin_id = (String) session.getId();
										if (admin_id == null) {
											response.sendRedirect("hello.jsp");
										}
									%>

									<p align="center">
										<font size=4 color=bule> <img alt=""
												src="image/train_3.gif">新增车次</font>
									</p>
									<form method="post" action="trainsvlt" name=test
										onSubmit="return check()">
										<input type="hidden" name="action" value="new">
										<table width="49%" height="50" border="0" align="center"
											cellpadding="0" cellspacing="0">
											<br />
											<tr>
												<td width="48%">
													车次
												</td>
												<td width="52%">
													<input name="trainid" type="text" id="trainid">
												</td>
											</tr>
											<tr>
												<td width="48%">
													起始站
												</td>
												<td width="52%">
													<input name="startplace" type="text" id="startplace">
												</td>
											</tr>
											<tr>
												<td width="48%">
													终点站
												</td>
												<td width="52%">
													<input name="destination" type="text" id="destination">
												</td>
											</tr>
											<tr>
												<td width="48%">
													发车时间
												</td>
												<td width="52%">
													<input name="starttime" type="text" id="starttime">
												</td>
											</tr>
											<tr>
												<td width="48%">
													火车类型
												</td>
												<td width="52%">
													<input name="type" type="text" id="type">
												</td>
											</tr>
											<tr>
												<td width="48%">
													车速
												</td>
												<td width="52%">
													<input name="speed" type="text" id="speed">
												</td>
											</tr>
											<tr>
												<td width="48%">
													车票数目
												</td>
												<td width="52%">
													<input name="ticketnum" type="text" id="ticketnum">
												</td>
											</tr>
											<tr>
												<td width="48%">
													单价(元/公里)
												</td>
												<td width="52%">
													<input name="unitprice" type="text" id="unitprice">
												</td>
											</tr>

										</table>
										<p align="center">
											<input type="submit" name="Submit" value="提交">
										</p>
									</form>
									<p>
										&nbsp;
									</p>
								</table>
								<table width="90%" border="0" cellspacing="0" cellpadding="0"
									align="center" class="englishfont" height="1">
									<tr>
										<td bgcolor="#000000">
										</td>
									</tr>
								</table>
								<table width="96%" border="0" cellspacing="0" cellpadding="4"
									align="bottom">
									<tr>
										<td>
											<div align="center">
												<font size=2 color=blue> <img alt=""
														src="image/train_2.gif">列车售票系统 
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

				</td>
			</tr>
		</table>

		<table width="778" border="0" cellspacing="0" cellpadding="0"
			align="center" background="image/bg.gif">
			<tr>
				<td align="center" style="line-height: 18px;">
					<font size=2 color=red>售票服务 | 网站声明 | 联系我们 | 反馈与投诉</font>
					<br />
					<br />
					<font size=2 color=bule> </font>
					<br>
					<font size=2 color=#ff7777> </font>
					<br />
				</td>
			</tr>
		</table>

	</BODY>
</html>
