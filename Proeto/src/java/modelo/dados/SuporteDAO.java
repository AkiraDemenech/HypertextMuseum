package modelo.dados;

import atividade.dados.IndConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Suporte;

import java.util.ArrayList;
import java.util.List;

public class SuporteDAO {
    
    public List<Suporte> select () {
        
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
        
    }
    
    public Suporte select (int id) {
        for (Suporte s: select())
            if (s.getId() == id)
                return s;
        return null;
    }
    
    public boolean delete (int id) {
        
        try {
            
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
        
    }
    
    public boolean insert (Suporte s) {
         boolean res = false;
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
        return res;//  (s==null)?(false):(s.insertInto());
    }
    
    public boolean update (Suporte s) {
        
        boolean res = false;
        
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
        
        return res;
    }
    
    protected IndConnector conn = new IndConnector();
    
}
