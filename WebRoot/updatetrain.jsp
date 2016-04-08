<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	errorPage="errorpage.jsp"%>
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

		<title>火车售票 &ndash;</title>


		<script language=javascript>
function check() {
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
	if ((document.test.unitprice.value == null)
			|| (document.test.unitprice.value.length == 0)) {
		alert("单价为空，请重新输入");
		document.test.unitprice.focus();
		return false;
	}
	var txt = test.speed.value;
	var txt2 = test.unitprice.value;
	if (checknumber(txt)) {
		alert("车速只允许输入数字！");
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
								更新车次
							</h2>
							<p>

								<%
									String admin_id = (String) session.getId();
									if (admin_id == null) {
										response.sendRedirect("hello.jsp");
									}

									String train_id = request.getParameter("trainid");
								%>

								<form method="post" action="trainsvlt" name=test
									onSubmit="return check()">
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="trainid" value="<%=train_id%>">
									<table width="100" border="0">
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
												单价(元/公里)
											</td>
											<td width="52%">
												<input name="unitprice" type="text" id="unitprice">
											</td>
										</tr>
										<tr>
											<td>
												<input name="" type="submit" value="提交" />
											</td>
											<td>
												<input name="" type="reset" value="重置" />
											</td>
										</tr>
									</table>
								</form>
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
														<b><a href="changeadminpass.jsp">修改密码</a>
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
														<b> <a href="userinfo.jsp"> <!--Button1-->用户信息</a>
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
														<b> <a href="orderinfo.jsp"> 订票信息</a>
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
														<b> <a href="traininfo.jsp"> 车次信息</a>
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
														<b> <a href="type.jsp"> <!--Button1-->火车类型</a>
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
														<b> <a href="station.jsp"> <!--Button1-->站点管理</a>
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
														<b> <a href="sta_sta.jsp"> <!--Button1-->站站管理</a> </b>
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





