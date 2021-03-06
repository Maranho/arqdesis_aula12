package service;

import java.io.IOException;

import model.Cliente;
import dao.ClienteDAO;

public class ClienteService {
	private ClienteDAO dao;
	
	public ClienteService() {
		dao = new ClienteDAO();
	}

	public int criar(Cliente cliente) throws IOException {
		return dao.incluir(cliente);
	}

	public void atualizar(Cliente cliente) throws IOException {
		dao.atualizar(cliente);
	}

	public void excluir(int id) throws IOException {
		dao.excluir(id);
	}

	public Cliente carregar(int id) throws IOException {
		Cliente cliente = dao.carregar(id);
		return cliente;
	}
}
