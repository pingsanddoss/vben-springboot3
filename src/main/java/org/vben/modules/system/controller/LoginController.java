package org.vben.modules.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.vben.common.api.vo.Result;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author scott
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/sys")
public class LoginController {

	private final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result<JSONObject> login(@RequestBody JSONObject jsonObject, HttpServletRequest request){
		Result<JSONObject> result = new Result<JSONObject>();
//		String username = sysLoginModel.getUsername();
//		String password = sysLoginModel.getPassword();
//		if(isLoginFailOvertimes(username)){
//			return result.error500("该用户登录失败次数过多，请于10分钟后再次登录！");
//		}

//		// step.1 验证码check
//        String captcha = sysLoginModel.getCaptcha();
//        if(captcha==null){
//            result.error500("验证码无效");
//            return result;
//        }
//        String lowerCaseCaptcha = captcha.toLowerCase();
//		// 加入密钥作为混淆，避免简单的拼接，被外部利用，用户自定义该密钥即可
//        String origin = lowerCaseCaptcha+sysLoginModel.getCheckKey()+jeecgBaseConfig.getSignatureSecret();
//		String realKey = Md5Util.md5Encode(origin, "utf-8");
//		Object checkCode = redisUtil.get(realKey);
//		//当进入登录页时，有一定几率出现验证码错误 #1714
//		if(checkCode==null || !checkCode.toString().equals(lowerCaseCaptcha)) {
//            log.warn("验证码错误，key= {} , Ui checkCode= {}, Redis checkCode = {}", sysLoginModel.getCheckKey(), lowerCaseCaptcha, checkCode);
//			result.error500("验证码错误");
//			// 改成特殊的code 便于前端判断
//			result.setCode(HttpStatus.PRECONDITION_FAILED.value());
//			return result;
//		}
//
//		// step.2 校验用户是否存在且有效
//		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
//		queryWrapper.eq(SysUser::getUsername,username);
//		SysUser sysUser = sysUserService.getOne(queryWrapper);
//		result = sysUserService.checkUserIsEffective(sysUser);
//		if(!result.isSuccess()) {
//			return result;
//		}
//
//		// step.3 校验用户名或密码是否正确
//		String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
//		String syspassword = sysUser.getPassword();
//		if (!syspassword.equals(userpassword)) {
//			addLoginFailOvertimes(username);
//			result.error500("用户名或密码错误");
//			return result;
//		}
//
//		// step.4  登录成功获取用户信息
//		userInfo(sysUser, result, request);
//
//		// step.5  登录成功删除验证码
//		redisUtil.del(realKey);
//		redisUtil.del(CommonConstant.LOGIN_FAIL + username);
//
//		// step.6  记录用户登录日志
//		LoginUser loginUser = new LoginUser();
//		BeanUtils.copyProperties(sysUser, loginUser);
//		baseCommonService.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null,loginUser);
		return result;
	}



	/**
	 * 用户信息
	 *
	 * @param sysUser
	 * @param result
	 * @return
	 */
//	private Result<JSONObject> userInfo(SysUser sysUser, Result<JSONObject> result, HttpServletRequest request) {
//		String username = sysUser.getUsername();
//		String syspassword = sysUser.getPassword();
//		// 获取用户部门信息
//		JSONObject obj = new JSONObject(new LinkedHashMap<>());
//
//		//1.生成token
//		String token = JwtUtil.sign(username, syspassword);
//		// 设置token缓存有效时间
//		redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
//		redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
//		obj.put("token", token);
//
//		//2.设置登录租户
//		Result<JSONObject> loginTenantError = sysUserService.setLoginTenant(sysUser, obj, username,result);
//		if (loginTenantError != null) {
//			return loginTenantError;
//		}
//
//		//2.1.设置登录单位
//		Result<JSONObject> loginAccountSuitError = sysUserService.setLoginAccountSuit(sysUser, obj, username,result);
//		if (loginAccountSuitError != null) {
//			return loginAccountSuitError;
//		}
//
//		//3.设置登录用户信息
//		obj.put("userInfo", sysUser);
//
//		//4.设置登录部门
//		List<SysDepart> departs = sysDepartService.queryUserDeparts(sysUser.getId());
//		obj.put("departs", departs);
//		if (departs == null || departs.size() == 0) {
//			obj.put("multi_depart", 0);
//		} else if (departs.size() == 1) {
//			sysUserService.updateUserDepart(username, departs.get(0).getOrgCode(),null);
//			obj.put("multi_depart", 1);
//		} else {
//			//查询当前是否有登录部门
//			// update-begin--Author:wangshuai Date:20200805 for：如果用戶为选择部门，数据库为存在上一次登录部门，则取一条存进去
//			SysUser sysUserById = sysUserService.getById(sysUser.getId());
//			if(oConvertUtils.isEmpty(sysUserById.getOrgCode())){
//				sysUserService.updateUserDepart(username, departs.get(0).getOrgCode(),null);
//			}
//			// update-end--Author:wangshuai Date:20200805 for：如果用戶为选择部门，数据库为存在上一次登录部门，则取一条存进去
//			obj.put("multi_depart", 2);
//		}
//
//		//update-begin---author:scott ---date:2024-01-05  for：【QQYUN-7802】前端在登录时加载了两次数据字典，建议优化下，避免数据字典太多时可能产生的性能问题 #956---
//		// login接口，在vue3前端下不加载字典数据，vue2下加载字典
//		String vue3Version = request.getHeader(CommonConstant.VERSION);
//		if(oConvertUtils.isEmpty(vue3Version)){
//			obj.put("sysAllDictItems", sysDictService.queryAllDictItems());
//		}
//		//end-begin---author:scott ---date:2024-01-05  for：【QQYUN-7802】前端在登录时加载了两次数据字典，建议优化下，避免数据字典太多时可能产生的性能问题 #956---
//
//		result.setResult(obj);
//		result.success("登录成功");
//		return result;
//	}
//
//
}