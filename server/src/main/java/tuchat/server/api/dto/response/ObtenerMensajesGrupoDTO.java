package tuchat.server.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuchat.server.api.dto.response.data.MensajeGrupoDataDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerMensajesGrupoDTO {
	
	private List<MensajeGrupoDataDTO> mensajesData;

}
