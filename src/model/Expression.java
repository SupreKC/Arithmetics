/**��ÿһ�����ʽ���������¹������֣�
 * ����ʽ��2+3-4/7��
 * һ��������+һ���������Ϊһ��Ԫ��(Element�����Element.java)���缴2+��3-
 * ÿ�����ʽΪ2��������Element���ɵ��б�(List)��
 * ���һ��Ԫ��(Element)û�и��ŵ������������"#"
 * 
 * �����ʽ��2+3-4/7����һ���б�{2+��3-��4/7#}��ʾ��
 */

package model;

import java.util.List;

public class Expression {
	private List<Element> expr;					//Element���ɵ��б���ʾһ�����ʽ
	
	public Expression()
	{
		
	}
	public Expression(List<Element> expr) {		//���췽��������ΪList<Element>����
		this.expr = expr;
	}

	//���ʽ�б��get/set����
	public List<Element> getExpr() {
		return expr;
	}

	public void setExpr(List<Element> expr) {
		this.expr = expr;
	}

	//�õ����ʽ�ַ���,��"1+2/4/7"
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

	/****ȡ��Html��ʽ�ַ���
	 * ��"<div class='nomal'>7</div><div class='nomal'>��</div><div class='nomal'>1</div><div class='nomal'>+</div><div class='nomal'>86</div><div class="nomal">=</div>"
	 * ��html��ֱ�Ӽ���toHtmlDiv()���ɵ��ַ������ɹ淶��ʾ�ñ��ʽ
	 */
	public String toHtmlDiv() {
		String exprStr = new String();
		for(Element e:expr) {			//��ÿ��Ԫ��תΪhtml��ʽdiv�����Element.java
			exprStr += e.getFraction().toHtmlDiv();
			if(!e.getOperator().equals("#"))
			{
				//�������ʱ��"*��/"��ʾ�˳���������˴�ת��Ϊ"������"
				if(e.getOperator().equals("*"))
					exprStr += "<div class='nomal'>��</div>";
				else if(e.getOperator().equals("/"))
					exprStr += "<div class='nomal'>��</div>";
				else
					exprStr += "<div class='nomal'>"+ e.getOperator() + "</div>";
			}
		}
		exprStr += "<div class=\"nomal\">=</div>";
		System.out.println(exprStr);
		return exprStr;
	}
	
	/****�����������ʽ�Ƿ���ͬ****/
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
	
	/****�����������ʽ���Ϸ�ʱ������null****/
	public RationalNumber getResult() {
		RationalNumber rn = null;				//���
		int length = this.getExpr().size();		//����������
		String op1 = this.getExpr().get(0).operator,op2;
		if(length == 2)							//��Ԫ����
			rn = calc(this.getExpr().get(0).fraction,op1,this.getExpr().get(1).fraction);
		else {
			op2 = this.getExpr().get(1).operator;
			RationalNumber rn_temp;				//�м���
			//��������ȼ��ж�
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
	
	/****���ݵ�����������㵥�����***/
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