package ro.alxchr.certauthapp.security;

import java.security.cert.X509Certificate;

public class IssuerSerialPrincipalExtractor implements CertificatePrincipalExtractor {
    @Override
    public Object extractPrincipal(X509Certificate cert) {
        String issuerDn = cert.getIssuerX500Principal().getName();
        String serial = cert.getSerialNumber().toString();
        return issuerDn + "|" + serial;
    }
}
