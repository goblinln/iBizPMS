import { Vue, Component, Prop } from 'vue-property-decorator';
import { AppServiceBase, Util } from 'ibiz-core';
import "./app-style2-default-layout.less";

@Component({})
export class AppStyle2DefaultLayout extends Vue {
    
    /**
     * 视图实例对象
     * 
     * @protected
     * @type {*}
     * @memberof AppStyle2DefaultLayout
     */
    @Prop() protected viewInstance!: any;

    /**
     * 应用上下文
     * 
     * @public
     * @type {*}
     * @memberof AppStyle2DefaultLayout
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     * 
     * @public
     * @type {*}
     * @memberof AppStyle2DefaultLayout
     */
    @Prop() public viewparams!: any;

    /**
     * 模型服务对象
     * 
     * @memberof AppStyle2DefaultLayout
     */
    @Prop() public modelService!:any;

    /**
     * 视图模型数据
     * 
     * @memberof AppDefaultViewLayout
     */
     @Prop() public model!: any;

    /**
     * 当前字体
     *
     * @memberof AppStyle2DefaultLayout
     */
    get selectFont() {
        const appStore: any = AppServiceBase.getInstance().getAppStore();
        if (appStore && appStore.state) {
            return appStore.state.selectFont;
        } else if (localStorage.getItem('font-family')) {
            return localStorage.getItem('font-family');
        } else {
            return 'Microsoft YaHei';
        }
    }

    /**
     * 初始化类名
     * 
     * @memberof AppStyle2DefaultLayout
     */
    public initRenderClassNames(otherClassNames?: any) {
        let classNames: any = {
            "view-style2": true,
            [this.viewInstance.viewType?.toLowerCase()]: true,
            [Util.srfFilePath2(this.viewInstance?.codeName)]: true,
        };
        if(this.viewInstance.getPSSysCss() ){
            Object.assign(classNames, {
                [this.viewInstance.getPSSysCss()?.cssName || '']: true,
            });
        }
        if(otherClassNames) {
            Object.assign(classNames, {...otherClassNames});
        }
        return classNames;
    }

    /**
     * 是否显示标题栏
     *
     * @readonly
     * @memberof AppDefaultViewLayout
     */
    get showCaption(){
        if(this.viewInstance && this.$parent && Util.isExist(this.viewInstance.showCaptionBar)){
            return this.viewInstance.showCaptionBar && !(this.$parent as any).noViewCaption
        }else{
            return true;
        }
    }

    /**
     * 绘制视图标题
     *  
     * @memberof AppStyle2DefaultLayout
     */
    public renderViewCaption() {
        const { viewSysImage } = this.viewInstance;
        if(this.showCaption) {
            return (
                <div slot="title">
                    {viewSysImage?.cssClass && viewSysImage.cssClass != '' ? 
                        <span class="caption-image">
                            <i class={viewSysImage.cssClass}></i>
                        </span> : 
                        viewSysImage?.imagePath && viewSysImage.imagePath != '' ? 
                            <span class="caption-image">
                                <img src={viewSysImage.imagePath}></img>
                            </span> : null}
                    <span class="caption-info">{this.$slots.captionInfo?this.$slots.captionInfo:this.model.srfCaption}</span>
                </div>
            );
        }
    }

    /**
     * 绘制视图
     * 
     * @memberof AppStyle2DefaultLayout
     */
    public render(h: any): any {
        const { codeName } = this.viewInstance;
        const viewClassNames = this.initRenderClassNames();
        const styleMode: any = this.$uiState.layoutState.styleMode;
        if (Object.is('DEFAULT', styleMode)) {
            return (
                <studio-view
                    style={{ 'font-family': this.selectFont }}
                    viewName={codeName?.toLowerCase()}
                    viewTitle={this.model.srfCaption}
                    viewInstance={this.viewInstance}
                    viewparams={this.viewparams}
                    context={this.context}
                    class={viewClassNames}>
                        {this.renderViewCaption()}
                    <template slot="quickSearch">
                        {this.$slots.quickSearch}
                    </template>
                    <template slot="topMessage">
                        {this.$slots.topMessage}
                    </template>
                    <template slot="quickSearchForm">
                        {this.$slots.quickSearchForm}
                    </template>
                    <template slot="quickGroupTab">
                        {this.$slots.quickGroupTab}
                    </template>
                    <template slot="quickGroupSearch">
                        {this.$slots.quickGroupSearch}
                    </template>
                    <template slot="dataPanel">
                        {this.$slots.datapanel}
                    </template>
                    <template slot="toolbar">
                        {this.$slots.toolbar}
                    </template>
                    <template slot="searchForm">
                        {this.$slots.searchForm}
                    </template>
                    <template slot="searchBar">
                        {this.$slots.searchBar}
                    </template>
                    <template slot="bodyMessage">
                        {this.$slots.bodyMessage}
                    </template>
                    {this.$slots.default}
                    <template slot="bottomMessage">
                        {this.$slots.bottomMessage}
                    </template>
                    <template slot="footer">
                        {this.$slots.footer}
                    </template>
                </studio-view>
            );
        } else {
            return (
                <studio-view-style2
                    style={{ 'font-family': this.selectFont }}
                    viewName={codeName?.toLowerCase()}
                    viewTitle={this.model.srfCaption}
                    viewInstance={this.viewInstance}
                    viewparams={this.viewparams}
                    context={this.context}
                    class={viewClassNames}>
                        {this.renderViewCaption()}
                    <template slot="quickSearch">
                        {this.$slots.quickSearch}
                    </template>
                    <template slot="topMessage">
                        {this.$slots.topMessage}
                    </template>
                    <template slot="quickSearchForm">
                        {this.$slots.quickSearchForm}
                    </template>
                    <template slot="quickGroupTab">
                        {this.$slots.quickGroupTab}
                    </template>
                    <template slot="quickGroupSearch">
                        {this.$slots.quickGroupSearch}
                    </template>
                    <template slot="dataPanel">
                        {this.$slots.datapanel}
                    </template>
                    <template slot="toolbar">
                        {this.$slots.toolbar}
                    </template>
                    <template slot="searchForm">
                        {this.$slots.searchForm}
                    </template>
                    <template slot="searchBar">
                        {this.$slots.searchBar}
                    </template>
                    <template slot="bodyMessage">
                        {this.$slots.bodyMessage}
                    </template>
                    {this.$slots.default}
                    <template slot="bottomMessage">
                        {this.$slots.bottomMessage}
                    </template>
                    <template slot="footer">
                        {this.$slots.footer}
                    </template>
                </studio-view-style2>
            );
        }
    }
}