package com.udemy.spring.course.services.validation;

import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.enums.CustomerType;
import com.udemy.spring.course.dto.CustomerDTO;
import com.udemy.spring.course.dto.CustomerNewDTO;
import com.udemy.spring.course.repositories.CustomerRepository;
import com.udemy.spring.course.resources.exception.FieldMessage;
import com.udemy.spring.course.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void initialize(CustomerUpdate ann){
    }

    @Override
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context){
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Customer customer = customerRepository.findByEmail(objDto.getEmail());

        if(customer != null && !customer.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email already exists on database."));
        }

        for(FieldMessage error: list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
