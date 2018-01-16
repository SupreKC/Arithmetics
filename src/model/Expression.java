/**对每一个表达式，按照以下规则将其拆分：
 * 如表达式“2+3-4/7”
 * 一个运算数+一个运算符作为一个元素(Element，详见Element.java)，如即2+、3-
 * 每个表达式为2个或三个Element构成的列表(List)，
 * 最后一个元素(Element)没有跟着的运算符，补充"#"
 * 
 * 即表达式“2+3-4/7”由一个列表{2+、3-、4/7#}表示。
 */

package model;

import java.util.List;

public class Expression {
	private List<Element> expr;					//Element构成的列表，表示一个表达式
	
	public Expression()
	{
		
	}
	public Expression(List<Element> expr) {		//构造方法，参数为List<Element>类型
		this.expr = expr;
	}

	//表达式列表的get/set方法
	public List<Element> getExpr() {
		return expr;
	}

	public void setExpr(List<Element> expr) {
		this.expr = expr;
	}

	//得到表达式字符串,如"1+2/4/7"
	@Override
	public String toString() {
		String exprStr = new String();
		for(Element e:expr) {
			exprStr += e.getFraction().toString();
			if(!e.getOperator().equals("#"))
				exprStr += e.getOperator();
		}
		return exprStr;
	}

	/****取得Html格式字符串
	 * 如"<div class='nomal'>7</div><div class='nomal'>×</div><div class='nomal'>1</div><div class='nomal'>+</div><div class='nomal'>86</div><div class="nomal">=</div>"
	 * 在html中直接加入toHtmlDiv()生成的字符串，可规范显示该表达式
	 */
	public String toHtmlDiv() {
		String exprStr = new String();
		for(Element e:expr) {			//将每个元素转为html格式div，详见Element.java
			exprStr += e.getFraction().toHtmlDiv();
			if(!e.getOperator().equals("#"))
			{
				//存运算符时以"*、/"表示乘除运算符，此处转换为"×、÷"
				if(e.getOperator().equals("*"))
					exprStr += "<div class='nomal'>×</div>";
				else if(e.getOperator().equals("/"))
					exprStr += "<div class='nomal'>÷</div>";
				else
					exprStr += "<div class='nomal'>"+ e.getOperator() + "</div>";
			}
		}
		exprStr += "<div class=\"nomal\">=</div>";
		System.out.println(exprStr);
		return exprStr;
	}
	
	/****两个算数表达式是否相同****/
	@Override
	public boolean equals(Object obj) {
		Expression expr = (Expression)obj;
		if(this.expr.size() != expr.expr.size())
			return false;
		for(int i = 0;i < this.expr.size();i++)
		{
			if(!this.expr.get(i).equals(expr.expr.get(i)))
				return false;
		}
		return true;
	}
	
	/****计算结果，表达式不合法时，返回null****/
	public RationalNumber getResult() {
		RationalNumber rn = null;				//结果
		int length = this.getExpr().size();		//运算数个数
		String op1 = this.getExpr().get(0).operator,op2;
		if(length == 2)							//二元运算
			rn = calc(this.getExpr().get(0).fraction,op1,this.getExpr().get(1).fraction);
		else {
			op2 = this.getExpr().get(1).operator;
			RationalNumber rn_temp;				//中间数
			//运算符优先级判断
			if((op1.equals("+") || op1.equals("-"))&&(op2.equals("*")||op2.equals("/"))){
				rn_temp = calc(this.getExpr().get(1).fraction,op2,this.getExpr().get(2).fraction);
				if(rn_temp == null)return null;
				//if(rn_temp == null || rn_temp.getDenominator() > 100 || rn_temp.getNumerator() > 100 || rn_temp.getDenominator() < 0 || rn_temp.getNumerator() < 0 || rn_temp.getDenominator() == 0|| (rn_temp.getDenominator() != 1 && rn_temp.getNumerator() >= rn_temp.getDenominator())) return null;
				rn = calc(this.getExpr().get(0).fraction,op1,rn_temp);
			}else {
				rn = calc(this.getExpr().get(0).fraction,op1,this.getExpr().get(1).fraction);
				if(rn == null)return null;
				rn = calc(rn,op2,this.getExpr().get(2).fraction);
			}
		}
		
		if(rn == null || rn.getDenominator() > 100 || rn.getNumerator() > 100 || rn.getDenominator() < 0 || rn.getNumerator() < 0 || rn.getDenominator() == 0|| (rn.getDenominator() != 1 && rn.getNumerator() >= rn.getDenominator()))
			return null;
		else
			return rn;
	}
	
	/****根据单个运算符计算单个结果***/
	private RationalNumber calc(RationalNumber rn1,String o,RationalNumber rn2) {
		RationalNumber rn = null;
		if(rn1.getDenominator() > 100 || rn1.getNumerator() > 100 || rn1.getDenominator() < 0 || rn1.getNumerator() < 0 || rn1.getDenominator() == 0|| (rn1.getDenominator() != 1 && rn1.getNumerator() >= rn1.getDenominator()) || rn2.getDenominator() > 100 || rn2.getNumerator() > 100 || rn2.getDenominator() < 0 || rn2.getNumerator() < 0 || rn2.getDenominator() == 0 || (rn2.getDenominator() != 1 && rn2.getNumerator() >= rn2.getDenominator())){
			rn = null;
		}else {
			if(o.equals("+"))
				rn = rn1.plus(rn2);
			else if(o.equals("-"))
				rn = rn1.minus(rn2);
			else if(o.equals("*"))
			{
				if(rn1.getNumerator()<=9&&rn2.getNumerator()<=9)
				{
					rn = rn1.multiply(rn2);
				}
				else return null;
			}
			else if(o.equals("/"))
			{
				if((rn2.getNumerator() != 0)&&(rn2.getNumerator()<=9)&&(rn1.getNumerator()%rn2.getNumerator() == 0)&&(rn1.getNumerator()/rn2.getNumerator()<=9))
					rn = rn1.divide(rn2);
				else return null;
			}
		}
		return rn;
	}
}