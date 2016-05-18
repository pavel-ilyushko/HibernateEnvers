package my.simple.envers;

import java.util.List;

import my.simple.envers.base.BaseTestCase;
import org.junit.Ignore;
import org.junit.Test;

public class MyTest extends BaseTestCase {

	@Test
	@Ignore
	public void testEnversWithCustomQuery() throws Exception {

		MyA a = new MyA();
		a.setName("a-1");
		persist(a);

		commit();

		MyB b = new MyB();
		b.setName("b-1");
		persist(b);

		commit();

		a = find(a, a.getId());
		a.setName("a-2");
		em().merge(a);

		commit();

		b = find(b, b.getId());
		b.setMyA(a);
		b.setName("b-2");
		em().merge(b);

		commit();


//		final List<Number> revisions = reader().getRevisions(MyA.class, a.getId());
//		revisions.stream().forEach(System.out::println);
//
//		List<MyA_AUD1> myA_aud1s = em().createQuery("Select a From MyA_AUD1 a",
//				MyA_AUD1.class).getResultList();
//
//		myA_aud1s.stream().forEach(System.out::println);
//
//		List<MyB_AUD1> myB_aud1s = em().createQuery("Select b From MyB_AUD1 b",
//				MyB_AUD1.class).getResultList();
//
//		myB_aud1s.stream().forEach(System.out::println);

		//Assert.assertEquals(4, reader.getRevisions(Project.class,project.getId()).size());
	}
}
