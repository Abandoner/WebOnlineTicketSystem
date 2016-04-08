<%@ page language="java" import="java.util.*" pageEncoding="utf-8" errorPage="errorpage.jsp"%>
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
			<script language=javascript>
function check()
{               
  if((document.test.name4.value==null)||(document.test.name4.value.length==0))
  {   
    alert("站点为空，请重新输入");
    document.test.name4.focus();
    return false;
  }
  return true;
}
	
	</script>
  </head>
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
          
            <h2>按站点查询</h2>
            <p>
		
			<form action="stationqueryresult.jsp" method=post name=test onsubmit="return check()">
			
			
			<table width="120" border="1">
  <tr>
    <td>站点：</td>
    <td><input name="name4" type="text" /></td>
  </tr>
  <tr>
    <td><input name="submit" type="submit" value="提交" /></td>
    <td><input name="reset" type="reset" value="重置" /></td>
  </tr>
</table>

			</form>

			
			
			
            
         
           
            
            
         
    
        
            
           
            
          </div>
        </div>

  <div id="panel-left" class="box panel">
          <div class="in">
 <table width="150" border="0" cellspacing="0" cellpadding="0"  >
    
	<tr><td>
		
	<table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
              <td height="25"> 
                  <div align="center"><b><a href="hello.jsp">首页</a></b></div>
                </td>
              </tr>
            </table>
		
		
<table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >
              <tr> 
                <td> 
                  <div align="center"><b>
<a href="userlogin.jsp">
<!--Button1-->用户登录</a></b></div>
                </td>
              </tr>
</table>

  <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >   
              <tr> 
                <td> 
                  <div align="center"><b>
<a href="adminlogin.jsp">
管理员登录</a></b></div>
                </td>
              </tr>
            </table>
          
          
   <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >         
              <tr> 
                <td> 
                  <div align="center"><b>
<a href="trainquery.jsp">
按车次查询</a></b></div>
                </td>
              </tr>
            </table>
           
           
  <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >          
              <tr> 
                <td> 
                  <div align="center"><b>
<a href="addressquery.jsp">
按地点查询</a></b></div>
                </td>
              </tr>
            </table>
           
  <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >         
            
              <tr> 
                <td> 
                  <div align="center"><b>
<a href="stationquery.jsp">
按站点查询</a></b></div>
                </td>
              </tr>
            </table>
          
  <table width="120" border="0" cellspacing="0" cellpadding="0" height="20"   >        
            
              <tr> 
                <td> 
                  <div align="center"><b>

<a href="userregister.jsp">
<!--Button6-->用户注册</a> </b></div>
                </td>
              </tr>
            </table>   
        
        
            

</td></tr>
      </table>


          </div>
        </div>
      
      </div>
        
        <div id="footer">
        
        </div>
    </div></div>
  </body>
</html>



