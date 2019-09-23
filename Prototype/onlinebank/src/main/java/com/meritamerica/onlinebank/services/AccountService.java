package com.meritamerica.onlinebank.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.meritamerica.onlinebank.models.Account;
import com.meritamerica.onlinebank.models.Address;
import com.meritamerica.onlinebank.repository.AccountRepository;
import com.meritamerica.onlinebank.repository.AddressRepository;
import com.meritamerica.onlinebank.repository.UserRepository;

@Service
public class AccountService {
	private final AddressRepository aRepo;
	private final AccountRepository acctRepo;
	
	public AccountService(UserRepository r, AddressRepository a, AccountRepository ac) { aRepo = a; acctRepo = ac; }
	

    
    public Address createAddress(Address a) { return aRepo.save(a); }
    
    public Account createAccount(Account a) { return acctRepo.save(a); }
    public Account updateAccount(Account a) { return acctRepo.save(a); }
    
    public Optional<Account> findAccountById(Long id) { return acctRepo.Id(id); }
    public Optional<Account> findByAccountNumber(Long num) { return acctRepo.findByAccountNumber(num); }
}
