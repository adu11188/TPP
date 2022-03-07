package com.tpp.ica.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "INTEREST_DETAILS")
@Getter
@Setter
@ToString
@IdClass(InterestDetailsId.class)
public class InterestDetails implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "INTEREST_AMOUNT")
	private BigDecimal interestAmount;
	
    @Id
	@Column(name = "identification")
	private Integer identification;
	
	@Column(name ="BALANCE_DATE")
	private LocalDate balanceDate;
	
	@Column(name ="BALANCE")
	private BigDecimal balance;
	
}
