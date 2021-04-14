import { Component } from 'vue-property-decorator';
import { AppStyle2DefaultLayout } from '../app-style2-default-layout/app-style2-default-layout';
import "./app-style2-wfdynaactionview-layout.less";

@Component({})
export class AppStyle2WFDynaActionViewLayout extends AppStyle2DefaultLayout {

    /**
     * 绘制视图
     * 
     * @memberof AppStyle2WFDynaActionViewLayout
     */
    public render(h: any): any {
        const { codeName, title } = this.viewInstance;
        const viewClassNames = this.initRenderClassNames();
        const styleMode: any = this.$uiState.layoutState.styleMode;
        if (Object.is('DEFAULT', styleMode)) {
            return (
                <studio-view
                    viewName={codeName?.toLowerCase()}
                    viewTitle={title}
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
                        {this.$slots.button}
                    </template>
                </studio-view>
            );
        } else {
            return (
                <studio-view-style2
                    viewName={codeName?.toLowerCase()}
                    viewTitle={title}
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
                        {this.$slots.button}
                    </template>
                </studio-view-style2>
            );
        }
    }
}