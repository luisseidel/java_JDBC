package dao;

import database.SingleConnection;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        connection = SingleConnection.connect();
    }

    public void insert(Usuario usuario) {
        try {
            String sql = "insert into usuarios (nome, email) values (?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());

            statement.execute();
            connection.commit();
            System.out.println("Usuário Salvo!");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() throws SQLException {
        String sql = "select * from usuarios limit 100";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet r = statement.executeQuery();
        List<Usuario> list = new ArrayList<>();

        while (r.next()) {
            Usuario u = new Usuario();
            u.setId(r.getLong("id"));
            u.setNome(r.getString("nome"));
            u.setEmail(r.getString("email"));

            list.add(u);
        }

        return list;
    }

    public Usuario getById(Long id) {
        try {
            String sql = "select * from usuarios where id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet r = statement.executeQuery();

            Usuario u = new Usuario();
            while (r.next()) {
                u.setId(r.getLong("id"));
                u.setNome(r.getString("nome"));
                u.setEmail(r.getString("email"));
            }

            return u;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void update(Usuario usuario) {
        try {
            String sql = "update usuarios set nome = ?, email = ? where id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setLong(3, usuario.getId());

            statement.execute();
            connection.commit();
            System.out.println("Usuário Atualizado!");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            String sql = "delete from usuarios where id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.execute();
            connection.commit();
            System.out.println("Usuário Deletado!");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
