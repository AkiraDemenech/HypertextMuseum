package modelo.dados;

import modelo.Artista;

public class ArtistaDAO {
    
    public Artista select (int id) {
        for (Artista a: Artista.artistas)
            if (a.getId() == id)
                return a;
        return null;
    }
    
    public boolean delete (int id) {
        for (int c=0; c<Artista.artistas.size(); c++)
            if (Artista.artistas.get(c).getId() == id)
                return Artista.artistas.remove(c)!=null;
        return false;
    }
    
    public boolean insert (Artista a) {
        return (a==null)?(false):(a.insertInto());
    }
}
