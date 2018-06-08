package com.alipapa.smp.consumer.controller;

import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.enums.ConsumerScopeEnum;
import com.alipapa.smp.common.enums.FellowUpRulesEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 客户导入导出
 *
 * @author liuwei
 * @version 1.0
 * 2018-04-20
 */

@RestController
@CrossOrigin
@RequestMapping("/api/consumer")
public class FileDownloadController {
    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Value("${upload.fail.pathPrefix}")
    private String pathPrefix;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private UserConsumerRelationService userConsumerRelationService;

    @Autowired
    private UserService userService;

    /**
     * 管理人员可进行客户上载，上载客户可在公共资源池中查看
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public WebApiResponse<String> upload(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletResponse response) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (!"admin".equals(userInfo.getRoleName())) {
            return WebApiResponse.error("没有权限！");
        }

        try {
            if (multipartFile == null || multipartFile.isEmpty()) {
                return error("用户上传的文件必须有误");
            }

            String name = multipartFile.getOriginalFilename();
            long size = multipartFile.getSize();
            if (name == null || ExcelUtil.EMPTY.equals(name) && size == 0) {
                return error("用户上传的文件必须有误");
            }
            //读取Excel数据到List中
            //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：
            List<ArrayList<String>> list = new ExcelRead().readExcel(multipartFile);
            if (CollectionUtils.isEmpty(list)) {
                return error("文件内容为空");
            }

            //失败列表
            List<ArrayList<String>> failedList = new ArrayList<>();

            int total = 0;

            if (!CollectionUtils.isEmpty(list) && list.size() > 1) {
                for (int i = 0; i < list.size(); i++) {
                    ArrayList<String> consumerRowList = list.get(i);
                    //客户姓名	国籍	电子邮箱	主营业务	客户来源（展会/IFQ/访客营销/阿里询盘/地推/转介绍/公司官网/社交软件/6688）	客户类型（零售商/批发商/贸易商/品牌商）	客户级别（A/B/C/D/E）	客户意向	数量
                    // 公司地址	公司网址	联系方式1	联系方式2	联系方式3	Whatapp	Wechat	Facebook	QQ	Skype	LinkedIn	收货人	联系电话	邮政编码	收货地址	失败原因

                    //必填字段
                    String consumerName = consumerRowList.get(0);
                    String country = consumerRowList.get(1);
                    String email = consumerRowList.get(2);
                    String mainBusiness = consumerRowList.get(3);
                    String source = consumerRowList.get(4);
                    String type = consumerRowList.get(5);

                    //可为空
                    String level = consumerRowList.get(6);
                    String intention = consumerRowList.get(7);
                    String intentionQuantity = consumerRowList.get(8);
                    String companyAddress = consumerRowList.get(9);
                    String companyWebsite = consumerRowList.get(10);
                    String contact1 = consumerRowList.get(11);
                    String contact2 = consumerRowList.get(12);
                    String contact3 = consumerRowList.get(13);
                    String contacts = "";
                    if (StringUtil.isNotEmptyString(contact1)) {
                        contacts = contact1 + ";";
                    }
                    if (StringUtil.isNotEmptyString(contact2)) {
                        contacts = contact2 + ";";
                    }
                    if (StringUtil.isNotEmptyString(contact3)) {
                        contacts = contact3 + ";";
                    }
                    String whatsapp = consumerRowList.get(14);
                    String wechat = consumerRowList.get(15);
                    String facebook = consumerRowList.get(16);
                    String qq = consumerRowList.get(17);
                    String skype = consumerRowList.get(18);
                    String linkedin = consumerRowList.get(19);
                    String consignee = consumerRowList.get(20);
                    String telMobile = consumerRowList.get(21);
                    String postalCode = consumerRowList.get(22);
                    String receivingAddress = consumerRowList.get(23);
                    String userNo = consumerRowList.get(24);

                    if (StringUtil.isEmptyString(consumerName)) {
                        consumerRowList.add("客户姓名不能为空");
                        failedList.add(consumerRowList);
                        continue;
                    }

                    if (StringUtil.isEmptyString(country)) {
                        consumerRowList.add("客户国籍不能为空");
                        failedList.add(consumerRowList);
                        continue;
                    }

                    if (StringUtil.isEmptyString(email)) {
                        consumerRowList.add("邮箱不能为空");
                        failedList.add(consumerRowList);
                        continue;
                    }

                    if (StringUtil.isEmptyString(mainBusiness)) {
                        consumerRowList.add("主营业务不能为空");
                        failedList.add(consumerRowList);
                        continue;
                    }

                    if (StringUtil.isEmptyString(source)) {
                        consumerRowList.add("客户来源不能为空");
                        failedList.add(consumerRowList);
                        continue;
                    }

                    if (StringUtil.isEmptyString(type)) {
                        consumerRowList.add("客户类型不能为空");
                        failedList.add(consumerRowList);
                        continue;
                    }

                    if (StringUtil.isNotEmptyString(intentionQuantity) && !StringUtil.isNumber(intentionQuantity)) {
                        consumerRowList.add("意向数量字段请输入数字！");
                        failedList.add(consumerRowList);
                        continue;
                    }


                    Consumer dbConsumer = consumerService.getConsumerByNameAndEmail(consumerName, email);
                    if (dbConsumer != null) {
                        consumerRowList.add("客户已存在");
                        failedList.add(consumerRowList);
                        continue;
                    }

                    Consumer consumer = new Consumer();
                    //YYYYMMDD+随机四位数
                    Long consumerId = consumerService.getLatestConsumerId();
                    if (consumerId == null) {
                        consumerId = 0L;
                    }
                    consumer.setConsumerNo(DateUtil.formatToStr(new Date()) + String.format("%04d", consumerId + 1L));

                    //拓传参数
                    consumer.setName(consumerName);
                    if (checkEnum(CategoryCode.country.getCodeName(), country, consumerId)) {
                        consumer.setCountry(country);
                    }
                    consumer.setEmail(email.trim());

                    consumer.setMainBusiness(mainBusiness);

                    if (checkEnum(CategoryCode.Source.getCodeName(), source, consumerId)) {
                        consumer.setSource(source);
                    }
                    if (checkEnum(CategoryCode.Type.getCodeName(), type, consumerId)) {
                        consumer.setType(type);
                    }
                    if (checkEnum(CategoryCode.level.getCodeName(), level, consumerId)) {
                        consumer.setLevel(level);
                    }
                    if (StringUtil.isNotEmptyString(intention) && checkEnum(CategoryCode.intention.getCodeName(), intention, consumerId)) {
                        consumer.setIntention(intention);
                    }
                    if (StringUtil.isNotEmptyString(intentionQuantity)) {
                        consumer.setIntentionQuantity(Long.valueOf(intentionQuantity));
                    }
                    consumer.setFacebook(facebook);
                    consumer.setWhatsapp(whatsapp);
                    consumer.setLinkedin(linkedin);
                    consumer.setWechat(wechat);
                    consumer.setSkype(skype);
                    consumer.setQq(qq);

                    if (StringUtil.isNotEmptyString(contacts)) {
                        consumer.setContacts(contacts.substring(0, contacts.length() - 1));
                    }
                    consumer.setCompanyAddress(companyAddress);
                    consumer.setCompanyWebsite(companyWebsite);

                    consumer.setConsignee(consignee);
                    consumer.setTelMobile(telMobile);
                    consumer.setPostalCode(postalCode);
                    consumer.setReceivingAddress(receivingAddress);

                    consumer.setCreatedTime(new Date());
                    consumer.setUpdatedTime(new Date());
                    consumer.setCreateUser(userInfo.getUserNo());
                    consumer.setScope(ConsumerScopeEnum.Public.getCodeName());
                    consumerService.addConsumer(consumer);


/*                    ExecutorService executor = Executors.newFixedThreadPool(4);
                    executor.submit(new Runnable() {
                        @Override
                        public void run() {*/
                    try {
                        if (StringUtil.isNotEmptyString(userNo)) {
                            logger.info("userNo:" + userNo);
                            User user = userService.getUserByUserNo(userNo);
                            Consumer savedConsumer = consumerService.getConsumerByNameAndEmail(consumerName, email.trim());
                            if (savedConsumer != null && user != null) {
                                relateUserAndConsumer(savedConsumer, user);
                                savedConsumer.setScope(ConsumerScopeEnum.Private.getCodeName());
                                consumerService.updateConsumer(savedConsumer);
                            }
                        }
                    } catch (Exception ex) {
                        logger.error("绑定用户失败", ex);
                    }
                   /*     }
                    });*/

