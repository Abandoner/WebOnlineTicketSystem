<%@ page language="java" import="java.sql.*" errorPage="errorpage.jsp"
	pageEncoding="utf-8"%>
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
	if ((document.r.userid.value == null)
			|| (document.r.userid.value.length == 0)) {
		alert("登录名为空，请重新输入");
		document.r.userid.focus();
		return false;
	}
	if ((document.r.userid.value != null)
			&& (document.r.userid.value.length > 16)) {
		alert("登录名长度过长，长度不得超过16位,请重新输入!");
		document.r.userid.focus();
		return false;
	}
	if ((document.r.username.value == null)
			|| (document.r.username.value.length == 0)) {
		alert("姓名为空，请重新输入");
		document.r.username.focus();
		return false;
	}
	if ((document.r.username.value != null)
			&& (document.r.username.value.length > 5)) {
		alert("姓名格式不对，长度不得超过5位，请重新输入!");
		document.r.username.focus();
		return false;
	}
	if ((document.r.password.value == null)
			|| (document.r.password.value.length == 0)) {
		alert("密码为空，请重新输入");
		document.r.password.focus();
		return false;
	}
	if ((document.r.password.value != null)
			&& ((document.r.password.value.length < 6) || (document.r.password.value.length > 16))) {
		alert("密码长度格式不对，长度必须在6到16位之间，请重新输入!");
		document.r.username.focus();
		return false;
	}
	if ((document.r.id.value == null) || (document.r.id.value.length == 0)) {
		alert("身份证号为空，请重新输入");
		document.r.id.focus();
		return false;
	}
	if ((document.r.id.value != null)
			&& ((document.r.id.value.length < 16)
					|| (document.r.id.value.length > 18) || (document.r.id.value.length == 17))) {
		alert("身份证号格式错误,身份证号码必须是16到18位,请重新输入!");
		document.r.id.focus();
		return false;
	}
	if ((document.r.email.value == null)
			|| (document.r.email.value.length == 0)) {
		alert("电子邮箱为空，请重新输入");
		document.r.email.focus();
		return false;
	}
	if ((document.r.address.value == null)
			|| (document.r.address.value.length == 0)) {
		alert("联系地址为空，请重新输入");
		document.r.address.focus();
		return false;
	}
	if ((document.r.phone.value == null)
			|| (document.r.phone.value.length == 0)) {
		alert("电话为空，请重新输入");
		document.r.phone.focus();
		return false;
	}
	if ((document.r.phone.value != null)
			&& ((document.r.phone.value.length < 7) || (document.r.phone.value.length > 12))) {
		alert("电话格式不对,如例021-23233322或者013823221023，请重新输入！");
		document.r.phone.focus();
		return false;
	}
	return true;
}
</script>


	</head>
	<jsp:useBean id="register" scope="request" class="bean.register"></jsp:useBean>
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
								用户注册
							</h2>
							<form method=post action="registersvlt" name=r
								onsubmit="return check()">
								<input type="hidden" name="action" value="register">
								<table width="100" border="1">
									<tr>
										<td>
											登录名(*)
										</td>
										<td>
											<input name="userid" type="text" id="userid">
										</td>

									</tr>
									<tr>
										<td width="48%">
											姓名(*)
										</td>
										<td width="52%">
											<input name="username" type="text" id="username">
										</td>
									</tr>
									<tr>
										<td>
											密码(*)
										</td>
										<td>
											<input name="password" type="password" id="password"
												maxlength="10">
										</td>
									</tr>
									<tr>
										<td>
											身份证号(*)
										</td>
										<td>
											<input name="id" type="text" id="id" maxlength="20">
										</td>
									</tr>
									<tr>
										<td>
											性别
										</td>
										<td>
											<select name="sex" size="1" id="select">
												<option>
													男
												</option>
												<option>
													女
												</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>
											电子邮箱(*)
										</td>
										<td>
											<input name="email" type="text" id="email" maxlength="20">
										</td>
									</tr>
									<tr>
										<td>
											联系地址(*)
										</td>
										<td>
											<input name="address" type="text" id="address" maxlength="20">
										</td>
									</tr>
									<tr>
										<td>
											电话(*)
										</td>
										<td>
											<input name="phone" type="text" id="phone" maxlength="20">
										</td>
									</tr>
								</table>
								<p align="center">
									<input type="submit" name="Submit" value="提交信息">
								</p>
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
														<b><a href="hello.jsp">首页</a>
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
														<b> <a href="userlogin.jsp"> <!--Button1-->用户登录</a>
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
														<b> <a href="adminlogin.jsp"> 管理员登录</a>
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
														<b> <a href="trainquery.jsp"> 按车次查询</a>
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
														<b> <a href="addressquery.jsp"> 按地点查询</a>
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
														<b> <a href="stationquery.jsp"> 按站点查询</a>
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
														<b> <a href="userregister.jsp"> 用户注册</a>
														</b>
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

				</div>
			</div>
		</div>
	</body>
</html>





