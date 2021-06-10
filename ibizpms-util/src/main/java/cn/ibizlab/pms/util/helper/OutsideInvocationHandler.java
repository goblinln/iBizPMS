package cn.ibizlab.pms.util.helper;

import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import feign.InvocationHandlerFactory;
import feign.Target;
import feign.Util;
import feign.hystrix.FallbackFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class OutsideInvocationHandler implements InvocationHandler {

    private final Target target;
    private final Map<Method, InvocationHandlerFactory.MethodHandler> dispatch;
    FallbackFactory fallbackFactory;
    private Map<Method, Method> fallbackMethodMap;

    public OutsideInvocationHandler(Target target, Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
        this.target = Util.checkNotNull(target, "target");
        this.dispatch = Util.checkNotNull(dispatch, "dispatch for %s", target);
    }

    public OutsideInvocationHandler(Target target, Map<Method, InvocationHandlerFactory.MethodHandler> dispatch, FallbackFactory fallbackFactory) {
        this.target = Util.checkNotNull(target, "target");
        this.dispatch = Util.checkNotNull(dispatch, "dispatch for %s", target);
        this.fallbackFactory = fallbackFactory;
        this.fallbackMethodMap = toFallbackMethod(dispatch);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("equals".equals(method.getName())) {
            try {
                Object otherHandler =
                        args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                return equals(otherHandler);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else if ("hashCode".equals(method.getName())) {
            return hashCode();
        } else if ("toString".equals(method.getName())) {
            return toString();
        }

        Object result;
        InvocationHandlerFactory.MethodHandler methodHandler = this.dispatch.get(method);
        // only handle by HardCodedTarget
        if (target instanceof Target.HardCodedTarget) {
                try {
                    result = methodHandler.invoke(args);
                }
                catch (Throwable ex) {
                    // fallback handle
                    if (!BlockException.isBlockException(ex)) {
                        Tracer.trace(ex);
                    }
                    if (fallbackFactory != null) {
                        try {
                            Object fallbackResult = fallbackMethodMap.get(method)
                                    .invoke(fallbackFactory.create(ex), args);
                            return fallbackResult;
                        }
                        catch (IllegalAccessException e) {
                            // shouldn't happen as method is public due to being an
                            // interface
                            throw new AssertionError(e);
                        }
                        catch (InvocationTargetException e) {
                            throw new AssertionError(e.getCause());
                        }
                    }
                    else {
                        // throw exception if fallbackFactory is null
                        throw ex;
                    }
                }
                finally {
                    ContextUtil.exit();
                }

        }
        else {
            // other target type using default strategy
            result = methodHandler.invoke(args);
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OutsideInvocationHandler) {
            OutsideInvocationHandler other = (OutsideInvocationHandler) obj;
            return target.equals(other.target);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }

    @Override
    public String toString() {
        return target.toString();
    }

    static Map<Method, Method> toFallbackMethod(Map<Method, InvocationHandlerFactory.MethodHandler> dispatch) {
        Map<Method, Method> result = new LinkedHashMap<>();
        for (Method method : dispatch.keySet()) {
            method.setAccessible(true);
            result.put(method, method);
        }
        return result;
    }
}