package com.nts.tcm;

import java.util.ArrayList;

public class MCDC {
	private ArrayList<String> postOrder;
	private int operandSize;
	private String[][] oneTable;
	private String[][] zeroTable;
	
	MCDC(ArrayList<String> postOrder, int operandSize) {
		this.postOrder = postOrder;
		this.operandSize = operandSize;
		oneTable = new String[this.operandSize][this.operandSize];
		zeroTable = new String[this.operandSize][this.operandSize];
	}
	
	public void aaaa() {
		for (int i = 0; i < operandSize; ++i) {
			oneTable[i][i] = "1";
			zeroTable[i][i] = "0";
		}
		
		for (int i = 0; i < operandSize; ++i) {
			for (int j = 0; j < operandSize; ++j) {
				System.out.print(oneTable[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < operandSize; ++i) {
			for (int j = 0; j < operandSize; ++j) {
				System.out.print(zeroTable[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	private boolean isOperator(String token) {
		if (token.equals("&") || token.equals("|") || token.equals("(") || token.equals(")")) {
			return true;
		}
		return false;
	}
	
	public void getResult() {
		for (int i = 0; i < operandSize; ++i) {
			ArrayList<String> temp = new ArrayList<>(postOrder);
			for (int j = 0, k = 0; j < temp.size() && k < operandSize; ++j) {
				if (isOperator(temp.get(j))) {
					continue;
				}
				temp.set(j, oneTable[i][k]);
				k++;
			}
			for (int m = 0; temp.size() != 1; ++m) {
				if (temp.get(m).equals("&")) {
					// 하나가 null 이면 null 을 1으로
					if (temp.get(m - 2) == null) {
						temp.set(m - 2, "1");
					}
				} else if (temp.get(m).equals("|")) {
					// 하나가 null 이면 null 을 0으로
					
				}
			}
		}
	}
}
