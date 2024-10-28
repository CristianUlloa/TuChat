package tuchat.server.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerStatusDTO {
	
	private List<String> correosNuevosMensajes;
	
	private List<Integer> gruposNuevosMensajes;
	
	private int num_grupo;


}
