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
import com.alura.jdbc.modelo.producto;
import com.alura.jdbc.persistencia.ProductoDAO;

public class ProductoController {

	private ProductoDAO preductoDAO;

	public ProductoController() {
		this.preductoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(String nombre, String descripcion, Integer id, Integer cantidad) {
		return preductoDAO.modificar(nombre, descripcion, id, cantidad);

		
	}

	public int eliminar(Integer id) {
		return preductoDAO.eliminar(id);
	}

	public List<producto> listar()  {
		return preductoDAO.listar();
	}

	public void guardar(producto producto)  {
		ProductoDAO productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
		productoDAO.guardar(producto);
	}
}
