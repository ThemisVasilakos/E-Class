package net.themis.eclass.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.themis.eclass.config.CustomUserDetailService;
import net.themis.eclass.config.JwtUtil;
import net.themis.eclass.model.AuthenticationRequest;
import net.themis.eclass.model.AuthenticationResponse;
import net.themis.eclass.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;


    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value="/authenticate" ,method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (DisabledException e)
        {
            throw new Exception("USER_DISABLED",e);
        }
        catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailService.save(user));
    }

}
