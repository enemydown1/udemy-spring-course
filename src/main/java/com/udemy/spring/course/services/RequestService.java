package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.BilletPayment;
import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.Request;
import com.udemy.spring.course.domain.RequestItem;
import com.udemy.spring.course.domain.enums.PaymentState;
import com.udemy.spring.course.repositories.CustomerRepository;
import com.udemy.spring.course.repositories.PaymentRepository;
import com.udemy.spring.course.repositories.RequestItemRepository;
import com.udemy.spring.course.repositories.RequestRepository;
import com.udemy.spring.course.security.UserSpringSecurity;
import com.udemy.spring.course.services.exception.AuthorizationException;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private BilletService billetService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RequestItemRepository requestItemRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    public Request find(Integer id){
        Optional<Request> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                String.format("Object not found with id {%s} of type %s", id, Request.class.getName())
        ));
    }

    public Request insert(Request request) {
        request.setId(null);
        request.setCreatedAt(new Date());
        request.setCustomer(customerService.find(request.getCustomer().getId()));
        request.getPayment().setState(PaymentState.PENDING);
        request.getPayment().setRequest(request);
        if(request.getPayment() instanceof BilletPayment){
            BilletPayment billetPayment = (BilletPayment) request.getPayment();
            billetService.setBilletPayment(billetPayment, request.getCreatedAt());
        }
        request = repository.save(request);
        paymentRepository.save(request.getPayment());
        for(RequestItem requestItem : request.getItens()){
            requestItem.setDiscount(0.0);
            requestItem.setProduct(productService.find(requestItem.getProduct().getId()));
            requestItem.setPrice(requestItem.getProduct().getPrice());
            requestItem.setRequest(request);
        }
        requestItemRepository.saveAll(request.getItens());
        emailService.sendOrderConfirmationHtmlEmail(request);
        return request;
    }

    public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        UserSpringSecurity user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Access Denied");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Customer customer = customerService.find(user.getId());
        return repository.findByCustomer(customer, pageRequest);
    }
}
