package ma.ensa.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import ma.ensa.model.Client;

@Aspect
public class CompteLog {
	private long start;
	private Long tempsExecution;

	@Pointcut("call(void *..*.Compte.debiter(double))")
	public void debit() {
	};

	@Before("debit()")
	public void beforeDebit(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		System.out.print("Retrait de " + thisJoinPoint.getArgs()[0] + " sur le compte de : " + client.getNom());
	}

	@Around("debit()")
	public Object aroundDebit(ProceedingJoinPoint thisJoinPoint) {
		start = System.currentTimeMillis();
		Object result = thisJoinPoint.proceed();
		tempsExecution = System.currentTimeMillis() - start;
		System.out.println(" - " + tempsExecution + "ms.");
		return result;
	}

	@After("debit()")
	public void afterDebit(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		/*
		 * Est-ce la bonne méthode ?
		 */
		System.out.println("Nouveau solde : " + client.getCp().getSolde());
	}

	/*
	 * Approvisionnement
	 */
	@Pointcut("call(void *..*.Compte.approvisionner(double))")
	public void approvisionner() {
	};

	@Before("approvisionner()")
	public void beforeApprovisionner(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		System.out.print("Approvisionnement de " + thisJoinPoint.getArgs()[0] + " sur le compte de : " + client.getNom());
	}

	@Around("approvisionner()")
	public Object aroundApprovisionner(ProceedingJoinPoint thisJoinPoint) {
		start = System.currentTimeMillis();
		Object result = thisJoinPoint.proceed();
		tempsExecution = System.currentTimeMillis() - start;
		System.out.println(" - " + tempsExecution + "ms.");
		return result;
	}

	@After("approvisionner()")
	public void afterApprovisionner(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		System.out.println("Nouveau solde : " + client.getCp().getSolde());
	}
}
