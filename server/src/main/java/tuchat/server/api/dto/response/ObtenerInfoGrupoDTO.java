package tuchat.server.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuchat.server.api.dto.response.data.GrupoUsuarioDataDTO;
import tuchat.server.api.dto.response.data.ObtenerArchivoDataDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObtenerInfoGrupoDTO {

	private int id;

	private String nombre;

	private String descripcion;

	private ObtenerArchivoDataDTO iconData;

	private List<GrupoUsuarioDataDTO> integrantes;

}
