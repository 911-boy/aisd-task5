package ru.vsu.cs.task4;

import javax.swing.*;
import java.awt.*;

public class SolutionFrame extends JFrame {
    private JTextField inputField;
    private JButton calcButton;
    private JTextArea outputArea;

    public SolutionFrame() {
        setTitle("Максимум среди листьев двоичного дерева");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(new JLabel("Дерево в скобочной нотации:"), BorderLayout.WEST);
        inputField = new JTextField();
        topPanel.add(inputField, BorderLayout.CENTER);
        calcButton = new JButton("Максимум в листьях");
        topPanel.add(calcButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        calcButton.addActionListener(e -> {
            String s = inputField.getText();
            SimpleIntBinaryTree tree = new SimpleIntBinaryTree();
            try {
                tree.fromBracketNotation(s);
                Integer maxLeaf = tree.findMaxLeaf();
                if (maxLeaf == null) {
                    outputArea.setText("В дереве нет листьев или дерево пустое.");
                } else {
                    outputArea.setText("Максимальное значение среди листьев: " + maxLeaf);
                }
            } catch (Exception ex) {
                outputArea.setText("Ошибка: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SolutionFrame().setVisible(true));
    }
}
