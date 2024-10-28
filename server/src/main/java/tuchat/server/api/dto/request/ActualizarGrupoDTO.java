package tuchat.server.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuchat.server.api.dto.request.data.GrupoDataDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarGrupoDTO {
	
	private int id;

	private GrupoDataDTO grupoData;

}
