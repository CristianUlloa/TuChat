package tuchat.server.api.dto.response.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensajeGrupoDataDTO {
	
	private ObtenerMensajeDataDTO mensajeData;
	
	private List<MensajeStatusDataDTO> statusData;
}
