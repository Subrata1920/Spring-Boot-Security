package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.CollectUserService;

/**
 * 
 * @author subrata
 *
 */

@Controller
public class HomeController {
	
	@Autowired
	private CollectUserService collectUserService;
	
	@GetMapping(value={"/", "/login"})
	public String home(){
		return "login";
	}
	
	@PreAuthorize("hasAuthority('State_level_user:state_soe_reports(view)')")
	@GetMapping(value = "/stateSOE")
	public String stateSOE() {
		return "statesoe";
	}

	@PreAuthorize("hasAuthority('NGO_level_user:ngo_report(view)')")
	@GetMapping(value="/ngoReport")
	public String ngoReport(){
		return "ngoreport";
	}
	
	@PreAuthorize("hasAuthority('District_level_user:district_report(view)')")
	@GetMapping(value="/districtSOE")
	public String districtSOE(){
		return "districtsoe";
	}
	
	@PreAuthorize("hasAuthority('NGO_level_user:ngo_soe_reports(view)')")
	@GetMapping(value = "/ngoSOE")
	public String ngoSOE() {
			return "ngosoe";
	}
	/**
	 * Giving authority using "hasAnyAuthority" for different level of user
	 * 
	 */
	@PreAuthorize("hasAnyAuthority('NGO_level_user:change_password(view)','State_level_user:change_password(view)','District_level_user:change_password(view)')")
//	@PreAuthorize("hasAuthority('NGO_level_user:change_password(view)') or hasAuthority('State_level_user:change_password(view)') or hasAuthority('District_level_user:change_password(view)')")
	@GetMapping("/changepassword")
	String changepassword(){
		return "changepassword"; 
	}
	
	@GetMapping(value="/Access_Denied")
	public String accessDenied(){
		return "Access_Denied";
	}
	
	@GetMapping(value={"/save"})
	public void save(){
		collectUserService.save();
	}
	

}
