package tuchat.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuchat.server.api.dto.request.data.MensajeDataDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnviarMensajePrivadoDTO {
	
	private MensajeDataDTO mensajeData;

	private String correo;


}
