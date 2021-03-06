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
package com.feilong.core.util.comparator.propertycomparator;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.feilong.core.util.comparator.PropertyComparator;
import com.feilong.store.member.UserSameHashCodeWithComparable;

public class PropertyNameAndValueConvertToClassTest{

    @Test
    public void testPropertyComparatorWithTreeSetWithComparable(){
        UserSameHashCodeWithComparable userSameHashCodeWithComparable1 = new UserSameHashCodeWithComparable(1, "11");
        UserSameHashCodeWithComparable userSameHashCodeWithComparable2 = new UserSameHashCodeWithComparable(5, "2");
        //------

        PropertyComparator<UserSameHashCodeWithComparable> propertyComparator = new PropertyComparator<>("name");
        assertThat(propertyComparator.compare(userSameHashCodeWithComparable1, userSameHashCodeWithComparable2), lessThan(0));

        propertyComparator = new PropertyComparator<>("name", Integer.class);
        assertEquals(1, propertyComparator.compare(userSameHashCodeWithComparable1, userSameHashCodeWithComparable2));
    }

    //----------------------------------------------------------------------------

    @Test(expected = NullPointerException.class)
    public void testPropertyComparatorNullPropertyNameAndPropertyValueConvertToClass(){
        new PropertyComparator<>(null, Integer.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPropertyComparatorEmptyPropertyNameAndPropertyValueConvertToClass(){
        new PropertyComparator<>("", Integer.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPropertyComparatorBlankPropertyNameAndPropertyValueConvertToClass(){
        new PropertyComparator<>("    ", Integer.class);
    }
}