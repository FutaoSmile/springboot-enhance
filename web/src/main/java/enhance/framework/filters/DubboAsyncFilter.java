package enhance.framework.filters;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate(group = {Constants.PROVIDER})
public class DubboAsyncFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().getAttachments().remove(Constants.ASYNC_KEY);
        return invoker.invoke(invocation);
    }
}
