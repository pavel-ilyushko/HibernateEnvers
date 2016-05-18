package my.simple.envers;

import java.util.List;

import my.simple.envers.base.BaseTestCase;
import org.junit.Ignore;
import org.junit.Test;

public class MyTest extends BaseTestCase {

	@Test
	@Ignore
	public void testEnversWithCustomQuery() {

		MyA a = new MyA();
		a.setName("a-1");
		persist(a);

		MyB b = new MyB();
		b.setName("b-1");
		b.setMyA(a);
		persist(b);

		commit();

		a = find(a, a.getId());
		a.setName("a-2");
		em().merge(a);


		final List<Number> revisions = reader().getRevisions(MyA.class, a.getId());

		for (Number rev : revisions) {
			System.out.println(rev);
		}

		List<MyA_AUD1> myA_aud1s = em().createQuery("Select a From MyA_AUD1 a",
				MyA_AUD1.class).getResultList();

		for (MyA_AUD1 myA_aud1 : myA_aud1s) {
			System.out.println(myA_aud1);
		}

		List<MyB_AUD1> myB_aud1s = em().createQuery("Select b From MyB_AUD1 b",
				MyB_AUD1.class).getResultList();

		for (MyB_AUD1 myB_aud1 : myB_aud1s) {
			System.out.println(myB_aud1);
		}

		//Assert.assertEquals(4, reader.getRevisions(Project.class,project.getId()).size());
	}
}
