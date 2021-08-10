import { VNode } from 'vue';
import { Util } from 'ibiz-core';
import './badge.less';

/**
 * 微标指令
 *
 * @export
 * @class Badge
 */
export const Badge: any = {
    /**
     * 指令初始化
     *
     * @param {HTMLDivElement} el
     * @param {*} binding
     * @param {VNode} vNode
     * @param {VNode} oldVNode
     */
    bind(el: HTMLDivElement, binding: any, vNode: VNode, oldVNode: VNode) {
        bc.init(el,binding);
    },

    /**
     * 指令更新
     *
     * @param {HTMLDivElement} el
     * @param {*} binding
     * @param {VNode} vNode
     * @param {VNode} oldVNode
     */
    componentUpdated(el: HTMLDivElement, binding: any, vNode: VNode, oldVNode: VNode) {
        bc.update(el,binding);
    },
};

/**
 * 微标控制器
 *
 * @export
 * @class BadgeController
 */
 export class BadgeController {
  /**
   * 唯一实例
   *
   * @private
   * @static
   * @memberof BadgeControllerController
   */
  private static readonly instance = new BadgeController();

  /**
   * 容器
   *
   * @protected
   * @type {HTMLDivElement}
   * @memberof NotificationSignalController
   */
  protected el!: HTMLElement;

  /**
   * Creates an instance of BadgeControllerController.
   * @memberof BadgeControllerController
   */
  private constructor() {
      if (BadgeController.instance) {
          return BadgeController.instance;
      }
  }

  /**
   * 初始化
   *
   * @param {HTMLDivElement} 
   * @param {any} 
   * @memberof BadgeController
   */
  public init(el: HTMLDivElement, binding: any): void {
    const item: any = binding.value;
    if (Object.keys(item).length > 0 && Util.isExistAndNotEmpty(item.count)) {
      if(!item.showZero && item.count == 0) {
        return;
      }
      let badge: HTMLElement = document.createElement("sup");
      badge.innerHTML = String(item.count);
      badge.classList.add('ibiz-badge');
      this.el = badge;
      el.append(badge);
      this.setBadgeclass(item.type);
      this.setBadgeclass(item.className);
      this.setBadgeOffset(item.offset);
    }
  }

  /**
   * 更新
   *
   * @param {HTMLDivElement} 
   * @param {any} 
   * @memberof BadgeController
   */
   public update(el: HTMLDivElement, binding: any): void {
    const item: any = binding.value;
    if (Object.keys(item).length > 0 && Util.isExistAndNotEmpty(item.count)) {
      if(!item.showZero && item.count == 0) {
        return;
      }
      this.el.innerHTML = String(item.count);
    }
  }

  /**
   * 设置徽标类型样式
   *
   * @param {string} 
   * @memberof BadgeController
   */
  public setBadgeclass(type: string) {
    if (!type) {
      return;
    }
    this.el.classList.add(type);
  }

  /**
   * 设置徽标偏移量
   *
   * @param {string} 
   * @memberof BadgeController
   */
  public setBadgeOffset(offset: Array<number>) {
    if (offset && offset.length == 2) {
      this.el.style.transform = `translate(${offset[0]}px, ${offset[1]}px)`;
    }
  }


  /**
   * 获取唯一实例
   *
   * @static
   * @returns {BadgeController}
   * @memberof BadgeController
   */
  public static getInstance(): BadgeController {
      return BadgeController.instance;
  }
}

// 导出服务
export const bc: BadgeController = BadgeController.getInstance();


