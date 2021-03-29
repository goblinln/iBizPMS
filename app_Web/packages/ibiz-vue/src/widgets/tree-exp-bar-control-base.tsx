import { IBizTreeExpBarModel } from 'ibiz-core';
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
    public controlInstance!: IBizTreeExpBarModel;

    /**
     * 分割宽度
     *
     * @type {number}
     * @memberof TreeExpBarControlBase
     */
    public split: number = 0.15;

    /**
     * 刷新标识
     *
     * @public
     * @type {number}
     * @memberof TreeExpBarControlBase
     */
    public counter: number = 0;

    /**
     * 获取关系项视图
     *
     * @param {*} [arg={}]
     * @returns {*}
     * @memberof TreeExpBarControlBase
     */
    public getExpItemView(arg: any = {}) {
        let expmode = arg.nodetype.toUpperCase();
        if (!expmode) {
            expmode = '';
        }
        let view = null;
        this.controlInstance?.appViewRefs?.forEach((viewRef: any) => {
            if (viewRef.name.indexOf('EXPITEM:') > -1 && new RegExp(`${expmode}`, 'i').test(viewRef.name)) {
                view = {
                    viewpath: viewRef.getRefPSAppView?.dynaModelFilePath,
                    parentdata: viewRef.parentDataJO || {},
                    deKeyField: viewRef.getRefPSAppView?.appDataEntity?.codeName?.toLowerCase(),
                }
            }
        });
        return view;
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
        const refview: any = this.getExpItemView({ nodetype: nodetype });
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
        Object.assign(this.selection, { view: { viewname: 'app-view-shell' } });
        Object.assign(tempContext, { viewpath: refview.viewpath });
        Object.assign(this.selection, { 'viewparam': tempViewparam, 'context': tempContext });
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
        let tree = this.controlInstance.getXDataControl();
        if (this.viewState && tree) {
            this.viewState.next({ tag: tree.name, action: 'filter', data: { srfnodefilter: this.searchText } });
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
        let tree: any = this.controlInstance?.getXDataControl();
        if (tree) {
            this.viewState.next({ tag: tree.name, action: 'refresh_parent' });
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
