package mx.com.qtx;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

import com.zaxxer.hikari.HikariDataSource;

public class QtxTransactionManager extends DataSourceTransactionManager {
	private DataSource dataSource;
	private static final long serialVersionUID = 1L;
	private static boolean on = true;
	
	public QtxTransactionManager(DataSource dataSource) {
		super(dataSource);
		this.dataSource = dataSource;
		if(QtxTransactionManager.on) {
		    System.out.println("***** QtxTransactionManager instanciado *****");
			mostrarPoolConexiones(dataSource);
		}
	}
	private void mostrarPoolConexiones(DataSource dataSource) {
		if(dataSource instanceof HikariDataSource) {
			HikariDataSource hsd = (HikariDataSource) dataSource;
			System.out.println("***** Active Connections:" + hsd.getHikariPoolMXBean().getActiveConnections() + "*****");			
		}
	}
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
		if(QtxTransactionManager.on) {
     		System.out.println("***** QtxTransactionManager.doBegin() name:" + definition.getName() + ", :" + definition.toString()
				+ " *****");
     		mostrarPoolConexiones(dataSource);
		}
	}
	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		super.doCommit(status);
		if(QtxTransactionManager.on) {
		   System.out.println("***** QtxTransactionManager.doCommit():" + status.getClass().getName()
		   		+ " *****");
    		mostrarPoolConexiones(dataSource);
		}
	}
	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		super.doRollback(status);
		if(QtxTransactionManager.on) {
		   System.out.println("***** QtxTransactionManager.doRollback(" 
				+ ") *****");
    		mostrarPoolConexiones(dataSource);
		}
	}
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		if(QtxTransactionManager.on) {
			   System.out.println("***** QtxTransactionManager.doCleanupAfterCompletion(" 
					+ ") *****");
	    		mostrarPoolConexiones(dataSource);
			}
	}
	
}
