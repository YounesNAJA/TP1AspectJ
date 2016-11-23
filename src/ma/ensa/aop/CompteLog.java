package ma.ensa.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import ma.ensa.model.Client;

@Aspect
public class CompteLog {

	@Pointcut("call(void *..*.Compte.debiter(double))")
	public void debit() {
	};

	@Before("debit()")
	public void beforeRetrait(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		System.out.println("Retrait sur le compte de : " + client.getNom());
		System.out.println("Before");
	}

	@After("debit()")
	public void afterRetrait(JoinPoint thisJoinPoint) {
		Client client = (Client) thisJoinPoint.getThis();
		/*
		 * Est-ce la bonne méthode ?
		 */
		System.out.println("Nouveau solde : " + client.getCp().getSolde());
		System.out.println("After");
	}
}
