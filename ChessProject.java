import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
/*
 * Original code provided by NCI - Keith Maycock / Emin Zerman This class can be
 * used as a starting point for creating your Chess game project. The only piece
 * that has been coded is a white pawn...a lot done, more to do!
 * 
 * Name: Mark Kelly Student Number: 17138311
 */

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	boolean correctColour;

	public ChessProject() {
		Dimension boardSize = new Dimension(600, 600);

		// Use a Layered Pane for this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		// Add a chess board to the Layered Pane
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
			else
				square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
		}

		// Setting up the Initial Chess board.
		for (int i = 8; i < 16; i++) {
			pieces = new JLabel(new ImageIcon("WhitePawn.png"));
			panels = (JPanel) chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel(new ImageIcon("WhiteRook.png"));
		panels = (JPanel) chessBoard.getComponent(0);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
		panels = (JPanel) chessBoard.getComponent(1);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
		panels = (JPanel) chessBoard.getComponent(6);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
		panels = (JPanel) chessBoard.getComponent(2);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
		panels = (JPanel) chessBoard.getComponent(5);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteKing.png"));
		panels = (JPanel) chessBoard.getComponent(3);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
		panels = (JPanel) chessBoard.getComponent(4);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("WhiteRook.png"));
		panels = (JPanel) chessBoard.getComponent(7);
		panels.add(pieces);
		for (int i = 48; i < 56; i++) {
			pieces = new JLabel(new ImageIcon("BlackPawn.png"));
			panels = (JPanel) chessBoard.getComponent(i);
			panels.add(pieces);
		}
		pieces = new JLabel(new ImageIcon("BlackRook.png"));
		panels = (JPanel) chessBoard.getComponent(56);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKnight.png"));
		panels = (JPanel) chessBoard.getComponent(57);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKnight.png"));
		panels = (JPanel) chessBoard.getComponent(62);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackBishop.png"));
		panels = (JPanel) chessBoard.getComponent(58);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackBishop.png"));
		panels = (JPanel) chessBoard.getComponent(61);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackKing.png"));
		panels = (JPanel) chessBoard.getComponent(59);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackQueen.png"));
		panels = (JPanel) chessBoard.getComponent(60);
		panels.add(pieces);
		pieces = new JLabel(new ImageIcon("BlackRook.png"));
		panels = (JPanel) chessBoard.getComponent(63);
		panels.add(pieces);

	}

	int moveCount = 0;

	private void nextMove() {
		moveCount++;
		System.out.println("Cmove Count  = " + moveCount);
	}

	// if(pieceName.contains("White")&&(moveCount%2==0))

	// {
	// 	correctColour = true;
	// 	AIAgent PDiddy = new AIAgent();
	// 	PDiddy.testPlumbing("Mark");

	/*
	 * This method checks if there is a piece present on a particular square.
	 */
	private Boolean piecePresent(int x, int y) {
		Component c = chessBoard.findComponentAt(x, y);
		if (c instanceof JPanel) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * This is a method to check if a piece is a Black piece.
	 */
	private Boolean checkWhiteOponent(int newX, int newY) {
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel) c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if (((tmp1.contains("Black")))) {
			oponent = true;
			if (tmp1.contains("King")) {
				JOptionPane.showMessageDialog(null, "White Player Wins \n\n Game Over! \n \n Please Play again :-)");
				System.exit(0);
			}
		} else {
			oponent = false;
		}
		return oponent;
	}

	/*
	 * This is a method to check if a piece is a White piece.
	 */
	private Boolean checkBlackOponent(int newX, int newY) {
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel) c1;
		String tmp1 = awaitingPiece.getIcon().toString();

		if (((tmp1.contains("White")))) {
			oponent = true;
			if (tmp1.contains("King")) {
				JOptionPane.showMessageDialog(null, "White Player Wins \n\n Game Over! \n \n Please Play again :-)");
				System.exit(0);
			}
		} else {
			oponent = false;
		}
		return oponent;
	}

	/*
	 * This is a method to check if there is at least 1 space between kings. Removed
	 * the check oponentAt to new Method to allow to check location other then the
	 * location dropped into
	 */
	private String oponentName(int newX, int newY) {
		Component c1 = chessBoard.findComponentAt(newX, newY);
		if (c1 instanceof JLabel) {
			JLabel awaitingPiece = (JLabel) c1;
			String tmp1 = awaitingPiece.getIcon().toString();
			return tmp1;
		} else {
			return "";
		}
	}

	private Boolean checkKingSpace(int newX, int newY) {
		// check same X && Y+75 || Y-75
		if ((piecePresent(newX, newY + 75) && oponentName(newX, newY + 75).contains("King"))
				|| (piecePresent(newX, newY - 75) && oponentName(newX, newY - 75).contains("King"))) {
			return true;
		}
		// check X -75 && Y+75 || Y-75
		else if ((piecePresent(newX - 75, newY + 75) && oponentName(newX - 75, newY + 75).contains("King"))
				|| (piecePresent(newX - 75, newY - 75) && oponentName(newX - 75, newY - 75).contains("King"))) {
			return true;
		}
		// check X +75 && same Y|| X +75 && same Y
		else if ((piecePresent(newX - 75, newY) && oponentName(newX - 75, newY).contains("King"))
				|| (piecePresent(newX + 75, newY) && oponentName(newX + 75, newY).contains("King"))) {
			return true;
		}
		// check X -75 && Y-75|| X +75 && Y +75
		else if ((piecePresent(newX + 75, newY - 75) && oponentName(newX + 75, newY - 75).contains("King"))
				|| (piecePresent(newX + 75, newY + 75) && oponentName(newX + 75, newY + 75).contains("King"))) {
			return true;
		}
		return false;
	}

	/*
	 * Find all white pieces on the board This code is from the Project Manual
	 * provided from NCI code snippet 2
	 */
	private Stack findWhitePieces() {
		Stack wSquares = new Stack();
		String icon;
		int x;
		int y;
		String pieceName;
		for (int i = 0; i < 600; i += 75) {
			for (int j = 0; j < 600; j += 75) {
				y = i / 75;
				x = j / 75;
				Component tmp = chessBoard.findComponentAt(j, i);
				if (tmp instanceof JLabel) {
					chessPiece = (JLabel) tmp;
					icon = chessPiece.getIcon().toString();
					pieceName = icon.substring(0, (icon.length() - 4));
					if (pieceName.contains("White")) {
						Square stmp = new Square(x, y, pieceName);
						wSquares.push(stmp);
					}
				}
			}
		}
		return wSquares;
	}

	/*
	 * Find all the squares currently been attached by white pieces This code is
	 * from the Project Manual provided from NCI (Code Snippet3)
	 */
	private Stack getWhiteAttackingSquares(Stack pieces) {
		while (!pieces.empty()) {
			Square s = (Square) pieces.pop();
			String tmpString = s.getName();
			if (tmpString.contains("Knight")) {
				tempK = getKnightMoves(s.getXC(), s.getYC(), s.getName());
				while (!tempK.empty()) {
					Square tempKnight = (Square) tempK.pop();
					knight.push(tempKnight);
				}
			} else if (tmpString.contains("Bishop")) {

			}
		}
	}

	/*
	 * getKnightMoves code snippet4 from CA Manual provided by NCI Find squares been
	 * attacked by White Knights
	 */
	private Stack getKnightMoves(int x, int y, String piece) {
		// System.out.println(x);
		// System.out.println(y);
		// System.out.println(piece);

		Stack moves = new Stack();
		Stack attacking = new Stack();

		Square s = new Square(x + 1, y + 2);
		moves.push(s);
		Square s1 = new Square(x + 1, y - 2);
		moves.push(s1);
		Square s2 = new Square(x - 1, y + 2);
		moves.push(s2);
		Square s3 = new Square(x - 1, y - 2);
		moves.push(s3);
		Square s4 = new Square(x + 2, y + 1);
		moves.push(s4);
		Square s5 = new Square(x + 2, y - 1);
		moves.push(s5);
		Square s6 = new Square(x - 2, y + 1);
		moves.push(s6);
		Square s7 = new Square(x - 2, y - 1);
		moves.push(s7);

		for (int i = 0; i < 8; i++) {
			Square tmp = (Square) moves.pop();
			if ((tmp.getXC() < 0) || (tmp.getXC() > 7) || (tmp.getYC() < 0) || (tmp.getXC() > 7)) {
			} else if (piecePresent(((tmp.getXC() * 75) + 20), (((tmp.getYC() * 75) + 20)))) {
				if (piece.contains("White")) {
					if (checkWhiteOponent(((tmp.getXC() * 75) + 20), ((tmp.getYC() * 75) + 20))) {
						attacking.push(tmp);
					} else {
						System.out.println("Its our own colour piece");
					}
				} else {
					if (checkBlackOponent(tmp.getXC(), tmp.getYC())) {
						attacking.push(tmp);
					}
				}
			} else {
				attacking.push(tmp);
			}
		}
		Stack tmp = attacking;
		colourSquares(tmp);
		return attacking;
	}

	/*
	 * method to colour squares provided by NCI - code snippet 5
	 */

	private void colourSquares(Stack squares) {
		Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
		while (!squares.empty()) {
			Square s = (Square) squares.pop();
			int location = s.getXC() + ((s.getYC()) * 8);
			JPanel panel = (JPanel) chessBoard.getComponent(location);
			panel.setBorder(greenBorder);
		}
	}

	private void uncolourSquares() {

		Stack moves = new Stack();

		for (int i = 0; i <= 7; i++) {
			for (int y = 0; y <= 7; y++) {
				Square s = new Square(i, y);
				moves.push(s);
			}
		}
		Border emptyBorder = BorderFactory.createEmptyBorder();
		while (!moves.empty()) {
			Square s = (Square) moves.pop();
			int location = s.getXC() + ((s.getYC()) * 8);
			JPanel panel = (JPanel) chessBoard.getComponent(location);
			panel.setBorder(emptyBorder);
		}

	}

	// ##########################################################################################
	// ################################## Mouse Pressed
	// ##########################################
	// ##########################################################################################

	/*
	 * This method is called when we press the Mouse. So we need to find out what
	 * piece we have selected. We may also not have selected a piece!
	 */

	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX() / 75);
		startY = (e.getY() / 75);
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length() - 4));

		/* call knight moves check - what squares a knight can move to */
		if (pieceName.contains("Knight")) {
			getKnightMoves(startX, startY, pieceName);
		}

	}

	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null)
			return;
		chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	}

	// ##########################################################################################
	// ################################# Mouse Released
	// #########################################
	// ##########################################################################################
	/*
	 * This method is used when the Mouse is released...we need to make sure the
	 * move was valid before putting the piece back on the board.
	 */
	public void mouseReleased(MouseEvent e) {
		if (chessPiece == null)
			return;

		chessPiece.setVisible(false);
		Boolean wSuccess = false;
		Boolean bSuccess = false;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length() - 4));
		Boolean validMove = false;

		correctColour = true;
		/* from Keith Maycock videos on Moodle */
		int landingX = (e.getX() / 75);
		int landingY = (e.getY() / 75);
		int xMovement = Math.abs((e.getX() / 75) - startX);
		int yMovement = Math.abs((e.getY() / 75) - startY);
		System.out.println("*-*-*-*-*-*-*-*-*-*-");
		System.out.println("piece been moved is : " + pieceName);
		System.out.println("starting coordinates are : " + startX + "," + startY + ")");
		System.out.println("The xMovement is: " + xMovement);
		System.out.println("The yMovement is: " + yMovement);
		System.out.println("landing coordinates are : " + landingX + "," + landingY + ")");
		System.out.println("*-*-*-*-*-*-*-*-*-*-");
		uncolourSquares();

		if (pieceName.contains("White") && (moveCount % 2 == 0)) {
			correctColour = true;

		} else if (pieceName.contains("Black") && (moveCount % 2 == 1)) {
			correctColour = true;
		} else {
			correctColour = false;

		}
		if (correctColour) {
			/*
			 * Start of White Pawn
			 * 
			 * 
			 * Pawns move vertically forward one square, with the option to move two squares
			 * if they have not yet moved. Pawns are the only piece to capture different to
			 * how they move. Pawns capture one square diagonally in a forward direction.
			 * Pawns are unable to move backwards on captures or moves. Upon reaching the
			 * other side of the board a pawn promotes into any other piece, Queen for this
			 * CA
			 */

			if (pieceName.equals("WhitePawn")) {
				if (startY == 1) {
					if ((startX == (e.getX() / 75))
							&& ((((e.getY() / 75) - startY) == 1) || ((e.getY() / 75) - startY) == 2)) {
						if ((((e.getY() / 75) - startY) == 2)) {
							if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() - 75)))) {
								validMove = true;

							}

							else {
								validMove = false;
							}
						}

						else {
							if ((!piecePresent(e.getX(), (e.getY())))) {
								validMove = true;

							} else {
								validMove = false;
							}
						}
					} else if ((yMovement == 1) && (startY < landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;

							} else {
								validMove = false;
							}
						}
					}

					else {
						validMove = false;
					}
				} else {
					int newY = e.getY() / 75;
					int newX = e.getX() / 75;
					if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
						if ((piecePresent(e.getX(), (e.getY())))
								&& ((((newX == (startX + 1) && (startX + 1 <= 7)))
										|| ((newX == (startX - 1)) && (startX - 1 >= 0))))
								&& ((((newY == (startY + 1))) || ((newY == (startY - 1)))))) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;

								if (startY == 6) {
									wSuccess = true;
								}
							} else {
								validMove = false;
							}
						} else {
							if (!piecePresent(e.getX(), (e.getY()))) {
								if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == 1) {
									if (startY == 6) {
										wSuccess = true;
									}
									validMove = true;

								} else {
									validMove = false;
								}
							} else {
								validMove = false;
							}
						}
					} else {
						validMove = false;
					}
				}
			}
			/*
			 * End of White Pawn
			 */
			/*
			 * Start of Black Pawn - Copy of White Pawn with moves reversed
			 * 
			 * Pawns move vertically forward one square, with the option to move two squares
			 * if they have not yet moved. Pawns are the only piece to capture different to
			 * how they move. Pawns capture one square diagonally in a forward direction.
			 * Pawns are unable to move backwards on captures or moves. Upon reaching the
			 * other side of the board a pawn promotes into any other piece, Queen for this
			 * CA
			 */
			else if (pieceName.equals("BlackPawn")) {
				if (startY == 6) {
					if ((startX == (e.getX() / 75))
							&& ((((e.getY() / 75) - startY) == -1) || ((e.getY() / 75) - startY) == -2)) {
						if ((((e.getY() / 75) - startY) == -2)) {
							if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
								validMove = true;

							} else {
								validMove = false;
							}
						} else {
							if ((!piecePresent(e.getX(), (e.getY())))) {
								validMove = true;

							} else {
								validMove = false;
							}
						}
					} else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
						if (piecePresent(e.getX(), e.getY())) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;

							} else {
								validMove = false;
							}
						}
					} else {
						validMove = false;
					}
				} else {
					int newY = e.getY() / 75;
					int newX = e.getX() / 75;
					if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
						if ((piecePresent(e.getX(), (e.getY())))
								&& ((((newX == (startX + 1) && (startX + 1 <= 7)))
										|| ((newX == (startX - 1)) && (startX - 1 >= 0))))
								&& ((((newY == (startY + 1))) || ((newY == (startY - 1)))))) {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
								if (startY == 1) {
									bSuccess = true;
								}
							} else {
								validMove = false;
							}
						} else {
							if (!piecePresent(e.getX(), (e.getY()))) {
								if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == -1) {
									if (startY == 1) {
										bSuccess = true;
									}
									validMove = true;
								} else {
									validMove = false;
								}
							} else {
								validMove = false;
							}
						}
					} else {
						validMove = false;
					}
				}
			}
			/*
			 * End of Black Pawn
			 */
			/*
			 * Start of Queen Queens move diagonally, horizontally, or vertically any number
			 * of squares. They are unable to jump over pieces.
			 */
			else if (pieceName.contains("Queen")) {
				Boolean inTheWay = false;
				int xDistance = Math.abs(startX - landingX);
				int yDistance = Math.abs(startY - landingY);
				// Has queen been moved off the board?
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				}
				/*
				 * This part of the code is copied from the Rook below
				 */

				else if (((xDistance != 0) && (yDistance == 0))
						|| ((xDistance == 0) && (Math.abs(landingY - startY) != 0))) {
					if (xDistance != 0) {
						if (startX - landingX > 0) {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX - (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < xMovement; i++) {
								if (piecePresent(initialX + (i * 75), e.getY())) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					} else {
						if (startY - landingY > 0) {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY - (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						} else {
							for (int i = 0; i < yMovement; i++) {
								if (piecePresent(e.getX(), initialY + (i * 75))) {
									inTheWay = true;
									break;
								} else {
									inTheWay = false;
								}
							}
						}
					}

					if (inTheWay) {
						validMove = false;
					} else {
						if (piecePresent(e.getX(), e.getY())) {
							if (pieceName.contains("White")) {
								if (checkWhiteOponent(e.getX(), e.getY())) {
									validMove = true;
								} else {
									validMove = false;
								}
							}

							else {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
								} else {
									validMove = false;
								}
							}

						} else {
							validMove = true;
						}
					}
				}

				/*
				 * This part of the code is copied from the Bishop below
				 */
				else {
					validMove = true;
					if (xDistance == yDistance) {
						if ((startX - landingX < 0) && (startY - landingY < 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX + (i * 75)), initialY + (i * 75))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX + (i * 75)), initialY - (i * 75))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX - (i * 75)), initialY - (i * 75))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX - (i * 75)), initialY + (i * 75))) {
									inTheWay = true;
								}
							}
						}
						if (inTheWay) {
							validMove = false;
						} else {
							if (piecePresent(e.getX(), e.getY())) {
								if (pieceName.contains("White")) {
									if (checkWhiteOponent(e.getX(), e.getY())) {
										validMove = true;
									} else {
										validMove = false;
									}
								}

								else {
									if (checkBlackOponent(e.getX(), e.getY())) {
										validMove = true;
									} else {
										validMove = false;
									}
								}

							} else {
								validMove = true;
							}
						}

					} else {
						validMove = false;
					}
				}
			}

			/*
			 * End of Queen
			 */

			/*
			 * Start of Knight *Knights move in an ‘L’ shape’: two squares in a horizontal
			 * or vertical direction, then move one square horizontally or vertically. They
			 * are the only piece able to jump over other pieces.
			 */
			else if (pieceName.contains("Knight")) {
				if (((xMovement == 1) && (yMovement == 2)) || ((xMovement == 2) && (yMovement == 1))) {
					if (!piecePresent(e.getX(), e.getY())) {
						validMove = true;
					} else {
						if (pieceName.contains("White")) {
							if (checkWhiteOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						} else {
							if (checkBlackOponent(e.getX(), e.getY())) {
								validMove = true;
							}
						}
					}
				}
			}
			/*
			 * End of Knight
			 */

			/*
			 * Start of King Kings move one square in any direction, so long as that square
			 * is not attacked by an enemy piece.
			 */

			else if (pieceName.contains("King")) {
				// the statement below checks if the piece is placed on the board, if not its an
				// invalid move
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				}
				/* One space at a time and at least one space moved */
				else if (xMovement > 1 || yMovement > 1 || (xMovement == 0 && yMovement == 0)) {
					validMove = false;
				} else {
					if (!(checkKingSpace(e.getX(), e.getY()))) {
						if (!piecePresent(e.getX(), e.getY())) {
							validMove = true;
						} else {
							if (pieceName.contains("White")) {
								if (checkWhiteOponent(e.getX(), e.getY())) {
									validMove = true;
								}
							} else {
								if (checkBlackOponent(e.getX(), e.getY())) {
									validMove = true;
								}
							}
						}
					}
				}
			}

			/*
			 * End of King
			 */

			/*
			 * Start of Bishop Bishops move diagonally any number of squares. They are
			 * unable to jump over pieces. this code is based on the Practical Workbook for
			 * CA2
			 */
			else if (pieceName.contains("Bishop")) {

				Boolean inTheWay = false;
				int xDistance = Math.abs(startX - landingX);
				int yDistance = Math.abs(startY - landingY);
				// the statement below checks if the piece is placed on the board, if not its an
				// invalid move
				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				} else {
					validMove = true;
					if (xDistance == yDistance) {
						if ((startX - landingX < 0) && (startY - landingY < 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX + (i * 75)), initialY + (i * 75))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX + (i * 75)), initialY - (i * 75))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX - (i * 75)), initialY - (i * 75))) {
									inTheWay = true;
								}
							}
						} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
							for (int i = 0; i < xDistance; i++) {
								if (piecePresent((initialX - (i * 75)), initialY + (i * 75))) {
									inTheWay = true;
								}
							}
						}
						if (inTheWay) {
							validMove = false;
						} else {
							if (piecePresent(e.getX(), e.getY())) {
								if (pieceName.contains("White")) {
									if (checkWhiteOponent(e.getX(), e.getY())) {
										validMove = true;
									} else {
										validMove = false;
									}
								}

								else {
									if (checkBlackOponent(e.getX(), e.getY())) {
										validMove = true;
									} else {
										validMove = false;
									}
								}

							} else {
								validMove = true;
							}
						}

					} else {
						validMove = false;
					}
				}
			}
			/*
			 * End of Bishop
			 */

			/*
			 * Start of Rook Rooks move horizontally or vertically any number of squares.
			 * They are unable to jump over pieces.
			 */
			else if (pieceName.contains("Rook")) {

				Boolean inTheWay = false;
				int xDistance = Math.abs(startX - landingX);
				int yDistance = Math.abs(startY - landingY);

				if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
					validMove = false;
				} else {
					if (((xDistance != 0) && (yDistance == 0))
							|| ((xDistance == 0) && (Math.abs(landingY - startY) != 0))) {
						if (xDistance != 0) {
							if (startX - landingX > 0) {
								for (int i = 0; i < xMovement; i++) {
									if (piecePresent(initialX - (i * 75), e.getY())) {
										inTheWay = true;
										break;
									} else {
										inTheWay = false;
									}
								}
							} else {
								for (int i = 0; i < xMovement; i++) {
									if (piecePresent(initialX + (i * 75), e.getY())) {
										inTheWay = true;
										break;
									} else {
										inTheWay = false;
									}
								}
							}
						} else {
							if (startY - landingY > 0) {
								for (int i = 0; i < yMovement; i++) {
									if (piecePresent(e.getX(), initialY - (i * 75))) {
										inTheWay = true;
										break;
									} else {
										inTheWay = false;
									}
								}
							} else {
								for (int i = 0; i < yMovement; i++) {
									if (piecePresent(e.getX(), initialY + (i * 75))) {
										inTheWay = true;
										break;
									} else {
										inTheWay = false;
									}
								}
							}
						}

						if (inTheWay) {
							validMove = false;
						} else {
							if (piecePresent(e.getX(), e.getY())) {
								if (pieceName.contains("White")) {
									if (checkWhiteOponent(e.getX(), e.getY())) {
										validMove = true;
									} else {
										validMove = false;
									}
								}

								else {
									if (checkBlackOponent(e.getX(), e.getY())) {
										validMove = true;
									} else {
										validMove = false;
									}
								}

							} else {
								validMove = true;
							}
						}
					} else {
						validMove = false;
					}
				}
			}
		}

		/*
		 * End of Rook
		 */
		if (!validMove) {
			int location = 0;
			if (startY == 0) {
				location = startX;
			} else {
				location = (startY * 8) + startX;
			}
			String pieceLocation = pieceName + ".png";
			pieces = new JLabel(new ImageIcon(pieceLocation));
			panels = (JPanel) chessBoard.getComponent(location);
			panels.add(pieces);
		} else {
			nextMove();
			if (wSuccess) {
				int location = 56 + (e.getX() / 75);
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				} else {
					Container parent = (Container) c;
					pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				}
			}

			else if (bSuccess) {
				int location = 0 + (e.getX() / 75);
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				} else {
					Container parent = (Container) c;
					pieces = new JLabel(new ImageIcon("BlackQueen.png"));
					parent = (JPanel) chessBoard.getComponent(location);
					parent.add(pieces);
				}
			} else {
				if (c instanceof JLabel) {
					Container parent = c.getParent();
					parent.remove(0);
					parent.add(chessPiece);
				} else {
					Container parent = (Container) c;
					parent.add(chessPiece);
				}
				chessPiece.setVisible(true);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	/*
	 * Main method that gets the ball moving.
	 */
	public static void main(String[] args) {
		JFrame frame = new ChessProject();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
