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
package com.feilong.component;

import static com.feilong.core.Validator.isNullOrEmpty;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.context.Data;
import com.feilong.context.DataListQuery;
import com.feilong.context.FileReworker;
import com.feilong.context.Task;
import com.feilong.context.filecreator.ListDataFileCreator;
import com.feilong.context.log.UseTimeLogable;
import com.feilong.core.bean.BeanUtil;
import com.feilong.json.JsonUtil;
import com.feilong.net.mail.DefaultMailSender;
import com.feilong.net.mail.MailSender;
import com.feilong.net.mail.entity.MailSenderConfig;

/**
 * 这是一个一体化 数据{@code -->}转成Excel/CVS{@code -->}保存文件到硬盘{@code -->}变成附件发邮件给相关人员 的组件.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @param <T>
 *            the generic type
 * @since 2.1.0
 */
public class DataFileEmailComponent<T extends Data> implements Task<Void>,UseTimeLogable,Component{

    /** The Constant LOGGER. */
    private static final Logger    LOGGER     = LoggerFactory.getLogger(DataFileEmailComponent.class);

    //---------------------------------------------------------------

    /** 专门用来查询数据的接口. */
    private DataListQuery<T>       dataListQuery;

    /** 文件创造器. */
    private ListDataFileCreator<T> listDataFileCreator;

    /** The file reworker. */
    private FileReworker           fileReworker;

    //---------------------------------------------------------------

    /** The mail sender. */
    private MailSender             mailSender = new DefaultMailSender();

    //---------------------------------------------------------------

    /** The mail sender config. */
    private MailSenderConfig       mailSenderConfig;

    //---------------------------------------------------------------

    /** Post construct. */
    @PostConstruct
    protected void postConstruct(){
        Validate.notNull(mailSender, "mailSender can't be null!");
        Validate.notNull(mailSenderConfig, "mailSenderConfig can't be null!");
    }

    //---------------------------------------------------------------

    /**
     * Run.
     *
     * @return the void
     */
    @Override
    public Void run(){
        //一体化 数据-->转成Excel/CVS-->保存文件到硬盘-->变成附件发邮件给相关人员 的组件
        //-------------1.数据--------------------------------------------------
        List<T> dataList = dataListQuery.query();
        if (isNullOrEmpty(dataList)){
            LOGGER.info("dataList is null or empty ,nothing to do");
            return null;
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug(JsonUtil.format(dataList));
        }
        //--------------2.转成Excel/CVS-------------------------------------------------
        //文件生成
        String filePath = listDataFileCreator.create(dataList);
        Validate.notBlank(filePath, "filePath can't be blank!");

        //---------------3.压缩------------------------------------------------

        String attachFilePath = filePath;
        if (null != fileReworker){
            String outputFile = fileReworker.rework(filePath);
            attachFilePath = outputFile;
        }

        //----------------4.变成附件发邮件给相关人员 的组件---------------------------------
        sendEmail(attachFilePath, dataList);
        return null;
    }

    /**
     * 4.变成附件发邮件给相关人员 的组件
     *
     * @param filePath
     *            the file path
     * @param dataList
     *            the data list
     * @since 2.1.0
     */
    private void sendEmail(String filePath,List<T> dataList){
        //避免影响到元数据
        MailSenderConfig useMailSenderConfig = BeanUtil.cloneBean(mailSenderConfig);
        //变成附件发邮件给相关人员的组件
        useMailSenderConfig.setAttachFilePaths(filePath);
        mailSender.sendMail(useMailSenderConfig);
    }

    //---------------------------------------------------------------

    /**
     * Sets the data list query.
     *
     * @param dataListQuery
     *            the dataListQuery to set
     */
    public void setDataListQuery(DataListQuery<T> dataListQuery){
        this.dataListQuery = dataListQuery;
    }

    /**
     * Sets the mail sender.
     *
     * @param mailSender
     *            the mailSender to set
     */
    public void setMailSender(MailSender mailSender){
        this.mailSender = mailSender;
    }

    /**
     * Sets the mail sender config.
     *
     * @param mailSenderConfig
     *            the mailSenderConfig to set
     */
    public void setMailSenderConfig(MailSenderConfig mailSenderConfig){
        this.mailSenderConfig = mailSenderConfig;
    }

    /**
     * 设置 文件创造器.
     *
     * @param listDataFileCreator
     *            the listDataFileCreator to set
     */
    public void setListDataFileCreator(ListDataFileCreator<T> listDataFileCreator){
        this.listDataFileCreator = listDataFileCreator;
    }

    /**
     * Sets the file reworker.
     *
     * @param fileReworker
     *            the fileReworker to set
     */
    public void setFileReworker(FileReworker fileReworker){
        this.fileReworker = fileReworker;
    }

}
