package edu.eci.arsw.services;

import edu.eci.arsw.exceptions.RuletaPersistenceException;

import edu.eci.arsw.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.eci.arsw.persistence.UserPersistence;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserPersistence usuario;

    public void crearUser(Usuario user) throws RuletaPersistenceException, SQLException {
        usuario.crearUser(user);
    }
    public Usuario getUsuario(String correo) throws RuletaPersistenceException, SQLException {
        return usuario.getUsuario(correo);
    }

}
