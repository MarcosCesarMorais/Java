package com.mcm.sp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mcm.sp.entities.Categoria;
import com.mcm.sp.entities.Cidade;
import com.mcm.sp.entities.Cliente;
import com.mcm.sp.entities.Endereco;
import com.mcm.sp.entities.Estado;
import com.mcm.sp.entities.Produto;
import com.mcm.sp.entities.enums.TipoCliente;
import com.mcm.sp.repositories.CategoriaRepository;
import com.mcm.sp.repositories.CidadeRepository;
import com.mcm.sp.repositories.ClienteRepository;
import com.mcm.sp.repositories.EnderecoRepository;
import com.mcm.sp.repositories.EstadoRepository;
import com.mcm.sp.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaDePedidoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaDePedidoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));	
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "maria@gmail.com","03639437296", TipoCliente.PESSOAFISICA);
		
		cl1.getTelefones().addAll(Arrays.asList("22547821","58945267"));
		
		Endereco e1 = new Endereco(null,"Rua Dona Barbara","300","ap 12","Uberaba","38220834",cl1, c1);
		Endereco e2 = new Endereco(null,"Reinaldo Issberner","408","Sobrado Azul","Cajuru","38220834",cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}
