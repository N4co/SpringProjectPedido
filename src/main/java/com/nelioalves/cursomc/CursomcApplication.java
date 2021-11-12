package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoCartao;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import com.nelioalves.cursomc.repository.CidadeRepository;
import com.nelioalves.cursomc.repository.ClienteRepository;
import com.nelioalves.cursomc.repository.EnderecoRepository;
import com.nelioalves.cursomc.repository.EstadoRepository;
import com.nelioalves.cursomc.repository.ItemPedidoRepository;
import com.nelioalves.cursomc.repository.PagamentoRepository;
import com.nelioalves.cursomc.repository.PedidoRepository;
import com.nelioalves.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

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
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Livraria");
		Categoria cat5 = new Categoria(null, "biblioteca");
		Categoria cat6 = new Categoria(null, "Jardinagem");
		Categoria cat7 = new Categoria(null, "Ferramentas");
		
		Produto p1 = new Produto(null, "computador", 2000.00); 
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
	
	    categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
	    produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	
	    Estado est1 = new Estado(null, "Minas Gerais");
	    Estado est2 = new Estado(null, "São Paulo");    
	    
	    Cidade c1 = new Cidade(null, "Uberlândia", est1);
	    Cidade c2 = new Cidade(null, "São Paulo", est2);
	    Cidade c3 = new Cidade(null, "Campinas", est2);
	    
	    est1.getCidades().addAll(Arrays.asList(c1));
	    est2.getCidades().addAll(Arrays.asList(c2, c3));
	  
	    estadoRepository.saveAll(Arrays.asList(est1, est2));
	    cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	    
	    Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA );
	    
	    cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
	    
	    Endereco e1 = new Endereco(null, "Rua Flores", "numero 300", "Apto 203", "Jardim", "cep 38220834", cli1, c1);
	    Endereco e2 = new Endereco(null, "Avenida Matos", "numero 105", "Sala 800", "Centro", "Cep 38777012", cli1, c2);
	   
	   cli1.getEnderecos().addAll(Arrays.asList(e1, e2)); 
	
	   clienteRepository.saveAll(Arrays.asList(cli1));
	   enderecoRepository.saveAll(Arrays.asList(e1,e2));
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	   
	  Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	  Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 10:25"), cli1, e2); 
	   
	   Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	   ped1.setPagamento(pagto1);
	
	   Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("19/10/2021 00:00"), null);
	   ped2.setPagamento(pagto2);
	
	   cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

 
	   pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	   pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	
       ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
	   ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	   ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	
	   ped1.getItens().addAll(Arrays.asList(ip1, ip2));
	   ped2.getItens().addAll(Arrays.asList(ip3));
	   
	   p1.getItens().addAll(Arrays.asList(ip1));
	   p2.getItens().addAll(Arrays.asList(ip3));
	   p3.getItens().addAll(Arrays.asList(ip2));
	   
	   itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	   
	   
	   
	}
	
	}


