import { IPSAppViewRef, IPSDETree, IPSTreeExpBar } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';
import { ExpBarControlBase } from './expbar-control-base';
/**
 * 树视图导航栏部件基类
 *
 * @export
 * @class TreeExpBarControlBase
 * @extends {ExpBarControlBase}
 */
export class TreeExpBarControlBase extends ExpBarControlBase {

    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof TreeExpBarControlBase
     */
    public controlInstance!: IPSTreeExpBar;

    /**
     * 数据部件
     *
     * @memberof ListExpBarControlBase
     */
    protected $xDataControl!: IPSDETree;

    /**
     * 刷新标识
     *
     * @public
     * @type {number}
     * @memberof TreeExpBarControlBase
     */
    public counter: number = 0;

    /**
     * 分割宽度
     *
     * @public
     * @type {number}
     * @memberof TreeExpBarControlBase
     */
    public split: number = 0.15;
    
    /**
    * 处理数据部件参数
    *
    * @memberof ExpBarControlBase
    */
    public async handleXDataCtrlOptions() {
        await super.handleXDataCtrlOptions();
    }

    /**
     * 获取关系项视图
     *
     * @param {*} [arg={}]
     * @returns {*}
     * @memberof TreeExpBarControlBase
     */
    public async getExpItemView(arg: any = {}) {
        let expmode = 'EXPITEM:' + arg.nodetype.toUpperCase();
        if(this.controlInstance?.getPSAppViewRefs()){
            for(let viewRef of (this.controlInstance?.getPSAppViewRefs()) as IPSAppViewRef[]){
                if (Object.is(expmode, viewRef.name.toUpperCase())) {
                    let view = {
                        viewModelData: viewRef.getRefPSAppView(),
                        parentdata: viewRef.parentDataJO || {},
                        deKeyField: viewRef.getRefPSAppView()?.getPSAppDataEntity()?.codeName?.toLowerCase(),
                    }
                    await view.viewModelData?.fill();
                    return view;
                }
            }
        }
        return null;
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof ExpBarControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        this.xDataControlName = this.controlInstance.xDataControlName;
        this.$xDataControl = ModelTool.findPSControlByName(this.xDataControlName, this.controlInstance.getPSControls());
    }

    /**
     * 树导航选中
     *
     * @param {any []} args
     * @param {string} [tag]
     * @param {*} [$event2]
     * @returns {void}
     * @memberof TreeExpBarControlBase
     */
    public async onSelectionChange(args: any[], tag?: string, $event2?: any): Promise<void> {
        this.dragstate = false;
        if (args.length === 0) {
            this.calcToolbarItemState(true);
            return;
        }
        const arg: any = args[0];
        if (!arg.id) {
            this.calcToolbarItemState(true);
            return;
        }
        const nodetype = arg.id.split(';')[0];
        const refview: any = await this.getExpItemView({ nodetype: nodetype });
        if (!refview) {
            this.calcToolbarItemState(true);
            return;
        }
        let tempViewparam: any = {};
        let tempContext: any = {};
        if (arg && arg.navfilter) {
            this.counter += 1;
            Object.defineProperty(tempViewparam, arg.navfilter, {
                value: arg.srfkey,
                writable: true,
                enumerable: true,
                configurable: true
            })
            Object.assign(tempContext, { srfcounter: this.counter });
        }
        Object.assign(tempContext, JSON.parse(JSON.stringify(this.context)));
        if (arg.srfappctx) {
            Object.assign(tempContext, JSON.parse(JSON.stringify(arg.srfappctx)));
        }
        // 计算导航上下文
        if (arg && arg.navigateContext && Object.keys(arg.navigateContext).length > 0) {
            let tempData: any = arg.curData ? JSON.parse(JSON.stringify(arg.curData)) : {};
            Object.assign(tempData, arg);
            let _context = this.$util.computedNavData(tempData, tempContext, tempViewparam, arg.navigateContext);
            Object.assign(tempContext, _context);
        }
        if (arg.srfparentdename) {
            Object.assign(tempContext, { srfparentdename: arg.srfparentdename });
        }
        if (arg.srfparentdemapname) {
            Object.assign(tempContext, { srfparentdemapname: arg.srfparentdemapname });
        }
        if (arg.srfparentkey) {
            Object.assign(tempContext, { srfparentkey: arg.srfparentkey });
        }
        // 计算导航参数
        if (arg && arg.navigateParams && Object.keys(arg.navigateParams).length > 0) {
            let tempData: any = arg.curData ? JSON.parse(JSON.stringify(arg.curData)) : {};
            Object.assign(tempData, arg);
            let _params = this.$util.computedNavData(tempData, tempContext, tempViewparam, arg.navigateParams);
            Object.assign(tempViewparam, _params);
            this.counter += 1;
            Object.assign(tempContext, { srfcounter: this.counter });
        }
        this.selection = {};
        Object.assign(tempContext, { viewpath: refview.viewModelData.modelFilePath });
        Object.assign(this.selection, { view: { viewname: 'app-view-shell' }, context: tempContext, viewparam: tempViewparam });
        this.calcToolbarItemState(false);
        this.$forceUpdate();
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof TreeExpBarControlBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isBranchAvailable: true
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 执行搜索
     *
     * @memberof TreeExpBarControlBase
     */
    public onSearch(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if (this.viewState && this.$xDataControl) {
            this.viewState.next({ tag: this.xDataControlName, action: 'filter', data: { srfnodefilter: this.searchText } });
        }
    }

    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof TreeExpBarControlBase
     */
    public onViewDatasChange($event: any): void {
        this.$emit("ctrl-event", { controlName: this.controlInstance?.name, action: "selectionchange", data: $event });
    }

    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof TreeExpBarControlBase
     */
    public onDrViewDatasChange($event: any): void {
        if (this.$xDataControl) {
            this.viewState.next({ tag: this.xDataControlName, action: 'refresh_parent' });
        }
    }

    /**
     * 视图数据被激活
     *
     * @param {*} $event
     * @memberof TreeExpBarControlBase
     */
    public viewDatasActivated($event: any): void {
        this.$emit("ctrl-event", { controlName: this.controlInstance.name, action: "activated", data: $event });
    }

    /**
     * 视图数据加载完成
     *
     * @param {*} $event
     * @memberof TreeExpBarControlBase
     */
    public onViewLoad($event: any): void {
        this.$emit("ctrl-event", { controlName: this.controlInstance.name, action: "load", data: $event });
    }

}
