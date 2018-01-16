/******��Ŀ��Ҳ��Ϊ������ʽ�����������*****/

package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.rewrite.Substitution.RewriteRuleBackReferenceElement;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

public class ExpressionRandom {
	/***���������****/
	private static  String[] operator = {"+","-","*","/"};
	
	/****����õ�һ�����ʽ��elementNumΪԪ�ظ�����isFraction��ʾ�Ƿ�֧�ַ�������*****/
	public static Expression getExpression(int elementNum,boolean isFraction) {
		/***���ʽ�б�***/
		List<Element> expr = new ArrayList<Element>();
		int i; 	//ѭ�����������ӣ���ĸ
		int nume[] = new int[elementNum];
		int demo[] = new int[elementNum];
		String[] oper = new String[elementNum];
		/***�������elementNum��Element(������� + �����)������ӵ�expr***/
		if(!isFraction)
		{
			for(i = 0;i < elementNum ;i ++)
			{
				demo[i] = 1;
			}
		}
		else
		{
			for(i = 0;i < elementNum ;i ++)
			{
				demo[i] = (int)(Math.random()*100+1);
			}
		}
		int a = 0,b=0,c=0,d=0;
		for(i = 0;i < elementNum; i++)
		{
			nume[i] = (int)(Math.random()*101);
			oper[i] = operator[(int)(Math.random() * 4)];
		}
		if(elementNum == 2)
		{
			if(oper[0].equals("*"))
			{
				if(nume[0]>9||nume[0]==0)
				{
					nume[0] = (int)(Math.random()*9+1);
				}
				if(nume[1]==0||nume[1]>9)
				{
					nume[1] = (int)(Math.random()*9+1);
				}
			}
			if(oper[0].equals("/"))
			{
				while(true)
				{
					if((nume[1]!=0)&&(nume[0]<=81)&&(nume[1]<=9)&&(nume[0]%nume[1]==0)&&(nume[0]/nume[1]<=9))
					{
						break;
					}
					else
					{
						nume[0] = (int)(Math.random()*81+1);
						nume[1] = (int)(Math.random()*9+1);
					}
				}
			}
			if(oper[0].equals("-"))
			{
				int exchange=0;
				if(nume[0]<nume[1])
				{
					exchange=nume[0];
					nume[0]=nume[1];
					nume[1]=exchange;
				}
				
			}
			if(oper[0].equals("+"))
			{
				while(nume[0]+nume[1]>100)
				{
					for(i = 0;i < elementNum; i++)
					{
						nume[i] = (int)(Math.random()*101);
					}
				}
					
			}
		}
		if(elementNum == 3)
		{
			
			for(i = 0;i<elementNum-1;i++)
			{
				if(oper[i].equals("*")||oper[i].equals("/"))
				{
					oper[i] = operator[(int)(Math.random()*2)];
				}
			}
			//�ж����������㲻��Ϊ������ǰ�����������Ϊ����
			if(oper[0].equals("+")&&oper[1].equals("-"))
			{
				while(true)
				{			
						if((nume[0]+nume[1]-nume[2]<0)||(nume[0]+nume[1]-nume[2]>100)||(nume[0]+nume[1]>100))
						{
							for(i = 0;i < elementNum; i++)
							{
								nume[i] = (int)(Math.random()*101);
							}
							
						}
						else
						{
							break;
						}				
				}
					
				
			}
			
			
			else if(oper[0].equals("-")&&oper[1].equals("-"))
			{
				
				while(true)
				{			
						if((nume[0]-nume[1]-nume[2]<0)||(nume[0]<nume[1]))
						{
							for(i = 0;i < elementNum; i++)
							{
								nume[i] = (int)(Math.random()*101);
							}
							
						}
						else
						{
							break;
						}				
				}
					
				
			}
			else if(oper[0].equals("-")&&oper[1].equals("+"))
			{
				while(true)
				{			
						if((nume[0]-nume[1]+nume[2]<0)||(nume[0]<nume[1])||(nume[0]-nume[1]+nume[2]>100))
						{
							for(i = 0;i < elementNum; i++)
							{
								nume[i] = (int)(Math.random()*101);
							}
							
						}
						else
						{
							break;
						}				
				}
					
				
			}
			else if(oper[0].equals("+")&&oper[1].equals("+"))
			{
				while(true)
				{			
						if(nume[0]+nume[1]+nume[2]>100)
						{
							for(i = 0;i < elementNum; i++)
							{
								nume[i] = (int)(Math.random()*101);
							}
							
						}
						else
						{
							break;
						}				
				}
				
			}
			
		}
		for(i = 0;i<elementNum;i++)
		{
			RationalNumber rb = new RationalNumber(nume[i],demo[i]);
			rb.simply();
			expr.add(new Element(rb, oper[i]));
		}
		expr.get(i-1).setOperator("#");
		/****ͨ��expr����������ʽ��������****/
		return  new Expression(expr);
	}


	/****�õ��������˵ı��ʽ*****/
	public static Expression getValidExpression(int elementNum,boolean isFraction) {
		Expression expr = null;
		do {
			expr = getExpression(elementNum,isFraction);
		}while(expr.getResult()== null);				//�������ʽ���Ϸ�ʱ�����ؼ�����Ϊnull��
		return expr;
	}
	
	/******��������������ʱû��********/
	public static void main(String[] args) {
		/*for(int i = 0;i < 100;) {
			Expression expr = ExpressionRandom.getExpression(2, true);
			if(expr.getResult() != null) {
				System.out.println(expr.toString() + "=" + expr.getResult() + "\n");
				i++;
			}
		}*/
	}
}
