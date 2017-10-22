package com.nts.tcm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Controller extends JFrame implements ActionListener {
	private static final long serialVersionUID = 4349430279354340298L;
	private final int initialXPosition = 100;
	private final int initialYPosition = 100;
	private final int minWidth = 300;
	private final int minHeight = 600;
	private final int initialWidth = 300;
	private final int initialHeight = 600;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private final String fileMenuText = "파일";
	private JTextField expressionField;
	private final String expressionToolTip = "표현식을 입력하거나 버튼을 눌러 표현식을 작성하세요.";
	private char baseCondition = 'A';
	private JPanel panel;
	private JButton andBtn;
	private final String andBtnText = "AND";
	private final String andBtnToolTip = "AND 연산을 추가합니다.";
	private JButton orBtn;
	private final String orBtnText = "OR";
	private final String orBtnToolTip = "OR 연산을 추가합니다..";
	private JButton leftParenthesisBtn;
	private final String leftParenthesisBtnText = "(";
	private final String leftParenthesisBtnToolTip = "( 을 추가합니다.";
	private JButton rightParenthesisBtn;
	private final String rightParenthesisBtnText = ")";
	private final String rightParenthesisBtnToolTip = ") 을 추가합니다.";
	private JButton clearBtn;
	private final String clearBtnText = "CLEAR";
	private final String clearBtnTollTip = "작성한 표현식을 초기화합니다.";
	private JButton cancelBtn;
	private final String cancelBtnText = "x";
	private final String cancelBtnTollTip = "지우기";
	private JButton mccBtn;
	private final String mccBtnText = "MCC";
	private final String mccBtnTollTip = "표현식에 대한 MCC 을 구합니다.";
	private JButton mcdcBtn;
	private final String mcdcBtnText = "MC/DC";
	private final String mcdcBtnTollTip = "표현식에 대한 MC/DC 을 구합니다.";
	private DefaultTableModel initialTable;
	private final String[] columnNames = { "1", "0" };
	public Object rowData[][];
	private boolean isMCCCalculated;

	public Controller() {
		// Frame
		super("TestCase Maker");
		setMinimumSize(new Dimension(minWidth, minHeight));
		setBounds(initialXPosition, initialYPosition, initialWidth, initialHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 메뉴바
		menuBar = new JMenuBar();
		fileMenu = new JMenu(fileMenuText);
		fileMenu.add(new JMenuItem("저장"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("종료"));
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		// 텍스트 영역
		JPanel a = new JPanel();
		a.setLayout(new GridLayout(1, 1));
		a.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		this.add("North", a);
		expressionField = new JTextField();
		expressionField.setToolTipText(expressionToolTip);
		a.add(expressionField);
		// 버튼 영역
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 4, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		this.add("Center", panel);
		// AND 버튼
		andBtn = new JButton(andBtnText);
		andBtn.addActionListener(this);
		andBtn.setToolTipText(andBtnToolTip);
		panel.add(andBtn);
		// OR 버튼
		orBtn = new JButton(orBtnText);
		orBtn.addActionListener(this);
		orBtn.setToolTipText(orBtnToolTip);
		panel.add(orBtn);
		// ( 버튼
		leftParenthesisBtn = new JButton(leftParenthesisBtnText);
		leftParenthesisBtn.addActionListener(this);
		leftParenthesisBtn.setToolTipText(leftParenthesisBtnToolTip);
		panel.add(leftParenthesisBtn);
		// ) 버튼
		rightParenthesisBtn = new JButton(rightParenthesisBtnText);
		rightParenthesisBtn.addActionListener(this);
		rightParenthesisBtn.setToolTipText(rightParenthesisBtnToolTip);
		panel.add(rightParenthesisBtn);
		// 초기화 버튼
		clearBtn = new JButton(clearBtnText);
		clearBtn.addActionListener(this);
		clearBtn.setToolTipText(clearBtnTollTip);
		panel.add(clearBtn);
		// 지우기 버튼
		cancelBtn = new JButton(cancelBtnText);
		cancelBtn.addActionListener(this);
		cancelBtn.setToolTipText(cancelBtnTollTip);
		panel.add(cancelBtn);
		// MCC 버튼
		mccBtn = new JButton(mccBtnText);
		mccBtn.addActionListener(this);
		mccBtn.setToolTipText(mccBtnTollTip);
		panel.add(mccBtn);
		// MC/DC 버튼
		mcdcBtn = new JButton(mcdcBtnText);
		mcdcBtn.addActionListener(this);
		mcdcBtn.addActionListener(this);
		mcdcBtn.setToolTipText(mcdcBtnTollTip);
		panel.add(mcdcBtn);
		// 표
		JPanel b = new JPanel();
		b.setLayout(new GridLayout(1, 1));
		b.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		this.add("South", b);
		initialTable = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(initialTable);
		JScrollPane scrollPane = new JScrollPane(table);
		b.add(scrollPane);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder sb = new StringBuilder(expressionField.getText());
		if (!isMCCCalculated && e.getSource().equals(mccBtn)) {
			Parser b = new Parser(expressionField.getText());
			b.parseExpression();
			MCC a =  new MCC(b.getPostOrder(), b.getOperandSize());
			a.setTable(a.getConditions(), a.getResult());
			int size = a.getTrueTable().size() >= a.getFalseTable().size() ? a.getTrueTable().size() : a.getFalseTable().size();
			for (int i = 0; i < size; ++i) {
				Vector<Item> v = new Vector<Item>();
				try {
					v.addElement(a.getTrueTable().get(i));
				} catch (IndexOutOfBoundsException e1) {
					v.add(null);
				}
				try {
					v.addElement(a.getFalseTable().get(i));
				} catch (IndexOutOfBoundsException e1) {
					v.add(null);
				}
				initialTable.addRow(v);
			}
			isMCCCalculated = true;
		} else if (e.getSource().equals(mcdcBtn)) {
			
		} else if (e.getSource().equals(andBtn)) {
			if (sb.length() == 0) {
				sb.append(baseCondition);
			}
			sb.append("&");
			sb.append(++baseCondition);
		} else if (e.getSource().equals(orBtn)) {
			if (sb.length() == 0) {
				sb.append(baseCondition);
			}
			sb.append("|");
			sb.append(++baseCondition);
		} else if (e.getSource().equals(leftParenthesisBtn)) {
			sb.append("(");
		} else if (e.getSource().equals(rightParenthesisBtn)) {
			sb.append(")");
		} else if (e.getSource().equals(clearBtn)) {
			sb.delete(0, sb.length());
		} else if (e.getSource().equals(cancelBtn)) {
			if (sb.length() != 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		expressionField.setText(sb.toString());
	}
}
