import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;

public class GUI2048 extends JFrame{

    private JPanel contentPane;
    private JTextField commandTextField;
    private JButton matrix[][];
    private G2048 game;
    private static int ROW_SIZE=4;
    private static int COLUMN_SIZE=4;


    private void makeMatrix() {
        JButton btn1 = new JButton("0");
        btn1.setEnabled(false);
        btn1.setBounds(64, 75, 50, 48);
        contentPane.add(btn1);

        JButton btn0 = new JButton("0");
        btn0.setEnabled(false);
        btn0.setBounds(6, 75, 50, 48);
        contentPane.add(btn0);

        JButton btn2 = new JButton("0");
        btn2.setEnabled(false);
        btn2.setBounds(127, 75, 50, 48);
        contentPane.add(btn2);

        JButton btn3 = new JButton("0");
        btn3.setEnabled(false);
        btn3.setBounds(185, 75, 50, 48);
        contentPane.add(btn3);

        JButton btn7 = new JButton("0");
        btn7.setEnabled(false);
        btn7.setBounds(185, 124, 50, 48);
        contentPane.add(btn7);

        JButton btn6 = new JButton("0");
        btn6.setEnabled(false);
        btn6.setBounds(127, 124, 50, 48);
        contentPane.add(btn6);

        JButton btn5 = new JButton("0");
        btn5.setEnabled(false);
        btn5.setBounds(64, 124, 50, 48);
        contentPane.add(btn5);

        JButton btn4 = new JButton("0");
        btn4.setEnabled(false);
        btn4.setBounds(6, 124, 50, 48);
        contentPane.add(btn4);

        JButton btn11 = new JButton("0");
        btn11.setEnabled(false);
        btn11.setBounds(185, 179, 50, 48);
        contentPane.add(btn11);

        JButton btn10 = new JButton("0");
        btn10.setEnabled(false);
        btn10.setBounds(127, 179, 50, 48);
        contentPane.add(btn10);

        JButton btn9 = new JButton("0");
        btn9.setEnabled(false);
        btn9.setBounds(64, 179, 50, 48);
        contentPane.add(btn9);

        JButton btn8 = new JButton("0");
        btn8.setEnabled(false);
        btn8.setBounds(6, 179, 50, 48);
        contentPane.add(btn8);

        JButton btn15 = new JButton("0");
        btn15.setEnabled(false);
        btn15.setBounds(185, 230, 50, 48);
        contentPane.add(btn15);

        JButton btn14 = new JButton("0");
        btn14.setEnabled(false);
        btn14.setBounds(127, 230, 50, 48);
        contentPane.add(btn14);

        JButton btn13 = new JButton("0");
        btn13.setEnabled(false);
        btn13.setBounds(64, 230, 50, 48);
        contentPane.add(btn13);

        JButton btn12 = new JButton("0");
        btn12.setEnabled(false);
        btn12.setBounds(6, 230, 50, 48);
        contentPane.add(btn12);

        matrix = new JButton[][]{{btn0,btn1,btn2,btn3},
                {btn4,btn5,btn6,btn7},
                {btn8,btn9,btn10,btn11},
                {btn12,btn13,btn14,btn15}
        };
    }


    public void init()
    {
        setTitle("GAME");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 253, 358);
        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        makeMatrix();

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(168, 45, 62, 16);
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playCommand();
            }
        });
        contentPane.add(enterButton);

        JLabel lblNewLabel = new JLabel("Please insert a letter to play ");
        lblNewLabel.setBounds(6, 17, 229, 16);
        contentPane.add(lblNewLabel);

        commandTextField = new JTextField();
        commandTextField.setBounds(6, 40, 150, 26);
        contentPane.add(commandTextField);
        commandTextField.setColumns(10);


        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        resetButton.setBounds(118, 290, 117, 29);
        contentPane.add(resetButton);
        setLocationRelativeTo(null);
    }

    public GUI2048(G2048 game) {
        this.game = game;
        this.init();
    }
    public void play(){
        this.setVisible(true);
    }
    public void playCommand() {
        String command = readService();
        if(validateCommand(command)){
            this.play(command);

            if (game.winGame()) {
                printService("You win !!!!! :)");
            }
            if (game.lostGame())  {
                printService("You lost !!! :(");
            }
        }
        else{
            printService("Please use only the indicated letters");
        }
        updateBoard(game.toString());
        //printService(game.toString());
    }

    private String[][] parseBoard(String gameStatus)
    {
        String[][] board = new String[4][4];
        String[] rows = gameStatus.split("\n");
        for(int i = 0; i < ROW_SIZE;i++){
            String[] columnItems = rows[i].split(" ");
            for(int j=0;j < COLUMN_SIZE;j++) {
                board[i][j]=columnItems[j];
            }
        }
        return board;
    }
    private void updateBoard(String gameStatus){
        String[][]board  = parseBoard(gameStatus);
        for(int i = 0; i < ROW_SIZE;i++){
            for(int j=0;j < COLUMN_SIZE;j++) {
                matrix[i][j].setText(board[i][j]);
            }
        }
    }
    private void play(String order){
        switch (order){
            case "W":this.game.moveUp();break;
            case "S":this.game.moveDown();break;
            case "A":this.game.moveLeft();break;
            case "D":this.game.moveRight();break;
            default: throw new UnsupportedOperationException("Order not recognized");
        }
    }
    private boolean validateCommand(String command)
    {
        command = command.toUpperCase();
        String[] commands = {"A","S","D","W"};
        for(String c: commands)
            if(c.equals(command))
                return true;
        return false;
    }
    private String readService(){

        String input= commandTextField.getText();
        return input;
    }
    private void printService(String message){
        JOptionPane.showMessageDialog(null, message,"Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private void resetGame(){
        this.game = new Game2048();
        this.commandTextField.setText("");
        for(int i = 0; i < ROW_SIZE;i++){
            for(int j=0;j < COLUMN_SIZE;j++) {
                matrix[i][j].setText("0");
            }
        }

    }

}
