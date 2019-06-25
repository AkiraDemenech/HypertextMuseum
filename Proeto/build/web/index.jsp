
﻿<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Obra"%>
<%@page import="modelo.Artista"%>
<%@page import="modelo.Suporte"%>
<%@page import="modelo.dados.SuporteDAO"%>
<%@page import="modelo.dados.ArtistaDAO"%>
<%@page import="modelo.dados.ObraDAO"%>

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

<script>
    function night_mode () {
        var is_day = document.getElementById('db').getAttribute('src').includes('der');
        document.getElementById('bd').setAttribute('style', 'background-color: ' + ((is_day)?('black'):('white')) + '; color: ' + ((is_day)?('#00FF00'):('black')) + '; font-family: ' + ((is_day)?('Consolas'):('Times New Roman')) + ';')
        document.getElementById('db').setAttribute('src', ((is_day)?('bm'):('bd')) + "er.png");
        is_day = !is_day;
    }
    
    function db_rel () {
        <%
                
                
                ObraDAO daobra = new ObraDAO();
                ArtistaDAO daoart = daobra.daoart;
                SuporteDAO daosup = daobra.daosup;
                Suporte sup = null;
                Artista art = null;
                
                
        %>
                
        alert("<%
                out.print (daobra.select().size());
            %> obras, <%
                out.print (daoart.select().size());
            %> artistas e <%
                out.print (daoart.select().size());
        %> suportes.");
    }
</script>
        
        <title>
            <%
                try {
                    art = daoart.select(Integer.parseInt(request.getParameter("a")));
                    out.println(art);
                } catch (NumberFormatException n) {
                    try {
                        sup = daosup.select(Integer.parseInt(request.getParameter("s")));
                        out.println(sup);
                    } catch (NumberFormatException m) {
                        out.print ("Hypertext Museum Language");
                    }
                }  
            %>
        </title>
    </head>
    
    <body id="bd">
        <button id="bt" onClick="night_mode()">
            Modo temporariamente escuro
        </button>
        <button id="bt" onClick="db_rel()">
            Sobre
        </button>
      
        <div class="row">
            
            <div class="column">
                <h3>Cadastrar Obra</h3>
                <form name="NewObra" action="CadastrarObra" method="POST">
                    <input type="text" name="Titulo" placeholder="Título" value="" /> <br>
                    <input type="text" name="URL" placeholder="URL" value="" /> <br>
                    <input type="number" name="Ano" placeholder="Ano" value="" /><br>
                    Autoria:<br>
                        <input type="radio" name="autor" value="null" checked>Não creditada;<br>
                    <%
                        for (Artista a: daoart.select()) {
                    %>
            <input type="radio" name="autor" value="<%
                out.print(a.getId()); //BD alteraria
            %>"><%
                out.println(a); //BD alteraria
            %>[<a href="index.jsp?a=<%out.print(a.getId());%>">ver mais</a>]<br> <!--BD Alteraria-->
                        <%}%>
                        <br>
                    Linguagem:<br>
                        <input type="radio" name="linguagem" value="null" checked>Inespecífica;<br>
                    <%
                        for (Suporte s: daosup.select()) { //BD alteraria
                    %>
            
            <input type="radio" name="linguagem" value="<%
                out.print(s.getId()); //BD alteraria
            %>"><%
                out.println(s); //BD alteraria
            %>[<a href="index.jsp?s=<%out.print(s.getId());%>">ver mais</a>]<br> <!--BD Alteraria-->
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
                out.println("<h2>[<button onClick=\"javascript:window.location.href='Cadastrar"+((sup==null)?("Artista?id="+art.getId()):("Suporte?id="+sup.getId()))+"'\">Apagar "+((art==null)?("suporte"):("artista"))+"</button>]</h2>"+
                            "<h1><a href='index.jsp'>Voltar para a galeria completa</h1></a>");
            
            for (Obra o: daobra.select()) {
                if(art!=null) {
                    if(!o.getAutor().equals(art)) //
                      continue;
                } else if (sup!=null ) {
                    if(!o.getLinguagem().equals(sup)) //
                      continue;
                }
        %>
        
        <h2>
            <%
                out.println(o);
                if (o==null)
                    continue;
            %>
            [<button onClick='javascript:window.location.href="CadastrarObra?id=<%=o.getId()%>"'>Apagar obra</button>]
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
        <a href="index.jsp?a=<%out.print(o.getAutor().getId());%>">
            <%
                if(o.getAutor() != null)
                    out.print (((o.getAutor().getAssinatura()==null)?((o.getAutor().getNome()==null)?(o.getAutor()):(o.getAutor().getNome())):(o.getAutor().getAssinatura())));
                else
                    out.print ("?");
            %></a>;
        <br><b>Linguagem</b>:
        <a href="index.jsp?s=<%out.print(o.getLinguagem().getId());%>">
            <%
                if(o.getLinguagem() != null)
                    out.print (((o.getLinguagem().getDescricao()==null)?(o.getLinguagem()):(o.getLinguagem().getDescricao())));
                else
                    out.print ("?");
            %></a>;
        <%
            }
        %>
      <p>
       <div style="text-align: right;">
          <img id="db" src="bder.png" title='MER e DER do Banco de Dados criado e só agora utilizado' onClick='javascript:db_rel();'>
       </div>
    </body>
</html>
