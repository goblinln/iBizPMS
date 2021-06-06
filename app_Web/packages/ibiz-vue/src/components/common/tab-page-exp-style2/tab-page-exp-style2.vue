<template>
  <div class="ibiz-page-tag" v-if="navHistory.historyList.length > 0">
    <div class="move-btn move-left" @click="leftMove">
      <icon type="ios-arrow-back" />
    </div>
    <div ref="scrollBody" class="tags-body">
      <div
        ref="scrollChild"
        class="tags-container"
        :style="{ left: styleLeft + 'px' }"
      >
        <transition-group name="tags-transition">
          <template v-for="(item, index) of navHistory.historyList">
            <Tag
              ref="tagElement"
              :key="item.tag + index"
              :class="isActive(item) ? 'tag-is-active' : ''"
              :name="index"
              closable
              @click.native="changePage(item)"
              @on-close="onClose(item)"
            >
              <div class="tag-text">
                <div
                  :title="translate(item)"
                  class="tag-caption-content"
                >
                  <i
                    v-if="
                      item.meta.iconCls && !Object.is(item.meta.iconCls, '')
                    "
                    :class="item.meta.iconCls"
                  ></i>
                  <img v-else :src="item.meta.imgPath" class="text-icon" />
                  &nbsp;{{ translate(item) }}
                </div>
              </div>
            </Tag>
          </template>
        </transition-group>
      </div>
    </div>
    <div class="move-btn move-right" @click="rightMove">
      <icon type="ios-arrow-forward" />
    </div>
    <Dropdown
      @on-click="doTagAction"
      placement="bottom-end"
      transfer-class-name="app-page-more"
    >
      <div class="move-btn">
        <icon type="ios-more" />
      </div>
      <DropdownMenu slot="list">
        <template v-for="(action, index) of actions">
          <DropdownItem :key="index" :name="action.value">{{
            $t(action.text)
          }}</DropdownItem>
        </template>
      </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Provide, Prop, Watch } from "vue-property-decorator";
import { AppServiceBase } from "ibiz-core";
import { AppNavHistory } from "ibiz-vue";
import { HistoryItem } from "ibiz-vue/src/app-service/common-service/app-nav-history";

@Component({})
export default class TabPageExpStyle2 extends Vue {
  @Provide()
  public styleLeft: number = 0;

  @Provide()
  public actions: any[] = [
    { text: "app.tabpage.closeall", value: "closeAll" },
    { text: "app.tabpage.closeother", value: "closeOther" },
  ];

  /**
   * 导航服务
   *
   * @memberof TabPageExpStyle2
   */
  protected navHistory: AppNavHistory = AppServiceBase.getInstance().getAppNavDataService();

  /**
   * 环境对象
   *
   * @memberof TabPageExpStyle2
   */
  protected environment: any = AppServiceBase.getInstance().getAppEnvironment();

  /**
   * 当前激活菜单项
   *
   * @type {*}
   * @memberof TabPageExpStyle2
   */
  @Prop() public activeItem: any;

  /**
   * 模型服务对象
   * 
   * @memberof AppDefaultFormDetail
   */
  @Prop() public modelService?:any;

  /**
   * 监听激活菜单项变化
   *
   * @param newVal 当前激活菜单项
   * @memberof TabPageExpStyle2
   */
  @Watch("activeItem")
  public onActiveItemChange(newVal: any) {
    if (newVal) {
      this.changeTabHistory(newVal);
    }
  }

  @Watch("$route")
  public onRouteChange(newVal: any) {
    this.moveToView(newVal);
    this.$emit("change", newVal);
  }

  public created() {
    Vue.prototype.$tabPageExp = this;
  }

