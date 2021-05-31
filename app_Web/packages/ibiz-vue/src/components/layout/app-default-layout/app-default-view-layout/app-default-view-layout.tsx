import { IPSAppView } from '@ibiz/dynamic-model-api';
import { ModelTool, Util } from 'ibiz-core';
import Vue from 'vue';
import { Prop, Component } from 'vue-property-decorator';
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
     * 是否展示视图工具栏
     * 
     * @memberof AppDefaultViewLayout
     */
    public viewIsshowToolbar: boolean = ModelTool.findPSControlByType("TOOLBAR", this.viewInstance.getPSControls()) ? true : false;

    /**
     * 是否显示标题栏
     *
     * @readonly
     * @memberof AppDefaultViewLayout
     */
    get showCaption(){
        if(this.viewInstance && this.$parent){
            return this.viewInstance.showCaptionBar && !(this.$parent as any).noViewCaption
        }else{
            return true;
        }
    }

    /**
     * 绘制头部内容
     * 
     * @memberof AppDefaultViewLayout
     */
    public renderViewHeader(): any {
        return [
            this.showCaption ? <span class='caption-info'>{this.$slots.captionInfo ? this.$slots.captionInfo : this.viewInstance.caption}</span> : null,
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
                    { (this.$slots.quickGroupSearch || this.$slots.quickSearch) && <div style="margin-bottom: 6px;">
                        {this.$slots.quickGroupSearch}
                        {this.$slots.quickSearchForm}
                        {this.$slots.quickSearch}
                    </div> }
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
                    viewTitle={this.viewInstance.title} />
                { this.renderContent()}
            </div>
        );
    }

}