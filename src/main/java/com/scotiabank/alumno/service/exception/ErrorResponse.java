package com.scotiabank.alumno.service.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {

    private String mensaje;
    private Map<String, String> errores;

    public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
        this.errores = new HashMap();
    }

    public ErrorResponse(String mensaje, Map<String, String> errores) {
        this.mensaje = mensaje;
        this.errores = errores;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public Map<String, String> getErrores() {
        return this.errores;
    }
}
