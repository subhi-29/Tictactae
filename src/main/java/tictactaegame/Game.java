package org.example;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Tictactoe{
    static char[][] symbol;
    static PrintStream li=new PrintStream((new FileOutputStream(FileDescriptor.out)));
    public Tictactoe(){
        symbol=new char[3][3];
        gothrough();
    }
     static void gothrough(){
        for(int i=0;i<symbol.length;i++){
            for(int j=0;j<symbol[i].length;j++)
            {
                symbol[i][j]=' ';
            }
        }
    }
    static void displaytictactoe(){
        li.println("--------------");
        for(int i=0;i<symbol.length;i++){
            li.print("| ");
            for(int j=0;j<symbol.length;j++)
            {

                li.print(symbol[i][j]+" | ");
            }
            li.println();
            li.println("--------------");
        }
    }
    static void placesymbol(int row,int column,char mark){
        if(row>=0 && row<=2 && column>=0 && column<=2){
            symbol[row][column]=mark;
        }
        else {
            li.println("Invalid input");
        }
    }
    static boolean columnwin(){
        for(int j=0;j<=2;j++){
            if(symbol[0][j]!=' ' && symbol[0][j]==symbol[1][j] && symbol[1][j]==symbol[2][j]){
                return true;
            }
        }
        return  false;
    }
    static boolean rowwin(){
        for(int i=0;i<=2;i++){
            if(symbol[i][0]!=' ' && symbol[i][0]==symbol[i][1] && symbol[i][1]==symbol[i][2]){
                return true;
            }
        }
        return  false;
    }
    static boolean diagonalwin(){
        return(symbol[0][0]!=' ' && symbol[0][0]==symbol[1][1] && symbol[1][1]==symbol[2][2] || symbol[0][2]!=' ' && symbol[0][2]==symbol[1][1] && symbol[1][1]==symbol[2][0]);
           
    }
}
class Humanmove extends Tictactoe{
    String name;
    char symbol;
    Humanmove(String name1,char symbol1){
        this.name=name1;
        this.symbol=symbol1;
    }
    void move(){
        Scanner sc=new Scanner(System.in);
        int r;
        int c;
        do {
            li.println("Enter the row number:");
            r = sc.nextInt();
            li.println("Enter the column number:");
            c = sc.nextInt();
        }while (!validmove(r,c));
        Tictactoe.placesymbol(r,c,symbol);
    }
    boolean validmove(int row,int column){
        if(row>=0 && row<=2 && column>=0 && column<=2){
            if(Tictactoe.symbol[row][column]==' '){
                return true;
            }
        }
        return false;
    }
}
public class Game {
    public static void main(String[] args) {
        int count=0;
        Tictactoe ti=new Tictactoe();
        PrintStream li=new PrintStream((new FileOutputStream(FileDescriptor.out)));
        Scanner sc1=new Scanner(System.in);
        Scanner sc=new Scanner(System.in);
        li.println("Enter the two person names");
        String name1=sc1.nextLine();
        String name2=sc1.nextLine();
        li.println("Enter two Symbols to play X or O");
        char c1=sc.next().charAt(0);
        char c2=sc.next().charAt(0);
        Humanmove h1=new Humanmove(name1,c1);
        Humanmove h2=new Humanmove(name2,c2);
        Humanmove currentplayer;
        currentplayer=h1;
      while(true)
      {
          li.println(currentplayer.name + " Starts");
          currentplayer.move();
          count++;

          Tictactoe.displaytictactoe();
          if(Tictactoe.columnwin()||Tictactoe.rowwin()||Tictactoe.diagonalwin()){
              li.println(currentplayer.name+ " has won");
              break;
          }

          else{
              if(currentplayer==h1){
                  currentplayer=h2;
              }
              else{
                  currentplayer=h1;
              }
          }

        if(count>=9)
        {
            li.println("Draw match!!");
            break;
        }
      }
    }
}
