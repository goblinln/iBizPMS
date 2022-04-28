import qs from 'qs';
import { GlobalHelp, IPSAppView } from '@ibiz/dynamic-model-api';
import { AppServiceBase, Http, getSessionStorage, setSessionStorage, AppModelService, Util, GetModelService } from 'ibiz-core';
import { AppCenterService, NoticeHandler } from 'ibiz-vue';
import { Environment } from '@/environments/environment';
import { DynamicInstanceConfig } from '@ibiz/dynamic-model-api';
import i18n from '@/locale';
import { clearCookie, getCookie, SyncSeriesHook } from 'qx-util';
import { handleLocaleMap } from '@/locale/local-util';

/**
 * AuthGuard net 对象
 * 调用 getInstance() 获取实例
 *
 * @class Http
 */
export class AuthGuard {


    /**
     * 执行钩子(包含获取租户前、获取租户后、获取应用数据前、获取应用数据后)
     *
     * @memberof AuthGuard
     */
    public static hooks = {
        dcSystemBefore: new SyncSeriesHook<[], { dcsystem: string }>(),
        dcSystemAfter: new SyncSeriesHook<[], { dcsystem: string, data: any }>(),
        appBefore: new SyncSeriesHook<[], { url: string, param: any }>(),
        appAfter: new SyncSeriesHook<[], { data: any }>()
    };