                    total++;
                }
            }

            long failedCount = 0;
            //TODO 生成失败文件
            if (!CollectionUtils.isEmpty(failedList)) {
                failedCount = failedList.size();
            }

            String longString = "上载成功量:" + total + ",上载失败量:" + failedCount;
            String userPathPrefix = pathPrefix + File.separator + userInfo.getUserNo();
            String reponse = ExcelExport.exportExcel(pathPrefix, longString, failedList, userPathPrefix);

            return WebApiResponse.success(reponse);

        } catch (Exception ex) {
            logger.error("管理人员进行客户上载异常", ex);
            return error("客户上载异常");
        }
    }


    /**
     * 检查是否在枚举里
     *
     * @param codeName
     * @param dictText
     * @param consumerId
     * @return
     */
    private boolean checkEnum(String codeName, String dictText, Long consumerId) {
        List<SysDict> sysDictList = sysDictService.listSysDict(codeName, dictText);
        if (CollectionUtils.isEmpty(sysDictList)) {
            SysDict sysDict = new SysDict();
            sysDict.setDictValue("EXT" + consumerId);
            sysDict.setStatus(1);
            sysDict.setCategoryCode(codeName);
            sysDict.setCreatedTime(new Date());
            sysDict.setDictText(dictText);
            sysDict.setReadonly(1);
            sysDict.setSort(consumerId.intValue());
            sysDict.setUpdatedTime(new Date());
            sysDictService.insertSysDict(sysDict);
        }
        return true;
    }


    /**
     * 关联用户及客户
     *
     * @param consumer
     * @param user
     * @return
     */
    private boolean relateUserAndConsumer(Consumer consumer, User user) {
        if (user == null) {
            logger.error("relateUserAndConsumer: user is null");
            return false;
        }

        if (consumer == null) {
            logger.error("relateUserAndConsumer: consumer is null");
            return false;
        }

        UserConsumerRelation userConsumerRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumer.getId(), user.getId(), FellowUpRulesEnum.Normal.getCode());
        if (userConsumerRelation != null) {
            return false;
        }

        //过期关联
        UserConsumerRelation invalidRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumer.getId(), user.getId(), null);
        if (invalidRelation != null) {
            invalidRelation.setIsDel(0);
            invalidRelation.setConsumerId(consumer.getId());
            invalidRelation.setConsumerNo(consumer.getConsumerNo());
            invalidRelation.setUserId(user.getId());
            invalidRelation.setUserNo(user.getUserNo());
            invalidRelation.setIsDel(0);
            invalidRelation.setHasOrder(0);
            invalidRelation.setDealOrder(0);
            invalidRelation.setFollowTime(new Date());
            invalidRelation.setNextFollowTime(null);
            invalidRelation.setUpdatedTime(new Date());
            userConsumerRelationService.updateUserConsumerRelation(invalidRelation);
        } else {
            //创建关联关系
            UserConsumerRelation newUserConsumerRelation = new UserConsumerRelation();
            newUserConsumerRelation.setConsumerId(consumer.getId());
            newUserConsumerRelation.setConsumerNo(consumer.getConsumerNo());
            newUserConsumerRelation.setUserId(user.getId());
            newUserConsumerRelation.setUserNo(user.getUserNo());
            newUserConsumerRelation.setIsDel(0);
            newUserConsumerRelation.setHasOrder(0);
            newUserConsumerRelation.setDealOrder(0);
            newUserConsumerRelation.setFollowTime(new Date());
            newUserConsumerRelation.setCreatedTime(new Date());
            newUserConsumerRelation.setUpdatedTime(new Date());
            userConsumerRelationService.addUserConsumerRelation(newUserConsumerRelation);
        }
        return true;
    }
}
