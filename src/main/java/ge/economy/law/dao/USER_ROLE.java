package ge.economy.law.dao;

import java.util.HashMap;
import java.util.Map;

public enum USER_ROLE {
	JUSTICE_ADMIN("JUSTICE_ADMIN",1), JUSTICE_OPERATOR("JUSTICE_OPERATOR",2), JUSTICE_SIP_ADMIN("JUSTICE_SIP_ADMIN",3),
	JUSTICE_SIP_OPERATOR("JUSTICE_SIP_OPERATOR",4);

	private String value;
	private int code;
	
	private static final Map<String, USER_ROLE> USER_ROLES = new HashMap<String, USER_ROLE>();

	private USER_ROLE(String value,int code) {
		this.value = value;
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}
	public int getCode() {
		return this.code;
	}
	
	static {
		for(USER_ROLE userRole : values()) {
			USER_ROLES.put(userRole.getValue(), userRole);
		}
	}
}
