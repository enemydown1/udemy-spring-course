package com.udemy.spring.course.services.validation;

import com.udemy.spring.course.domain.enums.CustomerType;
import com.udemy.spring.course.dto.CustomerNewDTO;
import com.udemy.spring.course.resources.exception.FieldMessage;
import com.udemy.spring.course.services.validation.utils.BR;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {

    @Override
    public void initialize(CustomerInsert ann){
    }

    @Override
    public boolean isValid(CustomerNewDTO objDto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getType().equals(CustomerType.PHYSICAL_PERSON.getCode()) && !BR.isValidCPF(objDto.getPersonIdentity())){
            list.add(new FieldMessage("personIdentity", "CPF is not valid"));
        }

        if(objDto.getType().equals(CustomerType.LEGAL_PERSON.getCode()) && !BR.isValidCNPJ(objDto.getPersonIdentity())){
            list.add(new FieldMessage("personIdentity", "CNPJ is not valid"));
        }

        for(FieldMessage error: list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
