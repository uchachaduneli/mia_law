/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.law.security.auth;

/**
 * @author ucha
 */
public class UserNotAuthorizedException extends Exception {

    public UserNotAuthorizedException() {
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
