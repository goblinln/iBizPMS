import { Route } from 'vue-router';
import qs from 'qs';
import { Util } from 'ibiz-core';
export class ViewTool {

    /**
     * 唯一实例
     *
     * @private

     * @type {ViewTool}
     * @memberof ViewTool
     */
    private static readonly instance: ViewTool = new ViewTool();

    /**
     * Creates an instance of ViewTool.
     * 私有构造，拒绝通过 new 创建对象
     * @memberof ViewTool
     */
    private constructor() {
        if (ViewTool.instance) {
            return ViewTool.instance;
        }
    }


    /**
     * 获取 ViewTool 单例对象
     *

     * @returns {ViewTool}
     * @memberof ViewTool
     */
    public static getInstance(): ViewTool {
        return this.instance;
    }
    /**
     * 解析参数，返回viewdata
     *

     * @param {any[]} [args] 多项数据
     * @param {*} [viewParam] 视图参数
     * @param {any[]} [deResParameters] 关系实体参数对象
     * @param {any[]} [parameters] 当前应用视图参数对象
     * @param {*} [data] 行为参数
     * @returns
     * @memberof ViewTool
     */
    public getViewdata(viewParam: any = {}, deResParameters: any[], parameters: any[], args: any[], data: any = {}): any {
        let viewdata: any = {};

        let [arg] = args;
        arg = arg ? arg : {};

        // 首页视图参数
        const indexViewParam: any = this.getIndexViewParam();
        Object.assign(viewdata, indexViewParam);

        // 关系应用实体参数
        deResParameters.forEach(({ pathName, parameterName }: { pathName: string, parameterName: string }) => {
            if (viewParam[parameterName] && !Object.is(viewParam[parameterName], '')) {
                Object.assign(viewdata, { [parameterName]: viewParam[parameterName] });
            } else if (arg[parameterName] && !Object.is(arg[parameterName], '')) {
                Object.assign(viewdata, { [parameterName]: arg[parameterName] });
            }
        });

        // 当前视图参数（应用实体视图）
        parameters.forEach(({ pathName, parameterName }: { pathName: string, parameterName: string }) => {
            if (arg[parameterName] && !Object.is(arg[parameterName], '')) {
                Object.assign(viewdata, { [parameterName]: arg[parameterName] });
            }
        });

        // 视图常规参数
        Object.assign(viewdata, data);
        // 传入父视图的srfsessionid
        Object.assign(viewdata, { srfsessionid: viewParam['srfsessionid'] });
        return viewdata;
    }

    /**
     * 格式化路由参数
     *
     * @param {*} params
     * @param {Route} route
     * @param {*} context
     * @param {*} viewparams
     * @memberof ViewTool
     */
    public formatRouteParams(params: any, route: Route, context: any, viewparams: any): void {
        Object.keys(params).forEach((key: string, index: number) => {
            const param: string | null | undefined = params[key];
            if (!param || Object.is(param, '') || Object.is(param, 'null')) {
                return;
            }
            if (param.indexOf('=') > 0) {
                const _param = qs.parse(param, { delimiter: ';' });
                Object.assign(context, _param);
            } else {
                Object.assign(context, { [key]: param });
            }
        });
        if (route && route.fullPath && route.fullPath.indexOf("?") > -1) {
            const _viewparams: any = route.fullPath.slice(route.fullPath.indexOf("?") + 1);
            const _viewparamArray: Array<string> = decodeURIComponent(_viewparams).split(";")
            if (_viewparamArray.length > 0) {
                _viewparamArray.forEach((item: any) => {
                    Object.assign(viewparams, qs.parse(item));
                })
            }
        }
    }

    /**
     * 首页路由结构参数
     *
     * @private
     * @type {any[]}
     * @memberof ViewTool
     */
    private indexParameters: any[] = [];

