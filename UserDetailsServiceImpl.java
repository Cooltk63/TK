package com.example.loginservice.service;

import com.example.loginservice.entity.User;
import com.example.loginservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Use loadByUserId ");
    }
    public UserDetails loadUserByUserId(int userId) throws UsernameNotFoundException {
        User user =  userRepository.findByUserId(userId);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        log.info("UserID::"+userId);
        //List<Map<String,Object>> temp = userRepository.getUserRole(userId);
       // log.info("UserRole::"+temp);
//        List<Map<String,Object>> temp =  userRepository.getUserRole(userId);
//        log.info("temp lenght::"+temp.size());
//        for(Map<String,Object> map : temp) {
//            log.info(map.toString());
//        }
//        Map<String,Object> userRoleInfo = temp.get(0);
        Map<String,Object> userRoleInfo = userRepository.getUserRole(user.getUserId());
        GrantedAuthority authority = new SimpleGrantedAuthority(userRoleInfo.get("role").toString());
        //GrantedAuthority authority = new SimpleGrantedAuthority("ROLE");
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getUserId()),user.getPasswordHash() ==null ? user.getPasswordHash() : "", List.of(authority));

    }
}
