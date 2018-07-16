/*
 * Copyright 2018 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.jib.registry;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;

public class RegistryAliasGroupTest {

  @Test
  public void testGetAliasesGroup_noKnownAliases() {
    ImmutableList<String> singleton = RegistryAliasGroup.getAliasesGroup("something.gcr.io");
    Assert.assertEquals(1, singleton.size());
    Assert.assertEquals("something.gcr.io", singleton.get(0));
  }

  @Test
  public void testGetAliasesGroup_registryHubDockerCom() {
    Assert.assertArrayEquals(
        RegistryAliasGroup.getAliasesGroup("registry.hub.docker.com").toArray(new String[0]),
        new String[] {"registry.hub.docker.com", "index.docker.io"});
  }

  @Test
  public void testGetAliasesGroup_indexDockerIo() {
    Assert.assertArrayEquals(
        RegistryAliasGroup.getAliasesGroup("index.docker.io").toArray(new String[0]),
        new String[] {"index.docker.io", "registry.hub.docker.com"});
  }
}
