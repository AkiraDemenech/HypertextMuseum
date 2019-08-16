package modelo.dados;

import atividade.dados.IndConnector;
import javax.persistence.EntityManager;
import java.util.List;
import modelo.Suporte;

public class SuporteDAO {
    
    public List<Suporte> select () {
/*        
        List<Suporte> res = new ArrayList<>();
        
        try {
            Connection c = conn.getConnection();
            PreparedStatement p = c.prepareStatement("SELECT * FROM suporte");
            
            ResultSet rs = p.executeQuery();
            
            while (rs.next())
                res.add(new Suporte(rs.getString("descricao"), rs.getInt("id")));
            
            p.close();
            c.close();
            
        } catch (Exception l) {
            System.out.println("ERR " + l);
//            res.add(new Suporte("Tela", 0));
//            res.add(new Suporte("Mural",1));
//            res.add(new Suporte("Litogravura",2));
        }
        
        return res;
*/        
        EntityManager e = IndConnector.getConnection();
        List<Suporte> s = e.createQuery("Select a from Suporte a").getResultList();
        e.close();
        return s;
    }
    
    public Suporte select (int id) {
        EntityManager e = IndConnector.getConnection();
        Suporte s = e.find(Suporte.class, new Long(id));
        e.close();
        return s;
/*        for (Suporte s: select())
            if (s.getId() == id)
                return s;
        return null;
*/
        
    }
    
    public boolean delete (int id) {
        
/*        try {
            
            Connection con = conn.getConnection();
            PreparedStatement prep = con.prepareStatement("DELETE FROM suporte WHERE id = ?");
            prep.setLong(1, id);
            prep.execute();
            if (prep != null)
                prep.close();
            if (con != null)
                con.close();

        } catch (Exception err) {
            System.out.println("ERR " + err);
            return false;
        }
        return true;
*/
        boolean ans = false;
        EntityManager e = IndConnector.getConnection();
        e.getTransaction().begin();
        try{
            e.remove(e.find(Suporte.class, new Long(id)));
            ans = true;
        }catch(Exception ex){
            // qualquer erro
        }
        e.getTransaction().commit();
        e.close();
        return ans;
    }
    
    public boolean insert (Suporte s) {
        EntityManager em = IndConnector.getConnection();
        boolean ans = false;
        try {
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            ans = true;
        } catch (Exception ex) {
            // qualquer Erro ou Exceção
        }
        em.close();
        return ans;
/*         boolean res = false;
        String sql = "INSERT INTO suporte (descricao) VALUES (?)";
        try{
            Connection con = conn.getConnection();
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, s.getDescricao());
            res = prep.execute();
            prep.close();
            con.close();
        } catch(SQLException q){
            System.out.println("ERR " + q);
        }
        return res;//  (s==null)?(false):(s.insertInto()); */
    }
    
    public boolean update (Suporte s) {
        return insert(s);
/*        boolean res = false;
        
        try{
            
            String sql = "UPDATE suporte SET descricao = ? WHERE id = ?";
            
            Connection con = conn.getConnection();
            PreparedStatement prep = con.prepareStatement(sql);
            
            prep.setString(1, s.getDescricao());
            prep.setString(2, Integer.toString(s.getId()));
            
            res = prep.execute();
            prep.close();
            con.close();
            
        }
        catch (SQLException q) {
            System.out.println("ERR "+ q);
        }
        
        return res;*/
    }
    
    //protected IndConnector conn = new IndConnector();
    
}
