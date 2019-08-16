package modelo.dados;

import atividade.dados.IndConnector;
import javax.persistence.EntityManager;
import java.util.List;
import modelo.Artista;

public class ArtistaDAO {
    
    public List<Artista> select () {
        EntityManager e = IndConnector.getConnection();
        List<Artista> a = e.createQuery("Select a from Artista a").getResultList();
        e.close();
        return a;
    }
    public Artista select (int id) {
/*        for (Artista a: Artista.artistas)
            if (a.getId() == id)
                return a;
        return null;
*/
        EntityManager e = IndConnector.getConnection();
        Artista a = e.find(Artista.class, new Long(id));
        e.close();
        return a;
    }
    
    public boolean delete (int id) {
        boolean ans = false;
        EntityManager e = IndConnector.getConnection();
        e.getTransaction().begin();
        try{
            e.remove(e.find(Artista.class, new Long(id)));
            ans = true;
        }catch(Exception ex){
            // qualquer erro
        }
        e.getTransaction().commit();
        e.close();
        return ans;
/*        for (int c=0; c<Artista.artistas.size(); c++)
            if (Artista.artistas.get(c).getId() == id)
                return Artista.artistas.remove(c)!=null;
        return false;
*/
    }
    
    public boolean insert (Artista a) {
        EntityManager em = IndConnector.getConnection();
        boolean ans = false;
        try {
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
            ans = true;
        } catch (Exception ex) {
            // qualquer Erro ou Exceção
        }
        em.close();
        return ans;
//        return (a==null)?(false):(a.insertInto());
    }
        public boolean update (Artista a) {
            return insert(a);
        }
        
}
