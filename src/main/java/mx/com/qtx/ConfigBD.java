package mx.com.qtx;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class ConfigBD {
	private static final String NOMBRE_BD = "testspring5";
	private static final String URL_BD = "jdbc:mysql://localhost:3306/"
			+ NOMBRE_BD
			+ "?serverTimezone=UTC"; 
	private static final String USER_BD = "root"; 
	private static final String PASSWD_BD = "root"; 
	
	public ConfigBD() {
		System.out.println("********* ConfigBD() *********");
	}
	
	@Bean
	public DataSource getDataSource() {
		 return DataSourceBuilder
				 	.create()
				 	.url(URL_BD)
		 			.username(USER_BD)
		 			.password(PASSWD_BD)
		 			.build();
	}
	@Bean("transactionManager")
	@Autowired
	public DataSourceTransactionManager getGestorTransacciones(DataSource dataSource) {
		return new QtxTransactionManager(dataSource);
	}

}
