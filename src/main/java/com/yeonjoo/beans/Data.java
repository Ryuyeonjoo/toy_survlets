package com.yeonjoo.beans;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.yeonjoo.toy_servlets.dao.Commons;

public class Data {

    String query_questions_list = "SELECT * FROM questions_list " + "ORDER BY ORDERS";
    String query_answers = "SELECT * FROM answers ";
    String query_example_list = "SELECT * FROM example_list " + "ORDER BY ORDERS";

    Commons commons;
    
    public Data(){
        //객체 생성시 mysql 접속
        this.commons = new Commons();
    }
    public ArrayList<ArrayList> getQuestions(){
        ArrayList<ArrayList> questions_list = new ArrayList<>();
        HashMap<String, String> question_list_question_uid = new HashMap<>();
        HashMap<String, String> question_list_questions = new HashMap<>();
        HashMap<String, Integer> question_list_orders = new HashMap<>();

        Statement statement = this.commons.getStatement();
        try {
            ResultSet resultSet = statement.executeQuery(query_questions_list);
            
            while (resultSet.next()){

                question_list_question_uid.put("questions_list", resultSet.getString("QUESTIONS_UID"));
                question_list_questions.put("questions", resultSet.getString("QUESTIONS"));
                question_list_orders.put("orders", resultSet.getInt("ORDERS"));

                ArrayList<HashMap<String, String>> arrTemp = new ArrayList<>();
                ArrayList<HashMap<String, Integer>> arrTempInteger = new ArrayList<>();

                arrTemp.add(question_list_question_uid);
                arrTemp.add(question_list_questions);
                arrTempInteger.add(question_list_orders);
                questions_list.add(arrTemp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions_list;
    }
    
    public ArrayList<ArrayList> getExamples(){
        ArrayList<ArrayList> example_list = new ArrayList<>();
        HashMap<String, String> example_list_question_uid = new HashMap<>();
        HashMap<String, String> example_list_questions = new HashMap<>();
        HashMap<String, Integer> example_list_orders = new HashMap<>();

        Statement statement = this.commons.getStatement();
        try {
            ResultSet resultSet = statement.executeQuery(query_example_list);
            
            while (resultSet.next()){

                example_list_question_uid.put("example_uid", resultSet.getString("EXAMPLE_UID"));
                example_list_questions.put("example", resultSet.getString("EXAMPLE"));
                example_list_orders.put("orders", resultSet.getInt("ORDERS"));

                ArrayList<HashMap<String, String>> arrTemp = new ArrayList<>();
                ArrayList<HashMap<String, Integer>> arrTempInteger = new ArrayList<>();

                arrTemp.add(example_list_question_uid);
                arrTemp.add(example_list_questions);
                arrTempInteger.add(example_list_orders);
                example_list.add(arrTemp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return example_list;
    }


    public ArrayList<ArrayList> getAnswers(){
        ArrayList<ArrayList> answers = new ArrayList<>();
        HashMap<String, String> answers_questions_uid = new HashMap<>();
        HashMap<String, String> answers_example_uid = new HashMap<>();

        Statement statement = this.commons.getStatement();
        try {
            ResultSet resultSet = statement.executeQuery(query_example_list);
            
            while (resultSet.next()){

                answers_questions_uid.put("questions_uid", resultSet.getString("QUESTIONS_UID"));
                answers_example_uid.put("example_uid", resultSet.getString("EXAMPLE_UID"));

                ArrayList<HashMap<String, String>> arrTemp = new ArrayList<>();

                arrTemp.add(answers_questions_uid);
                arrTemp.add(answers_example_uid);
                answers.add(arrTemp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }
}