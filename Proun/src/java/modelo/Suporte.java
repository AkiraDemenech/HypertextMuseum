package modelo;
import java.util.Objects;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Suporte implements Serializable {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    private Long id;
    private String descricao;
    
//    private static int id_suporte = 0;
//    public static final List<Suporte> suportes = new ArrayList<>();
    
    public void setId (Long id) { this.id = id; }
    public void setDescricao (String descricao) { this.descricao = descricao; }
    
    public Long getId () { return id; }
    public String getDescricao () { return descricao; }
    
    public Suporte (String descricao) {
        if (!"".equals(descricao))
            this.descricao = descricao;
    }
    public Suporte (String descricao, Long id) {
        this(descricao);
        this.id = id;
    }
    public Suporte () {}
    
    @Override
    public String toString () {
        return "Suporte nº" + (id + 1) + ": " + ((descricao==null || descricao.isEmpty())?("?"):(descricao));
    }

    @Override
    public int hashCode () {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
            
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        Suporte s = (Suporte) obj;
        return Objects.equals(this.id, s.id) && Objects.equals(this.descricao, s.descricao);
    }
    
}
