package javacalculator;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.*;

public class UI implements ActionListener {

   private final JFrame frame;

   private final JPanel panel;
   private final JPanel panelSub1;
   private final JPanel panelSub2;
   private final JPanel panelSub3;
   private final JPanel panelSub4;
   private final JPanel panelSub5;
   private final JPanel panelSub6;
   private final JPanel panelSub7;
   private final JPanel panelSub8;

   public final JTextArea text;
   public final JButton but[], butPoint, butAdd, butMinus, butMultiply, butDivide,
      butEqual, butCancel, butSquareRoot, butSquare, butInverse,
      butCos, butSin, butTan, butPower, butLog, butRate, butAbs, butBinary;
   private final Calculator calc;

   private final String[] buttonValue = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

   private final Font font;
   private final Font textFont;

   public UI() throws IOException {
      frame = new JFrame("Calculator");

      panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panelSub1 = new JPanel(new FlowLayout());
      panelSub2 = new JPanel(new FlowLayout());
      panelSub3 = new JPanel(new FlowLayout());
      panelSub4 = new JPanel(new FlowLayout());
      panelSub5 = new JPanel(new FlowLayout());
      panelSub6 = new JPanel(new FlowLayout());
      panelSub7 = new JPanel(new FlowLayout());
      panelSub8 = new JPanel(new FlowLayout());

      font = new Font("Consolas",Font.PLAIN, 18);

      text = new JTextArea(1, 30);

      textFont = new Font("Consolas",Font.BOLD, 24);

      but = new JButton[11];
      for (int i = 0; i < 10; i++) {
         but[i] = new JButton(String.valueOf(i));
      }
      butPoint = new JButton(".");
      butAdd = new JButton("+");
      butMinus = new JButton("-");
      butMultiply = new JButton("*");
      butDivide = new JButton("/");
      butEqual = new JButton("=");
      butSquareRoot = new JButton("sqrt");
      butSquare = new JButton("x*x");
      butInverse = new JButton("1/x");
      butCos = new JButton("cos");
      butSin = new JButton("sin");
      butTan = new JButton("tan");
      butPower = new JButton("x^y");
      butLog = new JButton("log");
      butRate = new JButton("x%");
      butAbs = new JButton("abs");
      butCancel = new JButton("C");
      butBinary = new JButton("Bin");

      calc = new Calculator();

   }

