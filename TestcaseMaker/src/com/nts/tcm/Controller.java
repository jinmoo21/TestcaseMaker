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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
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
	private JButton wrapInParenthesisBtn;
	private final String wrapInParenthesisBtnText = "( )";
	private final String wrapInParenthesisBtnToolTip = "선택한 표현식 양옆으로 괄호를 추가합니다.";
	private JButton showHideBtn;
	private boolean showHideFlag = false;
	private final String[] showHideBtnText = { "∨", "∧" };
	private final String[] showHideBtnToolTip = { "표를 펼칩니다.", "표를 접습니다." };
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
	private boolean valueFlag = false;
	private JPanel bottomPanel;
	private DefaultTableModel initialTable;
	private final String[] columnNames = { "Result", "TRUE", "FALSE" };
	public Object rowData[][];

	public Controller() {
		// Frame
		super("TestCase Maker");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// Wrap In Parenthesis 버튼
		wrapInParenthesisBtn = new JButton(wrapInParenthesisBtnText);
		wrapInParenthesisBtn.addActionListener(this);
		wrapInParenthesisBtn.setToolTipText(wrapInParenthesisBtnToolTip);
		centetPanel.add(wrapInParenthesisBtn);
		// ) 버튼
		showHideBtn = new JButton(showHideBtnText[0]);
		showHideBtn.addActionListener(this);
		showHideBtn.setToolTipText(showHideBtnToolTip[0]);
		centetPanel.add(showHideBtn);
		// 표 영역
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 1));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		this.add("South", bottomPanel);
		// 표
//		DefaultTableCellRenderer centerAlignment = new DefaultTableCellRenderer();
//		centerAlignment.setHorizontalAlignment(JLabel.CENTER);
		initialTable = new DefaultTableModel(rowData, columnNames);
		JTable table = new JTable(initialTable);
//		table.getColumn("Result").setCellRenderer(centerAlignment);
		table.setPreferredScrollableViewportSize(new Dimension(initialWidth, 100));
		table.getColumnModel().getColumn(0).setWidth(200);
		JScrollPane scrollPane = new JScrollPane(table);
		bottomPanel.add(scrollPane);
		bottomPanel.setVisible(Boolean.FALSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder sb = new StringBuilder(expressionField.getText());
		int beforeLength = sb.length();
		int caretPosition = expressionField.getCaretPosition();
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
			showHideFlag = Boolean.TRUE;
			bottomPanel.setVisible(Boolean.TRUE);
			showHideBtn.setText(showHideBtnText[1]);
			showHideBtn.setToolTipText(showHideBtnToolTip[1]);
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
			showHideFlag = Boolean.TRUE;
			bottomPanel.setVisible(Boolean.TRUE);
			showHideBtn.setText(showHideBtnText[1]);
			showHideBtn.setToolTipText(showHideBtnToolTip[1]);
		} else if (e.getSource().equals(andBtn)) {
			if (caretPosition == 0) {
				sb.insert(caretPosition, baseCondition);
				sb.insert(caretPosition + 1, "&");
				if (sb.length() == 2) {
					sb.append(baseCondition);
				}
			} else if ((sb.charAt(caretPosition - 1) >= 65 && sb.charAt(caretPosition - 1) < 91) || (sb.charAt(caretPosition - 1) >= 97 && sb.charAt(caretPosition - 1) < 123)) {
				sb.insert(caretPosition, "&");
				sb.insert(caretPosition + 1, baseCondition);
			}
		} else if (e.getSource().equals(orBtn)) {
			if (caretPosition == 0) {
				sb.insert(caretPosition, baseCondition);
				sb.insert(caretPosition + 1, "|");
				if (sb.length() == 2) {
					sb.append(baseCondition);
				}
			} else if ((sb.charAt(caretPosition - 1) >= 65 && sb.charAt(caretPosition - 1) < 91) || (sb.charAt(caretPosition - 1) >= 97 && sb.charAt(caretPosition - 1) < 123)) {
				sb.insert(caretPosition, "|");
				sb.insert(caretPosition + 1, baseCondition);
			}
		} else if (e.getSource().equals(wrapInParenthesisBtn)) {
			String selectedText = expressionField.getSelectedText();
			if (selectedText != null) {
				int start = expressionField.getSelectionStart();
				int end = expressionField.getSelectionEnd();
				sb.replace(start, end, "(" + selectedText + ")");
			}
		} else if (e.getSource().equals(clearBtn)) {
			sb.setLength(0);
			initialTable.setNumRows(0);
		} else if (e.getSource().equals(cancelBtn)) {
			if (caretPosition > 0) {
				sb.deleteCharAt(caretPosition - 1);
			}
		} else if (e.getSource().equals(exitBtn)) {
			System.exit(0);
		} else if (e.getSource().equals(showHideBtn)) {
			showHideFlag = !showHideFlag;
			if (showHideFlag) {
				bottomPanel.setVisible(showHideFlag);
				showHideBtn.setText(showHideBtnText[1]);
				showHideBtn.setToolTipText(showHideBtnToolTip[1]);
			} else {
				bottomPanel.setVisible(showHideFlag);
				showHideBtn.setText(showHideBtnText[0]);
				showHideBtn.setToolTipText(showHideBtnToolTip[0]);
			}
		}
		expressionField.setText(sb.toString());
		expressionField.requestFocus();
		if (sb.length() == beforeLength + 1) {
			expressionField.setCaretPosition(caretPosition + 1);
		} else if (sb.length() == beforeLength + 2) {
			expressionField.setCaretPosition(caretPosition + 2);
		} else if (sb.length() == beforeLength + 3) {
			expressionField.setCaretPosition(caretPosition + 3);
		} else if (sb.length() == beforeLength) {
			expressionField.setCaretPosition(caretPosition);
		} else if (sb.length() == beforeLength - 1) {
			expressionField.setCaretPosition(caretPosition - 1);
		} else {
			expressionField.setCaretPosition(0);
		}
	}
}
