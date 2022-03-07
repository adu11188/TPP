package com.tpp.ica.app.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tpp.ica.app.entity.AccountDetails;


@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetails, Long>    {

}
