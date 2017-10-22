package com.nts.tcm;

import java.util.ArrayList;
import java.util.LinkedList;

public class MCC {
	private ArrayList<String> postOrder;
	private int row;
	private int col;
	private String[][] conditions;
	private String[][] result;
//	private LinkedList<Item> queue;
	private LinkedList<Item> trueQueue;
	private LinkedList<Item> falseQueue;
	
	MCC(ArrayList<String> postOrder, int operandSize) {
		this.postOrder = postOrder;
		row = (int) Math.pow(2, operandSize);
		col = operandSize;
		conditions = new String[row][col];
		result = new String[row][1];
//		queue = new LinkedList<Item>();
		trueQueue = new LinkedList<Item>();
		falseQueue = new LinkedList<Item>();
	}
	
	private boolean isOperator(String token) {
		if (token.equals("&") || token.equals("|") || token.equals("(") || token.equals(")")) {
			return true;
		}
		return false;
	}
	
	public String[][] getConditions() {
		for (int i = 0; i < row; ++i) {
			String intToBin = Integer.toBinaryString(i);
			while (intToBin.length() != col) {
				intToBin = "0".concat(intToBin);
			}
			for (int j = 0; j < col; ++j) {
				String temp = intToBin.substring(j, j + 1);
				conditions[i][j] = temp;
			}
		}
		return conditions;
	}
	
	public String[][] getResult() {
		for (int i = 0; i < row; ++i) {
			ArrayList<String> temp = new ArrayList<>(postOrder);
			for (int j = 0, k = 0; j < temp.size() && k < col; ++j) {
				if (isOperator(temp.get(j))) {
					continue;
				}
				temp.set(j, conditions[i][k]);
				k++;
			}
			for (int m = 0; temp.size() != 1; ++m) {
				if (isOperator(temp.get(m))) {
					boolean first = false;
					if (temp.get(m-2).equals("1")) {
						first = true;
					}
					boolean second = false;
					if (temp.get(m-1).equals("1")) {
						second = true;
					}
					String boolToStr = "0";
					if (temp.get(m).equals("&")) {
						if (first & second == true) {
							boolToStr = "1";
						}
					} else {
						if (first | second == true) {
							boolToStr = "1";
						}
					}
					temp.set(m, boolToStr);
					temp.remove(m-2);
					temp.remove(m-2);
					m = -1;
				}
			}
			result[i][0] = temp.remove(0);
		}
		return result;
	}
/*	
	public void setMCC(String[][] conditions, String[][] result) {
		for (int i = 0; i < row; ++i) {
			Item item = new Item(conditions[i]);
			item.setResult(result[i][0]);
			queue.add(item);
		}
	}
*/	
	public void setTable(String[][] conditions, String[][] result) {
		for (int i = 0; i < row; ++i) {
			Item item = new Item(conditions[i]);
			if (result[i][0].equals("1")) {
				trueQueue.add(item);
			} else {
				falseQueue.add(item);
			}
		}
	}
/*	
	public LinkedList<Item> getMCC() {
		return queue;
	}
*/	
	public LinkedList<Item> getTrueTable() {
		return trueQueue;
	}
	
	public LinkedList<Item> getFalseTable() {
		return falseQueue;
	}
	
/*	public void showMCC() {
		for (Item item : queue) {
			System.out.println(item.toString());
		}
	}
*/	
	public void showTrueTable() {
		for (Item item : trueQueue) {
			System.out.println(item.toString());
		}
	}
	
	public void showFalseTable() {
		for (Item item : falseQueue) {
			System.out.println(item.toString());
		}
	}
}
