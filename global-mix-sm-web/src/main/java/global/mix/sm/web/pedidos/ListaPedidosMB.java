package global.mix.sm.web.pedidos;

import globa.mix.api.wrapper.WrapperPedidos;
import global.mix.sm.api.ejb.CatalogosBeanLocal;
import global.mix.sm.api.ejb.PedidosBeanLocal;
import global.mix.sm.api.entity.Asesor;
import global.mix.sm.api.entity.Cliente;
import global.mix.sm.api.entity.Confirmacionpago;
import global.mix.sm.api.entity.Estadopedido;
import global.mix.sm.api.entity.Pedidos;
import global.mix.sm.api.enums.EstadoPedido;
import global.mix.sm.web.utils.JasperReportHelper;
import global.mix.sm.web.utils.JasperUtil;
import global.mix.sm.web.utils.JsfUtil;
import global.mix.sm.web.utils.ReportFormat;
import global.mix.sm.web.utils.ReporteJasper;
import global.mix.sm.web.utils.SesionUsuarioMB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.primefaces.component.schedule.Schedule;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "listaPedidosMB")
@ViewScoped
public class ListaPedidosMB implements Serializable {

     private static final Logger log = Logger.getLogger(ListaPedidosMB.class);

    @EJB
    private PedidosBeanLocal pedidoBean;
    @EJB
    private CatalogosBeanLocal catalogoBean;

    @Resource(lookup = "jdbc/globamix")
    private DataSource dataSource;

    private List<Pedidos> listPedidos;
    private List<Pedidos> listPedidoFiltrado;
    private Integer idsesor;
    private Integer idcliente;
    private Integer idestadopedido;
    private String obra;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Asesor> listAsesor;
    private List<Cliente> listCliente;
    private List<Estadopedido> listEstado;
    private Date fechaInicioReporte;
    private Date fechaFinReporte;
    private ScheduleModel schedule;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent pedidoCalendario = new DefaultScheduleEvent();
    private Pedidos pedidoSelected;
    private ScheduleModel lazyModel;
    private String realPath;
    private String dirSeparator;

    public ListaPedidosMB() {
        schedule = new DefaultScheduleModel();
    }

    @PostConstruct
    void cargarDatos() {
        List<Pedidos> list = pedidoBean.listPedido();
        if (list != null) {
            listPedidos = list;
        } else {
            listPedidos = new ArrayList<>();
        }

        List<Asesor> listAs = catalogoBean.listAsesor();
        if (listAs != null) {
            listAsesor = listAs;
        } else {
            listAsesor = new ArrayList<>();
        }

        List<Cliente> listCl = catalogoBean.listCliente();
        if (listCl != null) {
            listCliente = listCl;
        } else {
            listCliente = new ArrayList<>();
        }

        List<Estadopedido> listEst = catalogoBean.listEstadoPedido();
        if (listEst != null) {
            listEstado = listEst;
        } else {
            listEstado = new ArrayList<>();
        }

        fechaInicio = new Date();
        fechaFin = new Date();
        buscarFiltro();
        loadCalendario();
    }

    public void loadData() {

    }

    public void verDetalle(Integer idPedido) {
        JsfUtil.redirectTo("/pedidos/detalle.xhtml?faces-redirect=true&idPedido=" + idPedido);
    }

    public void limpiarCampos() {
        idcliente = null;
        idestadopedido = null;
        idestadopedido = null;
        obra = null;
        fechaFin = null;
        fechaInicio = null;

        cargarDatos();
    }

