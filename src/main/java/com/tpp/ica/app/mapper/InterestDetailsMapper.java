package com.tpp.ica.app.mapper;

import org.springframework.stereotype.Component;

import com.tpp.ica.app.entity.InterestDetails;
import com.tpp.ica.app.sevice.vo.InterestCalcVO;

/**
 * 
 * @author adu11
 *
 */
@Component
public class InterestDetailsMapper {
	
	public void interestDetailsVoToEntityMapper(InterestCalcVO interestCalcVO, InterestDetails interestDetails) {
		interestDetails.setIdentification(interestCalcVO.getIdentification());
		interestDetails.setBalanceDate(interestCalcVO.getBalanceDate());
		interestDetails.setBalance(interestCalcVO.getBalance());
	}

}
