package tuchat.server.api.dto.request.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDataDTO {

	private String nombre;
	
	private String descripcion;
	
	private ArchivoDataDTO icon;
}
