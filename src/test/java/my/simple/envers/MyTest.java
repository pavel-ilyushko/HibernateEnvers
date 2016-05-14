package my.simple.envers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Test;

public class MyTest {

	@Test
	public void testEnversWithCustomQuery() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("myPU");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		MyA a = new MyA();
		a.setName("a-1");
		entityManager.persist(a);
		MyB b = new MyB();
		b.setName("b-1");
		b.setMyA(a);
		entityManager.persist(b);
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		a = entityManager.find(MyA.class, a.getId());
		a.setName("a-2");
		entityManager.merge(a);
		entityManager.getTransaction().commit();


		AuditReader reader = AuditReaderFactory.get(entityManager);
		final List<Number> revisions = reader.getRevisions(MyA.class, a.getId());

		for (Number rev : revisions) {
			System.out.println(rev);
		}


		entityManager.getTransaction().begin();
		List<MyA_AUD1> myA_aud1s = entityManager.createQuery("Select a From MyA_AUD1 a",
				MyA_AUD1.class).getResultList();

		for (MyA_AUD1 myA_aud1 : myA_aud1s) {
			System.out.println(myA_aud1);
		}
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		List<MyB_AUD1> myB_aud1s = entityManager.createQuery("Select b From MyB_AUD1 b",
				MyB_AUD1.class).getResultList();

		for (MyB_AUD1 myB_aud1 : myB_aud1s) {
			System.out.println(myB_aud1);
		}

		entityManager.getTransaction().commit();


		//Assert.assertEquals(4, reader.getRevisions(Project.class,project.getId()).size());
	}
}
