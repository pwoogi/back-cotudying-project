package com.week06.cotudying_project.config.jwt;

import com.week06.cotudying_project.model.Member;
import com.week06.cotudying_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login => 여기서 동작을 안함 왜냐면 formLogin.disable() 해버려서
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memeberEntity = memberRepository.findByUsername(username);
        return new PrincipalDetails(memeberEntity);
    }
}
