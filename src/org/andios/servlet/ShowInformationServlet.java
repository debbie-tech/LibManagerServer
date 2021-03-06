package org.andios.servlet;

import com.google.gson.Gson;
import org.andios.bean.ShowInformationBean;
import org.andios.service.ShowInformationService;
import org.andios.util.Constant;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by ZheWenYang on 2019/2/4
 */
@WebServlet(name = "ShowInformationServlet")
public class ShowInformationServlet extends HttpServlet {
    private ApplicationContext context=null;
    private String Sql=null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        String show_id=request.getParameter("show_id");
        Gson gson=new Gson();
        context = Constant.getContext();
        ShowInformationService showInformationService = (ShowInformationService) context.getBean("showInformationImplement");
        if (show_id!=null){
            Sql="from ShowInformationBean where show_id="+show_id;
        }else {
            Sql="from ShowInformationBean";
        }
        PrintWriter out = response.getWriter();
        List<ShowInformationBean> list= showInformationService.findUserByHQL(Sql);
        String stringList=gson.toJson(list);
        out.write(stringList);

    }
}
