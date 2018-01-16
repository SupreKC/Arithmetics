/******题目（也称为运算表达式）随机生成器*****/

package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.rewrite.Substitution.RewriteRuleBackReferenceElement;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

public class ExpressionRandom {
	/***定义运算符****/
	private static  String[] operator = {"+","-","*","/"};
	
	/****随机得到一个表达式，elementNum为元素个数，isFraction表示是否支持分数计算*****/
	public static Expression getExpression(int elementNum,boolean isFraction) {
		/***表达式列表***/
		List<Element> expr = new ArrayList<Element>();
		int i; 	//循环变量，分子，分母
		int nume[] = new int[elementNum];
		int demo[] = new int[elementNum];
		String[] oper = new String[elementNum];
		/***随机生成elementNum个Element(随机分数 + 运算符)，并添加到expr***/
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
			//判断三个数运算不能为负数，前两个相减不能为负数
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
		/****通过expr构造运算表达式，并返回****/
		return  new Expression(expr);
	}


	/****得到经过过滤的表达式*****/
	public static Expression getValidExpression(int elementNum,boolean isFraction) {
		Expression expr = null;
		do {
			expr = getExpression(elementNum,isFraction);
		}while(expr.getResult()== null);				//（当表达式不合法时，返回计算结果为null）
		return expr;
	}
	
	/******测试主函数，暂时没用********/
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
