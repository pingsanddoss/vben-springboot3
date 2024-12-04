package org.vben.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vben.modules.system.entity.SalaryBodyDetail;
import org.vben.modules.system.entity.SalaryHeadDetail;
import org.vben.modules.system.entity.SysSmsGzNew;
import org.vben.common.api.vo.Result;
import org.vben.modules.system.service.ISalaryBodyDetailService;
import org.vben.modules.system.service.ISalaryHeadDetailService;
import org.vben.modules.system.service.ISysSmsGzNewService;
import org.vben.modules.system.util.Reflections;
import org.vben.common.util.oConvertUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/sys/smsGz")
public class SysSmsGzNewController {


    @Autowired
    private ISysSmsGzNewService sysSmsGzNewService;

    @Autowired
    private ISalaryHeadDetailService salaryHeadDetailService;

    @Autowired
    private ISalaryBodyDetailService salaryBodyDetailService;

    @RequestMapping(value = "/sendSmsGzNew", method = RequestMethod.POST)
    public Result<?> sendSmsGzNew(@RequestBody JSONObject jsonObject) {
        String sendState =   jsonObject.getString("sendState");
        String sendDate =   jsonObject.getString("sendDate");
        String sendType =   jsonObject.getString("sendType");
        String hide =   jsonObject.getString("hide");
        String mail = jsonObject.getString("mail");
        String phone = jsonObject.getString("phone");
        String salary = jsonObject.getString("salary");
        Boolean showBroadcast = jsonObject.getBoolean("showBroadcast");
        String broadcastText = jsonObject.getString("broadcastText");
        Boolean showCron = jsonObject.getBoolean("showCron");
        Date cronText = jsonObject.getDate("cronText");
        SimpleDateFormat dateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");

        Boolean showNull = jsonObject.getBoolean("showNull");
        String name = jsonObject.getString("name");
        SysSmsGzNew sysSmsGzNew = new SysSmsGzNew();
        sysSmsGzNew.setHide(hide);
        sysSmsGzNew.setMail(mail);
        sysSmsGzNew.setPhone(phone);
        sysSmsGzNew.setSalary(salary);
        sysSmsGzNew.setShowBroadcast(showBroadcast? "Y":"N");
        sysSmsGzNew.setBroadcastText(broadcastText);
        sysSmsGzNew.setShowCron(showCron? "Y":"N");
        if(cronText!=null){
            sysSmsGzNew.setCronText( dateFormat.format(cronText));
        }
        sysSmsGzNew.setShowNull(showNull? "Y":"N");
        sysSmsGzNew.setName(name);
        sysSmsGzNew.setSendState(sendState);
        sysSmsGzNew.setSendDate(sendDate);
        sysSmsGzNew.setSendType(sendType);
        sysSmsGzNew.setDelFlag(0);
        sysSmsGzNewService.save(sysSmsGzNew);
        List<SalaryBodyDetail> dataBody = jsonObject.getJSONArray("dataBody").toJavaList(SalaryBodyDetail.class);
        for (SalaryBodyDetail salaryBodyDetail : dataBody) {
            String salt = oConvertUtils.randomGen(6);
            salaryBodyDetail.setEmployId(sysSmsGzNew.getId());
            salaryBodyDetail.setLink(salt);
            salaryBodyDetail.setDelFlag(0);
        }
        salaryBodyDetailService.saveBatch(dataBody);
        ArrayList<SalaryHeadDetail> salaryHeadDetailArrayList = new ArrayList<SalaryHeadDetail>();
        JSONObject dataHead = jsonObject.getObject("dataHead",JSONObject.class);
        Iterator<String> keys = dataHead.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = (String) dataHead.get(key);
            SalaryHeadDetail salaryHeadDetail = new SalaryHeadDetail();
            salaryHeadDetail.setEmployId(sysSmsGzNew.getId());
            salaryHeadDetail.setHead(key);
            salaryHeadDetail.setBody(value);
            salaryHeadDetail.setDelFlag(0);
            salaryHeadDetailArrayList.add(salaryHeadDetail);
            System.out.println(key);
        }
        salaryHeadDetailService.saveBatch(salaryHeadDetailArrayList);





        String[] sendStat = sysSmsGzNew.getSendState().substring(1, sysSmsGzNew.getSendState().length()-1).split(",");

        //TODO
        //定时任务
        if(showCron.equals("Y")){

            //定时任务执行


        }else {
            //立即执行
            //TODO
            //发短信
            for (String a : sendStat) {
                if (a.equals("1")) {
                    //短信

                    //
//                    String aa =  (String) Reflections.getFieldValue(salaryBodyDetail, salaryHeadDetail1.getHead());
//                    JSONObject obj = new JSONObject();
//                    obj.put("name", smsGz.getName());
//                    obj.put("year", smsGz.getYear());
//                    obj.put("month", smsGz.getMonth());
//                    obj.put("code", smsGz.getHx());
//                  //  Map<String, Object> result = DySmsHelper.sendSms(smsGz.getPhone(), obj, DySmsEnum.BAIXUE_GZ);
//                    Boolean isSuccess = (Boolean) result.get("result");
//                    if (isSuccess) {
//                        smsGz.setIsSuccess("T");
//                    } else {
//                        smsGz.setIsSuccess("F");
//                    }

                } else if (a.equals("2")) {
                    //公众号

                } else if (a.equals("3")) {
                    //邮件

                }
            }
        }
        System.out.println(jsonObject);


