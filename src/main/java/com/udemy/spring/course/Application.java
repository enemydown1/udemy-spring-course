package com.udemy.spring.course;

import com.udemy.spring.course.domain.*;
import com.udemy.spring.course.domain.enums.PaymentState;
import com.udemy.spring.course.domain.enums.CustomerType;
import com.udemy.spring.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category(null, "Informática");
        Category category2 = new Category(null, "Escritório");

        Produto produto1 = new Produto(null, "Computador", 2000.00);
        Produto produto2 = new Produto(null, "Impressora", 800.00);
        Produto produto3 = new Produto(null, "Mouse", 80.00);

        category1.getProducts().addAll(Arrays.asList(produto1,produto2,produto3));
        category2.getProducts().addAll(Collections.singletonList(produto2));

        produto1.getCategories().addAll(Collections.singletonList(category1));
        produto2.getCategories().addAll(Arrays.asList(category1, category2));
        produto3.getCategories().addAll(Collections.singletonList(category1));

        categoryRepository.saveAll(Arrays.asList(category1, category2));
        productRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

        Estado estado1 = new Estado(null, "Minas Gerais");
        Estado estado2 = new Estado(null, "São Paulo");

        Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
        Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
        Cidade cidade3 = new Cidade(null, "Campinas", estado2);

        estado1.getCidades().addAll(Collections.singletonList(cidade1));
        estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));

        stateRepository.saveAll(Arrays.asList(estado1, estado2));
        cityRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

        Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12629141675", CustomerType.PHYSICAL_PERSON);
        cliente1.getTelefones().addAll(Arrays.asList("38148199", "998068391"));

        Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38408136", cliente1, cidade1);
        Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Apto 500", "Centro", "38408135", cliente1, cidade2);

        cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

        resourceRepository.saveAll(Collections.singletonList(cliente1));
        addressRepository.saveAll(Arrays.asList(endereco1, endereco2));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido1 = new Pedido(null, simpleDateFormat.parse("30/09/2017 10:32"), cliente1, endereco1);
        Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("30/10/2019 11:27"), cliente1, endereco2);

        Pagamento pagamento1 = new PagamentoComCartao(null, PaymentState.SETTLED, pedido1, 6);
        pedido1.setPagamento(pagamento1);

        Pagamento pagamento2 = new PagamentoComBoleto(null, PaymentState.PENDING, pedido2, simpleDateFormat.parse("20/10/2017 00:00"), null);
        pedido2.setPagamento(pagamento2);

        cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

        orderRepository.saveAll(Arrays.asList(pedido1, pedido2));
        paymentRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

        ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 0.00, 1, 2000.00);
        ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 0.00, 2, 80.00);
        ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 100.00, 1, 800.00);

        pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
        pedido2.getItens().addAll(Collections.singletonList(itemPedido3));

        produto1.getItens().addAll(Collections.singletonList(itemPedido1));
        produto2.getItens().addAll(Collections.singletonList(itemPedido3));
        produto3.getItens().addAll(Collections.singletonList(itemPedido2));

        orderItemRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));

    }
}
