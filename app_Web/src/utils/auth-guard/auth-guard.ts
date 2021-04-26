import qs from 'qs';
import { GlobalHelp } from '@ibiz/dynamic-model-api';
import { AppServiceBase, Http, getSessionStorage, setSessionStorage, AppModelService } from 'ibiz-core';
import { AppCenterService, ErrorUtil } from 'ibiz-vue';
import { Environment } from '@/environments/environment';
import { DynamicInstanceConfig } from '@ibiz/dynamic-model-api';
import i18n from '@/locale';

/**
 * AuthGuard net 对象
 * 调用 getInstance() 获取实例
 *
 * @class Http
 */
export class AuthGuard {
    /**
     * 获取 Auth 单例对象
     *
     * @static
     * @returns {Auth}
     * @memberof Auth
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
    private constructor() {}

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
                        }else{
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
            if (tempViewParam.srfdcsystem) {
                setSessionStorage('dcsystem', tempViewParam);
                let requestUrl: string = `/uaa/getbydcsystem/${tempViewParam.srfdcsystem}`;
                const get: Promise<any> = Http.getInstance().get(requestUrl);
                get.then((response: any) => {
                    if (response && response.status === 200) {
                        let { data }: { data: any } = response;
                        if (data && data.length > 0) {
                            setSessionStorage('orgsData', data);
                            setSessionStorage('activeOrgData', data[0]);
                        }
                        resolve(true);
                    } else {
                        resolve(false);
                    }
                }).catch(() => {
                    resolve(false);
                    ErrorUtil.errorHandler("通过租户获取组织数据出现异常");
                });
            }else{
                ErrorUtil.errorHandler("未获取到租户数据标识");
                resolve(false);
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
                const get: Promise<any> = Http.getInstance().get(url);
                get.then((response: any) => {
                    if (response && response.status === 200) {
                        let { data }: { data: any } = response;
                        if (data) {
                            // token认证把用户信息放入应用级数据
                            if (localStorage.getItem('user')) {
                                let user: any = JSON.parse(localStorage.getItem('user') as string);
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
                    this.initAppService(router).then(() => resolve(true));
                    ErrorUtil.errorHandler("获取应用数据出现异常");
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
        });
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
}
