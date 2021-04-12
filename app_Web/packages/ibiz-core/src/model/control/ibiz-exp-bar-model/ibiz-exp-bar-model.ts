import { DynamicService } from '../../../service';
import { IBizMainControlModel } from '../ibiz-main-control-model';
import { IBizTreeModel } from '../ibiz-tree-model/ibiz-tree-model';

/**
 * 导航栏部件模型基类
 *
 * @export
 * @class IBizExpBarModel
 */
export class IBizExpBarModel extends IBizMainControlModel{

    /**
     * 数据部件
     *
     * @protected
     * @type {*}
     * @memberof IBizExpBarModel
     */
    protected $xDataControl!: any;

    /**
     * Creates an instance of IBizExpBarModel.
     * IBizExpBarModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof IBizExpBarModel
     */
    constructor(opts: any = {}, viewRef?: any, parentRef?: any,runtimeData?:any) {
        super(opts, viewRef, parentRef,runtimeData);
        Object.defineProperty(this, '$xDataControl',{enumerable: false, writable: true })
    }

    /**
     * 加载模型数据(应用实体)
     *
     * @memberof IBizExpBarModel
     */
    public async loaded() {
        await super.loaded();
        // 加载导航栏数据部件
        if (this.xDataControl?.modelref === true && this.xDataControl.path) {
            // 加载子部件时已经加载过了
            let xDataControlData = this.getControlDataByName(this.xDataControl.name)
            if(xDataControlData){
                Object.assign(this.xDataControl, xDataControlData);
                delete this.xDataControl.modelref;
            }
        }
        // 从$controlsMap里拿到数据部件实例
        this.$xDataControl = this.getControl(this.xDataControl.name);
        // 加载关联视图
        if(this.controlModelData?.getPSAppViewRefs?.length > 0){
            for(const view of this.controlModelData.getPSAppViewRefs){
                const res = await DynamicService.getInstance(this.context).getAppViewModelJsonData(view.getRefPSAppView.path)
                Object.assign(view.getRefPSAppView, res);
                delete view.getRefPSAppView.modelref;
            }
        }
    }

    /**
     * 获取数据部件实例
     *
     * @returns
     * @memberof IBizExpBarModel
     */
    public getXDataControl(){
        return this.$xDataControl;
    }

    /**
     * 数据部件的模型JSON数据
     *
     * @readonly
     * @memberof IBizExpBarModel
     */
    get xDataControl(){
        return this.controlModelData?.getPSControls?.find((item:any)=>{return this.controlModelData.xDataControlName === item.name});
    }

    /**
     * 布局
     */
    get sideBarLayout() {
        return this.$view.sideBarLayout
    }

    /**
     * 是否显示标题栏
     *
     * @readonly
     * @memberof IBizExpBarModel
     */
    get showTitleBar(){
        return this.controlModelData.showTitleBar;
    }

    /**
     * 是否支持搜索
     *
     * @readonly
     * @memberof IBizExpBarModel
     */
    get enableSearch(){
        return this.controlModelData.enableSearch;
    }

    /**
     * 标题
     *
     * @readonly
     * @memberof IBizExpBarModel
     */
    get title(){
        return this.controlModelData.title;
    }

    /**
     * 部件宽度
     *
     * @readonly
     * @memberof IBizExpBarModel
     */
    get width(){
        return this.controlModelData.width;
    }

    /**
     * 获取视图关系集合
     *
     * @readonly
     * @memberof IBizExpBarModel
     */
    get appViewRefs(){
        return this.controlModelData.getPSAppViewRefs;
    }

    /**
     * 启用计数器
     * 
     * @memberof IBizExpBarModel
     */
    get enableCounter() {
        return this.controlModelData.enableCounter;
    }

    /**
     * 启用快速搜索
     * 
     * @memberof IBizExpBarModel
     */
    get enableQuickGroup() {
        return this.controlModelData.enableQuickGroup;
    }

    /**
     * 工具栏
     * 
     * @readonly
     * @memberof IBizExpBarModel
     */
    get toolbar() {
        if(this.controlModelData.getPSControls?.length>0) {
            let targetToolbar: any = this.controlModelData.getPSControls.find((control: any) => {
                return control.name == `${this.controlModelData.name?.toLowerCase()}_toolbar`;
            });
            if(targetToolbar) {
                return targetToolbar;
            }
        }
    }

}