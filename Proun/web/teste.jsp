
<%@page import="modelo.dados.ArtistaDAO"%>
<%@page import="modelo.dados.SuporteDAO"%>
<%@page import="modelo.dados.ObraDAO"%>
<%@page import="modelo.Obra"%>
<%@page import="modelo.Suporte"%>
<%@page import="modelo.Artista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSPágina</title>
    </head>
    <body style="background-color:black;color:#00FF00;font-family:Consolas;">
        <%
            if(!"Nutella".equals(request.getParameter("rOOT"))) {
                out.print("<script>alert('Usuário e/ou Senha incorretos!')\nwindow.location.replace('index.jsp');</script>");
                return;
            }
            SuporteDAO sd = new SuporteDAO();
            ArtistaDAO ad = new ArtistaDAO();
            ObraDAO od = new ObraDAO();      
            
            for (Obra o: od.select())
            {
                if(od.delete(o.getId().intValue()))
                    out.println("<br>"+o+"DELETADO COM SUCESSO!");
                else
                    out.println("<br><div style='color:#FF0000'>"+o+" A GENTE NÃO CONSEGUIU DELETAR NÃO....</div>");
            }
            
            for (Suporte s: sd.select())
            {
                if(sd.delete(s.getId().intValue()))
                    out.println("<br>"+s+"DELETADO COM SUCESSO!");
                else
                    out.println("<br><div style='color:#FF0000'>"+s+" A GENTE NÃO CONSEGUIU DELETAR NÃO....</div>");
            }
            
            for (Artista a: ad.select())
            {
                if(ad.delete(a.getId().intValue()))
                    out.println("<br>"+a+"DELETADO COM SUCESSO!");
                else
                    out.println("<br><div style='color:#FF0000'>"+a+" A GENTE NÃO CONSEGUIU DELETAR NÃO....</div>");
            }
        %>
    </body>
</html>
