package com.pedromoreirareisgmail.geoquiz.Game;

import com.pedromoreirareisgmail.geoquiz.Commons.Common;
import com.pedromoreirareisgmail.geoquiz.models.Answer;
import com.pedromoreirareisgmail.geoquiz.models.AnswerBut;
import com.pedromoreirareisgmail.geoquiz.models.Question;
import com.pedromoreirareisgmail.geoquiz.models.ThreeWrong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alternatives {

    public static AnswerBut getAlternatives(String qMain, int type, List<Answer> listAnswer) {

        //  Objeto Answer com resposta correta
        Answer correctAnswer = new Answer();
        String correctString = "";

        //  Lista de objetos com apenas com respostas erradas
        List<Answer> listAnswerWrongs = new ArrayList<>();

        for (Answer currentAnswer : listAnswer) {

            if (currentAnswer.getMain().equals(qMain)) {

                //   CORRETA
                correctAnswer = currentAnswer;

            } else {

                //  ERRADAS
                listAnswerWrongs.add(currentAnswer);

            }
        }


        //  Lista de Strings com alternativas erradas, separadas por tipo
        //  Se capital - Adiciona capitais
        //  Se flag/map - Adiciona nome main
        List<String> listWrong = new ArrayList<>();


        //  Gerando lista de acordo com tipo de jogo
        if (type == Common.TYPE_CAPITAIS) {

            for (int i = 0; i < listAnswerWrongs.size(); i++) {

                listWrong.add(listAnswerWrongs.get(i).getCapital().trim());
            }

            correctString = correctAnswer.getCapital().trim();

        } else if (type == Common.TYPE_FLAGS || type == Common.TYPE_MAPS) {

            for (int i = 0; i < listAnswerWrongs.size(); i++) {

                listWrong.add(listAnswerWrongs.get(i).getMain().trim());
            }

            correctString = correctAnswer.getMain().trim();

        }

        //  Objeto com 3 alternativas erradas
        ThreeWrong threeWrong = new ThreeWrong();
        threeWrong.setFirst("");
        threeWrong.setSecond("");
        threeWrong.setThird("");


        //  Seleciona aleatoriamente 3 alternativas erradas
        while (threeWrong.getFirst().isEmpty()
                || threeWrong.getSecond().isEmpty()
                || threeWrong.getThird().isEmpty()) {

            if (threeWrong.getFirst().isEmpty()) {   //  Seleciona 1ª alternativa ERRADA

                //  Altera ordem aleatoriamente
                Collections.shuffle(listWrong);
                Collections.shuffle(listWrong);

                //  Pega a 3ª alternativa da lista - poderia ser 1ª ou 2ª
                threeWrong.setFirst(listWrong.get(2));

            } else if (threeWrong.getSecond().isEmpty()) { //  Seleciona 2ª alternativa ERRADA

                do { //  Repete até que a 2ª alternativa seja diferente da 1ª

                    //  Altera ordem aleatoriamente
                    Collections.shuffle(listWrong);
                    Collections.shuffle(listWrong);

                    //  Pega a 4ª alternativa da lista - poderia ser 1ª ou 2ª
                    threeWrong.setSecond(listWrong.get(2));

                } while (threeWrong.getSecond().equals(threeWrong.getFirst()));

            } else if (threeWrong.getThird().isEmpty()) {  //  Seleciona 3ª Alternativa ERRADA

                do { //  Repete até que a 3ª seja diferente da 1ª e da 2ª


                    //  Altera ordem aleatoriamente
                    Collections.shuffle(listWrong);
                    Collections.shuffle(listWrong);

                    //  Pega a 4ª alternativa da lista - poderia ser 1ª ou 2ª
                    threeWrong.setThird(listWrong.get(2));

                } while (threeWrong.getThird().equals(threeWrong.getFirst())
                        || threeWrong.getThird().equals(threeWrong.getSecond()));

            }

        }

        //  Lista com as 4 alternativas - 3 Erradas e 1 Certa
        List<String> listToButs = new ArrayList<>();
        listToButs.add(correctString);
        listToButs.add(threeWrong.getFirst());
        listToButs.add(threeWrong.getSecond());
        listToButs.add(threeWrong.getThird());

        //  Altera a ordem aleatoriamente
        Collections.shuffle(listToButs);
        Collections.shuffle(listToButs);

        //  Cria objetos com as alternativas para resposta
        AnswerBut answerBut = new AnswerBut();

        //  Alternativas
        answerBut.setA(listToButs.get(0));
        answerBut.setB(listToButs.get(1));
        answerBut.setC(listToButs.get(2));
        answerBut.setD(listToButs.get(3));


        return answerBut;
    }

    public static String getCorrect(List<Question> listQuestion, int type, int currentIndex){

        String correct = "";

        if(type == Common.TYPE_CAPITAIS){

            correct = listQuestion.get(currentIndex).getCapital().trim();


        }else if(type == Common.TYPE_FLAGS || type == Common.TYPE_MAPS){

            correct = listQuestion.get(currentIndex).getMain().trim();
        }

        return correct;
    }
}
