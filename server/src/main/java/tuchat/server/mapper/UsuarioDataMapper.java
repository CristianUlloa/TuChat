package tuchat.server.mapper;

import tuchat.server.api.dto.request.data.UsuarioDataDTO;
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

}
