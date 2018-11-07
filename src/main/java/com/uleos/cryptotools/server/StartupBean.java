package com.uleos.cryptotools.server;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class StartupBean {

	private static final Logger LOG = LoggerFactory.getLogger(StartupBean.class);

	@Inject
	JeeDBMigrator dbMigrator;

	@PostConstruct
	private void startup() {
		LOG.info("Startup called.");
		dbMigrator.migrate();
		LOG.info("migration finished!");
	}

}
