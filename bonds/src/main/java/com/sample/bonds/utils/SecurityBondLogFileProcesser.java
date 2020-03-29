package com.sample.bonds.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class SecurityBondLogFileProcesser implements ISecurityBondLogProcesser {
	private Pattern pricePattern = Pattern.compile("\\d+(\\.\\d+)?");
	
	private PrintStream outputStream;
	
	public SecurityBondLogFileProcesser() {
		outputStream = System.out;
	}

	public SecurityBondLogFileProcesser(PrintStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void readFile(String fileName) throws Exception {
		if (StringUtils.isEmpty(fileName)) {
			throw new Exception("File name can not be null.");
		}

		try (InputStream inputStream = new FileInputStream(fileName);
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferReader = new BufferedReader(inputStreamReader)) {

			String cusip = null;
			String price = null;

			String line = bufferReader.readLine();
			while (line != null) {
				if (isPrice(line)) {
					price = line;
				} else {
					// first line from the price log
					if (cusip == null) {
						cusip = line;
					} else {
						outputStream.println(cusip);
						outputStream.println(price);
						cusip = line;
					}
				}

				line = bufferReader.readLine();
			}

			// last CUSIP
			if (cusip != null) {
				outputStream.println(cusip);
				outputStream.println(price);
			}

		}
		

	}

	private boolean isPrice(String line) {
		Matcher matcher = pricePattern.matcher(line);
		return matcher.matches();
	}

}
