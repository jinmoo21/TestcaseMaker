package com.nts.tcm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controller extends JFrame implements ActionListener {
	private static final long serialVersionUID = 4349430279354340298L;
	private final static String title = "MCC MC/DC 계산기";
	private final int initialXPosition = 100;
	private final int initialYPosition = 100;
	private final int initialWidth = 330;
	private final int initialHeight = 190;
	private final int btnWidth = 75;
	private final int btnHeight = 35;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private final String fileMenuText = "파일";
	private JMenuItem saveAsBtn;
	private final String saveAsText = "저장";
//	private FileDialog saveDialog;
	private JMenuItem exitBtn;
	private final String exitText = "종료";
	private JPanel topPanel;
	private JTextField expressionField;
	private final int longWidth = 315;
	private final int expressionFieldHeight = 50; 
	private final String expressionToolTip = "표현식을 입력하거나 버튼을 눌러 표현식을 작성하세요.";
	private char baseCondition = 'A';
	private JPanel centerPanel;
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
	private final int showHideBtnWidth = 45;
	private final int showHideBtnHeight = 25;
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
	private DefaultTableModel initialTable;
	private JTable table;
	public static final String[] columnNames = { "TRUE", "FALSE" };
	private JScrollPane scrollPane;
	private final int scrollPaneHeight = 150;
	public Object rowData[][];

	public Controller() {
		// Frame
		super(title);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(null);
		setResizable(Boolean.FALSE);
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
//		this.add("North", topPanel);
		// 텍스트필드
		expressionField = new JTextField() {
			
		};
		expressionField.setToolTipText(expressionToolTip);
		expressionField.setFont(new Font("arian", Font.BOLD, 20));
		expressionField.setBounds(5, 5, longWidth, expressionFieldHeight);
		this.add(expressionField);
//		topPanel.add(expressionField);
		// 버튼 영역
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 4, 10, 10));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//		this.add("Center", centerPanel);
		// MCC 버튼
		mccBtn = new JButton(mccBtnText);
		mccBtn.setBounds(5, 60, btnWidth, btnHeight);
		mccBtn.addActionListener(this);
		mccBtn.setToolTipText(mccBtnTollTip);
		this.add(mccBtn);
//		centerPanel.add(mccBtn);
		// MC/DC 버튼
		mcdcBtn = new JButton(mcdcBtnText);
		mcdcBtn.setBounds(85, 60, btnWidth, btnHeight);
		mcdcBtn.addActionListener(this);
		mcdcBtn.setToolTipText(mcdcBtnTollTip);
		this.add(mcdcBtn);
//		centerPanel.add(mcdcBtn);
		// 초기화 버튼
		clearBtn = new JButton(clearBtnText);
		clearBtn.setBounds(165, 60, btnWidth, btnHeight);
		clearBtn.addActionListener(this);
		clearBtn.setToolTipText(clearBtnTollTip);
		this.add(clearBtn);
//		centerPanel.add(clearBtn);
		// 지우기 버튼
		cancelBtn = new JButton(cancelBtnText);
		cancelBtn.setBounds(245, 60, btnWidth, btnHeight);
		cancelBtn.addActionListener(this);
		cancelBtn.setToolTipText(cancelBtnTollTip);
		this.add(cancelBtn);
//		centerPanel.add(cancelBtn);
		// AND 버튼
		andBtn = new JButton(andBtnText);
		andBtn.setBounds(5, 100, btnWidth, btnHeight);
		andBtn.addActionListener(this);
		andBtn.setToolTipText(andBtnToolTip);
		this.add(andBtn);
//		centerPanel.add(andBtn);
		// OR 버튼
		orBtn = new JButton(orBtnText);
		orBtn.setBounds(85, 100, btnWidth, btnHeight);
		orBtn.addActionListener(this);
		orBtn.setToolTipText(orBtnToolTip);
		this.add(orBtn);
//		centerPanel.add(orBtn);
		// Wrap In Parenthesis 버튼
		wrapInParenthesisBtn = new JButton(wrapInParenthesisBtnText);
		wrapInParenthesisBtn.setBounds(165, 100, btnWidth, btnHeight);
		wrapInParenthesisBtn.addActionListener(this);
		wrapInParenthesisBtn.setToolTipText(wrapInParenthesisBtnToolTip);
		this.add(wrapInParenthesisBtn);
//		centerPanel.add(wrapInParenthesisBtn);
		// ShowHide 버튼
		showHideBtn = new JButton(showHideBtnText[0]);
		showHideBtn.setBounds(275, 110, showHideBtnWidth, showHideBtnHeight);
		showHideBtn.addActionListener(this);
		showHideBtn.setToolTipText(showHideBtnToolTip[0]);
		this.add(showHideBtn);
