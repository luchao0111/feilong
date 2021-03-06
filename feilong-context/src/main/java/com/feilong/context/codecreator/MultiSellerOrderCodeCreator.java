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
package com.feilong.context.codecreator;

/**
 * 多卖家的平台订单号创建接口.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.12.9
 */
public interface MultiSellerOrderCodeCreator extends OrderCodeCreator{

    /**
     * 创建订单号.
     * 
     * @param buyerId
     *            买家id
     * @param sellerId
     *            卖家id
     * @return 订单号
     */
    String create(Long buyerId,Long sellerId);

}
