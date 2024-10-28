package tuchat.server.api.dto.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerMensajeDataDTO {
	
	private int id;
	
	private String texto;
	
	private String correo;
	
	private ObtenerArchivoDataDTO archivo;
	
}
