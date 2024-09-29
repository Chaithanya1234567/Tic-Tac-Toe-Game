import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	public static char[][] board;
	public TicTacToe() {
		board=new char[3][3];
		init();
	}
	void init() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=' ';
			}
		}
	}
	static void display() {
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	static void mark(int row,int col,char c) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
		board[row][col]=c;
		}else {
			System.out.println("Invalid Position");
		}
	}
	public static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0]!=' '&& board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	public static boolean checkColWin() {
		for(int j=0;j<=2;j++) {
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) {
				return true;
			}
		}
		return false;
	}
	public static boolean checkDiaWin() {
		if((board[0][0]!=' ')&&(board[0][0]==board[1][1] && board[1][1]==board[2][2])||
				(board[0][2]!=' ')&&board[0][2]==board[1][1]&&board[1][1]==board[2][0]) {
			return true;
		}
		return false;
	}
	public static boolean checkDraw() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
}
abstract class Player{
	public String name;
	public char mark;
	abstract void makeMove();
	boolean isValid(int row,int col) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}	
}
class Human extends Player{
	public Human(String name,char mark) {
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("Enter the row and col ");
			row=scan.nextInt();
			col=scan.nextInt();
		}while(!isValid(row,col));
		TicTacToe.mark(row, col, mark);
   }
}
class AIPlayer extends Player{
	AIPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Random r=new Random();
		int row;
		int col;
		do {
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isValid(row,col));
		TicTacToe.mark(row,col,mark);
	}
}
public class LaunchGame {
	/* 2 HumanPlayers
//	public static void main(String[] args) {
//		TicTacToe game=new TicTacToe();
//		Human p1=new Human("Chay",'x');
//		Human p2=new Human("sam",'o');
//		Human cp;
//		cp=p1;
//		while(true) {
//			System.out.println(cp.name+" 's turn");
//			cp.makeMove();
//			TicTacToe.display();
//			if(TicTacToe.checkColWin()|| TicTacToe.checkDiaWin()||TicTacToe.checkRowWin()) {
//				System.out.println(cp.name+" Won the match");
//				break;
//			}else {
//				if(cp==p1) {
//					cp=p2;
//				}else {
//					cp=p1;
//				}
//			}
//		}
//	}
 */
	public static void main(String[] args) {
		TicTacToe game=new TicTacToe();
		System.out.println("Enter Your Name: ");
		Scanner scan=new Scanner(System.in);
		String name=scan.nextLine();
	Player p1=new Human(name,'x');
	Player p2=new AIPlayer("AI",'o');
	Player cp;
	cp=p1;
	while(true) {
	System.out.println(cp.name+" 's turn");
	cp.makeMove();
	TicTacToe.display();
	if(TicTacToe.checkColWin()||TicTacToe.checkDiaWin()||TicTacToe.checkRowWin()) {
		System.out.println(cp.name+" Won");
		break;
	}else if(TicTacToe.checkDraw()) {
		System.out.println("The Game is Draw");
		break;
	}else {
		if(cp==p1) {
			cp=p2;
		}else {
			cp=p1;
		}
	}
	}
	}
	
}
