/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.proyectos;

import com.controllerEmail.EnviarCorreosMasivos.controller;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.dao.DificultadFacadeLocal;
import org.dao.EstadoFacadeLocal;
import org.dao.NotificacionFacadeLocal;
import org.dao.PedidoFacadeLocal;
import org.dao.PersonaFacadeLocal;
import org.dao.ProyectoFacadeLocal;
import org.entidades.Dificultad;
import org.entidades.Estado;
import org.entidades.Notificacion;
import org.entidades.Persona;
import org.entidades.Proyecto;
import org.entidades.Rol;
import org.login.ControladorSesion;

/**
 *
 * @author David
 */
@Named(value = "listarProyectos")
@ConversationScoped
public class ListarProyectos implements Serializable {

    @EJB
    private ProyectoFacadeLocal pfl;
    @EJB
    private DificultadFacadeLocal dificultadFacadeLocal;
    @EJB
    private EstadoFacadeLocal estadoFacadeLocal;
    @EJB
    private PedidoFacadeLocal pedidoFacadeLocal;
    @EJB
    private NotificacionFacadeLocal notificacionFacadeLocal;
    @Inject
    private Conversation conversacion;
    @Inject
    private ControladorSesion controladorSesion;
    private Proyecto proyectoSeleccionado;
    //datos temporales
    private Estado estado;
    private Dificultad dificultad;
    private List<Proyecto> proyecto;
    private List<Estado> listaEstados;
    private List<Dificultad> listaDificultades;
    private List<Persona> listaOperarios;
    private Date currentDate;
    private controller c;
    private Notificacion notificacion;

    public ListarProyectos() {

    }