  /**
   * 向左移动
   *
   * @memberof TabPageExpStyle2
   */
  public leftMove() {
    const scrollBody: any = this.$refs.scrollBody;
    const scrollChild: any = this.$refs.scrollChild;
    if (
      scrollBody &&
      scrollChild &&
      scrollChild.offsetWidth > scrollBody.offsetWidth
    ) {
      if (
        scrollChild.offsetWidth - scrollBody.offsetWidth + this.styleLeft >
        100
      ) {
        this.styleLeft -= 100;
      } else {
        this.styleLeft = scrollBody.offsetWidth - scrollChild.offsetWidth;
      }
    }
  }

  /**
   * 向右移动
   *
   * @memberof TabPageExpStyle2
   */
  public rightMove() {
    if (this.styleLeft < 0) {
      if (this.styleLeft + 100 > 0) {
        this.styleLeft = 0;
      } else {
        this.styleLeft += 100;
      }
    }
  }

  /**
   * 是否选中
   *
   * @param {HistoryItem} item
   * @returns {boolean}
   * @memberof TabPageExpStyle2
   */
  public isActive(item: HistoryItem): boolean {
    return this.navHistory.isRouteSame(item.to, this.$route);
  }

  /**
   * 关闭
   *
   * @param {HistoryItem} item
   * @memberof TabPageExpStyle2
   */
  public onClose(item: HistoryItem) {
    // const appview = this.$store.getters['viewaction/getAppView'](item.tag);
    const appview: any = {};
    if (appview && appview.viewdatachange) {
      const title: any = this.$t("app.tabpage.sureclosetip.title");
      const content: any = this.$t("app.tabpage.sureclosetip.content");
      this.$Modal.confirm({
        title: title,
        content: content,
        onOk: () => {
          this.navHistory.remove(item);
          if (this.navHistory.historyList.length > 0) {
            if (this.navHistory.isRouteSame(item.to, this.$route)) {
              this.$router.back();
            }
          } else {
            this.gotoPage();
          }
        },
      });
    } else {
      this.navHistory.remove(item);
      if (this.navHistory.historyList.length > 0) {
        if (this.navHistory.isRouteSame(item.to, this.$route)) {
          let go: any = this.navHistory.historyList[
            this.navHistory.historyList.length - 1
          ].to;
          this.$router.push({
            path: go.path,
            params: go.params,
            query: go.query,
          });
        }
      } else {
        this.gotoPage();
      }
    }
  }

  /**
   * 是否显示关闭
   *
   * @returns
   * @memberof TabPageExpStyle2
   */
  public isClose() {
    if (this.navHistory.historyList.length > 1) {
      return true;
    }
    return false;
  }

  /**
   * 切换分页
   *
   * @param {*} item
   * @memberof TabPageExpStyle2
   */
  public changePage(item: HistoryItem) {
    this.gotoPage(item.to);
  }

  /**
   * 跳转页面
   *
   * @returns
   * @memberof TabPageExpStyle2
   */
  public gotoPage(page?: any) {
    if (page && this.navHistory.historyList.length > 0) {
      if (Object.is(page.fullPath, this.$route.fullPath)) {
        return;
      }
      this.updateSortIndex(page);
      this.$router.push({
        path: page.path,
        params: page.params,
        query: page.query,
      });
    } else {
      const path: string | null = window.sessionStorage.getItem(
        this.environment.AppName
      );
      if (path) {
        this.$router.push({ path: path });
      } else {
        const name: any = this.$route?.matched[0].name;
        const param = this.$route.params[name];
        const path = `/${name}${param ? `/${param}` : ""}`;
        this.$router.push({ path });
      }
    }
  }

  /**
   * 更新排序值
   *
   * @returns
   * @memberof TabPageExpStyle2
   */
  public updateSortIndex(page: any) {
    const pages: any[] = this.navHistory.historyList;
    if (pages.length > 0) {
      pages.forEach((item: any) => {
        if (Object.is(item.to.fullPath, page.fullPath)) {
          this.navHistory.updateSortIndex(item);
        }
      });
    }
  }

