package edu.eci.arsw.persistence;

import edu.eci.arsw.exceptions.RuletaPersistenceException;
import edu.eci.arsw.models.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserPersistence {

    public void crearUser(Usuario user) throws RuletaPersistenceException, SQLException;
    
    public Usuario getUsuario(String correo) throws RuletaPersistenceException, SQLException;

}
