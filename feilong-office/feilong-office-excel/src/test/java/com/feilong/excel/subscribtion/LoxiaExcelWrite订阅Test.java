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
package com.feilong.excel.subscribtion;

import static com.feilong.core.bean.ConvertUtil.toArray;
import static com.feilong.core.bean.ConvertUtil.toMap;
import static com.feilong.core.util.CollectionsUtil.newArrayList;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.feilong.excel.AbstractLoxiaWriteTest;

public class LoxiaExcelWrite订阅Test extends AbstractLoxiaWriteTest{

    @Test
    public void test(){
        String configurations = "loxia/subscribtion/feilong-sheets-2订阅.xml";
        String templateFileName = "subscribtion/template-list-2sheet.xlsx";

        //---------------------------------------------------------------
        List<SubscribtionCommand> alist = buildAList();
        List<SubscribtionCommand> blist = buildBList();

        Map<String, Object> map = toMap("alist", (Object) alist, "blist", blist);
        build(templateFileName, configurations, toArray("aSheet", "bSheet"), map);
    }

    //---------------------------------------------------------------

    private List<SubscribtionCommand> buildAList(){
        List<SubscribtionCommand> list = newArrayList();

        for (int i = 1; i <= 100; ++i){
            SubscribtionCommand subscribtionCommand = new SubscribtionCommand();
            subscribtionCommand.setEmail("xin.jin" + i + "@**.com");
            subscribtionCommand.setMobile("15001841318" + i);
            list.add(subscribtionCommand);
        }
        return list;
    }

    private List<SubscribtionCommand> buildBList(){
        List<SubscribtionCommand> list = newArrayList();

        for (int i = 1; i <= 100; ++i){
            SubscribtionCommand subscribtionCommand = new SubscribtionCommand();
            subscribtionCommand.setEmail("xin.jin" + i + "@**.com");
            subscribtionCommand.setMobile("15001841318" + i);
            list.add(subscribtionCommand);
        }
        return list;
    }
}
