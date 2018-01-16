/**每个Element由一个运算数(RationalNumber) + 一个运算符(String)构成：
 * 具体代码如下：
 */

package model;

public class Element{
	
	RationalNumber fraction;			//运算数
	String operator;					//运算符
	//构造方法
	public Element(RationalNumber fraction, String operator) {
		this.fraction = fraction;
		this.operator = operator;
	}
	//运算数的get/set方法
	public RationalNumber getFraction() {
		return fraction;
	}
	public void setFraction(RationalNumber fraction) {
		this.fraction = fraction;
	}
	//运算符的get/set方法
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	//重载equals方法，判读两个元素是否相同
	@Override
	public boolean equals(Object obj) {
		Element element = (Element)obj;
		if(this.fraction.equals(element) && this.operator.equals(element.operator))
			return true;
		else return false;
	}
	
	
}