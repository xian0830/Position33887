package com.sample.bonds;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sample.bonds.utils.ISecurityBondLogProcesser;
import com.sample.bonds.utils.SecurityBondLogFileProcesser;

@RunWith(MockitoJUnitRunner.class)
public class SecurityFileReaderTest {
	@Mock
	private PrintStream printStream = System.out;

	@Captor
	private ArgumentCaptor<String> lineContent;

	private ISecurityBondLogProcesser reader;

	@Before
	public void initTest() {
		reader = new SecurityBondLogFileProcesser(printStream);
	}

	@Test
	public void checkFinalResultAfterRead() {
		// get absolute file name of input file.
		String absFileName = App.class.getClassLoader().getResource("SecurityPriceLog").getPath();

		try {
			reader.readFile(absFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Mockito.verify(printStream, Mockito.times(22)).println(lineContent.capture());
		List<String> closingPricesByCUSIPs = lineContent.getAllValues();

		assertEquals("CUSIP100", closingPricesByCUSIPs.get(0));
		assertEquals("11.5", closingPricesByCUSIPs.get(1));

		assertEquals("CUSIP101", closingPricesByCUSIPs.get(2));
		assertEquals("23.5", closingPricesByCUSIPs.get(3));

		assertEquals("CUSIP102", closingPricesByCUSIPs.get(4));
		assertEquals("34.5", closingPricesByCUSIPs.get(5));

		assertEquals("CUSIP103", closingPricesByCUSIPs.get(6));
		assertEquals("9.78", closingPricesByCUSIPs.get(7));

		assertEquals("CUSIP104", closingPricesByCUSIPs.get(8));
		assertEquals("43.9", closingPricesByCUSIPs.get(9));

		assertEquals("CUSIP105", closingPricesByCUSIPs.get(10));
		assertEquals("20.9", closingPricesByCUSIPs.get(11));

		assertEquals("CUSIP106", closingPricesByCUSIPs.get(12));
		assertEquals("32.4", closingPricesByCUSIPs.get(13));

		assertEquals("CUSIP107", closingPricesByCUSIPs.get(14));
		assertEquals("31.7", closingPricesByCUSIPs.get(15));

		assertEquals("CUSIP108", closingPricesByCUSIPs.get(16));
		assertEquals("41.2", closingPricesByCUSIPs.get(17));

		assertEquals("CUSIP109", closingPricesByCUSIPs.get(18));
		assertEquals("60", closingPricesByCUSIPs.get(19));

		assertEquals("CUSIP110", closingPricesByCUSIPs.get(20));
		assertEquals("79.6", closingPricesByCUSIPs.get(21));
	}

	@After
	public void postTest() {

	}
}
