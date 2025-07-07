 
package ltd.carb.mall.service.impl;

import ltd.carb.mall.common.Constants;
import ltd.carb.mall.common.ServiceResultEnum;
import ltd.carb.mall.controller.vo.CarBMallUserVO;
import ltd.carb.mall.dao.MallUserMapper;
import ltd.carb.mall.entity.MallUser;
import ltd.carb.mall.service.CarBMallUserService;
import ltd.carb.mall.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CarBMallUserServiceImpl implements CarBMallUserService {

    @Autowired
    private MallUserMapper mallUserMapper;

    @Override
    public PageResult getCarBMallUsersPage(PageQueryUtil pageUtil) {
        List<MallUser> mallUsers = mallUserMapper.findMallUserList(pageUtil);
        int total = mallUserMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(mallUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String register(String loginName, String password) {
        if (mallUserMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        MallUser registerUser = new MallUser();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (mallUserMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        MallUser user = mallUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            CarBMallUserVO carBMallUserVO = new CarBMallUserVO();
            BeanUtil.copyProperties(user, carBMallUserVO);
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, carBMallUserVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public CarBMallUserVO updateUserInfo(MallUser mallUser, HttpSession httpSession) {
        CarBMallUserVO userTemp = (CarBMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        MallUser userFromDB = mallUserMapper.selectByPrimaryKey(userTemp.getUserId());
        if (userFromDB != null) {
            if (StringUtils.hasText(mallUser.getNickName())) {
                userFromDB.setNickName(CarBMallUtils.cleanString(mallUser.getNickName()));
            }
            if (StringUtils.hasText(mallUser.getAddress())) {
                userFromDB.setAddress(CarBMallUtils.cleanString(mallUser.getAddress()));
            }
            if (StringUtils.hasText(mallUser.getIntroduceSign())) {
                userFromDB.setIntroduceSign(CarBMallUtils.cleanString(mallUser.getIntroduceSign()));
            }
            if (mallUserMapper.updateByPrimaryKeySelective(userFromDB) > 0) {
                CarBMallUserVO carBMallUserVO = new CarBMallUserVO();
                BeanUtil.copyProperties(userFromDB, carBMallUserVO);
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, carBMallUserVO);
                return carBMallUserVO;
            }
        }
        return null;
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return mallUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}
