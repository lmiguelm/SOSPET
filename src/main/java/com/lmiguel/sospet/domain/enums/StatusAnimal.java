package com.lmiguel.sospet.domain.enums;


public enum StatusAnimal {
	
	DESAPARECIDO(0, "Desaparecido"),
	ACHADO(1, "Achado"),
	ADOCAO(2, "Adoção"),
	ADOTADO(3, "Adotado");
	
	private int code;
	private String desc;
	
	private StatusAnimal(int code, String desc) {
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
	
	public static StatusAnimal toEnum(Integer code) {
	
		if (code == null) {
			return null;
		}
		
		for (StatusAnimal x : StatusAnimal.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
