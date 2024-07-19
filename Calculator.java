import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Calculator extends Applet implements ActionListener {

    TextField display;

    public void init() {
        setBackground(Color.lightGray);
        setLayout(null);

        display = new TextField();
        display.setBounds(100, 50, 300, 40);
        this.add(display);

        Button[] numButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new Button(String.valueOf(i));
            numButtons[i].setBounds(100 + ((i % 3) * 50), 100 + ((i / 3) * 50), 50, 50);
            this.add(numButtons[i]);
            numButtons[i].addActionListener(this);
        }

        Button dot = new Button(".");
        dot.setBounds(200, 250, 50, 50);
        this.add(dot);
        dot.addActionListener(this);

        Button clear = new Button("C");
        clear.setBounds(250, 250, 50, 50);
        this.add(clear);
        clear.addActionListener(this);

        Button[] operators = new Button[5];
        operators[0] = new Button("/");
        operators[1] = new Button("*");
        operators[2] = new Button("-");
        operators[3] = new Button("+");
        operators[4] = new Button("=");
        
        for (int i = 0; i < 4; i++) {
            operators[i].setBounds(300, 100 + (i * 50), 50, 50);
            this.add(operators[i]);
            operators[i].addActionListener(this);
        }
        
        operators[4].setBounds(350, 250, 70, 50);
        this.add(operators[4]);
        operators[4].addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        char c = command.charAt(0);
        
        String num1 = "";
        String op = "";
        String num2 = "";

        if (c >= '0' && c <= '9' || c == '.') {
            String currentDisplay = display.getText();
            int operatorIndex = currentDisplay.indexOf('+') != -1 ? currentDisplay.indexOf('+') :
                                currentDisplay.indexOf('-') != -1 ? currentDisplay.indexOf('-') :
                                currentDisplay.indexOf('*') != -1 ? currentDisplay.indexOf('*') :
                                currentDisplay.indexOf('/') != -1 ? currentDisplay.indexOf('/') : -1;
            
            if (operatorIndex != -1) {
                num1 = currentDisplay.substring(0, operatorIndex);
                op = String.valueOf(currentDisplay.charAt(operatorIndex));
                num2 = currentDisplay.substring(operatorIndex + 1);
            } else {
                num1 = currentDisplay;
            }
            
            if (!op.equals("")) {
                num2 += command;
            } else {
                num1 += command;
            }
            
            display.setText(num1 + op + num2);
        } else if (c == 'C') {
            display.setText("");
        } else if (c == '=') {
            String currentDisplay = display.getText();
            int operatorIndex = currentDisplay.indexOf('+') != -1 ? currentDisplay.indexOf('+') :
                                currentDisplay.indexOf('-') != -1 ? currentDisplay.indexOf('-') :
                                currentDisplay.indexOf('*') != -1 ? currentDisplay.indexOf('*') :
                                currentDisplay.indexOf('/') != -1 ? currentDisplay.indexOf('/') : -1;
            
            if (operatorIndex != -1) {
                num1 = currentDisplay.substring(0, operatorIndex);
                op = String.valueOf(currentDisplay.charAt(operatorIndex));
                num2 = currentDisplay.substring(operatorIndex + 1);
                
                if (!num1.equals("") && !num2.equals("")) {
                    try {
                        double result;
                        double n1 = Double.parseDouble(num1);
                        double n2 = Double.parseDouble(num2);

                        switch (op) {
                            case "+":
                                result = n1 + n2;
                                break;
                            case "-":
                                result = n1 - n2;
                                break;
                            case "*":
                                result = n1 * n2;
                                break;
                            case "/":
                                if (n2 == 0) {
                                    display.setText("Error: Division by zero");
                                    return;
                                }
                                result = n1 / n2;
                                break;
                            default:
                                result = 0;
                        }

                        display.setText(num1 + op + num2 + " = " + result);
                    } catch (Exception ex) {
                        display.setText("Error");
                    }
                }
            }
        } else {
            String currentDisplay = display.getText();
            int operatorIndex = currentDisplay.indexOf('+') != -1 ? currentDisplay.indexOf('+') :
                                currentDisplay.indexOf('-') != -1 ? currentDisplay.indexOf('-') :
                                currentDisplay.indexOf('*') != -1 ? currentDisplay.indexOf('*') :
                                currentDisplay.indexOf('/') != -1 ? currentDisplay.indexOf('/') : -1;

            if (operatorIndex != -1) {
                num1 = currentDisplay.substring(0, operatorIndex);
                op = String.valueOf(currentDisplay.charAt(operatorIndex));
                num2 = currentDisplay.substring(operatorIndex + 1);

                try {
                    double result;
                    double n1 = Double.parseDouble(num1);
                    double n2 = Double.parseDouble(num2);

                    switch (op) {
                        case "+":
                            result = n1 + n2;
                            break;
                        case "-":
                            result = n1 - n2;
                            break;
                        case "*":
                            result = n1 * n2;
                            break;
                        case "/":
                            if (n2 == 0) {
                                display.setText("Error: Division by zero");
                                return;
                            }
                            result = n1 / n2;
                            break;
                        default:
                            result = 0;
                    }

                    num1 = String.valueOf(result);
                    op = command;
                    num2 = "";
                    display.setText(num1 + op + num2);
                } catch (Exception ex) {
                    display.setText("Error");
                }
            } else {
                op = command;
                display.setText(num1 + op + num2);
            }
        }
    }
}
/*
<applet code = LocalCalculator.class width=500 height=400>
</applet>
*/
