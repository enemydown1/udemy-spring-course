package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.*;
import com.udemy.spring.course.domain.enums.PaymentState;
import com.udemy.spring.course.domain.enums.Profile;
import com.udemy.spring.course.repositories.*;
import com.udemy.spring.course.security.UserSpringSecurity;
import com.udemy.spring.course.services.exception.AuthorizationException;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public Request find(Integer id){
        Optional<Request> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                String.format("Object not found with id {%s} of type %s", id, Request.class.getName())
        ));
    }

    public Request insert(Request obj) {
        obj.setId(null);
        obj.setCreatedAt(new Date());
        obj.getPayment().setState(PaymentState.PENDING);
        obj.getPayment().setRequest(obj);
        if(obj.getPayment() instanceof BilletPayment){
            BilletPayment billetPayment = (BilletPayment) obj.getPayment();
            billetService.setBilletPayment(billetPayment, obj.getCreatedAt());
        }
        obj = repository.save(obj);
        paymentRepository.save(obj.getPayment());
        for(RequestItem requestItem : obj.getItens()){
            requestItem.setDiscount(0.0);
            requestItem.setPrice(productService.find(requestItem.getProduct().getId()).getPrice());
            requestItem.setRequest(obj);
        }
        requestItemRepository.saveAll(obj.getItens());
        return obj;
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
