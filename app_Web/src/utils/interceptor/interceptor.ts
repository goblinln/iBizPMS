import { Store } from 'vuex';
import Router from 'vue-router';
import i18n from '@/locale';
import { Environment } from '@/environments/environment';
import { Util, Http } from 'ibiz-core';
import { AppLoadingService, nsc } from 'ibiz-vue';

/**
 * 拦截器
 *
 * @export
 * @class Interceptors
 */
export class Interceptors {

    /**
     * 路由对象
     *
     * @private
     * @type {(Router | any)}
     * @memberof Interceptors
     */
    private router: Router | any;

    /**
     * 缓存对象
     *
     * @private
     * @type {(Store<any> | any)}
     * @memberof Interceptors
     */
    private store: Store<any> | any;

    /**
     *  单列对象
     *
     * @private
     * @static
     * @type {LoadAppData}
     * @memberof Interceptors
     */
    private static readonly instance: Interceptors = new Interceptors();

    /**
     * Creates an instance of Interceptors.
     * 私有构造，拒绝通过 new 创建对象
     * 
     * @memberof Interceptors
     */
    private constructor() {
        if (Interceptors.instance) {
            return Interceptors.instance;
        } else {
            this.intercept();
        }
    }

    /**
     * 获取 LoadAppData 单例对象
     *
     * @static
     * @param {Router} route
     * @param {Store<any>} store
     * @returns {Interceptors}
     * @memberof Interceptors
     */
    public static getInstance(route: Router, store: Store<any>): Interceptors {
        this.instance.router = route;
        this.instance.store = store;
        return this.instance;
    }

    /**
     * 拦截器实现接口
     *
     * @private
     * @memberof Interceptors
     */
    private intercept(): void {
        Http.getHttp().interceptors.request.use((config: any) => {
            let appdata: any;
            if (this.router) {
                appdata = this.store.getters.getAppData();
            }
            if (appdata && appdata.context) {
                config.headers['srforgsectorid'] = appdata.context.srforgsectorid;
            }
            if(Environment.SaaSMode){
                let activeOrgData:any = this.store.getters.getActiveOrgData();
                config.headers['srforgid'] =  activeOrgData.orgid;
                config.headers['srfsystemid'] =  activeOrgData.systemid;
            }
            if (!window.localStorage.getItem('token')) {
                let arr;
                let reg = new RegExp("(^| )ibzuaa-token=([^;]*)(;|$)");
                if (arr = document.cookie.match(reg)) {
                    window.localStorage.setItem('token', unescape(arr[2]));
                }
            }
            if (window.localStorage.getItem('token')) {
                const token = window.localStorage.getItem('token');
                config.headers['Authorization'] = `Bearer ${token}`;
            } else {
                // 第三方应用打开免登
                if (sessionStorage.getItem("srftoken")) {
                    const token = sessionStorage.getItem('srftoken');
                    config.headers['Authorization'] = `Bearer ${token}`;
                }
            }
            config.headers['Accept-Language'] = i18n.locale;
            if (!Object.is(Environment.BaseUrl, "") && !config.url.startsWith('https://') && !config.url.startsWith('http://') && !config.url.startsWith('./assets')) {
                config.url = Environment.BaseUrl + config.url;
            }

            // 开启loading
            this.beginLoading();

            return config;
        }, (error: any) => {
            return Promise.reject(error);
        });

        Http.getHttp().interceptors.response.use((response: any) => {
            if (response.headers && response.headers['refreshtoken'] && localStorage.getItem('token')) {
                this.refreshToken(response);
            }

            // 关闭loading
            this.endLoading();

            return response;
        }, (error: any) => {
            // 关闭loading
            this.endLoading();
            
            error = error ? error : { response: {} };
            let { response: res } = error;
            let { data: _data } = res;
            // 处理异常
            if (res.headers && res.headers['x-ibz-error']) {
                res.data.errorKey = res.headers['x-ibz-error'];
            }
            if (res.headers && res.headers['x-ibz-params']) {
                res.data.entityName = res.headers['x-ibz-params'];
            }
            if (res.status === 401) {
                this.doNoLogin(_data.data);
            }
            if (res.status === 403) {
                if (res.data && res.data.status && Object.is(res.data.status, "FORBIDDEN")) {
                    let alertMessage: string = "非常抱歉，您无权操作此数据，如需操作请联系管理员！";
                    Object.assign(res.data, { localizedMessage: alertMessage, message: alertMessage });
                }
            }
            // if (res.status === 404) {
            //     this.router.push({ path: '/404' });
            // } else if (res.status === 500) {
            //     this.router.push({ path: '/500' });
            // }

            return Promise.reject(res);
        });
    }

    /**
     * 处理未登录异常情况
     *
     * @private
     * @param {*} [data={}]
     * @memberof Interceptors
     */
    private doNoLogin(data: any = {}): void {
        // 清除user、token和cookie
        if (localStorage.getItem('user')) {
            localStorage.removeItem('user');
        }
        if (localStorage.getItem('token')) {
            localStorage.removeItem('token');
        }
        let leftTime = new Date();
        leftTime.setTime(leftTime.getSeconds() - 1);
        document.cookie = "ibzuaa-token=;expires=" + leftTime.toUTCString();
        if (data.loginurl && !Object.is(data.loginurl, '') && data.originurl && !Object.is(data.originurl, '')) {
            let _url = encodeURIComponent(encodeURIComponent(window.location.href));
            let loginurl: string = data.loginurl;
            const originurl: string = data.originurl;

            if (originurl.indexOf('?') === -1) {
                _url = `${encodeURIComponent('?RU=')}${_url}`;
            } else {
                _url = `${encodeURIComponent('&RU=')}${_url}`;
            }
            loginurl = `${loginurl}${_url}`;

            window.location.href = loginurl;
        } else {
            if (Object.is(this.router.currentRoute.name, 'login')) {
                return;
            }
            this.router.push({ name: 'login', query: { redirect: this.router.currentRoute.fullPath } });
        }
    }

    /**
     * 刷新token
     *
     * @private
     * @param {*} [data={}]
     * @memberof Interceptors
     */
    private refreshToken(data: any = {}): void {
        if (data && data.config && (data.config.url == "/uaa/refreshToken")) {
            return;
        }
        Http.getInstance().post('/uaa/refreshToken', localStorage.getItem('token'), false).then((response: any) => {
            if (response && response.status === 200) {
                const data = response.data;
                if (data) {
                    localStorage.setItem('token', data);
                    Util.setCookie('ibzuaa-token', data, 0);
                }
            } else {
                console.log("刷新token出错");
            }
        }).catch((error: any) => {
            console.log("刷新token出错");
        });
    }

    /**
     * 开始加载
     *
     * @private
     * @memberof Http
     */
     private beginLoading(): void {
        AppLoadingService.getInstance().beginLoading();
    }

    /**
     * 加载结束
     *
     * @private
     * @memberof Http
     */
    private endLoading(): void {
        AppLoadingService.getInstance().endLoading();
    }

}