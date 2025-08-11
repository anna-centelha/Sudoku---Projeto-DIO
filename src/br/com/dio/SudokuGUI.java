package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.Space;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static br.com.dio.util.BoardTemplate.*;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class SudokuGUI extends JFrame {

    private Board board;
    private final JTextField[][] fields = new JTextField[9][9];
    private final JPanel boardPanel = new JPanel();

    public SudokuGUI() {
        setTitle("Sudoku DIO");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(9, 9));
        Font font = new Font("SansSerif", Font.BOLD, 24);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                fields[row][col] = new JTextField();
                fields[row][col].setHorizontalAlignment(JTextField.CENTER);
                fields[row][col].setFont(font);
                boardPanel.add(fields[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton newGameButton = new JButton("Novo Jogo");
        JButton checkButton = new JButton("Verificar");
        JButton clearButton = new JButton("Limpar");

        buttonPanel.add(newGameButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        newGameButton.addActionListener(e -> chooseDifficulty());
        clearButton.addActionListener(e -> clearUserInput());
        checkButton.addActionListener(e -> checkSolution());
    }

    private void chooseDifficulty() {
        Object[] options = {"Fácil", "Médio", "Difícil"};
        int n = JOptionPane.showOptionDialog(this,
                "Escolha o nível de dificuldade:",
                "Novo Jogo",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        String template = switch (n) {
            case 0 -> BOARD_TEMPLATE_EASY;
            case 1 -> BOARD_TEMPLATE_MEDIUM;
            case 2 -> BOARD_TEMPLATE_HARD;
            default -> null;
        };

        if (template != null) {
            startGame(template);
        }
    }

    private void startGame(final String boardTemplate) {
        final var positions = Stream.of(boardTemplate.split(";"))
                .collect(toMap(
                        k -> k.split(",")[0] + "," + k.split(",")[1],
                        v -> v.split(",")[2] + "," + v.split(",")[3]
                ));

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                var positionKey = "%d,%d".formatted(i, j);
                var positionConfig = positions.get(positionKey);
                if (nonNull(positionConfig)) {
                    var expected = Integer.parseInt(positionConfig.split(",")[0]);
                    var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                    spaces.get(i).add(new Space(expected, fixed));
                } else {
                    spaces.get(i).add(new Space(0, false));
                }
            }
        }
        board = new Board(spaces);
        updateBoardView();
    }

    private void updateBoardView() {
        if (board == null) return;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Space space = board.getSpaces().get(row).get(col);
                JTextField field = fields[row][col];

                if (nonNull(space.getActual()) && space.getActual() != 0) {
                    field.setText(String.valueOf(space.getActual()));
                } else {
                    field.setText("");
                }

                if (space.isFixed()) {
                    field.setEditable(false);
                    field.setForeground(Color.BLUE);
                } else {
                    field.setEditable(true);
                    field.setForeground(Color.BLACK);
                }
            }
        }
    }

    private void clearUserInput() {
        if (board == null) return;
        board.reset();
        updateBoardView();
    }

    private void checkSolution() {
        if (board == null) {
            JOptionPane.showMessageDialog(this, "Comece um novo jogo primeiro!");
            return;
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = fields[row][col].getText();
                if (!board.getSpaces().get(row).get(col).isFixed()) {
                    if (text.isEmpty()) {
                        board.getSpaces().get(row).get(col).setActual(null);
                    } else {
                        try {
                            board.getSpaces().get(row).get(col).setActual(Integer.parseInt(text));
                        } catch (NumberFormatException e) {
                            board.getSpaces().get(row).get(col).setActual(null);
                        }
                    }
                }
            }
        }

        if (board.gameIsFinished()) {
            JOptionPane.showMessageDialog(this, "Parabéns! Você resolveu o Sudoku!");
        } else if (board.hasErrors()){
            JOptionPane.showMessageDialog(this, "Existem erros no seu tabuleiro. Tente novamente!");
        } else {
            JOptionPane.showMessageDialog(this, "O tabuleiro ainda está incompleto.");
        }
    }
}