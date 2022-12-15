package com.phoneshop.controller;

import java.io.IOException;
import java.util.Iterator;
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
import com.phoneshop.shopping.LineItem;

/**
 * Servlet implementation class AddToCart2
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ERROR = "/404.html";
	private static final String SUCCESS = "/cart.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String url = SUCCESS;
		// response.sendRedirect(url);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String url = "/cart.jsp";

		try {
			String action = request.getParameter("action");
			String ID = request.getParameter("ID");
			String productType = request.getParameter("type");

			ProductDAO dao = new ProductDAO();
			List<ProductEntity> productList = dao.getListProductByType(ProductType.PHONE);
			ProductEntity phone = dao.getProductById(Long.valueOf(ID));

			if (!productList.isEmpty()) {
				request.setAttribute("ACTIVE_PRODUCT_LIST", productList);
			}

			if (!ID.equals("0")) {
				int quantity = 1;
				LineItem lineItem = new LineItem(phone, quantity);

				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute("CART");
				if (cart == null) {
					cart = new Cart();
				}

				cart.addItem(lineItem);

				List<ProductEntity> phonecaseList = dao.getListProductByTypeWithPhoneId(ProductType.PHONECASE,
						phone.getId());
				List<ProductEntity> bestSeller = dao.bestPhoncaseSeller(Long.valueOf(ID));
				request.setAttribute("PHONECASE_ACTIVE_PRODUCT_LIST", phonecaseList);
				request.setAttribute("BEST_SELLER", bestSeller);
				session.setAttribute("CART", cart);
				url = SUCCESS;

			}

		} catch (Exception e) {
		} finally {
			// response.sendRedirect(request.getContextPath() + url);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
