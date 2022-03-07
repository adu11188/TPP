package com.tpp.ica.app.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode
public class InterestDetailsId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer identification;
	
	private LocalDate balanceDate;

}
