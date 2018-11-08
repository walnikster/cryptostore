FROM uleos/payara5-conf
MAINTAINER niki@uleos.com
COPY target/cryptostore.war ${DEPLOYMENT_DIR}