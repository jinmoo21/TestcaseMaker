package com.nts.tcm;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
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
	private final int minWidth = 500;
	private final int minHeight = 650;
	private final int initialWidth = 500;
	private final int initialHeight = 650;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private final String fileMenuText = "파일";
	private JMenuItem saveAsBtn;
	private final String saveAsText = "저장";
	private JMenuItem exitBtn;
	private final String exitText = "종료";
	private JPanel topPanel;
	private JTextField expressionField;
	private final String expressionToolTip = "표현식을 입력하거나 버튼을 눌러 표현식을 작성하세요.";
	private char baseCondition = 'A';
	private JPanel centetPanel;
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
	private final String cancelBtnText = "BACKSPACE";
	private final String cancelBtnTollTip = "지우기";
	private JButton mccBtn;
	private final String mccBtnText = "MCC";
	private final String mccBtnTollTip = "표현식에 대한 MCC 을 구합니다.";
	private JButton mcdcBtn;
	private final String mcdcBtnText = "MC/DC";
	private final String mcdcBtnTollTip = "표현식에 대한 MC/DC 을 구합니다.";
	private JPanel bottomPanel;
	private DefaultTableModel initialTable;
	private final String[] columnNames = { "1", "0" };
	public Object rowData[][];

	public Controller() {
		// Frame
		super("TestCase Maker");
		setMinimumSize(new Dimension(minWidth, minHeight));
		setBounds(initialXPosition, initialYPosition, initialWidth, initialHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 메뉴바
		menuBar = new JMenuBar();
		fileMenu = new JMenu(fileMenuText);
		saveAsBtn = new JMenuItem(saveAsText);
		saveAsBtn.addActionListener(this);
		fileMenu.add(saveAsBtn);
		fileMenu.addSeparator();
		exitBtn = new JMenuItem(exitText);
		exitBtn.addActionListener(this);
		fileMenu.add(exitBtn);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		// 텍스트 영역
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 1));
		topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		this.add("North", topPanel);
		// 텍스트필드
		expressionField = new JTextField();
		expressionField.setToolTipText(expressionToolTip);
		expressionField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		topPanel.add(expressionField);
		// 버튼 영역
		centetPanel = new JPanel();
		centetPanel.setLayout(new GridLayout(2, 4, 10, 10));
		centetPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		this.add("Center", centetPanel);
		// AND 버튼
		andBtn = new JButton(andBtnText);
		andBtn.addActionListener(this);
		andBtn.setToolTipText(andBtnToolTip);
		centetPanel.add(andBtn);
		// OR 버튼
		orBtn = new JButton(orBtnText);
		orBtn.addActionListener(this);
		orBtn.setToolTipText(orBtnToolTip);
		centetPanel.add(orBtn);
		// ( 버튼
		leftParenthesisBtn = new JButton(leftParenthesisBtnText);
		leftParenthesisBtn.addActionListener(this);
		leftParenthesisBtn.setToolTipText(leftParenthesisBtnToolTip);
		centetPanel.add(leftParenthesisBtn);
		// ) 버튼
		rightParenthesisBtn = new JButton(rightParenthesisBtnText);
		rightParenthesisBtn.addActionListener(this);
		rightParenthesisBtn.setToolTipText(rightParenthesisBtnToolTip);
		centetPanel.add(rightParenthesisBtn);
		// 초기화 버튼
		clearBtn = new JButton(clearBtnText);
		clearBtn.addActionListener(this);
		clearBtn.setToolTipText(clearBtnTollTip);
		centetPanel.add(clearBtn);
		// 지우기 버튼
		cancelBtn = new JButton(cancelBtnText);
		cancelBtn.addActionListener(this);
		cancelBtn.setToolTipText(cancelBtnTollTip);
		centetPanel.add(cancelBtn);
		// MCC 버튼
		mccBtn = new JButton(mccBtnText);
		mccBtn.addActionListener(this);
		mccBtn.setToolTipText(mccBtnTollTip);
		centetPanel.add(mccBtn);
		// MC/DC 버튼
		mcdcBtn = new JButton(mcdcBtnText);
		mcdcBtn.addActionListener(this);
		mcdcBtn.setToolTipText(mcdcBtnTollTip);
		centetPanel.add(mcdcBtn);
		// 표 영역
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 1));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		bottomPanel.setSize(new Dimension(100, 100));
		this.add("South", bottomPanel);
		// 표
		initialTable = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(initialTable);
		JScrollPane scrollPane = new JScrollPane(table);
		bottomPanel.add(scrollPane);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder sb = new StringBuilder(expressionField.getText());
		if (e.getSource().equals(mccBtn) && sb.length() != 0) {
			initialTable.setNumRows(0);
			Parser parser = new Parser(expressionField.getText());
			parser.parseExpression();
			MCC mcc =  new MCC(parser.getPostOrder(), parser.getOperandSize());
			mcc.setTable(mcc.getConditions(), mcc.getResult());
			int size = mcc.getTrueTable().size() >= mcc.getFalseTable().size() ? mcc.getTrueTable().size() : mcc.getFalseTable().size();
			for (int i = 0; i < size; ++i) {
				Vector<Item> v = new Vector<Item>();
				try {
					v.addElement(mcc.getTrueTable().get(i));
				} catch (IndexOutOfBoundsException e1) {
					v.add(null);
				}
				try {
					v.addElement(mcc.getFalseTable().get(i));
				} catch (IndexOutOfBoundsException e1) {
					v.add(null);
				}
				initialTable.addRow(v);
			}
		} else if (e.getSource().equals(mcdcBtn) && sb.length() != 0) {
			initialTable.setNumRows(0);
			Parser parser = new Parser(expressionField.getText());
			parser.removeParenthesis();
			parser.parseExpression();
			MCDC mcdc = new MCDC(parser.getExpression(), parser.getOperandSize());
			mcdc.setDiagonalValue();
			mcdc.setNeutralValue();
			mcdc.setDistinct();
			int size = mcdc.getTrueTable().size() >= mcdc.getFalseTable().size() ? mcdc.getTrueTable().size() : mcdc.getFalseTable().size();
			for (int i = 0; i < size; ++i) {
				Vector<Item> v = new Vector<Item>();
				try {
					v.addElement(mcdc.getTrueTable().get(i));
				} catch (IndexOutOfBoundsException e1) {
					v.add(null);
				}
				try {
					v.addElement(mcdc.getFalseTable().get(i));
				} catch (IndexOutOfBoundsException e1) {
					v.add(null);
				}
				initialTable.addRow(v);
			}
		} else if (e.getSource().equals(andBtn)) {
			if (sb.length() == 0) {
				sb.append(baseCondition);
			}
			sb.append("&");
			sb.append(baseCondition);
		} else if (e.getSource().equals(orBtn)) {
			if (sb.length() == 0) {
				sb.append(baseCondition);
			}
			sb.append("|");
			sb.append(baseCondition);
		} else if (e.getSource().equals(leftParenthesisBtn)) {
			sb.append("(");
		} else if (e.getSource().equals(rightParenthesisBtn)) {
			sb.append(")");
		} else if (e.getSource().equals(clearBtn)) {
			sb.delete(0, sb.length());
			initialTable.setNumRows(0);
		} else if (e.getSource().equals(cancelBtn)) {
			if (sb.length() != 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		} else if (e.getSource().equals(exitBtn)) {
			System.exit(0);
		}
		expressionField.setText(sb.toString());
	}
}
