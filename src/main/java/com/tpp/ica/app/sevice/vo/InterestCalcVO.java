package com.tpp.ica.app.sevice.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class InterestCalcVO {
	
	private LocalDate balanceDate;
	private Integer identification;
	private BigDecimal balance;

}
