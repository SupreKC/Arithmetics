/*******运算数类*******/

package model;
public class RationalNumber {
	private int denominator;	//分母
	private int numerator;		//分子
	
	/***构造函数，整数看做分母为1的分数***/
	public RationalNumber(int numerator) {
		this.numerator = numerator;
		denominator = 1;
	}
	/****构造函数，第一个参数为分子，第二个为分母****/
	public RationalNumber(int numerator,int denominator) {
		this.denominator = denominator;
		this.numerator = numerator;
	}
	public int getDenominator() {
		return denominator;
	}
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	public int getNumerator() {
		return numerator;
	}
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	
	/***判断两个分数是否相等***/
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		RationalNumber rn = (RationalNumber)obj;
		if(this.denominator == rn.denominator && this.numerator == rn.numerator) {
			return true;
		}else return false;
	}
	
	/*****分数相加*****/
	public RationalNumber plus(RationalNumber rb) {
		int addNum1 = this.numerator * rb.denominator;
		int addNum2 = rb.numerator * this.denominator;
		int demo = this.denominator * rb.denominator;
		RationalNumber value = new RationalNumber(addNum1 + addNum2,demo);
		value.simply();
		return value;
	}
	
	/*****分数相减*****/
	public RationalNumber minus(RationalNumber rb) {
		int addNum1 = this.numerator * rb.denominator;
		int addNum2 = rb.numerator * this.denominator;
		int demo = this.denominator * rb.denominator;
		RationalNumber value = new RationalNumber(addNum1 - addNum2,demo);
		value.simply();
		return value;
	}
	
	/*****分数相乘*****/
	public RationalNumber multiply(RationalNumber rb) {
		RationalNumber value = new RationalNumber(this.numerator * rb.numerator,this.denominator * rb.denominator);
		value.simply();
		return value;
	}
	
	/*****分数相除*****/
	public RationalNumber divide(RationalNumber rb) {
		RationalNumber value = new RationalNumber(this.numerator * rb.denominator,this.denominator * rb.numerator);
		value.simply();
		return value;
	}
	
	/*****分数化简*****/
	public void simply() {
		if(numerator == 0) {
			denominator = 1;
			return ;
		}
		
		int small,big,temp;
		if(numerator > denominator) {
			small = denominator;
			big = numerator;
		}else {
			big = denominator;
			small = numerator;
		}
		while(small != 0) {
			temp = big % small;
			big = small;
			small = temp;
		}
		numerator /= big;
		denominator /= big;
	}
	
	/****取得字符串****/
	@Override
	public String toString() {
		if(denominator == 1)
			return String.valueOf(numerator);
		else return String.valueOf(numerator + "/" + denominator);
	}
	
	/****取得Html格式字符串****/
	public String toHtmlDiv() {
		String str = null;
		if(denominator == 1)
			str = "<div class='nomal'>" + this.numerator + "</div>";
		else
			str = "<div class='fraction'><div>" + this.numerator + "</div><div style='border-top:2px solid black;'>" + this.denominator + "</div></div>";
		return str;
	}
}
