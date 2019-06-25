package modelo;
import java.util.Objects;

public class Obra {
    
    private int id = 0;
    private String titulo;
    private String url;
    private Integer ano;
    
    private Artista autor;
    private Suporte linguagem;
    
//    private static int id_obra = 0;
//    public static final List<Obra> obras = new ArrayList<>();
    
    public int getId () { return id; }
    public String getTitulo () { return titulo; }
    public String getUrl () { return url; }
    public Integer getAno () { return ano; }
        
    public void setTitulo (String titulo) { this.titulo = titulo; }
    public void setUrl (String url) { this.url = url; }
    public void setAno (Integer ano) { this.ano = ano; }
    
    public void setAutor (Artista a) {  this.autor = a; }
    public void setLinguagem (Suporte s) {  this.linguagem = s; }
    public Suporte getLinguagem () {    return linguagem;   }
    public Artista getAutor () {    return autor;   }
    
    public Obra (String titulo, String url, Integer ano) {
        if (!"".equals(titulo))
            this.titulo = titulo;
        if (!"".equals(ano))
            this.url = url;
        this.ano = ano;
        
    }
    public Obra (String titulo, String url, Integer ano, Artista a, Suporte s) {
        this(titulo,url,ano);
        this.linguagem = s;
        this.autor = a;
    }
    public Obra (String titulo, String url, Integer ano, Artista a, Suporte s, int id) {
        this(titulo,url,ano,a,s);
        this.id = id;
    }
    
    @Override
    public String toString () {
        return "Obra nº" + (id+1) + ": " + ((titulo==null || titulo.isEmpty())?("?"):(titulo)) + " (" + ((ano==null)?("?"):((ano<0)?((-ano) + " a.C."):(ano))) + ")";
    }

    @Override
    public int hashCode () {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.titulo);
        hash = 67 * hash + Objects.hashCode(this.url);
        hash = 67 * hash + Objects.hashCode(this.ano);
        hash = 67 * hash + Objects.hashCode(this.autor);
        hash = 67 * hash + Objects.hashCode(this.linguagem);
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        Obra o = (Obra) obj;
        return this.id == o.id && Objects.equals(ano, o.ano) && Objects.equals(this.titulo, o.titulo) && Objects.equals(this.url, o.url) && Objects.equals(autor,o.autor) &&Objects.equals(linguagem,o.linguagem);
    }
    
}
