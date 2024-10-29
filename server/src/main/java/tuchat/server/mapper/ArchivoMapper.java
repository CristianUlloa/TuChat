package tuchat.server.mapper;

import tuchat.server.api.dto.request.data.ArchivoDataDTO;
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

}
