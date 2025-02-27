package tictactaegame;
import java.io.PrintStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.*;
class Tictactae
{
	static PrintStream l=new PrintStream((new FileOutputStream(FileDescriptor.out)));
	static char[][]board=new char[3][3];
	Tictactae(){
		initBoard();
	}
	static void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board.length;j++)
			{
				board[i][j]=' ';
			}
		}
	}
	void printboard()
	{
		l.println("-------");
		for(int i=0;i<board.length;i++)
		{
			l.print("|");
			for(int j=0;j<board.length;j++)
			{
				l.print(board[i][j]+ "|");
			}
			l.println();
			l.println("-------");
		}
	}
	static void placeMark(int row,int col,char mark) {
		board[row][col]=mark;
	}
	static boolean checkRow()       
	{
		for(int i=0;i<3;i++) {
		if(board[i][0]!=' ' && board[i][0] == board[i][1] && board[i][1]== board[i][2]) {
			return true;
		}
		}
		return false;
	}
	static boolean checkcol()
	{
		for(int j=0;j<3;j++) 
		{
			if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
			{
				return true;
		        }
		}
		return false;
	}
	static boolean checkDiag() {
		return board [0][0]!=' ' && board[0][0] ==  board[1][1] && board[1][1] ==  board[2][2] && board[0][0] == board[2][2] || board[0][2]!=' ' && board[0][2] == board[1][1] && board[1][1]==board[2][0];
		
	}
}
class HumanPlayer 
{
	String name;
	char mark;
	HumanPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	 
	void makeMove()
	{
	Scanner scan=new Scanner(System.in);
	int row;
	int col;
	do
	{
		Tictactae.l.println("enter row and col:");
		row=scan.nextInt();
		col=scan.nextInt();
	}while(!validMove(row,col));
	Tictactae.placeMark(row,col,mark);
	}
	boolean validMove(int row,int col)
	{
		return row>=0 && row<=2 && col>=0 && col<=2 && Tictactae.board[row][col]==' ';
		
	}
}
public class Game 
{
    public static void main( String[] args )
    {
    Tictactae tic=new Tictactae();
    Scanner scan=new Scanner(System.in);
    Scanner scan1=new Scanner(System.in);
    char mark;
    char mark1;
    int t=0;
    int count=0;
    tic.printboard();
    Tictactae.l.println("Enter Player1");
    String name=scan.nextLine();
    Tictactae.l.println("Choose X  OR O");
    mark=scan.next().charAt(0);
    HumanPlayer player1=new HumanPlayer(name,mark);
    
    Tictactae.l.println("Enter Player2");
    String name1=scan1.nextLine();
    if(mark=='X') {
    mark1='O';
    }else {
    	mark1='X';
    }
    HumanPlayer player2=new HumanPlayer(name1,mark1);
    
    HumanPlayer cp;
    cp = player1;
    
    while(count<9) {
    Tictactae.l.println(cp.name + "Turn");
    cp.makeMove();
    tic.printboard();
    if(Tictactae.checkRow() || Tictactae.checkcol() || Tictactae.checkDiag() )
    {
    	Tictactae.l.println(cp.name +"Win");
    	t++;
    	break;
    }
    else
    {
	count++;
    	if(cp==player1)
    	{
    		cp=player2;
    	}
    	else
    	{
    		cp=player1;
    	}
    }
    }
    if(t==0) {
    	Tictactae.l.println("Match is Draw");
    }
    }
    
      
}
