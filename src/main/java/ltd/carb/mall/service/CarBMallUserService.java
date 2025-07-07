 
package ltd.carb.mall.service;

import ltd.carb.mall.controller.vo.CarBMallUserVO;
import ltd.carb.mall.entity.MallUser;
import ltd.carb.mall.util.PageQueryUtil;
import ltd.carb.mall.util.PageResult;

import javax.servlet.http.HttpSession;

public interface CarBMallUserService {
    /**
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarBMallUsersPage(PageQueryUtil pageUtil);

    /**
     *
     * @param loginName
     * @param password
     * @return
     */
    String register(String loginName, String password);

    /**
     *
     * @param loginName
     * @param passwordMD5
     * @param httpSession
     * @return
     */
    String login(String loginName, String passwordMD5, HttpSession httpSession);

    /**
     *
     * @param mallUser
     * @return
     */
    CarBMallUserVO updateUserInfo(MallUser mallUser, HttpSession httpSession);

    /**
     *
     * @param ids
     * @param lockStatus
     * @return
     */
    Boolean lockUsers(Integer[] ids, int lockStatus);
}
