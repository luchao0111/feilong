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
package com.feilong.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * The Interface ExcelWriter.
 */
public interface ExcelWriter extends ExcelConfig{

    /**
     * Write.
     *
     * @param outputStream
     *            the os
     * @param beans
     *            the beans
     */
    void write(OutputStream outputStream,Map<String, Object> beans);

    /**
     * Write.
     *
     * @param template
     *            the template
     * @param outputStream
     *            the os
     * @param beans
     *            the beans
     */
    void write(String template,OutputStream outputStream,Map<String, Object> beans);

    /**
     * Write.
     *
     * @param inputStream
     *            the is
     * @param outputStream
     *            the os
     * @param beans
     *            the beans
     */
    void write(InputStream inputStream,OutputStream outputStream,Map<String, Object> beans);

    //---------------------------------------------------------------

    /**
     * Write per sheet.
     *
     * @param outputStream
     *            the os
     * @param beansList
     *            the beans list
     */
    void writePerSheet(OutputStream outputStream,List<Map<String, Object>> beansList);

    /**
     * Write per sheet.
     *
     * @param template
     *            the template
     * @param outputStream
     *            the os
     * @param beansList
     *            the beans list
     */
    void writePerSheet(String template,OutputStream outputStream,List<Map<String, Object>> beansList);

    //---------------------------------------------------------------

    /**
     * Write per sheet.
     *
     * @param inputStream
     *            the is
     * @param outputStream
     *            the os
     * @param beansList
     *            the beans list
     */
    void writePerSheet(InputStream inputStream,OutputStream outputStream,List<Map<String, Object>> beansList);

}
