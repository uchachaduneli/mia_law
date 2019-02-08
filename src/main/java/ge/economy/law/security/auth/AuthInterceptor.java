package ge.economy.law.security.auth;

import org.keycloak.AuthorizationContext;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ucha
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws IOException {

		KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) request
				.getAttribute(KeycloakSecurityContext.class.getName());
		if (keycloakSecurityContext != null) {
			AuthorizationContext authzContext = keycloakSecurityContext.getAuthorizationContext();
		}

		Integer userId = (Integer) request.getSession().getAttribute("userId");
		String uri = request.getRequestURI();

		if (userId == null && request.getHeader("X-Requested-With") == null) {
			if (uri.startsWith(request.getContextPath())) {
				uri = uri.replace(request.getContextPath(), "");
			}
			response.sendRedirect("login");
			if (uri.length() > 0 && !uri.equals("/")) {
				response.sendRedirect("login");
			} else {

			}
			return false;
		} else if (userId == null) {
			response.sendError(353,
					"ÃƒÂ¡Ã†â€™Ã‚Â¡ÃƒÂ¡Ã†â€™Ã¢â‚¬ï¿½ÃƒÂ¡Ã†â€™Ã‚Â¡ÃƒÂ¡Ã†â€™Ã‹Å“ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã‚Â¡ ÃƒÂ¡Ã†â€™Ã¢â‚¬â„¢ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã‚Â£ÃƒÂ¡Ã†â€™Ã¢â‚¬Â¢ÃƒÂ¡Ã†â€™Ã‹Å“ÃƒÂ¡Ã†â€™Ã¢â‚¬Å“ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ ÃƒÂ¡Ã†â€™Ã¢â‚¬Â¢ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã¢â‚¬Å“ÃƒÂ¡Ã†â€™Ã¯Â¿Â½, ÃƒÂ¡Ã†â€™Ã¢â‚¬â„¢ÃƒÂ¡Ã†â€™Ã¢â‚¬â€�ÃƒÂ¡Ã†â€™Ã‚Â®ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã¢â‚¬Â¢ÃƒÂ¡Ã†â€™Ã¢â‚¬â€� ÃƒÂ¡Ã†â€™Ã¢â‚¬â€�ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã¢â‚¬Â¢ÃƒÂ¡Ã†â€™Ã‹Å“ÃƒÂ¡Ã†â€™Ã¢â‚¬Å“ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã…â€œ ÃƒÂ¡Ã†â€™Ã¢â‚¬â„¢ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã‹Å“ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã‚Â ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã¢â‚¬â€� ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã¢â‚¬Â¢ÃƒÂ¡Ã†â€™Ã‚Â¢ÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã‚Â ÃƒÂ¡Ã†â€™Ã‹Å“ÃƒÂ¡Ã†â€™Ã¢â‚¬â€œÃƒÂ¡Ã†â€™Ã¯Â¿Â½ÃƒÂ¡Ã†â€™Ã‚ÂªÃƒÂ¡Ã†â€™Ã‹Å“ÃƒÂ¡Ã†â€™Ã¯Â¿Â½");
			return false;
		}
		return true;
		/*
		 * KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext)
		 * request.getAttribute(KeycloakSecurityContext.class.getName());
		 * AuthorizationContext authzContext =
		 * keycloakSecurityContext.getAuthorizationContext(); if(authzContext == null) {
		 * response.sendRedirect("https://accounts.pol.ge/auth"); return false; } return
		 * true;
		 */
	}
}
