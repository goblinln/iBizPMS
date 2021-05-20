
import { Emit, Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobPortletControlBase } from '../../../widgets';
import { AppViewLogicService } from '../../../app-service';
import { IPSDBAppViewPortletPart, IPSDBCustomPortletPart, IPSDBHtmlPortletPart, IPSDBRawItemPortletPart, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';

/**
 * 门户部件部件基类
 *
 * @export
 * @class AppMobPortletBase
 * @extends {MobPortletControlBase}
 */
export class AppMobPortletBase extends MobPortletControlBase {

    /**
     * 部件动态参数
     *
     * @memberof AppMobPortletBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppMobPortletBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobPortletBase
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
     * @memberof AppMobPortletBase
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
     * @memberof AppMobPortletBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobPortletBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制其他部件
     *
     * @returns
     * @memberof AppMobPortletBase
     */
    public renderControl() {
        if (this.controlInstance.getPSControls()?.length) {
            let control = this.controlInstance.getPSControls()?.[0];
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(control);
            return this.$createElement(targetCtrlName,{ props: targetCtrlParam, ref: control?.name, on: targetCtrlEvent })
        }
    }

    /**
     * 绘制直接内容
     *
     * @returns
     * @memberof AppMobPortletBase
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
     * @memberof AppMobPortletBase
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
     * @memberof AppMobPortletBase
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
     * @memberof AppMobPortletBase
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
     * @memberof AppMobPortletBase
     */
    public renderCustom() {
        let plugin  = (this.controlInstance as IPSDBCustomPortletPart)?.getPSSysPFPlugin?.();
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
     * @memberof AppMobPortletBase
     */
    public renderAppMenu() {
        let menuInstance = this.controlInstance.getPSControls()?.[0];
        if (menuInstance) {
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(menuInstance);
            Object.assign(targetCtrlParam.staticProps, { controlStyle: 'ICONVIEW' })
            return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: menuInstance.name, on: targetCtrlEvent });
        }
    }

    /**
     * 绘制视图
     *
     * @returns
     * @memberof AppMobPortletBase
     */
    public renderView() {
        let portletAppView = (this.controlInstance as IPSDBAppViewPortletPart)?.getPortletPSAppView();
        if (!portletAppView) {
            return;
        }
        const {modelFilePath, name} = portletAppView;
        Object.assign(this.context,{viewpath:modelFilePath})
        return this.$createElement('app-view-shell', {
            props: {
                staticProps: {
                    panelState: this.viewState,
                    viewDefaultUsage: 'includedView',
                    viewModelData: portletAppView,
                    isChildView:true
                },
                dynamicProps: {
                    _context: JSON.stringify(this.context),
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
     * @memberof AppMobPortletBase
     */
    public renderByPortletType() {
        switch (this.controlInstance.portletType) {
            case 'VIEW':
                return <div class="portlet-view-container">{this.renderView()}</div>;
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
     * @memberof AppMobPortletBase
     */
    public renderTitle() {
        return this.editTitle() ? <ion-list-header class='app-mob-portlet__header'>
            {this.isEditTitle ? <ion-input value={this.editTitle()} on-ionChange={this.titleChange}></ion-input> : null}
            {!this.isEditTitle ? <span >{this.editTitle()}</span> : null}
            {this.actionBarModelData && this.actionBarModelData.length > 0 ? <div class="portlet__header_right">
                <app-mob-icon name="ellipsis-horizontal-outline" on-onClick={()=>{this.selectStatus = true}}></app-mob-icon>
            </div> : null}
        </ion-list-header> : null


    }


    /**
     * 绘制界面行为图标
     *
     * @param {*} actionDetail 界面行为组成员
     * @returns
     * @memberof AppMobPortletBase
     */
    public renderIcon(actionDetail: IPSUIActionGroupDetail) {
        const { showIcon } = actionDetail;
        const uiAction = actionDetail.getPSUIAction();
        if (showIcon && uiAction?.getPSSysImage()) {
            let image = uiAction.getPSSysImage();
            if (image?.cssClass) {
                return <i class={image?.cssClass} />
            } else {
                return <img src={image?.imagePath} />
            }
        }

    }

    /**
     * 绘制界面行为组
     *
     * @returns
     * @memberof AppMobPortletBase
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
                let contentElement = <a on-click={(e: any) => { this.handleActionClick(e, actionDetail) }} >
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
     * @memberof AppMobPortletBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (
            <ion-card>
                <ion-row>
                    <ion-list class={{ ...controlClassNames, 'app-mob-portlet': true }}>
                        {this.renderTitle()}
                        {this.renderByPortletType()}
                    </ion-list>
                    <van-action-sheet v-model={this.selectStatus} get-container="#app" actions={this.actionBarModelData} cancel-text="取消" close-on-click-action on-select={($event:any)=>{this.handleItemClick($event,null)}} on-cancel={()=>{this.selectStatus = false}} />
                </ion-row>
            </ion-card>
        )
    }
}
