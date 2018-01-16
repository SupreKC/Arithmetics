<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" 
	import="java.util.List"
	import="java.util.ArrayList"%>
<%ExpressionRandom exprRand = new ExpressionRandom(); %>
<%
		//题目数量
		String str = request.getParameter("count");
	
		int count = 0;						//题目数量
		int elementNum = 0;					//运算数个数
		boolean isFraction = false;			//分数计算
		List<Expression> exprs = new ArrayList<Expression>();	//题目
		if(str != null){					//判断是由用户定制题目页面跳转至该页面
			count = Integer.parseInt(str);
			elementNum = Integer.parseInt(request.getParameter("elementNum"));
			isFraction = (request.getParameter("fraction") != null);
			for(int i = 0;i < count;i++){
				exprs.add(ExpressionRandom.getExpression(elementNum, isFraction));
				//out.println(exprs.get(i).toString());
			}
			session.setAttribute("exprs", exprs);
		}else{
			
		}
		//时间限制
		String timeLimit = request.getParameter("timelimit").trim();
		int time = Integer.parseInt(timeLimit) * 60;
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>小学生算数</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<!-- 调整结果输入框位置 -->
		<%if(isFraction == false) {%>
		<style type="text/css">
			.result{
				position: relative;
				top: 12px;
			}
		</style>
		<%} %>
		<!-- 调整结束 -->
		<script type="text/javascript" src="js/jquery-2.1.0.js" ></script>
		<script type="text/javascript" src="js/demo.js" ></script>
	</head>
	<body style="margin:0px" onload="setWords()">
	<img class="background" src="img/bg.jpg" />
	<form action="result.jsp">
		<table class="box" align="center" border="0" cellspacing="10" cellpadding="10">
	<%for(int i = 0;i < count;i+=2){%>
			<tr>
				<td align="center" class='expr'>
					<%=exprs.get(i).toHtmlDiv()%>
				</td>
				<td><input oninput="value=value.replace(/\D/g,'')" onkeydown="focusNext(<%=i %>,event.keyCode)" autocomplete="off" value="" class="result" name="result" type="text"/></td>
				<td align="center" class='expr'>
					<%=exprs.get(i + 1).toHtmlDiv()%>
				</td>
				<td><input oninput="value=value.replace(/\D/g,'')" onkeydown="focusNext(<%=i+1 %>,event.keyCode)" autocomplete="off" value="" class="result" name="result" type="text"/></td>
			</tr>
		<%} %>
			<tr>
				<td colspan="4" align="center"><button class="submit" onclick="return cusSubmit();">提交</button></td>
			</tr>
		</table>
		<input class="timer" type="text" disabled="disable"/>
		<input name="time" class="timer" style="top:80px" type="hidden" />
	</form>
	
	<div id="cover"></div>
	<div id="ready">
		<br>
		<p style="margin-left: 20px;font-size: xx-large;">准备好了吗？</p>
		<P style="font-size: x-large;">系统为你准备了<%=count %>道题，点击按钮开始倒计时。</p><br>
		<p><button onclick="timing(<%=time %>);timing2();start()" class="submit">现在开始！</button></p>
		<br>
		<p id="words"><img alt="图图在此" src="img/tutu.png" style="width: 47px;position: relative;top: 10px;">我爱你，从这里到月亮，再绕回来。</p>
	</div>
	<label id="pause">暂停</label>
	</body>
</html>