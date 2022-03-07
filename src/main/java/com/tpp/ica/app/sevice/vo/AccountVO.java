package com.tpp.ica.app.sevice.vo;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

/**
 * Account Value object hold values received as input from event message
 * 
 * @author adu11
 *
 */
@Getter
@ToString
public class AccountVO {

	private Integer bsb;
	private Integer identification;
	private Date openingDate;

	public AccountVO() {

	}

	public AccountVO(AccountVOBuilder accountVOBuilder) {
		this.bsb = accountVOBuilder.bsb;
		this.identification = accountVOBuilder.identification;
		this.openingDate = accountVOBuilder.openingDate;
	}

	public static class AccountVOBuilder {
		private Integer bsb;
		private Integer identification;
		private Date openingDate;

		public AccountVOBuilder bsb(Integer bsb) {
			this.bsb = bsb;
			return this;
		}

		public AccountVOBuilder identification(Integer identification) {
			this.identification = identification;
			return this;
		}

		public AccountVOBuilder openingDate(Date openingDate) {
			this.openingDate = openingDate;
			return this;
		}

		public AccountVO buld() {
			return new AccountVO(this);

		}

	}

}
