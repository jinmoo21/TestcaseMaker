package com.nts.tcm;

public class Main {
	public static void main(String[] args) {
		//new Controller();
		Parser a = new Parser("A|B|C");
		a.parseExpression();
		MCDC mcdc = new MCDC(a.getPostOrder(), a.getOperandSize());
		mcdc.aaaa();
	}
}
