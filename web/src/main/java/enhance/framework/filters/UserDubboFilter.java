package enhance.framework.filters;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

/**
 * 调用提供者前把用户信息写入RpcContext
 *
 * @author futaosmile@gmail.com
 */
@Activate(group = Constants.CONSUMER, order = -10000)
public class UserDubboFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        // JSONObject currentUser = UserUtils.currentUser(false);
        // if (Objects.nonNull(currentUser)) {
        //     CommonLoginUser user = new CommonLoginUser();
        //     user.setUserId(UserUtils.adminIdLong());
        //     user.setUserName(UserUtils.username());
        //     RpcContext.getContext().setAttachment(CommonConstant.LOGIN_USER_CONTEXT, JSON.toJSONString(user));
        // }

        // 进行服务调用并返回
        return invoker.invoke(invocation);
    }

}
