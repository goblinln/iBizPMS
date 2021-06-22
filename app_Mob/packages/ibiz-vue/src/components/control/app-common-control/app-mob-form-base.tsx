import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobFormControlBase } from '../../../widgets';
import { IPSAppDEView, IPSDEEditForm, IPSDEFormDetail, IPSDEFormDRUIPart, IPSDEFormGroupPanel, IPSDEFormItem, IPSDEFormPage } from '@ibiz/dynamic-model-api';
/**
 * 编辑表单部件基类
 *
 * @export
 * @class AppMobFormBase
 * @extends {MobFormControlBase}
 */
export class AppMobFormBase extends MobFormControlBase {

    /**
     * 表单的模型对象
     *
     * @type {*}
     * @memberof FormControlBase
     */
    public controlInstance!: IPSDEEditForm;

    /**
     * 部件动态参数
     *
     * @memberof AppMobFormBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobFormBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobFormBase
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
     * @memberof AppMobFormBase
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
     * @memberof AppMobFormBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobFormBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制表单分页
     *
     * @returns
     * @memberof AppMobFormBase
     */
    public renderFormPage(modelJson: IPSDEFormPage, index: number): any {
        const { noTabHeader } = this.controlInstance;
        if (noTabHeader) {
            return this.renderDetails(modelJson);
        }
        let customStyle = this.detailsModel[modelJson.name].visible ? '' : 'display: none;'
        return (
            <app-default-mob-form-page
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
                style={customStyle}
                modelService={this.modelService}
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
     * @memberof AppMobFormBase
     */
    public renderDetails(modelJson: any) {
        let formDetails: IPSDEFormDetail[] = modelJson.getPSDEFormDetails();
        // 没有子表单成员
        if (!formDetails || formDetails.length == 0) {
            return null;
        }
        return formDetails.map((item: any, index: number) => {
            return this.detailsModel[item.name]?.visible ? this.renderByDetailType(item, index) : null;
        });
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @param {number} index
     * @memberof AppMobFormBase
     */
    public renderByDetailType(modelJson: any, index: number) {
        if (modelJson.getPSSysPFPlugin()) {
            const pluginInstance: any = this.PluginFactory.getPluginInstance(
                'CONTROLITEM',
                modelJson.getPSSysPFPlugin()?.pluginCode || '',
            );
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, modelJson, this, null);
            }
        } else {
            switch (modelJson.detailType) {
                case 'FORMPAGE':
                    return this.renderFormPage(modelJson as IPSDEFormPage, index);
                case 'GROUPPANEL':
                    return this.renderGroupPanel(modelJson as IPSDEFormGroupPanel, index);
                case 'TABPAGE':
                // return this.renderTabPage(modelJson as IPSDEFormTabPage, index);
                case 'TABPANEL':
                // return this.renderTabPanel(modelJson as IPSDEFormTabPanel, index);
                case 'FORMITEM':
                    return this.renderFormItem(modelJson as IPSDEFormItem, index);
                case 'BUTTON':
                // return this.renderButton(modelJson as IPSDEFormButton, index);
                case 'DRUIPART':
                    return this.renderDruipart(modelJson as IPSDEFormDRUIPart, index);
                case 'RAWITEM':
                // return this.renderRawitem(modelJson as IPSDEFormRawItem, index);
                case 'IFRAME':
                // return this.renderIframe(modelJson as IPSDEFormIFrame, index);
                case 'FORMPART':
                // return this.renderFormPart(modelJson as IPSDEFormFormPart, index);
            }
        }
    }

    /**
     * 绘制表单项
     *
     * @returns
     * @memberof AppMobFormBase
     */
    public renderFormItem(modelJson: IPSDEFormItem, index: number): any {
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
                modelService={this.modelService}
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
     * @memberof AppMobFormBase
     */
    public renderGroupPanel(modelJson: IPSDEFormGroupPanel, index: number): any {
        let customStyle = this.detailsModel[modelJson.name].visible ? '' : 'display: none;'
        return (
            <app-default-mob-group-panel
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
                // on-groupUIActionClick={this.handleActionClick.bind(this)}
                // on-managecontainerclick={this.manageContainerClick.bind(this)}
                context={this.context}
                viewparams={this.viewparams}
                data={this.data}
                modelService={this.modelService}
            >
                {this.renderDetails(modelJson)}
            </app-default-mob-group-panel>
        );
    }


    /**
     * 绘制表单内容
     *
     * @returns
     * @memberof AppMobFormBase
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
                        color="var(--app-background-color)"
                        animated={true}
                        swipeable={true}
                        scrollspy={true}
                        value={this.detailsModel[name]?.activatedPage}
                    >
                        {formPages.map((item: any, index: number) => {
                            const caption = this.$tl(item.getCapPSLanguageRes()?.lanResTag,item.caption);
                            return <van-tab title={caption} class="app-form-tab" name={item.name}>
                                {this.renderFormPage(item, index)}
                            </van-tab>
                        })}
                    </van-tabs>
                );
            }
        }
    }

    /**
     * 绘制关系界面
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderDruipart(modelJson: any, index: number): any {
        const { refreshItems, parentDataJO } = modelJson;
        const appView = modelJson.getPSAppView() as IPSAppDEView;
        const appDERSPaths = (appView as IPSAppDEView)?.getPSAppDERSPaths();
        const appDataEntity = this.controlInstance.getPSAppDataEntity();
        let tempContext: any = Util.deepCopy(this.context);
        // druipart样式
        let druipartHeight: any;
        let druipartStyle: any = { height: druipartHeight, overflow: 'auto' };
        let viewName = Util.srfFilePath2(appView?.codeName);
        let partCaption: any = '';
        return (
            <app-form-druipart
                class={modelJson.getPSSysCss()?.cssName}
                formState={this.formState}
                isForbidLoad={this.data?.srfuf === '0'}
                paramItem={appDataEntity?.codeName?.toLowerCase()}
                parentdata={parentDataJO || undefined}
                parameters={Util.formatAppDERSPath(this.context, appDERSPaths)}
                context={tempContext}
                viewparams={this.viewparams}
                parameterName={appDataEntity?.codeName?.toLowerCase()}
                parentName={appDataEntity?.codeName}
                appViewtype={appView?.viewType}
                refreshitems={refreshItems}
                viewModelData={appView}
                ignorefieldvaluechange={this.ignorefieldvaluechange}
                viewname={viewName}
                navigateContext={Util.formatNavParam(modelJson.getPSNavigateContexts())}
                navigateParam={Util.formatNavParam(modelJson.getPSNavigateParams())}
                tempMode={appView?.tempMode ? appView.tempMode : 0}
                data={JSON.stringify(this.data)}
                on-drdatasaved={($event: any) => this.drdatasaved($event)}
                style={druipartStyle}
                caption={partCaption ? partCaption : ''}
            ></app-form-druipart>
        );
    }

    /**
     * 绘制表单
     *
     * @returns {*}
     * @memberof AppMobFormBase
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
