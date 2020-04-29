package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.dto.ConsultaListaExamenDTO;
import com.escalab.model.Consulta;
import com.escalab.repo.IConsultaExamenRepo;
import com.escalab.repo.IConsultaRepo;
import com.escalab.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService{
	
	@Autowired
	private IConsultaRepo repo;
	
	private IConsultaExamenRepo ceRepo;
	
	@Override
	public Consulta registrar(Consulta obj) {
		obj.getDetalleConsulta().forEach(det ->{
			det.setConsulta(obj);
		});
		return repo.save(obj);
	}
	
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) {
		dto.getConsulta().getDetalleConsulta().forEach(det -> {
			det.setConsulta(dto.getConsulta());
		});
		
		repo.save(dto.getConsulta());
		
		dto.getLstExamen().forEach(ex -> ceRepo.registrar(dto.getConsulta().getIdConsulta(), ex.getIdExamen()));
		return dto.getConsulta();
	}
	
	@Override
	public Consulta modificar(Consulta obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<Consulta> listar(){
		return repo.findAll();
	}
	
	@Override
	public Consulta leerPorId(Integer id) {
		Optional<Consulta> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Consulta();
	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
