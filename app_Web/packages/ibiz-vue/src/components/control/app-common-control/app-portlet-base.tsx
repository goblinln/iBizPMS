
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { PortletControlBase } from '../../../widgets';
import { AppViewLogicService } from '../../../app-service';
import { IPSDBAppViewPortletPart, IPSDBCustomPortletPart, IPSDBHtmlPortletPart, IPSDBRawItemPortletPart, IPSDBSysPortletPart, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';

/**
 * 门户部件部件基类
 *
 * @export
 * @class AppPortletBase
 * @extends {PortletControlBase}
 */
export class AppPortletBase extends PortletControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppPortletBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppPortletBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPortletBase
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
     * @memberof AppPortletBase
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
     * @memberof AppPortletBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppPortletBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制其他部件
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderControl() {
        if (this.controlInstance.getPSControls()?.length) {
            // 绘制其他部件
            let control = this.controlInstance.getPSControls()?.[0];
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(control);
            return this.$createElement(targetCtrlName,{ props: targetCtrlParam, ref: control.name, on: targetCtrlEvent })
        }
    }

    /**
     * 绘制直接内容
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderRawItem() {
        const { rawItemHeight, rawItemWidth, contentType, rawContent, htmlContent } = this.controlInstance as IPSDBRawItemPortletPart;
        let sysCss = this.controlInstance.getPSSysCss();
        let sysImage = this.controlInstance.getPSSysImage();
        let rawStyle = (rawItemHeight > 0 ? `height:${rawItemHeight}px` : '') + (rawItemWidth > 0 ? `width:${rawItemWidth}px` : '');
        let newRawContent = rawContent;
        if (newRawContent) {
            const items = newRawContent.match(/\{{(.+?)\}}/g);
            if (items) {
                items.forEach((item: string) => {
                    newRawContent = newRawContent.replace(/\{{(.+?)\}}/, eval(item.substring(2, item.length - 2)));
                });
            }
        }
        const tempNode = this.$createElement('div', {
            domProps: {
                innerHTML: newRawContent,
            },
        });
        return (
            <app-rawitem viewparams={this.viewparams} context={this.context} contentStyle={sysCss?.cssName} sizeStyle={rawStyle} contentType={contentType} htmlContent={htmlContent} imageClass={sysImage?.cssClass}>
                {contentType == 'RAW' && tempNode}
            </app-rawitem>
        );
    }

    /**
     * 绘制HTML
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderHtml() {
        let { pageUrl, height } = this.controlInstance as IPSDBHtmlPortletPart;
        height = height > 0 ? height : 400;
        let iframeStyle = `height: ${height}px; width: 100%; border-width: 0px;`;
        return <iframe src={pageUrl} style={iframeStyle}></iframe>;
    }

    /**
     * 绘制工具栏栏
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderToolBar() {
        return (
            <div class='toolbar-container'>
                <view-toolbar
                    toolbarModels={this.toolbarModels}
                    on-item-click={(data: any, $event: any) => {
                        this.handleItemClick(data, $event);
                    }}
                ></view-toolbar>
            </div>
        );
    }

    /**
     * 绘制操作栏
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderActionBar() {
        return <app-actionbar
            viewState={this.viewState}
            uiService={this.appUIService}
            items={this.actionBarModelData}
            on-itemClick={this.handleItemClick.bind(this)}
        ></app-actionbar>;
    }

    /**
     * 绘制自定义
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderCustom() {
        let plugin  = (this.controlInstance as IPSDBCustomPortletPart)?.getPSSysPFPlugin?.();;
        // todo自定义绘制
        if (plugin) {
            // todo 自定义门户部件
        } else {
            return <div>{this.$t('app.portlet.noExtensions')}</div>;
        }
    }

    /**
     * 绘制应用菜单
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderAppMenu() {
        let menuInstance = this.controlInstance.getPSControls()?.[0];
        if (menuInstance) {
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(menuInstance);
            return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: menuInstance.name, on: targetCtrlEvent });
        }
    }

    /**
     * 绘制视图
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderView() {
        let portletAppView = (this.controlInstance as IPSDBAppViewPortletPart)?.getPortletPSAppView();
        if (!portletAppView) {
            return;
        }
        const { modelFilePath, name } = portletAppView;
        let tempContext: any = Object.assign(this.context, { viewpath: modelFilePath });
        return this.$createElement('app-view-shell', {
            props: {
                staticProps: {
                    portletState: this.viewState,
                    viewDefaultUsage: false,
                    viewModelData: portletAppView
                },
                dynamicProps: {
                    viewdata: JSON.stringify(tempContext),
                    viewparam: JSON.stringify(this.viewparams),
                },
            },
            on:{
                'viewIsMounted': ()=>{
                    this.setIsMounted(portletAppView?.name);
                }
            },
            ref: name,
        });
    }

    /**
     * 根据portletType绘制
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderByPortletType() {
        switch (this.controlInstance.portletType) {
            case 'VIEW':
                return this.renderView();
            case 'APPMENU':
                return this.renderAppMenu();
            case 'CUSTOM':
                return this.renderCustom();
            case 'ACTIONBAR':
                return this.renderActionBar();
            case 'TOOLBAR':
                return this.renderToolBar();
            case 'HTML':
                return this.renderHtml();
            case 'RAWITEM':
                return this.renderRawItem();
            default:
                return this.renderControl();
        }
    }

    /**
     * 绘制标题
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderTitle() {
        const {  portletType } = this.controlInstance;
        let image = this.controlInstance?.getPSSysImage?.();
        return [
            <p class='portlet-title'>
                <span>
                    {image && <i class={image?.cssClass} />}
                    {this.controlInstance.title}
                </span>
                {portletType != 'ACTIONBAR' && this.renderUiAction()}
            </p>,
            <el-divider></el-divider>,
        ];
    }


    /**
     * 绘制界面行为图标
     *
     * @param {*} actionDetail 界面行为组成员
     * @returns
     * @memberof AppPortletBase
     */
    public renderIcon(actionDetail: any) {
        const { getPSUIAction: uiAction, showIcon } = actionDetail;
        if (showIcon && uiAction?.getPSSysImage) {
            let image = uiAction.getPSSysImage;
            if (image.cssClass) {
                return <i class={image.cssClass} />
            } else {
                return <img src={image.imagePath} />
            }
        }

    }

    /**
     * 绘制界面行为组
     *
     * @returns
     * @memberof AppPortletBase
     */
    public renderUiAction() {
        const actionGroupDetails = this.controlInstance?.getPSUIActionGroup?.()?.getPSUIActionGroupDetails?.();
        if (!actionGroupDetails) {
            return
        }
        return <span class="portlet-action">
            {actionGroupDetails.map((actionDetail: IPSUIActionGroupDetail) => {
                const { showCaption } = actionDetail;
                let uiAction = actionDetail?.getPSUIAction?.();
                let uiactionName = uiAction?.codeName?.toLowerCase() || actionDetail.name?.toLowerCase();
                // 显示内容
                // todo 界面行为显示this.actionModel?.[uiactionName]?.visabled 
                let contentElement = <a on-click={(e: any) => { this.handleActionClick(e, actionDetail) }} v-show={true} >
                    {this.renderIcon(actionDetail)}
                    {showCaption && (uiAction?.caption || uiAction?.name)}
                </a>
                if (showCaption) {
                    return <tooltip transfer={true} max-width={600}>
                        {contentElement}
                        <div slot='content'>{uiAction?.caption || uiAction?.name}</div>
                    </tooltip>
                } else {
                    return contentElement
                }
            })}
        </span>
    }

    /**
     * 处理操作列点击
     * 
     * @memberof GridControlBase
     */
    public handleActionClick(event: any, detail: any) {
        const { name } = this.controlInstance;
        let data = Util.deepCopy(this.context);
        AppViewLogicService.getInstance().executeViewLogic(`${name}_${detail.name}_click`, event, this, data, this.controlInstance?.getPSAppViewLogics());
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppPortletBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const { showTitleBar, title, height, width } = this.controlInstance;
        let isShowTitle = showTitleBar && title;
        let portletStyle;
        if (this.isAdaptiveSize) {
            portletStyle = 'height:100%; width:100%';
        } else {
            portletStyle = !Util.isEmpty(height) && height > 0 ? `height: ${height}px;` : 'auto';
            portletStyle += width ? `width: ${width}px;` : '';
        }
        return (!this.isAdaptiveSize
            ? <card class={'portlet-card custom-card'} bordered={false} dis-hover padding={0}>
                <div class={{ 'portlet': true, ...controlClassNames }}>
                    {isShowTitle && this.renderTitle()}
                    <div class={{ 'portlet-with-title': isShowTitle, 'portlet-without-title': !isShowTitle }} style={portletStyle}>{this.renderByPortletType()}</div>
                </div>
            </card>
            : <div class={{ 'portlet': true, ...controlClassNames }} style={portletStyle}>
                {isShowTitle && this.renderTitle()}
                <div class={{ 'portlet-with-title': isShowTitle, 'portlet-without-title': !isShowTitle }}>{this.renderByPortletType()}</div>
            </div>
        )
    }
}
