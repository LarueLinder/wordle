import java.util.Scanner;
import java.util.Random;

/**
 * Project 2: Wordle
 *
 * This project has you create a text based version of Wordle
 * (https://www.nytimes.com/games/wordle/index.html). Wordle is a word guessing
 * game in which you have 6 tries to guess a 5-letter word. You are told whether
 * each letter of your guess is in the word and in the right position, in the
 * word but in the wrong position, or not in the word at all.
 *
 * Some key differences in our version are:
 *
 * - Text menu based with no grid. Players have to scroll up to see their
 * previous guesses.
 *
 * - Support for 4, 5, or 6 letter words
 *
 * - We don't check for whether a guess is a valid word or not. Players can
 * guess anything they want (of the correct length).
 *
 * Fun facts: The original Wordle was developed by Josh Wardle. Wardle's wife
 * chose the official word list for the game.
 *Larue Linder 
 *10/6/22
 */
public class Wordle {

   /**
    * Defining the only Random variable you may (and must) use. DO NOT CHANGE
    * THIS LINE OF CODE.
    */
   static Random gen = new Random(0);

   /**
    * Defines the number of guesses the player starts with for each word. DO NOT
    * CHANGE THIS LINE OF CODE.
    */
   static final int MAX_GUESSES = 6;  
   /**
    * Defines the number of hints the player starts with for each word. DO NOT
    * CHANGE THIS LINE OF CODE.
    */
   static final int MAX_HINTS = 2; 
  
   static int maxGuesses = MAX_GUESSES; //need to be able to edit 
   //guesses in multiple methods 
   static int maxHints = MAX_HINTS; //need to be able to edit hints 
   //in multiple methods 
   static String wordInputted; //this is the word the user inputted
   static String t; //meant to hold text variable in main 
   //and allow for it to be used in other methods
   static String userGuess; //stores the users guess
   static String output; //this is the feedback. gets outputted after each guess
 

   /**
    * The main method. This is where most of your menu logic and game logic
    * (i.e. implementation of the rules of the game ) will end up. Feel free to
    * move logic in to smaller subroutines as you see fit.
    *
    * @param args commandline args
    */
   public static void main(String[] args) {
      
      newWord(); // create one new word 
      //if user wants any additional words they need to type n or N themseleves
      userChoice();
      
      if (maxGuesses > 0) {
         userChoice();
      }
      else {
         endRun(); 
      }
        
   }
   /**
   * This collects the users input and controls alot of the functionality 
   *of the program. Calls each neccesary method depending on input 
   */
   
   public static void userChoice() {
      printMenu();      
      Scanner kb = new Scanner(System.in);
      System.out.print("Please enter a choice: ");
      String text = kb.nextLine();
      t = text;
      
      if ("n".equals(t) || "N".equals(t)) {
         newWord();
      } else if ("h".equals(t) || "H".equals(t)) {
         giveHint(wordInputted);
         userChoice();
      } else if ("g".equals(t) || "G".equals(t)) {
          
         if (maxGuesses > 0) {
            usersGuess();
         } 
         else {
            System.out.println("Sorry, you're out of guesses! "  
               + "Use the \"n\"/\"N\" command to play again.");
            userChoice(); // check this 
         }
         
         checkGuess(wordInputted, userGuess);
       
      }
         
      else if ("e".equals(t) || "E".equals(t)) {
         endRun();
         
      } else {
         System.out.println("Invalid option! try again! ");
         //calls itself to repeat process until valid choice.
         userChoice();
      } 
   }
   

   /**
   * if user clicks e to end run.
   */
   public static void endRun() {
      
      System.exit(0);
   }
   
   /**
   *takes the usersGuess and stores it into a static variable only if.
   *validateGuess is true otherwise it repeatedly asks for a new guess 
   *until the user inputs a valid one 
   */
   
   public static void usersGuess() {
      
      Scanner scnr = new Scanner(System.in);
      System.out.println("Enter your guess ");
      userGuess = scnr.next();
      
      //this loop runs while the guess is invalid.
      //it uses validate guess method to make sure 
      //length and characters are valid 
      //once valid it stores the input as the guess 
      while (!validateGuess(userGuess.length(), userGuess)) {  
         
         System.out.println("Your guess must contain only "
            + "upper case and lower case letters. ");
         userGuess = scnr.next(); 
      }
      
   }
   
   /**
    * Prints "HINT! The word contains the letter: X" where X is a randomly
    * chosen letter in the word parameter.
    *
    * @param word The word to give a hint for.
    */
   
