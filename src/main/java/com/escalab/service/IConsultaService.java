package com.escalab.service;

import com.escalab.dto.ConsultaListaExamenDTO;
import com.escalab.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta>{
	
	Consulta registrarTransaccional(ConsultaListaExamenDTO dto);

}
