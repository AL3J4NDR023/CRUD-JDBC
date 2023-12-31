package com.alura.jdbc.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.producto;

public class ProductoDAO {
    final private Connection con;

    public ProductoDAO(Connection con) {
        this.con = con;
    }

    public void guardar(producto producto) {

        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO "
                    + "(nombre, descripcion, cantidad, categoria_id)"
                    + " VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, producto.getNombre());
                statement.setString(2, producto.getDescripcion());
                statement.setInt(3, producto.getCantidad());
                statement.setInt(4, producto.getCategoriaId());

                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();

                try (resultSet) {
                    while (resultSet.next()) {
                        producto.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertado el producto de ID %d", resultSet.getInt(1)));
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // private void ejecutaRegistro(com.alura.jdbc.modelo.producto producto,
    // PreparedStatement statement)
    // throws SQLException {

    // statement.setString(1, producto.getNombre());
    // statement.setString(2, producto.getDescripcion());
    // statement.setInt(3, producto.getCantidad());

    // statement.execute();
    // final ResultSet resultSet = statement.getGeneratedKeys();

    // try (resultSet) {
    // while (resultSet.next()) {
    // producto.setId(resultSet.getInt(1));
    // System.out.println(String.format("Fue insertado el producto de ID %d",
    // resultSet.getInt(1)));
    // }
    // }

    // resultSet.close();
    // }

    public List<producto> listar() {
        List<producto> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        producto fila = new producto(resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD"));

                        resultado.add(fila);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String nombre, String descripcion, Integer id, Integer cantidad) {
        try {
            final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
                    + " NOMBRE = ? "
                    + ", DESCRIPCION = ? "
                    + ", CANTIDAD = ? "
                    + "WHERE ID = " + id + ";");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<producto> listar(Integer categoriaId) {
        List<producto> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD " + " FROM PRODUCTO" + " WHERE CATEGORIA_ID = ?");

            try (statement) {
                statement.setInt(1, categoriaId);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        producto fila = new producto(resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD"));

                        resultado.add(fila);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
