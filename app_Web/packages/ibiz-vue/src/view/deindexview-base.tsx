import { IPSAppDEIndexView, IPSDEDRBar, IPSDEForm } from '@ibiz/dynamic-model-api';
import { DeIndexViewEngine, ModelTool } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

/**
 * @description 实体首页视图基类
 * @export
 * @class DeIndexViewBase
 * @extends {MainViewBase}
 */
export class DeIndexViewBase extends MainViewBase {

    /**
     * @description 应用实体首页视图模型实例对象
     * @type {IPSAppDEIndexView}
     * @memberof DeIndexViewBase
     */
    public viewInstance!: IPSAppDEIndexView;

    /**
     * @description 实体首页视图引擎实例对象
     * @type {DeIndexViewEngine}
     * @memberof DeIndexViewBase
     */
    public engine: DeIndexViewEngine = new DeIndexViewEngine();

    /**
     * @description 表单部件实例对象
     * @type {IPSDEForm}
     * @memberof DeIndexViewBase
     */
    public formInstance!: IPSDEForm;

    /**
     * @description 数据关系栏部件实例对象
     * @type {IPSDEDRBar}
     * @memberof DeIndexViewBase
     */
    public drBarInstance!: IPSDEDRBar;

    /**
     * @description 视图模型初始化
     * @memberof DeIndexViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        const controls = this.viewInstance.getPSControls() || [];
        this.formInstance = ModelTool.findPSControlByType('FORM', controls);
        this.drBarInstance = ModelTool.findPSControlByType('DRBAR', controls);
    }

    /**
     * @description 引擎初始化
     * @return {*}  {void}
     * @memberof DeIndexViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.formInstance.name] as any).ctrl,
            drbar:(this.$refs[this.drBarInstance.name] as any).ctrl,
            p2k: '0',
            isLoadDefault: true,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
    }
  
}