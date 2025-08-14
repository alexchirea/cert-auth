# CA Setup

## 1. Generate a Root CA
### Generate CA private key
```shell
openssl genrsa -out ca.key 4096
```

### Generate CA certificate (self-signed)
```shell
openssl req -x509 -new -nodes -key ca.key -sha256 -days 365 \
-out ca.crt -subj "/C=RO/ST=RO/L=Bucharest/O=AxC Dev/OU=AxC Dev CA/CN=AxC Dev Root CA"
```

## 2. Generate Server Key Pair & Certificate Signed by CA
### Server private key
```shell
openssl genrsa -out server.key 2048
```

### Server CSR (Certificate Signing Request)
```shell
openssl req -new -key server.key -out server.csr \
-subj "/C=RO/ST=RO/L=Bucharest/O=AxC Dev/OU=AxC Dev CA/CN=localhost"
```

### Sign server certificate with CA
```shell
openssl x509 -req -in server.csr -CA ca.crt -CAkey ca.key -CAcreateserial \
-out server.crt -days 365 -sha256
```

## 3. Create Server Keystore (PKCS#12)
```shell
openssl pkcs12 -export \
-in server.crt -inkey server.key -out server-keystore.p12 \
-name springboot-server -CAfile ca.crt -caname root \
-password pass:changeit
```

## 4. Create Truststore (Trusted CAs for client auth)
```shell
# Import CA certificate into a new Java truststore
keytool -importcert -trustcacerts -file ca.crt -alias example-ca \
-keystore server-truststore.p12 -storetype PKCS12 -storepass changeit -noprompt
```

## 5. Generate Client Certificate
### Client private key
```shell
openssl genrsa -out client.key 2048
```

### Client CSR
```shell
openssl req -new -key client.key -out client.csr \
-subj "/C=RO/ST=RO/L=Bucharest/CN=[AxC-Dev] User X1/givenName=User/surname=X1/emailAddress=user_x1@axc.dev/serialNumber=AXC1001"
```

### Sign client CSR with CA
```shell
openssl x509 -req -in client.csr -CA ca.crt -CAkey ca.key -CAcreateserial \
-out client.crt -days 365 -sha256
```

## Convert Client Cert + Key to PFX
```shell
openssl pkcs12 -export \
-in client.crt -inkey client.key \
-out client.pfx -name client-user-x1 -passout pass:changeit
```

