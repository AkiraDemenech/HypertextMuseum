package modelo.dados;

import modelo.Obra;

public class ObraDAO {
    
    public Obra select (int id) {
        for (Obra o: Obra.obras)
            if (o.getId() == id)
                return o;
        return null;
    }
    
    public boolean delete (int id) {
        for (int c=0; c<Obra.obras.size(); c++)
            if (Obra.obras.get(c).getId() == id)
                return Obra.obras.remove(c)!=null;
        return false;
    }
    
    public boolean insert (Obra o) {
        return (o==null)?(false):(o.insertInto());
    }
    
}
