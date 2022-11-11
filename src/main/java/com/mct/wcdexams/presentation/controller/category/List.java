package com.mct.wcdexams.presentation.controller.category;

import com.mct.wcdexams.di.DataInjection;
import com.mct.wcdexams.domain.model.Category;
import com.mct.wcdexams.domain.use_cases.CategoryUseCases;
import com.mct.wcdexams.presentation.controller.BaseController;
import com.mct.wcdexams.utils.Paging;
import com.mct.wcdexams.utils.Validate;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category/list")
public class List extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setupTable(req);
        forwardTo(req, resp, "/category/list.jsp");
    }

    private static final int PAGE_SIZE = 5;

    public static void setupTable(@NotNull HttpServletRequest req) {
        String query = req.getParameter("q");
        String page = req.getParameter("p");
        int mPage = Validate.getValidPage(page, 1);
        CategoryUseCases useCases = DataInjection.provideCategoryUseCases();

        Paging<Category> paging = new Paging<>(
                useCases.getGetCategories().invoke(query, PAGE_SIZE, (mPage - 1) * PAGE_SIZE),
                useCases.getGetCategories().count(query), mPage, PAGE_SIZE);

        String url = "/category/list";
        if (query != null && !query.isEmpty()) {
            url += "?q=" + query + "&p=";
        } else {
            url += "?p=";
        }

        req.setAttribute("query", query);
        req.setAttribute("pageUrl", url);
        req.setAttribute("paging", paging);
    }
}
