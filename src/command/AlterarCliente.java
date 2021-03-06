package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import service.ClienteService;

public class AlterarCliente implements Command {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pFone = request.getParameter("fone");
		String pEmail = request.getParameter("email");
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (Exception e) {

		}
		ClienteService cs = new ClienteService();
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(pNome);
		cliente.setFone(pFone);
		cliente.setEmail(pEmail);
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		cs.atualizar(cliente);
		@SuppressWarnings("unchecked")
		ArrayList<Cliente> lista = (ArrayList<Cliente>) session
				.getAttribute("lista");
		int pos = busca(cliente, lista);
		lista.remove(pos);
		lista.add(pos, cliente);
		session.setAttribute("lista", lista);
		request.setAttribute("cliente", cliente);
		view = request.getRequestDispatcher("VisualizarCliente.jsp");
		view.forward(request, response);
	}

	public int busca(Cliente cliente, ArrayList<Cliente> lista) {
		Cliente to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == cliente.getId()) {
				return i;
			}
		}
		return -1;
	}

}
