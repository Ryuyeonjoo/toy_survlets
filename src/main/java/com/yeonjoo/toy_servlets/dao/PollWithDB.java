package com.yeonjoo.toy_servlets.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class PollWithDB {
    public HashMap<String, Object> getQuestion(String questionsUid) throws SQLException{

        Commons commons = new Commons();
        Statement statement = commons.getStatement();
        
        String query = "SELECT * FROM QUESTIONS_LIST " +
                        " WHERE QUESTIONS_UID = '" + questionsUid + "'";
        ResultSet resultSet = statement.executeQuery(query);
        
        HashMap<String, Object> result = null;
        while(resultSet.next()){
            result = new HashMap<>();
            result.put("QUESTIONS_UID",resultSet.getString("QUESTIONS_UID"));
            result.put("QUESTIONS", resultSet.getString("QUESTIONS"));
            result.put("ORDERS",resultSet.getInt("ORDERS"));
        }

        return result;
    }

    public ArrayList<String> getExample(String questionsUid) throws SQLException{
        Commons commons = new Commons();
        Statement statement = commons.getStatement();
        ArrayList<String> example_Uid = new ArrayList<>();
        ArrayList<String> example = new ArrayList<>();
        String query = "SELECT * FROM answers WHERE QUESTIONS_UID ='" + questionsUid + "'";
        ResultSet resultSet = statement.executeQuery(query);
        //답항 갯수만큼 example_uid 저장
        String query_ex = "SELECT * FROM example_list WHERE ";
        while(resultSet.next()){
            example_Uid.add(resultSet.getString("EXAMPLE_UID"));
            if(resultSet.isLast()){
                query_ex +="EXAMPLE_UID='"+resultSet.getString("EXAMPLE_UID")+"'";
                query_ex +=" order by 'ORDERS'";
            }else{
                query_ex +="EXAMPLE_UID='"+resultSet.getString("EXAMPLE_UID")+"'"+"||";
                
            }
            
        }
        example = getExampleSub(example_Uid, query_ex);
        for(int i = 0; i<example.size(); i++){
            System.out.println((String)example.get(i).getClass().getName());
        }
        return example;
    }
    public ArrayList<String> getExampleSub(ArrayList<String> example_Uid,  String query_ex) throws SQLException{
        Commons commons = new Commons();
        Statement statement = commons.getStatement();
        ArrayList<String> example = new ArrayList<>();

        //SELECT * FROM example_list WHERE EXAMPLE_UID ="E1" || EXAMPLE_UID ="E2" || EXAMPLE_UID ="E3";
         // 
        ResultSet resultSet = statement.executeQuery(query_ex);
        while(resultSet.next()){
            example.add(resultSet.getString("EXAMPLE"));
        }
        return example;
    }
}