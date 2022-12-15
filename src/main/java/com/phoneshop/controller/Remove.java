package com.phoneshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phoneshop.phones.PhoneDAO;
import com.phoneshop.phones.PhoneDTO;
import com.phoneshop.shopping.Cart;
import com.phoneshop.shopping.LineItem;


@WebServlet("/Remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ERROR = "/404.html";
    private static final String SUCCESS = "/cart.jsp";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remove() {
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
            
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
             
            cart.removeItem(Long.parseLong(ID));
            
            session.setAttribute("CART", cart);
            url = SUCCESS;
            
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
	}

}
