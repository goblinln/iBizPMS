/**
 * 工具栏项类
 * 
 * @memberof IBizToolBarItemModel
 */
export class IBizToolBarItemModel{

    /**
     * 工具栏项模型数据
     *
     * @memberof IBizToolBarItemModel
     */
    public toolbarItemModelData: any;

    /**
     * 初始化 IBizToolBarItemModel 对象
     * @param opts 额外参数
     *
     * @memberof IBizToolBarItemModel
     */
    constructor(opts:any){
        this.toolbarItemModelData = opts;
    }

    /**
     * 标识
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get name(){
        return this.toolbarItemModelData.name;
    }

    /**
     * 行为级别
     *
     * @return {*}
     * @memberof IBizToolBarItemModel
     */
    actionLevel(){
        return this.toolbarItemModelData.actionLevel;
    }

    /**
     * 启用点击切换模式
     *
     * @return {boolean}
     * @memberof IBizToolBarItemModel
     */
    get enableToggleMode(){
        return this.toolbarItemModelData.enableToggleMode;
    }

    /**
     * 图片资源
     *
     * @return {*}
     * @memberof IBizToolBarItemModel
     */
    get getPSSysImage(){
        return this.toolbarItemModelData.getPSSysImage;
    }

    /**
     * 界面行为组展开模式
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get groupExtractMode(){
        return this.toolbarItemModelData.groupExtractMode;
    }

    /**
     * 是否隐藏
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get hiddenItem(){
        return this.toolbarItemModelData.hiddenItem;
    }

    /**
     * 无权限显示模式
     *
     * @return {number}
     * @memberof IBizToolBarItemModel
     */
    get  noPrivDisplayMode(){
        return this.toolbarItemModelData.noPrivDisplayMode;
    }

    /**
     * 界面行为操作目标
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get uIActionTarget(){
        return this.toolbarItemModelData.uIActionTarget;
    }

    /**
     * 启用
     *
     * @return {boolean}
     * @memberof IBizToolBarItemModel
     */
    get valid(){
        return this.toolbarItemModelData.valid;
    }

    /**
     * 数据对象部件名称
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get xDataControlName(){
        return this.toolbarItemModelData.xDataControlName;
    }

    /**
     * 标题
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get caption(){
        return this.toolbarItemModelData.caption;
    }

    /**
     * 类型
     *
     * @return {('DEUIACTION' | 'SEPERATOR')} 界面行为 | 分割线
     * @memberof IBizToolBarItemModel
     */
    get itemType(){
        return this.toolbarItemModelData.itemType;
    }

    /**
     * 是否显示标题
     *
     * @return {boolean}
     * @memberof IBizToolBarItemModel
     */
    get showCaption(){
        return this.toolbarItemModelData.showCaption;
    }

    /**
     * 是否显示图标
     *
     * @return {boolean}
     * @memberof IBizToolBarItemModel
     */
    get showIcon(){
        return this.toolbarItemModelData.showIcon;
    }

    /**
     * 提示
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get tooltip(){
        return this.toolbarItemModelData.tooltip;
    }

    /**
     * 样式图标
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get iconcls(){
        return this.getPSSysImage?.cssClass;
    }

    /**
     * 路径图标
     *
     * @return {string}
     * @memberof IBizToolBarItemModel
     */
    get icon(){
        // todo
        return null;
    }

    /**
     * 映射的界面行为
     *
     * @return {any}
     * @memberof IBizToolBarItemModel
     */
    get getPSUIAction(){
        return this.toolbarItemModelData.getPSUIAction;
    }

    /**
     * 子项
     *
     * @return {any}
     * @memberof IBizToolBarItemModel
     */
    get items() {
        return this.toolbarItemModelData.items;
    }
    
}