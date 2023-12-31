package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.producto;

public class CategoriaDAO {
    private Connection con;

    public CategoriaDAO(Connection recuperaConexion) {
        this.con = con;
    }

    public List<Categoria> listar() {
        List<Categoria> resultado = new ArrayList<>();
        try {
            var querySelect = "SELECT ID, NOMBRE FROM CATEGORIA";
            final PreparedStatement statement = con.prepareStatement(querySelect);
            try (statement) {
                final ResultSet resutlSet = statement.executeQuery();
                try (resutlSet) {
                    while (resutlSet.next()) {
                        var categoria = new Categoria(resutlSet.getInt("ID"), resutlSet.getString("NOMBRE"));
                        resultado.add(categoria);
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public List<Categoria> listarConProductos() {
        List<Categoria> resultado = new ArrayList<>();
        try {
            var querySelect = "SELECT C.ID, C.NOMBRE, P.ID, P.NOMBRE, P.CANTIDAD "
                    + "FROM CATEGORIA C "
                    + "INNER JOIN PRODUCTO P ON C.ID = P.CATEGORIA_ID";
            final PreparedStatement statement = con.prepareStatement(querySelect);
            try (statement) {
                final ResultSet resutlSet = statement.executeQuery();
                try (resutlSet) {
                    while (resutlSet.next()) {
                        Integer categoriaId = resutlSet.getInt("C.ID");
                        String categoriaNombre = resutlSet.getString("NOMBRE");
                        var categoria = resultado.stream().filter(cat -> cat.getId().equals(categoriaId))
                                .findAny().orElseGet(() -> {
                                    Categoria cat = new Categoria(categoriaId, categoriaNombre);

                                    
                                    resultado.add(cat);

                                    return cat;
                                });
                        var producto = new producto(resultSet.getInt("P.ID"), resultSet.getString("P.NOMBRE"),
                                resultSet.getInt("P.CANTIDAD"));

                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

}
