package com.project.millionare;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

   private String answerPlayer;
   private ArrayList<String> playerGameQuestions;



    public void setPlayerGameQuestions(ArrayList<String> playerGameQuestions) {
        this.playerGameQuestions = playerGameQuestions;
    }

    public String getAnswerPlayer() {
        return answerPlayer;
    }



    @Override
    public boolean equals(Object obj) {
        if (this ==  obj) return true;
        Question that = (Question) obj;
        if (this ==  null) return false;
        return that == null;
    }


    String actionPlayer(String playerQuestion, String [] activeAnswers){
        Question question = new Question();
        question.printQuestion(playerQuestion, playerGameQuestions);
        for (String activeAnswer : activeAnswers){
            if(activeAnswer.contains("*")){
                activeAnswer = activeAnswer.replaceAll("[*]", "");
                System.out.println(activeAnswer);

            } else{
                System.out.println(activeAnswer);
            }

        }
        System.out.println("\nВведите вариант ответа a-d: ");

        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        for(String var : activeAnswers){
            String[] splittedVar = var.split(" ");
            if( temp.equals(splittedVar[0]) == true){
                temp = var;
                String printChangeAnswer = temp.replaceAll("[*]", "");
                System.out.println("Ваш ответ: " + printChangeAnswer + "\n");
                break;
            }
        }

         String rightAnswer = question.readRightAnswerQuestion(activeAnswers);


        System.out.println("Правильный ответ: " + rightAnswer);
        answerPlayer = temp;
        return answerPlayer;
    }



}
