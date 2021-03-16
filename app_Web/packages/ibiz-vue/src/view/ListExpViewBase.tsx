import { ListExpViewEngine, IBizListExpBarModel, IBizListExpViewModel } from 'ibiz-core';
import { ExpViewBase } from './ExpViewBase';


/**
 * 列表导航视图基类
 *
 * @export
 * @class ListExpViewBase
 * @extends {ExpViewBase}
 */
export class ListExpViewBase extends ExpViewBase {

    /**
     * 视图实例
     * 
     * @memberof ListExpviewBase
     */
    public viewInstance!: IBizListExpViewModel;

    /**
     * 导航栏实例
     * 
     * @memberof ListExpviewBase
     */
    public expBarInstance!: IBizListExpBarModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof ListExpviewBase
     */
    public engine: ListExpViewEngine = new ListExpViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ListExpviewBase
     */
    public engineInit(): void {
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            listexpbar: (this.$refs[this.expBarInstance.name] as any).ctrl,
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof ListExpviewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizListExpViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.expBarInstance = this.viewInstance.getControl('listexpbar');
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof ListExpviewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            sideBarLayout: this.viewInstance.sideBarLayout
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }
}