package com.bosonit.formacion.block7CrudValidationRebuild.person.application;

import com.bosonit.formacion.block7CrudValidationRebuild.person.domain.Person;
import com.bosonit.formacion.block7CrudValidationRebuild.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class PersonUserDetailsService implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByPersonUser(username).orElseThrow(()->
                new UsernameNotFoundException("El usuario "+username+" no existe."));

        Collection<? extends GrantedAuthority> authorities = person.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName().name())).collect(Collectors.toSet());

        return new User(person.getPersonUser(),
                person.getPassword(),
                true, true, true, true,
                authorities);
    }
}
