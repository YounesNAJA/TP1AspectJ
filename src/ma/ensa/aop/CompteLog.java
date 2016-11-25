package ma.ensa.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ma.ensa.model.Client;

@Aspect
public class CompteLog {
	/*
	 * Debut de l'execution
	 */
	private long start;
	
	/*
	 * Debut de l'execution
	 */
	private Long tempsExecution;

	Logger rootLogger = Logger.getRootLogger();

	/*
	 * Pointcut pour la méthode debiter()
	 */
	@Pointcut("call(void *..*.Compte.debiter(double))")
	public void debit() {
	};

	/*
	 * Advice @Around
	 */
	@Around("debit()")
	public Object aroundDebit(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		start = System.currentTimeMillis();
		Client client = (Client) thisJoinPoint.getThis();

		/*
		 * Test si le paramètre (Montant) est supérieur au solde du compte
		 */
		if (client.getCp().getSolde() < (double) thisJoinPoint.getArgs()[0]) {
			throw new CompteException("Solde insuffisant.");
		} else {
			Object result = thisJoinPoint.proceed();
			tempsExecution = System.currentTimeMillis() - start;
			return result;
		}
	}
	
	/*
	 * Si l'execution de debit() lève une exception
	 */
	@AfterThrowing("debit()")
	public void afterThrowingDebit(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		rootLogger.fatal("Un problème est survenu lors de l'opération de retrait de " + thisJoinPoint.getArgs()[0] + " sur le compte de : " + client.getNom());
		rootLogger.fatal("Le solde actuel : " + client.getCp().getSolde());
	}

	/*
	 * Si l'execution de debit() ne lève aucune exception
	 */
	@AfterReturning("debit()")
	public void afterDebit(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		rootLogger.fatal("Retrait de " + thisJoinPoint.getArgs()[0] + " sur le compte de : " + client.getNom() + ". Opération effectuée avec succès. (" + tempsExecution + "ms)");
		rootLogger.fatal("Nouveau solde : " + client.getCp().getSolde());
	}

	/*
	 * Approvisionnement
	 */
	@Pointcut("call(void *..*.Compte.approvisionner(double))")
	public void approvisionner() {
	};

	@Around("approvisionner()")
	public Object aroundApprovisionner(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		start = System.currentTimeMillis();
		Object result = thisJoinPoint.proceed();
		tempsExecution = System.currentTimeMillis() - start;
		return result;
	}
	
	@AfterThrowing("approvisionner()")
	public void afterThrowingApprovisionner(JoinPoint thisJoinPoint) {
		rootLogger.fatal("Un problème est survenu lors de l'opération d'approvisionnement.");
	}

	@AfterReturning("approvisionner()")
	public void afterApprovisionner(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		rootLogger.fatal("Approvisionnement de " + thisJoinPoint.getArgs()[0] + " sur le compte de : " + client.getNom() + ". Opération effectuée avec succès. (" + tempsExecution + "ms)");
		rootLogger.fatal("Nouveau solde : " + client.getCp().getSolde());
	}
}
