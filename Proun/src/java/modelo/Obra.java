package modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Obra {
    
    private int id = 0;
    private String titulo;
    private String url;
    private Integer ano;
    
    public final List<Artista> autores = new ArrayList<>();
    public final List<Suporte> linguagens = new ArrayList<>();
    
    private static int id_obra = 0;
    public static final List<Obra> obras = new ArrayList<>();
    
    public static Obra get (int id) {
        for (Obra o : obras)
            if (o.id == id)
                return o;
        return null;
    }
    public static boolean set (Obra o) {
        o.id = id_obra++;
        return obras.add(o);
    }
    public static boolean remove (int id) {
        for (int c=0; c<obras.size(); c++)
            if (obras.get(c).id == id)
                return obras.remove(c)!=null;
        return false;
    }
    
    public int getId () { return id; }
    public String getTitulo () { return titulo; }
    public String getUrl () { return url; }
    public Integer getAno () { return ano; }
        
    public void setTitulo (String titulo) { this.titulo = titulo; }
    public void setUrl (String url) { this.url = url; }
    public void setAno (Integer ano) { this.ano = ano; }
    
    public Obra (String titulo, String url, Integer ano) {
        if (!"".equals(titulo))
            this.titulo = titulo;
        if (!"".equals(ano))
            this.url = url;
        this.ano = ano;
    }
    
    @Override
    public String toString () {
        return "Obra nº" + id + ": " + ((titulo==null || titulo.isEmpty())?("?"):(titulo)) + " (" + ((ano==null)?("?"):(ano)) + ")";
    }

    @Override
    public int hashCode () {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.titulo);
        hash = 67 * hash + Objects.hashCode(this.url);
        hash = 67 * hash + Objects.hashCode(this.ano);
        hash = 67 * hash + Objects.hashCode(this.autores);
        hash = 67 * hash + Objects.hashCode(this.linguagens);
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        Obra o = (Obra) obj;
        return this.id == o.id && Objects.equals(ano, o.ano) && Objects.equals(this.titulo, o.titulo) && Objects.equals(this.url, o.url) && autores.equals(o.autores) && linguagens.equals(o.linguagens);
    }
    
}
