/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.source.dash.manifest;

import static com.google.common.truth.Truth.assertThat;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase;
import com.google.android.exoplayer2.util.MimeTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/** Unit test for {@link Representation}. */
@RunWith(RobolectricTestRunner.class)
public class RepresentationTest {

  @Test
  public void testGetCacheKey() {
    String uri = "http://www.google.com";
    SegmentBase base = new SingleSegmentBase(new RangedUri(null, 0, 1), 1, 0, 1, 1);
    Format format =
        Format.createVideoContainerFormat(
            "0",
            MimeTypes.APPLICATION_MP4,
            null,
            MimeTypes.VIDEO_H264,
            2500000,
            1920,
            1080,
            Format.NO_VALUE,
            null,
            0);
    Representation representation =
        Representation.newInstance("test_stream_1", 3, format, uri, base);
    assertThat(representation.getCacheKey()).isEqualTo("test_stream_1.0.3");

    format =
        Format.createVideoContainerFormat(
            "150",
            MimeTypes.APPLICATION_MP4,
            null,
            MimeTypes.VIDEO_H264,
            2500000,
            1920,
            1080,
            Format.NO_VALUE,
            null,
            0);
    representation =
        Representation.newInstance(
            "test_stream_1", Representation.REVISION_ID_DEFAULT, format, uri, base);
    assertThat(representation.getCacheKey()).isEqualTo("test_stream_1.150.-1");
  }
}
