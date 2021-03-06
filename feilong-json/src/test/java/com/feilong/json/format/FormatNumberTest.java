/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.json.format;

import org.junit.Test;

import com.feilong.json.AbstractJsonTest;
import com.feilong.json.JsonUtil;

public class FormatNumberTest extends AbstractJsonTest{

    /**
     * Test json string2.
     */
    @Test
    @SuppressWarnings("static-method")
    public void testJsonString2(){
        LOGGER.debug(JsonUtil.format(1L));
        LOGGER.debug(JsonUtil.format(1));
    }
}
