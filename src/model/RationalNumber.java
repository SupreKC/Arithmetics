/*******��������*******/

package model;
public class RationalNumber {
	private int denominator;	//��ĸ
	private int numerator;		//����
	
	/***���캯��������������ĸΪ1�ķ���***/
	public RationalNumber(int numerator) {
		this.numerator = numerator;
		denominator = 1;
	}
	/****���캯������һ������Ϊ���ӣ��ڶ���Ϊ��ĸ****/
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
	
	/***�ж����������Ƿ����***/
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		RationalNumber rn = (RationalNumber)obj;
		if(this.denominator == rn.denominator && this.numerator == rn.numerator) {
			return true;
		}else return false;
	}
	
	/*****�������*****/
	public RationalNumber plus(RationalNumber rb) {
		int addNum1 = this.numerator * rb.denominator;
		int addNum2 = rb.numerator * this.denominator;
		int demo = this.denominator * rb.denominator;
		RationalNumber value = new RationalNumber(addNum1 + addNum2,demo);
		value.simply();
		return value;
	}
	
	/*****�������*****/
	public RationalNumber minus(RationalNumber rb) {
		int addNum1 = this.numerator * rb.denominator;
		int addNum2 = rb.numerator * this.denominator;
		int demo = this.denominator * rb.denominator;
		RationalNumber value = new RationalNumber(addNum1 - addNum2,demo);
		value.simply();
		return value;
	}
	
	/*****�������*****/
	public RationalNumber multiply(RationalNumber rb) {
		RationalNumber value = new RationalNumber(this.numerator * rb.numerator,this.denominator * rb.denominator);
		value.simply();
		return value;
	}
	
	/*****�������*****/
	public RationalNumber divide(RationalNumber rb) {
		RationalNumber value = new RationalNumber(this.numerator * rb.denominator,this.denominator * rb.numerator);
		value.simply();
		return value;
	}
	
	/*****��������*****/
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
	
	/****ȡ���ַ���****/
	@Override
	public String toString() {
		if(denominator == 1)
			return String.valueOf(numerator);
		else return String.valueOf(numerator + "/" + denominator);
	}
	
	/****ȡ��Html��ʽ�ַ���****/
	public String toHtmlDiv() {
		String str = null;
		if(denominator == 1)
			str = "<div class='nomal'>" + this.numerator + "</div>";
		else
			str = "<div class='fraction'><div>" + this.numerator + "</div><div style='border-top:2px solid black;'>" + this.denominator + "</div></div>";
		return str;
	}
}
