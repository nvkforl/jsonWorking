package com.jsonJPA.jsonWorking.valueObj.aggregated;

import java.util.Set;

public class Clm_flds {
	private Set<String> claim_status;

	private Set<String> claim_loss_dt;

	private String claim_nbr;

	private Set<String> claim_peril;

	public Set<String> getClaim_status() {
		return claim_status;
	}

	public void setClaim_status(Set<String> claim_status) {
		this.claim_status = claim_status;
	}

	public Set<String> getClaim_loss_dt() {
		return claim_loss_dt;
	}

	public void setClaim_loss_dt(Set<String> claim_loss_dt) {
		this.claim_loss_dt = claim_loss_dt;
	}

	public String getClaim_nbr() {
		return claim_nbr;
	}

	public void setClaim_nbr(String claim_nbr) {
		this.claim_nbr = claim_nbr;
	}

	public Set<String> getClaim_peril() {
		return claim_peril;
	}

	public void setClaim_peril(Set<String> claim_peril) {
		this.claim_peril = claim_peril;
	}

	@Override
	public String toString() {
		return "Clm_flds [claim_status=" + claim_status + ", claim_loss_dt=" + claim_loss_dt + ", claim_nbr="
				+ claim_nbr + ", claim_peril=" + claim_peril + "]";
	}

}