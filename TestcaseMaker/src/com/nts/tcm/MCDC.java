package com.nts.tcm;

import java.util.Arrays;
import java.util.LinkedList;

public class MCDC {
	private String expression;
	private int operandSize;
	private String[][] oneTable;
	private String[][] zeroTable;
	private LinkedList<Item> trueQueue;
	private LinkedList<Item> falseQueue;
	
	MCDC(String expression, int operandSize) {
		this.expression = expression;
		this.operandSize = operandSize;
		oneTable = new String[this.operandSize][this.operandSize];
		zeroTable = new String[this.operandSize][this.operandSize];
	}
	
	public void voo() {
		for (int i = 0; i < operandSize; ++i) {
			for (int j = 0; j < operandSize; ++j) {
				if (oneTable[i] == null) {
					continue;
				}
				System.out.print(oneTable[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < operandSize; ++i) {
			for (int j = 0; j < operandSize; ++j) {
				if (zeroTable[i] == null) {
					continue;
				}
				System.out.print(zeroTable[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void setDiagonalValue() {
		for (int i = 0; i < operandSize; ++i) {
			oneTable[i][i] = "1";
			zeroTable[i][i] = "0";
		}
	}
	
	public void setNeutralValue() {
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
	
	public void setDistinct() {
		for (int i = 0; i < operandSize - 1; ++i) {
			for (int j = i + 1; j < operandSize; ++j) {
				if (Arrays.equals(oneTable[i], oneTable[j])) {
					oneTable[j] = null;
				}
			}
		}
		for (int i = 0; i < operandSize - 1; ++i) {
			for (int j = i + 1; j < operandSize; ++j) {
				if (Arrays.equals(zeroTable[i], zeroTable[j])) {
					zeroTable[j] = null;
				}
			}
		}
	}
	
	public LinkedList<Item> getTrueTable() {
		trueQueue = new LinkedList<Item>();
		for (int i = 0; i < operandSize; ++i) {
			if (oneTable[i] == null) {
				continue;
			}
			Item item = new Item(oneTable[i]);
			trueQueue.add(item);
		}
		return trueQueue;
	}
	
	public LinkedList<Item> getFalseTable() {
		falseQueue = new LinkedList<Item>();
		for (int i = 0; i < operandSize; ++i) {
			if (zeroTable[i] == null) {
				continue;
			}
			Item item = new Item(zeroTable[i]);
			falseQueue.add(item);
		}
		return falseQueue;
	}
}
