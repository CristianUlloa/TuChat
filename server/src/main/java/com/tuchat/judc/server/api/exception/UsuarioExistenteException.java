package com.tuchat.judc.server.api.exception;

public class UsuarioExistenteException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4871886300179996398L;

	public UsuarioExistenteException(String mensaje) {
        super(mensaje);
    }
}
