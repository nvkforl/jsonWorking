package com.jsonJPA.jsonWorking.valueObj.aggregated;


public class InputFromIntermediate {

	private int pkId;

	private AggregatedPayload AggregatedPayload;

	public int getPkId() {
		return pkId;
	}

	public void setPkId(int pkId) {
		this.pkId = pkId;
	}

	public AggregatedPayload getAggregatedPayload() {
		return AggregatedPayload;
	}

	public void setAggregatedPayload(AggregatedPayload aggregatedPayload) {
		AggregatedPayload = aggregatedPayload;
	}



}
