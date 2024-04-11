package global.mix.sm.web.utils;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "sesionUsuarioMB")
@SessionScoped
public class SesionUsuarioMB {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static String getUserName() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("usuario") == null) {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("/index.xhtml");
            return null;
        }
        return session.getAttribute("usuario").toString();
    }

    public static Integer getUserId() {
        HttpSession session = getSession();
        if (session.getAttribute("idusuario") != null) {
            return (Integer) session.getAttribute("idusuario");
        }
        return null;
    }

    public static boolean getRootUsuario() {
        HttpSession session = getSession();
        if (session.getAttribute("administrador") != null) {
            return (boolean) session.getAttribute("administrador");
        }
        return false;
    }

    public static String getRolUsuario() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session.getAttribute("rol") != null) {
            return (String) session.getAttribute("rol");
        }
        return null;
    }

}
