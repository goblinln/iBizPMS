import { Prop, Watch, Emit } from 'vue-property-decorator';
import { throttle, Util } from 'ibiz-core';
import { MobPanelControlBase } from '../../../widgets';
import { IPSCodeListEditor, IPSDEViewPanel, IPSPanelContainer, IPSPanelControl, IPSPanelItem, IPSPanelTabPage, IPSPanelTabPanel, IPSSysCss, IPSSysPanelField } from '@ibiz/dynamic-model-api';

/**
 * 多编辑面板部件基类
 *
 * @export
 * @class AppMobPanelBase
 * @extends {PanelControlBase}
 */
export class AppMobPanelBase extends MobPanelControlBase {
    /**
     * 部件动态参数
     *
     * @memberof AppMobPanelBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof PanelControlBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobPanelBase
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
     * @memberof AppMobPanelBase
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
     * @memberof AppMobPanelBase
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
     * @memberof AppMobPanelBase
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
        if (item.getPSSysCss()) {
            detailClass += ` ${item.getPSSysCss()?.cssName}`;
        }
        return detailClass;
    }

    /**
     * 获取栅格布局的props
     *
     * @param {*} parent 父
     * @param {*} child 子
     * @returns {*}
     * @memberof AppMobPanelBase
     */
    public getGridLayoutProps(parent: any, child: any): any {
        let layout = parent?.getPSLayout()?.layout;
        let { colXS, colSM, colMD, colLG, colXSOffset, colSMOffset, colMDOffset, colLGOffset } = child?.getPSLayoutPos();
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
            lg: colLG,
            span: colMD,
            sm: colSM,
            offset: colMDOffset,
        };

    }

    /**
     * 绘制顶级面板成员集合
     *
     * @memberof AppMobPanelBase
     */

    public renderRootPSPanelItems(controlInstance: any) {
        return controlInstance.getRootPSPanelItems()?.map((container: any, index: number) => {
            return this.renderByDetailType(container, index);
        });
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @param {number} index
     * @memberof AppMobPanelBase
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
                case 'CONTROL':
                    return this.renderControl(modelJson, index);               
            }
        }
    }

    /**
     * 绘制面板Container
     *
     * @memberof AppMobPanelBase
     */

    public renderContainer(container: IPSPanelContainer, index: number) {
        const panelItems: IPSPanelItem[] = container.getPSPanelItems() || [];
        let layout = container.getPSLayout() as any;
        let layoutMode = container.getPSLayout()?.layout;
        let css = container.getPSSysCss() as IPSSysCss;
        let containerClass = {
            'app-layoutpanel-container': true,
            [`layoutpanel-container-${container.name.toLowerCase()}`]: true,
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
                'width' : '100%',
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
                <van-col style={containerStyle} class={containerClass}>
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
                            if (this.detailsModel[item.name] && !this.detailsModel[item.name].visible) {
                                Object.assign(detailStyle, { display: 'none' });
                            }
                            switch (itemType) {
                                case 'CONTAINER':
                                    return this.renderByDetailType(item, index);
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
                                    {this.renderByDetailType(item, index)}
                                </div>
                            );
                        })}
                </van-col>
            );
        } else {
            let attrs = this.getGridLayoutProps(null, container);
            // 栅格布局
            return (
                <van-col {...{ props: attrs }} style={containerStyle} class={containerClass}>
                    {container.showCaption ? <van-row class="viewpanel-container-header">
                        <span>{this.$tl(container.getCapPSLanguageRes?.()?.lanResTag, container.caption)}</span>
                    </van-row> : null}
                    {panelItems.map((item: any, index: number) => {
                        let { height, width, itemType } = item;
                        let detailStyle: any = {};
                        if (this.detailsModel[item.name] && !this.detailsModel[item.name].visible) {
                            Object.assign(detailStyle, { display: 'none' });
                        }
                        if (height) {
                            detailStyle.height = height + 'px';
                        }
                        switch (itemType) {
                            case 'CONTAINER':
                                return this.renderByDetailType(item, index);
                            case 'CTRLPOS':
                                detailStyle.width = width ? width + 'px' : '100%';
                                break;
                        }
                        // 栅格布局
                        let attrs = this.getGridLayoutProps(container, item);
                        // 自定义类名
                        const controlClassName = this.renderDetailClass(item);
                        return (
                            <van-col {...{ props: attrs }} style={detailStyle} class={controlClassName}>
                                {this.renderByDetailType(item, index)}
                            </van-col>
                        );
                    })}
                </van-col>
            );
        }
    }

    /**
     * 绘制面板Button
     *
     * @memberof AppMobPanelBase
     */

    public renderButton(modelJson: any, index: number) {
        let {
            caption,
            showCaption,
            name,
            width,
            height,
        } = modelJson;
        const buttonStyle = {
            width: width && width > 0 ? width + 'px' : '',          
            height: height && height > 0 ? height + 'px' : '',
        };
        const icon = modelJson.getPSSysImage();
        const uiAction = modelJson.getPSUIAction();
        const labelPSSysCss = modelJson?.getLabelPSSysCss?.();
        return (
            <app-mob-button
                style={buttonStyle}
                text={showCaption ? caption : ''}
                iconName={
                    icon?.cssClass
                        ? icon.cssClass
                        : uiAction?.getPSSysImage()?.cssClass
                            ? uiAction.getPSSysImage().cssClass
                            : null
                }
                disabled={this.detailsModel[name]?.disabled}
                class={labelPSSysCss?.cssName}
                flexType={'horizontal'}
                showDefaultIcon={false}
                on-click={($event: any) => {
                    this.buttonClick(this.controlInstance.name, { tag: name }, $event);
                }}
            ></app-mob-button>
        );
    }

    /**
     * 绘制面板Field
     *
     * @memberof AppMobPanelBase
     */

    public renderField(modelJson: any, index: number) {
        let { name, caption, hidden, showCaption } = modelJson;
        const editor: any = modelJson.getPSEditor();
        if (this.needFindDEField) {
            this.findDEFieldForPanelField(modelJson);
        }
        let labelPos = 'LEFT';
        return (
          !hidden && (<app-panel-field
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
                {editor && (
                    <app-default-editor
                        value={this.data[editor.name]}
                        editorInstance={editor}
                        contextData={this.data}
                        containerCtrl={this.controlInstance}
                        parentItem={modelJson}
                        valueFormat={modelJson.valueFormat}                        
                        context={this.context}
                        viewparams={this.viewparams}
                        on-change={(value: any) => {
                            this.onPanelItemValueChange(this.data, value);
                        }}
                    />
                )}
            </app-panel-field>
          )
        )
    }
    /**
     * 绘制面板Rawitem
     *
     * @memberof AppMobPanelBase
     */

    public renderRawitem(modelJson: any, index: number) {
        let { rawItemHeight, rawItemWidth, contentType, htmlContent, rawContent } = modelJson;
        let sysCssName = modelJson.getPSSysCss()?.cssName;
        let sysImage = modelJson.getPSSysImage()?.cssClass;
        let sysImgurl = modelJson.getPSSysImage()?.imagePath;
        const style: any = {
            width: rawItemWidth > 0 ? `${rawItemWidth}px` : '100%',
            height: rawItemHeight > 0 ? `${rawItemHeight}px` : '100%',
        }
        let content: any;
        if (Object.is(contentType,'RAW')) {
            content = rawContent;
        } else if (Object.is(contentType,'HTML')){
            content = htmlContent;
        }
        if (content) {
            const items = content.match(/\{{(.+?)\}}/g);
            if (items) {
                items.forEach((item: string) => {
                    content = content.replace(/\{{(.+?)\}}/, eval(item.substring(2, item.length - 2)));
                });
            }
            content = content.replaceAll('&lt;','<');
            content = content.replaceAll('&gt;','>');
            content = content.replaceAll('&amp;nbsp;',' ');
            content = content.replaceAll('&nbsp;',' ');
        }
        return (
            <app-rawitem
                class={sysCssName}
                sizeStyle={style}
                viewparams={this.viewparams}
                context={this.context}
                contentType={contentType}
                imageClass={sysImage}
                imgUrl={sysImgurl}
                htmlContent={content}
                rawContent={content}
            >
              {content}
            </app-rawitem>
        );
    }

    /**
     * 绘制面板TabPanel
     *
     * @memberof AppMobPanelBase
     */

    public renderTabPanel(modelJson: IPSPanelTabPanel, index:number) {
        let attrs = this.getGridLayoutProps(null, modelJson);      
        let activatedPage = this.detailsModel[modelJson.name]?.activatedPage;
        const tabPages: IPSPanelTabPage[] = modelJson.getPSPanelTabPages() || [];
        return (
            <van-col {...{ props: attrs }}  class={['tab-panel-container']}>
                <van-tabs
                    type="card"
                    v-model={activatedPage}
                    on-click={($event: any) => throttle(this.handleTabPanelClick,[modelJson.name, $event],this)}
                    class={this.renderDetailClass(modelJson)}
                >
                    {tabPages.length > 0 ?
                        tabPages.map((item: IPSPanelTabPage, index: number) => {
                            return this.renderTabPage(item, index);
                        }) : null}
                </van-tabs>
            </van-col>
        );
    }

    /**
     * 绘制面板TabPage
     *
     * @memberof AppMobPanelBase
     */
    public renderTabPage(modelJson: IPSPanelTabPage, index:number) {
        let label = this.$tl(modelJson.getCapPSLanguageRes?.()?.lanResTag, modelJson.caption) || '分页';
        const panelItems: IPSPanelItem[] = modelJson.getPSPanelItems() || [];
        return (
            <van-tab title={label} name={modelJson.name} class={this.renderDetailClass(modelJson)}>
                {panelItems.map((item: IPSPanelItem, index: number) => {
                    return this.renderByDetailType(item, index)
                })}
            </van-tab>
        );
    }    

    /**
     * 绘制面板Control
     *
     * @memberof AppMobPanelBase
     */
    public renderControl(modelJson: IPSPanelControl, index:number) {
        const { showCaption, caption, height, width } = modelJson;
        const cssName: any = modelJson.getPSSysCss()?.cssName;
        const controlStyle: any ={
            'height': height ? height + 'px' : 'auto',
            'width': width ? width + 'px' : '100%',
        }
        const controlModelJson: any = modelJson.getPSControl();
        return (
            <van-col class={['control',cssName,controlModelJson?.controlType?.toLocaleLowerCase()]} style={controlStyle}>
                {
                    showCaption && caption ? 
                    <div class='control-caption'>
                        <p>
                            {caption}
                        </p>
                        <van-divider />
                    </div>
                    : null
                }
                <div class={{ 'control-with-caption': showCaption, 'control-without-caption': !showCaption }} style="height:100%;">{this.renderByControlType(controlModelJson)}</div>
            </van-col>
        )
    }

    /**
     * 根据controlType绘制对应control
     *
     * @param {*} modelJson
     * @memberof AppMobPanelBase
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
     * @memberof AppMobPanelBase
     */
    public renderViewPanel(modelJson: IPSDEViewPanel) {
        let controlAppView = modelJson.getEmbeddedPSAppDEView();
        if (!controlAppView) {
            return;
        }
        const { modelFilePath, name } = controlAppView;
        let tempContext: any = Object.assign(Util.deepCopy(this.context), { viewpath: modelFilePath, });
        const appDeKeyCodeName = this.controlInstance.getPSAppDataEntity()?.codeName?.toLowerCase();
        if(appDeKeyCodeName){
                Object.assign(tempContext,{[appDeKeyCodeName]:this.data.srfkey})
        }
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
     * @memberof AppMobPanelBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }

        let controlClassNames = {
            'app-layoutpanel': true,
            ...this.renderOptions.controlClassNames,
        };
        let { panelWidth, height, layoutMode } = this.controlInstance;
        let controlStyle: any = {};
        controlStyle.width = panelWidth > 0 ? panelWidth + 'px' : 'auto';
        controlStyle.height = height > 0 ? height + 'px' : '100%';
        if (layoutMode == 'FLEX') {
            controlStyle.display = 'flex';
        }        
        return (
            <div class="view-container" style="height:100%;width:100%">
                <van-row class={controlClassNames} style={controlStyle}>
                    {this.renderRootPSPanelItems(this.controlInstance)}
                </van-row>
            </div>
        );
    }
}
