import { IPSCodeListEditor, IPSEditor } from '@ibiz/dynamic-model-api';
import { ModelTool, Util } from 'ibiz-core';
import { Vue, Component, Prop, Inject, Model, Emit } from 'vue-property-decorator';
import { AppComponentService } from '../../../app-service';
import { Watch } from '../../../decorators/vue-lifecycleprocessing';

/**
 * editor解析器基类
 *
 * @export
 * @class EditorBase
 * @extends {Vue}
 */
export class EditorBase extends Vue {

    /**
     * 编辑器值
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() value!: any;

    /**
     * 编辑器模型
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() editorInstance!: IPSEditor;

    /**
     * 外层部件容器模型
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() containerCtrl!: any;

    /**
     * 父级项模型（表单项，表格项）
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() parentItem!: any;
    
    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() public viewparams!: any;

    /**
     * 上下文data数据(form里的data，表格里的row)
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() public contextData?: any;

    /**
     * 是否禁用
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop({default: false}) public disabled!: boolean;

    /**
     * 编辑器状态(表单里的formState)
     *
     * @type {*}
     * @memberof EditorBase
     */    
    @Prop() public contextState?: any;
    
    /**
     * 表单服务
     *
     * @type {*}
     * @memberof EditorBase
     */    
    @Prop() public service?: any;
    
    /**
     * 编辑器组件名称
     *
     * @type {*}
     * @memberof EditorBase
     */
    public editorComponentName!: string;

    /**
     * 自定义样式的对象
     *
     * @type {*}
     * @memberof EditorBase
     */
    public customStyle: any = {};

    /**
     * 设置自定义props
     *
     * @type {*}
     * @memberof EditorBase
     */
    public customProps: any = {};

    /**
     * 编辑器是否初始化完成
     * 
     * @type {boolean}
     * @memberof EditorBase
     */
    public editorIsLoaded: boolean = false;
    
    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof EditorBase
     */
    @Emit('change')
    public editorChange(value: any): void {}

    /**
     * 生命周期-created
     *
     * @memberof EditorBase
     */
    created() {}

    /**
     * editorJsonStr值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof EditorBase
     */
    @Watch('editorInstance', { immediate: true})
    public onEditorInstanceChange(newVal: any, oldVal: any) {
        if (newVal && newVal != oldVal) {
            this.initEditorBase();
        }
    }

    /**
     * 编辑器初始化(基类)
     *
     * @memberof EditorBase
     */
    public async initEditorBase() {
        this.editorChange = this.editorChange.bind(this);
        this.customProps = {
            placeholder: this.editorInstance.placeHolder,
        };
        this.editorComponentName = AppComponentService.getEditorComponents(this.editorInstance.editorType,this.editorInstance.editorStyle);
        this.setCustomStyle();
        await this.initEditor();
        this.setEditorParams();
        this.editorIsLoaded = true;
    }

    /**
     * 编辑器初始化
     *
     * @memberof EditorBase
     */
    public async initEditor() {
        try {
            // 加载编辑器实体
            await (this.editorInstance as any)?.getPSAppDataEntity?.()?.fill();
            // 加载编辑器代码表
            await (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.()?.fill();
        } catch (error) {
            console.error(error)
        }
    }

    /**
     * 设置编辑器的自定义高宽
     *
     * @memberof EditorBase
     */
    public setCustomStyle() {
        let { editorWidth, editorHeight } = this.editorInstance;
        this.customStyle = {};
        if (!Util.isEmpty(editorWidth) && editorWidth != 0) {
            this.customStyle.width = editorWidth + 'px';
        }
        if (!Util.isEmpty(editorHeight) && editorHeight != 0) {
            this.customStyle.height = editorHeight + 'px';
        }
    }

    /**
     * 设置编辑器导航参数
     * 
     * @param keys 编辑器参数key
     * @memberof EditorBase
     */
    public setEditorParams() {
        let _this = this;
        if(!this.editorInstance.editorParams){
            return 
        }
        Object.assign(this.customProps, {
            localContext: ModelTool.getNavigateContext(this.editorInstance),
            localParam: ModelTool.getNavigateParams(this.editorInstance)
        });
        for (const key in this.editorInstance.editorParams) {
          let param: any;
          if (key == 'uploadparams' || key == 'exportparams') {
            param = eval('(' + this.editorInstance.editorParams[key] + ')');
          }else {
            param = this.editorInstance.editorParams[key];
          }
            if(key.indexOf('.') != -1) {
                let splitArr: Array<any> = key.split('.');
                switch (splitArr[0]) {
                    case "SRFNAVPARAM":
                        Object.assign(this.customProps.localParam, { [splitArr[1]]: param });
                        break;
                    case "SRFNAVCTX":
                        Object.assign(this.customProps.localContext, { [splitArr[1]]: param });
                        break;
                }
            } else {
                if(param) {
                    this.customProps[key] = param;
                }
            }
        }
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof EditorBase
     */
    public render(): any {
        return <div>{this.editorInstance ? this.$t('app.editor.nooutput') : this.$t('app.editor.noexist')}</div>;
    }
}
