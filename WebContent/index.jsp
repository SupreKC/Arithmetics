<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>小学生四则运算</title>
<style type="text/css">
body{
  background:url("./img/start.jpg");
  background-size:1200px 700px;
  text-align:center;
  font-family: 方正卡通简体;
}
</style>
<script type="text/javascript" src="js/jquery-2.1.0.js" ></script>
<script type="text/javascript" src="js/demo.js" ></script>
</head>
<body>
	<form action="arithmetic.jsp" class="option">
		<center><h2>欢迎使用在线答题系统</h2></center>
		<br /><br />
		<p>选择题目类型：
			<label id="elementNum" class="elementNumChecked" style="border-bottom-left-radius: 8px;border-top-left-radius: 8px;"><input name="elementNum" value="2" checked="checked" type="radio">2元运算</label>
			<label id="elementNum" class="elementNumUnChecked" style="border-bottom-right-radius: 8px;border-top-right-radius: 8px;"><input name="elementNum" value="3" type="radio">3元运算</label><br></p>
		<label id="sample" style="color:red"></label><br><br>
		<p>我要在
				<select name="timelimit" onChange="setCount(this.options[this.selectedIndex].value)">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
				</select>
				分钟内做完
				<select name="count" id="count">
					<option>10</option>
					<option>20</option>
					<option>30</option>
					<option>40</option>
					<option>50</option>
					<option>60</option>
				</select>道题！</p>
				<!-- <input name="count" value="5000" type="radio" />5000<br/>
				<input name="count" value="1000000" type="radio" />10000000(极限测试，慎选！！) -->
			<!--<input name="fraction" type="checkbox"><label>分数计算</label>-->
		<br><br />
		<p><button class="submit">开始答题喽！</button>
	</p></form>
</body></html>