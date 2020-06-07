package com.lmiguel.sospet.domain.enums;


public enum TipoAnimal {
	
	CACHORRO(0, "Cachorro"),
	GATO(1, "Gato"),
	OUTROS(2, "Outros");
	
	private int code;
	private String desc;
	
	private TipoAnimal(int code, String desc) {
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
	
	public static TipoAnimal toEnum(Integer code) {
	
		if (code == null) {
			return null;
		}
		
		for (TipoAnimal x : TipoAnimal.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
