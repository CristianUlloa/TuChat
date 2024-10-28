package tuchat.server.api.dto.response.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuchat.server.api.dto.response.ObtenerInfoUsuarioDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoUsuarioDataDTO {

	private String rol;
	
	private ObtenerInfoUsuarioDTO data;
}
