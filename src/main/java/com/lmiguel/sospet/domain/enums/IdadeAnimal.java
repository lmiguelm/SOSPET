package com.lmiguel.sospet.domain.enums;


public enum IdadeAnimal {
	
	FILHOTE(1, "Filhote"),
	ADULTO(2, "Adulto");
	
	private int code;
	private String desc;
	
	private IdadeAnimal(int code, String desc) {
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
	
	public static IdadeAnimal toEnum(Integer code) {
	
		if (code == null) {
			return null;
		}
		
		for (IdadeAnimal x : IdadeAnimal.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
