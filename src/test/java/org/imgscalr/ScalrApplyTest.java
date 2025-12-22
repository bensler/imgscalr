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

import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.OP_BRIGHTER;
import static org.imgscalr.Scalr.OP_DARKER;
import static org.imgscalr.Scalr.OP_GRAYSCALE;
import static org.imgscalr.Scalr.apply;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImageOp;

import org.junit.jupiter.api.Test;

class ScalrApplyTest extends AbstractScalrTest {
	@Test
	void testApplyEX() {
		try {
			apply(src, (BufferedImageOp[]) null);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	void testApply1() {
	  assertImgEquals(load("time-square-apply-1.png"), apply(src, OP_ANTIALIAS));
	}

	@Test
	void testApply4() {
	  assertImgEquals(
				load("time-square-apply-4.png"),
				apply(src, Scalr.OP_ANTIALIAS, OP_BRIGHTER, OP_DARKER,
						OP_GRAYSCALE));
	}
}
