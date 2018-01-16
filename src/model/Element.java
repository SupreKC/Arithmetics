/**ÿ��Element��һ��������(RationalNumber) + һ�������(String)���ɣ�
 * ����������£�
 */

package model;

public class Element{
	
	RationalNumber fraction;			//������
	String operator;					//�����
	//���췽��
	public Element(RationalNumber fraction, String operator) {
		this.fraction = fraction;
		this.operator = operator;
	}
	//��������get/set����
	public RationalNumber getFraction() {
		return fraction;
	}
	public void setFraction(RationalNumber fraction) {
		this.fraction = fraction;
	}
	//�������get/set����
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	//����equals�������ж�����Ԫ���Ƿ���ͬ
	@Override
	public boolean equals(Object obj) {
		Element element = (Element)obj;
		if(this.fraction.equals(element) && this.operator.equals(element.operator))
			return true;
		else return false;
	}
	
	
}