    /**
     * 设置首页路由结构参数
     *
     * @param {any[]} parameters
     * @memberof ViewTool
     */
    public setIndexParameters(parameters: any[]): void {
        this.indexParameters = [...parameters]
    }

    /**
     * 获取首页路由结构参数
     *
     * @returns {any[]}
     * @memberof ViewTool
     */
    public getIndexParameters(): any[] {
        return this.indexParameters;
    }

    /**
     * 首页视图参数
     *
     * @type {*}
     * @memberof ViewTool
     */
    public indexViewParam: any = {};

    /**
     * 设置首页视图参数
     *
     * @param {*} [viewParam={}]
     * @memberof ViewTool
     */
    public setIndexViewParam(viewParam: any = {}): void {
        Object.assign(this.indexViewParam, viewParam);
    }

    /**
     * 获取首页视图参数
     *
     * @returns {*}
     * @memberof ViewTool
     */
    public getIndexViewParam(): any {
        return this.indexViewParam;
    }

    /**
     * 判断是否为 null
     *
     * @param {*} o
     * @returns {boolean}
     * @memberof ViewTool
     */
    public isNull(o: any): boolean {
        return Object.prototype.toString.call(o).slice(8, -1) === 'Null'
    }

    /**
     * 判断是否为 undefined
     *
     * @param {*} o
     * @returns {boolean}
     * @memberof ViewTool
     */
    public isUndefined(o: any): boolean {
        return Object.prototype.toString.call(o).slice(8, -1) === 'Undefined'
    }


    /**
     * 是否字符串
     *
     * @param {*} o
     * @returns {boolean}
     * @memberof ViewTool
     */
    public isString(o: any): boolean {
        return Object.prototype.toString.call(o).slice(8, -1) === 'String'
    }

    /**
     * 导航参数处理
     *
     * @param {*} [navigateContext={}] 导航上下文
     * @param {*} [navigateParam={}] 导航参数
     * @param {*} [context={}] 默认上下文
     * @param {*} [viewparams={}] 默认参数
     * @param {*} [formData={}] 表单数据
     * @returns {{ context: {}, param: {} }} 返回处理后直接可用的上下文和参数对象
     * @memberof ViewTool
     */
    public formatNavigateParam(navigateContext: any = {}, navigateParam: any = {}, context: any = {}, viewparams: any = {}, formData: any = {}): { context: {}, param: {} } {
        // 填充默认上下文和默认参数
        let _itemParam = { context: { ...context }, param: { ...viewparams } };
        // 处理导航上下文
        this.formatNavigateParamItem(navigateContext, _itemParam, formData, 'context');
        // 处理导航参数
        this.formatNavigateParamItem(navigateParam, _itemParam, formData, 'param');
        return _itemParam;
    }

    /**
     * 导航参数项处理
     *
     * @param {*} [param] 导航参数项
     * @param {*} [itemPara] 填充默认上下文和默认参数
     * @param {*} [formData] 表单数据
     * @param {*} [tag] 参数类型
     * @memberof ViewTool
     */
    private  formatNavigateParamItem(param: any, itemParam: any, formData: any, tag: string){
        if (param && Object.keys(param).length > 0) {
            Object.keys(param).forEach((name: string) => {
                let value: string | null = param[name];
                if (!name || !this.isString(value)) {
                    return;
                }
                if (itemParam[tag].hasOwnProperty(name) && (this.isNull(value) || this.isUndefined(value)) || value =='null') {
                    delete itemParam[tag][name];
                    return;
                }
                if (value && value.startsWith('%') && value.endsWith('%')) {
                    const key: string = value.slice(1, -1);
                    let has_value = false;
                    // 后续补充全局上下文
                    if (itemParam.context && itemParam.context.hasOwnProperty(key)) {
                        has_value = true;
                        value = itemParam.context[key];
                    }
                    // 后续补充全局参数
                    if (itemParam.param && itemParam.param.hasOwnProperty(key)) {
                        has_value = true;
                        value = itemParam.param[key];
                    }
                    if (formData && formData.hasOwnProperty(key)) {
                        has_value = true;
                        value = formData[key];
                    }
                    // 不存在值对象
                    if (!has_value) {
                        return;
                    }
                }
                Object.assign(itemParam[tag], { [name]: value });
            });
        }
    }

