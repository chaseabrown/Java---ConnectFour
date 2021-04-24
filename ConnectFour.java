import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnectFour {

	static int[][] board = new int[6][7];
	static JPanel[][] panels = new JPanel[6][7];
	static boolean playerOneTurn = true;
	static boolean play = true;
	public static JFrame frame = new JFrame("Connect Four");
	static JLabel topbar = new JLabel("");
	static JButton[] buttons = new JButton[7];
	static JPanel background = new JPanel();

	public static void main(String[] args) {
		mainGUI();

	}

	public static void mainGUI() {

		frame.setTitle("Connect Four");
		
		for (int i = 0; i < 6; i++) {
			for (int s = 0; s < 7; s++) {
			board[i][s] = 0;
			}
		}
		
		playerOneTurn = true;

		frame.setSize(525, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topbar.setBounds(0, 0, 531, 35);
		topbar.setBackground(Color.gray);
		topbar.setOpaque(true);
		frame.add(topbar);
		for (int i = 0; i < 6; i++) {
			for (int s = 0; s < 7; s++) {
				panels[i][s] = new JPanel();
				panels[i][s].setBackground(Color.white);
				panels[i][s].setBorder(BorderFactory.createLineBorder(
						Color.blue, 5));
				panels[i][s].setBounds((s * 75), i * 75 + 35, 75, 75);
				frame.add(panels[i][s]);
			}
		}
		for (int i = 0; i < 7; i++) {
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.DARK_GRAY);
			buttons[i].setBounds(i * 75, 484, 75, 75);

			final int column = i;
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					play = true;
					if (play) {
						if (playerOneTurn) {
							if (board[0][column] == 0) {
								dropDisk(column, 1);
								playerOneTurn = false;
								play = false;
								if (checkWin(board) == 1) {
									promptReset();
								}else if(checkWin(board) == -1){
									promptReset();
								}
							}
						}
					}
					if (play) {
						if (!playerOneTurn) {
							if (board[0][column] == 0) {
								dropDisk(column, 2);
								playerOneTurn = true;
								play = false;
								if (checkWin(board) == 1) {
									promptReset();
								}else if(checkWin(board) == -1){
									promptReset();
								}
							}
						}
					}

					/*
					 * Random gen = new Random(); int column = gen.nextInt(7);
					 * while (board[0][column] != 0) { column = gen.nextInt(7);
					 * } dropDisk(column, 2); if (checkWin(board) == 1) {
					 * System.out.println("Winner"); }
					 */
				}
			});

			frame.add(buttons[i]);
		}
		background.setBackground(Color.white);
		background.setBounds(0, 0, 720, 780);
		frame.add(background);
		frame.setVisible(true);

	}

	public static void promptReset() {

		removeAll();

		frame.setTitle("New Game?");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton button1 = new JButton();
		final JButton button2 = new JButton();

		button1.setText("Play Again");
		button1.setBackground(Color.magenta);
		button1.setBorder(BorderFactory.createLineBorder(Color.black));
		button1.setOpaque(true);
		button1.setBounds(0, 0, 400, 200);

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.remove(button1);
				frame.remove(button2);
				mainGUI();
			}
		});

		button2.setText("Quit");
		button2.setBackground(Color.MAGENTA);
		button2.setBorder(BorderFactory.createLineBorder(Color.black));
		button2.setOpaque(true);
		button2.setBounds(0, 200, 400, 200);

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
			}
		});

		frame.add(button1);
		frame.add(button2);
		background.setBackground(Color.white);
		background.setBounds(0, 0, 400, 400);
		frame.add(background);
		frame.setVisible(true);
		frame.setVisible(true);

	}

	public static void removeAll() {
		frame.remove(topbar);
		frame.remove(background);
		for (int i = 0; i < 6; i++) {
			for (int s = 0; s < 7; s++) {
				frame.remove(panels[i][s]);
			}
		}
		for (int i = 0; i < 7; i++) {
			frame.remove(buttons[i]);
		}
	}

	public static void dropDisk(int column, int side) {
		for (int i = 5; i >= 0; i--) {
			if (board[i][column] == 0) {
				board[i][column] = side;
				if (side == 1) {
					panels[i][column].setBackground(Color.cyan);
				} else {
					panels[i][column].setBackground(Color.red);
				}
				break;
			}
		}
	}

	public static int checkWin(int[][] checkboardArray) {
		int row;
		int column;
		int boardWidth = 6;
		int winLength = 4;
		boolean checkWin = false;
		int boardHeight = 5;
		// check for horizontal winners
		for (row = 0; row < 6; row++) {
			for (column = 0; column <= boardWidth - winLength; column++) {
				checkWin = true;
				if (checkboardArray[column][row] == 0) {
					checkWin = false;
				} else
					for (int forCounter = 0; forCounter < winLength; forCounter++) {
						if (checkboardArray[column][row] == 0) {
							checkWin = false;
						}
						if (checkboardArray[column][row] != checkboardArray[column
								+ forCounter][row]) {
							checkWin = false;
						}
					}
				if (checkWin)
					return 1;
			}
		}

		// check for vertical winners
		for (row = 0; row < boardHeight - winLength + 1; row++) {
			for (column = 0; column < boardWidth; column++) {
				checkWin = true;
				if (checkboardArray[column][row] == 0) {
					checkWin = false;
				} else
					for (int forCounter = 0; forCounter < winLength; forCounter++) {
						if (checkboardArray[column][row] == 0) {
							checkWin = false;
						}
						if (checkboardArray[column][row] != checkboardArray[column][row
								+ forCounter]) {
							checkWin = false;
						}
					}
				if (checkWin)
					return 1;

			}
		}
		// winner not found checking for positive slope winner
		for (row = 0; row < boardHeight - winLength + 1; row++) {
			for (column = 0; column < boardWidth - winLength + 1; column++) {
				if (checkboardArray[column][row] != 0
						&& checkboardArray[column + 1][row + 1] == checkboardArray[column][row]
						&& checkboardArray[column + 2][row + 2] == checkboardArray[column][row]
						&& checkboardArray[column + 3][row + 3] == checkboardArray[column][row]) {
					return 1;
				}
			}
		}
		// winner not found, checking for negative slope winner
		for (row = winLength - 1; row < boardHeight; row++) {
			for (column = 0; column < boardWidth - winLength + 1; column++) {
				if (checkboardArray[column][row] != 0
						&& checkboardArray[column + 1][row - 1] == checkboardArray[column][row]
						&& checkboardArray[column + 2][row - 2] == checkboardArray[column][row]
						&& checkboardArray[column + 3][row - 3] == checkboardArray[column][row]) {
					return 1;
				}
			}
		}
		boolean full = true;
		for (row = 0; row < 5; row++) {
			for (column = 0; column < 6; column++) {
				if (checkboardArray[column][row] == 0) {
					full = false;
				}
			}
		}
		if (full == true) {
			return -1;
		}
		return 0;

	}

	public static void checkWin(int row, int column) {
		System.out.println(row + "," + column);
		if (row < 3) {
			if (board[row][column] == board[row + 1][column]
					&& board[row][column] == board[row + 2][column]
					&& board[row][column] == board[row + 3][column]) {
				System.out.println("gameover");
			}
		}
		if (row > 3) {
			if (board[row][column] == board[row - 1][column]
					&& board[row][column] == board[row - 2][column]
					&& board[row][column] == board[row - 3][column]) {
				System.out.println("gameover");
			}
		}
		if (column < 4) {
			if (board[row][column] == board[row][column + 1]
					&& board[row][column] == board[row][column + 2]
					&& board[row][column] == board[row][column + 3]) {
				System.out.println("gameover");
			}
		}
		if (column > 3) {
			if (board[row][column] == board[row][column - 1]
					&& board[row][column] == board[row][column - 2]
					&& board[row][column] == board[row][column - 3]) {
				System.out.println("gameover");
			}
		}
		if (column > 3 && row > 3) {
			System.out.println("made it top");
			if (board[row][column] == board[row - 1][column - 1]
					&& board[row][column] == board[row - 2][column - 2]
					&& board[row][column] == board[row - 3][column - 3]) {
				System.out.println("gameover");
			}
		}
		if (column < 4 && row < 3) {
			System.out.println("made it bottom");
			if (board[row][column] == board[row + 1][column + 1]
					&& board[row][column] == board[row + 2][column + 2]
					&& board[row][column] == board[row + 3][column + 3]) {
				System.out.println("gameover");
			}
		}
	}

}
