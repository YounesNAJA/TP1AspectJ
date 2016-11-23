package ma.ensa.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CompteLog {
	
	@Pointcut("execution(* *..*.Compte.debiter(double))")
	public void debit(){
		
	};
	
	@Before("debit()")
	public void beforeDebit(){
		System.out.println("Before");
	}
	
	@After("debit()")
	public void afterDebit(){
		System.out.println("After");
	}
}
