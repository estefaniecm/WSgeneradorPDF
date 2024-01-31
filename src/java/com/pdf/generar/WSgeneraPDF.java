package com.pdf.generar;

import com.pdf.backend.CrearPDF;
import com.pdf.entidades.DatosPdf;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ESTEFANIECM
 */
@WebService(serviceName = "WSgeneraPDF")
public class WSgeneraPDF {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "generarPDF")
    public String generarPDF(@WebParam(name = "datos") DatosPdf dp){
        String respuesta = "";
        try {
            respuesta = new CrearPDF().CrearPDF(dp);
            System.out.println("Mi respuesta es: \n"+respuesta);
        } catch (Exception e) {
        respuesta = "Error al procesar el Recibo: " + e.getMessage();
        e.printStackTrace();
    }
        
        return respuesta;
    }
    
}
