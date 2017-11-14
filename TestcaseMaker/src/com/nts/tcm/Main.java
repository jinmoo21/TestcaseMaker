package com.nts.tcm;

public class Main {
	public static void main(String[] args) {
		//new Controller();
		String expression = "A|B&C|D&E&F";
		Parser parser = new Parser(expression);
		parser.parseExpression();
		MCDC mcdc = new MCDC(expression, parser.getOperandSize());
		mcdc.foo1();
		mcdc.voo();
		mcdc.foo2();
		mcdc.voo();
		mcdc.foo3();
		mcdc.voo();
	}
}
