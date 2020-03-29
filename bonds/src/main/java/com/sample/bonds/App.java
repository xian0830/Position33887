package com.sample.bonds;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sample.anagram.AnagramGroup;
import com.sample.bonds.utils.SecurityBondLogFileProcesser;

/**
 * Read Security Bonds Log App
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		System.out.println("-------- Output of CUSIP's closing prices -----------");
		String absFileName = App.class.getClassLoader().getResource("SecurityPriceLog").getPath();
		SecurityBondLogFileProcesser reader = new SecurityBondLogFileProcesser();

		try {
			reader.readFile(absFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n-------- Output of Anagram Groups -----------");
		AnagramGroup ag = new AnagramGroup();
		List<Set<String>> agResult = ag.getAnagramGroups("apt pat tap tome  mote toem");

		for (Set<String> grpSet : agResult) {
			Iterator<String> it = grpSet.iterator();
			while (it.hasNext()) {
				System.out.print(it.next() + " ");
			}
			System.out.println();
		}
    }
}
