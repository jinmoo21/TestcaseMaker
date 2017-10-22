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
	private static final String andBtnToolTip = "AND 연산을 추가합니다.";
	private static final String orBtnText = "OR";
	private static final String orBtnToolTip = "OR 연산을 추가합니다.";
	private static final String leftParenthesisBtnText = "(";
	private static final String leftParenthesisBtnToolTip = "( 을 추가합니다.";
	private static final String rightParenthesisBtnText = ")";
	private static final String rightParenthesisBtnToolTip = ") 을 추가합니다.";
	private static final String clearBtnText = "CLEAR";
	private static final String clearBtnTollTip = "작성한 표현식을 초기화합니다.";
	private static final String cancelBtnText = "x";
	private static final String cancelBtnTollTip = "지우기";
	private static final String mccBtnText = "MCC";
	private static final String mccBtnTollTip = "표현식에 대한 MCC를 구합니다.";
	private static final String mcdcBtnText = "MC/DC";
	private static final String mcdcBtnTollTip = "표현식에 대한 MC/DC를 구합니다.";
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
		// 텍스트 영역
		JTextField expressionField = new JTextField(4);
		this.add(expressionField);
		// AND 버튼
		JButton andBtn = new JButton(andBtnText);
		andBtn.setToolTipText(andBtnToolTip);
		this.add(andBtn);
		// OR 버튼
		JButton orBtn = new JButton(orBtnText);
		orBtn.setToolTipText(orBtnToolTip);
		this.add(orBtn);
		// ( 버튼
		JButton leftParenthesisBtn = new JButton(leftParenthesisBtnText);
		leftParenthesisBtn.setToolTipText(leftParenthesisBtnToolTip);
		this.add(leftParenthesisBtn);
		// ) 버튼
		JButton rightParenthesisBtn = new JButton(rightParenthesisBtnText);
		rightParenthesisBtn.setToolTipText(rightParenthesisBtnToolTip);
		this.add(rightParenthesisBtn);
		// 초기화 버튼
		JButton clearBtn = new JButton(clearBtnText);
		clearBtn.setToolTipText(clearBtnTollTip);
		this.add(clearBtn);
		// 지우기 버튼
		JButton cancelBtn = new JButton(cancelBtnText);
		cancelBtn.setToolTipText(cancelBtnTollTip);
		this.add(cancelBtn);
		// MCC 버튼
		JButton mccBtn = new JButton(mccBtnText);
		mccBtn.setToolTipText(mccBtnTollTip);
		this.add(mccBtn);
		// MC/DC 버튼
		JButton mcdcBtn = new JButton(mcdcBtnText);
		mcdcBtn.setToolTipText(mcdcBtnTollTip);
		this.add(mcdcBtn);
		// 표
		DefaultTableModel initialTable = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(initialTable);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
		setVisible(true);
	}
}
