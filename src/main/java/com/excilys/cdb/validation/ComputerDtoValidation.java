package com.excilys.cdb.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.excilys.cdb.dto.ComputerDto;

public class ComputerDtoValidation {

	public static List<String> validate (ComputerDto cdto){
		
		List<String> errors = new ArrayList<String>();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<ComputerDto>> constraintViolations = validator.validate(cdto);

		if (constraintViolations.size() > 0) {
			System.out.println("Impossible to validate dates : ");
			for (ConstraintViolation<ComputerDto> contraintes : constraintViolations) {
				String error = (contraintes.getRootBeanClass()
						.getSimpleName()
						+ "."
						+ contraintes.getPropertyPath()
						+ " " + contraintes.getMessage());
				errors.add(error);	
			}
		}
			
		return errors;

	}
}

