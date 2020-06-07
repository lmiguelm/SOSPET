package com.lmiguel.sospet.domain.enums;


public enum SexoPessoa {
	
	MASCULINO(0, "Masculino"),
	FEMININO(1, "Feminino");
	
	private int code;
	private String desc;
	
	private SexoPessoa(int code, String desc) {
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
	
	public static SexoPessoa toEnum(Integer code) {
	
		if (code == null) {
			return null;
		}
		
		for (SexoPessoa x : SexoPessoa.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
