
package atividade;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dados.ObraDAO;
import modelo.Obra;
import modelo.Suporte;
import modelo.Artista;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


@WebServlet(name = "NewObra", urlPatterns = {"/CadastrarObra"})
public class NewObra extends HttpServlet {
    
    private final ObraDAO o = new ObraDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s = "Erro";
        try {
            if (o.delete(Integer.parseInt(request.getParameter("id"))))
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
        List<Suporte> l = o.daosup.select();
        List<Artista> a = o.daoart.select();
        Integer an = null;
        Obra    obr;
        
        try {
            an = Integer.parseInt(request.getParameter("Ano"));
        }catch(NumberFormatException e) {}
        
        obr = new Obra(request.getParameter("Titulo"), request.getParameter("URL"), an, new ArrayList<Artista>(), new ArrayList<Suporte>());
        
        for (int c=0; c<a.size() || c<l.size(); c++) {
            try {
                if(request.getParameter("A" + a.get(c).getId())!=null)
                    obr.setAutor(a.get(c));
//      nova.autores.add(Artista.get(Integer.parseInt(request.getParameter("A" + c))));
            } catch (Exception n) { }
            try {
                if(request.getParameter("S" + l.get(c).getId())!=null)
                    obr.setLinguagem(l.get(c));
//      nova.linguagens.add(Suporte.get(Integer.parseInt(request.getParameter("S" + c))));                
                
            } catch (Exception n) { }
        }
        
        try {
            if (o.insert(obr))
            response.sendRedirect("index.jsp");
        }
        catch(Exception n) {
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
