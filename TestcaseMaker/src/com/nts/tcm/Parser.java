package com.nts.tcm;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
	private String expression;
	private int operandSize;
	private ArrayList<String> inOrder;
	private ArrayList<String> postOrder;

	Parser(String expression) {
		this.expression = expression;
		operandSize = 0;
		inOrder = new ArrayList<String>();
		postOrder = new ArrayList<String>();
	}

	private int order(String operand) {
		if (operand.equals("&")) {
			return 1;
		} else if (operand.equals("|")) {
			return 0;
		}
		return -1;
	}

	private boolean isOperator(String token) {
		if (token.equals("&") || token.equals("|") || token.equals("(") || token.equals(")")) {
			return true;
		}
		return false;
	}

	public void parseExpression() {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < expression.length(); ++i) {
			String str = String.valueOf(expression.charAt(i));
			if (isOperator(str)) {
				if (!temp.toString().equals("")) { 
					inOrder.add(temp.toString());
				}
				temp.delete(0, temp.length());
				inOrder.add(str);
			} else {
				temp.append(str);
				operandSize++;
			}
			if (i == expression.length() - 1 && !temp.toString().equals("")) {
				inOrder.add(temp.toString());
			}
		}
	}

	public ArrayList<String> getPostOrder() {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < inOrder.size(); ++i) {
			String str = inOrder.get(i);
			if (str.equals("(")) {
				stack.push(str);
			} else if (str.equals(")")) {
				while (!stack.peek().equals("(")) {
					postOrder.add(stack.pop());
				}
				stack.pop();
			} else if (isOperator(str)) {
				while (!stack.isEmpty() && order(str) <= order(stack.peek())) {
					if (stack.peek().equals("(")) {
						break;
					}
					postOrder.add(stack.pop());
				}
				stack.push(str);
			} else {
				postOrder.add(str);
			}
		}
		while (!stack.isEmpty()) {
			postOrder.add(stack.pop());
		}
		return postOrder;
	}

	public int getOperandSize() {
		return operandSize;
	}
	
	public void removeParenthesis() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < expression.length(); ++i) {
			if (expression.substring(i, i + 1).equals("(")) {
				/*System.out.println(i + "( 시작");
				a.add(i + 1);
				while (expression.substring(1, 2).equals(")")) {
					
				}
				System.out.println(i + 1 + "을 큐에 저장");*/
			} else if (expression.substring(i, i + 1).equals(")")) {
				// System.out.println(i + ") 끝");
			} else {
				sb.append(expression.substring(i, i + 1));
			}
		}
		expression = sb.toString();
	}
	
	public String getExpression() {
		return expression;
	}
}
