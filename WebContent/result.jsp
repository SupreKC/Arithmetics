<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body style="background-image: url(img/result.jpg);background-size: cover;background-attachment: fixed;">
<% List<Expression> exprs =(List<Expression>) session.getAttribute("exprs");		//题目
	String[] result = request.getParameterValues("result");	//用户答案
	int right = 0,i = 0;
	for(String re:result){
		re = re.trim();			//去除两端空格
		if(re.equals(exprs.get(i).getResult().toString()))
			right++;
		i++;
	}
	/*for(i = 0;i < exprs.size();i++){
		System.out.println(result[i]+"-------"+exprs.get(i).getResult());
		if(result[i].equals(exprs.get(i).getResult()))
			right++;
		i++;
	}*/
	
	%>
	<table class="box" align="center" border="0" cellspacing="10" cellpadding="10">
		<tr>
			<td></td>
		</tr>
		<tr>
			<td colspan="6">你共答对了<%=right %>道题，答错<%=exprs.size()-right %>道题，用时：<%=request.getParameter("time") %></td>
			<td align = "right" style = "font-size:20px;color:red">得分:<%=(int)((double)right/exprs.size() * 100) %></td>
		</tr>
		<%for(i = 0;i < exprs.size();i+=2){%>
			<tr>
				<td align="center" class='expr'>
					<%=exprs.get(i).toHtmlDiv()%>
				</td>
				<td><br><br><input disabled="disabled" value="<%=result[i] %>" class="result" name="result" type="text"/></td>
				<%
				if(exprs.get(i).getResult().toString().equals(result[i])){
				%>
					<td>
						<br><br><img src = "./img/right.PNG" width = "30" height = "30" alt = "" />
					</td>
				<%
				}else{
				%>
					<td>
						<br><br><img src = "./img/error.PNG" width = "30" height = "30" alt = "" />&nbsp;&nbsp;正确答案：<%=exprs.get(i).getResult() %>
					</td>
				<%	
				}
				%>
				<td align="center" class='expr'>
					<%=exprs.get(i + 1).toHtmlDiv()%>
				</td>
				<td><br><br><input disabled="disabled" value="<%=result[i+1] %>" class="result" name="result" type="text"/></td>
				<%
				if(exprs.get(i + 1).getResult().toString().equals(result[i + 1])){
				%>
					<td>
						<br><br><img src = "./img/right.PNG" width = "30" height = "30" alt = "" />
					</td>
				<%
				}else{
				%>
					<td>
						<br><br><img src = "./img/error.PNG" width = "30" height = "30" alt = "" />&nbsp;&nbsp;正确答案：<%=exprs.get(i + 1).getResult() %>
					</td>
				<%	
				}
				%>
			</tr>
		<%} %>
			<tr>
				<td colspan="6" align="center"><button class="submit" onClick="history.go(-2)">返回首页</button></td>
			</tr>
	</table>
</body>
</html>