import { Component } from 'vue-property-decorator';
import { AppStyle2DefaultLayout } from '../app-style2-default-layout/app-style2-default-layout';
import './app-style2-mpickupview-layout.less';

@Component({})
export class AppStyle2MPickupViewLayout extends AppStyle2DefaultLayout {


    /**
     * 绘制视图
     * 
     * @memberof AppStyle2MPickupViewLayout
     */
    public render() {
      
      const { codeName, title } = this.viewInstance;
      const viewClassNames = this.initRenderClassNames();
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
                      {this.$slots.dataPanel}
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
                  <div class="content-container pickup-view">
                  {this.$slots.default}
                  </div>
                  <template slot="bottomMessage">
                      {this.$slots.bottomMessage}
                  </template> 
                  <template slot="footer">
                      {this.$slots.footer}
                  </template>
          </studio-view>
      );
  }

}