package com.sample.anagram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnagramGroupTest {
	private AnagramGroup agGroup;
	private String words;

	@Before
	public void init() {
		words = "apt pat tap tome  mote toem";
		agGroup = new AnagramGroup();
	}

	@Test
	public void TestGetAnagramGroups() {
		List<Set<String>> result = agGroup.getAnagramGroups(words);

		assertEquals(2, result.size());
		assertTrue(result.get(0).contains("mote"));
		assertTrue(result.get(0).contains("tome"));
		assertTrue(result.get(0).contains("toem"));

		assertTrue(result.get(1).contains("apt"));
		assertTrue(result.get(1).contains("pat"));
		assertTrue(result.get(1).contains("tap"));
	}

	@After
	public void post() {

	}

}
