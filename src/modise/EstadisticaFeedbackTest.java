package modise;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EstadisticaFeedbackTest {

	@Test(expected = ArithmeticException.class)
	public void testDivPorCero() {
		EstadisticaFeedback.mediaPuntuacion(5, 0);
	}

	@Test
	public void testMediaPuntuacion() {
		System.out.println("testMediaPuntuacion");
		double expected = EstadisticaFeedback.mediaPuntuacion(4, 2);
		double actual = 4 / 2;
		double delta = 0; // difference
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testSiNO() {
		System.out.println("testSiNo");
		double a = 3;
		double b = 5;
		double c = 8;
		double delta = 0; // difference
		EstadisticaFeedback.siNo(a, b, c);
		assertEquals(a + b, c, delta);
	}

}
