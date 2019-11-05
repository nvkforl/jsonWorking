package com.example;

public class Obj {
	private String aorcode;

	private String payload;

	public Obj(String aorcode, String payload) {
		this.aorcode = aorcode;
		this.payload = payload;
	}

	public String getAorcode() {
		return aorcode;
	}

	public void setAorcode(String aorcode) {
		this.aorcode = aorcode;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Obj [aorcode=" + aorcode + ", payload=" + payload + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aorcode == null) ? 0 : aorcode.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obj other = (Obj) obj;
		if (aorcode == null) {
			if (other.aorcode != null)
				return false;
		} else if (!aorcode.equals(other.aorcode))
			return false;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		return true;
	}
	
	
	
	
}
