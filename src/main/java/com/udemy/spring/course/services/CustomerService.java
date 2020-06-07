package com.udemy.spring.course.services;

import com.udemy.spring.course.domain.Address;
import com.udemy.spring.course.domain.City;
import com.udemy.spring.course.domain.Customer;
import com.udemy.spring.course.domain.enums.CustomerType;
import com.udemy.spring.course.dto.CustomerDTO;
import com.udemy.spring.course.dto.CustomerNewDTO;
import com.udemy.spring.course.repositories.AddressRepository;
import com.udemy.spring.course.repositories.CustomerRepository;
import com.udemy.spring.course.services.exception.DataIntegrityException;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer find(Integer id){
        Optional<Customer> obj = customerRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                String.format("Object not found with id {%s} of type %s", id, Customer.class.getName())
        ));
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return customerRepository.findAll(pageRequest);
    }

    @Transactional
    public Customer insert(Customer obj){
        obj.setId(null);
        obj = customerRepository.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
    }

    public Customer update(Customer obj) {
        Customer newObj = find(obj.getId());
        updateData(newObj, obj);
        return customerRepository.save(newObj);
    }

    private void updateData(Customer newObj, Customer obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try{
            customerRepository.deleteById(id);
        } catch(DataIntegrityViolationException exception){
            throw new DataIntegrityException("Delete is not possible when customer has requests");
        }
    }

    public Customer fromDTO(CustomerDTO objDto){
        return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }

    public Customer fromDTO(CustomerNewDTO objDto){
        Customer customer = new Customer(null, objDto.getName(), objDto.getEmail(), objDto.getPersonIdentity(), CustomerType.toEnum(objDto.getType()));
        City city = new City(objDto.getCityId(), null, null);
        Address address = new Address(null, objDto.getPublicPlace(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getZipCode(), customer, city);
        customer.getAddresses().add(address);
        customer.getPhones().add(objDto.getPhone1());
        if(objDto.getPhone2()!=null){
            customer.getPhones().add(objDto.getPhone2());
        }
        if(objDto.getPhone3()!=null){
            customer.getPhones().add(objDto.getPhone3());
        }
        return customer;
    }
}
