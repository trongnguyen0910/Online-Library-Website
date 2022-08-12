
package fu.ex.servlet;

import fu.ex.daos.BookDAO;
import fu.ex.entities.Book;
import fu.ex.entities.Member;
import fu.ex.entities.ShoppingCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thien Le
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {
     
    public CartServlet(){
        super();
    }  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        if(session==null){
               request.setAttribute("errormessage", "Please login!");
               request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response); 
            }
        if(session.getAttribute("userdata") != null){ // check login
            Member member= (Member)session.getAttribute("userdata");
            if(member.getMemberRole()==0){ // check role
            if(action == null){
                displayCart(request, response);
            } else{               
                if(action.equalsIgnoreCase("buy")){ // if add item to cart but that item has quantity = 0 => cancel add to cart
                    BookDAO bDao = new BookDAO();
                    Book b = bDao.find(request.getParameter("bookId"));
                    if (b.getBookQuantity()>=1) {
                        buyItem(request, response);
                    } else {                        
                       request.setAttribute("errorBuy", "This book is not available!");
                       request.getRequestDispatcher("ListBookServlet").forward(request, response);
                    }
                }else if(action.equalsIgnoreCase("remove")){
                    removeItem(request, response);
                } else if(action.equalsIgnoreCase("empty")){
                    emptyCart(request, response);
                }
                else if(action.equalsIgnoreCase("RemoveItems")){ // action remove items by check                     
                    removeItems(request, response);
                }
            }
            }else{
            request.setAttribute("errormessage", "Incorrect Role!");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("errormessage", "Please login!");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
    }
    protected void displayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);
    }
  protected void buyItem(HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException {
      BookDAO bookDAO = new BookDAO();
      HttpSession session = request.getSession();
      if(session.getAttribute("cart") == null){
          List<ShoppingCartItem> cart = new ArrayList<ShoppingCartItem>();
          cart.add(new ShoppingCartItem(bookDAO.find(request.getParameter("bookId")), 1));
          session.setAttribute("cart", cart);
      }else{
          List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
          int index = isExisting(request.getParameter("bookId"), cart);
          if(index == -1){
              cart.add(new ShoppingCartItem(bookDAO.find(request.getParameter("bookId")), 1));
          } else{             
              cart.get(index).setQuantity(1);
          }
          session.setAttribute("cart", cart);
      }
      response.sendRedirect("CartServlet");
  }
  protected void removeItem (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
      HttpSession session = request.getSession();      
        List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("bookId"), cart);
        cart.remove(index);       
        session.setAttribute("cart", cart);        
        response.sendRedirect("CartServlet");
  }
  protected void removeItems (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{ // function remove items by check
        HttpSession session = request.getSession();       
        List<ShoppingCartItem> cart = (List<ShoppingCartItem>) session.getAttribute("cart");
        int s = cart.size();
        for(int i=0; i<s; i++){ // use loop to get the book that checked by member to delete        
        if(request.getParameter("Book"+i) != null){ // the book checked will get parameter != null
            int index = isExisting(request.getParameter("Book"+i), cart); // get index of this book in cart to remove it
            cart.remove(index);          
           }
        }
        session.setAttribute("cart", cart);  
        response.sendRedirect("CartServlet");
  }
   protected void emptyCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        request.getSession().removeAttribute("cart");       
        response.sendRedirect("CartServlet");
    }
   private int isExisting(String id, List<ShoppingCartItem> cart){
        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getBook().getBookId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
