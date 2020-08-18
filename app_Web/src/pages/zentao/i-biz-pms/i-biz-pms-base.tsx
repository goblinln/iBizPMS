import { Vue } from 'vue-property-decorator';
import { FooterItemsService } from '@/studio-core/service/FooterItemsService';
import { AppService } from '@/studio-core/service/app-service/AppService';
import AppMenusModel from '@/widgets/app/zentao-appmenu/zentao-appmenu-model';
import { Environment } from '@/environments/environment';

/**
 * 应用首页基类
 */
export class IBizPMSBase extends Vue {
  /**
   * 计数器服务对象集合
   *
   * @type {any[]}
   * @memberof IBizPMSBase
   */
  protected counterServiceArray: any[] = [];

  /**
   * 应用服务
   *
   * @protected
   * @type {AppService}
   * @memberof IBizPMSBase
   */
  protected appService: AppService = new AppService();

  /**
   * 应用菜单集合
   *
   * @type {AppMenusModel}
   * @memberof IBizPMSBase
   */
  protected appMenuModel: AppMenusModel = new AppMenusModel();

  /**
   * 左侧导航菜单
   *
   * @type {*}
   * @memberof IBizPMSBase
   */
  protected left_exp: any = this.appMenuModel.getMenuGroup('left_exp') || {};

  /**
   * 底部导航菜单
   *
   * @type {*}
   * @memberof IBizPMSBase
   */
  protected bottom_exp: any = this.appMenuModel.getMenuGroup('bottom_exp') || {};
 
  /**
   * 标题栏菜单
   *
   * @type {*}
   * @memberof IBizPMSBase
   */
  protected top_menus: any = this.appMenuModel.getMenuGroup('top_menus') || {};
 
  /**
   * 用户菜单
   *
   * @type {*}
   * @memberof IBizPMSBase
   */
  protected user_menus: any = this.appMenuModel.getMenuGroup('user_menus') || {};

  /**
   * 底部项绘制服务
   *
   * @type {FooterItemsService}
   * @memberof IBizPMSBase
   */
  protected footerItemsService: FooterItemsService = new FooterItemsService();

  /**
   * 视图标识
   *
   * @type {string}
   * @memberof IBizPMSBase
   */
  protected viewtag: string = '1ed8a475f4c185fc99c487900bbcf69e';

  /**
   * 视图模型数据
   *
   * @type {*}
   * @memberof IBizPMSBase
   */
  protected model: any = {
      srfCaption: 'app.views.ibizpms.caption',
      srfTitle: 'app.views.ibizpms.title',
      srfSubTitle: 'app.views.ibizpms.subtitle',
      dataInfo: ''
  }

  /**
   * 应用上下文
   *
   * @type {*}
   * @memberof IBizPMSBase
   */
  protected context: any = {};

  /**
   * 视图参数
   *
   * @type {*}
   * @memberof IBizPMSBase
   */
  protected viewparams: any = {};

  /**
   * 注册底部项
   *
   * @memberof IBizPMSBase
   */
  protected registerFooterItems(): void {
    const leftItems: any = this.appMenuModel.getMenuGroup('footer_left');
    const centerItems: any = this.appMenuModel.getMenuGroup('footer_center');
    const rightItems: any = this.appMenuModel.getMenuGroup('footer_right');
    if (leftItems && leftItems.items) {
      leftItems.items.forEach((item: any) => {
        this.footerItemsService.registerLeftItem((h: any) => {
          return <div class='action-item' title={item.tooltip} on-click={() => this.click(item)}>
            <menu-icon item={item}/>
            {item.text}
          </div>;
        });
      });
    }
    if (centerItems && centerItems.items) {
      centerItems.items.forEach((item: any) => {
        this.footerItemsService.registerCenterItem((h: any) => {
          return <div class='action-item' title={item.tooltip} on-click={() => this.click(item)}>
            <menu-icon item={item}/>
            {item.text}
          </div>;
        });
      });
    }
    if (rightItems && rightItems.items) {
      rightItems.items.forEach((item: any) => {
        this.footerItemsService.registerRightItem((h: any) => {
          return <div class='action-item' title={item.tooltip} on-click={() => this.click(item)}>
            <menu-icon item={item}/>
            {item.text}
          </div>;
        });
      });
    }
  }

  /**
   * 项点击触发界面行为
   *
   * @protected
   * @param {*} item
   * @memberof IBizPMSBase
   */
  protected click(item: any): void {
    const appMenu: any = this.$refs.appmenu;
    if (appMenu) {
      appMenu.click(item);
    }
  }

