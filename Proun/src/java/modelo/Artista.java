package modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import modelo.dados.ArtistaDAO;

public class Artista {
    
    private int id = 0;
    private String nome;
    private String assinatura;
    
    private static int id_artista = 0;
    public static final List<Artista> artistas = new ArrayList<>();
    
    
    public static Artista get (int id){
//      for (Artista a: artistas)
//          if (a.id == id)
//              return a;
        return new ArtistaDAO().select(id);//null;
    }
    public static boolean remove (int id) {
//      for (int c=0; c<artistas.size(); c++)
//          if (artistas.get(c).id == id)
//              return artistas.remove(c)!=null;
        return new ArtistaDAO().delete(id);//false;
    }
    public boolean insertInto () {
        if (artistas.contains(this))
            return false;
        id = id_artista++;
        return artistas.add(this);
    }
    
    public int getId () { return id; }
    public String getNome () { return nome; }
    public String getAssinatura () { return assinatura; }
    
    public void setId (int id) { this.id = id; }
    public void setNome (String nome) { this.nome = nome; }
    public void setAssinatura (String assinatura) { this.assinatura = assinatura; }
    
    public Artista (String nome, String assinatura) {
        if (!"".equals(nome))
            this.nome = nome;
        if (!"".equals(assinatura))
            this.assinatura = assinatura;
    }

    @Override
    public String toString () {
        return "Artista nº" + id + ": " + ((nome==null || nome.isEmpty())?("?"):(nome)) + ((assinatura==null || assinatura.isEmpty())?(""):(" \\ " + assinatura));
    }

    @Override
    public int hashCode () {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        return this.id == a.id && Objects.equals(this.nome, a.nome) && Objects.equals(this.assinatura, a.assinatura);
    }
    
}
