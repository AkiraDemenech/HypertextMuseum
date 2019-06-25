
package atividade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Suporte;

@WebServlet(name = "NewSuporte", urlPatterns = {"/CadastrarSuporte"})
public class NewSuporte extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (Suporte.remove(Integer.parseInt(request.getParameter("id"))))
                response.sendRedirect("index.jsp");
        } catch (NumberFormatException e) { }
        response.getWriter().println("<script>alert('Erro');window.location.replace(\"index.jsp?s="+request.getParameter("id")+"\");</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (new Suporte(request.getParameter("Descricao")).insertInto())
            response.sendRedirect("index.jsp");
        else
            response.getWriter().println("<script>alert('Erro');window.location.replace(\"index.jsp\");</script>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Cadastrar Suporte";
    }// </editor-fold>

}
