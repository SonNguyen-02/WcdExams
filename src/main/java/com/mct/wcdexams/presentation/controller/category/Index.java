package com.mct.wcdexams.presentation.controller.category;

import com.mct.wcdexams.presentation.controller.BaseController;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/category", "/category/"})
public class Index extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List.setupTable(req);
        forwardTo(req, resp, "/category/index.jsp");
    }

}
