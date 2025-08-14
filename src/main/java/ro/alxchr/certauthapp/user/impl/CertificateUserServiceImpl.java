package ro.alxchr.certauthapp.user.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.alxchr.certauthapp.user.CertificateUser;
import ro.alxchr.certauthapp.user.CertificateUserService;

@Service
public class CertificateUserServiceImpl implements CertificateUserService {

    private static final String DEMO_USER_ID = "CN=AxC Dev Root CA,OU=AxC Dev CA,O=AxC Dev,L=Bucharest,ST=RO,C=RO|436843889351112246961156674827460765636710857508";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (DEMO_USER_ID.equals(username)) {
            return CertificateUser.builder()
                    .id(DEMO_USER_ID)
                    .commonName("[AxC-Dev] User X1")
                    .firstName("User")
                    .lastName("X1")
                    .email("user_x1@axc.dev")
                    .dnSerialNumber("AXC1001")
                    .serialNumber("436843889351112246961156674827460765636710857508")
                    .issuerDn("CN=AxC Dev Root CA,OU=AxC Dev CA,O=AxC Dev,L=Bucharest,ST=RO,C=RO")
                    .build();
        }
        throw new UsernameNotFoundException("Invalid username or password");
    }

    @Override
    public CertificateUser getUserDetails() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (CertificateUser) auth.getPrincipal();
    }
}
