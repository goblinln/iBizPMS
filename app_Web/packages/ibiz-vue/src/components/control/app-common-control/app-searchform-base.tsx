import { Prop, Watch, Emit } from 'vue-property-decorator';
import {
    Util,
    IBizFormItemModel,
    IBizFormButtonModel,
    IBizFormPageModel,
    IBizFormGroupPanelModel,
    IBizFormDetailContainerModel,
    IBizFormDetailModel,
} from 'ibiz-core';
import { SearchFormControlBase } from '../../../widgets';

/**
 * 搜索表单部件基类
 *
 * @export
 * @class AppSearchFormBase
 * @extends {SearchFormControlBase}
 */
export class AppSearchFormBase extends SearchFormControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppDefaultForm
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppDefaultForm
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultForm
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
     * @memberof AppDefaultForm
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
     * @memberof AppSearchFormBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制子表单成员,布局控制
     *
     * @param {*} modelJson
     * @returns
     * @memberof AppFormBase
     */
    public renderDetails(modelJson: IBizFormDetailContainerModel) {
        let { getChildFormDetails: formDetails, getLayout: layout } = modelJson;
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
        if (layout.layout == 'TABLE_24COL' || layout.layout == 'TABLE_12COL') {
            return (
                <row>
                    {formDetails.map((item: IBizFormDetailModel, index: number) => {
                        if (item.hidden) {
                            return
                        }
                        let attrs = item.getLayoutPos.getGridOptionsIn24();
                        return <i-col class='form-layout-container' {...{ props: attrs }} style={this.detailsModel[item.name]?.visible ? '' : 'display: none;'}>{this.renderByDetailType(item, index)}</i-col>;
                    })}
                </row>
            );
        }

        // FLEX布局
        if (layout.layout == 'FLEX') {
            const flexStyle = layout.getCssStyle();
            return (
                <div style={flexStyle}>
                    {formDetails.map((item: IBizFormDetailModel, index: number) => {
                        if (item.hidden) {
                            return
                        }
                        let detailStyle = item.getLayoutPos.getCssStyle();
                        detailStyle += this.detailsModel[item.name].visible ? '' : 'display: none;'
                        return <div style={detailStyle} class='form-layout-container'>{this.renderByDetailType(item, index)}</div>;
                    })}
                </div>
            );
        }
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @param {number} index
     * @memberof AppFormBase
     */
    public renderByDetailType(modelJson: any, index: number) {
        if (modelJson.getPSSysPFPlugin) {
            const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", modelJson.getPSSysPFPlugin.pluginCode);
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, modelJson, this, null);
            }
        } else {
            switch (modelJson.detailType) {
                case 'FORMPAGE':
                    return this.renderFormPage(modelJson, index);
                case 'GROUPPANEL':
                    return this.renderGroupPanel(modelJson, index);
                case 'TABPAGE':
                    return this.renderTabPage(modelJson, index);
                case 'TABPANEL':
                    return this.renderTabPanel(modelJson, index);
                case 'FORMITEM':
                    return this.renderFormItem(modelJson, index);
                case 'BUTTON':
                    return this.renderButton(modelJson, index);
                case 'DRUIPART':
                    return this.renderDruipart(modelJson, index);
                case 'RAWITEM':
                    return this.renderRawitem(modelJson, index);
                case 'IFRAME':
                    return this.renderIframe(modelJson, index);
                case 'FORMPART':
                    return this.renderFormPart(modelJson, index);
            }
        }
    }

    /**
     * 绘制表单部件
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormPart(modelJson: any, index: number): any {
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
    public renderIframe(modelJson: any, index: number): any {
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
    public renderRawitem(modelJson: any, index: number): any {
        const data: any = this.data;
        let { getPSSysCss, rawItemHeight, rawItemWidth, contentType, getPSSysImage, htmlContent, rawContent } = modelJson;
        let sizeStyle = rawItemHeight > 0 && rawItemWidth > 0 ? { width: rawItemWidth, height: rawItemHeight } : '';
        if (rawContent) {
            const items = rawContent.match(/\{{(.+?)\}}/g);
            if (items) {
                items.forEach((item: string) => {
                    rawContent = rawContent.replace(/\{{(.+?)\}}/, eval(item.substring(2, item.length - 2)));
                })
            }
        }
        const tempNode = this.$createElement('div', {
            domProps: {
                innerHTML: rawContent,
            }
        })
        return (
            <app-rawitem
                viewparams={this.viewparams}
                context={this.context}
                contentStyle={getPSSysCss?.cssName}
                sizeStyle={sizeStyle}
                contentType={contentType}
                imageClass={getPSSysImage?.cssClass}
                htmlContent={htmlContent}
            >
                { Object.is(contentType, 'RAW') ? tempNode : null}
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
    public renderDruipart(modelJson: any, index: number): any {
        const { getPSSysCss, refreshItems, getLayoutPos, contentHeight, appView, parentdata, localContext, localParam, appDERSPaths } = modelJson;
        const { appDataEntity } = this.controlInstance;
        const { dynaModelFilePath } = appView;
        let tempContext: any = Object.assign(this.context, { viewpath: dynaModelFilePath });
        // druipart样式
        let druipartHeight: any;
        if (getLayoutPos?.layout == 'FlEX') {
            druipartHeight = '100%';
        } else if (contentHeight === 0 && appView?.height > 0) {
            druipartHeight = appView.height;
        } else {
            druipartHeight = contentHeight;
        }
        let druipartStyle = { height: druipartHeight, overflow: 'auto' };
        return (
            <app-form-druipart
                class={getPSSysCss?.cssName}
                formState={this.formState}
                isForbidLoad={this.data?.srfuf === '0'}
                paramItem={appDataEntity?.codeName?.toLowerCase()}
                parentdata={parentdata}
                parameters={Util.formatAppDERSPath(this.context, appDERSPaths)}
                context={tempContext}
                viewparams={this.viewparams}
                parameterName={appDataEntity?.codeName?.toLowerCase()}
                parentName={appDataEntity?.codeName}
                appViewtype={appView?.viewType}
                refreshitems={refreshItems}
                ignorefieldvaluechange={this.ignorefieldvaluechange}
                viewname={'app-view-shell'}
                localContext={Util.formatNavParam(localContext)}
                localParam={Util.formatNavParam(localParam)}
                tempMode={appView.tempMode ? appView.tempMode : 0}
                data={JSON.stringify(this.data)}
                on-drdatasaved={($event: any)=>this.drdatasaved($event)}
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
    public renderButton(modelJson: IBizFormButtonModel, index: number): any {
        let { getPSUIAction: uiAction, getSysImage: sysImage, name, width, height, showCaption, caption, getPSSysCss } = modelJson;
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
        if (getPSSysCss?.cssName) {
            Object.assign(controlClassNames, { [getPSSysCss.cssName]: true });
        }
        let title = uiAction.caption;
        const { appDataEntity, codeName, controlType } = this.controlInstance;
        if (appDataEntity) {
            let tag = `entities.${appDataEntity.codeName?.toLowerCase()}.${codeName?.toLowerCase()}_${controlType?.toLowerCase()}.uiactions.`;
            if (uiAction?.$appDataEntity) {
                tag += uiAction.$appDataEntity?.codeName?.toLowerCase() + "_";
            }
            tag += uiAction?.uIActionTag?.toLowerCase();
            title = this.$t(tag) || title;
        }
        return (
            <div>
                {badge}
                <i-button title={title} type='primary' class={controlClassNames} style={btnClass} on-click={($event: any) => this.onFormItemActionClick({ tag: modelJson.name, event: $event })} disabled={this.detailsModel[modelJson.name]?.disabled}>
                    {sysImage ? (
                        <i class={sysImage?.cssClass} style='margin-right: 2px;'></i>
                    ) : (
                        <menu-icon item={{ iconcls: uiAction?.getPSSysImage?.cssClass }} />
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
    public renderFormItem(modelJson: IBizFormItemModel, index: number): any {
        const { editor } = modelJson;
        return (
            <app-default-search-form-item
                detailsInstance={modelJson}
                index={index}
                data={this.data}
                rules={this.rules[modelJson.name]}
                runtimeModel={this.detailsModel[modelJson.name]}
                context={this.context}
                viewparams={this.viewparams}
                contextState={this.formState}
                service={this.service}
                ignorefieldvaluechange={this.ignorefieldvaluechange}
                on-formItemValueChange={(value: any) => {
                    this.onFormItemValueChange({ name: editor.name, value: value });
                }}
                controlInstance={this.controlInstance}
            >
                {editor && (
                    <app-default-editor
                        editorInstance={editor}
                        value={this.data[editor.name]}
                        contextData={this.data}
                        context={this.context}
                        viewparams={this.viewparams}
                        contextState={this.formState}
                        service={this.service}
                        disabled={this.detailsModel[modelJson.name]?.disabled}
                        ignorefieldvaluechange={this.ignorefieldvaluechange}
                        on-change={(value: any) => {
                            this.onFormItemValueChange(value);
                        }}
                    />
                )}
            </app-default-search-form-item>
        );
    }

    /**
     * 绘制分页部件panel
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderTabPanel(modelJson: any, index: number): any {
        return (
            <app-default-search-form-tab-panel
                detailsInstance={modelJson}
                index={index}
                controlInstance={this.controlInstance}>
                { this.renderDetails(modelJson)}
            </app-default-search-form-tab-panel>
        )
    }

    /**
     * 绘制分页部件
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderTabPage(modelJson: any, index: number): any {
        return (
            <app-default-search-form-tab-page 
                detailsInstance={modelJson}
                index={index}
                controlInstance={this.controlInstance}>
                { this.renderDetails(modelJson)}
            </app-default-search-form-tab-page>
        );
    }

    /**
     * 绘制分组面板
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderGroupPanel(modelJson: IBizFormGroupPanelModel, index: number): any {
        return (
            <app-default-group-panel
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
                on-groupUIActionClick={this.handleActionClick.bind(this)}
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
    public renderFormPage(modelJson: IBizFormPageModel, index: number): any {
        const { noTabHeader } = this.controlInstance;
        if (noTabHeader) {
            return this.renderDetails(modelJson);
        }
        return (
            <app-default-search-form-page
                detailsInstance={modelJson}
                index={index}
                runtimeModel={this.detailsModel[modelJson.name]}
                controlInstance={this.controlInstance}
            >
                {this.renderDetails(modelJson)}
            </app-default-search-form-page>
        );
    }

    /**
     * 绘制表单内容
     *
     * @returns
     * @memberof AppFormBase
     */
    public renderFormContent() {
        const { formPages, noTabHeader, codeName, name, appDataEntity, controlType } = this.controlInstance;
        if (formPages && formPages.length > 0) {
            if (noTabHeader) {
                return formPages.map((item: any, index: number) => {
                    return this.renderFormPage(item, index);
                });
            } else {
                const tabsName = `${appDataEntity.codeName.toLowerCase()}_${controlType?.toLowerCase()}_${codeName.toLowerCase()}`;
                return (
                    <tabs animated={false} name={tabsName}
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
     * @memberof AppSearchFormBase
     */
    public render(): any {
        const isShow = this.controlInstance.formStyle != 'SEARCHBAR' ? this.isExpandSearchForm : true;
        if (!(this.controlIsLoaded && isShow)) {
            return
        }
        const { controlClassNames } = this.renderOptions;
        let formId = this.controlInstance.appDataEntity.codeName.toLowerCase() + this.controlInstance.codeName.toLowerCase();
        return (
            <i-form
                props={{ model: this.data }}
                class={{ ...controlClassNames, 'app-search-form': true }}
                ref={this.controlInstance.name}
                id={formId}
                on-on-valuidate={this.formItemValidate.bind(this)}
            >
                <input style='display:none;' />
                <row>
                    <i-col span="22" class="form-content">
                        <row>
                            {this.renderFormContent()}
                        </row>
                    </i-col>
                    {this.controlInstance.formStyle != 'SEARCHBAR' &&
                        <i-col span="2" class="search-button">
                            {Object.keys(this.data).length > 0 && <row>
                                <i-button class='search_reset' size='default' type='primary' on-click={this.onSearch.bind(this)}>{this.$t('app.searchButton.search')}</i-button>
                                <i-button class='search_reset' size='default' on-click={this.onReset.bind(this)}>{this.$t('app.searchButton.reset')}</i-button>
                            </row>}
                        </i-col>
                    }
                </row>
            </i-form>
        );
    }
}
