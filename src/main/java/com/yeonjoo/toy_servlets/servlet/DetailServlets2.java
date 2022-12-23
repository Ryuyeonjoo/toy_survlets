package com.yeonjoo.toy_servlets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.yeonjoo.beans.Data;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pollServlet2")
public class DetailServlets2 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Data data = new Data();

        ArrayList<ArrayList> question = data.getQuestions();
        ArrayList<ArrayList> example_list = data.getExamples();
        ArrayList<ArrayList> answers = data.getAnswers();


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        // HashMap<String, String> a = new HashMap<>();
        // a = (HashMap<String, String>) answers.get(2).get(1);
        // pw.println("<div>Test</div>");
        // pw.println(a.get("questions"));

        //이동!
        req.setAttribute("question", question);
        req.setAttribute("example_list", example_list);
        req.setAttribute("answers", answers);

        RequestDispatcher reqDispatcher = req.getRequestDispatcher("/details.jsp");
        reqDispatcher.forward(req, resp);
        pw.close();
    }
}