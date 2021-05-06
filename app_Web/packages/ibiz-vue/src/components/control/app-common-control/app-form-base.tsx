import { Prop, Watch, Emit } from 'vue-property-decorator';
import { LayoutTool, Util } from 'ibiz-core';
import { EditFormControlBase } from '../../../widgets';
import {
    IPSAppDEUIAction,
    IPSAppDEView,
    IPSDEFormButton,
    IPSDEFormDetail,
    IPSDEFormDRUIPart,
    IPSDEFormFormPart,
    IPSDEFormGroupPanel,
    IPSDEFormIFrame,
    IPSDEFormItem,
    IPSDEFormItemEx,
    IPSDEFormPage,
    IPSDEFormRawItem,
    IPSDEFormTabPage,
    IPSDEFormTabPanel,
    IPSFlexLayout,
    IPSFlexLayoutPos,
    IPSGridLayoutPos,
    IPSLayout,
} from '@ibiz/dynamic-model-api';

/**
 * 编辑表单部件基类
 *
 * @export
 * @class AppFormBase
 * @extends {EditFormControlBase}
 */
export class AppFormBase extends EditFormControlBase {
    /**
     * 部件动态参数
     *
     * @memberof AppFormBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppFormBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppFormBase
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
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppFormBase
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
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppFormBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 销毁视图回调
     *
     * @memberof AppFormBase
     */
    public destroyed() {
        this.ctrlDestroyed();
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
        let layout: IPSLayout = modelJson.getPSLayout();

        // 没有子表单成员
        if (!formDetails || formDetails.length == 0) {
            return null;
        }

        // 无布局
        if (!layout) {
            return formDetails.map((item: any, index: number) => {
                return this.renderByDetailType(item, index);
            });
        }

        // 栅格布局
        if (layout.layout == 'TABLE_24COL' || layout.layout == 'TABLE_12COL' || layout.layout == 'TABLE') {
            return (
                <row>
                    {formDetails.map((item: IPSDEFormDetail, index: number) => {
                        if ((item as any).hidden) {
                            return;
                        }
                        let attrs = LayoutTool.getGridOptions(item.getPSLayoutPos() as IPSGridLayoutPos);
                        return (
                            <i-col
                                class='form-layout-container'
                                {...{ props: attrs }}
                                style={this.detailsModel[item.name]?.visible ? '' : 'display: none;'}
                            >
                                {this.renderByDetailType(item, index)}
                            </i-col>
                        );
                    })}
                </row>
            );
        }

        // FLEX布局
        if (layout.layout == 'FLEX') {
            const flexStyle = LayoutTool.getFlexStyle(layout as IPSFlexLayout);
            return (
                <div style={flexStyle}>
                    {formDetails.map((item: IPSDEFormDetail, index: number) => {
                        if ((item as any).hidden) {
                            return;
                        }
                        let detailStyle = LayoutTool.getFlexStyle2(item?.getPSLayoutPos() as IPSFlexLayoutPos);
                        detailStyle += this.detailsModel[item.name].visible ? '' : 'display: none;';
                        return (
                            <div style={detailStyle} class='form-layout-container'>
                                {this.renderByDetailType(item, index)}
                            </div>
                        );
                    })}
                </div>
            );
        }

        throw new Error(`布局模式「${layout.layout}」暂未支持!`);
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
                    return this.renderTabPage(modelJson as IPSDEFormTabPage, index);
                case 'TABPANEL':
                    return this.renderTabPanel(modelJson as IPSDEFormTabPanel, index);
                case 'FORMITEM':
                    return this.renderFormItem(modelJson as IPSDEFormItem, index);
                case 'BUTTON':
                    return this.renderButton(modelJson as IPSDEFormButton, index);
                case 'DRUIPART':
                    return this.renderDruipart(modelJson as IPSDEFormDRUIPart, index);
                case 'RAWITEM':
                    return this.renderRawitem(modelJson as IPSDEFormRawItem, index);
                case 'IFRAME':
                    return this.renderIframe(modelJson as IPSDEFormIFrame, index);
                case 'FORMPART':
                    return this.renderFormPart(modelJson as IPSDEFormFormPart, index);
            }
        }
    }

    /**
     * 绘制表单部件
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormPart(modelJson: IPSDEFormFormPart, index: number): any {
        let { formPartType, name, codeName } = modelJson;
        // 后续补
        let systemCodeName = '',
            appCodeName = '',
            deCodeName = '',
            formCodeName = '';

        if (formPartType && formPartType == 'DYNASYS') {
            return (
                <app-form-part
                    name={name}
                    context={this.context}
                    viewparams={this.viewparams}
                    formState={this.formState}
                    systemCodeName={systemCodeName}
                    appCodeName={appCodeName}
                    deCodeName={deCodeName}
                    formCodeName={formCodeName}
                    formDetailCodeName={codeName}
                    on-change={this.onFormItemValueChange}
                ></app-form-part>
            );
        }
    }

    /**
     * 绘制iframe
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderIframe(modelJson: IPSDEFormIFrame, index: number): any {
        let { contentHeight, iFrameUrl } = modelJson;
        let iframeStyle = { height: contentHeight + 'px' };
        return (
            <div style={iframeStyle}>
                <iframe src={iFrameUrl}></iframe>
            </div>
        );
    }

    /**
     * 绘制直接内容
     *
     * @returns
     * @memberof AppDefaultFormBase
     */
    public renderRawitem(modelJson: IPSDEFormRawItem, index: number): any {
        const data: any = this.data;
        let { rawItemHeight, rawItemWidth, contentType, htmlContent, rawContent } = modelJson;
        let sysCss = modelJson.getPSSysCss();
        let sysImage = modelJson.getPSSysImage();
        let sizeStyle = rawItemHeight > 0 && rawItemWidth > 0 ? { width: rawItemWidth, height: rawItemHeight } : '';
        if (rawContent) {
            const items = rawContent?.match(/\{{(.+?)\}}/g);
            if (items) {
                items?.forEach((item: string) => {
                    rawContent = rawContent?.replace(/\{{(.+?)\}}/, eval(item?.substring(2, item?.length - 2)));
                });
            }
        }
        const tempNode = this.$createElement('div', {
            domProps: {
                innerHTML: rawContent,
            },
        });
        return (
            <app-rawitem
                viewparams={this.viewparams}
                context={this.context}
                contentStyle={sysCss?.cssName}
                sizeStyle={sizeStyle}
                contentType={contentType}
                imageClass={sysImage?.cssClass}
                htmlContent={htmlContent}
            >
                {Object.is(contentType, 'RAW') ? tempNode : null}
            </app-rawitem>
        );
    }

    /**
     * 关系界面保存事件
     *
     * @param {*} e
     * @memberof AppFormBase
     */
    public onDrDataSaved(e: any) {
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'drdatasaved',
            data: e,
        });
    }

    /**
     * 绘制关系界面
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderDruipart(modelJson: IPSDEFormDRUIPart, index: number): any {
        const { refreshItems, height, parentDataJO } = modelJson;
        const layoutPos = modelJson.getPSLayoutPos();
        const appView = modelJson.getPSAppView() as IPSAppDEView;
        const appDERSPaths = (appView as IPSAppDEView)?.getPSAppDERSPaths();
        const appDataEntity = this.controlInstance.getPSAppDataEntity();
        let tempContext: any = Util.deepCopy(this.context);
        // druipart样式
        let druipartHeight: any;
        if (layoutPos?.layout == 'FlEX') {
            druipartHeight = '100%';
        } else if (!height && appView?.height > 0) {
            druipartHeight = appView.height;
        } else {
            druipartHeight = Util.isNumber(height) && height > 0 ? `${height}px` : 'auto';
        }
        let druipartStyle = { height: druipartHeight, overflow: 'auto' };
        return (
            <app-form-druipart
                class={modelJson.getPSSysCss()?.cssName}
                formState={this.formState}
                isForbidLoad={ this.data?.srfuf === '0'}
                userTag={ modelJson.userTag ? modelJson.userTag : ''}
                paramItem={appDataEntity?.codeName?.toLowerCase()}
                parentdata={parentDataJO || undefined}
                parameters={Util.formatAppDERSPath(this.context, appDERSPaths)}
                context={tempContext}
                viewModelData={appView}
                viewparams={this.viewparams}
                parameterName={appDataEntity?.codeName?.toLowerCase()}
                parentName={appDataEntity?.codeName}
                appViewtype={appView?.viewType}
                refreshitems={refreshItems || undefined}
                ignorefieldvaluechange={this.ignorefieldvaluechange}
                viewname={appView?.codeName}
                localContext={Util.formatNavParam(modelJson.getPSNavigateContexts())}
                localParam={Util.formatNavParam(modelJson.getPSNavigateParams())}
                tempMode={appView.tempMode ? appView.tempMode : 0}
                data={JSON.stringify(this.data)}
                on-drdatasaved={($event: any) => this.drdatasaved($event)}
                style={druipartStyle}
            ></app-form-druipart>
        );
    }

    /**
     * 绘制按钮
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderButton(modelJson: IPSDEFormButton, index: number): any {
        const { width, height, showCaption, caption } = modelJson;
        const uiAction = modelJson.getPSUIAction() as IPSAppDEUIAction;
        const sysImage = modelJson.getPSSysImage();
        const sysCss = modelJson.getPSSysCss();
        let btnClass = width > 0 && height > 0 ? { width: width, height: height } : '';
        let badge = null;
        // TODO计数器徽章
        // if (uiAction) {
        //     let { appCounter, counterId } = uiAction;
        //     let { codeName } = appCounter;
        //     let count = codeName + 'counterservice.counterData.' + counterId;
        //     badge = <badge type='primary' count={count}></badge>;
        // }
        // 自定义类名
        const controlClassNames: any = { 'app-form-button': true };
        if (sysCss?.cssName) {
            Object.assign(controlClassNames, { [sysCss.cssName]: true });
        }
        let title: any = uiAction?.caption;
        const { codeName, controlType } = this.controlInstance;
        const appDataEntity = this.controlInstance.getPSAppDataEntity();
        if (appDataEntity) {
            let tag = `entities.${appDataEntity.codeName?.toLowerCase()}.${codeName?.toLowerCase()}_${controlType?.toLowerCase()}.uiactions.`;
            if (uiAction?.getPSAppDataEntity()) {
                tag += uiAction.getPSAppDataEntity()?.codeName?.toLowerCase() + '_';
            }
            tag += uiAction?.uIActionTag?.toLowerCase();
            title = this.$t(tag) || title;
        }
        return (
            <div>
                {badge}
                <i-button
                    title={title}
                    type='primary'
                    class={controlClassNames}
                    style={btnClass}
                    on-click={($event: any) => this.onFormItemActionClick({ tag: modelJson.name, event: $event })}
                    disabled={this.detailsModel[modelJson.name]?.disabled}
                >
                    {sysImage ? (
                        <i class={sysImage?.cssClass} style='margin-right: 2px;'></i>
                    ) : (
                        <menu-icon item={{ iconcls: uiAction?.getPSSysImage()?.cssClass }} />
                    )}
                    {showCaption && <span>{caption}</span>}
                </i-button>
            </div>
        );
    }

    /**
     * 绘制表单项
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormItem(modelJson: IPSDEFormItem, index: number): any {
        const { compositeItem, contentWidth, contentHeight } = modelJson;
        let editor = modelJson.getPSEditor();
        const refFormItem: any = [];
        const style = {
            width: contentWidth ? contentWidth : false,
            height: contentHeight ? contentHeight : false,
        };
        if (compositeItem) {
            let formItems = (modelJson as IPSDEFormItemEx).getPSDEFormItems();
            formItems?.forEach((formItem: any) => {
                refFormItem.push(formItem?.id);
            });
        }
        return (
            <app-default-form-item
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
            </app-default-form-item>
        );
    }

    /**
     * 绘制分页部件panel
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderTabPanel(modelJson: IPSDEFormTabPanel, index: number): any {
        return (
            <app-default-form-tab-panel
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
            >
                {modelJson.getPSDEFormTabPages?.()?.length &&
                    modelJson.getPSDEFormTabPages()?.map((item: IPSDEFormTabPage, index2: number) => {
                        return this.renderTabPage(item, index2);
                    })}
            </app-default-form-tab-panel>
        );
    }

    /**
     * 绘制分页部件
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderTabPage(modelJson: IPSDEFormTabPage, index: number): any {
        return (
            <app-default-form-tab-page
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
            >
                {this.renderDetails(modelJson)}
            </app-default-form-tab-page>
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
            <app-default-group-panel
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
                on-groupUIActionClick={this.handleActionClick.bind(this)}
                on-managecontainerclick={this.manageContainerClick.bind(this)}
                context={this.context}
                viewparams={this.viewparams}
                data={this.data}
            >
                {this.renderDetails(modelJson)}
            </app-default-group-panel>
        );
    }

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
            <app-default-form-page
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
            >
                {this.renderDetails(modelJson)}
            </app-default-form-page>
        );
    }

    /**
     * 绘制表单内容
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormContent() {
        const { noTabHeader, codeName, name, controlType } = this.controlInstance;
        const formPages = this.controlInstance.getPSDEFormPages();
        if (formPages && formPages.length > 0) {
            if (noTabHeader) {
                return formPages.map((item: any, index: number) => {
                    return this.renderFormPage(item, index);
                });
            } else {
                const tabsName = `${this.controlInstance
                    .getPSAppDataEntity()
                    ?.codeName?.toLowerCase()}_${controlType?.toLowerCase()}_${codeName?.toLowerCase()}`;
                return (
                    <tabs
                        animated={false}
                        name={tabsName}
                        value={this.detailsModel[name]?.activatedPage}
                        on-on-click={(e: any) => {
                            this.detailsModel[name]?.clickPage(e);
                        }}
                    >
                        {formPages.map((item: any, index: number) => {
                            return this.renderFormPage(item, index);
                        })}
                    </tabs>
                );
            }
        }
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppFormBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        let formId =
            this.controlInstance.getPSAppDataEntity()?.codeName?.toLowerCase() +
            this.controlInstance.codeName?.toLowerCase();
        return (
            <i-form
                props={{ model: this.data }}
                class={{ ...controlClassNames, 'app-form': true }}
                ref={this.controlInstance.name}
                id={formId}
                on-on-validate={this.formItemValidate.bind(this)}
            >
                <input style='display:none;' />
                <row>{this.renderFormContent()}</row>
            </i-form>
        );
    }
}
