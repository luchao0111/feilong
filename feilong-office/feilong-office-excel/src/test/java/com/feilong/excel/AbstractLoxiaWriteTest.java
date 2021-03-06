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

import static com.feilong.core.bean.ConvertUtil.toArray;
import static com.feilong.core.bean.ConvertUtil.toMap;
import static com.feilong.core.date.DateUtil.nowTimestamp;

import java.util.List;
import java.util.Map;

import com.feilong.coreextension.awt.DesktopUtil;
import com.feilong.excel.ExcelWriteUtil;
import com.feilong.io.FilenameUtil;
import com.feilong.test.AbstractTest;
import com.feilong.tools.slf4j.Slf4jUtil;

/**
 * The Class AbstractLoxiaExcelWriteTest.
 */
public abstract class AbstractLoxiaWriteTest extends AbstractTest{

    /**
     * Builds the.
     *
     * @param <T>
     *            the generic type
     * @param templateFileName
     *            the template file name
     * @param configurations
     *            the configurations
     * @param sheetName
     *            the sheet name
     * @param dataName
     *            the data name
     * @param list
     *            the list
     */
    protected static <T> void build(String templateFileName,String configurations,String sheetName,String dataName,List<T> list){
        Map<String, Object> beans = toMap(dataName, (Object) list);

        build(templateFileName, configurations, toArray(sheetName), beans);
    }

    /**
     * Builds the.
     *
     * @param templateFileName
     *            the template file name
     * @param configurations
     *            the configurations
     * @param sheetNames
     *            the sheet names
     * @param beans
     *            the beans
     */
    protected static void build(String templateFileName,String configurations,String[] sheetNames,Map<String, Object> beans){
        String outputFileName = Slf4jUtil.format(
                        "/Users/feilong/feilong/excel/{}{}.{}",
                        sheetNames,
                        nowTimestamp(),
                        FilenameUtil.getExtension(templateFileName));

        String excelTemplateLocation = "classpath:loxia/" + templateFileName;
        ExcelWriteUtil.write(excelTemplateLocation, configurations, sheetNames, beans, outputFileName);

        DesktopUtil.open(outputFileName);
    }

}
