<%@ page language="java" import="java.sql.*" errorPage="errorpage.jsp" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <link href="css/screen.css" type="text/css" rel="stylesheet" media="screen,projection" />
    <!--[if lte IE 6]>
    <link href="css/msie.css" type="text/css" rel="stylesheet" media="screen,projection" />
    <![endif]-->
    <link rel="stylesheet" media="print" type="text/css" href="css/print.css" />

    <title>火车售票 &ndash;  </title>


	
	
  </head>
 <jsp:useBean id="book" scope="page" class="bean.book"/> 
  <body>

    <div id="layout">
      
      <div id="header">
        
        <h1 id="logo"><a href="./" title="Company">火车<span class="light">售票</span><span class="leaf">&nbsp;</span></a></h1>
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
          
            <h2>订票信息</h2>
<%
String admin_id = (String)session.getId(); 
if(admin_id==null){response.sendRedirect("hello.jsp");}
String userid="",ticketid="",trainid="",start="",dest="",type="",tic_id="";
double dingjin,afford,price;
String num,buyno;//订票数量 



   ResultSet rs = book.getBook();
  
  rs.last();
  
  
   int pageSize=8;
int Page=1;
int  totalPage=1;
int totalrecord=0;


totalrecord=rs.getRow();
if(totalrecord==0){
%>
<p>暂无订票信息</p>
<%
}
else{
if(totalrecord%pageSize==0)
totalPage=totalrecord/pageSize;
else
totalPage=(int)(totalrecord/pageSize)+1;

if(totalPage==0) totalPage=1;
if(request.getParameter("Page")==null||request.getParameter("Page").equals(""))
{Page=1;}
else
try{
Page=Integer.parseInt(request.getParameter("Page"));
}
catch(java.lang.NumberFormatException e)
{Page=1;}
if(Page<1)Page=1;
if(Page>totalPage)Page=totalPage;
rs = book.getBook();
rs.absolute((Page-1)*pageSize+1); 
   %>
   
    <table width="150"  border="0" >

    <tr>
    		 <td>订单号</td>
      <td>车票编号</td>
	<td>车次</td>
	 <td>起始站</td>
		<td>终点站</td>
		<td>车票日期</td>
		<td>价格</td>
		<td>定金</td>
	

		<td>退票</td>
		
         </tr>
 
   

      	 
   <%
 
 for(int iPage=1;iPage<=pageSize;iPage++) 
 {
 num=rs.getString("countnumber");
tic_id=rs.getString("book.ticket_id");
 trainid=rs.getString("train_id");
 start=rs.getString("book.start_station");
 dest=rs.getString("book.dest_station");
 type=rs.getString("book.starttime");
 price=rs.getDouble("price");
 dingjin=rs.getDouble("subscription");

 afford=rs.getDouble("afford");
  %>
 
	  
      <tr> 
      	  <td><%=num%></td>
       <td><%=tic_id%><br></td>
      
        <td><%=trainid%><br></td>
	    <td><%=start%><br></td>
	    <td><%=dest%><br></td>
        <td><%=type%></td>
        <td><%=price%><br></td>
	    <td><%=dingjin%></td>

	
        <td><a href="ticketsvlt?action=delete&userid=<%=userid%>&ticketid=<%=tic_id%>">退票</a></td>
      </tr>
      <%
        if(!rs.next())
		  break; 
}
  %>

    </table>






	 <form action="orderstatus.jsp.jsp" method="get">
<%
if(Page!=1)
{out.print(" <a href=orderstatus.jsp.jsp?Page=1>第一页</a>");
out.print(" <a href=orderstatus.jsp.jspPage="+(Page-1)+">上一页</a>");
}
if(Page!=totalPage)
{
out.print(" <a href=orderstatus.jsp.jsp?Page="+(Page+1)+">下一页</a>");
out.print(" <a href=orderstatus.jsp.jsp?Page="+totalPage+">最后一页</a>");
}
%>

输入页数：<input type="text" name="Page" size="3"/>页数：<%=Page%>/<%=totalPage%><%} %></form>
		
			

			
			
			
            
         
           
            
            
         
    
        
            
           
            
          </div>
        </div>

         <div id="panel-left" class="box panel">
          <div class="in">
 <table width="150" border="0" cellspacing="0" cellpadding="0"  >
    
	<tr><td>
		
	<table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
              <td height="25"> 
                <div align="center"><b><a href="changepass.jsp">修改密码</a></b></div>
                </td>
              </tr>
            </table>
		
		
    <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
                <td> 
  <div align="center"><b> 
 
<a href="orderstatus.jsp"> 
<!--Button6-->订票情况</a> </b></div>
                </td>
              </tr>
            </table>
          
          
            <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
                <td> 
 <div align="center"><b>
<a href="trainquery1.jsp">
按车次查询</a></b></div>
                </td>
              </tr>
            </table>
           
           
            <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
                <td> 
  <div align="center"><b>
<a href="addressquery1.jsp">
按地点查询</a></b></div>
                </td>
              </tr>
            </table>
           
           
            <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
                <td> 
   <div align="center"><b>

<a href="stationquery1.jsp">
按站点查询</a></b></div>
                </td>
              </tr>
            </table>
          
          
            <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
                <td> 
<div align="center"><b> 
<a href="logincheck?action=logout"> 
退出登录</a> </b></div>
                </td>
              </tr>
            </table>   
        
        
            <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
            </table>
            </td>
	</tr>
      </table>


          </div>
        </div>
      
      </div>
        
        <div id="footer">
          <span class="f-left">  <a href="/"> </a></span> <span class="f-right">  <a href="http://www.davidkohout.cz"> </a>  <a href="http://www.efektim.com" title="Efektim - Real Estate Consulting"> </a></span>
        </div>
    </div></div>
  </body>
</html>





