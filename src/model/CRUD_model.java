
package model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CRUD_model extends Conexion{
    
    public boolean registrar(Serie_model pro){
    
        PreparedStatement ps;
        PreparedStatement ps1;

        Connection con= getConnection();
        
        String sql="INSERT INTO series (idSerie,Titulo,Temporadas,Episodios) "
                + "VALUES (?,?,?,?)";
        
        try{
            ps= con.prepareStatement(sql);
 
            ps.setInt(1, pro.getidSerie());
            ps.setString(2, pro.getTitulo());
            ps.setInt(3, pro.getTemporadas());
            ps.setDouble(4,pro.getEpisodios());
            ps.execute();
            
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean modificar(Serie_model pro){
        PreparedStatement ps;
        Connection con= getConnection();
        String sql="UPDATE series SET idSerie=?,Titulo=?,Temporadas=?,Episodios=? WHERE idSerie=?";

        try{
            ps= con.prepareStatement(sql);
            ps.setInt(1,pro.getidSerie());
            ps.setString(2, pro.getTitulo());
            ps.setInt(3, pro.getTemporadas());
            ps.setDouble(4,pro.getEpisodios());
            ps.setInt(5,pro.getidSerie());
            ps.execute();
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean eliminar(Serie_model pro){
        PreparedStatement ps;
        Connection con= getConnection();
        String sql="DELETE FROM series WHERE idSerie=?";

        try{
            ps= con.prepareStatement(sql);
            ps.setInt(1,pro.getidSerie());
            ps.execute();
            
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public boolean buscar(Serie_model pro){
        PreparedStatement ps;
        ResultSet rs;
        Connection con= getConnection();
        String sql="SELECT * FROM series WHERE idSerie=?";

        try{
            ps= con.prepareStatement(sql);
            ps.setInt(1,pro.getidSerie());
            rs= ps.executeQuery();
            if(rs.next()){
                pro.setidSerie(Integer.parseInt(rs.getString("idSerie")));
                pro.setTitulo(rs.getString("Titulo"));
                pro.setTemporadas(Integer.parseInt(rs.getString("Temporadas")));
                pro.setEpisodios(Double.parseDouble(rs.getString("Episodios")));
                return true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    
    }
    
    public List listar(){
        List<Serie_model>datos= new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con= getConnection();        
        String sql= "SELECT * FROM series";

        try{
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Serie_model modPro= new Serie_model();
                modPro.setidSerie(rs.getInt(1));
                modPro.setTitulo(rs.getString(2));
                modPro.setTemporadas(rs.getInt(3));
                modPro.setEpisodios(rs.getDouble(4));
                datos.add(modPro);
            } 
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return datos;
    }
    
    
    
}
