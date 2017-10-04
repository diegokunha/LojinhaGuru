package teste;



import java.util.Calendar;

import br.com.lojinhaguru.dao.ClienteDao;
import br.com.lojinhaguru.model.Clientes;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Clientes cliente = new Clientes();
		
		cliente.setNome("Diego Fernando da Cunha");
		cliente.setEndereco("Rua X");
		cliente.setNumero("95");
		cliente.setBairro("Morongo");
		cliente.setMunicipio("Nova Iguaçu");
		cliente.setUf("RJ");
		cliente.setCep("26070-000");
		cliente.setContato("21986603071");
		cliente.setEmail("diegokunha@gmail.com");
		cliente.setDataNascimento(Calendar.getInstance());
		
		ClienteDao dao = new ClienteDao();
		dao.incluiCliente(cliente);
		
		System.out.println("Gravado!!");
		
	}

}
