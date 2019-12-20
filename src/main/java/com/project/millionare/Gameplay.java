package com.project.millionare;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gameplay {

    ArrayList<String> gameQuestions;
    int levelQuestion;
    String gameplayQuestion;
    String[] gameplayAnswers;
    String gameplayAnswer;
    String gameActivRightAnswer;



    @Override
    public boolean equals(Object obj) {
        if (this ==  obj) return true;
        Question that = (Question) obj;
        if (this ==  null) return false;
        return that == null;
    }

    public void playGame()throws FileNotFoundException {

        if(printStartGame()) {
            Player player = new Player();
            Question question = new Question();
            gameQuestions = collectGameQuestions(gameplayQuestion);
            levelQuestion = calculateLevelQuestion(gameQuestions);
            question.questionRead(levelQuestion);
            gameplayQuestion = question.getActiveQuestion();
            question.readAnswersQuestion(gameplayQuestion);
            gameplayAnswers = question.getActiveAnswers();
            gameQuestions = collectGameQuestions(gameplayQuestion);
            player.setPlayerGameQuestions(gameQuestions);
            player.actionPlayer(gameplayQuestion, gameplayAnswers);
            gameplayAnswer = player.getAnswerPlayer();
            gameActivRightAnswer = question.readRightAnswerQuestion(gameplayAnswers);

            int countAnswer = 1;
            boolean playGameAnswer = false;

            if (countAnswer == 1 && isRightAnswer(gameplayAnswer, gameActivRightAnswer)) {
                System.out.println("\nПоздравляем Ваш ответ правильный !!!");
                calculateSumWin(gameQuestions);
                playGameAnswer = continuePlayGame();
            }

            while (isRightAnswer(gameplayAnswer, gameActivRightAnswer) && playGameAnswer && countAnswer < 15) {

                while (isRepetitionQuestion(gameQuestions, gameplayQuestion)) {
                    levelQuestion = calculateLevelQuestion(gameQuestions);
                    question.questionRead(levelQuestion);
                    gameplayQuestion = question.getActiveQuestion();
                }

                question.readAnswersQuestion(gameplayQuestion);
                gameplayAnswers = question.getActiveAnswers();
                gameQuestions = collectGameQuestions(gameplayQuestion);
                player.setPlayerGameQuestions(gameQuestions);
                player.actionPlayer(gameplayQuestion, gameplayAnswers);
                gameplayAnswer = player.getAnswerPlayer();
                gameActivRightAnswer = question.readRightAnswerQuestion(gameplayAnswers);
                if (isRightAnswer(gameplayAnswer, gameActivRightAnswer)) {
                    System.out.println("\nПоздравляем Ваш ответ правильный !!!");
                    calculateSumWin(gameQuestions);
                    countAnswer++;
                    if (countAnswer < 15) {
                        playGameAnswer = continuePlayGame();
                    }
                }
            }

            if (!isRightAnswer(gameplayAnswer, gameActivRightAnswer)) {
                System.out.println("\nВаш ответ не верный, Вы проиграли!!!");
                calculateGuaranteedSumWin(gameQuestions);
                System.out.println("\nGame over!!!");
            } else if (!playGameAnswer) {
                calculateSumWin(gameQuestions);
                System.out.println("\nGame over!!!");
            } else if (countAnswer == 15) {
                System.out.println("\nПоздравляем Вы миллионер, игра закончена!!! ");
            }
        }

        Scanner in = new Scanner(System.in);
        int a = 0;


        System.out.println("\nХотите еще раз попробовать сыграть в игру? Да - 1 ; Нет - 0 и нажмите ENTER : \n");

        a = in.nextInt();

        while (a != 1 && a != 0) {

            System.out.println("\nДанные введены некорректно, введите 0 или 1!! \n");
            a = in.nextInt();

        }

        if (a == 1) {
            gameplayQuestion = null;
            playGame();
        } else {
            System.out.println("СПАСИБО ЗА ИГРУ!");
            return;
        }

    }






    boolean isRightAnswer(String answerPlayer, String activeRightAnswer){
        if (answerPlayer.equals(activeRightAnswer)){
            return true;

        }else{
            return false;
        }

    }


    ArrayList<String> collectGameQuestions(String gameplayQuestion){
        if(gameplayQuestion == null){
            ArrayList<String> gameQuestions = new ArrayList<String>();
            gameQuestions.add("0");
            return  gameQuestions;
        } else if(gameQuestions.get(0) == "0"){
            gameQuestions.remove("0");
        }
        gameQuestions.add(gameplayQuestion);
        return  gameQuestions;

    }

    boolean isRepetitionQuestion(ArrayList<String> gameQuestions, String activeQuestion){

        for(String str : gameQuestions){
            if(str.equals(activeQuestion)){
                return true;
            }


        }
        return false;
    }

    int calculateLevelQuestion(ArrayList<String> gameQuestions){
         if(gameQuestions.size() >= 5 && gameQuestions.size() < 10){
             return 20;
         } else if(gameQuestions.size() >= 10){
             return 30;
         }else {
             return 10;
         }
    }


    int calculateSumWin(ArrayList<String> gameQuestions){
        int [] masSumWin = new int[]{0, 100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000
                , 64000, 125000, 250000, 500000, 1000000};

        int sumWin = masSumWin[gameQuestions.size()];
        System.out.println("\nВы выиграли : " + sumWin + " рублей");
        return sumWin;
    }

    int calculateGuaranteedSumWin(ArrayList<String> gameQuestions) {
        int[] masGuaranteedSumWin = new int[]{0, 500, 16000};
        int guaranteedSumWin;
        if (gameQuestions.size() <=10 && gameQuestions.size() > 5) {
            guaranteedSumWin = masGuaranteedSumWin[1];
            System.out.println("\nВы выиграли : " + guaranteedSumWin + " рублей");
        } else if (gameQuestions.size() <= 15 && gameQuestions.size() > 10) {
            guaranteedSumWin = masGuaranteedSumWin[2];
            System.out.println("\nВы выиграли : " + guaranteedSumWin + " рублей");
        } else {
            guaranteedSumWin = masGuaranteedSumWin[0];
            System.out.println("\nВы выиграли : " + guaranteedSumWin + " рублей");

        }
        return guaranteedSumWin;
    }

   boolean printStartGame(){
       System.out.println("Добро пожаловать в игру 'Кто хочет стать миллионером'\nНачать игру, 1 - Да, 0 - Нет? :\n");
       Scanner in = new Scanner(System.in);
       int play = in.nextInt();
       if(play == 1){
           System.out.println("Игра началась!!!\n");
           return true;
       }else {
           System.out.println("Игра закончилась не начавшись!!!");
           return false;
       }
   }

    boolean continuePlayGame(){
        System.out.println("\nПродолжить игру, введите 1 или завершить игру, введите 0 ?:  ");
        Scanner in = new Scanner(System.in);
        int answer = in.nextInt();
        if(answer == 1){
            return true;
        }else {
            return false;
        }
    }

}
