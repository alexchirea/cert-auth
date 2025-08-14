package ro.alxchr.certauthapp.security;

import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;

import java.security.cert.X509Certificate;

public interface CertificatePrincipalExtractor extends X509PrincipalExtractor {
    Object extractPrincipal(X509Certificate cert);
}
