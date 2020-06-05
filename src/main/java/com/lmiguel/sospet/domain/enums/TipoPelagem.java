package com.lmiguel.sospet.domain.enums;


public enum TipoPelagem {
	
	CURTA(1, "Curta"),
	MEDIA(2, "Média"),
	LONGA(4, "Longa");
	
	private int code;
	private String desc;
	
	private TipoPelagem(int code, String desc) {
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
	
	public static TipoPelagem toEnum(Integer code) {
	
		if (code == null) {
			return null;
		}
		
		for (TipoPelagem x : TipoPelagem.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
