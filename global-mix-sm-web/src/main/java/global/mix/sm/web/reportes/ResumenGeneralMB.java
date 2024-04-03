package global.mix.sm.web.reportes;

import global.mix.sm.api.ejb.DespachosBeanLocal;
import global.mix.sm.api.entity.Despachos;
import globa.mix.api.wrapper.ResumenWrapper;
import global.mix.sm.web.utils.JsfUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
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
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rcacacho
 */
@ManagedBean(name = "resumenGeneralMB")
@ViewScoped
public class ResumenGeneralMB implements Serializable {

    private static final Logger log = Logger.getLogger(ResumenGeneralMB.class);

    @EJB
    private DespachosBeanLocal despachoBean;

    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicioReporte;
    private Date fechaFinReporte;
    private List<Despachos> listDespachos;
    private List<Despachos> listDespachosFilter;
    private final String LOGO = "logo.jpeg";
    private String realPath;
    private String dirSeparator;
    private List<ResumenWrapper> listRes;
    private ResumenWrapper resumenWrapper;

    public ResumenGeneralMB() {
        listRes = new ArrayList<>();
        resumenWrapper = new ResumenWrapper();
    }

    @PostConstruct
    void cargarDatos() {
        List<Despachos> response = despachoBean.listDespachoBuzonReporteGeneral(fechaInicio, fechaFin);
        if (response != null) {
            listDespachos = response;
        }
    }

    public void dialogReporte() {
        fechaInicioReporte = null;
        fechaFinReporte = null;
        RequestContext.getCurrentInstance().execute("PF('dlgReporte').show()");
    }

