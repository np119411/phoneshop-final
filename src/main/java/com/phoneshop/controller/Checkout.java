package com.phoneshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phoneshop.SendMail;
import com.phoneshop.dao.OrderDAO;
import com.phoneshop.dao.ProductDAO;
import com.phoneshop.entities.DetailOrderEntity;
import com.phoneshop.entities.OrderEntity;
import com.phoneshop.entities.ProductEntity;
import com.phoneshop.enums.ProductType;
import com.phoneshop.phones.PhoneDAO;
import com.phoneshop.phones.PhoneDTO;
import com.phoneshop.shopping.Cart;
import com.phoneshop.shopping.LineItem;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR = "/404.html";
	private static final String SUCCESS = "/checkout.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Checkout() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = "/checkout.jsp";

		try {
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("CART");
			ProductDAO dao = new ProductDAO();
			String action = request.getParameter("action");
			if (action != null && action.equals("checkout")) {

				
				String fname = request.getParameter("billing_first_name"); 
				String lname = request.getParameter("billing_last_name"); 
				String address = request.getParameter("billing_address_1"); 
				String email = request.getParameter("billing_email"); 
				String phone = request.getParameter("billing_phone");
				 
				System.out.println("FName: " + fname);
				System.out.println("LName: "+ lname);
				System.out.println("Address: " + address);
				System.out.println("Email: " + email);
				System.out.println("Phone: " + phone);
				
				/*
				 * String fname = "Ngan"; String lname = "Phan"; String address = "An Giang";
				 * String email = "19110096@student.hcmute.edu.vn"; String phone = "0964307703";
				 */

				OrderEntity order = new OrderEntity();

				order.setFullName(fname + lname);
				order.setAddress(address);
				order.setEmail(email);
				order.setPhone(phone);

				for (LineItem lineItem : cart.getLineItems()) {
					ProductEntity product = dao.getProductById(lineItem.getProduct().getId());
					
					DetailOrderEntity detail = new DetailOrderEntity(lineItem.getQuantity(), product);
					detail.setOrderEntity(order);
					order.getDetailOrderEntities().add(detail);
				}

				OrderDAO.insertOrder(order);
				SendMail.sendMail(email, cart, address);
			}

			List<ProductEntity> productList = dao.getListProductByType(ProductType.PHONE);

			session.setAttribute("CART", cart);
			cart = new Cart();
			if (!productList.isEmpty()) {
				request.setAttribute("ACTIVE_PRODUCT_LIST", productList);
			}
			url = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
