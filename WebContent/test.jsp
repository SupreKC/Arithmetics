<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="util.DBUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Connection con = DBUtil.getConnection();
	if(con != null)
		out.print("连接成功");
	
	else{
		out.println(DBUtil.error);
		out.print("连接失败");
	}
	out.println(DBUtil.error);
%>
</body>
</html>