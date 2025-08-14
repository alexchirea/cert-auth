package ro.alxchr.certauthapp.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CertificateUserService extends UserDetailsService {
    CertificateUser getUserDetails();
}
