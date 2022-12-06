package edu.eci.arsw.persistence.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.eci.arsw.DB.RuletaDB;
import edu.eci.arsw.exceptions.RuletaPersistenceException;
import edu.eci.arsw.models.Usuario;
import edu.eci.arsw.persistence.UserPersistence;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceImpl implements UserPersistence{
    public static ArrayList<Usuario> users;
    public UserPersistenceImpl() {
       users = new ArrayList<>();
    }

    RuletaDB ruletaDB = new RuletaDB();
    
    @Override
    public void crearUser(Usuario user) throws RuletaPersistenceException, SQLException {
        ruletaDB.realizaConexion();
        ruletaDB.insertarUsuario(user);
    }
    @Override
    public Usuario getUsuario(String correo) throws RuletaPersistenceException, SQLException {
        ruletaDB.realizaConexion();
        return ruletaDB.getUsuario(correo);
    }


}
