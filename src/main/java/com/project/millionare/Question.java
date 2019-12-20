package com.project.millionare;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Question  {

    private String activeQuestion;
    private String [] activeAnswers;


    public String[] getActiveAnswers() {
        return activeAnswers;
    }

    public String getActiveQuestion() {
        return activeQuestion;
    }



    @Override
    public boolean equals(Object obj) {
        if (this ==  obj) return true;
        Question that = (Question) obj;
        if (this ==  null) return false;
        return that == null;
    }



   String questionRead(int levelNewQuestion) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("C:\\1.txt"));
        Random random = new Random();

        int a = 0;

        while (a == 0) {
            a = random.nextInt(levelNewQuestion);
        }

        String c = String.valueOf(a);
        while (sc.hasNext()) {

            activeQuestion = sc.nextLine();
            String[] splitted = activeQuestion.split(" ");

            if (c.equals(splitted[0]) == true) {
                break;
            }

        }
        return activeQuestion;
    }


    String []  readAnswersQuestion(String activeQuestion) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("C:\\1.txt"));
        StringBuilder builder = new StringBuilder();
        while (sc.hasNext()) {

         String   temp = sc.nextLine();

            if (temp.equals(activeQuestion)) {
                for (int x = 0; x <= 4; x++){
                    builder.append(sc.nextLine());
                    builder.append("\n");
                }

            }

        }
        activeAnswers = builder.toString().split("\n");


        return  activeAnswers;

    }

    String readRightAnswerQuestion(String [] activeAnswers){
       String answer = null;
        for(String str : activeAnswers){
            if(str.contains("*")){
                answer = str;
                break;
            }

        }
        return answer;
    }
      void printQuestion(String activeQuestion, ArrayList<String> Questions){
          String numQuestion = String.valueOf(Questions.size());
          String temp = activeQuestion.replaceAll("[0-9]", "");
          char[] masQuestion = temp.toCharArray();
          masQuestion[0] = '1';
          String newString = new String(masQuestion);
          temp = newString.replaceAll("[0-9]", numQuestion + " ");
          System.out.println(temp);
      }

}