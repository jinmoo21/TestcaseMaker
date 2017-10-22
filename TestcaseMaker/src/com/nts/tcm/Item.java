package com.nts.tcm;

public class Item {
	private String[] condition;
	private String result;
	
	Item(String[] condition) {
		this.condition = condition;
		this.result = null;
	}
	
	public String[] getCondition() {
		return condition;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < condition.length; ++i) {
			sb = sb.append(condition[i]);
		}
		return sb.toString();
	}
}
