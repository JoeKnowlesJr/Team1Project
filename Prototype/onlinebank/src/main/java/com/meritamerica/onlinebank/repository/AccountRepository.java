package com.meritamerica.onlinebank.repository;

import org.springframework.stereotype.Repository;

import com.meritamerica.onlinebank.models.Account;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {
}
