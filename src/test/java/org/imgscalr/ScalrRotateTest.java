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

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.imgscalr.Scalr.Rotation;
import org.junit.jupiter.api.Test;

class ScalrRotateTest extends AbstractScalrTest {
	@Test
	void testRotateEx() {
	  assertThrows(IllegalArgumentException.class, ()-> Scalr.rotate(src, null));
	}

	@Test
	void testRotate90() {
		assertImgEquals(load("time-square-rotate-90.png"),
				Scalr.rotate(load("time-square.png"), Rotation.CW_90));
	}

	@Test
	void testRotate180() {
		assertImgEquals(load("time-square-rotate-180.png"),
				Scalr.rotate(load("time-square.png"), Rotation.CW_180));
	}

	@Test
	void testRotate270() {
		assertImgEquals(load("time-square-rotate-270.png"),
				Scalr.rotate(load("time-square.png"), Rotation.CW_270));
	}

	@Test
	void testRotateFlipH() {
		assertImgEquals(load("time-square-rotate-horz.png"),
				Scalr.rotate(load("time-square.png"), Rotation.FLIP_HORZ));
	}

	@Test
	void testRotateFlipV() {
		assertImgEquals(load("time-square-rotate-vert.png"),
				Scalr.rotate(load("time-square.png"), Rotation.FLIP_VERT));
	}

	@Test
	void testRotateFlipHOps() {
		assertImgEquals(load("time-square-rotate-horz-ops.png"),
				Scalr.rotate(load("time-square.png"), Rotation.FLIP_HORZ,
						Scalr.OP_GRAYSCALE));
	}
}
