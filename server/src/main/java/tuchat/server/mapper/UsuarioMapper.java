package tuchat.server.mapper;

import tuchat.server.api.dto.request.CrearCuentaDTO;
import tuchat.server.model.tabla.Archivo;
import tuchat.server.model.tabla.Usuario;
import tuchat.server.model.tabla.UsuarioAuth;
import tuchat.server.model.tabla.UsuarioData;

public class UsuarioMapper {
	
	public static Usuario crear(CrearCuentaDTO dto)
	{
		Usuario usuario = Usuario.builder().correo(dto.getCorreo()).build();
		
		UsuarioData data = UsuarioDataMapper.toEntity(dto.getPerfil().getUsuarioData());
		
		usuario.setData(data);
		
		if(dto.getPerfil().getIcon() != null)
		{
			Archivo archivo = ArchivoMapper.toEntity(dto.getPerfil().getIcon());
			
			usuario.setIcon(archivo);
		}
		
		UsuarioAuth auth = UsuarioAuth.builder()
				.build();
		
		usuario.setAuth(auth);
						
		usuario.getAuth().setUsuario(usuario);
		usuario.getData().setUsuario(usuario);

		return usuario;
	}

}