        return Result.ok();
    }

    //@RequiresPermissions("system:smsGz:sendSmsGz")
    @RequestMapping(value = "/getSmsGzNew", method = RequestMethod.POST)
    public Result<?> getSmsGzNew(@RequestBody JSONObject jsonObject) {
        String id =   jsonObject.getString("id");
        String name =   jsonObject.getString("name");
        Result<String> result = new Result<>();
        if(id == null){
            result.setData("false");
            return result;
        }


        SalaryBodyDetail salaryBodyDetail = salaryBodyDetailService.getOne(new QueryWrapper<SalaryBodyDetail>().eq("link", id).eq("del_flag",0));
        if(salaryBodyDetail == null){
            result.setData("false");
            return result;
        }
        SysSmsGzNew sysSmsGzNew = sysSmsGzNewService.getById(salaryBodyDetail.getEmployId());

        //SalaryBodyDetail salaryBodyDetail1 = salaryBodyDetailService.getOne(new QueryWrapper<SalaryBodyDetail>().eq("link", id).eq(sysSmsGzNew.getName();));
        String realName = (String) Reflections.getFieldValue(salaryBodyDetail,sysSmsGzNew.getName());

        if(name.equals(realName)){
            result.setData("true");
        }else {
            result.setData("false");
        }

        return result;
    }

    @RequestMapping(value = "/getSmsGzNewDetail", method = RequestMethod.POST)
    public Result<?> getSmsGzNewDetail(@RequestBody JSONObject jsonObject) {
        String id =     jsonObject.getString("id");
        String name =     jsonObject.getString("name");
        ArrayList<Object> smsGzNewDetail = new ArrayList<>();
        ArrayList<Object> data1 = new ArrayList<>();


        Result<Object> result = new Result<>();
        if(id == null){
            result.setData("false");
            return result;
        }

        SalaryBodyDetail salaryBodyDetail = salaryBodyDetailService.getOne(new QueryWrapper<SalaryBodyDetail>().eq("link", id).eq("del_flag",0));
        SysSmsGzNew sysSmsGzNew = sysSmsGzNewService.getById(salaryBodyDetail.getEmployId());
        String realName = (String) Reflections.getFieldValue(salaryBodyDetail,sysSmsGzNew.getName());

        if(salaryBodyDetail == null){
            result.setData("false");
            return result;
        }

        if(name.equals(realName)){
            result.setData("true");
        }else {
            result.setData("false");
            return result;
        }
        String aa = null;
        String name1 = null;
        List<SalaryHeadDetail> salaryHeadDetail = salaryHeadDetailService.list(new QueryWrapper<SalaryHeadDetail>().eq("employ_id", salaryBodyDetail.getEmployId()).eq("del_flag",0));
        for (SalaryHeadDetail salaryHeadDetail1 : salaryHeadDetail) {
            Map<String,String> data = new LinkedHashMap<>();

            if(sysSmsGzNew.getSalary().equals(salaryHeadDetail1.getHead())){
                aa =  (String) Reflections.getFieldValue(salaryBodyDetail, salaryHeadDetail1.getHead());
            }
            if(sysSmsGzNew.getName().equals(salaryHeadDetail1.getHead())){
                name1 =  (String) Reflections.getFieldValue(salaryBodyDetail, salaryHeadDetail1.getHead());
            }
            if(sysSmsGzNew.getShowNull().equals("Y")){
                if(Reflections.getFieldValue(salaryBodyDetail, salaryHeadDetail1.getHead()) == null || Reflections.getFieldValue(salaryBodyDetail, salaryHeadDetail1.getHead()).equals("")){
                    continue;
                }
            }
            if(sysSmsGzNew.getHide().substring(1, sysSmsGzNew.getHide().length()-1).split(",").length > 1
                    || !sysSmsGzNew.getHide().substring(1, sysSmsGzNew.getHide().length() - 1).isEmpty()){
                String[] aa1 = sysSmsGzNew.getHide().substring(1, sysSmsGzNew.getHide().length()-1).split(",");
                if( Arrays.stream(aa1).anyMatch(value -> value.equals(salaryHeadDetail1.getHead()))){
                    continue;
                };
            }
            data.put("name",salaryHeadDetail1.getBody());
            data.put("value", (String) Reflections.getFieldValue(salaryBodyDetail, salaryHeadDetail1.getHead()));
            data1.add(data);
            //data.put(salaryHeadDetail1.getBody(), (String) Reflections.getFieldValue(salaryBodyDetail, salaryHeadDetail1.getHead()));
        }

        smsGzNewDetail.add(data1);
        sysSmsGzNew.setSalary(aa);
        sysSmsGzNew.setName(name1);
        smsGzNewDetail.add(sysSmsGzNew);
        result.setData(smsGzNewDetail);

        return result;
    }
}
