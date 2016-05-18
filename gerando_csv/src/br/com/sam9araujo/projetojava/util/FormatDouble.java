package br.com.sam9araujo.projetojava.util;

import java.math.BigDecimal;

public class FormatDouble {

	public static BigDecimal truncateDecimal(final double d,
			final int numberofDecimals) {
		return new BigDecimal(String.valueOf(d)).setScale(numberofDecimals,
				BigDecimal.ROUND_DOWN);
	}

}
