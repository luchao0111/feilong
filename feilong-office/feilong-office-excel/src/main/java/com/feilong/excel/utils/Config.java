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
package com.feilong.excel.utils;

import static com.feilong.core.util.ResourceBundleUtil.toMap;

import java.util.Map;

/**
 * The Class Config.
 */
public class Config{

    /** The Constant LOXIA_EXCEL_MAP. */
    private static final Map<String, String> LOXIA_EXCEL_MAP = toMap("config/excel/support", "loxia/support-default");

    //---------------------------------------------------------------

    /** Don't let anyone instantiate this class. */
    private Config(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //---------------------------------------------------------------

    /**
     * 获得.
     *
     * @param name
     *            the name
     * @return the string
     */
    public static String get(String name){
        return LOXIA_EXCEL_MAP.get(name);
    }

}
