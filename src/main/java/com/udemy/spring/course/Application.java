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

        Product product1 = new Product(null, "Computador", 2000.00);
        Product product2 = new Product(null, "Impressora", 800.00);
        Product product3 = new Product(null, "Mouse", 80.00);

        category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        category2.getProducts().addAll(Collections.singletonList(product2));

        product1.getCategories().addAll(Collections.singletonList(category1));
        product2.getCategories().addAll(Arrays.asList(category1, category2));
        product3.getCategories().addAll(Collections.singletonList(category1));

        categoryRepository.saveAll(Arrays.asList(category1, category2));
        productRepository.saveAll(Arrays.asList(product1, product2, product3));

        State state1 = new State(null, "Minas Gerais");
        State state2 = new State(null, "São Paulo");

        City city1 = new City(null, "Uberlândia", state1);
        City city2 = new City(null, "São Paulo", state2);
        City city3 = new City(null, "Campinas", state2);

        state1.getCities().addAll(Collections.singletonList(city1));
        state2.getCities().addAll(Arrays.asList(city2, city3));

        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));

        Customer customer1 = new Customer(null, "Maria Silva", "maria@gmail.com", "12629141675", CustomerType.PHYSICAL_PERSON);
        customer1.getPhones().addAll(Arrays.asList("38148199", "998068391"));

        Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38408136", customer1, city1);
        Address address2 = new Address(null, "Avenida Matos", "105", "Apto 500", "Centro", "38408135", customer1, city2);

        customer1.getAddresses().addAll(Arrays.asList(address1, address2));

        resourceRepository.saveAll(Collections.singletonList(customer1));
        addressRepository.saveAll(Arrays.asList(address1, address2));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido1 = new Pedido(null, simpleDateFormat.parse("30/09/2017 10:32"), customer1, address1);
        Pedido pedido2 = new Pedido(null, simpleDateFormat.parse("30/10/2019 11:27"), customer1, address2);

        Payment payment1 = new CardPayment(null, PaymentState.SETTLED, pedido1, 6);
        pedido1.setPayment(payment1);

        Payment payment2 = new BilletPayment(null, PaymentState.PENDING, pedido2, simpleDateFormat.parse("20/10/2017 00:00"), null);
        pedido2.setPayment(payment2);

        customer1.getOrders().addAll(Arrays.asList(pedido1, pedido2));

        orderRepository.saveAll(Arrays.asList(pedido1, pedido2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        OrderItem orderItem1 = new OrderItem(pedido1, product1, 0.00, 1, 2000.00);
        OrderItem orderItem2 = new OrderItem(pedido1, product3, 0.00, 2, 80.00);
        OrderItem orderItem3 = new OrderItem(pedido2, product2, 100.00, 1, 800.00);

        pedido1.getItens().addAll(Arrays.asList(orderItem1, orderItem2));
        pedido2.getItens().addAll(Collections.singletonList(orderItem3));

        product1.getItens().addAll(Collections.singletonList(orderItem1));
        product2.getItens().addAll(Collections.singletonList(orderItem3));
        product3.getItens().addAll(Collections.singletonList(orderItem2));

        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));

    }
}
