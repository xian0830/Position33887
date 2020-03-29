package com.sample.anagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

public class AnagramGroup {
	public List<Set<String>> getAnagramGroups(String words) {

		if (StringUtils.isEmpty(words)) {
			return null;
		}

		Map<String, Set<String>> anagramGroups = new HashMap<>();
		String[] wordTokens = words.split("\\b");

		Arrays.asList(wordTokens).stream().sorted().filter(s -> !StringUtils.isEmpty(s) && s.trim().length() > 0)
				.forEach(t -> {
			String key = getKeyByCharFrequency(t);
			Set<String> group = anagramGroups.get(key);

			if (group == null) {
				group = new HashSet<>();
				anagramGroups.put(key, group);
			}

			group.add(t);
		});

		return anagramGroups.values().stream().collect(Collectors.toList());
	}

	private String getKeyByCharFrequency(String word) {
		int[] frequencies = new int[26];
		int a = 'a';
		word.chars().forEach(i -> frequencies[(i - a)]++);

		StringBuilder keyBuilder = new StringBuilder();
		IntStream.of(frequencies).forEach(ch -> keyBuilder.append(ch));
		;

		return keyBuilder.toString();
	}
}
