import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobSearchFormControlBase } from '../../../widgets';
import { IPSDEFormDetail, IPSDEFormGroupPanel, IPSDEFormPage, IPSDESearchFormItem } from '@ibiz/dynamic-model-api';
/**
 * 搜索表单基类
 *
 * @export
 * @class AppMobSearchFormBase
 * @extends {MobSearchFormControlBase}
 */
export class AppMobSearchFormBase extends MobSearchFormControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppMobSearchFormBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobSearchFormBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobSearchFormBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobSearchFormBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppMobSearchFormBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobSearchFormBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制表单分页
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormPage(modelJson: IPSDEFormPage, index: number): any {
        const { noTabHeader } = this.controlInstance;
        if (noTabHeader) {
            return this.renderDetails(modelJson);
        }
        return (
            <app-default-mob-form-page
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
            >
                {this.renderDetails(modelJson)}
            </app-default-mob-form-page>
        );
    }

    /**
     * 绘制子表单成员,布局控制
     *
     * @param {*} modelJson
     * @returns
     * @memberof AppFormBase
     */
    public renderDetails(modelJson: any) {
        let formDetails: IPSDEFormDetail[] = modelJson.getPSDEFormDetails();
        // 没有子表单成员
        if (!formDetails || formDetails.length == 0) {
            return null;
        }
        return formDetails.map((item: any, index: number) => {
            return this.renderByDetailType(item, index);
        });
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @param {number} index
     * @memberof AppFormBase
     */
    public renderByDetailType(modelJson: IPSDEFormDetail, index: number) {
        if (modelJson.getPSSysPFPlugin()) {
            const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", modelJson.getPSSysPFPlugin()?.pluginCode || '');
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, modelJson, this, null);
            }
        } else {
            switch (modelJson.detailType) {
                case 'FORMPAGE':
                    return this.renderFormPage(modelJson as IPSDEFormPage, index);
                case 'GROUPPANEL':
                    return this.renderGroupPanel(modelJson as IPSDEFormGroupPanel, index);
                // case 'TABPAGE':
                //     return this.renderTabPage(modelJson, index);
                // case 'TABPANEL':
                //     return this.renderTabPanel(modelJson, index);
                case 'FORMITEM':
                    return this.renderFormItem(modelJson as IPSDESearchFormItem, index);
                // case 'BUTTON':
                //     return this.renderButton(modelJson, index);
                // case 'DRUIPART':
                //     return this.renderDruipart(modelJson, index);
                // case 'RAWITEM':
                //     return this.renderRawitem(modelJson, index);
                // case 'IFRAME':
                //     return this.renderIframe(modelJson, index);
                // case 'FORMPART':
                //     return this.renderFormPart(modelJson, index);
            }
        }
    }

    /**
     * 绘制表单项
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormItem(modelJson: IPSDESearchFormItem, index: number): any {
        let editor = modelJson.getPSEditor();
        return (
            <app-default-mob-form-item
                detailsInstance={modelJson}
                index={index}
                data={this.data}
                rules={this.rules[modelJson.name]}
                runtimeModel={this.detailsModel[modelJson.name]}
                context={Util.deepCopy(this.context)}
                viewparams={Util.deepCopy(this.viewparams)}
                contextState={this.formState}
                service={this.service}
                ignorefieldvaluechange={this.ignorefieldvaluechange}
                on-formItemValueChange={(value: any) => {
                    this.onFormItemValueChange(value);
                }}
                controlInstance={this.controlInstance}
            >
                {editor && (
                    <app-default-editor
                        editorInstance={editor}
                        containerCtrl={this.controlInstance}
                        parentItem={modelJson}
                        value={this.data[editor?.name]}
                        contextData={this.data}
                        context={Util.deepCopy(this.context)}
                        viewparams={Util.deepCopy(this.viewparams)}
                        contextState={this.formState}
                        service={this.service}
                        disabled={this.detailsModel[modelJson.name]?.disabled}
                        ignorefieldvaluechange={this.ignorefieldvaluechange}
                        on-change={(value: any) => {
                            this.onFormItemValueChange(value);
                        }}
                    />
                )}
            </app-default-mob-form-item>
        );
    }

    /**
     * 绘制分组面板
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderGroupPanel(modelJson: IPSDEFormGroupPanel, index: number): any {
        return (
            <app-default-mob-group-panel
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
            // on-groupUIActionClick={this.handleActionClick.bind(this)}
            >
                {this.renderDetails(modelJson)}
            </app-default-mob-group-panel>
        );
    }


    /**
     * 绘制表单内容
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormContent() {
        const { noTabHeader, name } = this.controlInstance;
        const formPages = this.controlInstance.getPSDEFormPages();
        if (formPages && formPages.length > 0) {
            if (noTabHeader) {
                return formPages.map((item: any, index: number) => {
                    return this.renderFormPage(item, index);
                });
            } else {
                return (
                    <van-tabs
                        type="card"
                        class="app-form-tabs"
                        color="#333"
                        animated={true}
                        swipeable={true}
                        scrollspy={true}
                        value={this.detailsModel[name]?.activatedPage}
                        on-change={(e: any) => {
                            this.detailsModel[name]?.clickPage(e);
                        }}
                    >
                        {formPages.map((item: any, index: number) => {
                            return <van-tab title={item.caption} class="app-form-tab" name={item.name}>
                                {this.renderFormPage(item, index)}
                            </van-tab>
                        })}
                    </van-tabs>
                );
            }
        }
    }

    /**
     * 绘制表单
     *
     * @returns {*}
     * @memberof AppMobSearchFormBase
     */
    public render() {
        const { controlClassNames } = this.renderOptions;
        if (!this.controlIsLoaded) {
            return null;
        }
        return (
            <div class={controlClassNames}>
                {this.renderFormContent()}
            </div>
        );
    }
}
