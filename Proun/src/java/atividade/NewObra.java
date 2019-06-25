
package atividade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Artista;
import modelo.Suporte;
import modelo.Obra;

@WebServlet(name = "NewObra", urlPatterns = {"/CadastrarObra"})
public class NewObra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (Obra.remove(Integer.parseInt(request.getParameter("id"))))
                response.sendRedirect("index.jsp");
        } catch (NumberFormatException e) { }
        response.getWriter().println("<script>alert('Erro');window.location.replace(\"index.jsp\");</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer a = null;
        try {
            a = Integer.parseInt(request.getParameter("Ano"));
        } catch (NumberFormatException n) {  }
        Obra nova = new Obra(request.getParameter("Titulo"), request.getParameter("URL"), a);
        
        if (nova.insertInto()) {
            for (int c=0; c<Artista.artistas.size() || c<Suporte.suportes.size(); c++) {
                try {
                    nova.autores.add(Artista.get(Integer.parseInt(request.getParameter("A" + c))));
                } catch (NumberFormatException n) { }
                try {
                    nova.linguagens.add(Suporte.get(Integer.parseInt(request.getParameter("S" + c))));
                } catch (NumberFormatException n) { }
            }
            response.sendRedirect("index.jsp");
        } else
            response.getWriter().println("<script>alert('Erro');window.location.replace(\"index.jsp\");</script>");
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
