package tuchat.server.api.dto.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensajePrivadoDataDTO {
	
	private ObtenerMensajeDataDTO mensajeData;
	
	private MensajeStatusDataDTO statusData;

}
