package com.phoneshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phoneshop.phonecase.PhonecaseDAO;
import com.phoneshop.phonecase.PhonecaseDTO;
import com.phoneshop.phones.PhoneDAO;
import com.phoneshop.phones.PhoneDTO;
import com.phoneshop.shopping.Cart;
import com.phoneshop.shopping.LineItem;


@WebServlet("/RemovePhonecase")
public class RemovePhonecase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR = "/404.html";
    private static final String SUCCESS = "/cart.jsp";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemovePhonecase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String ID = request.getParameter("ID");
            int quantity = 1;
//            
//            PhonecaseDAO dao = new PhonecaseDAO();
//            PhonecaseDTO phone = dao.getProductForCart(ID);
//            
//            LineItem lineItem = new LineItem(phone, quantity);
//            
//            HttpSession session = request.getSession();
//            Cart cart = (Cart) session.getAttribute("CART");
//             
//            cart.removePhonecase(lineItem);
//            
//            session.setAttribute("CART", cart);
//            url = SUCCESS;
//            
        } 
        catch (Exception e) {
        }
        finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
	}

}