package com.tpp.ica.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNT_DETAILS")
@Getter
@Setter
@IdClass(AccountDetailsId.class)
public class AccountDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "bsb")
	private Integer bsb;
	
    @Id
	@Column(name = "identitification")
	private Integer identitification;
	
	@Column(name ="opening_date")
	private Date openingDate;
	
	@Column(name ="balance")
	private BigDecimal balance;
	

}
