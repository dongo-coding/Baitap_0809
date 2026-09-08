package murach.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import murach.business.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        String action = request.getParameter("action");
        String productCode = request.getParameter("productCode");
        String url = "/cart.jsp";

        if (action == null) {
            action = "cart";
        }

        switch (action) {
            case "add":
                if (productCode != null) {
                    Product product = getProductByCode(productCode);
                    if (product != null) {
                        LineItem lineItem = new LineItem(product, 1);
                        cart.addItem(lineItem);
                    }
                }
                session.setAttribute("cart", cart);
                break;

            case "update":
                int quantity;
                try {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (NumberFormatException e) {
                    quantity = 1;
                }

                LineItem itemToUpdate = null;
                for (LineItem item : cart.getItems()) {
                    if (item.getProduct().getCode().equalsIgnoreCase(productCode)) {
                        itemToUpdate = item;
                        break;
                    }
                }

                if (itemToUpdate != null) {
                    if (quantity > 0) {
                        itemToUpdate.setQuantity(quantity);
                    } else {
                        cart.removeItem(itemToUpdate);
                    }
                }
                session.setAttribute("cart", cart);
                break;

            case "remove":
                LineItem itemToRemove = null;
                for (LineItem item : cart.getItems()) {
                    if (item.getProduct().getCode().equalsIgnoreCase(productCode)) {
                        itemToRemove = item;
                        break;
                    }
                }
                if (itemToRemove != null) {
                    cart.removeItem(itemToRemove);
                }
                session.setAttribute("cart", cart);
                break;

            case "checkout":
                double grandTotal = 0;
                for (LineItem item : cart.getItems()) {
                    grandTotal += item.getTotal();
                }
                request.setAttribute("grandTotal", grandTotal);
                url = "/checkout.jsp";
                break;

            default:
                url = "/cart.jsp";
                break;
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private Product getProductByCode(String code) {
        if (code == null) return null;
        switch (code) {
            case "8601":
                return new Product("8601", "86 (the band) - True Life Songs and Pictures", 14.95);
            case "pf01":
                return new Product("pf01", "Paddlefoot - The first CD", 12.95);
            case "pf02":
                return new Product("pf02", "Paddlefoot - The second CD", 14.95);
            case "jr01":
                return new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", 14.95);
            default:
                return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}