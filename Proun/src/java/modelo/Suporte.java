package modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import modelo.dados.SuporteDAO;

public class Suporte {
    
    
    private int id = 0;
    private String descricao;
    
    private static int id_suporte = 0;
    public static final List<Suporte> suportes = new ArrayList<>();
    
    public static Suporte get (int id) {
//      for (Suporte s : suportes)
//          if (s.id == id)
//              return s;
        return new SuporteDAO().select(id);//null;
    }
    public static boolean remove (int id) {
//      for (int c=0; c<suportes.size(); c++)
//          if (suportes.get(c).id == id)
//              return suportes.remove(c)!=null;
        return new SuporteDAO().delete(id);//false;
    }
    public boolean insertInto () {
        if (suportes.contains(this))
            return false;
        id = id_suporte++;
        return suportes.add(this);
    }
    
    public int getId () { return id; }
    public String getDescricao () { return descricao; }
    
    public void setDescricao (String descricao) { this.descricao = descricao; }
    
    public Suporte (String descricao) {
        if (!"".equals(descricao))
            this.descricao = descricao;
    }
    
    @Override
    public String toString () {
        return "Suporte nº" + id + ": " + ((descricao==null || descricao.isEmpty())?("?"):(descricao));
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
