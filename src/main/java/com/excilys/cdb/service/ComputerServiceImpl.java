package com.excilys.cdb.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDaoImpl;

public enum ComputerServiceImpl implements ComputerService {
	
	instance;

	private ComputerDaoImpl cdao = ComputerDaoImpl.instance;

	private ComputerServiceImpl() {
	}

	@Override
	public void create(String name, LocalDateTime dateTime,	LocalDateTime dateTimeFin, int comp) {
		cdao.create(name, dateTime, dateTimeFin, comp);
	}

	@Override
	public List<Computer> getAll() {
		return cdao.getAll();
	}

	 @Override
	 public Page getAPage(int index, int nb, String name) {
//		 List<Computer> lcomp = cdao.getAPage(index, nb); 
//		 List<ComputerDto> ldto = new ArrayList<ComputerDto>();
//		 for(Computer c : lcomp){
//			 ComputerDto cdto = DtoMapper.computerToDto(c);
//			 ldto.add(cdto);
//		 }
//		 Page p = new Page(index,nb,ldto);
		 Page p = cdao.getAPage(index, nb, name);
		 p.setNbTotalComputer(cdao.getNbComputers(name));
		 
		 return p;
	 }
	 
////	 @Override
//		public int getNbComputers() {
//		  	return cdao.getNbComputers("");
//		}


	@Override
	public void delete(int computerId) {
		cdao.delete(computerId);
	}

	@Override
	public void update(Computer computer) {
		cdao.update(computer);
	}

	@Override
	public Computer getById(int computerId) {
		return cdao.getById(computerId);
	}

	@Override
	public List<Computer> getByName(String name) {
		return cdao.getByName(name);
	}

}
