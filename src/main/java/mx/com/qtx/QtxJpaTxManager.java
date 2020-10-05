package mx.com.qtx;

import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class QtxJpaTxManager extends JpaTransactionManager {
	private static final long serialVersionUID = 1L;
	private static boolean on = true;
	
	private EntityManagerFactory unEmf;

	public QtxJpaTxManager(EntityManagerFactory emf) {
		super(emf);
		this.unEmf = emf;
		if(QtxJpaTxManager.on) {
		    System.out.println("***** QtxJpaTxManager instanciado *****");
		}
	}
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
		if(QtxJpaTxManager.on) {
     		System.out.println("***** QtxJpaTxManager.doBegin() name:" + definition.getName() + ", :" + definition.toString()
				+ " *****");
		}
	}
	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		super.doCommit(status);
		if(QtxJpaTxManager.on) {
		   System.out.println("***** QtxJpaTxManager.doCommit():" + status.getClass().getName()
		   		+ " *****");
		}
	}
	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		super.doRollback(status);
		if(QtxJpaTxManager.on) {
		   System.out.println("***** QtxJpaTxManager.doRollback(" 
				+ ") *****");
		}
	}
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		if(QtxJpaTxManager.on) {
			   System.out.println("***** QtxJpaTxManager.doCleanupAfterCompletion(" 
					+ ") *****");
			}
	}
	

}
