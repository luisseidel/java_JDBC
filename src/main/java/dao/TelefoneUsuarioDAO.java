package dao;

import database.SingleConnection;
import model.TelefoneUsuario;
import model.enums.TipoTelefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefoneUsuarioDAO {

    private Connection connection;

    public TelefoneUsuarioDAO() {
        this.connection = SingleConnection.connect();
    }

    public void insert(TelefoneUsuario tu) {
        try {
            String sql = "insert into telefones_usuarios (numero, tipo, usuario) values (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tu.getNumero());
            statement.setInt(2, tu.getTipoTelefone().getValue());
            statement.setLong(3, tu.getUsuario().getId());

            statement.execute();
            connection.commit();
            System.out.println("telefones_usuarios Salvo!");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<TelefoneUsuario> listar() throws SQLException {
        String sql = "select * from telefones_usuarios limit 100";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet r = statement.executeQuery();
        List<TelefoneUsuario> list = new ArrayList<>();

        while (r.next()) {
            TelefoneUsuario tu = new TelefoneUsuario();
            tu.setId(r.getLong("id"));
            tu.setNumero(r.getString("numero"));
            tu.setTipoTelefone(TipoTelefone.findById(r.getInt("tipo")));
            tu.setUsuario(new UsuarioDAO().getById(r.getLong("usuario")));

            list.add(tu);
        }

        return list;
    }

    public TelefoneUsuario getById(Long id) throws SQLException {
        String sql = "select tu.* from telefones_usuarios tu where id = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet r = statement.executeQuery();

        TelefoneUsuario tu = new TelefoneUsuario();
        while (r.next()) {
            tu.setId(r.getLong("id"));
            tu.setNumero(r.getString("numero"));
            tu.setTipoTelefone(TipoTelefone.findById(r.getInt("tipo")));
            tu.setUsuario(new UsuarioDAO().getById(r.getLong("usuario")));
        }

        return tu;
    }

    public void update(TelefoneUsuario telefoneUsuario) {
        try {
            String sql = "update telefones_usuarios set numero = ?, tipo = ?, usuario = ? where id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, telefoneUsuario.getNumero());
            statement.setInt(2, telefoneUsuario.getTipoTelefone().getValue());
            statement.setLong(3, telefoneUsuario.getUsuario().getId());
            statement.setLong(4, telefoneUsuario.getId());

            statement.execute();
            connection.commit();
            System.out.println("Telefone Atualizado!");

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
            String sql = "delete from telefones_usuarios where id = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.execute();
            connection.commit();
            System.out.println("telefones_usuarios Deletado!");

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
