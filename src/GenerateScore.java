/**
 * ******************************
 * Copyright 2015: smanna@cpp.edu
 * CS 241
 * PA4
 * ******************************
 * !!!WARNING!!! You are not allowed
 * to make any changes to this code.
 */

import java.io.File;
import java.util.Scanner;

public class GenerateScore {

  private int score;

  public GenerateScore(String test) {
    score = 0;
    grade(test);
  }

  private void grade(String test) {

    try {
      Scanner answers = new Scanner(new File(test));
      Scanner reader;
      KnightsTour tour = new KnightsTour(4);
      Boolean grade = false;
      String pathPresent = "";
      String studentPathPresent = "";
      String answer = "";
      String studentAnswer = "";
      int cases = 0;
      int start;
      int finish;

      //print header
      printBorders();
      System.out.printf("|%-40s|%-40s|%-11s|%-11s","Student Ans","Answer",
                        "StudentPath","PathAnswer");

      //grade attempts

      while (answers.hasNextLine()) {
        try {
        cases += 2; //2 points possible per line

        //set up answers
        answer = answers.nextLine().trim();
        reader = new Scanner(answer);
        reader.next();
        start = reader.nextInt();
        reader.next();
        finish = reader.nextInt();
        pathPresent = reader.next();
        answer = reader.nextLine();



        //set up student attempts
        studentAnswer = tour.findPath(start, finish).trim();
        reader = new Scanner(studentAnswer);
        studentPathPresent = reader.next();
        studentAnswer = reader.nextLine();
        reader.close();

        //grade student path present
        grade = (studentPathPresent.equals(pathPresent));
        if (grade) {
          score++;
        }
        grade = false; //reset

        //grade
        grade = (studentAnswer.equals(answer));
        if (grade) {
          score++;
        }

        //print attempts, answers, score
        printBorders();
        System.out.printf("|%-40s|%-40s|%-12s|%-12s",studentAnswer,answer,
                          studentPathPresent,pathPresent);


        //catch student's exceptions
        } catch (java.util.NoSuchElementException a) {
          printBorders();
          System.out.printf("|%-40s|%-40s|%-12s|%-12s","error w/ findPath output format ",
                            answer,"error",pathPresent);

        } catch (NullPointerException b) {
          printBorders();
          System.out.printf("|%-40s|%-40s|%-12s|%-12s",null,answer,null,pathPresent);

        } catch (Exception c) {
          c.printStackTrace();
        }

      }
      //print final score
      printBorders();
      System.out.println("Score = " + score + "/" + cases);
      answers.close();

    } catch (java.io.FileNotFoundException a) {
      System.out.println("Answer file not found");
    }
  }

  private void printBorders() {
    System.out.print("\n|");
    for (int i = 0; i < 104; i++) System.out.print("-");
    System.out.print("|\n");
  }

  public static void main(String[] args) {
    String golden_file;
    if (args.length < 1 || args[0].equals("test")) {
      golden_file = "data/test.txt";
    } else if (args[0].equals("eval")) {
      golden_file = "data/eval.txt";
    } else {
      System.out.println("Unknown option: " + args[0]);
      System.exit(1);
      return;
    }
    new GenerateScore(golden_file);

  }
}
