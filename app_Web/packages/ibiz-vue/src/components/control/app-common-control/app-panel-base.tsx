import { Prop, Watch, Emit } from 'vue-property-decorator';
import { debounce, Util } from 'ibiz-core';
import { PanelControlBase } from '../../../widgets';
import { IPSCodeListEditor, IPSPanel, IPSPanelItem, IPSPanelTabPage, IPSSysPanelField, IPSUIAction } from '@ibiz/dynamic-model-api';

/**
 * 面板部件基类
 *
 * @export
 * @class AppPanelBase
 * @extends {PanelControlBase}
 */
export class AppPanelBase extends PanelControlBase {
    /**
     * 部件动态参数
     *
     * @memberof AppPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppPanelBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPanelBase
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
     * @memberof AppPanelBase
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
     * @memberof AppPanelBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDefaultTree
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 监听数据对象
     *
     * @memberof AppPanelBase
     */
    @Watch('inputData', { deep: true })
    public onInputDataChange(newVal: any, oldVal: any) {
        if (newVal) {
            this.computedUIData(newVal);
            this.panelData = Util.deepCopy(newVal);
            this.computeButtonState(newVal);
            this.panelLogic({ name: '', newVal: null, oldVal: null });
            this.$forceUpdate();
        }
    }

    /**
     * FLEX布局时类名映射
     *
     * @memberof AppPanelBase
     */
    public classObj: any = {
        BUTTON: 'app-layoutpanel-button',
        CONTAINER: 'app-layoutpanel-container',
        FIELD: 'app-layoutpanel-field',
        RAWITEM: 'app-layoutpanel-rowitem',
        TABPAGE: 'app-layoutpanel-tabpage',
        TABPANEL: 'app-layoutpanel-tabpanel',
    };

    /**
     * 获取FLEX布局时类名
     * @param item
     */
    public renderDetailClass(item: any) {
        // 映射类名
        let detailClass: any = this.classObj[item.itemType];
        if (item?.getPSSysCss?.()) {
            detailClass += ` ${item.getPSSysCss().cssName}`;
        }
        return detailClass;
    }

    /**
     * 获取栅格布局的props
     *
     * @param {*} parent 父
     * @param {*} child 子
     * @returns {*}
     * @memberof AppPanelBase
     */
    public getGridLayoutProps(parent: any, child: any): any {
        let layout = parent?.getPSLayout().layout;
        let { colXS, colSM, colMD, colLG, colXSOffset, colSMOffset, colMDOffset, colLGOffset } = child.getPSLayoutPos();
        // 设置初始值
        colXS = !colXS || colXS == -1 ? 24 : colXS;
        colSM = !colSM || colSM == -1 ? 24 : colSM;
        colMD = !colMD || colMD == -1 ? 24 : colMD;
        colLG = !colLG || colLG == -1 ? 24 : colLG;
        colXSOffset = !colXSOffset || colXSOffset == -1 ? 0 : colXSOffset;
        colSMOffset = !colSMOffset || colSMOffset == -1 ? 0 : colSMOffset;
        colMDOffset = !colMDOffset || colMDOffset == -1 ? 0 : colMDOffset;
        colLGOffset = !colLGOffset || colLGOffset == -1 ? 0 : colLGOffset;
        if (layout == 'TABLE_12COL') {
            // 重新计算12列的栅格数值
            colXS = Math.min(colXS * 2, 24);
            colSM = Math.min(colSM * 2, 24);
            colMD = Math.min(colMD * 2, 24);
            colLG = Math.min(colXS * 2, 24);
            // 重新计算12列的栅格偏移
            let sign = (num: number) => (num == 0 ? 0 : num / Math.abs(num));
            colXSOffset = sign(colXSOffset) * Math.min(colXSOffset * 2, 24);
            colSMOffset = sign(colSMOffset) * Math.min(colSMOffset * 2, 24);
            colMDOffset = sign(colMDOffset) * Math.min(colMDOffset * 2, 24);
            colLGOffset = sign(colLGOffset) * Math.min(colLGOffset * 2, 24);
        }
        return {
            xs: { span: colXS, offset: colXSOffset },
            sm: { span: colSM, offset: colSMOffset },
            md: { span: colMD, offset: colMDOffset },
            lg: { span: colLG, offset: colLGOffset },
        };
    }

    /**
     * 绘制顶级面板成员集合
     *
     * @memberof AppPanelBase
     */

    public renderRootPSPanelItems(controlInstance: IPSPanel) {
        return controlInstance.getRootPSPanelItems()?.map((container: any, index: number) => {
            return this.renderByDetailType(container, index);
        });
    }

    /**
     * 绘制面板成员集合
     *
     * @memberof AppPanelBase
     */

