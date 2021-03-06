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
package com.feilong.formatter.builder;

import static java.lang.Math.max;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.feilong.core.bean.ConvertUtil;

/**
 * 列的最大宽度构建.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.10.4
 */
public final class ColumnMaxWidthsBuilder{

    /** Don't let anyone instantiate this class. */
    private ColumnMaxWidthsBuilder(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //---------------------------------------------------------------

    /**
     * 定位每列最大的宽度.
     *
     * @param rows
     *            the rows
     * @return the int[]
     */
    public static int[] build(List<Object[]> rows){
        //最大列数
        int columnCount = -1;
        for (Object[] row : rows){
            columnCount = max(columnCount, row.length);
        }

        //---------------------------------------------------------------
        int[] columnMaxWidths = new int[columnCount];
        for (Object[] row : rows){
            for (int colNum = 0; colNum < row.length; colNum++){
                //双层循环,定位每列最大的宽度
                columnMaxWidths[colNum] = max(columnMaxWidths[colNum], StringUtils.length(ConvertUtil.toString(row[colNum])));
            }
        }

        //---------------------------------------------------------------
        return columnMaxWidths;
    }
}
