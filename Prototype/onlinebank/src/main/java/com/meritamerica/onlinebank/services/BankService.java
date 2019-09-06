package com.meritamerica.onlinebank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.Address;
import com.meritamerica.onlinebank.models.User;
import com.meritamerica.onlinebank.repository.AccountRepository;
import com.meritamerica.onlinebank.repository.AddressRepository;
import com.meritamerica.onlinebank.repository.UserRepository;

@Service
public class BankService {
	private final UserRepository uRepo;
	private final AddressRepository aRepo;
	private final AccountRepository acctRepo;
	
	public BankService(UserRepository r, AddressRepository a, AccountRepository ac) { uRepo = r; aRepo = a; acctRepo = ac; }
	
    public List<User> allUsers() { return uRepo.findAll(); }
    
    public User createUser(User u) { return uRepo.save(u); }
    
    public User updateUser(User u) { return uRepo.save(u); }
    
    public Optional<User> findUserByEmail(String email) { return uRepo.findByEmail(email); }
    
    public Optional<User> findUserById(Long id) { return uRepo.Id(id); }
    
    public Address createAddress(Address a) { return aRepo.save(a); }
    
    public Account createAccount(Account a) { return acctRepo.save(a); }
    
    public Optional<Account> findAccountById(Long id) { return acctRepo.Id(id); }
}