    public void buscarFiltro() {
        try {
            if (fechaInicio != null || fechaFin != null) {
                List<Despachos> response = despachoBean.listDespachoBuzonReporteGeneral(fechaInicio, fechaFin);
                if (response != null) {
                    for (Despachos dd : response) {
                        if (listRes.isEmpty()) {
                            resumenWrapper.setBeneficioPrimo(dd.getBeneficioprimo());
                            resumenWrapper.setCliente(dd.getIdpedido().getIdcliente().getNombres() + " " + dd.getIdpedido().getIdcliente().getApellidos());
                            resumenWrapper.setColocado(dd.getCostototalcolocado());
                            resumenWrapper.setComision(dd.getComision());
                            resumenWrapper.setCompras(0.0);
                            resumenWrapper.setDiesel(dd.getCostototaldieseldinero());
                            resumenWrapper.setEncargados(dd.getMetroscubicossolicitados());
                            resumenWrapper.setFecha(dd.getFechacreacion());
                            resumenWrapper.setIngresoNeto(dd.getPagototalsiniva());
                            resumenWrapper.setObra(dd.getIdpedido().getObra());
                            resumenWrapper.setTotalBombeo(dd.getCostototalbombeo());
                            resumenWrapper.setTotalMateriaPrima(dd.getTotalmateriaprima());
                            resumenWrapper.setVendidos(dd.getMetroscubicosvendidos());
                            listRes.add(resumenWrapper);
                        } else {
                            for (ResumenWrapper wrapper : listRes) {
                                if (wrapper.getFecha().compareTo(dd.getFechacreacion()) == 0) {
                                    if (resumenWrapper.getBeneficioPrimo() != null) {
                                        resumenWrapper.setBeneficioPrimo(wrapper.getBeneficioPrimo() + resumenWrapper.getBeneficioPrimo());
                                    }

                                    if (resumenWrapper.getColocado() != null) {
                                        resumenWrapper.setColocado(wrapper.getColocado() + resumenWrapper.getColocado());
                                    }

                                    if (resumenWrapper.getComision() != null) {
                                        resumenWrapper.setComision(wrapper.getComision() + resumenWrapper.getComision());
                                    }

                                    if (resumenWrapper.getDiesel() != null) {
                                        resumenWrapper.setDiesel(wrapper.getDiesel() + resumenWrapper.getDiesel());
                                    }

                                    resumenWrapper.setEncargados(wrapper.getEncargados() + resumenWrapper.getEncargados());
                                    
                                    if (resumenWrapper.getIngresoNeto() != null) {
                                        resumenWrapper.setIngresoNeto(wrapper.getIngresoNeto() + resumenWrapper.getIngresoNeto());
                                    }

                                    resumenWrapper.setTotalBombeo(wrapper.getTotalBombeo() + resumenWrapper.getTotalBombeo());
                                    resumenWrapper.setTotalMateriaPrima(wrapper.getTotalMateriaPrima() + resumenWrapper.getTotalMateriaPrima());
                                    resumenWrapper.setVendidos(wrapper.getVendidos() + resumenWrapper.getVendidos());
                                    listRes.add(resumenWrapper);
                                } else {
                                    resumenWrapper = new ResumenWrapper();
                                    resumenWrapper.setBeneficioPrimo(dd.getBeneficioprimo());
                                    resumenWrapper.setCliente(dd.getIdpedido().getIdcliente().getNombres() + " " + dd.getIdpedido().getIdcliente().getApellidos());
                                    resumenWrapper.setColocado(dd.getCostototalcolocado());
                                    resumenWrapper.setComision(dd.getComision());
                                    resumenWrapper.setCompras(0.0);
                                    resumenWrapper.setDiesel(dd.getCostototaldieseldinero());
                                    resumenWrapper.setEncargados(dd.getMetroscubicossolicitados());
                                    resumenWrapper.setFecha(dd.getFechacreacion());
                                    resumenWrapper.setIngresoNeto(dd.getPagototalsiniva());
                                    resumenWrapper.setObra(dd.getIdpedido().getObra());
                                    resumenWrapper.setTotalBombeo(dd.getCostototalbombeo());
                                    resumenWrapper.setTotalMateriaPrima(dd.getTotalmateriaprima());
                                    resumenWrapper.setVendidos(dd.getMetroscubicosvendidos());
                                    listRes.add(resumenWrapper);
                                }
                            }
                        }
                    }
                    //listDespachos = response;
                } else {
                    listDespachos = new ArrayList<>();
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void limpiarCampos() {
        fechaInicio = null;
        fechaFin = null;

        cargarDatos();
    }

    public void verDetalle(Integer idPedido) {
        JsfUtil.redirectTo("/pedidos/detalle.xhtml?faces-redirect=true&idPedido=" + idPedido);
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

    public StreamedContent imprimirExcel() throws IOException {
        StreamedContent content = null;
        List<Despachos> listaDesp = despachoBean.listDespachoBuzonReporteGeneral(fechaInicio, fechaFin);

        HashMap<Integer, Fila> mapaFilas = new HashMap<>();
        Workbook workbook = new SXSSFWorkbook(1000);
        Sheet sheet = workbook.createSheet("Resumen_General");

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
            cell15.setCellValue("Resumen general");
            cell15.setCellStyle(cellStyleTitulo);
        }

        Row encabezados = sheet.createRow(rownum++);
//        InputStream inputStream = new FileInputStream(getImagesDir() + LOGO);
//        byte[] bytes = IOUtils.toByteArray(inputStream);
//        //Adds a picture to the workbook
//        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
//        //close the input stream
//        inputStream.close();
//        //Returns an object that handles instantiating concrete classes
//        CreationHelper helper = workbook.getCreationHelper();
//        //Creates the top-level drawing patriarch.
//        Drawing drawing = sheet.createDrawingPatriarch();
//        //Create an anchor that is attached to the worksheet
//        ClientAnchor anchor = helper.createClientAnchor();
//        //set top-left corner for the image
//        anchor.setCol1(3);
//        anchor.setRow1(1);
//        //Creates a picture
//        Picture pict = drawing.createPicture(anchor, pictureIdx);
//        //Reset the image to the original size
//        pict.resize(0.7, 6);

        Cell celda0 = encabezados.createCell(headerNum++);
        celda0.setCellValue("No.");
        celda0.setCellStyle(headerStyle);
        Cell celda1 = encabezados.createCell(headerNum++);
        celda1.setCellValue("Cliente");
        celda1.setCellStyle(headerStyle);
        Cell celda2 = encabezados.createCell(headerNum++);
        celda2.setCellValue("Obra");
        celda2.setCellStyle(headerStyle);
        Cell celda3 = encabezados.createCell(headerNum++);
        celda3.setCellValue("M|3 encargados");
        celda3.setCellStyle(headerStyle);
        Cell celda4 = encabezados.createCell(headerNum++);
        celda4.setCellValue("M|3 vendidos");
        celda4.setCellStyle(headerStyle);
        Cell celda5 = encabezados.createCell(headerNum++);
        celda5.setCellValue("Total materia prima");
        celda5.setCellStyle(headerStyle);
        Cell celda6 = encabezados.createCell(headerNum++);
        celda6.setCellValue("Diésel");
        celda6.setCellStyle(headerStyle);
        Cell celda7 = encabezados.createCell(headerNum++);
        celda7.setCellValue("Total bombeo");
        celda7.setCellStyle(headerStyle);
        Cell celda8 = encabezados.createCell(headerNum++);
        celda8.setCellValue("Total colocado");
        celda8.setCellStyle(headerStyle);
        Cell celda9 = encabezados.createCell(headerNum++);
        celda9.setCellValue("Total comision");
        celda9.setCellStyle(headerStyle);
        Cell celda10 = encabezados.createCell(headerNum++);
        celda10.setCellValue("Total ingreso neto");
        celda10.setCellStyle(headerStyle);
        Cell celda11 = encabezados.createCell(headerNum++);
        celda11.setCellValue("Beneficio primo");
        celda11.setCellStyle(headerStyle);
        Cell celda12 = encabezados.createCell(headerNum++);
        celda12.setCellValue("Compras");
        celda12.setCellStyle(headerStyle);
        Cell celda13 = encabezados.createCell(headerNum++);
        celda13.setCellValue("Fecha");
        celda13.setCellStyle(headerStyle);
        int correlativo = 1;

        for (Despachos reporte : listaDesp) {
            if (!mapaFilas.containsKey(reporte.getIddespacho())) {
                Fila fila = new Fila(sheet.createRow(rownum++));
                mapaFilas.put(reporte.getIddespacho(), fila);

                Cell cell = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell.setCellValue(correlativo++);
                cell.setCellStyle(cellStyle);

                Cell cell1 = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell1.setCellValue(reporte.getIdpedido().getIdcliente().getNombres() + " " + reporte.getIdpedido().getIdcliente().getApellidos());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell2.setCellValue(reporte.getIdpedido().getObra());
                cell2.setCellStyle(cellStyle);

                Cell cell3 = fila.getFila().createCell(fila.nextIndex().shortValue());
                cell3.setCellValue(reporte.getIdpedido().getVolumen());
                cell3.setCellStyle(cellStyleNumero);

                if (reporte.getMetroscubicosvendidos() != null) {
                    Cell cell4 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell4.setCellValue(reporte.getMetroscubicosvendidos());
                    cell4.setCellStyle(cellStyle);
                } else {
                    Cell cell4 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell4.setCellValue("");
                    cell4.setCellStyle(cellStyle);
                }

                if (reporte.getTotalmateriaprima() != null) {
                    Cell cell5 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell5.setCellValue(reporte.getTotalmateriaprima());
                    cell5.setCellStyle(cellStyle);
                } else {
                    Cell cell5 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell5.setCellValue("");
                    cell5.setCellStyle(cellStyle);
                }

                if (reporte.getCostototaldieseldinero() != null) {
                    Cell cell6 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell6.setCellValue(reporte.getCostototaldieseldinero());
                    cell6.setCellStyle(cellStyle);
                } else {
                    Cell cell6 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell6.setCellValue("");
                    cell6.setCellStyle(cellStyle);
                }

                if (reporte.getCostototalbombeo() != null) {
                    Cell cell7 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell7.setCellValue(reporte.getCostototalbombeo());
                    cell7.setCellStyle(cellStyle);
                } else {
                    Cell cell7 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell7.setCellValue("");
                    cell7.setCellStyle(cellStyle);
                }

                if (reporte.getCostototalcolocado() != null) {
                    Cell cell8 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell8.setCellValue(reporte.getCostototalcolocado());
                    cell8.setCellStyle(cellStyle);
                } else {
                    Cell cell8 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell8.setCellValue("");
                    cell8.setCellStyle(cellStyle);
                }

                if (reporte.getComision() != null) {
                    Cell cell9 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell9.setCellValue(reporte.getComision());
                    cell9.setCellStyle(cellStyle);
                } else {
                    Cell cell9 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell9.setCellValue("");
                    cell9.setCellStyle(cellStyle);
                }

                if (reporte.getPagototalsiniva() != null) {
                    Cell cell10 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell10.setCellValue(reporte.getPagototalsiniva());
                    cell10.setCellStyle(cellStyle);
                } else {
                    Cell cell10 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell10.setCellValue("");
                    cell10.setCellStyle(cellStyle);
                }

                if (reporte.getBeneficioprimo() != null) {
                    Cell cell11 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell11.setCellValue(reporte.getBeneficioprimo());
                    cell11.setCellStyle(cellStyle);
                } else {
                    Cell cell11 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell11.setCellValue("");
                    cell11.setCellStyle(cellStyle);
                }

                //pendiente
                if (reporte.getBeneficiometrocubico() != null) {
                    Cell cell12 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell12.setCellValue(reporte.getPagototalsiniva());
                    cell12.setCellStyle(cellStyle);
                } else {
                    Cell cell12 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell12.setCellValue("");
                    cell12.setCellStyle(cellStyle);
                }

                if (reporte.getFechacreacion() != null) {
                    Cell cell13 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell13.setCellValue(reporte.getFechacreacion());
                    cell13.setCellStyle(cellStyle);
                } else {
                    Cell cell13 = fila.getFila().createCell(fila.nextIndex().shortValue());
                    cell13.setCellValue("");
                    cell13.setCellStyle(cellStyle);
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

    public String getOutputDir() {
        return String.format("%sresources%sreports%sgenerated%s", getRealPath(), getDirSeparator(), getDirSeparator(), getDirSeparator());
    }

    /*Metodos getters y setters */
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

    public List<Despachos> getListDespachos() {
        return listDespachos;
    }

    public void setListDespachos(List<Despachos> listDespachos) {
        this.listDespachos = listDespachos;
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

    public List<Despachos> getListDespachosFilter() {
        return listDespachosFilter;
    }

    public void setListDespachosFilter(List<Despachos> listDespachosFilter) {
        this.listDespachosFilter = listDespachosFilter;
    }

}
