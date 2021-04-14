/**
 * 视图引擎基类
 *
 * @export
 * @class ViewEngine
 */
export class ViewEngine {
    /**
     * 视图部件对象
     *
     * @protected
     * @type {*}
     * @memberof ViewEngine
     */
    protected view: any = null;
    /**
     * 引擎参数
     *
     * @type {*}
     * @memberof ViewEngine
     */
    protected opt: any = {};
    /**
     *
     *
     * @type {*}
     * @memberof ViewEngine
     */
    protected methods: any = {};

    /**
     * 是否默认记载
     *
     * @type {boolean}
     * @memberof ViewEngine
     */
    public isLoadDefault: boolean = true;

    /**
     * 实体主键属性
     *
     * @type {(string | undefined)}
     * @memberof ViewEngine
     */
    public keyPSDEField: string | undefined;

    /**
     * 实体主信息属性
     *
     * @type {(string | undefined)}
     * @memberof ViewEngine
     */
    public majorPSDEField: string | undefined;

    /**
     * Creates an instance of ViewEngine.
     * @memberof ViewEngine
     */
    constructor() { }

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof ViewEngine
     */
    public init(options: any = {}): void {
        this.opt = options;
        this.methods = options.methods;
        this.view = options.view;
        this.isLoadDefault = options.isLoadDefault;
        this.keyPSDEField = options.keyPSDEField;
        this.majorPSDEField = options.majorPSDEField;
        this.load();
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof ViewEngine
     */
    public load(opts: any = {}): void {

    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof ViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {

    }

    /**
     * 视图事件处理
     *
     * @param {string} eventName 事件tag
     * @param {*} args 事件参数
     * @memberof ViewEngine
     */
    public emitViewEvent(eventName: string, args: any): void {
        if(this.view) {
            this.view.$emit('view-event', { viewName: this.view.viewName, action: eventName, data: args });
        }
    }
 
    /**
     * 处理界面行为
     *
     * @param {string} tag
     * @param {string} [actionmode]
     * @memberof ViewEngine
     */
    public doSysUIAction(tag: string, actionmode?: string): void {
        if (Object.is(actionmode, 'FRONT')) {
            if (this.methods.front) {
                this.methods.front(tag);
            }
        }
    }

    /**
     * 处理工作流界面行为
     *
     * @param {string} tag
     * @param {string} [actionmode]
     * @memberof ViewEngine
     */
    public doSysWFUIAction(tag: string, actionmode?: string): void {
        if (Object.is(actionmode, 'WFFRONT')) {
            if (this.methods.wfFront) {
                this.methods.wfFront(tag);
            }
        }
    }

    /**
     * 是否为方法
     *
     * @protected
     * @param {*} func
     * @returns {boolean}
     * @memberof ViewEngine
     */
    protected isFunc(func: any): boolean {
        return func instanceof Function;
    }

    /**
     * 父数据参数模式
     *
     * @param {{ tag: string, action: string, viewdata: any }} { tag, action, viewdata }
     * @memberof ViewEngine
     */
    public setViewState2({ tag, action, viewdata }: { tag: string, action: string, viewdata: any }): void {
        this.view.viewState.next({ tag: tag, action: action, data: viewdata });
    }

    /**
     * 计算工具栏状态
     *
     * @param {boolean} state
     * @param {*} [dataaccaction]
     * @memberof ViewEngine
     */
    public calcToolbarItemState(state: boolean, dataaccaction?: any) {
        const _this: any = this;
        if (!_this.view.toolbarModels || (_this.view.toolbarModels.length === 0)) {
            return;
        }

        for (const key in _this.view.toolbarModels) {
            if (!_this.view.toolbarModels.hasOwnProperty(key)) {
                return;
            }
            const _item = _this.view.toolbarModels[key];
            if (_item.uiaction && (Object.is(_item.uiaction.actionTarget, 'SINGLEKEY') || Object.is(_item.uiaction.actionTarget, 'MULTIKEY'))) {
                _item.disabled = state;
            }
            _item.visabled = true;
            if(_item.noprivdisplaymode && _item.noprivdisplaymode === 6){
                _item.visabled = false;
            }
        }
    }

    /**
     * 计算工具栏权限状态
     *
     * @param {boolean} state
     * @param {*} [dataaccaction]
     * @memberof ViewEngine
     */
    public calcToolbarItemAuthState(data:any){
        const _this: any = this;
        if(!_this.view.appUIService)
            return;
        for (const key in _this.view.toolbarModels) {
            if (!_this.view.toolbarModels.hasOwnProperty(key)) {
                return;
            }
            const _item = _this.view.toolbarModels[key];
            if(_item && _item['dataaccaction']){
                let dataActionResult:any;
                if (_item.uiaction && (Object.is(_item.uiaction.actionTarget, "NONE") || Object.is(_item.uiaction.actionTarget, ""))){
                    if(Object.is(_item.uiaction.actionTarget, "") && Object.is(_item.uiaction.tag, "Save") && _this.view.appUIService.isEnableDEMainState){
                        if(data && Object.keys(data).length >0){
                            dataActionResult= _this.view.appUIService.getAllOPPrivs(data)[_item['dataaccaction']];       
                        }
                    }else{
                        dataActionResult = _this.view.appUIService.getResourceOPPrivs(_item['dataaccaction']);
                    }
                }else{
                    if(data && Object.keys(data).length >0 && _this.view.appUIService.isEnableDEMainState){
                        dataActionResult= _this.view.appUIService.getAllOPPrivs(data)[_item['dataaccaction']];       
                    }
                }
                // 无权限:0;有权限:1
                if(dataActionResult === 0){
                    // 禁用:1;隐藏:2;隐藏且默认隐藏:6
                    if(_item.noprivdisplaymode === 1){
                        _this.view.toolbarModels[key].disabled = true;
                    }
                    if((_item.noprivdisplaymode === 2) || (_item.noprivdisplaymode === 6)){
                        _this.view.toolbarModels[key].visabled = false;
                    }else{
                        _this.view.toolbarModels[key].visabled = true;
                    }
                }
                if(dataActionResult === 1){
                    _this.view.toolbarModels[key].visabled = true;
                    _this.view.toolbarModels[key].disabled = false;
                }
            }
        }
    }   

}