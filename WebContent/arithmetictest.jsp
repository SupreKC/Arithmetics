<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" 
	import="java.util.List"
	import="java.util.ArrayList"%>
<%ExpressionRandom exprRand = new ExpressionRandom(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>小学生算数</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<script type="text/javascript" src="js/jquery-2.1.0.js" ></script>
		<script type="text/javascript" src="js/demo.js" ></script>
	</head>
	<body>
	
	<%
		//单元测试：测试加减乘除四种题目比例/数量
		int PLUS = 0;
		int MINU = 0;
		int MULT = 0;
		int DIVI = 0;
	%>
	
	
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
				
				/***以下代码为测试代码，测试完毕后应删去***/
				if(exprs.get(i).getExpr().get(0).getOperator().equals("+"))
					PLUS++;
				else if(exprs.get(i).getExpr().get(0).getOperator().equals("-"))
					MINU++;
				else if(exprs.get(i).getExpr().get(0).getOperator().equals("*"))
					MULT++;
				else if(exprs.get(i).getExpr().get(0).getOperator().equals("/"))
					DIVI++;
				/****测试代码结束******/
			}
			session.setAttribute("exprs", exprs);
		}else{
			
		}
	%>
	<form action="result.jsp">
		<table class="box" align="center" border="0" cellspacing="10" cellpadding="10">
	<%for(int i = 0;i < count;i+=2){%>
			<tr>
				<td align="center" class='expr'>
					<%=exprs.get(i).toHtmlDiv()%>
				</td>
				<td><input autocomplete="off" value="" class="result" name="result" type="text"/></td>
				<td align="center" class='expr'>
					<%=exprs.get(i + 1).toHtmlDiv()%>
				</td>
				<td><input autocomplete="off" value="" class="result" name="result" type="text"/></td>
			</tr>
		<%} %>
			<tr>
				<td colspan="4" align="center"><button class="submit">提交</button></td>
			</tr>
		</table>
		<input name="time" class="timer" type="text" />
	</form>
	
	<%
		/******测试输出******/
		
	%>
	加法运算：<%=PLUS %>所占比例：<%=(double)PLUS/(PLUS+MINU+MULT+DIVI) %><br/>
	减法运算：<%=MINU %>所占比例：<%=(double)MINU/(PLUS+MINU+MULT+DIVI) %><br/>
	乘法运算：<%=MULT %>所占比例：<%=(double)MULT/(PLUS+MINU+MULT+DIVI) %><br/>
	除法运算：<%=DIVI %>所占比例：<%=(double)DIVI/(PLUS+MINU+MULT+DIVI) %><br/>
	
	</body>
</html>