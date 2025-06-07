package m2i.example.digitalskills.interceptor;

import m2i.example.digitalskills.service.ClientTempService;
import m2i.example.digitalskills.model.ClientTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

@Component
public class ClientTempInterceptor implements HandlerInterceptor {

    @Autowired
    private ClientTempService clientTempService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // Ignorer les endpoints d'API et les ressources statiques
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api/") ||
                requestURI.startsWith("/static/") ||
                requestURI.startsWith("/css/") ||
                requestURI.startsWith("/js/") ||
                requestURI.startsWith("/images/")) {
            return true;
        }

        // Vérifier si c'est une première visite
        String clientId = getClientIdFromRequest(request);

        if (clientId == null || !clientTempService.existsByClientId(clientId)) {
            // Créer automatiquement un client temporaire
            ClientTemp newClient = clientTempService.createTempClient();

            // Ajouter l'ID client dans les headers ET cookies
            response.setHeader("X-Client-ID", newClient.getClientId());

            // Créer un cookie qui expire dans 30 jours
            Cookie clientCookie = new Cookie("tempClientId", newClient.getClientId());
            clientCookie.setMaxAge(30 * 24 * 60 * 60); // 30 jours en secondes
            clientCookie.setPath("/");
            clientCookie.setHttpOnly(false); // Accessible en JavaScript
            response.addCookie(clientCookie);

            // Optionnel : Log pour debugging
            System.out.println("Nouveau client temporaire créé: " + newClient.getClientId());
        }

        return true;
    }

    /**
     * Récupère l'ID client depuis les headers, cookies ou paramètres
     */
    private String getClientIdFromRequest(HttpServletRequest request) {
        // 1. Vérifier dans les headers
        String clientId = request.getHeader("X-Client-ID");
        if (clientId != null) {
            return clientId;
        }

        // 2. Vérifier dans les cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("tempClientId".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        // 3. Vérifier dans les paramètres de requête
        clientId = request.getParameter("clientId");
        if (clientId != null) {
            return clientId;
        }

        return null;
    }
}