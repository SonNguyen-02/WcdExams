package com.mct.wcdexams.presentation.controller.category;

import com.mct.wcdexams.di.DataInjection;
import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.presentation.controller.BaseController;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category/detail")
public class Detail extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Category category = DataInjection.provideCategoryUseCases().getGetCategory().invoke(id);
            if (category == null) {
                goto404(resp);
                return;
            }
            req.setAttribute("val_name", category.getName());
            req.setAttribute("val_desc", category.getDesc());

            forwardTo(req, resp, "/category/detail.jsp");
        } catch (NumberFormatException e) {
            goto404(resp);
        }
    }
}
