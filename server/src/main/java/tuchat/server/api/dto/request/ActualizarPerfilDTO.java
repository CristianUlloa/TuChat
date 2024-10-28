package tuchat.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuchat.server.api.dto.request.data.ArchivoDataDTO;
import tuchat.server.api.dto.request.data.UsuarioDataDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarPerfilDTO {
	
	private ArchivoDataDTO icon;

	private UsuarioDataDTO usuarioData;
}
