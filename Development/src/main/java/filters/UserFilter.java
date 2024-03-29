package filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // pagina a cui si vuole accedere
        String resource = httpRequest.getServletPath();

        // tipologia di utente salvata nella sessione,
        // se non presente sessione o l'attributo giusto attributo in essa,
        // viene settato il tipo 'none'
        String tipo = (session == null || session.getAttribute("tipo") == null) ? "none" : (String) session.getAttribute("tipo");

        // controlla a che pagina si vuole accedere e se si hanno i permessi per farlo
        // in base alle variabili definite sopra
        if (tipo.compareTo("none") == 0
                || ((resource.compareTo("/Amministratore") == 0 || resource.compareTo("/Amministratore.jsp") == 0) && tipo.compareTo("admin") != 0)
                || ((resource.compareTo("/Aderente") == 0 || resource.compareTo("/Aderente.jsp") == 0) && tipo.compareTo("aderente") != 0)
                || ((resource.compareTo("/Simpatizzante") == 0 || resource.compareTo("/Simpatizzante.jsp") == 0) && tipo.compareTo("simpatizzante") != 0))
            httpResponse.sendRedirect((httpResponse.encodeRedirectURL(httpRequest.getContextPath()+"/notAuthorized")));

        chain.doFilter(request, response);
    }
}