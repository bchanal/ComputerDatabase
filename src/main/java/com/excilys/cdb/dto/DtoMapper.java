package com.excilys.cdb.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Language;
import com.excilys.cdb.util.DateUtil;
import com.excilys.cdb.util.Util;

/**
 * 
 * @author berangere
 *
 */
@Component
public class DtoMapper {
    
    @Autowired
    DateUtil dateUtil;

    public DtoMapper() {

    }

    /**
     * get a Dto from a computer
     * @param computer the computer
     * @return computerDto : the computer with the dates in String format
     */

    public ComputerDto computerToDto(Computer computer) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateUtil.getDatePattern());

        String introduced = "";
        String discontinued = "";
        Company company = new Company();

        int id = computer.getId();
        String name = computer.getName();
        if (computer.getDateIntro() != null) {
            introduced = df.format(computer.getDateIntro());
        }

        if (computer.getDateDiscontinued() != null) {
            discontinued = df.format(computer.getDateDiscontinued());
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
        introduced = Util.checkDate(computerd.getIntroduced());

        discontinued = Util.checkDate(computerd.getDiscontinued());

        Company company = computerd.getCompany();

        Computer c = new Computer(id, name, introduced, discontinued, company);

        return c;

    }
}
