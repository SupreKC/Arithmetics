$(function(){
	/******二元运算/三元运算切换效果*****/
	var twoElement_rd = $(".elementNumChecked");
	var threeElement_rd = $(".elementNumUnChecked");
	var sample = $("#sample");
	twoElement_rd.click(function(){
		twoElement_rd.removeClass("elementNumUnChecked");
		twoElement_rd.addClass("elementNumChecked");
		threeElement_rd.removeClass("elementNumChecked");
		threeElement_rd.addClass("elementNumUnChecked");
		sample.html("例如：12+32=?");
	});
	threeElement_rd.click(function(){
		threeElement_rd.removeClass("elementNumUnChecked");
		threeElement_rd.addClass("elementNumChecked");
		twoElement_rd.removeClass("elementNumChecked");
		twoElement_rd.addClass("elementNumUnChecked");
		sample.html("例如：12+32-18=?");
	});
})

/******倒计时*****/
function timing(timelimit){
	var time = $(".timer").eq(0);
	var pause = $("#pause");
	var date = new Date();
	date.setHours(0);
	date.setMinutes(timelimit / 60);
	date.setSeconds(timelimit % 60);
	var t = window.setInterval(function(){
		time.html(date.getMinutes().toString() + ":" + date.getSeconds().toString());
		time.attr("value",com(date.getMinutes().toString()) + ":" + com(date.getSeconds().toString()));
		date.setTime(date.getTime() - 1000);
		var r;
		if(time.val() == "00:00")
		{
			alert("时间到！请提交答案。");
			$("form").submit();
		}
	},1000);
	
	/*****暂停按钮******/
	pause.click(function(){
		if(pause.html() == "暂停"){
			window.clearInterval(t);
			pause.html("继续");
			$("#cover").css("display","block");
		}else if(pause.html() == "继续"){
			pause.html("暂停");
			$("#cover").css("display","none");
			window.setInterval(function(){
			time.html(date.getMinutes().toString() + ":" + date.getSeconds().toString());
			time.attr("value",com(date.getMinutes().toString()) + ":" + com(date.getSeconds().toString()));
			date.setTime(date.getTime() - 1000);
			var r;
			if(time.val() == "00:00")
			{
				alert("时间到！请提交答案。");
				$(".submit").click();
			}
	},1000);
		}
	});
}

/*****累计用时*****/
function timing2(){
	var time = $(".timer").eq(1);
	var pause = $(".pause");
	var date = new Date();
	date.setHours(0);
	date.setMinutes(0);
	date.setSeconds(0);
	window.setInterval(function(){
		time.html(date.getMinutes().toString() + ":" + date.getSeconds().toString());
		time.attr("value",com(date.getMinutes().toString()) + ":" + com(date.getSeconds().toString()));
		date.setTime(date.getTime() + 1000);
	},1000);
}

/******对个位数，不足位补零，补齐2位数****/
function com(s){
	if(s < 10)
		s = "0" + s;
	return s;
}

/******开始答题，隐藏遮盖层*****/
function start() {
	$("#ready").css("display","none");
	$("#cover").css("display","none");
}

/*****题目限时+题目数量二级联动****/
function setCount(time){
	$("#count").val(time * 10);
}

/******图图台词*******/
function setWords(){
	var words = new Array("我爱你，从这里到月亮，再绕回来。","友谊真是个奇怪的东西，比不吃番茄的还要奇怪","假如不见了 会不会在梦里念叨他的名字","友谊是个很美好的东西 比老鼠还美好的东西");
	var i = Math.floor(Math.random() * 4);
	$("#words").html('<img alt="图图在此" src="img/tutu.png" style="width: 47px;position: relative;top: 10px;">' + words[i]);
}
/****方向键更改焦点****/
function focusNext(i,keyCode){
	//alert(i);
	//alert(keyCode);
	var results = $(".result");
	
	if(keyCode == 37){
		if(i > 0)
			results[i - 1].focus();
		else 
			results[results.length - 1].focus();
	}else if(keyCode == 39){
		if(i < results.length - 1)
			results[i + 1].focus();
		else
			results[0].focus();
	}else if(keyCode == 38){
		if(i > 1)
			results[i-2].focus();
		else
			results[results.length - 2  + i].focus();
	}else if(keyCode == 40){
		if(i >= results.length - 2)
			results[0].focus();
		else 
			results[i + 2].focus();
	}
}

function cusSubmit(){
	
	var results = $(".result");
	var empty = 0;
	
	for(var i = 0;i < results.length;i++){
		if(results[i].value == "")
			empty++;
	}
	if(empty > 0){
		var t = confirm("你还有" + empty + "道题没有输入答案，确定交卷吗？");
		if(t)
			return true;
		else return false;
	}
	return true;
}