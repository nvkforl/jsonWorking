package com.example.pojo;

import java.util.Set;

public class Clm_flds {

	private String claim_nbr;

	private String claim_loss_dt;

	private String claim_peril;

	private String claim_status;

	public Clm_flds() {}
	
	public Clm_flds(String claim_nbr, String claim_loss_dt, String claim_peril, String claim_status) {
		this.claim_nbr = claim_nbr;
		this.claim_loss_dt = claim_loss_dt;
		this.claim_peril = claim_peril;
		this.claim_status = claim_status;
	}

	public String getClaim_nbr() {
		return claim_nbr;
	}

	public void setClaim_nbr(String claim_nbr) {
		this.claim_nbr = claim_nbr;
	}

	public String getClaim_loss_dt() {
		return claim_loss_dt;
	}

	public void setClaim_loss_dt(String claim_loss_dt) {
		this.claim_loss_dt = claim_loss_dt;
	}

	public String getClaim_peril() {
		return claim_peril;
	}

	public void setClaim_peril(String claim_peril) {
		this.claim_peril = claim_peril;
	}

	public String getClaim_status() {
		return claim_status;
	}

	public void setClaim_status(String claim_status) {
		this.claim_status = claim_status;
	}

	@Override
	public String toString() {
		return "Clm_flds [claim_nbr=" + claim_nbr + ", claim_loss_dt=" + claim_loss_dt + ", claim_peril=" + claim_peril
				+ ", claim_status=" + claim_status + "]";
	}

}