    /**
     * 视图参数处理
     *
     * @param {*} view
     * @param {boolean} isPSDEView
     * @returns {{ context: {}, param: {} }} 返回处理后直接可用的上下文和参数对象
     * @memberof ViewTool
     */
    public formatNavigateViewParam(view: any, isPSDEView: boolean): { context: {}, param: {} } {
        let _context: any = {}, _param: any = {};
        // 合并全局上下文
        const { context, param } = view.$store.getters.getAppData();
        if (context && Object.keys(context).length > 0) {
            _context = { ...context };
        }
        // 合并全局参数
        if (param && Object.keys(param).length > 0) {
            _param = { ...param };
        }


        if (view.viewDefaultUsage != "routerView") {
            // 视图模态或者嵌入打开
            if (view._context && !Object.is(view._context, '')) {
                _context = { ..._context, ...JSON.parse(view._context) };
            }
            if (view._viewparams && !Object.is(view._viewparams, '')) {
                _param = { ..._param, ...JSON.parse(view._viewparams) };
            }
        } else {
            // 视图路由打开
            const path = (view.$route.matched[view.$route.matched.length - 1]).path;
            const keys: Array<any> = [];
            const curReg = view.$pathToRegExp.pathToRegexp(path, keys);
            const matchArray = curReg.exec(view.$route.path);
            keys.forEach((item: any, index: number) => {
                Object.assign(_context, { [item.name]: matchArray[index + 1]=='null'?null:matchArray[index + 1] });
            });
            if (_context.hasOwnProperty('viewshell')) {
                let viewshell: string = _context['viewshell'];
                if (!Object.is(viewshell, 'null')) {
                    _context = { ..._context, ...qs.parse(viewshell) }
                }
                delete _context.viewshell;
            }
            if (view.$route && view.$route.fullPath && view.$route.fullPath.indexOf("?") > -1) {
                const viewParamStr = view.$route.fullPath.slice(view.$route.fullPath.indexOf("?") + 1);
                _param = { ..._param, ...qs.parse(viewParamStr, { delimiter: '&' }) };
            }
        }

        let data = this.formatNavigateParam(view.navContext, view.navParam, _context, _param, {});

        if (isPSDEView && !data.context.hasOwnProperty('srfsessionid')) {
            Object.assign(data.context, { srfsessionid: Util.createUUID() });
        }
        return data;
    }

    /**
     * 计算界面行为项权限状态
     *
     * @static
     * @param {*} [data] 传入数据
     * @param {*} [ActionModel] 界面行为模型
     * @param {*} [UIService] 界面行为服务
     * @memberof ViewTool
     */
    public calcActionItemAuthState(data:any,ActionModel:any,UIService:any){
        for (const key in ActionModel) {
            if (!ActionModel.hasOwnProperty(key)) {
                return;
            }
            const _item = ActionModel[key];
            if(_item && _item['dataaccaction'] && UIService && data && Object.keys(data).length >0){
                let dataActionResult:any = UIService.getAllOPPrivs(data)[_item['dataaccaction']];
                // 无权限:0;有权限:1
                if(dataActionResult === 0){
                    // 禁用:1;隐藏:2;隐藏且默认隐藏:6
                    if(_item.noprivdisplaymode === 1){
                        _item.disabled = true;
                    }
                    if((_item.noprivdisplaymode === 2) || (_item.noprivdisplaymode === 6)){
                        _item.visabled = false;
                    }else{
                        _item.visabled = true;
                    }
                }
                if(dataActionResult === 1){
                    _item.visabled = true;
                    _item.disabled = false;
                }
            }
        }
    }
}