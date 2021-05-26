import * as dd from 'dingtalk-jsapi';
import { AppServiceBase, Http } from "ibiz-core";

export interface stateResult {

    /**
     * 状态
     *
     * @memberof stateResult
     */
    state: "SUCCESS" | "ERROR";

    /**
     * 数据
     *
     * @memberof stateResult
     */
    result: any;

}
/**
 * 第三方登录服务
 * 
 * @memberof AppThirdService
 */
export class AppThirdService {

    /**
     * 国际化
     * 
     * @memberof AppNavHistory
     */
     public i18n: any = AppServiceBase.getInstance().getI18n();

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AppThirdService}
     * @memberof AppThirdService
     */
    private static appThirdService: AppThirdService;

    /**
     * 获取 AppThirdService 单例对象
     *
     * @static
     * @returns {AppThirdService}
     * @memberof AppThirdService
     */
    public static getInstance(): AppThirdService {
        if (!AppThirdService.appThirdService) {
            AppThirdService.appThirdService = new AppThirdService();
        }
        return this.appThirdService;
    }

    /**
     * 钉钉授权登录
     * 
     * @memberof AppThirdService
     */
    public dingtalkLogin(Environment: any): Promise<any> {
        return new Promise((resolve) => {
            const get: Promise<any> = Http.getInstance().get('/uaa/open/dingtalk/appid?id=' + Environment.dingTalkAccAppId);
            get.then((response: any) => {
                if (response && response.status === 200) {
                    const data = response.data;
                    if (data && data.appid) {
                        resolve({ state: "SUCCESS", data: data });
                    } else {
                        resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getAppidError')}，${data.detail}` });
                    }
                }
            }).catch((error: any) => {
                const data = error.data;
                if (data && data.detail) {
                    resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getAppidError')}，${data.detail}` });
                } else {
                    resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getAppidError')}` });
                }
            });
        })
    }

    /**
     * 企业微信授权登录
     * 
     * @memberof AppThirdService
     */
    public wxWorkLogin(Environment: any): Promise<any> {
        return new Promise((resolve) => {
            const get: Promise<any> = Http.getInstance().get('/uaa/open/wxwork/appid?id=' + Environment.wxWorkAppId);
            get.then((response: any) => {
                if (response && response.status === 200) {
                    const data = response.data;
                    if (data && data.corp_id && data.appid) {
                        resolve({ state: "SUCCESS", data: data });
                    } else {
                        resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getAppidError')}，${data.detail}` });
                    }
                }
            }).catch((error: any) => {
                const data = error.data;
                if (data && data.detail) {
                    resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getAppidError')}，${data.detail}` });
                } else {
                    resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getAppidError')}` });
                }
            });
        })
    }

    /**
     * 钉钉内部免登
     * 
     * @memberof AppThirdService
     */
    public embedDingTalkLogin(Environment: any): Promise<any> {
        return new Promise((resolve) => {
            Http.getInstance().get(`/uaa/open/dingtalk/access_token?id=${Environment.dingTalkAppId}`).then((access_token: any) => {
                if (access_token.status == 200 && access_token.data && access_token.data.corp_id) {
                    dd.runtime.permission.requestAuthCode({ corpId: access_token.data.corp_id }).then((res: any) => {
                        if (res && res.code) {
                            Http.getInstance().get(`/uaa/open/dingtalk/auth/${res.code}?id=${Environment.dingTalkAppId}`).then((userInfo: any) => {
                                if (userInfo.status == 200 && userInfo.data.token && userInfo.data.user) {
                                    return { state: "SUCCESS", data: userInfo.data };
                                } else {
                                    return { state: "ERROR", message: `${userInfo.data.message}` };
                                }
                            })
                        } else {
                            resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getDingInfoError')}` });
                        }
                    }).catch((error: any) => {
                        resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getDingInfoError')}` });
                    })
                }
            }).catch((error: any) => {
                resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getIDError')}` });
            })
        })
    }

    /**
     * 企业微信内部免登
     * 
     * @memberof AppThirdService
     */
    public embedwxWorkLogin(Environment: any): Promise<any> {
        return new Promise((resolve) => {
            Http.getInstance().get(`/uaa/open/wxwork/access_token?id=${Environment.wxWorkAppId}`).then((result: any) => {
                if (result.status == 200 && result.data && result.data.corp_id) {
                    resolve({ state: "SUCCESS", data: result.data });
                } else {
                    resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getIDError')}` });
                }
            }).catch((error: any) => {
                resolve({ state: "ERROR", message: `${this.i18n('app.commonWords.getIDError')}` });
            })
        })


    }


}