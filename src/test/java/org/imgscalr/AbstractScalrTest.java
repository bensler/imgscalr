/**
 * Copyright 2011 Riyad Kalla
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.imgscalr;

import static org.junit.jupiter.api.AssertionFailureBuilder.assertionFailure;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

abstract class AbstractScalrTest {

  protected static BufferedImage src;

	@BeforeAll
	static void setup() {
		src = load("time-square.png");
	}

	@AfterAll
	public static void tearDown() {
		src.flush();
	}

	protected static BufferedImage load(String name) {
		try {
			return ImageIO.read(AbstractScalrTest.class.getResource(name));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void checkSinglePixel(Color origColor, Color tmpColor , AtomicInteger alphaDiff, List<Integer> colorDiffs) {
    if (!origColor.equals(tmpColor)) {
      if (origColor.getAlpha() != tmpColor.getAlpha()) {
        alphaDiff.incrementAndGet();
      }
      colorDiffs.add(
          Math.abs(origColor.getRed()   - tmpColor.getRed())
        + Math.abs(origColor.getGreen() - tmpColor.getGreen())
        + Math.abs(origColor.getBlue()  - tmpColor.getBlue())
      );
    }
  }

	protected static void assertImgEquals(BufferedImage orig, BufferedImage tmp) {
	  final int w = orig.getWidth();
	  final int h = orig.getHeight();
	  final int pxCount = w * h;
	  final AtomicInteger alphaDiff = new AtomicInteger();
	  final List<Integer> colorDiffs = new ArrayList<>(pxCount);

		assertEquals(w, tmp.getWidth());
		assertEquals(h, tmp.getHeight());

		// Ensure every RGB pixel value is the same.
		IntStream.range(0, w).forEach(x -> IntStream.range(0, h).forEach(y -> checkSinglePixel(
	    new Color(orig.getRGB(x, y)), new Color(tmp.getRGB(x, y)), alphaDiff, colorDiffs
    )));
		if (colorDiffs.size() > 0) {
		  final Map<Integer, Integer> colorDiffHistogram = colorDiffs.stream().collect(Collectors.toMap(intValue -> intValue, intValue -> 1, (v1, v2) -> v1 + v2));
		  assertionFailure().message(
	      "\n%s of %s pixels are different (in alpha: %s)\nrgb distance count:\n%s".formatted(
          colorDiffs.size(), pxCount, alphaDiff.get(),
          colorDiffHistogram.entrySet().stream().sorted(
            (e1, e2) -> e1.getKey().compareTo(e2.getKey())
          ).map(Entry::toString).collect(Collectors.joining("\n"))
        )
      ).buildAndThrow();
		}
	}

}
