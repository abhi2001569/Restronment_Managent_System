package com.RMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RMS.Entity.DingingTable;
import com.RMS.Repository.DingingTableRepository;

@Controller
public class DingingTableController {
	
	@Autowired
	private DingingTableRepository dingingtablerepo;
	
	@RequestMapping("/godingigntable")
	public String fodingingtableform(Model model) {
		
		long diningtablecount = dingingtablerepo.count();
		model.addAttribute("diningtablecount", diningtablecount);
		
		return "AddDingingTable";
	}
	
	@PostMapping("/adddingingtable")
	public String saveaddinging(@ModelAttribute DingingTable dingingtable, Model model) {
	    
	    if (dingingtablerepo.existsByTableNumber(dingingtable.getTableNumber())) {
	        model.addAttribute("errormessage", "Dining table already exists!");
	    } else {
	        dingingtablerepo.save(dingingtable);
	        model.addAttribute("successmessage", "Dining table added successfully!");
	    }
	    
	    return "AddDingingTable";
	}

	
	@RequestMapping("/displayingingtables")
	public String displaydingingtable(Model model){
		
		List<DingingTable> dingingtable = dingingtablerepo.findAll();
		
		long diningtablecount = dingingtablerepo.count();
		
		model.addAttribute("displaydining", dingingtable);
		model.addAttribute("diningtablecount", diningtablecount);
		 
		 return "DisplayDingingTable";
		
	}
	
	@RequestMapping("editdinging/{tableId}")
	public String editdingingtable(@PathVariable int tableId, Model model) {
		
		DingingTable dingingtable = dingingtablerepo.findById(tableId).orElse(null);
		
		model.addAttribute("dingingtabledata",dingingtable);
		
		return "EditDiningTable";	
				
	}
	
	@PostMapping("/updatediningtable/{tableId}")
	public String updatedingingtable(@PathVariable int tableId, @ModelAttribute DingingTable dingingtable, Model model)
	{
		DingingTable dingingtableupdate = dingingtablerepo.findById(tableId).orElse(null);
		
		if(dingingtableupdate != null)
		{
			dingingtableupdate.setTableNumber(dingingtable.getTableNumber());
			dingingtableupdate.setTablCapacity(dingingtable.getTablCapacity()); // Corrected
			dingingtableupdate.setTableAvailable(dingingtable.getTableAvailable()); // Corrected

			
			
			 model.addAttribute("successmessage", "Dining Table Updated Successfully");
			 
			dingingtablerepo.save(dingingtableupdate);
		}	
		
		return "redirect:/displayingingtables";
	}
	
	@GetMapping("deletedinngtable/{tableId}")
	public String deletediningtable(@PathVariable int tableId) {
		
		dingingtablerepo.deleteById(tableId);
		
		return "redirect:/displayingingtables";
		
	}

}
