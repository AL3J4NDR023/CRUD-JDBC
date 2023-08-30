package com.alura.jdbc.controller;

import java.net.IDN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.producto;
import com.alura.jdbc.persistencia.ProductoDAO;

public class ProductoController {

	private ProductoDAO productoDAO;

	public ProductoController() {
		this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(String nombre, String descripcion, Integer id, Integer cantidad) {
		return productoDAO.modificar(nombre, descripcion, id, cantidad);

		
	}

	public int eliminar(Integer id) {
		return productoDAO.eliminar(id);
	}

	public List<producto> listar()  {
		return productoDAO.listar();
	}

	public List<producto> listar(Categoria categoria) {
		return productoDAO.listar(categoria.getId());
	}

	public void guardar(producto producto, Integer categoriaId)  {
		producto.setCategoriaId(categoriaId);
		productoDAO.guardar(producto);
	}
}
