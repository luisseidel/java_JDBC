package database;

import dao.TelefoneUsuarioDAO;
import dao.UsuarioDAO;
import model.TelefoneUsuario;
import model.enums.TipoTelefone;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TelefoneUsuarioDAOTest {

    private TelefoneUsuarioDAO uDao = new TelefoneUsuarioDAO();

    @Test
    public void testInsert() {
        TelefoneUsuario u = new TelefoneUsuario();
        u.setNumero("5551999999999");
        u.setTipoTelefone(TipoTelefone.MOVEL);
        u.setUsuario(new UsuarioDAO().getById(1L));

        uDao.insert(u);
    }


    @Test
    public void testListar() {
        try {
            List<TelefoneUsuario> l = uDao.listar();
            for (TelefoneUsuario u : l) {
                System.out.println(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testBuscar() {
        try {
            TelefoneUsuario u = uDao.getById(1L);
            System.out.println(u);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            TelefoneUsuario u = new TelefoneUsuario();
            u.setId(2L);
            u.setTipoTelefone(TipoTelefone.FIXO);
            u.setUsuario(new UsuarioDAO().getById(2L));
            u.setNumero("555132132211");

            System.out.println("Objeto antes do update: \n");
            System.out.println(uDao.getById(u.getId()));
            uDao.update(u);
            System.out.println("Objeto APÓS update: \n");
            System.out.println(uDao.getById(u.getId()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        uDao.delete(3L);
    }
}
