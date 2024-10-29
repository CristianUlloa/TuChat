package tuchat.server.mapper;

import tuchat.server.api.dto.request.data.ArchivoDataDTO;
import tuchat.server.api.dto.response.data.ObtenerArchivoDataDTO;
import tuchat.server.model.tabla.Archivo;

public class ArchivoMapper {
	
	private static OnSaveFile onSave = null;
	
	public static interface OnSaveFile {

		Archivo save(ArchivoDataDTO icon);
		
	}
	
	public static Archivo toEntity(ArchivoDataDTO dto)
	{
		return onSave.save(dto);
				
	}
	
	public static ObtenerArchivoDataDTO toDTO(Archivo archivo)
	{
		
		if(archivo == null)
			return null;
		
		return ObtenerArchivoDataDTO.builder().path(archivo.getPath()).build();
				
	}

}
