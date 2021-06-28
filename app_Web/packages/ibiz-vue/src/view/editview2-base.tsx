import { EditView2Engine, ModelTool, EditView2Interface } from 'ibiz-core';
import { IPSDRBar } from '@ibiz/dynamic-model-api';
import { EditViewBase } from './editview-base';


/**
 * 编辑视图基类
 *
 * @export
 * @class EditView2Base
 * @extends {EditView2Base}
 * @implements {EditView2Interface}
 */
export class EditView2Base extends EditViewBase implements EditView2Interface {

    /**
     * 视图引擎
     *
     * @public
     * @type {EditView2Engine}
     * @memberof EditView2Base
     */
    public engine: EditView2Engine = new EditView2Engine();

    /**
     * 数据关系分页部件实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditView2Base
     */
    public drbarInstance! :IPSDRBar;

    /**
     * 选中数据
     *
     * @public
     * @type {any}
     * @memberof EditView2Base
     */
    public selection: any = {};

    /**
     * 初始化编辑视图实例
     * 
     * @memberof EditView2Base
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.drbarInstance = ModelTool.findPSControlByType('DRBAR',this.viewInstance.getPSControls()) as IPSDRBar;
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof EditView2Base
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.editFormInstance.name] as any).ctrl,
            drbar: (this.$refs[this.drbarInstance.name] as any).ctrl,
            p2k: '0',
            isLoadDefault: this.viewInstance.loadDefault,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
        if(this.dataPanelInstance){
            this.datapanel.init({
                view: this,
                parentContainer: this.$parent,
                datapanel: (this.$refs[this.dataPanelInstance?.name] as any).ctrl,
                p2k: '0',
                isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: this.appDeCodeName.toLowerCase(),
                majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            });
        }
    }

}
