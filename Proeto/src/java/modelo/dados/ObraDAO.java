package modelo.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Obra;

import java.util.ArrayList;
import java.util.List;

public class ObraDAO {
    
    public List<Obra> select () {
        
        List<Obra> res = new ArrayList<>();
        
        try {
            Connection c = daoart.conn.getConnection();
            PreparedStatement p = c.prepareStatement("SELECT * FROM obra");
            
            ResultSet rs = p.executeQuery();
            
            while (rs.next())
                res.add(new Obra(rs.getString("titulo"),rs.getString("url"),rs.getInt("ano"),
                        daoart.select(rs.getInt("id_artista")),   // isso aqui é quase um Join
                        daosup.select(rs.getInt("id_suporte")),
                        rs.getInt("id")));
            
            p.close();
            c.close();
            
        } catch (Exception l) {
            System.out.println("ERR " + l);
//            res.add(new Obra("Mona Lisa", "https://www.google.com/", 2001, daoart.select(0), daosup.select(0), 0));
//            res.add(new Obra("Angelus Novus", "https://www.paintingmania.com/arts/paul-klee/large/angelus-novus-new-angel-115_19802.jpg", 1920, daoart.select(1), daosup.select(1), 1));
//            res.add(new Obra("Клином красным бей белых!", "https://www.dailyartmagazine.com/wp-content/uploads/2018/07/red-star-1.jpg", 1919, daoart.select(2), daosup.select(2), 2));
        }
        
        return res;
        
    }
    
    public Obra select (int id) {
        for (Obra o: select())
            if (o.getId() == id)
                return o;
        return null;
    }
    
    public boolean delete (int id) {
        
        try {
            
            Connection con = daoart.conn.getConnection();
            PreparedStatement prep = con.prepareStatement("DELETE FROM obra WHERE id = ?");
            prep.setLong(1, id);
            prep.execute();
            prep.close();
            con.close();

        } catch (Exception err) {
            System.out.println("ERR " + err);
            return false;
        }
        return true;
    }
    
    public boolean insert (Obra o) {
        
        boolean res = false;
        
        try{
            
            String sql = "INSERT INTO obra (titulo, url, ano, id_artista, id_suporte) VALUES (?,?,?,?,?)";
            
            Connection con = daoart.conn.getConnection();
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, o.getTitulo());
            prep.setString(2, o.getUrl());
            prep.setString(3, o.getAno().toString());
            prep.setString(4, Integer.toString(o.getAutor().getId()));
            prep.setString(5, Integer.toString(o.getLinguagem().getId()));
            res = prep.execute();
            prep.close();
            con.close();
        } catch(SQLException s){
            System.out.println("ERR " + s);
        }
        return res;//   (o==null)?(false):(o.insertInto());
    }
    
    public boolean update (Obra o) {
        
        boolean res = false;
        
        try{
            
            String sql = "UPDATE obra SET titulo = ?, url = ?, ano = ?, id_artista = ?, id_suporte = ? WHERE id = ?";
            
            Connection con = daoart.conn.getConnection();
            PreparedStatement prep = con.prepareStatement(sql);
            
            prep.setString(1, o.getTitulo());
            prep.setString(2, o.getUrl());
            prep.setString(3, o.getAno().toString());
            prep.setString(4, Integer.toString(o.getAutor().getId()));
            prep.setString(5, Integer.toString(o.getLinguagem().getId()));
            prep.setString(6, Integer.toString(o.getId()));
            
            res = prep.execute();
            prep.close();
            con.close();
            
        }
        catch (SQLException s) {
            System.out.println("ERR "+ s);
        }
        return res;
    }
    
    public final ArtistaDAO daoart = new ArtistaDAO ();
    public final SuporteDAO daosup = new SuporteDAO ();
    
}
