package tuchat.server.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuchat.server.api.dto.response.data.ObtenerArchivoDataDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerInfoUsuarioDTO {
	private String nombre;
	private String correo;
	private String descripcion;
	private ObtenerArchivoDataDTO iconData;
}
