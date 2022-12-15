
package com.phoneshop.controller;
import com.phoneshop.dao.ProductDAO;
import com.phoneshop.enums.ProductType;
import com.phoneshop.phones.PhoneDAO;
import com.phoneshop.phones.PhoneDTO;
import com.phoneshop.phones.Type;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetShopPage2", urlPatterns = {"/GetShopPage2"})
public class GetShopPage2 extends HttpServlet {

    private static final String SUCCESS = "shop2.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "404.html";
        try {
        	ProductDAO dao = new ProductDAO();
			List<PhoneDTO> productList = dao.getListProductByType(ProductType.PHONE).stream().map(product -> new PhoneDTO(product))
					.collect(Collectors.toList());

            if (!productList.isEmpty()) {
                request.setAttribute("ACTIVE_PRODUCT_LIST", productList);

                url = SUCCESS;
            }
        } 
        catch (Exception e) {
            log("Error at GetShopPage: " + e.toString());
        }
        finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
