import { IPSPanelContainer, IPSPanelItem, IPSPanelRawItem, IPSPanelTabPage, IPSPanelTabPanel, IPSSysCss, IPSSysPanelField, IPSViewLayoutPanel } from '@ibiz/dynamic-model-api';
import { Util, ThirdPartyService } from 'ibiz-core';
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
    @Prop() public viewInstance!: any;

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
     * 视图参数
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop({default: true}) public enableControlUIAuth!: any;

    /**
     * 模型服务对象
     * 
     * @memberof AppDefaultViewLayout
     */
    @Prop() public modelService: any;

    /**
     * 视图布局面板
     * 
     * @memberof AppDefaultViewLayout
     */
    public viewLayoutPanel?: IPSViewLayoutPanel | null;

    /**
     * Vue生命周期，实例创建完成
     *
     * @memberof AppDefaultViewLayout
     */
     created(){
        this.viewLayoutPanel = this.viewInstance.getPSViewLayoutPanel();
    }


    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewHeader() {
        return <ion-header>
            {!ThirdPartyService.getInstance().platform ? this.$slots.captionbar && !this.viewInstance.isPartsView ? this.$slots.captionbar : null:null}
            {this.$slots.toolbar}
            {this.$slots.quicksearch}
            {this.$slots.quickGroupSearch}
            {this.$slots.topMessage}
        </ion-header>
    }

    /**
     * 是否为部件视图
     * 
     * @memberof AppDefaultViewLayout
     */
    get isEmbedView(){
        return this.viewInstance.viewType.indexOf('VIEW9') != -1
    }

    /**
     * 绘制视图内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewContent() {
        return this.$slots.content;
    }

    /**
     * 绘制底部
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewFooter() {
        return <ion-footer class="view-footer">
            {this.$slots.mobbottommenu}
            {this.$slots.footer}
            {this.$slots.scrollTool}
            {this.$slots.bottomMessage}
        </ion-footer>
    }

    /**
     * 绘制内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderContent() {
        return [
            this.renderViewHeader(),
            this.renderViewContent(),
            this.renderViewFooter()
        ];
    }

    /**
     * 绘制布局
     * 
     * @memberof AppDefaultViewLayout
     */
    public render(h: any) {
        const sysCss = this.viewInstance.getPSSysCss();
        let viewClass = {
            'view-container': true,
            [this.viewInstance.viewType?.toLowerCase()]: true,
            [Util.srfFilePath2(this.viewInstance.codeName)]: true,
        };
        if (sysCss?.cssName) {
            Object.assign(viewClass, { [sysCss?.cssName]: true });
        }
        return (
            this.isEmbedView ? <app-embed-view className={viewClass}>
                <template slot="header">{this.renderViewHeader()}</template>
                <template slot="content">{this.$slots.default}</template>
                <template slot="footer">{this.renderViewFooter()}</template>
            </app-embed-view> :
                <ion-page className={viewClass}>
                     {(this.viewLayoutPanel && this.viewLayoutPanel.useDefaultLayout) ? this.renderContent() : this.renderViewLayoutPanel()}
                </ion-page>
        );
    }

    /**
     * 绘制视图布局面板
     *
     * @memberof AppDefaultViewLayout
     */
     public renderViewLayoutPanel() {
        if ((this.viewLayoutPanel as any)?.layoutBodyOnly) {
            return this.renderLayoutBodyOnly();
        } else {
            return this.renderRootPSPanelItems();
        }
        
    }
    
    /**
     * 绘制顶级面板成员集合
     *
     * @memberof AppDefaultViewLayout
     */
     public renderRootPSPanelItems() {
        return <van-row class="app-viewlayout-panel" style={{ 'height': '100%' }}>
            {this.viewLayoutPanel?.getRootPSPanelItems()?.map((container: any, index: number) => {
                return this.renderByDetailType(container);
            })}
        </van-row>
    }

     /**
     * 仅布局内容区模式绘制
     *
     * @memberof AppDefaultViewLayout
     */
    public renderLayoutBodyOnly() {
        let cardClass = {
            'view-card': true,
        };
        return [<ion-header>
            {!ThirdPartyService.getInstance().platform ? this.$slots.captionbar && !this.viewInstance.isPartsView ? this.$slots['layout-captionbar'] || this.$slots.captionbar : null:null}
            {this.$slots.toolbar}
            {this.$slots.quicksearch}
            {this.$slots.quickGroupSearch}
            {this.$slots.topMessage}
        </ion-header>,
        <ion-content ref="ionScroll" id={this.viewInstance.codeName} scroll-y={this.enableControlUIAuth as string}>
        {this.$slots.bodyMessage}
        {this.renderRootPSPanelItems()}
        </ion-content>,
        <ion-footer class="view-footer">
            {this.$slots.mobbottommenu}
            {this.$slots.footer}
            {this.$slots.scrollTool}
            {this.$slots.bottomMessage}
        </ion-footer>
        ];
    }


    /**
     * 根据detailType绘制对应detail
     *
     * @param {*} modelJson
     * @memberof AppDefaultViewLayout
     */
     public renderByDetailType(modelJson: any, parent?: any) {
        if (modelJson.getPSSysPFPlugin()) {
            const pluginInstance: any = PluginService.getInstance().getPluginInstance("CONTROLITEM", modelJson.getPSSysPFPlugin().pluginCode);
            if (pluginInstance) {
                return pluginInstance.renderCtrlItem(this.$createElement, modelJson, this, this.context);
            }
        }
        switch (modelJson.itemType) {
            case 'CONTAINER':
                return this.renderContainer(modelJson);
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
        let containerClass = {
            'app-viewlayoutpanel-container': true,
            [`viewlayoutpanel-container-${container.name.toLowerCase()}`]: true,
            'show-caption': container.showCaption
        };
        if (isRootContainer && css && css.cssName) {
            Object.assign(containerClass, { [css.cssName]: true });
        }
        let containerStyle = {
            width: container.width ? container.width + 'px' : '',
            height: container.height ? container.height + 'px' : '',
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
                <van-col style={containerStyle} class={containerClass}>
                    {container.showCaption ? <div class="viewlayoutpanel-container-header">
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
                                return this.renderByDetailType(item, container);
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
                                {this.renderByDetailType(item, container)}
                            </div>
                        );
                    })}
                </van-col>
            );
        } else {
            // 栅格布局
            let attrs = this.getGridLayoutProps(null, container);
            return (
                <van-col {...{ props: attrs }} style={containerStyle} class={containerClass}>
                    { container.showCaption ? <van-row class="viewlayoutpanel-container-header">
                        <span>{this.$tl(container.getCapPSLanguageRes?.()?.lanResTag, container.caption)}</span>
                    </van-row> : null}
                    {panelItems.map((item: any, index: number) => {
                        let { height, width, itemType } = item;
                        let detailStyle: any = {};
                        if (height) {
                            detailStyle.height = height + 'px';
                        }
                        switch (itemType) {
                            case 'CONTAINER':
                                return this.renderByDetailType(item, container);
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
                                {this.renderByDetailType(item, container)}
                            </van-col>
                        );
                    })}
                </van-col>

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
            <van-tabs
                type="card"
                color="var(--app-background-color)"
                animated={true}
                swipeable={true}
                scrollspy={true}
                value={activedTabPage}
                class={this.renderDetailClass(modelJson)}
            >
                {tabPages.length > 0 ?
                    tabPages.map((item: IPSPanelTabPage, index: number) => {
                        return this.renderTabPage(item);
                    }) : null}
            </van-tabs>
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
            <van-tab title={label} name={modelJson.name} class={this.renderDetailClass(modelJson)}>
                {panelItems.map((item: IPSPanelItem, index: number) => {
                    return this.renderByDetailType(item)
                })}
            </van-tab>
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
        let labelClass = {
            'item-field-label': true,
            [modelJson.getPSSysCss()?.cssName || '']: true
        }
        const viewFieldName = (modelJson as any).viewFieldName;
        return (
            !hidden && (
                <div class="item-field ">
                    {caption ? <ion-label class={labelClass} >{caption}</ion-label> : null}
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
                </div>
            )
        );
    }

    /**
     * 绘制面板Rawitem
     *
     * @memberof AppDefaultViewLayout
     */
    public renderRawitem(modelJson: IPSPanelRawItem) {
        let {
            contentType,
            htmlContent,
            getPSSysImage,
            rawContent,
        } = modelJson;
        if (rawContent) {
            // const items = rawContent.match(/\{{(.+?)\}}/g);
            // if (items) {
            //     items.forEach((item: string) => {
            //         rawContent = this.$t(rawContent.replace(/\{{(.+?)\}}/, item.substring(2, item.length - 2)));
            //     }) as any;
            // }
        }
        const tempNode = this.$createElement('div', {
            domProps: {
                innerHTML: rawContent,
            },
        });
        switch (contentType) {
            case 'HTML':
                return htmlContent
            case 'RAW':
                return tempNode
            case 'IMAGE':
                return <img src={getPSSysImage()?.imagePath} ></img>
        }
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
            span: colMD,
            offset: colMDOffset,
        };
    }

}