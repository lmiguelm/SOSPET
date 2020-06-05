package com.lmiguel.sospet.domain.enums;


public enum SexoAnimal {
	
	MACHO(1, "Macho"),
	FEMEA(2, "Fêmea");
	
	private int code;
	private String desc;
	
	private SexoAnimal(int code, String desc) {
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
	
	public static SexoAnimal toEnum(Integer code) {
	
		if (code == null) {
			return null;
		}
		
		for (SexoAnimal x : SexoAnimal.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
