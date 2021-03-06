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
package com.feilong.namespace.parser;

import static com.feilong.namespace.BeanDefinitionParserUtil.addPropertyValue;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.w3c.dom.Element;

/**
 * The Class CookieEntityBeanDefinitionBuilderBuilder.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 2.0.0
 */
public class CookieEntityBeanDefinitionBuilderBuilder{

    /** Don't let anyone instantiate this class. */
    private CookieEntityBeanDefinitionBuilderBuilder(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //---------------------------------------------------------------

    /**
     * Builds the.
     *
     * @param element
     *            the element
     * @return the bean definition builder
     */
    public static BeanDefinitionBuilder build(Element element){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                        .genericBeanDefinition("com.feilong.servlet.http.entity.CookieEntity");

        addPropertyValue(element, beanDefinitionBuilder, "name", true);

        addPropertyValue(
                        element,
                        beanDefinitionBuilder, //
                        "version",
                        "httpOnly",
                        "secure",
                        "path",
                        "domain",
                        "comment",
                        "maxAge");
        return beanDefinitionBuilder;
    }
}
