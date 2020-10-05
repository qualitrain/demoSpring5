package mx.com.qtx;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
public class ConfigBD extends AbstractJdbcConfiguration{
	private static final String NOMBRE_BD = "testspring5";
	private static final String URL_BD = "jdbc:mysql://localhost:3306/"
			+ NOMBRE_BD
			+ "?serverTimezone=UTC"; 
	private static final String USER_BD = "root"; 
	private static final String PASSWD_BD = "root"; 
	
	public ConfigBD() {
		System.out.println("********* ConfigBD() *********");
	}
	
	@Bean("dataSource")
	public DataSource getDataSource() {
		DataSource dataSource =  DataSourceBuilder
				 	.create()
				 	.url(URL_BD)
		 			.username(USER_BD)
		 			.password(PASSWD_BD)
		 			.build();
		System.out.println("***** ConfigBD.getDataSource() clase utilizada:" + dataSource.getClass().getName()
				+ " *****");
		return dataSource;
	}
	
	@Primary
	@Bean("dataSourceProp")
	@ConfigurationProperties("qtx.datasource")
	public DataSource getDataSourceProp() {
		DataSource dataSource =  DataSourceBuilder
				 	.create()
		 			.build();
		System.out.println("***** ConfigBD.getDataSourceProp() clase utilizada:" + dataSource.getClass().getName()
				+ " *****");
		return dataSource;
	}
	
	@Bean("transactionManagerMon")
	public DataSourceTransactionManager getGestorTransaccionesDSmon(DataSource dataSource) {
		return new QtxTransactionManager(dataSource);
	}
	@Bean("transactionManager")
	public DataSourceTransactionManager getGestorTransaccionesDS(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean("gestorTransaccionesJPAmon")
	public QtxJpaTxManager getGestorTransaccionesJPAmon(EntityManagerFactory emf) {
		return new QtxJpaTxManager(emf);
	}
	@Bean("gestorTransaccionesJPA")
	@Primary
	public JpaTransactionManager getGestorTransaccionesJPA(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) { 
        return new NamedParameterJdbcTemplate(dataSource);
    }	

}
