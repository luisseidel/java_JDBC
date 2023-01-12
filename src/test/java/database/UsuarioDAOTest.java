package database;

import dao.UsuarioDAO;
import model.Usuario;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UsuarioDAOTest {

    private UsuarioDAO uDao = new UsuarioDAO();

    @Test
    public void testInsert() {
        Usuario u = new Usuario();
        u.setNome("Teste");
        u.setEmail("teste@email.com");

        uDao.insert(u);
    }


    @Test
    public void testListar() {
        try {
            List<Usuario> l = uDao.listar();
            for (Usuario u : l) {
                System.out.println(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testBuscar() {
        Usuario u = uDao.getById(1L);
        System.out.println(u);
    }

    @Test
    public void testUpdate() {
        Usuario u = new Usuario();
        u.setId(2L);
        u.setNome("Teste TEste");
        u.setEmail("email.teste@email.com");

        System.out.println("Objeto antes do update: \n");
        System.out.println(uDao.getById(u.getId()));
        uDao.update(u);
        System.out.println("Objeto APÓS update: \n");
        System.out.println(uDao.getById(u.getId()));
    }

    @Test
    public void testDelete() {
        uDao.delete(3L);
    }
}
