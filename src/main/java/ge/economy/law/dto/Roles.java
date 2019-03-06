package ge.economy.law.dto;

public enum Roles {
	JUSTICE_ADMIN("token1"),
	JUSTICE_OPERATOR("token2"),
	JUSTICE_SIP_ADMIN("token3"),
	JUSTICE_SIP_OPERATOR("token4");
	
	private String value;

	public String getValue() {
		return value;
	}

	private Roles(String value) {
		this.value = value;
	}
}
