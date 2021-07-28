import { Prop, Watch, Emit } from 'vue-property-decorator';
import { throttle, Util } from 'ibiz-core';
import { PanelControlBase } from '../../../widgets';
import { IPSDEViewPanel, IPSPanel, IPSPanelButton, IPSPanelContainer, IPSPanelControl, IPSPanelItem, IPSPanelRawItem, IPSPanelTabPage, IPSPanelTabPanel, IPSSysCss, IPSSysPanelField, IPSUIAction } from '@ibiz/dynamic-model-api';

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
     * 监听数据对象
     *
     * @memberof AppPanelBase
     */
     @Watch('data', { deep: true })
     public onDataChange(newVal: any, oldVal: any) {
         if (newVal) {
            this.computeButtonState(newVal);
            this.panelLogic({ name: '', newVal: null, oldVal: null });
            this.$forceUpdate();
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
     * FLEX布局时类名映射
     *
     * @memberof AppPanelBase
     */
    public classObj: any = {
        BUTTON: 'app-viewpanel-button',
        FIELD: 'app-viewpanel-field',
        RAWITEM: 'app-viewpanel-rowitem',
        TABPAGE: 'app-viewpanel-tabpage',
        TABPANEL: 'app-viewpanel-tabpanel',
    };

    /**
     * 获取FLEX布局时类名
     * @param item
     */
    public renderDetailClass(item: any) {
         // 映射类名
         let detailClass: any = this.classObj[item.itemType] || '';
         detailClass += ` viewpanel-${item.itemType.toLowerCase()}-${item.name.toLowerCase()}`;
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
            return this.renderByDetailType(container);
        });
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @memberof AppPanelBase
     */
    public renderByDetailType(modelJson: any) {
        if (modelJson.getPSSysPFPlugin()) {
            const pluginInstance: any = this.PluginFactory.getPluginInstance("CONTROLITEM", modelJson.getPSSysPFPlugin().pluginCode);
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, modelJson, this, this.data);
            }
        } else {
            switch (modelJson.itemType) {
                case 'CONTAINER':
                    return this.renderContainer(modelJson);
                case 'BUTTON':
                    return this.renderButton(modelJson);
                case 'FIELD':
                    return this.renderField(modelJson);
                case 'RAWITEM':
                    return this.renderRawitem(modelJson);
                case 'TABPANEL':
                    return this.renderTabPanel(modelJson);
                case 'TABPAGE':
                    return this.renderTabPage(modelJson);
                case 'CONTROL':
                    return this.renderControl(modelJson);
            }
        }
    }

    /**
     * 绘制面板Container
     *
     * @memberof AppPanelBase
     */

    public renderContainer(container: IPSPanelContainer) {
        const panelItems: IPSPanelItem[] = container.getPSPanelItems() || [];
        let layout = container.getPSLayout() as any;
        let layoutMode = container.getPSLayout()?.layout;
        let css = container.getPSSysCss() as IPSSysCss;
        let containerClass = {
            'app-viewpanel-container': true,
            [`viewpanel-container-${container.name.toLowerCase()}`]: true,
            'show-caption': container.showCaption
        };
        if (css && css.cssName) {
            Object.assign(containerClass, { [css.cssName]: true });
        }
        let containerStyle = {};
        if (container.width) {
            Object.assign(containerStyle, { width: container.width + 'px' });
        }
        if (container.height) {
            Object.assign(containerStyle, { height: container.height + 'px' });
        }
        if (this.detailsModel[container.name] && !this.detailsModel[container.name].visible) {
            Object.assign(containerStyle, { display: 'none' });
        }
        // FLEX布局
        if (layout && layoutMode == 'FLEX') {
            const containerGrow = (container.getPSLayoutPos() as any)?.grow;
            Object.assign(containerStyle, {
                'overflow': 'auto',
                'display': 'flex',
                'flex-grow': containerGrow && containerGrow != -1 ? containerGrow : 0
            });
            const { dir, align, vAlign } = layout;
            if (dir) {
                Object.assign(containerStyle, { 'flex-direction': dir });
            }
            if (align) {
                Object.assign(containerStyle, { 'justify-content': align });
            }
            if (vAlign) {
                Object.assign(containerStyle, { 'align-items': vAlign });
            }
            return (
                <i-col style={containerStyle} class={containerClass}>
                    {container.showCaption ? <div class="viewpanel-container-header">
                        <span>{this.$tl(container.getCapPSLanguageRes?.()?.lanResTag, container.caption)}</span>
                    </div> : null}
                        {panelItems.map((item: any, index: number) => {
                           // 子样式
                            let { height, width, itemType } = item;
                            let detailStyle: any = {};
                            if (height) {
                                detailStyle.height = height + 'px';
                            }
                            switch (itemType) {
                                case 'CONTAINER':
                                    return this.renderByDetailType(item);
                                case 'CTRLPOS':
                                    detailStyle.width = width ? width + 'px' : '100%';
                                    break;
                            }
                            if (item.getPSLayoutPos()) {
                                let grow = item.getPSLayoutPos()?.grow;
                                detailStyle.flexGrow = grow != -1 ? grow : 0;
                            }
                            // 自定义类名
                            const controlClassName = this.renderDetailClass(item);
                            return (
                                <div style={detailStyle} class={controlClassName}>
                                    {this.renderByDetailType(item)}
                                </div>
                            );
                        })}
                </i-col>
            );
        } else {
            let attrs = this.getGridLayoutProps(null, container);
            // 栅格布局
            return (
                <i-col {...{ props: attrs }} style={containerStyle} class={containerClass}>
                    {container.showCaption ? <row class="viewpanel-container-header">
                        <span>{this.$tl(container.getCapPSLanguageRes?.()?.lanResTag, container.caption)}</span>
                    </row> : null}
                    {panelItems.map((item: any, index: number) => {
                        let { height, width, itemType } = item;
                        let detailStyle: any = {};
                        if (this.detailsModel[container.name] && !this.detailsModel[container.name].visible) {
                            Object.assign(detailStyle, { display: 'none' });
                        }
                        if (height) {
                            detailStyle.height = height + 'px';
                        }
                        switch (itemType) {
                            case 'CONTAINER':
                                return this.renderByDetailType(item);
                            case 'CTRLPOS':
                                detailStyle.width = width ? width + 'px' : '100%';
                                break;
                        }
                        // 栅格布局
                        let attrs = this.getGridLayoutProps(container, item);
                        // 自定义类名
                        const controlClassName = this.renderDetailClass(item);
                        return (
                            <i-col {...{ props: attrs }} style={detailStyle} class={controlClassName}>
                                {this.renderByDetailType(item)}
                            </i-col>
                        );
                    })}
                </i-col>
            );
        }
    }

    /**
     * 绘制面板Button
     *
     * @memberof AppPanelBase
     */

    public renderButton(modelJson: IPSPanelButton) {
        let {
            caption,
            showCaption,
            name,
            height,
            tooltip,
        } = modelJson;
        const buttonStyle = {
            height: height && height > 0 ? height + 'px' : '',
        };
        const icon = modelJson.getPSSysImage();
        const uiAction = modelJson.getPSUIAction() as IPSUIAction;
        return (
            <app-panel-button
                buttonStyle={buttonStyle}
                caption={this.$tl(uiAction?.getCapPSLanguageRes()?.lanResTag, caption)}
                tooltip={this.$tl(uiAction?.getTooltipPSLanguageRes()?.lanResTag, tooltip)}
                icon={
                    icon?.cssClass
                        ? icon.cssClass
                        : uiAction?.getPSSysImage()?.cssClass
                            ? uiAction.getPSSysImage()?.cssClass
                            : null
                }
                showCaption={showCaption}
                disabled={this.detailsModel[name]?.disabled}
                on-onClick={($event: any) => {
                  throttle(this.buttonClick,[this.controlInstance.name, { tag: name }, $event],this);
                }}
            ></app-panel-button>
        );
    }

    /**
     * 绘制面板Field
     *
     * @memberof AppPanelBase
     */

    public renderField(modelJson: IPSSysPanelField) {
        let { name, caption, hidden, showCaption } = modelJson;
        const editor: any = modelJson.getPSEditor();
        let customCode: boolean = false;
        if (this.dataMap && this.dataMap.get(name)) {
            customCode = this.dataMap.get(name).customCode;
        }
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
                    required={this.detailsModel[modelJson.name]?.required}
                >
                    {editor && !customCode &&(
                        <app-default-editor
                            value={this.data[editor.name]}
                            editorInstance={editor}
                            containerCtrl={this.controlInstance}
                            parentItem={modelJson}
                            contextData={this.data}
                            valueFormat={modelJson.valueFormat}
                            context={this.context}
                            viewparams={this.viewparams}
                            disabled={this.detailsModel[modelJson.name]?.disabled}
                            on-change={(value: any) => {
                                this.onPanelItemValueChange(this.data, value);
                            }}
                        />
                    )}
                    {
                        customCode && this.$createElement('div', {
                            domProps: {
                                innerHTML: this.data[name],
                            },
                        })
                    }
                </app-panel-field>
            )
        );
    }

    /**
     * 绘制面板Rawitem
     *
     * @memberof AppPanelBase
     */

    public renderRawitem(modelJson: IPSPanelRawItem) {
        let { rawItemHeight, rawItemWidth, contentType, htmlContent, rawContent } = modelJson;
        let sysCssName = modelJson.getPSSysCss()?.cssName;
        let sysImage = modelJson.getPSSysImage()?.cssClass;
        const style: any = {
            width: rawItemWidth > 0 ? `${rawItemWidth}px` : '100%',
            height: rawItemHeight > 0 ? `${rawItemHeight}px` : '',
        }
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
                class={sysCssName}
                style={style}
                viewparams={this.viewparams}
                context={this.context}
                contentType={contentType}
                imageClass={sysImage}
                htmlContent={htmlContent}
            >
                {Object.is(contentType, 'RAW') && tempNode}
            </app-rawitem>
        );
    }

    /**
     * 绘制面板TabPanel
     *
     * @memberof AppPanelBase
     */

    public renderTabPanel(modelJson: IPSPanelTabPanel) {
        let activatedPage = this.detailsModel[modelJson.name]?.activatedPage;
        const tabPages: IPSPanelTabPage[] = modelJson.getPSPanelTabPages() || [];
        return (
            <i-col class={this.renderDetailClass(modelJson)}>
                <el-tabs
                    v-model={activatedPage}
                    on-tab-click={($event: any) => throttle(this.handleTabPanelClick,[modelJson.name, $event],this)}
                    class={this.renderDetailClass(modelJson)}
                >
                    {tabPages.length > 0 ?
                        tabPages.map((item: IPSPanelTabPage, index: number) => {
                            return this.renderTabPage(item);
                        }) : null}
                </el-tabs>
            </i-col>
        );
    }

    /**
     * 绘制面板TabPage
     *
     * @memberof AppPanelBase
     */
    public renderTabPage(modelJson: IPSPanelTabPage) {
        let label = this.$tl(modelJson.getCapPSLanguageRes?.()?.lanResTag, modelJson.caption) || '分页';
        const panelItems: IPSPanelItem[] = modelJson.getPSPanelItems() || [];
        return (
            <el-tab-pane label={label} name={modelJson.name} class={this.renderDetailClass(modelJson)}>
                {panelItems.map((item: IPSPanelItem, index: number) => {
                    return this.renderByDetailType(item)
                })}
            </el-tab-pane>
        );
    }

    /**
     * 绘制面板Control
     *
     * @memberof AppPanelBase
     */
    public renderControl(modelJson: IPSPanelControl) {
        const { showCaption, caption, height, width } = modelJson;
        const cssName: any = modelJson.getPSSysCss()?.cssName;
        const controlStyle: any ={
            'height': height ? height + 'px' : '',
            'width': width ? width + 'px' : '100%',
        }
        const controlModelJson: any = modelJson.getPSControl();
        return (
            <div class={['control',cssName,controlModelJson?.controlType?.toLocaleLowerCase()]} style={controlStyle}>
                {
                    showCaption && caption ? 
                    <div class='control-caption'>
                        <p>
                            {caption}
                        </p>
                        <el-divider></el-divider>
                    </div>
                    : null
                }
                <div class={{ 'control-with-caption': showCaption, 'control-without-caption': !showCaption }}>{this.renderByControlType(controlModelJson)}</div>
            </div>
        )
    }

    /**
     * 根据controlType绘制对应control
     *
     * @param {*} modelJson
     * @memberof AppPanelBase
     */
    public renderByControlType(modelJson: any) {
        switch (modelJson.controlType) {
            case 'VIEWPANEL':
                return this.renderViewPanel(modelJson);
        }
    }

    /**
     * 绘制ViewPanel
     * 
     * @param {*} control
     * @memberof AppPanelBase
     */
    public renderViewPanel(modelJson: IPSDEViewPanel) {
        let controlAppView = modelJson.getEmbeddedPSAppDEView();
        if (!controlAppView) {
            return;
        }
        const { modelFilePath, name } = controlAppView;
        let tempContext: any = Object.assign(Util.deepCopy(this.context), { viewpath: modelFilePath });
        return this.$createElement('app-view-shell', {
            props: {
                staticProps: {
                    portletState: this.viewState,
                    viewDefaultUsage: false,
                    viewModelData: controlAppView
                },
                dynamicProps: {
                    viewdata: JSON.stringify(tempContext),
                    viewparam: JSON.stringify(this.viewparams),
                },
            },
            on: {
                'viewIsMounted': () => {
                    this.setIsMounted(controlAppView?.name);
                }
            },
            ref: name,
        });
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
            'app-viewpanel': true,
            ...this.renderOptions.controlClassNames,
        };
        let { width, height, layoutMode } = this.controlInstance;
        let controlStyle: any = {};
        controlStyle.width = width > 0 ? width + 'px' : '100%';
        controlStyle.height = height > 0 ? height + 'px' : '100%';
        if (layoutMode == 'FLEX') {
            controlStyle.display = 'flex';
        }
        return (
            <div class="viewpanel-container" style={{'width': '100%' }}>
                <row class={controlClassNames} style={controlStyle}>
                    {this.renderRootPSPanelItems(this.controlInstance)}
                </row>
            </div>
        );
    }
}
