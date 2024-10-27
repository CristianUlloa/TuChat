package com.tuchat.judc.server.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuchat.judc.server.model.Grupo;
import com.tuchat.judc.server.model.RegistroGrupoEvento;
import com.tuchat.judc.server.model.TipoEvento;
import com.tuchat.judc.server.model.Usuario;
import com.tuchat.judc.server.repository.RegistroGrupoEventoRepository;

@Service
public class RegistroGrupoEventoService {

	@Autowired
	private RegistroGrupoEventoRepository registroGrupoEventoRepository;

	public void registrarEvento(Grupo grupo, Usuario usuarioDesencadenador, Usuario usuarioAfectado,
			TipoEvento tipoEvento) {

		RegistroGrupoEvento evento = RegistroGrupoEvento.builder().grupo(grupo)
				.usuarioDesencadenador(usuarioDesencadenador).usuarioAfectado(usuarioAfectado).tipoEvento(tipoEvento)
				.build();
		registroGrupoEventoRepository.save(evento);
	}

}
