
package atividade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Obra;
import modelo.Suporte;
import modelo.Artista;
import modelo.dados.ObraDAO;

@WebServlet(name = "NewObra", urlPatterns = {"/CadastrarObra"})
public class NewObra extends HttpServlet {
    
    private final ObraDAO o = new ObraDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s = "Erro";
        try {
            if (new ObraDAO().delete(Integer.parseInt(request.getParameter("id"))))
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
        Suporte l = null;
        Artista a = null;
        Integer an= null;
        
        try {
            an = Integer.parseInt(request.getParameter("Ano"));
        }catch(NumberFormatException e) {}
        try {
            a = o.daoart.select(Integer.parseInt(request.getParameter("autor")));
        }catch(NumberFormatException e) {}
        try {
            l = o.daosup.select(Integer.parseInt(request.getParameter("linguagem")));
        }catch(NumberFormatException e) {}
        
        try {
            if (o.insert(
                new Obra(request.getParameter("Titulo"), request.getParameter("URL"), an,
                a, l)))
            response.sendRedirect("index.jsp");
        }
        catch(NullPointerException n) {
            s = n.toString();
        }
        response.getWriter().println("<script>alert('"+s+"');window.location.replace(\"index.jsp\");</script>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Cadastrar Obra";
    }// </editor-fold>

}
