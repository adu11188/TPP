package com.tpp.ica.app.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode
public class AccountDetailsId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer identitification;
	
	private Date openingDate;

}
