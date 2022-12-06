package edu.eci.arsw.DB;

import edu.eci.arsw.models.Usuario;

import java.sql.*;


/**
 *
 * @author Usuario
 */
public class RuletaDB{
	private String urlDatabase = "jdbc:postgresql://ec2-3-230-122-20.compute-1.amazonaws.com:5432/d4gsagactd5tb4";
    private String usuarioDb = "ttlpdzwqtuszgz";
    private String passwordDb= "91521990bffc598c3c02c525232ae99b73beeeec7db54d4f9335b6a0f2c5c983";
    private Connection c = null;
    private int id;

    private String driverDB = "org.postgresql.Driver";
    private String baseDB = "Proyecto";
    
    public void realizaConexion() throws SQLException{

        try {
            Class.forName(driverDB);
            c = DriverManager.getConnection(urlDatabase,  usuarioDb, passwordDb);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } 
    }

    public void insertarUsuario(Usuario user){
        Statement stmt = null;
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }

        try{
            Class.forName(driverDB);
            stmt = c.createStatement();
            int userId = id+1;
            String sql = "INSERT INTO"+ (char)34 + baseDB + (char)34 +".users (id,nombre,fechan,telefono,correo,passw,points)"
                    +"VALUES ("+userId+",'"+user.getName()+"','"+user.getFechaN()+"',"+user.getTelefono()+
                    ",'"+user.getCorreo()+"','"+user.getPassword()+"',"+5000+");";
            id = userId;
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int obtenerPoints(String user){
        int points = 0;
        PreparedStatement stmt = null;
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
                c.setAutoCommit(false);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        try{
            Class.forName(driverDB);
            stmt = c.prepareStatement("Select points from "+ (char)34 + baseDB + (char)34 +".users where correo = ?");
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                points = rs.getInt("points");
            }
            rs.close();
            stmt.close();
            return points;
        }catch(Exception e){
            e.printStackTrace();
        }
        return points;
    }
    public void actualizarApuesta(String user, int vganado){
        int points = obtenerPoints(user) + vganado;
        Statement stmt = null;
        if(c == null){

            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }

        try{
            Class.forName(driverDB);
            stmt = c.createStatement();
            String sql = "UPDATE "+ (char)34 + baseDB + (char)34 +".users SET points = "+points+" WHERE correo = '"+user+"';";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public Usuario getUsuario(String correo) {
        int id = 0;
        String nombre = null;
        String fecha = null;
        int telefono = 0;
        String correoo = null;
        String passwd = null;
    	int points = 0;
        PreparedStatement stmt = null;
        if(c == null){
            try {
                c = DriverManager.getConnection(urlDatabase,usuarioDb, passwordDb);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        try{
            Class.forName(driverDB);
            stmt = c.prepareStatement("Select * from "+ (char)34 + baseDB + (char)34 +".users where correo = ?");
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
                nombre  = rs.getString("nombre");
                fecha  = rs.getString("fechan");
                telefono = rs.getInt("telefono");
                correoo  = rs.getString("correo");
                passwd = rs.getString("passw");
                points = rs.getInt("points");
            }

            Usuario u = new Usuario(id, nombre, fecha, telefono, correoo, passwd);
            u.setPoints(points);
            rs.close();
            stmt.close();
            return u;

        }catch(Exception e){
        	e.printStackTrace();
        }

        return null;

    }
}
