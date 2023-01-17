import java.util.Scanner;

public class checkGuessTester {

   public static void main(String [] args) {
           //next few lines are purely testing checkGuess method and functionality 
     // checkGuess("GATES", "HELLO"); // (word, Guess)
    
    
      String word = "ragesgsgsgsgahn";
      String g = word;
      
      word = g.replace('a','A');
      word = g.replace('s', '?');
      
      System.out.print(word);
      
   
   
   }







}