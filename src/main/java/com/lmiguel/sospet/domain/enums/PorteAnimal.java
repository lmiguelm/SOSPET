package com.lmiguel.sospet.domain.enums;


public enum PorteAnimal {
	
	PEQUENO(1, "Pequeno"),
	MEDIO(2, "Médio"),
	GRANDE(3, "Grande");
	
	private int code;
	private String desc;
	
	private PorteAnimal(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static PorteAnimal toEnum(Integer code) {
	
		if (code == null) {
			return null;
		}
		
		for (PorteAnimal x : PorteAnimal.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
