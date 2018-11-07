FROM uleos/payara5-configured
MAINTAINER niki@uleos.com
COPY target/cryptostore.war ${DEPLOYMENT_DIR}