//		centerPanel.add(showHideBtn);
		// 표 영역
//		bottomPanel = new JPanel();
//		bottomPanel.setBounds(5, 400, expressionFieldWidth, 300);
//		this.add(bottomPanel);
		// 표
		DefaultTableCellRenderer centerAlignment = new DefaultTableCellRenderer();
		centerAlignment.setHorizontalAlignment(SwingConstants.CENTER);
		initialTable = new DefaultTableModel(rowData, columnNames);
		table = new JTable(initialTable);
		table.getTableHeader().setDefaultRenderer(centerAlignment);
		table.getTableHeader().setReorderingAllowed(Boolean.FALSE);
		table.getTableHeader().setResizingAllowed(Boolean.FALSE);
		table.setRowHeight(25);
		table.setEnabled(Boolean.FALSE);
//		table.getColumn("Result").setCellRenderer(centerAlignment);
//		table.setPreferredScrollableViewportSize(new Dimension(expressionFieldWidth, 300));
//		table.getColumnModel().getColumn(0).setWidth(200);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 140, longWidth, scrollPaneHeight);
		scrollPane.setVisible(Boolean.FALSE);
		this.add(scrollPane);
//		bottomPanel.add(scrollPane);
//		bottomPanel.setVisible(Boolean.FALSE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			if (parser.getOperandSize() > 10) {
				JOptionPane.showMessageDialog(null, "표현식의 조건을 10개 이하로 입력해주세요.", title, JOptionPane.WARNING_MESSAGE);
			} else {
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
				showHideFlag = true;
				setBounds(getX(),getY(), initialWidth, initialHeight + scrollPaneHeight);
				scrollPane.setVisible(true);
				showHideBtn.setText(showHideBtnText[1]);
				showHideBtn.setToolTipText(showHideBtnToolTip[1]);
			}
		} else if (e.getSource().equals(mcdcBtn) && sb.length() != 0) {
			initialTable.setNumRows(0);
			Parser parser = new Parser(expressionField.getText());
			parser.removeParenthesis();
			parser.parseExpression();
			if (parser.getOperandSize() > 10) {
				JOptionPane.showMessageDialog(this, "표현식의 조건을 10개 이하로 입력해주세요.", title, JOptionPane.WARNING_MESSAGE);
			} else {
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
				showHideFlag = true;
				setBounds(getX(),getY(), initialWidth, initialHeight + scrollPaneHeight);
				scrollPane.setVisible(true);
				showHideBtn.setText(showHideBtnText[1]);
				showHideBtn.setToolTipText(showHideBtnToolTip[1]);
				
				
				for (int i = 0; i < table.getRowCount(); ++i) {
					for (int j = 0; j < table.getRowCount(); ++j) {
						System.out.println(table.getValueAt(i, 0) != null ? table.getValueAt(i, 0) : "");
						System.out.println(table.getValueAt(j, 1) != null ? table.getValueAt(j, 1) : "");
					}
				}
			}
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
				setBounds(getX(),getY(), initialWidth, initialHeight + scrollPaneHeight);
				scrollPane.setVisible(showHideFlag);
				showHideBtn.setText(showHideBtnText[1]);
				showHideBtn.setToolTipText(showHideBtnToolTip[1]);
			} else {
				setBounds(getX(),getY(), initialWidth, initialHeight);
				scrollPane.setVisible(showHideFlag);
				showHideBtn.setText(showHideBtnText[0]);
				showHideBtn.setToolTipText(showHideBtnToolTip[0]);
			}
		} else if (e.getSource().equals(saveAsBtn)) {
//			saveDialog = new FileDialog(this, "저장", FileDialog.SAVE);
//			saveDialog.setVisible(true);
			JFileChooser jfc = new JFileChooser();
			jfc.setCurrentDirectory(new File("/"));
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setDialogTitle("저장");
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setSelectedFile(new File("제목 없음.xlsx"));
			jfc.setFileFilter(new FileNameExtensionFilter("Excel 통합 문서 (*.xlsx)", "xlsx"));
			int val = jfc.showSaveDialog(null);
			// while (val != JFileChooser.CANCEL_OPTION || true) {
			if (val == JFileChooser.APPROVE_OPTION) {
				String path = jfc.getSelectedFile().toString();
					/*if (path.exists()) {
						int val2 = JOptionPane.showConfirmDialog(this, path + "이 (가) 이미 있습니다.\n바꾸시겠습니까?", "다른 이름으로 저장 확인", JOptionPane.YES_NO_OPTION);
						if (val2 == JOptionPane.YES_OPTION) {
							Save.toXLSXFile(table, path);
							break;
						}
					} else {*/
				Save.toXLSXFile(table, path);
					/*	break;
					}*/
			}
			// }
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