   public void init() {
      frame.setSize(450, 450);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      text.setFont(textFont);
      text.setEditable(false);

      for (int i = 0; i < 10; i++) {
         but[i].setFont(font);
      }
      butPoint.setFont(font);
      butAdd.setFont(font);
      butMinus.setFont(font);
      butMultiply.setFont(font);
      butDivide.setFont(font);
      butEqual.setFont(font);
      butSquareRoot.setFont(font);
      butSquare.setFont(font);
      butInverse.setFont(font);
      butCos.setFont(font);
      butSin.setFont(font);
      butTan.setFont(font);
      butPower.setFont(font);
      butLog.setFont(font);
      butRate.setFont(font);
      butAbs.setFont(font);
      butCancel.setFont(font);
      butBinary.setFont(font);

      panel.add(Box.createHorizontalStrut(100));
      panelSub1.add(text);
      panel.add(panelSub1);

      panelSub2.add(but[1]);
      panelSub2.add(but[2]);
      panelSub2.add(but[3]);
      panelSub2.add(Box.createHorizontalStrut(15));
      panelSub2.add(butAdd);
      panelSub2.add(butMinus);
      panel.add(panelSub2);

      panelSub3.add(but[4]);
      panelSub3.add(but[5]);
      panelSub3.add(but[6]);
      panelSub3.add(Box.createHorizontalStrut(15));
      panelSub3.add(butMultiply);
      panelSub3.add(butDivide);
      panel.add(panelSub3);

      panelSub4.add(but[7]);
      panelSub4.add(but[8]);
      panelSub4.add(but[9]);
      panelSub4.add(Box.createHorizontalStrut(15));
      panelSub4.add(butEqual);
      panelSub4.add(butCancel);
      panel.add(panelSub4);

      panelSub5.add(but[0]);
      panelSub5.add(butPoint);
      panelSub5.add(Box.createHorizontalStrut(110));
      panel.add(panelSub5);

      panelSub6.add(butSquare);
      panelSub6.add(butSquareRoot);
      panelSub6.add(butInverse);
      panelSub6.add(butPower);
      panel.add(panelSub6);

      panelSub7.add(butCos);
      panelSub7.add(butSin);
      panelSub7.add(butTan);
      panel.add(panelSub7);

      panelSub8.add(butLog);
      panelSub8.add(butRate);
      panelSub8.add(butAbs);
      panelSub8.add(butBinary);
      panel.add(panelSub8);

      for (int i = 0; i < 10; i++) {
         but[i].addActionListener(this);
      }
      butPoint.addActionListener(this);
      butAdd.addActionListener(this);
      butMinus.addActionListener(this);
      butMultiply.addActionListener(this);
      butDivide.addActionListener(this);
      butSquare.addActionListener(this);
      butSquareRoot.addActionListener(this);
      butInverse.addActionListener(this);
      butCos.addActionListener(this);
      butSin.addActionListener(this);
      butTan.addActionListener(this);
      butPower.addActionListener(this);
      butLog.addActionListener(this);
      butRate.addActionListener(this);
      butAbs.addActionListener(this);
      butBinary.addActionListener(this);

      butEqual.addActionListener(this);
      butCancel.addActionListener(this);

      frame.add(panel);
      frame.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      final Object source = e.getSource();

      for (int i = 0; i < 10; i++) {
         if (source == but[i]) {
            text.replaceSelection(buttonValue[i]);
            return;
         }
      }
      if (source == butPoint) {
         text.replaceSelection(".");
      } else if (source == butAdd) {
         writer(calc.calculateBi(Calculator.BiOperatorModes.add, reader()));
      } else if (source == butMinus) {
         writer(calc.calculateBi(Calculator.BiOperatorModes.minus, reader()));
      } else if (source == butMultiply) {
         writer(calc.calculateBi(Calculator.BiOperatorModes.multiply, reader()));
      } else if (source == butDivide) {
         writer(calc.calculateBi(Calculator.BiOperatorModes.divide, reader()));
      } else if (source == butPower) {
         writer(calc.calculateBi(Calculator.BiOperatorModes.power, reader()));
      } else if (source == butSquare) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.square, reader()));
      } else if (source == butSquareRoot) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, reader()));
      } else if (source == butInverse) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.inverse, reader()));
      } else if (source == butCos) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.cos, reader()));
      } else if (source == butSin) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.sin, reader()));
      } else if (source == butTan) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.tan, reader()));
      } else if (source == butLog) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.log, reader()));
      } else if (source == butRate) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.rate, reader()));
      } else if (source == butAbs) {
         writer(calc.calculateMono(Calculator.MonoOperatorModes.abs, reader()));
      } else if (source == butBinary) {
         parsetoBinary();
      } else if (source == butEqual) {
         writer(calc.calculateEqual(reader()));
      } else if (source == butCancel) {
         writer(calc.reset());
      }

      text.selectAll();
   }

   private void parsetoBinary() {
      try {
         text.setText("" + Long.toBinaryString(Long.parseLong(text.getText())));
      } catch (NumberFormatException ex) {
         System.err.println("Error while parse to binary." + ex.toString());
      }
   }

   public Double reader() {
      Double num;
      String str;
      str = text.getText();
      num = Double.valueOf(str);

      return num;
   }

   public void writer(final Double num) {
      if (Double.isNaN(num)) {
         text.setText("");
      } else {
         text.setText(Double.toString(num));
      }
   }
}