    @PostConstruct
    public void init() {
        listaEstados = estadoFacadeLocal.findAll();
        listaDificultades = dificultadFacadeLocal.findAll();
        listaOperarios = pedidoFacadeLocal.buscarOperarios();
        currentDate = new Date();
        c = new controller();

        for (Rol rol : controladorSesion.getP().getRoles()) {
            if (rol.getIdRol() == 1 || rol.getIdRol() == 2) {
                proyecto = pfl.findAll();

            } else if (rol.getIdRol() == 4 || rol.getIdRol() == 5) {
                proyecto = pfl.listarProyectosOperariosAsignados(controladorSesion.getP());

            }

        }

    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Dificultad> getListaDificultades() {
        return listaDificultades;
    }

    public void setListaDificultades(List<Dificultad> listaDificultades) {
        this.listaDificultades = listaDificultades;
    }

    public ControladorSesion getControladorSesion() {
        return controladorSesion;
    }

    public void setControladorSesion(ControladorSesion controladorSesion) {
        this.controladorSesion = controladorSesion;
    }

    public List<Proyecto> getProyecto() {
        return proyecto;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public List<Persona> getListaOperarios() {
        return listaOperarios;
    }

    private void iniciarConversacion() {
        if (conversacion.isTransient()) {
            conversacion.begin();

        }

    }

    private void terminarConversacion() {
        if (!conversacion.isTransient()) {
            conversacion.end();

        }

    }

    public String cancelar() {
        terminarConversacion();
        return "/admin/proyectos/listarProyectos.xhtml?faces-redirect=true";

    }

    public String preparacionEditar(Proyecto p) {
        iniciarConversacion();
        listaDificultades = dificultadFacadeLocal.findAll();
        listaEstados = estadoFacadeLocal.findAll();
        proyectoSeleccionado = p;
        return "/admin/proyectos/editarProyecto.xhtml?faces-redirect=true";

    }

    public String iniciarProyecto() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String asuntoNotificacionVendedor = "Movimiento proyecto";
        String mensajeNotificacionVendedor = "El proyecto " + proyectoSeleccionado.getPedidosIdPedido().getNombreProyecto() + " ha iniciado";

        try {
            DateFormat readFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");

            String mensaje = "<h2>Estimado cliente " + proyectoSeleccionado.getPedidosIdPedido().getNombreCliente() + "<h2/>"
                    + "<h3><br/>Nos permitimos informar que su proyecto acaba de iniciar, "
                    + "el cual sale de la linea de produccion el dia " + readFormat.format(proyectoSeleccionado.getTiempoEstimado()) + ""
                    + ". Una vez el operario finalice su labor le notificara via email. gracias.<br/><h3/>"
                    + "<h4>resumen del proyecto: <br/>"
                    + "nombre pedido = " + proyectoSeleccionado.getPedidosIdPedido().getNombreProyecto() + "<br/>"
                    + "descripcion pedido = " + proyectoSeleccionado.getPedidosIdPedido().getNombreProyecto() + "<br/>"
                    + "cantidad pedido = " + proyectoSeleccionado.getPedidosIdPedido().getCantidad() + "<br/>"
                    + "nombre cliente = " + proyectoSeleccionado.getPedidosIdPedido().getNombreCliente() + "<br/>"
                    + "telefono cliente = " + proyectoSeleccionado.getPedidosIdPedido().getTelefonoCliente() + "<br/>"
                    + "correo cliente = " + proyectoSeleccionado.getPedidosIdPedido().getCorreoCliente() + "<br/>"
                    + "materia prima = " + proyectoSeleccionado.getPedidosIdPedido().getMateriasPrimaIdMateria().getReferencia() + "<br/><h4/>"
                    + "<h5>Este correo es de carácter informativo, por favor no responder<br/>"
                    + "Fixedup " + year + "<h5/>";

            c.enviarEmailCliente(proyectoSeleccionado.getPedidosIdPedido().getCorreoCliente(), "Notificacion inicio proyecto", mensaje);

            Calendar cal = Calendar.getInstance();
            proyectoSeleccionado.setFechaInicio(cal.getTime());
            estado = new Estado(1);
            proyectoSeleccionado.setEstadosIdEstado(estado);
            pfl.edit(proyectoSeleccionado);
            generarNotificacion(asuntoNotificacionVendedor, mensajeNotificacionVendedor, proyectoSeleccionado.getPedidosIdPedido().getVendedorIdPersona());
            return cancelar();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public String finalizarProyecto(Proyecto p) {
        proyectoSeleccionado = p;
        String asuntoNotificacionVendedor = "Finalizacion proyecto";
        String mensajeNotificacionVendedor = "El proyecto " + proyectoSeleccionado.getPedidosIdPedido().getNombreProyecto() + " ha finalizado";

        try {
            String mensaje = "Estimado cliente " + proyectoSeleccionado.getPedidosIdPedido().getNombreCliente() + "<br/><br/>Nos permitimos informarle que su proyecto acaba de finalizar. Puede acercarce a nuestra bodega para hacer la entrega del proyecto. gracias.<br/><br/>";
            mensaje += "resumen del proyecto: <br/><br/>"
                    + "nombre pedido = " + proyectoSeleccionado.getPedidosIdPedido().getNombreProyecto() + "<br/>"
                    + "descripcion pedido = " + proyectoSeleccionado.getPedidosIdPedido().getNombreProyecto() + "<br/>"
                    + "cantidad pedido = " + proyectoSeleccionado.getPedidosIdPedido().getCantidad() + "<br/>"
                    + "nombre cliente = " + proyectoSeleccionado.getPedidosIdPedido().getNombreCliente() + "<br/>"
                    + "telefono cliente = " + proyectoSeleccionado.getPedidosIdPedido().getTelefonoCliente() + "<br/>"
                    + "correo cliente = " + proyectoSeleccionado.getPedidosIdPedido().getCorreoCliente() + "<br/>"
                    + "materia prima = " + proyectoSeleccionado.getPedidosIdPedido().getMateriasPrimaIdMateria().getReferencia() + "<br/>";
            c.enviarEmailCliente(proyectoSeleccionado.getPedidosIdPedido().getCorreoCliente(), "Notificacion finalizacion proyecto", mensaje);
            Calendar cal = Calendar.getInstance();
            proyectoSeleccionado = p;
            proyectoSeleccionado.setFechaFinalizado(cal.getTime());
            estado = new Estado(3);
            proyectoSeleccionado.setEstadosIdEstado(estado);
            pfl.edit(proyectoSeleccionado);
            generarNotificacion(asuntoNotificacionVendedor, mensajeNotificacionVendedor, proyectoSeleccionado.getPedidosIdPedido().getVendedorIdPersona());
            return cancelar();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public String actualizarProyecto() {
        Calendar cal = Calendar.getInstance();
        proyectoSeleccionado.setFechaInicio(cal.getTime());
        pfl.edit(proyectoSeleccionado);
        return cancelar();

    }

    public void eliminarProyecto() {
        try {
            System.out.println("entro a eliminar");
            pfl.remove(proyectoSeleccionado);
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "proyecto eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al eliminar el usuario", "intentelo de nuevo");
            FacesContext.getCurrentInstance().addMessage(null, msj);

        }

    }

    public void generarNotificacion(String asunto, String mensaje, Persona persona) {
        Date date = new Date();
        notificacion = new Notificacion(null, asunto, mensaje, date, 0, persona);
        try {
            notificacionFacadeLocal.create(notificacion);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error al generar la notificacion, por favor contacte al admin", "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }

    }

}
