package tuchat.server.mapper;

import tuchat.server.api.dto.request.data.MensajeDataDTO;
import tuchat.server.api.dto.response.data.MensajePrivadoDataDTO;
import tuchat.server.api.dto.response.data.MensajeStatusDataDTO;
import tuchat.server.api.dto.response.data.ObtenerMensajeDataDTO;
import tuchat.server.model.tabla.Mensaje;

public class MensajeMapper {

	public static Mensaje crear(MensajeDataDTO dto) {
		Mensaje mensaje = Mensaje.builder().texto(dto.getTexto()).build();

		if (dto.getArchivo() != null) {
			mensaje.setArchivo(ArchivoMapper.toEntity(dto.getArchivo()));
		}

		return mensaje;
	}

	public static MensajePrivadoDataDTO toDTO(Mensaje ent) {
		
		ObtenerMensajeDataDTO data = ObtenerMensajeDataDTO.builder()
				.id(ent.getId())
				.texto(ent.getTexto())
				.correo(ent.getUsuarioEmisor().getCorreo())
				.archivo(ArchivoMapper.toDTO(ent.getArchivo()))
				.build();
		
		MensajeStatusDataDTO status = new MensajeStatusDataDTO(data.getCorreo(), "DEFAULT");
		
		MensajePrivadoDataDTO dto = MensajePrivadoDataDTO.builder()
				.mensajeData(data)
				.statusData(status)//proximanente
				.build();		
		
		return dto;
	}

}
