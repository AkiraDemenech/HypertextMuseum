package modelo.dados;

import atividade.dados.IndConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Artista;

import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO {
    
    public List<Artista> select () {
        
        List<Artista> res = new ArrayList<>();
        
        try {
            Connection c = conn.getConnection();
            PreparedStatement p = c.prepareStatement("SELECT * FROM artista");
            
            ResultSet rs = p.executeQuery();
            
            while (rs.next())
                res.add(new Artista(rs.getString("nome"), rs.getString("assinatura"), rs.getInt("id")));
            
            p.close();
            c.close();
            
        } catch (Exception l) {
            System.out.println("ERR " + l);
//            res.add(new Artista("Fulano", "Ciclano", 0));
//            res.add(new Artista(null, "Paul Klee",1));
//            res.add(new Artista("Lazar Markovich Lissitzky", "El Lissitzky", 2));
        }
        
        return res;
    }
    
    public Artista select (int id) {
        for (Artista a: select())
            if (a.getId() == id)
                return a;
        return null;
    }
    
    public boolean delete (int id) {
        
        try {
            
            Connection con = conn.getConnection();
            PreparedStatement prep = con.prepareStatement("DELETE FROM artista WHERE id = ?");
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
    
    public boolean insert (Artista a) {
        boolean res = false;
        String sql = "INSERT INTO artista (nome, assinatura) VALUES (?, ?)";
        try{
            Connection con = conn.getConnection();
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, a.getNome());
            prep.setString(2, a.getAssinatura());
            res = prep.execute();
            prep.close();
            con.close();
        } catch(SQLException s){
            System.out.println("ERR " + s);
        }
        return res;// (a==null)?(false):(a.insertInto());
    }
    
    public boolean update (Artista a) {
        
        boolean res = false;
        
        try{
            
            String sql = "UPDATE artista SET nome = ?, assinatura = ? WHERE id = ?";
            
            Connection con = conn.getConnection();
            PreparedStatement prep = con.prepareStatement(sql);
            
            prep.setString(1, a.getNome());
            prep.setString(2, a.getAssinatura());
            prep.setString(3, Integer.toString(a.getId()));
            
            res = prep.execute();
            prep.close();
            con.close();
            
        }
        catch (SQLException s) {
            System.out.println("ERR "+ s);
        }
        return res;
    }
    
    protected IndConnector conn = new IndConnector();
}
