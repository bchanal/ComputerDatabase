package com.excilys.cdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.service.impl.ComputerServiceImpl;

/**
 * Servlet implementation class DeleteCommputer
 */
@Controller
@RequestMapping("/delete-computer")
public class DeleteComputerController {
	@Autowired
	private ComputerServiceImpl ctdao;

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@RequestParam("selection") String ids) {
		String[] tabId = ids.split(",");
		int id;

		for (String idStr : tabId) {
			id = Integer.parseInt(idStr);
			ctdao.delete(id);
		}
		return "redirect:/dashboard";
	}

}
