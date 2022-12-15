
package com.phoneshop.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phoneshop.dao.ProductDAO;
import com.phoneshop.entities.ProductEntity;
import com.phoneshop.enums.ProductType;
import com.phoneshop.phonecase.PhonecaseDAO;
import com.phoneshop.phonecase.PhonecaseDTO;
import com.phoneshop.phones.PhoneDAO;
import com.phoneshop.phones.PhoneDTO;
import com.phoneshop.shopping.Cart;

@WebServlet(name = "GetDetail", urlPatterns = { "/GetDetail" })
public class GetDetail extends HttpServlet {
	private static final String ERROR = "404.html";
	private static final String SUCCESS = "/single-product.jsp";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url = "/single-product.jsp";
		try {
			String ID = request.getParameter("ID");
			//PhoneDAO dao = new PhoneDAO();
			ProductDAO dao = new ProductDAO();
			List<PhoneDTO> productList = dao.getListProductByType(ProductType.PHONE).stream().map(product -> new PhoneDTO(product))
					.collect(Collectors.toList());
            
            if (!productList.isEmpty()) {
                request.setAttribute("ACTIVE_PRODUCT_LIST", productList);

                url = SUCCESS;
            }
			
            ProductEntity productEntity = dao.getProductById(Long.valueOf(ID));
			PhoneDTO product = new PhoneDTO(productEntity);
				if (product != null) {
					request.setAttribute("PRODUCT_DETAIL", product);
				}
			

			

		} catch (Exception e) {
			log("Error at GetDetail: " + e.toString());
		} finally {
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
