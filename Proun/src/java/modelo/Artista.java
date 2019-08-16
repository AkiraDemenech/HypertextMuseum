package modelo;
import java.util.Objects;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Artista implements Serializable {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    private Long id;
    private String nome;
    private String assinatura;
    
//    private static int id_artista = 0;
//    public static final List<Artista> artistas = new ArrayList<>();
    
    public void setId (Long id) { this.id = id; }
    public void setNome (String nome) { this.nome = nome; }
    public void setAssinatura (String assinatura) { this.assinatura = assinatura; }
    
    public Long getId () { return id; }
    public String getNome () { return nome; }
    public String getAssinatura () { return assinatura; }
    
    public Artista (String nome, String assinatura) {
        if (!"".equals(nome))
            this.nome = nome;
        if (!"".equals(assinatura))
            this.assinatura = assinatura;
    }
    public Artista (String nome, String assinatura, Long id) {
        this(nome, assinatura);
        this.id = id;
    }
    public Artista () {}

    @Override
    public String toString () {
        return "Artista nº" + (id + 1) + ": " + ((nome==null || nome.isEmpty())?("?"):(nome)) + ((assinatura==null || assinatura.isEmpty())?(""):(" \\ " + assinatura));
    }

    @Override
    public int hashCode () {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.assinatura);
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        Artista a = (Artista) obj;
        return Objects.equals(this.id, a.id) && Objects.equals(this.nome, a.nome) && Objects.equals(this.assinatura, a.assinatura);
    }
    
}
