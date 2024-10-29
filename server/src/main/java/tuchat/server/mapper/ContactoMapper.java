package tuchat.server.mapper;

import tuchat.server.model.tabla.ContactoUsuario;

public class ContactoMapper {

	public static String toString(ContactoUsuario ent) {

		return ent.getUsuarioRef().getCorreo();
	}
}
