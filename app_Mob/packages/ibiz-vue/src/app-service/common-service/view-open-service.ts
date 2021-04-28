import Router from 'vue-router';
import qs from 'qs';
import { Http, Util, HttpResponse } from 'ibiz-core';
import { AppModal } from '../../utils/app-modal/app-modal';
import { AppDrawer } from '../../utils/app-drawer/app-drawer';

/**
 * 界面打开服务
 *
 * @export
 * @class ViewOpenService
 */
export class ViewOpenService {

    /**
     * 
     *
     * @private
     * @type {(Router | any)}
     * @memberof ViewOpenService
     */
    private router: Router | any;

    /**
     * 唯一实例
     *
     * @private
     * @static
     * @type {ViewOpenService}
     * @memberof ViewOpenService
     */
    private static readonly instance: ViewOpenService = new ViewOpenService();

    /**
     * http请求服务
     *
     * @protected
     * @type {Http}
     * @memberof ViewOpenService
     */
    protected http: Http = Http.getInstance();

    /**
     * Creates an instance of ViewOpenService.
     * @memberof ViewOpenService
     */
    private constructor() {
        if (ViewOpenService.instance) {
            return ViewOpenService.instance;
        }
    }

    /**
     * 格式化路由数据
     *
     * @param {*} context
     * @param {*} deResParameters
     * @param {*} parameters
     * @param {*} args
     * @param {*} data
     * @returns {*}
     * @memberof ViewOpenService
     */
    public formatRouteParam(context: any, deResParameters: any, parameters: any, args: any, data: any): any {
        let urlStr: string = '';
        if (Array.isArray(deResParameters) && deResParameters.length > 0) {
            deResParameters.forEach(({ pathName: _pathName, parameterName: _parameterName }: { pathName: string, parameterName: string }) => {
                let value: any = null;
                if (context[_parameterName] && !Object.is(context[_parameterName], '') && !Object.is(context[_parameterName], 'null')) {
                    value = context[_parameterName];
                }
                urlStr = `${urlStr}/${_pathName}/${value}`;
            });
        }
        if (Array.isArray(deResParameters) && parameters.length === 2) {
            const [{ pathName: _pathName, parameterName: _parameterName }, { pathName: _pathName2, parameterName: _parameterName2 }] = parameters;
            const _value: any = context[_parameterName] && !Object.is(context[_parameterName], '') ? context[_parameterName] : null;
            urlStr = `${urlStr}/${_pathName}/${_value}/${_pathName2}`;
            if (Object.keys(data).length > 0) {
                urlStr = `${urlStr}?${qs.stringify(data, { delimiter: '&' })}`;
            }
        } else if (Array.isArray(deResParameters) && parameters.length === 1) {
            const [{ pathName: _pathName, parameterName: _parameterName }] = parameters;
            urlStr = `${urlStr}/${_pathName}`;
            if (Object.keys(data).length > 0) {
                urlStr = `${urlStr}?${qs.stringify(data, { delimiter: '&' })}`;
            }
        }
        if (!Object.is(urlStr, '')) {
            let indexViewContext = {};
            if (Object.keys(indexViewContext).length > 0) {
                let indexUrl = qs.stringify(indexViewContext, { delimiter: ';' })?qs.stringify(indexViewContext, { delimiter: ';' }):null;
                urlStr = `/viewshell/${indexUrl}${urlStr}`;
            } else {
                urlStr = `/viewshell/null${urlStr}`;
            }
        }
        return urlStr;
    }

    /**
     * 路由打开
     *
     * @param {*} opts
     * @returns {Promise<any>}
     * @memberof ViewOpenService
     */
    public async openView(opts: any): Promise<any> {
        if (opts && !Object.is(opts, '')) {
            this.router.push(opts);
        }
        Promise.resolve();
    }

    /**
     * 模态打开
     *
     * @param {*} view
     * @param {*} context
     * @param {*} param
     * @returns {Promise<any>}
     * @memberof ViewOpenService
     */
    public async openModal(view: any, context: any, param: any): Promise<any> {
        return await AppModal.getInstance().openModal(view, context, param);
    }

    /**
     * 抽屉打开
     *
     * @param {*} view
     * @param {*} context
     * @param {*} param
     * @returns {Promise<any>}
     * @memberof ViewOpenService
     */
    public async openDrawer(view: any, context: any, param: any): Promise<any> {
        return await AppDrawer.getInstance().openDrawer(view, context, param);
    }

    /**
     * 气泡卡片打开
     *
     * @param {*} view
     * @param {*} context
     * @param {*} param
     * @returns {Promise<any>}
     * @memberof ViewOpenService
     */
    public async openPopOver(view: any, context: any, param: any): Promise<any> {
        throw new Error('气泡打开界面未实现');
    }

    /**
     * 浏览器新标签页打开
     *
     * @param {string} url
     * @returns {Promise<any>}
     * @memberof ViewOpenService
     */
    public async openPopupApp(url: string): Promise<any> {
        window.open(url, '_blank');
        Promise.resolve();
    }

    /**
     * 打开重定向视图
     *
     * @param {string} url
     * @param {*} context
     * @param {*} data
     * @returns {Promise<any>}
     * @memberof ViewOpenService
     */
    public async openRedirect(url: string, context: any, data: any): Promise<any> {
        const response: HttpResponse = await this.http.post(url, data);
        if (response.status === 200 && response.data) {
            const result: any = response.data;
            // 添加额外参数
            if (result.viewparams && Object.keys(result.viewparams)) {
                Object.assign(data, result.viewparams);
            }
            if (Object.is(result.openmode, 'POPUPAPP')) {
                return this.openPopupApp(result.url);
            } else if (Object.is(result.openmode, 'INDEXVIEWTAB') || Object.is(result.openmode, '')) {
                const viewPath: string = `${result.viewmodule}_${result.viewname}`.toLowerCase();
                return this.openView({ viewpath: viewPath, params: data });
            } else if (Object.is(result.openmode, 'POPUPMODAL')) {
                const viewname: string = Util.srfFilePath2(result.viewname);
                const view: any = {
                    viewname,
                    title: result.title,
                    width: result.width,
                    height: result.height,
                }
                return this.openModal(view, context, data);
            } else if (result.openmode.startsWith('DRAWER')) {
                const viewname: string = Util.srfFilePath2(result.viewname);
                const view: any = {
                    viewname: viewname,
                    title: result.title,
                    width: result.width,
                    height: result.height,
                    placement: result.openmode,
                }
                return this.openDrawer(view, context, data);
            } else if (Object.is(result.openmode, 'POPOVER')) {
                const viewname: string = Util.srfFilePath2(result.viewname);
                const view: any = {
                    viewname: viewname,
                    title: result.title,
                    width: result.width,
                    height: result.height,
                    placement: result.openmode,
                }
                return this.openPopOver(view, context, data);
            }
        }
        return response;
    }


    /**
     * 获取实例
     *
     * @static
     * @param {Route} router
     * @returns {ViewOpenService}
     * @memberof ViewOpenService
     */
    public static getInstance(router?: Router): ViewOpenService {
        if (router) {
            this.instance.router = router;
        }
        return this.instance;
    }

}