    public void buscarFiltro() {
        try {
            if (idcliente != null || idestadopedido != null || idsesor != null || obra != null || fechaInicio != null || fechaFin != null) {
                if (fechaInicio != null) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(fechaInicio);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    fechaInicio = c.getTime();
                }

                if (fechaFin != null) {
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(fechaFin);
                    c1.set(Calendar.HOUR_OF_DAY, 23);
                    c1.set(Calendar.MINUTE, 59);
                    c1.set(Calendar.SECOND, 59);
                    fechaFin = c1.getTime();
                }

                List<Pedidos> response = pedidoBean.listPedidoBuzon(idsesor, idcliente, idestadopedido, obra, fechaInicio, fechaFin);
                if (response != null) {
                    listPedidos = response;
                } else {
                    listPedidos = new ArrayList<>();
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void dialogReporte() {
        fechaInicioReporte = null;
        fechaFinReporte = null;
        RequestContext.getCurrentInstance().execute("PF('dlgReporte').show()");
    }

    public void eliminarPedido(Pedidos id) throws IOException {
        Pedidos response = pedidoBean.deletePedido(id, SesionUsuarioMB.getUserName());
        if (response != null) {
            cargarDatos();
            JsfUtil.addSuccessMessage("Se elimino el pedido exitosamente");
            return;
        }
        JsfUtil.addErrorMessage("Sucedio un error al eliminar");
    }

    public void onRowEditPedido(RowEditEvent event) throws IOException {
        Pedidos objeto = ((Pedidos) event.getObject());
        Pedidos response = pedidoBean.updatePedido(objeto, SesionUsuarioMB.getUserName());
        if (response == null) {
            JsfUtil.addErrorMessage("Ocurrio un error al actualizar");
        } else {
            JsfUtil.addSuccessMessage("Pedido actualizado correctamente");
        }
    }

    public String abrirRegistro(Integer idPedido) {
        return "/pedidos/registro.xhtml?faces-redirect=true&idPedido=" + idPedido;
    }

    public StreamedContent imprimirExcel() throws IOException {
        if (fechaInicioReporte == null || fechaFinReporte == null) {
            JsfUtil.addErrorMessage("Debe de indicar las fechas");
            return null;
        }

        StreamedContent content = null;
        List<Pedidos> listaPedidos = pedidoBean.listPedidoByFechaInicio(fechaInicioReporte, fechaFinReporte);

        HashMap<Integer, Fila> mapaFilas = new HashMap<>();
        Workbook workbook = new SXSSFWorkbook(1000);
        Sheet sheet = workbook.createSheet("Pedidos");

        int rownum = 0;
        int headerNum = 0;
        sheet.setColumnWidth(0, 900);
        sheet.setColumnWidth(1, 9000);
        sheet.setColumnWidth(2, 9000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 7000);
        sheet.setColumnWidth(5, 5000);
        sheet.setColumnWidth(6, 5000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 5000);
        sheet.setColumnWidth(9, 5000);
        sheet.setColumnWidth(10, 5000);
        sheet.setColumnWidth(11, 5000);
        sheet.setColumnWidth(12, 11000);
        sheet.setColumnWidth(13, 12000);
        sheet.setColumnWidth(14, 14000);
        sheet.setColumnWidth(15, 2000);
        sheet.setColumnWidth(16, 6000);
        sheet.setColumnWidth(17, 5000);
        sheet.setColumnWidth(18, 4000);
        sheet.setColumnWidth(19, 6000);
        sheet.setColumnWidth(20, 6000);
        sheet.setColumnWidth(21, 7000);
        sheet.setColumnWidth(22, 7000);
        sheet.setColumnWidth(23, 7000);
        sheet.setColumnWidth(24, 19000);

        CellStyle headerStyle = workbook.createCellStyle();
        XSSFColor color = new XSSFColor(new java.awt.Color(255, 250, 250));
        ((XSSFCellStyle) headerStyle).setFillForegroundColor(color);
        headerStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        CellStyle cellStyle = workbook.createCellStyle();
        XSSFColor colorBlanco = new XSSFColor(new java.awt.Color(255, 250, 250));
        ((XSSFCellStyle) cellStyle).setFillForegroundColor(colorBlanco);
        Font font = workbook.createFont();//Create font
        cellStyle.setFont(font);//set it to bold

        CellStyle cellStyleTitulo = workbook.createCellStyle();
        XSSFColor colorBlancoTitulo = new XSSFColor(new java.awt.Color(255, 250, 250));
        ((XSSFCellStyle) cellStyleTitulo).setFillForegroundColor(colorBlancoTitulo);
        Font fontTitulo = workbook.createFont();//Create font
        fontTitulo.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        fontTitulo.setFontName(HSSFFont.FONT_ARIAL);
        fontTitulo.setFontHeightInPoints((short) 16);
        cellStyleTitulo.setFont(fontTitulo);//set it to bold

        CellStyle cellStyle2 = workbook.createCellStyle();
        XSSFColor colorBlanco2 = new XSSFColor(new java.awt.Color(255, 250, 250));
        ((XSSFCellStyle) cellStyle2).setFillForegroundColor(colorBlanco2);

        CellStyle cellStyleNumero = workbook.createCellStyle();
        XSSFColor colorBlanco3 = new XSSFColor(new java.awt.Color(255, 250, 250));
        ((XSSFCellStyle) cellStyleNumero).setFillForegroundColor(colorBlanco3);
        DataFormat df = workbook.createDataFormat();
        cellStyleNumero.setDataFormat(df.getFormat("###,###,##0.00"));

        CellStyle cellStyleFecha = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyleFecha.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        for (int i = 1; i < 1; i++) {
            Fila fila = new Fila(sheet.createRow(rownum++));
            Cell cell = fila.getFila().createCell(fila.nextIndex().shortValue());
            cell.setCellValue("");
            cell.setCellStyle(cellStyle);
        }

        for (int i = 1; i < 1; i++) {
            Fila fila = new Fila(sheet.createRow(rownum++));
            Cell cell = fila.getFila().createCell(fila.nextIndex().shortValue());
            cell.setCellValue("");
            cell.setCellStyle(cellStyle);

            Cell cell13 = fila.getFila().createCell(fila.nextIndex().shortValue());
            cell13.setCellValue("");
            cell13.setCellStyle(cellStyle);

            Cell cell11 = fila.getFila().createCell(fila.nextIndex().shortValue());
            cell11.setCellValue("Global_Mix");
            CellUtil.setAlignment(cell11, workbook, CellStyle.ALIGN_LEFT);
            cell11.setCellStyle(cellStyleTitulo);
        }

        for (int i = 1; i < 2; i++) {
            Fila fila = new Fila(sheet.createRow(rownum++));
            Cell cell12 = fila.getFila().createCell(fila.nextIndex().shortValue());
            cell12.setCellValue("");
            cell12.setCellStyle(cellStyle);

            Cell cell17 = fila.getFila().createCell(fila.nextIndex().shortValue());
            cell17.setCellValue("");
            cell17.setCellStyle(cellStyle);

            Cell cell15 = fila.getFila().createCell(fila.nextIndex().shortValue());
            cell15.setCellValue("Reporte de pedidos");
            cell15.setCellStyle(cellStyleTitulo);
        }

        Row encabezados = sheet.createRow(rownum++);

        Cell celda0 = encabezados.createCell(headerNum++);
        celda0.setCellValue("No.");
        celda0.setCellStyle(headerStyle);
        Cell celda1 = encabezados.createCell(headerNum++);
        celda1.setCellValue("Cliente");
        celda1.setCellStyle(headerStyle);
        Cell celda2 = encabezados.createCell(headerNum++);
        celda2.setCellValue("Asesor");
        celda2.setCellStyle(headerStyle);
        Cell celda3 = encabezados.createCell(headerNum++);
        celda3.setCellValue("Ubicación");
        celda3.setCellStyle(headerStyle);
        Cell celda4 = encabezados.createCell(headerNum++);
        celda4.setCellValue("Material");
        celda4.setCellStyle(headerStyle);
        Cell celda5 = encabezados.createCell(headerNum++);
        celda5.setCellValue("Tipo obra");
        celda5.setCellStyle(headerStyle);
        Cell celda6 = encabezados.createCell(headerNum++);
        celda6.setCellValue("Bombeo");
        celda6.setCellStyle(headerStyle);
        Cell celda7 = encabezados.createCell(headerNum++);
        celda7.setCellValue("Colocado");
        celda7.setCellStyle(headerStyle);
        Cell celda8 = encabezados.createCell(headerNum++);
        celda8.setCellValue("M|3 Encargados");
        celda8.setCellStyle(headerStyle);
        Cell celda9 = encabezados.createCell(headerNum++);
        celda9.setCellValue("Estado");
        celda9.setCellStyle(headerStyle);
        Cell celda10 = encabezados.createCell(headerNum++);
        celda10.setCellValue("Fecha");
        celda10.setCellStyle(headerStyle);
        int correlativo = 1;

        for (Pedidos reporte : listaPedidos) {
            if (!mapaFilas.containsKey(reporte.getIdpedido())) {
                Fila fila = new Fila(sheet.createRow(rownum++));
                mapaFilas.put(reporte.getIdpedido(), fila);

                Cell cell = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell.setCellValue(correlativo++);
                cell.setCellStyle(cellStyle);

                Cell cell1 = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell1.setCellValue(reporte.getIdcliente().getNombres() + " " + reporte.getIdcliente().getApellidos());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell2.setCellValue(reporte.getIdasesor().getNombres() + " " + reporte.getIdasesor().getApellidos());
                cell2.setCellStyle(cellStyle);

                Cell cell3 = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell3.setCellValue(reporte.getObra());
                cell3.setCellStyle(cellStyleNumero);

                if (reporte.getIdtipocemento() != null) {
                    Cell cell4 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell4.setCellValue(reporte.getIdtipocemento().getDescripcion() + " " + reporte.getExtraconcreto());
                    cell4.setCellStyle(cellStyle);
                } else {
                    Cell cell4 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell4.setCellValue("");
                    cell4.setCellStyle(cellStyle);
                }

                if (reporte.getElemento() != null) {
                    Cell cell5 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell5.setCellValue(reporte.getElemento());
                    cell5.setCellStyle(cellStyle);
                } else {
                    Cell cell5 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell5.setCellValue("");
                    cell5.setCellStyle(cellStyle);
                }

                if (reporte.getBombeo().equals("Si")) {
                    Cell cell6 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell6.setCellValue("Con bombeo");
                    cell6.setCellStyle(cellStyle);
                } else {
                    Cell cell6 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell6.setCellValue("Sin bombeo");
                    cell6.setCellStyle(cellStyle);
                }

                if (reporte.getColocado().equals("Si")) {
                    Cell cell7 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell7.setCellValue("Con colocado");
                    cell7.setCellStyle(cellStyle);
                } else {
                    Cell cell7 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell7.setCellValue("Sin colocado");
                    cell7.setCellStyle(cellStyle);
                }

                if (reporte.getVolumen() != null) {
                    Cell cell8 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell8.setCellValue(reporte.getVolumen());
                    cell8.setCellStyle(cellStyle);
                } else {
                    Cell cell8 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell8.setCellValue("");
                    cell8.setCellStyle(cellStyle);
                }

                if (reporte.getIdestadopedido() != null) {
                    Cell cell9 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell9.setCellValue(reporte.getIdestadopedido().getEstado());
                    cell9.setCellStyle(cellStyle);
                } else {
                    Cell cell9 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell9.setCellValue("");
                    cell9.setCellStyle(cellStyle);
                }

                if (reporte.getFechapedido() != null) {
                    Cell cell10 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell10.setCellValue(reporte.getFechapedido());
                    cell10.setCellStyle(cellStyleFecha);
                } else {
                    Cell cell10 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell10.setCellValue("");
                    cell10.setCellStyle(cellStyle);
                }
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String outputFileName = "Reporte_" + sdf.format(new Date()) + ".xlsx";
        String outputReportFile = getOutputDir() + outputFileName;
        FileOutputStream out = new FileOutputStream(outputReportFile);
        workbook.write(out);
        out.close();
        ((SXSSFWorkbook) workbook).dispose();
        FileInputStream stream = new FileInputStream(outputReportFile);
        content = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", outputFileName);

        return content;
    }

    class Fila {

        int cellIndex;
        Row fila;
        BigDecimal total;
        Integer cantidad;

        public Fila() {
            cellIndex = 0;
            total = new BigDecimal(0);
            cantidad = 0;
        }

        public Fila(Row fila) {
            this();
            this.fila = fila;
        }

        public void incrementarMonto(BigDecimal monto) {
            total = total.add(monto);
        }

        public void incrementarCantidad(Integer cantidad) {
            this.cantidad += cantidad;
        }

        public Integer nextIndex() {
            return cellIndex++;
        }

        public Integer getCellIndex() {
            return cellIndex;
        }

        public Row getFila() {
            return fila;
        }

        public void setFila(Row fila) {
            this.fila = fila;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public Integer getCantidad() {
            return cantidad;
        }
    }

    public String getOutputDir() {
        return String.format("%sresources%sreports%sgenerated%s", getRealPath(), getDirSeparator(), getDirSeparator(), getDirSeparator());
    }

    public String getImagesDir() {
        return String.format("%sresources%simages%s", getRealPath(), getDirSeparator(), getDirSeparator());
    }

    public String getRealPath() {
        if (realPath == null) {
            realPath = ((ServletContext) (FacesContext.getCurrentInstance()).getExternalContext().getContext()).getRealPath("/");
        }
        return this.realPath;
    }

    public String getDirSeparator() {
        if (dirSeparator == null) {
            dirSeparator = System.getProperty("file.separator");
        }
        return this.dirSeparator;
    }

      public StreamedContent imprimirReportePdf() throws SQLException, IOException {
        return imprimirReporte(ReportFormat.PDF);
    }
    
    public StreamedContent imprimirReporte(ReportFormat format) throws SQLException, IOException {
        if (fechaInicioReporte == null || fechaFinReporte == null) {
            JsfUtil.addErrorMessage("Debe de indicar las fechas");
            return null;
        }

        List<Pedidos> listaPedidos = pedidoBean.listPedidoByFechaInicio(fechaInicioReporte, fechaFinReporte);

        if (listaPedidos != null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/");
            String nombre = "rptPedidos2";            
            final List<WrapperPedidos> data = new ArrayList<>();

            for (Pedidos pedido : listaPedidos) {
                WrapperPedidos wrapper = new WrapperPedidos();
                wrapper.setAsesor(pedido.getIdasesor().getNombres() + ' ' + pedido.getIdasesor().getApellidos());
                if (pedido.getBombeo().equals("Si")) {
                    wrapper.setBombeo("Con bombeo");
                } else {
                    wrapper.setBombeo("Sin bombeo");
                }
                wrapper.setCliente(pedido.getIdcliente().getNombres() + ' ' + pedido.getIdcliente().getApellidos());
                if (pedido.getColocado().equals("Si")) {
                    wrapper.setBombeo("Con colocado");
                } else {
                    wrapper.setBombeo("Sin colocado");
                }
                wrapper.setElemento(pedido.getElemento());
                wrapper.setEstado(pedido.getIdestadopedido().getEstado());
                wrapper.setFecha(pedido.getFechapedido());
                wrapper.setMaterial(pedido.getIdtipocemento().getDescripcion() + ' ' + pedido.getExtraconcreto());
                wrapper.setUbicacion(pedido.getObra());
                wrapper.setVolumen(pedido.getVolumen());
                data.add(wrapper);
            }

            JasperReportHelper helper = new JasperReportHelper(nombre, "Pedidos", format) {
                @Override
                public Collection getData() {
                    return data;
                }
            };

            Map parameters = new HashMap<>();
            parameters.put("usuario", SesionUsuarioMB.getUserName());
            parameters.put("IMAGE", "logo.jpeg");
            parameters.put("DIRECTORIO", realPath + File.separator + "resources" + File.separator + "images" + File.separator);

            StreamedContent response = helper.fillReport(parameters);
            if (response == null) {
                JsfUtil.addErrorMessage("Error");
                return null;
            }
            return response;
        }
        JsfUtil.addErrorMessage("No se encontraron registros");
        return null;
    }

    public StreamedContent generReporte() throws ParseException {
        if (fechaInicioReporte == null || fechaFinReporte == null) {
            JsfUtil.addErrorMessage("Debe de indicar las fechas");
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(fechaInicioReporte);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        fechaInicioReporte = c.getTime();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(fechaFinReporte);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        fechaFinReporte = c1.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fe1 = sdf.format(fechaInicioReporte);
        String fe2 = sdf.format(fechaFinReporte);

        fechaInicioReporte = sdf.parse(fe1);
        fechaFinReporte = sdf.parse(fe2);
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
            parametros.put("FECHA_INICIO", fechaInicioReporte);
            parametros.put("FECHA_FIN", fechaFinReporte);
            parametros.put("usuario", SesionUsuarioMB.getUserName());

            ReporteJasper reporteJasper = JasperUtil.jasperReportPDF(nombreReporte, nombreArchivo, parametros, dataSource);
            StreamedContent streamedContent;
            FileInputStream stream = new FileInputStream(realPath + "resources/reports/" + reporteJasper.getFileName());
            streamedContent = new DefaultStreamedContent(stream, "application/pdf", reporteJasper.getFileName());
            return streamedContent;

        } catch (Exception ex) {
            JsfUtil.addErrorMessage("Ocurrio un error al generar el pdf del reporte");
        }
        RequestContext.getCurrentInstance().execute("PF('dlgGenerarEmpleado').hide()");
        return null;
    }

    public void verEditar(Integer idPedido) {
        JsfUtil.redirectTo("/pedidos/editar.xhtml?faces-redirect=true&idPedido=" + idPedido);
    }

    public void confirmarPedido(Pedidos idPedido) throws IOException {
        Confirmacionpago confir = new Confirmacionpago();
        confir.setIdpedido(idPedido);
        confir.setNombreconfirmo(SesionUsuarioMB.getUserName());

        Confirmacionpago response = pedidoBean.saveConfirmarPedido(confir, SesionUsuarioMB.getRolUsuario());
        if (response != null) {
            idPedido.setConfirmado(Boolean.TRUE);
            Pedidos responsePedido = pedidoBean.updatePedido(idPedido, SesionUsuarioMB.getRolUsuario());

            JsfUtil.addSuccessMessage("Se registro la confirmación exisitosamente");
        }
    }

    private DefaultScheduleEvent buildEvent(Pedidos pedido) {
        DefaultScheduleEvent e = new DefaultScheduleEvent();
        if (pedido.getIdtipocemento() != null) {
            e.setDescription(pedido.getIdtipocemento().getDescripcion());
        }

        e.setEndDate(pedido.getFechapedido());
        e.setStartDate(pedido.getFechapedido());
        e.setTitle(pedido.getObra());
        e.setData(pedido);
        if (pedido.getIdestadopedido().getIdestadopedido().equals(EstadoPedido.EN_PROCESO.getValue())) {
            e.setStyleClass("rowColorAmarillo");
        }
        if (pedido.getIdestadopedido().getIdestadopedido().equals(EstadoPedido.FINALIZADO.getValue())) {
            e.setStyleClass("rowColorCeleste");
        }
        //e.setStyleClass(dto.getStyleClass());
        return e;
    }

    public void loadCalendario() {
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;

        schedule = new DefaultScheduleModel();
        List<Pedidos> response = pedidoBean.listPedidoByMesAnio(mes, anio);
        if (response != null) {
            for (Pedidos ped : response) {
                schedule.addEvent(buildEvent(ped));
            }
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        pedidoCalendario = (ScheduleEvent) selectEvent.getObject();
        pedidoCalendario.getDescription();
        pedidoSelected = (Pedidos) pedidoCalendario.getData();
        log.debug("Evento seleccionado: " + pedidoSelected.toString());
        Pedidos response = pedidoBean.findPedidoById(pedidoSelected.getIdpedido());
        if (response != null) {
            pedidoSelected = response;
        }
    }

    public void onViewChange(SelectEvent selectEvent) {
        String view = selectEvent.getObject().toString();

        Schedule schedule = (Schedule) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:schedule");
        if (schedule == null) {
            System.out.println("------> es null es schedule..........");
        }

        AjaxBehaviorEvent behaviorEvent = (AjaxBehaviorEvent) selectEvent;

        //ZoneId zoneId = CalendarUtils.calculateZoneId(schedule.getTimeZone());
        //System.out.println("zoneId " + zoneId.toString());
    }


    /*Metodos getters y setters*/
    public List<Pedidos> getListPedidos() {
        return listPedidos;
    }

    public void setListPedidos(List<Pedidos> listPedidos) {
        this.listPedidos = listPedidos;
    }

    public List<Pedidos> getListPedidoFiltrado() {
        return listPedidoFiltrado;
    }

    public void setListPedidoFiltrado(List<Pedidos> listPedidoFiltrado) {
        this.listPedidoFiltrado = listPedidoFiltrado;
    }

    public Integer getIdsesor() {
        return idsesor;
    }

    public void setIdsesor(Integer idsesor) {
        this.idsesor = idsesor;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(Integer idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Asesor> getListAsesor() {
        return listAsesor;
    }

    public void setListAsesor(List<Asesor> listAsesor) {
        this.listAsesor = listAsesor;
    }

    public List<Cliente> getListCliente() {
        return listCliente;
    }

    public void setListCliente(List<Cliente> listCliente) {
        this.listCliente = listCliente;
    }

    public List<Estadopedido> getListEstado() {
        return listEstado;
    }

    public void setListEstado(List<Estadopedido> listEstado) {
        this.listEstado = listEstado;
    }

    public Date getFechaInicioReporte() {
        return fechaInicioReporte;
    }

    public void setFechaInicioReporte(Date fechaInicioReporte) {
        this.fechaInicioReporte = fechaInicioReporte;
    }

    public Date getFechaFinReporte() {
        return fechaFinReporte;
    }

    public void setFechaFinReporte(Date fechaFinReporte) {
        this.fechaFinReporte = fechaFinReporte;
    }

    public PedidosBeanLocal getPedidoBean() {
        return pedidoBean;
    }

    public void setPedidoBean(PedidosBeanLocal pedidoBean) {
        this.pedidoBean = pedidoBean;
    }

    public ScheduleModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleModel schedule) {
        this.schedule = schedule;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public Pedidos getPedidoSelected() {
        return pedidoSelected;
    }

    public void setPedidoSelected(Pedidos pedidoSelected) {
        this.pedidoSelected = pedidoSelected;
    }


}
