<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Obra"%>
<%@page import="modelo.Suporte"%>
<%@page import="modelo.Artista"%>
<!DOCTYPE html>

<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
<style>
* {
  box-sizing: border-box;
}

.column {
  float: left;
  width: 27%;
  padding: 10px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>
        
        <title>
            <%
                Suporte sup = null;
                Artista art = null;
                try {
                    art = Artista.get(Integer.parseInt(request.getParameter("a")));
                    out.println(art);
                } catch (NumberFormatException n) {
                    try {
                        sup = Suporte.get(Integer.parseInt(request.getParameter("s")));
                        out.println(sup);
                    } catch (NumberFormatException m) {
                        out.println("Hypertext Museum Language");
                    }
                }  
            %>
        </title>
    </head>
    
    <body>
        <div class="row">
            
            <div class="column">
                <h3>Cadastrar Obra</h3>
                <form name="NewObra" action="CadastrarObra" method="POST">
                    <input type="text" name="Titulo" placeholder="Título" value="" /> <br>
                    <input type="text" name="URL" placeholder="URL" value="" /> <br>
                    <input type="number" name="Ano" placeholder="Ano" value="" /><br>
                    Autoria:<br><%
                        for (int c=0; c<Artista.artistas.size(); c++) {
                    %>
            
            <input type="checkbox" name="<%
                out.print("A" + c);
            %>" value="<%
                out.print(Artista.artistas.get(c).getId()); //BD alteraria
            %>"><%
                out.println(Artista.artistas.get(c)); //BD alteraria
            %>[<a href="index.jsp?a=<%out.print(Artista.artistas.get(c).getId());%>">ver mais</a>]<br> <!--BD Alteraria-->
                        <%}%>
                        <br>
                    Linguagens:<br><%
                        for (int c=0; c<Suporte.suportes.size(); c++) { //BD alteraria
                    %>
            
            <input type="checkbox" name="<%
                out.print("S" + c);
            %>" value="<%
                out.print(Suporte.suportes.get(c).getId()); //BD alteraria
            %>"><%
                out.println(Suporte.suportes.get(c)); //BD alteraria
            %>[<a href="index.jsp?s=<%out.print(Suporte.suportes.get(c).getId());%>">ver mais</a>]<br> <!--BD Alteraria-->
                        <%}%>
                    <br><input type="submit" value="Submeter obra" name="okart" />
                </form>
            </div>
                    
            <div class="column">
                <h3>Cadastrar Artista</h3>
                <form name="NewArtista" action="CadastrarArtista" method="POST">
                    <input type="text" name="Nome" placeholder="Nome" value="" /> <br>
                    <input type="text" name="Assinatura" placeholder="Assinatura" value="" />
                    <br><input type="submit" value="Submeter artista" name="okart" />
                </form>
            </div>
            
            <div class="column">
                <h3>Cadastrar Suporte</h3>
                <form name="NewSuporte" action="CadastrarSuporte" method="POST">
                    <input type="text" name="Descricao" placeholder="Descrição" value="" /> <br>
                    <input type="submit" value="Submeter suporte" name="oksup" />
                </form>
            </div>
            
        </div>
        
        <%
            if (art!=null || sup!=null)
                out.println("<h2>[<a href='Cadastrar"+((sup==null)?("Artista?id="+art.getId()):("Suporte?id="+sup.getId()))+"'>Apagar "+((art==null)?("suporte"):("artista"))+"</a>]</h2>"+
                            "<h1><a href='index.jsp'>Voltar para a galeria completa</h1></a>");
            
            for (Obra o: Obra.obras) {
                if(art!=null) {
                    if(!o.autores.contains(art)) //
                      continue;
                } else if (sup!=null ) {
                    if(!o.linguagens.contains(sup)) //
                      continue;
                }
        %>
        
        <h2>
            <%
                out.println(o);
                if (o==null)
                    continue;
            %>
            [<a href='CadastrarObra?id=<%=o.getId()%>'>Apagar obra</a>]
        </h2>
        
        <%
            if(o.getUrl()!=null) {
                boolean t = o.getUrl().contains(".jpg") || o.getUrl().contains(".png") || o.getUrl().contains(".gif");
                if (t)
                    out.println("<img alt='" + o + "' title='" + o.getTitulo() + "'");
                else
                    out.println("<iframe");
                out.println(" src='" + o.getUrl() + "'>");
                if (!t)
                    out.println("<a href='"+o.getUrl()+"'>Link</a></iframe>");
                
            }
        %>
        <br><b>Autoria</b>:
        <%
            for (Artista a: o.autores) {
        %>
          <a href="index.jsp?a=<%out.print(a.getId());%>">
            <%
                if(a!=null)
                    out.println(((a.getAssinatura()==null)?((a.getNome()==null)?(a):(a.getNome())):(a.getAssinatura())));
                else
                    out.println("?");
            %></a>;
        <%
            }
        %>
        <br><b>Linguagem</b>:
        <%
            for (Suporte a: o.linguagens) {
        %>
          <a href="index.jsp?s=<%out.print(a.getId());%>">
            <%
                if(a!=null)
                    out.println(((a.getDescricao()==null)?(a):(a.getDescricao())));
                else
                    out.println("?");
            %></a>;
        <%
            }
        %>
        <%
            }
        %>
      <p>
    </body>
  <img src="bder.png" title="MER e DER do Banco de Dados criado mas não utilizado" alt='MER e DER do Banco de Dados criado porém não utilizado'>
</html>
