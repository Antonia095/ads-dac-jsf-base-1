package br.edu.ifpb.web.jsp;

import br.edu.ifpb.domain.Cliente;
import br.edu.ifpb.domain.Clientes;
import br.edu.ifpb.infra.ClientesEmJDBC;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// MCV - HTML (View) -> Servlet (Controller) -> Editora (Model)
@WebServlet(name = "ClienteController", urlPatterns = "/cliente.do")
public class ClienteController extends HttpServlet {
    private Clientes clientes = new ClientesEmJDBC();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //atendendo ao processamento
        request.setAttribute("clientes",clientes.todas());
        //redicionar à página
        request.getRequestDispatcher("listar.jsp")
                .forward(request, response); //mantendo a requisição original
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //atendendo ao processamento
        int id = Integer.parseInt(req.getParameter("Id"));
        String nome = req.getParameter("nome");
        LocalDate dataDeNascimento = LocalDate.parse(req.getParameter("dataDeNascimento"));
        String cpf = req.getParameter("cpf");
        clientes.nova(new Cliente(id, nome, dataDeNascimento, cpf));
        //redicionar à página
        resp.sendRedirect("cliente.do"); //gerando uma nova requisição
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
