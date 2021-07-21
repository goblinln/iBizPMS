import { IPSAppView, IPSPanelContainer, IPSPanelItem, IPSPanelRawItem, IPSPanelTabPage, IPSPanelTabPanel, IPSSysCss, IPSSysPanelField, IPSViewLayoutPanel } from '@ibiz/dynamic-model-api';
import { ModelTool, Util } from 'ibiz-core';
import Vue from 'vue';
import { Prop, Component } from 'vue-property-decorator';
import { PluginService } from '../../../../app-service/common-service/plugin-service';
import './app-default-view-layout.less';

/**
 * 视图基础布局
 *
 * @export
 * @class AppDefaultViewLayout
 * @extends {Vue}
 */
@Component({})
export class AppDefaultViewLayout extends Vue {

    /**
     * 视图模型数据
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop() public viewInstance!: IPSAppView;

    /**
     * 应用上下文
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop() public viewparams!: any;

    /**
     * 模型服务对象
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop() public modelService!: any;

    /**
     * 是否展示视图工具栏
     * 
     * @memberof AppDefaultViewLayout
     */
    public viewIsshowToolbar: boolean = false;

    /**
     * 视图模型数据
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop() public model!: any;

    /**
     * 是否显示标题栏
     *
     * @readonly
     * @memberof AppDefaultViewLayout
     */
    get showCaption() {
        if (this.viewInstance && this.$parent) {
            return this.viewInstance.showCaptionBar && !(this.$parent as any).noViewCaption
        } else {
            return true;
        }
    }

