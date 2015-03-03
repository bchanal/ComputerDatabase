package com.excilys.cdb.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Language;
import com.excilys.cdb.util.Util;

/**
 * 
 * @author berangere
 *
 */
@Component
public class DtoMapper {

    public DtoMapper() {

    }

    /**
     * get a Dto from a computer
     * @param computer the computer
     * @return computerDto : the computer with the dates in String format
     */
    public ComputerDto computerToDto(Computer computer) {

        String introduced = "";
        String discontinued = "";
        Company company = new Company();

        int id = computer.getId();
        String name = computer.getName();
        if (computer.getDateIntro() != null) {
            introduced = computer.getDateIntro().toString();
        }

        if (computer.getDateDiscontinued() != null) {
            discontinued = computer.getDateDiscontinued().toString();
        }
        if (computer.getManufacturer() != null) {
            company = computer.getManufacturer();
        }

        ComputerDto cdto = new ComputerDto(id, name, introduced, discontinued, company);

        return cdto;

    }

    /**
     * get a computer from a dto
     * @param computerd the computerDto
     * @return computer the computer
     */
    public Computer dtoToComputer(ComputerDto computerd, Language lang) {

        int id = computerd.getId();
        LocalDateTime introduced;
        LocalDateTime discontinued;
        String name = computerd.getName();
        if (lang == Language.FRENCH) {
            introduced = Util.checkDateFr(computerd.getIntroduced());
        } else {
            introduced = Util.checkDateEn(computerd.getIntroduced());
        }

        if (lang == Language.FRENCH) {
            discontinued = Util.checkDateFr(computerd.getDiscontinued());
        } else {
            discontinued = Util.checkDateEn(computerd.getIntroduced());
        }
        Company company = computerd.getCompany();

        Computer c = new Computer(id, name, introduced, discontinued, company);

        return c;

    }
}
