package com.pdf.entidades;

/**
 *
 * @author ESTEFANIECM
 */
import java.util.List;

public class DatosPdf {

    private String concepto;
    private String fechaRecibo;
    private List<FormasDePago> formasDePago; // Debe coincidir con el nombre en Angular
    private String idEmpresa;
    private String montoRecibido;
    private String noRecibo;
    private String recibimosDe;
    private String totalRecibo;

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(String fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public List<FormasDePago> getFormasDePago() {
        return formasDePago;
    }

    public void setFormasDePago(List<FormasDePago> formasDePago) {
        this.formasDePago = formasDePago;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(String montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public String getNoRecibo() {
        return noRecibo;
    }

    public void setNoRecibo(String noRecibo) {
        this.noRecibo = noRecibo;
    }

    public String getRecibimosDe() {
        return recibimosDe;
    }

    public void setRecibimosDe(String recibimosDe) {
        this.recibimosDe = recibimosDe;
    }

    public String getTotalRecibo() {
        return totalRecibo;
    }

    public void setTotalRecibo(String totalRecibo) {
        this.totalRecibo = totalRecibo;
    }

  

}
