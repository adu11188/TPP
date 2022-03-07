package com.tpp.ica.app.mapper;

import org.springframework.stereotype.Component;

import com.tpp.ica.app.entity.AccountDetails;
import com.tpp.ica.app.sevice.vo.AccountVO;
/**
 * Mapper helper to transform object like VO,POJO to entity
 * 
 * E.g Transform input values to required value for entity objects
 * 
 * @author adu11
 *
 */
@Component
public class AccountDetailsMapper {
	
	/**
	 * Account details Value object to Entity mapping logic
	 * 
	 * @param accountVO
	 * @param accountDetails
	 */
	public void accountDetailsVoToEntityMapper(AccountVO accountVO, AccountDetails accountDetails) {
		accountDetails.setBsb(accountVO.getBsb());
		accountDetails.setIdentitification(accountVO.getIdentification());
		accountDetails.setOpeningDate(accountVO.getOpeningDate());
	}

}