    public renderPanelItems(container: any) {
        if (!container.getPSPanelItems() || container.getPSPanelItems().length == 0) {
            return null;
        }
        let layoutMode = container.getPSLayout()?.layout;
        // FLEX布局
        if (layoutMode == 'FLEX') {
            let cssStyle: string = 'width: 100%; height: 100%; overflow: auto; display: flex;';
            cssStyle += container.getPSLayout().dir ? `flex-direction: ${container.getPSLayout().dir};` : '';
            cssStyle += container.getPSLayout().align ? `justify-content: ${container.getPSLayout().align};` : '';
            cssStyle += container.getPSLayout().vAlign ? `align-items: ${container.getPSLayout().vAlign};` : '';
            return (
                <div style={cssStyle}>
                    {container.getPSPanelItems().map((item: any, index: number) => {
                        // 子样式
                        let detailStyle: any = {
                            'display': this.detailsModel[item.name]?.visible ? false : 'none',
                        };
                        if (item.getPSLayoutPos()) {
                            let { grow, height, width } = item.getPSLayoutPos();
                            detailStyle.flexGrow = grow != -1 ? grow : 0;
                            detailStyle.height = height > 0 ? height + 'px' : '';
                            detailStyle.width = width > 0 ? width + 'px' : '';
                        }
                        // 自定义类名
                        const controlClassName = this.renderDetailClass(item);
                        return (
                            <div style={detailStyle} class={controlClassName}>
                                {this.renderByDetailType(item, index)}
                            </div>
                        );
                    })}
                </div>
            );
        } else {
            // 栅格布局
            return (
                <row style="height:100%;">
                    {container.getPSPanelItems().map((item: any, index: number) => {
                        //子样式
                        let detailStyle: any = {
                            'display': this.detailsModel[item.name]?.visible ? false : 'none',
                        };
                        if (item.getPSLayoutPos()) {
                            let { height, width } = item.getPSLayoutPos();
                            detailStyle.height = height > 0 ? height + 'px' : '';
                            detailStyle.width = width > 0 ? width + 'px' : '';
                        }
                        // 栅格布局
                        let attrs = this.getGridLayoutProps(container, item);
                        // 自定义类名
                        const controlClassName = this.renderDetailClass(item);
                        return (
                            <i-col {...{ props: attrs }} style={detailStyle} class={controlClassName}>
                                {this.renderByDetailType(item, index)}
                            </i-col>
                        );
                    })}
                </row>
            );
        }
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @param {number} index
     * @memberof AppPanelBase
     */
    public renderByDetailType(modelJson: any, index: number) {
        if (modelJson.getPSSysPFPlugin()) {
            const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", modelJson.getPSSysPFPlugin().pluginCode);
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, modelJson, this, this.data);
            }
        } else {
            switch (modelJson.itemType) {
                case 'CONTAINER':
                    return this.renderContainer(modelJson, index);
                case 'BUTTON':
                    return this.renderButton(modelJson, index);
                case 'FIELD':
                    return this.renderField(modelJson, index);
                case 'RAWITEM':
                    return this.renderRawitem(modelJson, index);
                case 'TABPANEL':
                    return this.renderTabPanel(modelJson, index);
                case 'TABPAGE':
                    return this.renderTabPage(modelJson, index);
            }
        }
    }

    /**
     * 绘制面板Container
     *
     * @memberof AppPanelBase
     */

    public renderContainer(modelJson: any, index: number) {
        return this.renderPanelItems(modelJson);
    }

    /**
     * 绘制面板Button
     *
     * @memberof AppPanelBase
     */

    public renderButton(modelJson: any, index: number) {
        let {
            caption,
            showCaption,
            xDataControlName,
            name,
            height,
            tooltip,
        } = modelJson;
        const buttonStyle = {
            height: height && height > 0 ? height + 'px' : false,
        };
        const icon = modelJson.getPSSysImage();
        const uiAction = modelJson.getPSUIAction() as IPSUIAction;
        const labelPSSysCss = modelJson?.getLabelPSSysCss?.();
        return (
            <app-panel-button
                buttonStyle={buttonStyle}
                caption={this.$tl(uiAction.getCapPSLanguageRes()?.lanResTag, caption)}
                tooltip={this.$tl(uiAction.getTooltipPSLanguageRes()?.lanResTag, tooltip)}
                icon={
                    icon?.cssClass
                        ? icon.cssClass
                        : uiAction?.getPSSysImage()?.cssClass
                            ? uiAction.getPSSysImage()?.cssClass
                            : null
                }
                showCaption={showCaption}
                disabled={this.detailsModel[name]?.disabled}
                lableStyle={labelPSSysCss?.cssName}
                on-onClick={($event: any) => {
                  debounce(this.buttonClick,[this.controlInstance.name, { tag: name }, $event],this);
                }}
            ></app-panel-button>
        );
    }

    /**
     * 绘制面板Field
     *
     * @memberof AppPanelBase
     */

    public renderField(modelJson: IPSSysPanelField, index: number) {
        let { name, caption, hidden, showCaption } = modelJson;
        const editor: any = modelJson.getPSEditor();
        let labelPos = 'LEFT';
        return (
            !hidden && (
                <app-panel-field
                    name={name}
                    labelPos={labelPos}
                    caption={this.$tl(modelJson.getCapPSLanguageRes()?.lanResTag, caption)}
                    isEmptyCaption={!showCaption}
                    error={this.rules[editor.name]?.message}
                    data={this.data}
                    value={this.data[name]}
                    itemRules={this.rules[editor.name]}
                >
                    {editor && (
                        <app-default-editor
                            value={this.data[editor.name]}
                            editorInstance={editor}
                            containerCtrl={this.controlInstance}
                            parentItem={modelJson}
                            contextData={this.data}
                            context={this.context}
                            viewparams={this.viewparams}
                            on-change={(value: any) => {
                                this.onPanelItemValueChange(this.data, value);
                            }}
                        />
                    )}
                </app-panel-field>
            )
        );
    }

    /**
     * 绘制面板Rawitem
     *
     * @memberof AppPanelBase
     */

    public renderRawitem(modelJson: any, index: number) {
        let {
            rawItemWidth,
            rawItemHeight,
            contentType,
            htmlContent,
            rawContent,
        } = modelJson;
        let sizeStyle: any = {};
        sizeStyle.width = rawItemWidth > 0 ? rawItemWidth + 'px' : '';
        sizeStyle.height = rawItemHeight > 0 ? rawItemHeight + 'px' : '';
        if (rawContent) {
            const items = rawContent.match(/\{{(.+?)\}}/g);
            if (items) {
                items.forEach((item: string) => {
                    rawContent = rawContent.replace(/\{{(.+?)\}}/, eval(item.substring(2, item.length - 2)));
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
                context={this.context}
                viewparams={this.viewparams}
                contentStyle={modelJson?.getPSSysCss()?.cssName}
                sizeStyle={sizeStyle}
                contentType={contentType}
                htmlContent={htmlContent}
                getPSSysImage={modelJson.getPSSysImage()?.cssClass}
            >
                {contentType === 'RAW' && tempNode}
            </app-rawitem>
        );
    }

    /**
     * 绘制面板TabPanel
     *
     * @memberof AppPanelBase
     */

    public renderTabPanel(modelJson: any, index: number) {
        let { name, getPSPanelTabPages } = modelJson;
        let activatedPage = this.detailsModel[name]?.activatedPage;
        return (
            <i-col class={this.renderDetailClass(modelJson)}>
                <el-tabs
                    v-model={activatedPage}
                    on-tab-click={($event: any) => debounce(this.handleTabPanelClick,[name, $event],this)}
                    class={this.renderDetailClass(modelJson)}
                >
                    {getPSPanelTabPages &&
                        getPSPanelTabPages.map((item: any, index: number) => {
                            return this.renderTabPage(item, index);
                        })}
                </el-tabs>
            </i-col>
        );
    }

    /**
     * 绘制面板TabPage
     *
     * @memberof AppPanelBase
     */

    public renderTabPage(modelJson: IPSPanelTabPage, index: number) {
        let label = this.$tl(modelJson.getCapPSLanguageRes()?.lanResTag, modelJson.caption) || '分页';
        const panelItems: IPSPanelItem[] = modelJson.getPSPanelItems() || [];
        return (
            <el-tab-pane label={label} name={modelJson.name} class={this.renderDetailClass(modelJson)}>
                {panelItems.map((item: IPSPanelItem, index: number) => {
                        return this.renderByDetailType(item, index);
                    })}
            </el-tab-pane>
        );
    }

    /**
     * 部件模型数据初始化
     *
     * @memberof AppPanelBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (this.controlInstance.getRootPSPanelItems() && (this.controlInstance.getRootPSPanelItems() as any)?.length > 0) {
            for (let i = 0; i < (this.controlInstance.getRootPSPanelItems() as any).length; i++) {
                const item = (this.controlInstance.getRootPSPanelItems() as any)[i];
                const getPSPanelItems = item.getPSPanelItems();
                if (getPSPanelItems) {
                    for (let i = 0; i < getPSPanelItems.length; i++) {
                        const panelItems = getPSPanelItems[i]?.getPSPanelItems?.();
                        if (Array.isArray(panelItems) && panelItems.length > 0) {
                            for (let i = 0; i < panelItems.length; i++) {
                                const item: IPSSysPanelField = panelItems[i];
                                //获取面板项代码表
                                if ((item?.getPSEditor?.() as IPSCodeListEditor)?.getPSAppCodeList?.()) {
                                    const codeList = (item.getPSEditor() as IPSCodeListEditor)?.getPSAppCodeList();
                                    if (codeList) {
                                        if (codeList.isFill) {
                                            await codeList.fill();
                                            // item.codelist = tempCodeList;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 绘制面板
     *
     * @returns {*}
     * @memberof AppPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }


        let controlClassNames = {
            'app-layoutpanel': true,
            ...this.renderOptions.controlClassNames,
        };

        let { width, height, layoutMode } = this.controlInstance;

        let controlStyle: any = {};
        controlStyle.width = width > 0 ? width : '100%';
        controlStyle.height = height > 0 ? height : '100%';
        if (layoutMode == 'FLEX') {
            controlStyle.display = 'flex';
        }

        return (
            <div class="panel-container">
                <row class={controlClassNames} style={controlStyle}>
                    {this.renderRootPSPanelItems(this.controlInstance)}
                </row>
            </div>
        );
    }
}
