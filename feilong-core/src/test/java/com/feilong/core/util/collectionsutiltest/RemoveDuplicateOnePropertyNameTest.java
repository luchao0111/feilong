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
package com.feilong.core.util.collectionsutiltest;

import static com.feilong.core.bean.ConvertUtil.toList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.feilong.core.util.CollectionsUtil;
import com.feilong.store.member.User;

public class RemoveDuplicateOnePropertyNameTest{

    @Test
    public void testRemoveDuplicate(){
        User user1 = new User(1L);
        User user2 = new User(1L);
        List<User> list = toList(user1, user2);

        List<User> removeDuplicate = CollectionsUtil.removeDuplicate(list, "id");

        assertThat(removeDuplicate, contains(user1));
        assertSame(1, removeDuplicate.size());
    }

    //---------------------------------------------------------------

    /**
     * Test remove duplicate null collection.
     */
    @Test
    public void testRemoveDuplicateNullCollection(){
        assertEquals(emptyList(), CollectionsUtil.removeDuplicate(null, (String) null));
    }

    @Test
    public void testRemoveDuplicateNullCollection1(){
        assertEquals(emptyList(), CollectionsUtil.removeDuplicate(null, ""));
    }

    //---------------------------------------------------------------

    @Test
    public void testRemoveDuplicateEmptyCollection(){
        assertEquals(emptyList(), CollectionsUtil.removeDuplicate(new ArrayList<>(), (String) null));
    }

    @Test
    public void testRemoveDuplicateEmptyCollection22(){
        assertEquals(emptyList(), CollectionsUtil.removeDuplicate(new ArrayList<>(), ""));
    }

    //---------------------------------------------------------------

    @Test
    public void testRemoveDuplicateEmptyCollection1(){
        assertEquals(emptyList(), CollectionsUtil.removeDuplicate(toList(), (String) null));
    }

    @Test
    public void testRemoveDuplicateEmptyCollection1222(){
        assertEquals(emptyList(), CollectionsUtil.removeDuplicate(toList(), ""));
    }

}
