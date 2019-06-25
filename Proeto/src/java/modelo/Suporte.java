package modelo;
import java.util.Objects;

public class Suporte {
    
    
    private int id = 0;
    private String descricao;
    
//    private static int id_suporte = 0;
//    public static final List<Suporte> suportes = new ArrayList<>();
    
    public int getId () { return id; }
    public String getDescricao () { return descricao; }
    
    public void setDescricao (String descricao) { this.descricao = descricao; }
    
    public Suporte (String descricao) {
        if (!"".equals(descricao))
            this.descricao = descricao;
    }
    
    public Suporte (String descricao, int id) {
        this(descricao);
        this.id = id;
    }
    
    @Override
    public String toString () {
        return "Suporte nº" + (id + 1) + ": " + ((descricao==null || descricao.isEmpty())?("?"):(descricao));
    }

    @Override
    public int hashCode () {
        int hash = 7;
        hash = 13 * hash + this.id;
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
        return this.id == s.id && Objects.equals(this.descricao, s.descricao);
    }
    
}