    /**
     * Vue生命周期，实例创建完成
     *
     * @memberof AppDefaultViewLayout
     */
    created(){
        this.viewIsshowToolbar = ModelTool.findPSControlByType("TOOLBAR", this.viewInstance.getPSControls()) ? true : false;
    }

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewHeader(): any {
        return [
            this.showCaption ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.model.srfCaption}</span> : null,
            this.viewIsshowToolbar ? <div class='toolbar-container'>
                {this.$slots.toolbar}
            </div> : null,
        ]
    }

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderContent() {
        let cardClass = {
            'view-card': true,
            'view-no-caption': !this.showCaption,
            'view-no-toolbar': !this.viewIsshowToolbar,
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                {(this.showCaption || this.viewIsshowToolbar) && (
                    <div slot='title' class='header-container' key='view-header'>
                        {this.renderViewHeader()}
                    </div>
                )}
                {this.$slots.topMessage}
                {this.$slots.searchForm}
                <div class='content-container'>
                    {(this.$slots.quickGroupSearch || this.$slots.quickSearch) && <div style="margin-bottom: 6px;">
                        {this.$slots.quickGroupSearch}
                        {this.$slots.quickSearchForm}
                        {this.$slots.quickSearch}
                    </div>}
                    {this.$slots.bodyMessage}
                    {this.$slots.default}
                </div>
                {this.$slots.bottomMessage}
            </card>
        );
    }

    /**
     * 绘制布局
     * 
     * @memberof AppDefaultViewLayout
     */
    public render(h: any) {
        const viewLayoutPanel: IPSViewLayoutPanel | null = this.viewInstance.getPSViewLayoutPanel();
        let viewClass = {
            'view-container': true,
            'view-default': true,
            [this.viewInstance.viewType.toLowerCase()]: true,
            [Util.srfFilePath2(this.viewInstance.codeName)]: true,
            [this.viewInstance.getPSSysCss()?.cssName || '']: true,
        };

        return (
            <div class={viewClass}>
                <app-studioaction
                    viewInstance={this.viewInstance}
                    context={this.context}
                    viewparams={this.viewparams}
                    viewName={this.viewInstance.codeName.toLowerCase()}
                    viewTitle={this.model?.srfCaption} />
                {(viewLayoutPanel && viewLayoutPanel.useDefaultLayout) ? this.renderContent() : this.renderRootPSPanelItems(viewLayoutPanel)}
            </div>
        );
    }

    /**
     * 绘制顶级面板成员集合
     *
     * @memberof AppDefaultViewLayout
     */

    public renderRootPSPanelItems(viewLayoutPanel: any) {
        return <row class="app-viewlayout-panel" style={{ 'height': '100%' }}>
            {viewLayoutPanel.getRootPSPanelItems()?.map((container: any, index: number) => {
                return this.renderByDetailType(container,true);
            })}
        </row>
    }

    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @memberof AppDefaultViewLayout
     */
    public renderByDetailType(modelJson: any, isRootContainer?: boolean, parent?: any) {
        if (modelJson.getPSSysPFPlugin()) {
            const pluginInstance: any = PluginService.getInstance().getPluginInstance("CONTROLITEM", modelJson.getPSSysPFPlugin().pluginCode);
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, modelJson, this, this.context);
            }
        }
        switch (modelJson.itemType) {
            case 'CONTAINER':
                return this.renderContainer(modelJson, isRootContainer);
            case 'TABPANEL':
                return this.renderTabPanel(modelJson);
            case 'TABPAGE':
                return this.renderTabPage(modelJson);
            case 'FIELD':
                return this.renderField(modelJson);
            case 'RAWITEM':
                return this.renderRawitem(modelJson);
            case 'CTRLPOS':
                return this.renderCtrlPos(modelJson, parent);
        }
    }

    /**
     * 绘制面板Container
     *
     * @memberof AppDefaultViewLayout
     */
    public renderContainer(container: IPSPanelContainer, isRootContainer: boolean = false) {
        const panelItems: IPSPanelItem[] = container.getPSPanelItems() || [];
        if (panelItems.length == 0) {
            return null;
        }
        let layout = container.getPSLayout() as any;
        let layoutMode = container.getPSLayout()?.layout;
        let css = container.getPSSysCss() as IPSSysCss;
        let containerClass = { 'app-viewlayoutpanel-container': true, 'show-caption': container.showCaption };
        if (isRootContainer && css && css.cssName) {
            Object.assign(containerClass, { [css.cssName]: true });
        }
        let containerStyle = {
            width: container.width ? container.width + 'px' : false,
            height: container.height ? container.height + 'px' : '100%',
        }
        // FLEX布局
        if (layout && layoutMode == 'FLEX') {
            let cssStyle: string = 'overflow: auto; display: flex;';
            cssStyle += layout.dir ? `flex-direction: ${layout.dir};` : '';
            cssStyle += layout.align ? `justify-content: ${layout.align};` : '';
            cssStyle += layout.vAlign ? `align-items: ${layout.vAlign};` : '';
            return (
                <i-col style={containerStyle} class={containerClass}>
                    {container.showCaption ? <div class="viewlayoutpanel-container-header">
                        <span>{this.$tl(container.getCapPSLanguageRes?.()?.lanResTag, container.caption)}</span>
                    </div> : null}
                    <div class="viewlayoutpanel-container-content" style={cssStyle}>
                        {panelItems.map((item: any, index: number) => {
                            // 子样式
                            let detailStyle: any = {};
                            let { height, width } = item;
                            detailStyle.height = height > 0 ? height + 'px' : '';
                            detailStyle.width = width > 0 ? width + 'px' : '';
                            if (item.getPSLayoutPos()) {
                                let grow = item.getPSLayoutPos();
                                detailStyle.flexGrow = grow != -1 ? grow : 0;
                            }
                            // 自定义类名
                            const controlClassName = this.renderDetailClass(item);
                            return (
                                <div style={detailStyle} class={controlClassName}>
                                    {this.renderByDetailType(item, false, container)}
                                </div>
                            );
                        })}
                    </div>
                </i-col>
            );
        } else {
            // 栅格布局
            let cssStyle = {
                width: container.width ? container.width + 'px' : '100%',
                height: container.height ? container.height + 'px' : ''
            }
            let attrs = this.getGridLayoutProps(null, container);
            return (
                <i-col {...{ props: attrs }} style={containerStyle} class={containerClass}>
                    { container.showCaption ? <row class="viewlayoutpanel-container-header">
                        <span>{this.$tl(container.getCapPSLanguageRes?.()?.lanResTag, container.caption)}</span>
                    </row> : null}
                    <row class="viewlayoutpanel-container-content" style={cssStyle}>
                        {panelItems.map((item: any, index: number) => {
                            let detailStyle: any = {};
                            let { height, width } = item;
                            detailStyle.height = height > 0 ? height + 'px' : '';
                            detailStyle.width = width > 0 ? width + 'px' : '';
                            // 栅格布局
                            let attrs = this.getGridLayoutProps(container, item);
                            // 自定义类名
                            const controlClassName = this.renderDetailClass(item);
                            return (
                                <i-col {...{ props: attrs }} style={detailStyle} class={controlClassName}>
                                    {this.renderByDetailType(item, false, container)}
                                </i-col>
                            );
                        })}
                    </row>
                </i-col>

            );
        }
    }

    /**
     * 绘制面板TabPanel
     *
     * @memberof AppDefaultViewLayout
     */
     public renderTabPanel(modelJson: IPSPanelTabPanel) {
        const tabPages: IPSPanelTabPage[] = modelJson.getPSPanelTabPages() || [];
        let activedTabPage: any = tabPages.length > 0 ? tabPages[0].name : '';
        return (
            <el-tabs
                value={activedTabPage}
                class={this.renderDetailClass(modelJson)}
            >
                {tabPages.length > 0 ?
                    tabPages.map((item: IPSPanelTabPage, index: number) => {
                        return this.renderTabPage(item);
                    }) : null}
            </el-tabs>
        );
    }

    /**
     * 绘制面板TabPage
     *
     * @memberof AppDefaultViewLayout
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
     * 绘制面板Field
     *
     * @memberof AppDefaultViewLayout
     */
     public renderField(modelJson: IPSSysPanelField) {
        let { name, caption, hidden, showCaption } = modelJson;
        const editor: any = modelJson.getPSEditor();
        let labelPos = 'LEFT';
        const viewFieldName = (modelJson as any).viewFieldName;
        return (
            !hidden && (
                <app-panel-field
                    name={viewFieldName}
                    labelPos={labelPos}
                    caption={this.$tl(modelJson.getCapPSLanguageRes()?.lanResTag, caption)}
                    isEmptyCaption={!showCaption}
                    data={this.context}
                    value={this.context[viewFieldName]}
                >
                    {editor && (
                        <app-default-editor
                            value={this.context[viewFieldName]}
                            editorInstance={editor}
                            containerCtrl={this.viewInstance}
                            parentItem={modelJson}
                            contextData={this.context}
                            context={this.context}
                            viewparams={this.viewparams}
                            disabled={false}
                        />
                    )}
                </app-panel-field>
            )
        );
    }

    /**
     * 绘制面板Rawitem
     *
     * @memberof AppDefaultViewLayout
     */
    public renderRawitem(modelJson: IPSPanelRawItem) {
        let { rawItemHeight, rawItemWidth, contentType, htmlContent, rawContent } = modelJson;
        let sysCssName = modelJson.getPSSysCss()?.cssName;
        let sysImage = modelJson.getPSSysImage()?.cssClass;
        const style: any = {
            width: rawItemWidth > 0 ? `${rawItemWidth}px` : false,
            height: rawItemHeight > 0 ? `${rawItemHeight}px` :false,
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
     * 绘制控件占位
     *
     * @memberof AppDefaultViewLayout
     */
    public renderCtrlPos(modelJson: any, parent?: any) {
        const { width, height, name } = modelJson;
        if (parent) {
            return this.$slots[`layout-${name}`];
        } else {
            let ctrlStyle = { width: width ? width + 'px' : '100%' };
            if (height) {
                Object.assign(ctrlStyle, { height: height + 'px' });
            }
            let ctrlCss = modelJson.getPSSysCss?.()?.cssName || '';
            return <div class={ctrlCss} style={ctrlStyle}>
                {this.$slots[`layout-${name}`]}
            </div>
        }
    }

    /**
     * FLEX布局时类名映射
     *
     * @memberof AppDefaultViewLayout
     */
    public classObj: any = {
        FIELD: 'app-viewlayoutpanel-field',
        RAWITEM: 'app-viewlayoutpanel-rowitem',
        TABPAGE: 'app-viewlayoutpanel-tabpage',
        TABPANEL: 'app-viewlayoutpanel-tabpanel',
    };


    /**
     * 获取FLEX布局时类名
     * @param AppDefaultViewLayout
     */
    public renderDetailClass(item: any) {
        // 映射类名
        let detailClass: any = this.classObj[item.itemType] || '';
        detailClass += ` viewlayoutpanel-${item.itemType.toLowerCase()}-${item.name.toLowerCase()}`;
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
     * @memberof AppDefaultViewLayout
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

}