  /**
   * 组件创建完毕
   *
   * @memberof IBizPMSBase
   */
  protected created() {
    this.left_exp = this.handleMenusResource(this.left_exp);
    this.bottom_exp = this.handleMenusResource(this.bottom_exp);
    this.top_menus = this.handleMenusResource(this.top_menus);
    this.user_menus = this.handleMenusResource(this.user_menus);
    const secondtag = this.$util.createUUID();
    this.$store.commit("viewaction/createdView", {
      viewtag: this.viewtag,
      secondtag: secondtag
    });
    this.viewtag = secondtag;
    this.parseViewParam();
    this.$uiState.changeLayoutState({
      styleMode: 'DEFAULT'
    });
    this.registerFooterItems();
  }

  /**
   * 销毁之前
   *
   * @memberof IBizPMSBase
   */
  protected beforeDestroy() {
    this.$store.commit("viewaction/removeView", this.viewtag);
  }

  /**
   * Vue声明周期(组件初始化完毕)
   *
   * @memberof IBizPMSBase
   */
  protected mounted() {
    this.$viewTool.setIndexParameters([
      { pathName: 'ibizpms', parameterName: 'ibizpms' }
    ]);
    this.$viewTool.setIndexViewParam(this.context);
    setTimeout(() => {
      const el = document.getElementById('app-loading-x');
      if (el) {
        el.style.display = 'none';
      }
    }, 300);
  }

  /**
   * 解析视图参数
   *
   * @private
   * @memberof IBizPMSBase
   */
  private parseViewParam(): void {
    const params = this.$route.params;
    if (params?.ibizpms) {
      this.context.ibizpms = params.ibizpms;
    }
    const context = this.$appService.contextStore.appContext;
    if (context) {
      Object.assign(this.context, context);
      context.clearAll();
      Object.assign(context, this.context);
    }
  }

  /**
   * 通过统一资源标识计算菜单
   *
   * @param {*} data
   * @memberof ZentaoBase
   */
  public handleMenusResource(inputMenus: Array<any>) {
    if (Environment.enablePermissionValid) {
      this.computedEffectiveMenus(inputMenus);
      this.computeParentMenus(inputMenus);
    }
    return inputMenus;
  }

  /**
   * 计算父项菜单项是否隐藏
   *
   * @param {*} inputMenus
   * @memberof ZentaoBase
   */
  public computeParentMenus(inputMenus: Array<any>) {
    if (inputMenus && inputMenus.length > 0) {
      inputMenus.forEach((item: any) => {
        if (item.hidden && item.items && item.items.length > 0) {
          item.items.map((singleItem: any) => {
            if (!singleItem.hidden) {
              item.hidden = false;
            }
            if (singleItem.items && singleItem.items.length > 0) {
              this.computeParentMenus(singleItem.items);
            }
          })
        }
      })
    }
  }

  /**
   * 计算有效菜单项
   *
   * @param {*} inputMenus
   * @memberof ZentaoBase
   */
  public computedEffectiveMenus(inputMenus: Array<any>) {
    inputMenus.forEach((_item: any) => {
      if (!this.$store.getters['authresource/getAuthMenu'](_item)) {
        _item.hidden = true;
        if (_item.items && _item.items.length > 0) {
          this.computedEffectiveMenus(_item.items);
        }
      }
    })
  }


  /**
   * 绘制内容
   */
  public render(): any {
    const styleMode = this.$uiState.layoutState.styleMode;
    let leftContent: any;
    switch (styleMode) {
      case 'DEFAULT':
        leftContent = <app-content-left-exp ref="leftExp" menus={this.left_exp.items} />;
        break;
      case 'STYLE2':
        leftContent = <app-content-left-nav-menu ref="leftNavMenu" menus={this.left_exp.items} on-menu-click={(item: any) => this.click(item)}/>;
    }
    return (
      <app-layout ref="appLayout">
        <template slot="header">
          <app-header>
            <template slot="header_left">
              <div class="title">
                iBiz软件生产管理
              </div>
            </template>
            <template slot="header_right">
              <app-header-menus ref="headerMenus" menus={this.top_menus.items} on-menu-click={(item: any) => this.click(item)}/>
              <user-info ref="userInfo" menus={this.user_menus.items} on-menu-click={(item: any) => this.click(item)}/>
            </template>
          </app-header>
          <view_appmenu ref='appmenu'/>
        </template>
        <app-content>
          {this.left_exp.items ? <template slot="content_left">
            {leftContent}
          </template> : null}
          {styleMode === 'DEFAULT' ? <tab-page-exp ref="tabExp"></tab-page-exp> : null}
          <div class="view-warp">
            <app-keep-alive routerList={this.appService.navHistory.historyList}>
              <router-view key={this.$route.fullPath}></router-view>
            </app-keep-alive>
          </div>
          {this.bottom_exp.items ? <template slot="content_bottom">
            <app-content-bottom-exp ref="bootomExp" menus={this.bottom_exp.items} />
          </template> : null}
        </app-content>
        <template slot="footer">
          <app-footer ref="footer"/>
        </template>
      </app-layout>
    );
  }
}