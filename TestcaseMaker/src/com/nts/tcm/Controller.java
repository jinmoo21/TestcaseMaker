package com.nts.tcm;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controller extends JFrame {
	private static final long serialVersionUID = 4349430279354340298L;
	private static final int initialXPosition = 100;
	private static final int initialYPosition = 100;
	private static final int minWidth = 250;
	private static final int minHeight = 500;
	private static final int initialWidth = 500;
	private static final int initialHeight = 750;
	private static final String andBtnText = "AND";
	private static final String andBtnToolTip = "AND ������ �߰��մϴ�.";
	private static final String orBtnText = "OR";
	private static final String orBtnToolTip = "OR ������ �߰��մϴ�.";
	private static final String leftParenthesisBtnText = "(";
	private static final String leftParenthesisBtnToolTip = "( �� �߰��մϴ�.";
	private static final String rightParenthesisBtnText = ")";
	private static final String rightParenthesisBtnToolTip = ") �� �߰��մϴ�.";
	private static final String clearBtnText = "CLEAR";
	private static final String clearBtnTollTip = "�ۼ��� ǥ������ �ʱ�ȭ�մϴ�.";
	private static final String cancelBtnText = "x";
	private static final String cancelBtnTollTip = "�����";
	private static final String mccBtnText = "MCC";
	private static final String mccBtnTollTip = "ǥ���Ŀ� ���� MCC�� ���մϴ�.";
	private static final String mcdcBtnText = "MC/DC";
	private static final String mcdcBtnTollTip = "ǥ���Ŀ� ���� MC/DC�� ���մϴ�.";
	private static final String[] columnNames = { "1", "0" };
	public Object rowData[][] = {
			{ "100", "000" },
			{ "010", "000" },
			{ "001", "000" },
			{ "100", "000" },
			{ "010", "000" },
			{ "001", "000" },
			{ "100", "000" },
			{ "010", "000" },
			{ "001", "000" },
	};
	
	public Controller() {
		// Frame
		super("TestCase Maker");
		setMinimumSize(new Dimension(minWidth, minHeight));
		setBounds(initialXPosition, initialYPosition, initialWidth, initialHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		// �ؽ�Ʈ ����
		JTextField expressionField = new JTextField(4);
		this.add(expressionField);
		// AND ��ư
		JButton andBtn = new JButton(andBtnText);
		andBtn.setToolTipText(andBtnToolTip);
		this.add(andBtn);
		// OR ��ư
		JButton orBtn = new JButton(orBtnText);
		orBtn.setToolTipText(orBtnToolTip);
		this.add(orBtn);
		// ( ��ư
		JButton leftParenthesisBtn = new JButton(leftParenthesisBtnText);
		leftParenthesisBtn.setToolTipText(leftParenthesisBtnToolTip);
		this.add(leftParenthesisBtn);
		// ) ��ư
		JButton rightParenthesisBtn = new JButton(rightParenthesisBtnText);
		rightParenthesisBtn.setToolTipText(rightParenthesisBtnToolTip);
		this.add(rightParenthesisBtn);
		// �ʱ�ȭ ��ư
		JButton clearBtn = new JButton(clearBtnText);
		clearBtn.setToolTipText(clearBtnTollTip);
		this.add(clearBtn);
		// ����� ��ư
		JButton cancelBtn = new JButton(cancelBtnText);
		cancelBtn.setToolTipText(cancelBtnTollTip);
		this.add(cancelBtn);
		// MCC ��ư
		JButton mccBtn = new JButton(mccBtnText);
		mccBtn.setToolTipText(mccBtnTollTip);
		this.add(mccBtn);
		// MC/DC ��ư
		JButton mcdcBtn = new JButton(mcdcBtnText);
		mcdcBtn.setToolTipText(mcdcBtnTollTip);
		this.add(mcdcBtn);
		// ǥ
		DefaultTableModel initialTable = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(initialTable);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		setVisible(true);
	}
}
