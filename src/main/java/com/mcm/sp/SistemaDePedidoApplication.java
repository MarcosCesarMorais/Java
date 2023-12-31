package com.mcm.sp;

import java.text.SimpleDateFormat;
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
import com.mcm.sp.entities.ItemPedido;
import com.mcm.sp.entities.Pagamento;
import com.mcm.sp.entities.PagamentoComBoleto;
import com.mcm.sp.entities.PagamentoComCartao;
import com.mcm.sp.entities.Pedido;
import com.mcm.sp.entities.Produto;
import com.mcm.sp.entities.enums.EstadoPagamento;
import com.mcm.sp.entities.enums.TipoCliente;
import com.mcm.sp.repositories.CategoriaRepository;
import com.mcm.sp.repositories.CidadeRepository;
import com.mcm.sp.repositories.ClienteRepository;
import com.mcm.sp.repositories.EnderecoRepository;
import com.mcm.sp.repositories.EstadoRepository;
import com.mcm.sp.repositories.ItemPedidoRepository;
import com.mcm.sp.repositories.PagamentoRepository;
import com.mcm.sp.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaDePedidoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Roupas");
		Categoria cat4 = new Categoria(null, "Bannho");
		Categoria cat5 = new Categoria(null, "Bebidas");
		Categoria cat6 = new Categoria(null, "Jardim");
		Categoria cat7 = new Categoria(null, "Brinquedos");
		Categoria cat8 = new Categoria(null, "Automoveis");
		Categoria cat9 = new Categoria(null, "Cozinha");
		Categoria cat10 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		Produto p11 = new Produto(null,"Shampoo", 90.00);
		Produto p4 = new Produto(null,"Mesa de escritório", 300.00);
		Produto p5 = new Produto(null,"Toalha", 50.00);
		Produto p6 = new Produto(null,"Colcha", 200.00);
		Produto p7 = new Produto(null,"TV", 1200.00);
		Produto p8 = new Produto(null,"Roçadeira", 800.00);
		Produto p9 = new Produto(null,"Abajour", 100.00);
		Produto p10 = new Produto(null,"Pendente", 180.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped1));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido (ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido (ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido (ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped1.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
