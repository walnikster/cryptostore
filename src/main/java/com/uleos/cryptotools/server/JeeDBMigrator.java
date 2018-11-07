package com.uleos.cryptotools.server;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class JeeDBMigrator {
	private static final Logger LOG = LoggerFactory.getLogger(JeeDBMigrator.class.getName());

	@Resource(name = "jdbc/h2Pool")
	private DataSource datasource;

	public void migrate() {

		if (datasource == null) {
			LOG.error("no datasource found to execute the db migrations!");
			throw new EJBException("no datasource found to execute the db migrations!");
		}
		Flyway flyway = Flyway.configure().dataSource(datasource).load();
		for (MigrationInfo i : flyway.info().all()) {
			LOG.info("migrate task: {}:{} from file: {}", i.getVersion(), i.getDescription(), i.getScript());
		}
		flyway.migrate();
		LOG.info("migration done");
	}
}
