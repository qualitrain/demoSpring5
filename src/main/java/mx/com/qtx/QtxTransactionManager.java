package mx.com.qtx;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class QtxTransactionManager extends DataSourceTransactionManager {

	private static final long serialVersionUID = 1L;
	private static boolean on = false;
	
	public QtxTransactionManager(DataSource dataSource) {
		super(dataSource);
		if(QtxTransactionManager.on)
		   System.out.println("***** QtxTransactionManager instanciado *****");
	}
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
		if(QtxTransactionManager.on)
     		System.out.println("***** QtxTransactionManager.doBegin() name:" + definition.getName() + ", :" + definition.toString()
				+ " *****");
	}
	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		super.doCommit(status);
		if(QtxTransactionManager.on)
		   System.out.println("***** QtxTransactionManager.doCommit() *****");
	}
	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		super.doRollback(status);
		if(QtxTransactionManager.on)
		   System.out.println("***** QtxTransactionManager.doRollback(" 
				+ ") *****");
	}
}
