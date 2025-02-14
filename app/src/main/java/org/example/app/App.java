package org.example.app;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws IOException {
        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        // Create panel to hold components
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        final Label lblOutput = new Label("");

        panel.addComponent(new Label("Num 1"));
        final TextBox txtNum1 = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);


        panel.addComponent(new Label("Num 2"));
        final TextBox txtNum2 = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(panel);

        panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        new Button("Add!", () -> extracted(txtNum1, txtNum2, lblOutput))
                .addTo(panel);

        new Button("Quit", () -> System.exit(0))
                .addTo(panel);

        panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        panel.addComponent(lblOutput);

        txtNum1.setTextChangeListener((s, user) -> {
            if (user) {
                extracted(txtNum1, txtNum2, lblOutput);
            }
        });

        txtNum2.setTextChangeListener((s, user) -> {
            if (user) {
                extracted(txtNum1, txtNum2, lblOutput);
            }
        });

        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setComponent(panel);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);
    }

    private static void extracted(TextBox txtNum1, TextBox txtNum2, Label lblOutput) {
        int num1 = getNum1(txtNum1);
        int num2 = getNum1(txtNum2);
        lblOutput.setText(Integer.toString(num1 + num2));
    }

    private static int getNum1(TextBox txtNum1) {
        int num1;
        try {
            num1 = Integer.parseInt(txtNum1.getText());
        } catch (NumberFormatException e) {
            num1 = 0;
        }
        return num1;
    }
}
