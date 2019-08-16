
package atividade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dados.ArtistaDAO;
import modelo.Artista;


@WebServlet(name = "NewArtista", urlPatterns = {"/CadastrarArtista"})
public class NewArtista extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s = "Erro";
        try {
            if (new ArtistaDAO().delete(Integer.parseInt(request.getParameter("id"))))
                response.sendRedirect("index.jsp");
        } catch (NumberFormatException e) { }
        catch (NullPointerException n) {
            s = n.getMessage();
        } 
        response.getWriter().println("<script>alert('"+s+"');window.location.replace(\"index.jsp\");</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String  s = "Erro!";
        try {
            if (new ArtistaDAO().insert(new Artista(request.getParameter("Nome"), request.getParameter("Assinatura"))))
                response.sendRedirect("index.jsp");
        }
        catch(NullPointerException n) {
            s = n.toString();
        }
        response.getWriter().println("<script>alert('" + s + "');window.location.replace(\"index.jsp\");</script>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Cadastrar Artista";
    }// </editor-fold>

}
