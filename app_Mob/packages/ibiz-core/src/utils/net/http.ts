import axios from 'axios';
import qs from 'qs';
import { Util } from '../util/util';

/**
 * Http net 对象
 * 调用 getInstance() 获取实例
 *
 * @class Http
 */
export class Http {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {Http}
     * @memberof Http
     */
    private static Http: Http;

    /**
     * 获取 Http 单例对象
     *
     * @static
     * @returns {Http}
     * @memberof Http
     */
    public static getInstance(): Http {
        if (!Http.Http) {
            Http.Http = new Http();
        }
        return this.Http;
    }

    /**
     * Creates an instance of Http.
     * 私有构造，拒绝通过 new 创建对象
     * 
     * @memberof Http
     */
    private constructor() { }

    /**
     * 网络请求对象
     * 
     * @memberof Http
     */
    public static getHttp() {
        return axios;
    }

    /**
     * post请求
     *
     * @param {string} url
     * @param {*} [params={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof Http
     */
    public post(url: string, params: any = {}, isloading?: boolean): Promise<any> {
        params = this.handleRequestData(params);
        url = this.handleAppParam(url, params);
        return new Promise((resolve: any, reject: any) => {
            axios({
                method: 'post',
                url: url,
                data: params,
                headers: { 'Content-Type': 'application/json;charset=UTF-8', 'Accept': 'application/json' },

            }).then((response: any) => {
                this.doResponseRresult(response, resolve, isloading);
            }).catch((response: any) => {
                this.doResponseRresult(response, reject, isloading);
            });
        });
    }

    /**
     * 获取
     *
     * @param {string} url
     * @param {*} [params={}]
     * @param {boolean} [isloading]
     * @param {number} [serialnumber]
     * @returns {Promise<any>}
     * @memberof Http
     */
    public get(url: string, params: any = {}, isloading?: boolean, serialnumber?: number): Promise<any> {
        params = this.handleRequestData(params);
        if (params.srfparentdata) {
            Object.assign(params, params.srfparentdata);
            delete params.srfparentdata;
        }
        if ((Object.keys(params)).length > 0) {
            let tempParam: any = {};
            Object.keys(params).forEach((item: any) => {
                if (params[item] || Object.is(params[item], 0)) {
                    tempParam[item] = params[item];
                }
            })
            url += `?${qs.stringify(tempParam)}`;
        }
        return new Promise((resolve: any, reject: any) => {
            axios.get(url).then((response: any) => {
                this.doResponseRresult(response, resolve, isloading);
            }).catch((response: any) => {
                this.doResponseRresult(response, reject, isloading);
            });
        });
    }

    /**
     * 删除
     *
     * @param {string} url
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof Http
     */
    public delete(url: string, isloading?: boolean, data?: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            url = this.handleAppParam(url, data);
            if (!data) {
                axios.delete(url).then((response: any) => {
                    this.doResponseRresult(response, resolve, isloading);
                }).catch((response: any) => {
                    this.doResponseRresult(response, reject, isloading);
                });
            } else {
                axios.delete(url, { data: data }).then((response: any) => {
                    this.doResponseRresult(response, resolve, isloading);
                }).catch((response: any) => {
                    this.doResponseRresult(response, reject, isloading);
                });
            }
        });
    }

    /**
     * 修改数据
     *
     * @param {string} url
     * @param {*} data
     * @param {boolean} [isloading]
     * @param {number} [serialnumber]
     * @returns {Promise<any>}
     * @memberof Http
     */
    public put(url: string, data: any, isloading?: boolean, serialnumber?: number): Promise<any> {
        data = this.handleRequestData(data);
        url = this.handleAppParam(url, data);
        return new Promise((resolve: any, reject: any) => {
            axios.put(url, data).then((response: any) => {
                this.doResponseRresult(response, resolve, isloading);
            }).catch((response: any) => {
                this.doResponseRresult(response, reject, isloading);
            });
        });
    }

    /**
     * 处理响应结果
     *
     * @private
     * @param {*} response
     * @param {Function} funt
     * @param {boolean} [isloading]
     * @memberof Http
     */
    private doResponseRresult(response: any, funt: Function, isloading?: boolean): void {
        funt(response);
    }

    /**
     * 处理请求数据
     * 
     * @private
     * @param data 
     * @memberof Http
     */
    private handleRequestData(data: any) {
        if (data.srfsessionkey) {
            delete data.srfsessionkey;
        }
        if (data.srfsessionid) {
            delete data.srfsessionid;
        }
        return data;
    }

    /**
     * 处理响应结果
     *
     * @private
     * @param {*} response
     * @param {Function} fn
     * @param {boolean} [isloading]
     * @memberof Http
     */
    private doResponseResult(response: any, fn: Function): void {
        if (response.status === 200) {
            response.ok = true;
        }
        fn(response);
    }

    /**
     * 处理系统级数据（以srf开始的字段）
     *
     * @private
     * @param url 原始路径
     * @param params 原始参数
     * @memberof Http
     */
    private handleAppParam(url: string, params: any): string {
        if (params && (Object.keys(params).length > 0)) {
            let tempParam: any = {};
            Object.keys(params).forEach((item: string) => {
                if (item.startsWith('srf') && !Util.isEmpty(params[item])) {
                    tempParam[item] = params[item];
                }
            });
            // 过滤前端标识属性
            if (tempParam && Object.is(tempParam['srfinsttag'], '__srfstdinst__')) {
                delete tempParam['srfinsttag'];
            }
            if (tempParam && (Object.keys(tempParam).length > 0)) {
                url += `?${qs.stringify(tempParam)}`;
            }
            return url;
        }
        return url;
    }

    /**
     * 获取模型
     *
     * @param {string} url
     * @param {*} [headers={}]
     * @returns {Promise<any>}
     * @memberof Http
     */
    public getModel(url: string, headers: any = {}): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            axios
                .get(url, {
                    headers: headers
                })
                .then((response: any) => {
                    this.doResponseResult(response, resolve);
                })
                .catch((response: any) => {
                    this.doResponseResult(response, reject);
                });
        });
    }
}