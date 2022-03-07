package com.tpp.ica.app.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tpp.ica.app.entity.InterestDetails;


@Repository
public interface InterestDetailsRepository extends CrudRepository<InterestDetails, Long>    {
	
	
	@Query("SELECT sum(i.interestAmount) from InterestDetails i  where i.identification = ?1 AND MONTH(i.balanceDate) = ?2 AND YEAR(i.balanceDate) = ?3")
	String getMonthlInterest(int id, int month,int year);

}
