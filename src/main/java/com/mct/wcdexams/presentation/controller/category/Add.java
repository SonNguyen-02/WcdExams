package com.mct.wcdexams.presentation.controller.category;

import com.mct.wcdexams.di.DataInjection;
import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.presentation.controller.BaseController;
import com.mct.wcdexams.presentation.internalmodel.Response;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/category/add")
public class Add extends BaseController {

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardTo(req, resp, "/category/add.jsp");
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");

        Category category = new Category();

        category.setName(name.length() == 0 ? null : name.trim());
        category.setDesc(desc.length() == 0 ? null : desc.trim());

        Map<String, String> errors = validateModel(category);

        if (DataInjection.provideCategoryUseCases().getCheckExistsCategory().invoke(category)) {
            errors.put("name", "Category name is exists!");
        }

        if (!errors.isEmpty()) {
            showResponse(resp, Response.error(errors).toJson());
            return;
        }

        long id = DataInjection.provideCategoryUseCases().getCreateCategory().invoke(category);
        if (id != -1) {
            showResponse(resp, Response.success("Add category success!").toJson());
        } else {
            showResponse(resp, Response.error("Add category false!").toJson());
        }

    }
}
