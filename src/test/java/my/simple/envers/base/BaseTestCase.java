package my.simple.envers.base;

import static java.lang.String.format;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.After;
import org.junit.Before;

@SuppressWarnings("unchecked")
public class BaseTestCase {

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

	private EntityManager em;

	@Before
	public void setUp() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	@After
	public void tearDown() {
		em.getTransaction().commit();
		em.close();
	}

	protected void commit() {
		em.getTransaction().commit();
		System.out.println("-----------------------------------------------------------");
		em.clear();
		em.getTransaction().begin();
	}

	protected <T> List<T> readAll(Class<T> clazz) {
		return em.createQuery(format("from %s", clazz.getName())).getResultList();
	}

	protected <T > T find(T value, Long id) {
		return (T) em.find(value.getClass(), id);
	}

	protected <T> T persist(T value) {
		em.persist(value);
		return value;
	}

	protected EntityManager em() {
		return em;
	}
	
	protected <T> Class<T> revClass(Class<T> clazz) {
		try {
			return (Class<T>) Class.forName(format("%srev", clazz.getName()));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	protected <T> List<Number> revisions(T value, Long id) {
		return reader().getRevisions(value.getClass(), id);
	}

	protected AuditReader reader() {
		return AuditReaderFactory.get(em);
	}
}