   static void giveHint(String word) {
      String str = word;
      int len = str.length();
      int rand = gen.nextInt(len - 1);    
      char hint = str.charAt(rand);
      maxHints--; //decrements hints left by 1
      
      if (maxHints > 0) {
         
         System.out.println("HINT! The word contains the letter: " + hint);
      }
      
      if (maxHints == 1) {
         System.out.println("You have 1 hint remaining. ");
      } else if (maxHints == 0) {
         System.out.println("HINT! The word contains the letter: " + hint);
         System.out.println("You have 0 hints remaining. ");
      } else {
         System.out.println("Sorry, you're out of hints! ");
      }
      
      
   }

   /**
    * Checks the players guess for validity. We define a valid guess as one that
    * is the correct length and contains only lower case letters and upper case
    * letters. If either validity condition fails, a message is printed 
    * indicating which condition(s) failed.
    *
    * @param length The length of the current word that the player is trynig to
    *               guess.
    * @param guess  The guess that the player has entered.
    * @return true if the guess is of the correct length and contains only valid
    * characters, otherwise false.
    */
   static boolean validateGuess(int length, String guess) {
      
      int len = wordInputted.length(); //needs to check if lengths match 
      String g = guess; 
      boolean bool = checkLetter(guess);
      
      if (bool && (len == g.length())) {
         return bool;
        
      } else {
         return false; 
      }
   }
   /**
   *uses ascii values to see if every char in String inputted is a letter
   *it gets the value for bool in ablove 'validateGuess' method 
   *ascii values uppercases from 65 - 90 lowercase from 97 - 122 
   *@param guess the guess the player has entered
   *@return true if all values in users guess is a letter a-z or A-Z
   *it returns false otherwise
   */
   
   static boolean checkLetter(String guess) {
      boolean bool = true;
    
      for (int i = 0; i < guess.length(); i++) {
         if ((guess.charAt(i) >= 65 && guess.charAt(i) <= 90) || 
            (guess.charAt(i) >= 97 && guess.charAt(i) <= 122)) {
            bool = true;
         } else {
            bool = false; 
            return bool;          
         }
      }
      return bool;
   }

   /**
    * Checks the player's guess against the current word. Capitalization is
    * IGNORED for this comparison. This function also prints a string
    * corresponding to the player's guess. A ? indicates a letter that isn't in
    * the word at all. A lower case letter indicates that the letter is in the
    * word but not in the correct position. An upper case letter indicates a
    * correct letter in the correct position. Example:
    *
    * SPLINE (the correct word)
    *
    * SPEARS (the player's guess)
    *
    * SPe??s (the output printed by this function)
    *
    * @param word  The current word the player is trying to guess.
    * @param guess The guess that a player has entered.
    * @return true if the word and guess match IGNORING CASE, otherwise false.
    */
   static boolean checkGuess(String word, String guess) {
      String g = guess.toUpperCase(); 
      
      for (int i = 0; i < g.length(); i++) {
        
         char c = g.charAt(i);
         
         if (word.charAt(i) == c) {
            output = g.replace(g.charAt(i), c);
            g = output;
            
         } 
         else if (word.contains(Character.toString(c))) {
            char l = Character.toLowerCase(c);
            output = g.replace(g.charAt(i), l);
            g = output; 
            
         } else {
             
            output = g.replace(g.charAt(i), '?');
            g = output;
         } 
      } 
      System.out.println(output); 
     
      maxGuesses--;
     
      if (g.equals(word)) {
         System.out.println("Congrats! You Won! ");
         maxGuesses = 0;
         
         userChoice();
         return true;
      } 
     
     
      if (maxGuesses == 1) {
         System.out.println("You have 1 guess remaining!");
      } 
      else if (maxGuesses == -1) {
         System.out.print(""); //stops it from printing -1 guesses left
      }
      else {
         System.out.println("You have " + maxGuesses + " guesses remaining.");
      } 
      
 
      if (output.equals(word)) {
         System.out.println("the words match ");
         return true;
      } else {
         userChoice();
      }
      return false; //should never do this just need for "return statement"
   }

   /**
    * Chooses a random word using WordProvider.getWord(int length). This should
    * print "New word length: X" where x is the length of the word.
    *
    * @return the randomly chosen word
    */
   static String newWord() {

      maxGuesses = 6; //resets max guesses for new word
      maxHints = 2; //resets max hints for new word 
      
      int wordLength = gen.nextInt(3) + 4;
      wordInputted = WordProvider.getWord(wordLength);
      System.out.println("New word Length: " + wordInputted.length());
      userChoice();
      return wordInputted; 
   }

   /**
    * Prints menu options.
    */
   static void printMenu() {
      System.out.println("n/N: New word");
      System.out.println("h/H: Get a hint");
      System.out.println("g/G: Make a guess");
      System.out.println("e/E: Exit");
      System.out.println("-------------");
      
   }
}
