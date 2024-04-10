import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame {
    private JLabel titleLabel;
    private JLabel guessLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JButton playAgainButton;

    private int secretNumber;
    private int maxNumber = 100;
    private int attempts = 0;
    private boolean correctGuess = false;

    public GuessTheNumberGUI() {
        setTitle("Guess The Number Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        titleLabel = new JLabel("猜数字游戏 - 1到" + maxNumber);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        guessLabel = new JLabel("输入你的猜测：");
        guessLabel.setHorizontalAlignment(JLabel.CENTER);
        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessButton = new JButton("猜！");
        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        playAgainButton = new JButton("重新开始");

        add(titleLabel);
        add(guessLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);
        add(playAgainButton);

        generateSecretNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    private void generateSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(maxNumber) + 1;
    }

    private void checkGuess() {
        String guessText = guessField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            attempts++;
            if (guess == secretNumber) {
                resultLabel.setText("恭喜你，猜对了！");
                correctGuess = true;
                disableGuessing();
            } else if (guess < secretNumber) {
                resultLabel.setText("你猜的数字太小了，请再试一次。");
            } else {
                resultLabel.setText("你猜的数字太大了，请再试一次。");
            }
            guessField.setText("");
            if (attempts == 6 && !correctGuess) {
                resultLabel.setText("很遗憾，你没有在6次机会内猜出正确答案。正确答案是 " + secretNumber);
                disableGuessing();
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("抱歉，请输入一个有效的数字。");
        }
    }

    private void resetGame() {
        generateSecretNumber();
        attempts = 0;
        correctGuess = false;
        resultLabel.setText("");
        enableGuessing();
    }

    private void disableGuessing() {
        guessField.setEnabled(false);
        guessButton.setEnabled(false);
    }

    private void enableGuessing() {
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessTheNumberGUI().setVisible(true);
            }
        });
    }
}
