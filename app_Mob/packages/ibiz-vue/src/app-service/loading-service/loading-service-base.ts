// import { Loading } from 'element-ui';
// import { ElLoadingComponent } from 'element-ui/types/loading';
// import { Loading } from 'ibiz-vue'

/**
 * 加载服务基类
 *
 * @export
 * @class LoadingServiceBase
 */
 export class LoadingServiceBase {

  /**
   * loading 对象
   *
   * @type {(ElLoadingComponent | any)}
   * @memberof LoadingServiceBase
   */
  public elLoadingComponent:  any;

  /**
   * 是否加载
   *
   * @type {boolean}
   * @memberof LoadingServiceBase
   */
  public isLoading: boolean = false;

  /**
   * 加载结束
   *
   * @public
   * @memberof LoadingServiceBase
   */
  public endLoading(selector: any): void {
      if (!this.isLoading) {
          return
      }
      if(selector){
        let cover = selector.querySelector('.cover');
        if (selector.contains(cover)) {
          selector.removeChild(cover);
        }
    }
      this.isLoading = false;
  }

  /**
   * 开始加载
   *
   * @public
   * @memberof LoadingServiceBase
   */
  public beginLoading(selector: any): void {
      this.isLoading = true;
      // 自定义loading元素
      const userEle = document.createElement('div');
      userEle.classList.add('cover');
      const innerDiv = document.createElement('div');
      innerDiv.classList.add('loading');
      for (let i = 0; i < 4; i++) {
        const dot = document.createElement('span');
        innerDiv.appendChild(dot);
      }
      userEle.appendChild(innerDiv);
      // 挂载
      if (selector) {
        selector.appendChild(userEle);
      }

  }
}