package modelo;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;

@Entity
public class Obra implements Serializable {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    private Long id;
    private String titulo;
    private String url;
    private Integer ano;
    
    @OneToMany(fetch=FetchType.LAZY)
    private List<Artista> autores;
    @OneToMany(fetch=FetchType.LAZY)
    private List<Suporte> linguagens;
    
//    private static int id_obra = 0;
//    public static final List<Obra> obras = new ArrayList<>();
        
    public void setId (Long id) { this.id = id; }
    public void setTitulo (String titulo) { this.titulo = titulo; }
    public void setUrl (String url) { this.url = url; }
    public void setAno (Integer ano) { this.ano = ano; }
    
    public void setLinguagem (Suporte s){  if(linguagens!=null && s!=null) linguagens.add(s);  }
    public void setAutor (Artista a)    {  if(autores!=null && a!=null)    autores.add(a);     }
    public void setAutores (List<Artista> a)    {  this.autores = a;    }
    public void setLinguagens (List<Suporte> s) {  this.linguagens = s; }
    public List<Suporte> getLinguagens (){    return linguagens; }
    public List<Artista> getAutores ()    {    return autores;    }
    
    public Long getId () { return id; }
    public String getTitulo () { return titulo; }
    public String getUrl () { return url; }
    public Integer getAno () { return ano; }
    
    public Obra (String titulo, String url, Integer ano) {
        if (!"".equals(titulo))
            this.titulo = titulo;
        if (!"".equals(url))
            this.url = url;
        this.ano = ano;
        
    }
    public Obra (String titulo, String url, Integer ano, List<Artista> a, List<Suporte> s) {
        this(titulo,url,ano);
        this.linguagens = s;
        this.autores = a;
    }
    public Obra (String titulo, String url, Integer ano, List<Artista> a, List<Suporte> s, Long id) {
        this(titulo,url,ano,a,s);
        this.id = id;
    }
    public Obra () {}
    
    @Override
    public String toString () {
        return "Obra nº" + (id+1) + ": " + ((titulo==null || titulo.isEmpty())?("?"):(titulo)) + " (" + ((ano==null)?("?"):((ano<0)?((-ano) + " a.C."):(ano))) + ")";
    }

    @Override
    public int hashCode () {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        return Objects.equals(id, o.id) && Objects.equals(ano, o.ano) && Objects.equals(this.titulo, o.titulo) && Objects.equals(this.url, o.url) && Objects.equals(autores,o.autores) &&Objects.equals(linguagens,o.linguagens);
    }
    
}
