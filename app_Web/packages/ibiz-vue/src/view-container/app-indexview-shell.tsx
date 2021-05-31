import { isNilOrEmpty } from 'qx-util';
import { Component, Emit, Prop } from 'vue-property-decorator';
import { ViewContainerBase } from './view-container-base';

/**
 * 视图壳
 *
 * @export
 * @class AppIndexViewShell
 * @extends {ViewContainerBase}
 */
@Component({})
export class AppIndexViewShell extends ViewContainerBase {
  /**
   * 数据变化
   *
   * @param {*} val
   * @returns {*}
   * @memberof AppIndexViewShell
   */
  @Emit()
  viewDatasChange(val: any): any {
    return val;
  }

  /**
   * 视图静态参数
   *
   * @type {string}
   * @memberof AppIndexViewShell
   */
  @Prop()
  staticProps!: any;

  /**
   * 视图动态参数
   *
   * @type {string}
   * @memberof AppIndexViewShell
   */
  @Prop()
  dynamicProps!: any;

  created() {
    if (this.$route && this.$route.matched && this.$route.matched.length > 0) {
      let indexRoute: any = this.$route.matched.find((item: any) => {
        return item?.meta?.dynaModelFilePath;
      });
      if (indexRoute) {
        this.dynaModelFilePath = indexRoute.meta.dynaModelFilePath;
      }
    }
    this.loadDynamicModelData();
  }

  mounted() {
    this.appLoadingDestroyed();
  }

  appLoadingDestroyed() {
    setTimeout(() => {
      const el = document.getElementById('app-loading-x');
      if (el) {
        el.style.display = 'none';
      }
    }, 300);
  }

  render(h: any) {
    if (isNilOrEmpty(this.viewContainerName)) {
      return;
    }
    return h(this.viewContainerName, {
      props: { dynamicProps: this.dynamicProps, staticProps: this.viewContext },
      on: {
        'view-event': this.handleViewEvent.bind(this),
      },
    });
  }
}