  /**
   * 激活菜单项变化时更改分页栏路由
   *
   * @returns
   * @memberof TabPageExpStyle2
   */
  public changeTabHistory(menu: any) {
    let menuTitle: string = "";
    if (!menu.hidden && menu.isActivated) {
      menuTitle = menu.text ? menu.text : menu.tooltip ? menu.tooltip : "";
    }
    let pages: any[] = this.navHistory.historyList;
    let groups: Array<any> = this.handleTabPagesGroup(pages);
    if (groups.length === 0) {
      return;
    }
    groups.forEach((group: any) => {
      if (
        Object.is(group.caption, menuTitle) &&
        group.items &&
        group.items.length > 0
      ) {
        let goTab: any = group.items.sort((val1: any, val2: any) => {
          return val2.sortIndex - val1.sortIndex;
        })[0];
        this.$router.push({
          path: goTab.to.fullPath,
          params: goTab.to.params,
          query: goTab.to.query,
        });
      }
    });
  }

  /**
   * 对所有打开的路由记录缓存进行分组
   *
   * @returns
   * @memberof TabPageExpStyle2
   */
  public handleTabPagesGroup(pages: any) {
    if (pages.length === 0) {
      return [];
    }
    let groups: Array<any> = [];
    pages.forEach((item: any) => {
      const caption: any = item.caption.split(" - ")[0];
      const tempArr: Array<any> = groups.filter((group: any) => {
        return Object.is(group.caption, caption);
      });
      if (tempArr.length === 0) {
        groups.push({ caption: caption, items: [item] });
      } else {
        tempArr[0].items.push(item);
      }
    });
    return groups;
  }

  /**
   * 移动至指定页面标签
   *
   * @param {*} page
   * @memberof TabPageExpStyle2
   */
  public moveToView(page: any) {
    const pages: any[] = this.navHistory.historyList;
    let leftWidth: number = 0;
    this.$nextTick(() => {
      pages.forEach((_page, index) => {
        const tag: any = this.$refs.tagElement;
        if (!tag) {
          return;
        }
        const el = tag[index].$el;
        if (Object.is(_page.fullPath, page.fullPath)) {
          this.setLeft(el, leftWidth);
        } else {
          leftWidth += el.offsetWidth;
        }
      });
    });
  }

  /**
   * 设置左侧边距
   *
   * @param {{ offsetWidth: number; }} tag
   * @param {number} leftWidth
   * @memberof TabPageExpStyle2
   */
  public setLeft(tag: { offsetWidth: number }, leftWidth: number) {
    if (tag) {
      const scrollBody: any = this.$refs.scrollBody;
      if (leftWidth < -this.styleLeft) {
        this.styleLeft = -leftWidth;
      } else if (
        leftWidth + tag.offsetWidth >
        scrollBody.offsetWidth - this.styleLeft
      ) {
        this.styleLeft -=
          leftWidth +
          tag.offsetWidth -
          (scrollBody.offsetWidth - this.styleLeft);
      }
    }
  }

  /**
   * 执行行为操作
   *
   * @param {string} name
   * @memberof TabPageExpStyle2
   */
  public doTagAction(name: string) {
    if (Object.is(name, "closeAll")) {
      this.navHistory.reset();
      this.gotoPage();
    } else if (Object.is(name, "closeOther")) {
      this.navHistory.removeOther({ to: this.$route });
      this.moveToView(this.$route);
    }
  }

  // 系统中有两个不同的tabPagExp但都注册了$tabPageExp，该方法防止页面调用$tabPageExp功能时报错
  public setCurPageCaption(opts: any) {
    this.navHistory.setCaption({ tag: opts.viewtag, info: opts.info });
  }

  /**
   * 翻译分页标题
   *
   * @returns
   * @memberof TabPageExpStyle2
   */
  public translate(item: any) {
    return item.info ? this.$tl(item.meta.captionTag,item.caption) + ' - ' + item.info : this.$tl(item.meta.captionTag,item.caption)
  }
}
</script>

<style lang="less">
@import "./tab-page-exp-style2.less";
</style>
