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
	if ((document.r.name.value == null) || (document.r.name.value.length == 0)) {
		alert("类型为空，请重新输入");
		document.r.name.focus();
		return false;
	}
	return true;
}
</script>
	</head>
	<jsp:useBean id="type" scope="request" class="bean.type" />
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
								修改类型
							</h2>
							<p>

								<%
									String admin_id = (String) session.getId();
									if (admin_id == null) {
										response.sendRedirect("hello.jsp");
									}
									String t_id = request.getParameter("typeid");
								%>
								<form method="post" action="typesvlt" name=r
									onsubmit="return check()">
									<table width="100" border="0">
										<tr>
											<td width="48%">
												类型
											</td>
											<td width="52%">
												<input name="name" type="text" id="name">
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





