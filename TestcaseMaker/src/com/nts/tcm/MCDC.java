package com.nts.tcm;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MCDC {
	private String expression;
	private int operandSize = 2;
	private String[][] oneTable;
	private String[][] zeroTable;
	
	Set<String[]> oneSet;
	Set<String[]> zeroSet;
	
	MCDC(String expression) {
		this.expression = expression; 
		oneTable = new String[this.operandSize][this.operandSize];
		zeroTable = new String[this.operandSize][this.operandSize];
	}
	
	private boolean isOperator(String token) {
		if (token.equals("&") || token.equals("|") || token.equals("(") || token.equals(")")) {
			return true;
		}
		return false;
	}
	
	public void voo() {
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
		System.out.println();
	}
	
	public void foo1() {
		for (int i = 0; i < operandSize; ++i) {
			oneTable[i][i] = "1";
			zeroTable[i][i] = "0";
		}
	}
	
	public void foo2() {
		for (int i = 0; i < operandSize; ++i) {
			for (int j = i - 1; j >= 0; --j) {
				int k = j * 2 + 1;
				if (expression.substring(k, k + 1).equals("&")) {
					oneTable[i][j] = "1";
					zeroTable[i][j] = "1";
				} else if (expression.substring(k, k + 1).equals("|")) {
					oneTable[i][j] = "0";
					zeroTable[i][j] = "0";
				}
			}
			for (int j = i + 1; j < operandSize; ++j) {
				int k = j * 2 - 1;
				if (expression.substring(k, k + 1).equals("&")) {
					oneTable[i][j] = "1";
					zeroTable[i][j] = "1";
				} else if (expression.substring(k, k + 1).equals("|")) {
					oneTable[i][j] = "0";
					zeroTable[i][j] = "0";
				}
			}
				
		}
	}
	
	public void foo3() {
		oneSet = new HashSet<String[]>();
		for (int i = 0; i < operandSize; ++i) {
			oneSet.add(oneTable[i]);
		}
		zeroSet = new HashSet<String[]>();
		for (int i = 0; i < operandSize; ++i) {
			zeroSet.add(zeroTable[i]);
		}
	}
	
	public void voo2() {
		for (String[] a : oneSet) {
			for (int i = 0; i < operandSize; ++i) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (String[] a : zeroSet) {
			for (int i = 0; i < operandSize; ++i) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}