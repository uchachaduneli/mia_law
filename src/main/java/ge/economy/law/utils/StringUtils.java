/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.law.utils;

import java.util.Arrays;
import java.util.List;

import ge.economy.law.dao.USER_ROLE;

/**
 *
 * @author ucha
 */
public class StringUtils {

    public static boolean isNotEmptyAndNull(String s) {
        return s != null && !s.isEmpty();
    }
    
    public static String rebuildString(String role) {
    	List<String> roles = Arrays.asList(role.split(","));
    	StringBuffer buffer = new StringBuffer();
    	if(roles.contains(USER_ROLE.JUSTICE_ADMIN.getValue())) {
    		buffer.append(USER_ROLE.JUSTICE_ADMIN.getValue());
    		buffer.append(",").append(USER_ROLE.JUSTICE_OPERATOR.getValue());
    		if(roles.contains(USER_ROLE.JUSTICE_SIP_ADMIN.getValue())) {
    			buffer.append(",").append(USER_ROLE.JUSTICE_SIP_ADMIN.getValue());
    			buffer.append(",").append(USER_ROLE.JUSTICE_SIP_OPERATOR.getValue());
    		}
    	}else {
    		if(roles.contains(USER_ROLE.JUSTICE_OPERATOR.getValue())) {
    			buffer.append(USER_ROLE.JUSTICE_OPERATOR.getValue());
    		}
    		if(roles.contains(USER_ROLE.JUSTICE_SIP_ADMIN.getValue())) {
    			buffer.append(USER_ROLE.JUSTICE_SIP_ADMIN.getValue());
    			buffer.append(",").append(USER_ROLE.JUSTICE_SIP_OPERATOR.getValue());
    		}else if(roles.contains(USER_ROLE.JUSTICE_SIP_OPERATOR.getValue())) {
    			buffer.append(USER_ROLE.JUSTICE_SIP_OPERATOR.getValue());
    		}
    	}
    	return buffer.toString();
    }
}