    /**
     * 获取 Auth 单例对象
     *
     * @static
     * @returns {AuthGuard}
     * @memberof AuthGuard
     */
    static getInstance(): AuthGuard {
        if (!AuthGuard.auth) {
            AuthGuard.auth = new AuthGuard();
        }
        return this.auth;
    }

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AuthGuard}
     * @memberof AuthGuard
     */
    private static auth: AuthGuard;

    /**
     * Creates an instance of AuthGuard.
     * 私有构造，拒绝通过 new 创建对象
     *
     * @memberof AuthGuard
     */
    private constructor() { }

    /**
     * 获取应用数据
     *
     * @param {string} url url 请求路径
     * @param {*} [params={}] 请求参数
     * @param {*} [router] 路由对象
     * @returns {Promise<any>} 请求相响应对象
     * @memberof AuthGuard
     */
    authGuard(url: string, params: any = {}, router: any): any {
        return new Promise((resolve: any, reject: any) => {
            if (Environment && Environment.SaaSMode) {
                if (getSessionStorage('activeOrgData')) {
                    this.getAppData(url, params, router).then((result: any) => {
                        result ? resolve(true) : reject(false);
                    });
                } else {
                    this.getOrgsByDcsystem(router).then((result: boolean) => {
                        if (!result) {
                            reject(false);
                        } else {
                            this.getAppData(url, params, router).then((result: any) => {
                                result ? resolve(true) : reject(false);
                            });
                        }
                    });
                }
            } else {
                this.getAppData(url, params, router).then((result: any) => {
                    result ? resolve(true) : reject(false);
                });
            }
        });
    }

    /**
     * 通过租户获取组织数据
     *
     * @memberof AuthGuard
     */
    getOrgsByDcsystem(_router: any): Promise<boolean> {
        return new Promise((resolve: any) => {
            let tempViewParam = this.hanldeViewParam(window.location.href);
            if (!tempViewParam.srfdcsystem) {
                if (!tempViewParam.redirect) {
                    if (getSessionStorage('dcsystem')) {
                        tempViewParam = getSessionStorage('dcsystem');
                    }
                } else {
                    tempViewParam = this.hanldeViewParam(tempViewParam.redirect);
                }
            }
            if (!tempViewParam.srfdcsystem && Environment.mockDcSystemId) {
                Object.assign(tempViewParam, { srfdcsystem: Environment.mockDcSystemId });
            }
            if (tempViewParam.srfdcsystem) {
                AuthGuard.hooks.dcSystemBefore.callSync({ dcsystem: tempViewParam.srfdcsystem });
                setSessionStorage('dcsystem', tempViewParam);
                let requestUrl: string = `/uaa/getbydcsystem/${tempViewParam.srfdcsystem}`;
                const get: Promise<any> = Http.getInstance().get(requestUrl);
                get.then((response: any) => {
                    if (response && response.status === 200) {
                        let { data }: { data: any } = response;
                        AuthGuard.hooks.dcSystemAfter.callSync({ dcsystem: tempViewParam.srfdcsystem, data: data });
                        if (data && data.length > 0) {
                            setSessionStorage('orgsData', data);
                            setSessionStorage('activeOrgData', data[0]);
                        }
                        resolve(true);
                    } else {
                        resolve(false);
                    }
                }).catch(() => {
                    this.doNoLogin(_router, "登录失败，请联系管理员").then((result:any) =>{
                        resolve(result);
                    })
                });
            } else {
                this.doNoLogin(_router, "登录失败，请联系管理员").then((result:any) =>{
                    resolve(result);
                })
            }
        });
    }

    /**
     * 获取应用数据
     *
     * @param {string} url url 请求路径
     * @param {*} [params={}] 请求参数
     * @param {*} [router] 路由对象
     * @returns {Promise<boolean>} 是否通过
     * @memberof AuthGuard
     */
    getAppData(url: string, _params: any = {}, router: any): Promise<boolean> {
        return new Promise((resolve: any) => {
            if (Environment.enableAppData) {
                AuthGuard.hooks.appBefore.callSync({ url: url, param: _params });
                const get: Promise<any> = Http.getInstance().get(url);
                get.then((response: any) => {
                    if (response && response.status === 200) {
                        let { data }: { data: any } = response;
                        AuthGuard.hooks.appAfter.callSync({ data: data });
                        if (data) {
                            // token认证把用户信息放入应用级数据
                            if (getCookie('ibzuaa-user')) {
                                let user: any = JSON.parse(getCookie('ibzuaa-user') as string);
                                let localAppData: any = {};
                                if (user.sessionParams) {
                                    localAppData = { context: user.sessionParams };
                                    Object.assign(localAppData, data);
                                }
                                data = JSON.parse(JSON.stringify(localAppData));
                            }
                            if (localStorage.getItem('localdata')) {
                                router.app.$store.commit(
                                    'addLocalData',
                                    JSON.parse(localStorage.getItem('localdata') as string),
                                );
                            }
                            router.app.$store.commit('addAppData', data);
                            // 提交统一资源数据
                            router.app.$store.dispatch('authresource/commitAuthData', data);
                        }
                    }
                    this.initAppService(router).then(() => resolve(true));
                }).catch(() => {
                    this.initAppService(router).then(() => {
                        this.doNoLogin(router, "登录失败，请联系管理员").then((result:any) =>{
                            resolve(result);
                        })
                    });
                });
            } else {
                this.initAppService(router).then(() => resolve(true));
            }
        });
    }

    /**
     * 初始化应用服务
     *
     * @param {*} [router] 路由对象
     *
     * @memberof AuthGuard
     */
    async initAppService(router: any): Promise<void> {
        AppServiceBase.getInstance().setAppEnvironment(Environment);
        AppServiceBase.getInstance().setAppStore(router.app.$store);
        AppServiceBase.getInstance().setI18n(i18n);
        AppServiceBase.getInstance().setRouter(router);
        const service = new AppModelService();
        await GlobalHelp.install(service, async (strPath: string, config: DynamicInstanceConfig) => {
            let url: string = '';
            if (Environment.bDynamic) {
                url = `${Environment.remoteDynaPath}${strPath}`;
                if (config) {
                    url += `?srfInstTag=${config.instTag}&srfInstTag2=${config.instTag2}`;
                }
            } else {
                url = `./assets/model${strPath}`;
            }
            try {
                const result: any = await Http.getInstance().get(url);
                return result.data ? result.data : null;
            } catch (error) {
                return null;
            }
        }, { lang: Environment?.isEnableMultiLan ? handleLocaleMap(i18n.locale) : '' });
        AppServiceBase.getInstance().setAppModelDataObject(service.app);
        AppCenterService.getInstance(router.app.$store);
    }

    /**
     * 处理路径数据
     *
     * @param {*} [urlStr] 路径
     *
     * @memberof AuthGuard
     */
    hanldeViewParam(urlStr: string): any {
        let tempViewParam: any = {};
        const tempViewparam: any = urlStr.slice(urlStr.indexOf('?') + 1);
        const viewparamArray: Array<string> = decodeURIComponent(tempViewparam).split(';');
        if (viewparamArray.length > 0) {
            viewparamArray.forEach((item: any) => {
                Object.assign(tempViewParam, qs.parse(item));
            });
        }
        return tempViewParam;
    }

    /**
     * 处理未登录异常情况
     *
     * @memberof AuthGuard
     */
    public async doNoLogin(router: any, message: string) {
        const gotoLogin: Function = () => {
            this.clearAppData(router.app.$store);
            if (Environment.loginUrl) {
                window.location.href = `${Environment.loginUrl}?redirect=${window.location.href}`;
            } else {
                if (Object.is(router.currentRoute.name, 'login')) {
                    NoticeHandler.errorHandler(message);
                    return;
                }
                router.push({ name: 'login', query: { redirect: window.location.hash.replace("#",'') } });
            }
            return false;
        }
        return this.handleAccUserMode(router, gotoLogin);
    }

    /**
     * 处理用户范围模式（0：未指定、 2：登录用户、 3：匿名用户及登录用户、 4：登录用户且拥有指定资源能力 ）
     *
     * @memberof AuthGuard
     */
     public async handleAccUserMode(router: any, gotoLogin: Function) {
        try {
            const activedPath: string = window.location.hash.substr(1);
            let activedRoute: any = router.getRoutes().find((item: any) => {
                return item.regex.test(activedPath);
            })
            if (activedRoute) {
                // 跳转应用首页视图
                if (activedRoute.redirect) {
                    activedRoute = router.getRoutes().find((item: any) => {
                        return item.meta.viewType === 'APPINDEX';
                    })
                    if (activedRoute && activedRoute.meta) {
                        if (activedRoute.meta.requireAuth) {
                            gotoLogin();
                        } else {
                            return true;
                        }
                    }
                } else {
                    // 自定义视图路径跳转
                    const dynaModelFilePath: string = await this.computeActivedViewFilePath(activedPath, activedRoute)
                    if (dynaModelFilePath) {
                        const modeldata = await ((await GetModelService()).getPSAppView(dynaModelFilePath)) as IPSAppView;
                        if ((modeldata.accUserMode === 0) || (modeldata.accUserMode === 3)) {
                            return true;
                        } else {
                            gotoLogin();
                        }
                    } else {
                        gotoLogin();
                    }
                }
            }else{
                gotoLogin();
            }
        } catch (error) {
            gotoLogin();
        }
    }

    /**
     * 计算视图动态路径
     *
     * @memberof AuthGuard
     */
    public async computeActivedViewFilePath(activedPath: string, route: any) {
        if (route && route.meta && route.meta.parameters) {
            let resource: string = route.meta.resource ? route.meta.resource.toLowerCase() : '';
            let activedView: any = route.meta.parameters.find((item: any) => {
                return item.pathName === 'views';
            });
            let localActivedView: any = Util.deepCopy(activedView);
            if (Object.is(localActivedView.parameterName, 'view') && Object.is(localActivedView.pathName, 'views')) {
                localActivedView.parameterName = this.parseUrlDynamicParam(activedPath, route).view;
            }
            if (localActivedView && localActivedView.parameterName) {
                const path = (await GetModelService()).getPSAppViewPath(`${resource}${localActivedView.parameterName}`);
                return path;
            } else {
                return '';
            }
        } else {
            return '';
        }
    }

    /**
     * 解析路由动态参数
     *
     * @memberof AuthGuard
     */
    public parseUrlDynamicParam(activedPath: string, route: any): any {
        const keys: Array<any> = route.regex.keys;
        const matchArray = route.regex.exec(activedPath);
        let tempValue: Object = {};
        keys.forEach((item: any, index: number) => {
            if (matchArray[index + 1]) {
                Object.defineProperty(tempValue, item.name, {
                    enumerable: true,
                    value: decodeURIComponent(matchArray[index + 1])
                });
            }
        });
        return tempValue;
    }

    /**
     * 清除应用数据
     *
     * @private
     * @memberof AuthGuard
     */
    private clearAppData(store: any) {
        // 清除user、token
        clearCookie('ibzuaa-token', true);
        clearCookie('ibzuaa-user', true);
        // 清除应用级数据
        localStorage.removeItem('localdata')
        store.commit('addAppData', {});
        store.dispatch('authresource/commitAuthData', {});
    }
}
