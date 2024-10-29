package tuchat.server.mapper;

import tuchat.server.api.dto.request.data.UsuarioDataDTO;
import tuchat.server.api.dto.response.ObtenerInfoUsuarioDTO;
import tuchat.server.model.tabla.Usuario;
import tuchat.server.model.tabla.UsuarioData;

public class UsuarioDataMapper {

	public static UsuarioData toEntity(UsuarioDataDTO dto) {
		UsuarioData data = UsuarioData.builder().nombreCompleto(dto.getNombreCompleto())
				.usaNombreCompleto(dto.isUsaNombreCompleto()).nombres(dto.getNombres()).apellidos(dto.getApellidos())
				.descripcion(dto.getDescripcion()).build();

		return data;
	}

	public static UsuarioDataDTO toDTO(UsuarioData dto) {
		UsuarioDataDTO data = UsuarioDataDTO.builder().nombreCompleto(dto.getNombreCompleto())
				.usaNombreCompleto(dto.isUsaNombreCompleto()).nombres(dto.getNombres()).apellidos(dto.getApellidos())
				.descripcion(dto.getDescripcion()).build();

		return data;
	}

	public static ObtenerInfoUsuarioDTO toInfoDTO(Usuario usuario) {
	    var data = usuario.getData(); 

	    ObtenerInfoUsuarioDTO infoUsuario = ObtenerInfoUsuarioDTO.builder()
	            .correo(usuario.getCorreo())
	            .descripcion(data.getDescripcion())
	            .iconData(ArchivoMapper.toDTO(usuario.getIcon()))
	            .build();

	    if (data.isUsaNombreCompleto()) {
	        infoUsuario.setNombre(data.getNombreCompleto());
	    } else {
	        infoUsuario.setNombre(data.getNombres() + " " + data.getApellidos());
	    }

	    return infoUsuario;
	}


}
