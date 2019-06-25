package modelo.dados;

import modelo.Suporte;

public class SuporteDAO {
    
    public Suporte select (int id) {
        for (Suporte s: Suporte.suportes)
            if (s.getId() == id)
                return s;
        return null;
    }
    
    public boolean delete (int id) {
        for (int c=0; c<Suporte.suportes.size(); c++)
            if (Suporte.suportes.get(c).getId() == id)
                return Suporte.suportes.remove(c)!=null;
        return false;
    }
    
    public boolean insert (Suporte s) {
        return (s==null)?(false):(s.insertInto());
    }
    
}
