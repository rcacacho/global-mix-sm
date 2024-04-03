package global.mix.sm.web.pedidos;

import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.DespachosBeanLocal;
import global.mix.sm.api.ejb.MaterialBeanLocal;
import global.mix.sm.api.ejb.PedidosBeanLocal;
import global.mix.sm.api.entity.Camion;
import global.mix.sm.api.entity.Despachos;
import global.mix.sm.api.entity.Detallematerial;
import global.mix.sm.api.entity.Detallepedido;
import global.mix.sm.api.entity.Detallepedidonormal;
import global.mix.sm.api.entity.Estadodespacho;
import global.mix.sm.api.entity.Estadopedido;
import global.mix.sm.api.entity.Material;
import global.mix.sm.api.entity.Pedidos;
import global.mix.sm.api.entity.Secuenciapedido;
import global.mix.sm.api.enums.EstadoDespacho;
import global.mix.sm.api.enums.EstadoPedido;
import global.mix.sm.api.enums.MaterialesPedido;
import global.mix.sm.api.enums.TipoPago;
import global.mix.sm.web.utils.JasperUtil;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.ReporteJasper;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "registroPedidoMB")
@ViewScoped
public class RegistroPedidoMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroPedidoMB.class);

    @EJB
    private PedidosBeanLocal pedidoBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;
    @EJB
    private MaterialBeanLocal materialBean;
    @EJB
    private DespachosBeanLocal despachoBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private Integer idPedido;
    private Pedidos pedido;
    private Camion idCamion;
    private Camion idCamionPer;
    private List<Camion> listCamion;
    private List<Material> listPiedrin;
    private List<Material> listAditivo;
    private Material idPiedrin;
    private Material idAditivo;
    private Material idPiedrinPer;
    private Material idAditivoPer;
    private Detallepedidonormal detallePedidoNormal;
    private Detallepedidonormal detallePedidoNormalPer;
    private List<Detallepedidonormal> listDetallePedido;
    private List<Detallepedidonormal> listDetallePedidoFilter;
    private Double cantidadAditivo;
    private Double cantidadCemento;
    private Double cantidadArena;
    private Double cantidadPiedrin;
    private Double cantidadAgua;
    private Double cantidadAditivoPer;
    private Double cantidadCementoPer;
    private Double cantidadArenaPer;
    private Double cantidadPiedrinPer;
    private Double cantidadAguaPer;
    private Integer horasDiesel;
    private Double cantidadDespachada;
    private Double cantidadDespachadaPer;
    private List<Integer> listAd;
    private List<Integer> listPied;
    private Detallepedido detalleOtro;
    private List<Detallepedido> listDetalleOtro;
    private List<Material> listOtro;

    public RegistroPedidoMB() {
        listAd = new ArrayList<>();
        listPied = new ArrayList<>();
        detalleOtro = new Detallepedido();
        listDetalleOtro = new ArrayList<>();
    }

    @PostConstruct
    void init() {
        List<Camion> list = catalogoBean.listCamiones();
        if (list != null) {
            listCamion = list;
        } else {
            listCamion = new ArrayList<>();
        }

        List<Material> listP = catalogoBean.listPiedrin();
        if (listP != null) {
            listPiedrin = listP;
        } else {
            listPiedrin = new ArrayList<>();
        }

        List<Material> listA = catalogoBean.listAditivo();
        if (listA != null) {
            listAditivo = listA;
        } else {
            listAditivo = new ArrayList<>();
        }

        List<Material> listO = catalogoBean.listMaterial();
        if (listO != null) {
            listOtro = listO;
        } else {
            listOtro = new ArrayList<>();
        }
    }

    public void cargarDatos() {
        Pedidos response = pedidoBean.findPedidoById(idPedido);
        if (response != null) {
            pedido = response;
        } else {
            JsfUtil.addErrorMessage("No se encontro información");
        }

        List<Detallepedidonormal> responseDet = pedidoBean.listDetallePedidoNormalByIdPedido(idPedido);
        if (responseDet != null) {
            listDetallePedido = responseDet;
        } else {
            listDetallePedido = new ArrayList<>();
        }
    }

    public void limpiarCampos() {
        cantidadAditivo = 0.0;
        cantidadAgua = 0.0;
        cantidadArena = 0.0;
        cantidadCemento = 0.0;
        cantidadPiedrin = 0.0;
        horasDiesel = null;
        idAditivo = null;
        idAditivo = new Material();
        idCamion = null;
        idCamion = new Camion();
        idPiedrin = null;
        idPiedrin = new Material();
        cantidadDespachada = 0.0;
    }

    public void limpiarPersonalizadoCampos() {
        cantidadAditivoPer = 0.0;
        cantidadAguaPer = 0.0;
        cantidadArenaPer = 0.0;
        cantidadCementoPer = 0.0;
        cantidadPiedrinPer = 0.0;
        horasDiesel = null;
        idAditivoPer = null;
        idAditivoPer = new Material();
        idCamionPer = null;
        idCamionPer = new Camion();
        idPiedrinPer = null;
        idPiedrinPer = new Material();
        cantidadDespachadaPer = 0.0;
        detalleOtro = null;
        listDetalleOtro = null;
        listDetalleOtro = new ArrayList<>();
    }

    public void registroPedido() throws IOException {
        detallePedidoNormal = null;
        detallePedidoNormal = new Detallepedidonormal();

        if (cantidadAditivo == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de aditivo");
            return;
        }

        if (cantidadAgua == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de agua");
            return;
        }

        if (cantidadArena == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de arena");
            return;
        }

        if (cantidadCemento == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de cemento");
            return;
        }

        if (cantidadPiedrin == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de piedrín");
            return;
        }

        if (cantidadDespachada == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad despachada");
            return;
        }

        detallePedidoNormal.setCantidadaditivo(cantidadAditivo);
        detallePedidoNormal.setCantidadagua(cantidadAgua);
        detallePedidoNormal.setCantidadarena(cantidadArena);
        detallePedidoNormal.setCantidadcemento(cantidadCemento);
        detallePedidoNormal.setCantidadpiedrin(cantidadPiedrin);
        detallePedidoNormal.setIdaditivo(idAditivo.getIdmaterial());
        detallePedidoNormal.setIdagua(MaterialesPedido.AGUA.getValue());
        detallePedidoNormal.setIdarena(MaterialesPedido.ARENA.getValue());
        detallePedidoNormal.setIdcamion(idCamion);
        detallePedidoNormal.setIdcemento(MaterialesPedido.CEMENTO.getValue());
        detallePedidoNormal.setIdpedido(pedido);
        detallePedidoNormal.setIdpiedrin(idPiedrin.getIdmaterial());
        detallePedidoNormal.setCantidaddespachada(cantidadDespachada);

        Detallepedidonormal responseDet = pedidoBean.saveDetallePedidoNormal(detallePedidoNormal, SesionUsuarioMB.getUserName());
        limpiarCampos();
    }

    public void registroPedidoPersonalizado() throws IOException {
        detallePedidoNormalPer = null;
        detallePedidoNormalPer = new Detallepedidonormal();

        if (cantidadAditivoPer == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de aditivo");
            return;
        }

        if (cantidadAguaPer == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de agua");
            return;
        }

        if (cantidadArenaPer == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de arena");
            return;
        }

        if (cantidadCementoPer == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de cemento");
            return;
        }

        if (cantidadPiedrinPer == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de piedrín");
            return;
        }

        if (cantidadDespachadaPer == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad despachada");
            return;
        }

        detallePedidoNormalPer.setCantidadaditivo(cantidadAditivoPer);
        detallePedidoNormalPer.setCantidadagua(cantidadAguaPer);
        detallePedidoNormalPer.setCantidadarena(cantidadArenaPer);
        detallePedidoNormalPer.setCantidadcemento(cantidadCementoPer);
        detallePedidoNormalPer.setCantidadpiedrin(cantidadPiedrinPer);
        detallePedidoNormalPer.setIdaditivo(idAditivo.getIdmaterial());
        detallePedidoNormalPer.setIdagua(MaterialesPedido.AGUA.getValue());
        detallePedidoNormalPer.setIdarena(MaterialesPedido.ARENA.getValue());
        detallePedidoNormalPer.setIdcamion(idCamion);
        detallePedidoNormalPer.setIdcemento(MaterialesPedido.CEMENTO.getValue());
        detallePedidoNormalPer.setIdpedido(pedido);
        detallePedidoNormalPer.setIdpiedrin(idPiedrin.getIdmaterial());
        detallePedidoNormalPer.setCantidaddespachada(cantidadDespachadaPer);

        if (!listDetalleOtro.isEmpty() || listDetalleOtro != null) {
            detallePedidoNormalPer.setCantidadotromaterial(getTotalOtroMaterial());
        }

        Detallepedidonormal responseDet = pedidoBean.saveDetallePedidoNormal(detallePedidoNormalPer, SesionUsuarioMB.getUserName());
        if (responseDet != null) {
            if (!listDetalleOtro.isEmpty() || listDetalleOtro != null) {
                for (Detallepedido dd : listDetalleOtro) {
                    dd.setIddetallepedidonormal(responseDet);
                    Detallepedido saveDetalle = pedidoBean.saveDetallePedido(dd, SesionUsuarioMB.getRolUsuario());
                }
            }
        }
        limpiarPersonalizadoCampos();
    }

    public void onRowEditDetallePedido(RowEditEvent event) throws IOException {
        Detallepedidonormal objeto = ((Detallepedidonormal) event.getObject());
        Detallepedidonormal response = pedidoBean.updateDetallePedidoNormal(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Material actualizado correctamente");
        }
    }

    public int getTotalCemento() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadcemento() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadcemento();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalArena() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadarena() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadarena();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalPiedrin() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadpiedrin() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadpiedrin();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalAditivo() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadaditivo() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadaditivo();
                    }
                }
            }
        }
        return total;
    }

    public int getTotalAgua() {
        int total = 0;

        if (listDetallePedido != null) {
            for (Detallepedidonormal det : listDetallePedido) {
                if (det.getCantidadagua() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidadagua();
                    }
                }
            }
        }
        return total;
    }

    public Double getTotalOtroMaterial() {
        Double total = null;

        if (listDetallePedido != null) {
            for (Detallepedido det : listDetalleOtro) {
                if (det.getCantidad() != null) {
                    if (det.getActivo() == true) {
                        total += det.getCantidad();
                    }
                }
            }
        }
        return total;
    }

    public void enviarDespacho() throws IOException {
        if (pedido.getIdtipopago().getIdtipopago().equals(TipoPago.CREDITO.getValue()) || pedido.getIdtipopago().getIdtipopago().equals(TipoPago.MIXTO.getValue())) {
            if (!pedido.getConfirmado()) {
                JsfUtil.addErrorMessage("Debe confirmar el credito antes de continuar");
                return;
            }
        }

        if (listDetallePedido == null || listDetallePedido.isEmpty()) {
            JsfUtil.addErrorMessage("Debe registrar al menos un material");
            return;
        }

        for (Detallepedidonormal det : listDetallePedido) {
            if (det.getCantidadaditivo() != null) {
                Material matAditvio = catalogoBean.findMaterialById(det.getIdaditivo());
                matAditvio.setExistenciainicial(matAditvio.getExistenciainicial() - det.getCantidadaditivo());
                if (matAditvio.getExistenciainicial() <= 0) {
                    Double resto = det.getCantidadaditivo() - matAditvio.getExistenciainicial();
                    matAditvio.setExistenciainicial(0.0);
                    Material responseUpdate = materialBean.updateMaterial(matAditvio, SesionUsuarioMB.getUserName());

                    Detallematerial detalle = new Detallematerial();
                    detalle.setExistenciaActual(matAditvio.getExistenciainicial());
                    detalle.setIdmaterial(matAditvio);
                    detalle.setEgreso(det.getCantidadaditivo());
                    detalle.setTotal(matAditvio.getExistenciainicial() * matAditvio.getValorneto());
                    Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());

                    Material matAditvioOtro = catalogoBean.findMaterialExistenciaMayorCeroById(det.getIdaditivo());
                    matAditvioOtro.setExistenciainicial(resto);
                    Material responseUpdateOtro = materialBean.updateMaterial(matAditvioOtro, SesionUsuarioMB.getUserName());

                } else {
                    Material responseUpdate = materialBean.updateMaterial(matAditvio, SesionUsuarioMB.getUserName());

                    Detallematerial detalle = new Detallematerial();
                    detalle.setExistenciaActual(matAditvio.getExistenciainicial());
                    detalle.setIdmaterial(matAditvio);
                    detalle.setEgreso(det.getCantidadaditivo());
                    detalle.setTotal(matAditvio.getExistenciainicial() * matAditvio.getValorneto());
                    Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());
                }
            }

            if (det.getCantidadagua() != null) {
                Material matAgua = catalogoBean.findMaterialById(det.getIdagua());
                matAgua.setExistenciainicial(matAgua.getExistenciainicial() - det.getCantidadagua());

                Material responseUpdate = materialBean.updateMaterial(matAgua, SesionUsuarioMB.getUserName());

                Detallematerial detalle = new Detallematerial();
                detalle.setExistenciaActual(matAgua.getExistenciainicial());
                detalle.setIdmaterial(matAgua);
                detalle.setEgreso(det.getCantidadagua());
                detalle.setTotal(matAgua.getExistenciainicial() * matAgua.getValorneto());
                Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());
            }

            if (det.getCantidadarena() != null) {
                Material matAren = catalogoBean.findMaterialById(det.getIdarena());
                matAren.setExistenciainicial(matAren.getExistenciainicial() - det.getCantidadarena());

                Material responseUpdate = materialBean.updateMaterial(matAren, SesionUsuarioMB.getUserName());

                Detallematerial detalle = new Detallematerial();
                detalle.setExistenciaActual(matAren.getExistenciainicial());
                detalle.setIdmaterial(matAren);
                detalle.setEgreso(det.getCantidadarena());
                detalle.setTotal(matAren.getExistenciainicial() * matAren.getValorneto());
                Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());
            }

            if (det.getCantidadcemento() != null) {
                Material matCem = catalogoBean.findMaterialById(det.getIdarena());
                matCem.setExistenciainicial(matCem.getExistenciainicial() - det.getCantidadcemento());

                Material responseUpdate = materialBean.updateMaterial(matCem, SesionUsuarioMB.getUserName());

                Detallematerial detalle = new Detallematerial();
                detalle.setExistenciaActual(matCem.getExistenciainicial());
                detalle.setIdmaterial(matCem);
                detalle.setEgreso(det.getCantidadcemento());
                detalle.setTotal(matCem.getExistenciainicial() * matCem.getValorneto());
                Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());
            }

            if (det.getCantidadpiedrin() != null) {
                Material matPie = catalogoBean.findMaterialById(det.getIdarena());
                matPie.setExistenciainicial(matPie.getExistenciainicial() - det.getCantidadpiedrin());

                Material responseUpdate = materialBean.updateMaterial(matPie, SesionUsuarioMB.getUserName());

                Detallematerial detalle = new Detallematerial();
                detalle.setExistenciaActual(matPie.getExistenciainicial());
                detalle.setIdmaterial(matPie);
                detalle.setEgreso(det.getCantidadpiedrin());
                detalle.setTotal(matPie.getExistenciainicial() * matPie.getValorneto());
                Detallematerial responseDetalle = materialBean.saveDetalleMaterial(detalle, SesionUsuarioMB.getUserName());
            }

            if (det.getIdpiedrin() != null) {
                List<Integer> list = new ArrayList<>();
                if (listPied.isEmpty()) {
                    listPied.add(det.getIdpiedrin());
                } else {
                    for (Integer pied : listPied) {
                        if (!pied.equals(det.getIdpiedrin())) {
                            list.add(det.getIdpiedrin());
                        }
                    }
                    if (!list.isEmpty()) {
                        listPied.addAll(list);
                    }
                }
            }

            if (det.getIdaditivo() != null) {
                List<Integer> list = new ArrayList<>();
                if (listAd.isEmpty()) {
                    listAd.add(det.getIdaditivo());
                } else {
                    for (Integer adit : listAd) {
                        if (!adit.equals(det.getIdaditivo())) {
                            list.add(det.getIdaditivo());
                        }
                    }
                    if (!list.isEmpty()) {
                        listAd.addAll(list);
                    }
                }
            }
        }

        Estadopedido responseEstado = catalogoBean.findEstadoPedido(EstadoPedido.FINALIZADO.getValue());
        pedido.setIdestadopedido(responseEstado);
        Pedidos response = pedidoBean.updatePedido(pedido, SesionUsuarioMB.getUserName());

        Despachos despacho = new Despachos();

        Double totalAgua = pedidoBean.sumCantidadAgua(idPedido);
        Material ma = catalogoBean.findMaterialById(MaterialesPedido.AGUA.getValue());

        Double totalArena = pedidoBean.sumCantidadArena(idPedido);
        Material are = catalogoBean.findMaterialById(MaterialesPedido.ARENA.getValue());

        Double totalCemento = pedidoBean.sumCantidadCemento(idPedido);
        Material cem = catalogoBean.findMaterialById(MaterialesPedido.CEMENTO.getValue());

        Double totalPiedrin = pedidoBean.sumCantidadPiedrin(idPedido);
        Double costoPiedrin = 0.0;

        Double totalAditivo = pedidoBean.sumCantidadAditivo(idPedido);
        Double costoAditivo = 0.0;

        for (Integer id : listPied) {
            Material mt = materialBean.findMaterialById(id);
            if (mt.getCosto() != null) {
                costoPiedrin += mt.getCosto();
            }
        }

        for (Integer idA : listAd) {
            Material mt = materialBean.findMaterialById(idA);
            if (mt.getCosto() != null) {
                costoAditivo += mt.getCosto();
            }
        }

        Estadodespacho responseEstDesp = catalogoBean.findEstadoDespacho(EstadoDespacho.EN_PROCESO.getValue());
        despacho.setMetroscubicossolicitados(pedido.getVolumen());
        despacho.setCostotalagua(totalAgua);
        despacho.setCostototalaguadinero(ma.getCosto() * despacho.getCostotalagua());
        despacho.setCostototaladitivo(totalAditivo);
        despacho.setCostototaladitivodinero(costoAditivo * despacho.getCostototaladitivo());
        despacho.setCostototalarena(totalArena);
        despacho.setCostototalarenadinero(are.getCosto() * despacho.getCostototalarena());
        despacho.setCostototalcemento(totalCemento);
        despacho.setCostototaldinerocemento(cem.getCosto() * despacho.getCostototalcemento());
        despacho.setCostototalpiedrin(totalPiedrin);
        despacho.setCostototalpiedrindinero(costoPiedrin * despacho.getCostototalpiedrin());
        despacho.setIdpedido(pedido);
        despacho.setIdestadodespacho(responseEstDesp);

        Despachos responseDespacho = despachoBean.saveDespacho(despacho, SesionUsuarioMB.getUserName());
        if (response != null) {
            JsfUtil.addSuccessMessage("Se envio exitosamente al despacho");
        }
    }

    public StreamedContent generReporte() {
        Despachos des = despachoBean.findDespachoByIdPedido(idPedido);
        if (des == null) {
            JsfUtil.addErrorMessage("Debe enviar el pedido al despacho para generar el reporte");
            return null;
        }

        String nombreReporte;
        String nombreArchivo;
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            //crearImage();
            HashMap parametros = new HashMap();
            nombreReporte = "rptPedidos";
            nombreArchivo = "Pedidos";

            parametros.put("IMAGE", "logo.jpeg");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("idPedido", idPedido);

            parametros.put("metroCubicosEncargado", pedido.getVolumen());
            parametros.put("cliente", pedido.getIdcliente().getNombres() + " " + pedido.getIdcliente().getApellidos());
            if (pedido.getIdtipocemento() != null) {
                parametros.put("material", pedido.getIdtipocemento().getDescripcion() + " " + pedido.getExtraconcreto());
            }
            parametros.put("metroCubicosVendidos", idPedido);
            parametros.put("ubicacion", pedido.getObra());
            parametros.put("tipoObra", pedido.getElemento());
            parametros.put("estadoPago", pedido.getIdestadopedido().getEstado());
            parametros.put("asesor", pedido.getIdasesor().getNombres() + " " + pedido.getIdasesor().getApellidos());

            if (pedido.getBombeo().equals("Si")) {
                parametros.put("bombeo", "Con bombeo");
            } else {
                parametros.put("bombeo", "Sin bombeo");
            }

            if (pedido.getColocado().equals("Si")) {
                parametros.put("colocado", "Con colocado");
            } else {
                parametros.put("colocado", "Sin colocado");
            }
            parametros.put("fecha", pedido.getFechacreacion());
            parametros.put("totalCemento", des.getCostototalcemento());
            parametros.put("totalArena", des.getCostototalarena());
            parametros.put("totalPiedrin", des.getCostototalpiedrin());
            parametros.put("totalAditivo", des.getCostototaladitivo());
            parametros.put("totalAgua", des.getCostotalagua());
            parametros.put("totalOtros", des.getCostototalotro());
            parametros.put("idPedido", idPedido);

            ReporteJasper reporteJasper = JasperUtil.jasperReportPDF(nombreReporte, nombreArchivo, parametros, dataSource);
            StreamedContent streamedContent;
            FileInputStream stream = new FileInputStream(realPath + "resources/reports/" + reporteJasper.getFileName());
            streamedContent = new DefaultStreamedContent(stream, "application/pdf", reporteJasper.getFileName());
            return streamedContent;

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Ocurrio un error al generar el pdf del reporte");
        }
        return null;
    }

    public StreamedContent generReporteRemision(Detallepedidonormal detalle) throws IOException {
        String secuencia = null;
        Secuenciapedido responseSecuencia = pedidoBean.findSecuenciaPedidoByIdDetalle(detalle.getIddetallepedidonormal());
        if (responseSecuencia == null) {
            Integer sec = 1;
            Secuenciapedido secuenciaPedido = new Secuenciapedido();
            LocalDate fechaHoy = LocalDate.now();
            Integer anio = fechaHoy.getYear();

            Secuenciapedido responseUltimo = pedidoBean.findUltimaSecuencia();
            if (responseUltimo != null) {
                sec += responseUltimo.getSecuencia();
            }

            secuencia = anio.toString() + '-' + sec;
            secuenciaPedido.setCorrelativo(secuencia);
            secuenciaPedido.setIddetallepedidonormal(detalle);
            secuenciaPedido.setSecuencia(sec);
            Secuenciapedido responseSaveSec = pedidoBean.saveSecuenciaPedido(secuenciaPedido, SesionUsuarioMB.getUserName());

        } else {
            secuencia = responseSecuencia.getCorrelativo();
        }

        String nombreReporte;
        String nombreArchivo;
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            //crearImage();
            HashMap parametros = new HashMap();
            nombreReporte = "rptRemision";
            nombreArchivo = "Remision";

            parametros.put("IMAGE", "logo.jpeg");
            parametros.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);
            parametros.put("secuencia", secuencia);
            parametros.put("cliente", detalle.getIdpedido().getIdcliente().getNombres() + " " + detalle.getIdpedido().getIdcliente().getApellidos());
            parametros.put("obra", detalle.getIdpedido().getObra());
            parametros.put("faltan", detalle.getIdpedido().getVolumen() - detalle.getCantidaddespachada());
            parametros.put("uso", detalle.getIdpedido().getElemento());
            parametros.put("cantidaDespachada", detalle.getCantidaddespachada());
            parametros.put("camion", detalle.getIdcamion().getNumero());
            parametros.put("asesor", detalle.getIdpedido().getIdasesor().getNombres() + " " + detalle.getIdpedido().getIdasesor().getApellidos());
            parametros.put("piloto", detalle.getIdcamion().getEncargado());
            parametros.put("fraguado", detalle.getIdpedido().getFraguado());
            parametros.put("despacho", detalle.getCantidaddespachada());

            if (detalle.getIdpedido().getIdtipocemento() != null) {
                parametros.put("cemento", detalle.getIdpedido().getIdtipocemento().getDescripcion() + " " + detalle.getIdpedido().getExtraconcreto());
            }

            if (detalle.getIdpedido().getBombeo().equals("Si")) {
                parametros.put("bombeo", "Con bombeo");
            } else {
                parametros.put("bombeo", "Sin bombeo");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaComoCadena = sdf.format(detalle.getIdpedido().getFechapedido());
            System.out.println(fechaComoCadena);

            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            String hora = sdf2.format(detalle.getIdpedido().getFechapedido());
            System.out.println(hora);

            parametros.put("horaPedido", hora);
            parametros.put("fechaPedido", fechaComoCadena);

            ReporteJasper reporteJasper = JasperUtil.jasperReportPDF(nombreReporte, nombreArchivo + secuencia, parametros, dataSource);
            StreamedContent streamedContent;
            FileInputStream stream = new FileInputStream(realPath + "resources/reports/" + reporteJasper.getFileName());
            streamedContent = new DefaultStreamedContent(stream, "application/pdf", reporteJasper.getFileName());
            return streamedContent;

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Ocurrio un error al generar el pdf del reporte");
        }
        return null;
    }

    public void registrarOtroMaterial() {
        if (detalleOtro.getCantidad() == null) {
            JsfUtil.addErrorMessage("Debe ingresar una cantidad de material");
            return;
        }

        if (detalleOtro.getIdmaterial() == null) {
            JsfUtil.addErrorMessage("Debe seleccionar un material");
            return;
        }

        listDetalleOtro.add(detalleOtro);
        detalleOtro = null;
        detalleOtro = new Detallepedido();
    }

    /*Metodos getters y setters*/
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public List<Camion> getListCamion() {
        return listCamion;
    }

    public void setListCamion(List<Camion> listCamion) {
        this.listCamion = listCamion;
    }

    public List<Material> getListPiedrin() {
        return listPiedrin;
    }

    public void setListPiedrin(List<Material> listPiedrin) {
        this.listPiedrin = listPiedrin;
    }

    public List<Material> getListAditivo() {
        return listAditivo;
    }

    public void setListAditivo(List<Material> listAditivo) {
        this.listAditivo = listAditivo;
    }

    public List<Detallepedidonormal> getListDetallePedido() {
        return listDetallePedido;
    }

    public void setListDetallePedido(List<Detallepedidonormal> listDetallePedido) {
        this.listDetallePedido = listDetallePedido;
    }

    public Material getIdPiedrin() {
        return idPiedrin;
    }

    public void setIdPiedrin(Material idPiedrin) {
        this.idPiedrin = idPiedrin;
    }

    public Material getIdAditivo() {
        return idAditivo;
    }

    public void setIdAditivo(Material idAditivo) {
        this.idAditivo = idAditivo;
    }

    public Double getCantidadAditivo() {
        return cantidadAditivo;
    }

    public void setCantidadAditivo(Double cantidadAditivo) {
        this.cantidadAditivo = cantidadAditivo;
    }

    public Double getCantidadCemento() {
        return cantidadCemento;
    }

    public void setCantidadCemento(Double cantidadCemento) {
        this.cantidadCemento = cantidadCemento;
    }

    public Double getCantidadArena() {
        return cantidadArena;
    }

    public void setCantidadArena(Double cantidadArena) {
        this.cantidadArena = cantidadArena;
    }

    public Double getCantidadPiedrin() {
        return cantidadPiedrin;
    }

    public void setCantidadPiedrin(Double cantidadPiedrin) {
        this.cantidadPiedrin = cantidadPiedrin;
    }

    public Double getCantidadAgua() {
        return cantidadAgua;
    }

    public void setCantidadAgua(Double cantidadAgua) {
        this.cantidadAgua = cantidadAgua;
    }

    public Integer getHorasDiesel() {
        return horasDiesel;
    }

    public void setHorasDiesel(Integer horasDiesel) {
        this.horasDiesel = horasDiesel;
    }

    public Camion getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Camion idCamion) {
        this.idCamion = idCamion;
    }

    public List<Detallepedidonormal> getListDetallePedidoFilter() {
        return listDetallePedidoFilter;
    }

    public void setListDetallePedidoFilter(List<Detallepedidonormal> listDetallePedidoFilter) {
        this.listDetallePedidoFilter = listDetallePedidoFilter;
    }

    public Detallepedidonormal getDetallePedidoNormal() {
        return detallePedidoNormal;
    }

    public void setDetallePedidoNormal(Detallepedidonormal detallePedidoNormal) {
        this.detallePedidoNormal = detallePedidoNormal;
    }

    public Double getCantidadDespachada() {
        return cantidadDespachada;
    }

    public void setCantidadDespachada(Double cantidadDespachada) {
        this.cantidadDespachada = cantidadDespachada;
    }

    public Double getCantidadAditivoPer() {
        return cantidadAditivoPer;
    }

    public void setCantidadAditivoPer(Double cantidadAditivoPer) {
        this.cantidadAditivoPer = cantidadAditivoPer;
    }

    public Double getCantidadCementoPer() {
        return cantidadCementoPer;
    }

    public void setCantidadCementoPer(Double cantidadCementoPer) {
        this.cantidadCementoPer = cantidadCementoPer;
    }

    public Double getCantidadArenaPer() {
        return cantidadArenaPer;
    }

    public void setCantidadArenaPer(Double cantidadArenaPer) {
        this.cantidadArenaPer = cantidadArenaPer;
    }

    public Double getCantidadPiedrinPer() {
        return cantidadPiedrinPer;
    }

    public void setCantidadPiedrinPer(Double cantidadPiedrinPer) {
        this.cantidadPiedrinPer = cantidadPiedrinPer;
    }

    public Double getCantidadAguaPer() {
        return cantidadAguaPer;
    }

    public void setCantidadAguaPer(Double cantidadAguaPer) {
        this.cantidadAguaPer = cantidadAguaPer;
    }

    public Double getCantidadDespachadaPer() {
        return cantidadDespachadaPer;
    }

    public void setCantidadDespachadaPer(Double cantidadDespachadaPer) {
        this.cantidadDespachadaPer = cantidadDespachadaPer;
    }

    public Camion getIdCamionPer() {
        return idCamionPer;
    }

    public void setIdCamionPer(Camion idCamionPer) {
        this.idCamionPer = idCamionPer;
    }

    public Material getIdPiedrinPer() {
        return idPiedrinPer;
    }

    public void setIdPiedrinPer(Material idPiedrinPer) {
        this.idPiedrinPer = idPiedrinPer;
    }

    public Material getIdAditivoPer() {
        return idAditivoPer;
    }

    public void setIdAditivoPer(Material idAditivoPer) {
        this.idAditivoPer = idAditivoPer;
    }

    public Detallepedidonormal getDetallePedidoNormalPer() {
        return detallePedidoNormalPer;
    }

    public void setDetallePedidoNormalPer(Detallepedidonormal detallePedidoNormalPer) {
        this.detallePedidoNormalPer = detallePedidoNormalPer;
    }

    public Detallepedido getDetalleOtro() {
        return detalleOtro;
    }

    public void setDetalleOtro(Detallepedido detalleOtro) {
        this.detalleOtro = detalleOtro;
    }

    public List<Detallepedido> getListDetalleOtro() {
        return listDetalleOtro;
    }

    public void setListDetalleOtro(List<Detallepedido> listDetalleOtro) {
        this.listDetalleOtro = listDetalleOtro;
    }

    public List<Material> getListOtro() {
        return listOtro;
    }

    public void setListOtro(List<Material> listOtro) {
        this.listOtro = listOtro;
    }

}
