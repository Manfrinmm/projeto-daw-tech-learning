package com.techlearning.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Session authentication") 
@RestController
@RequestMapping("/sessions")
public class SessionController {
	/*
	@Autowired
	AuthenticationManager authenticationManager;
	JwtTokenProvider tokenProvider;
	*/
	/*
	
	@Operation(summary = "Autentica um usuário e retorna um token")
	@PostMapping(value = "/assinatura")
	public ResponseEntity<?> assina(@RequestBody UserDTO objDTO) {
		try {
			
			String username = objDTO.getUsername();
			String password = objDTO.getPassword();
			
			System.out.println("/1/");
			System.out.println(username+" - "+password);
			System.out.println("/1/");
			
			User obj = dao.findByUsername(username);
			obj.setAccountNonExpired(true);
			obj.setAccountNonLocked(true);
			obj.setCredentialsNonExpired(true);
			obj.setEnabled(true);
			
			String token = "";
			
			if (obj.getUsername() != null) {
				token = tokenProvider.createToken(obj.getUsername(), obj.getRoles());
			} else {
				throw new UsernameNotFoundException("Usuário " + obj.getUsername() + " não encontrado!");
			}
			
			System.out.println("/2/");
			System.out.println(obj.isAccountNonLocked());
			System.out.println("/2/");
			
			UsernamePasswordAuthenticationToken tok = new UsernamePasswordAuthenticationToken(username, password);
			
			System.out.println("/3/");
			System.out.println(tok);
			System.out.println("/3/");
			
			authenticationManager.authenticate(tok);
				
			
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", obj.getUsername());
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}*/
}



