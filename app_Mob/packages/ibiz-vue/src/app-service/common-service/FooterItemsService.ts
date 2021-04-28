import { SingletonMode } from '../../decorators/SingletonMode';
import { CreateElement, VNode } from 'vue';
import { Subject, Observable } from 'rxjs';

/**
 * 底部项绘制服务
 *
 * @export
 * @class FooterActionService
 */
@SingletonMode()
export class FooterItemsService {
    /**
     * 变更脏检查手动触发
     *
     * @private
     * @type {Subject<void>}
     * @memberof FooterActionService
     */
    private tickSubject: Subject<void> = new Subject();
    /**
     * 底部左侧项
     *
     * @memberof FooterActionService
     */
    public readonly leftItemsRenders: ((h: CreateElement) => VNode)[] = [];
    /**
     * 底部中间项
     *
     * @memberof FooterActionService
     */
    public readonly centerItemsRenders: ((h: CreateElement) => VNode)[] = [];
    /**
     * 底部右侧项
     *
     * @memberof FooterActionService
     */
    public readonly rightItemsRenders: ((h: CreateElement) => VNode)[] = [];

    /**
     * 注册左侧项
     *
     * @param {(h: CreateElement) => VNode} fun
     * @param {number} [index] 插入指定位置
     * @returns {{ remove: () => boolean }}
     * @memberof FooterItemsService
     */
    public registerLeftItem(fun: (h: CreateElement) => VNode, index?: number): { remove: () => boolean } {
        return this.addItem(this.leftItemsRenders, fun, index);
    }

    /**
     * 注册中间项
     *
     * @param {(h: CreateElement) => VNode} fun
     * @param {number} [index] 插入指定位置
     * @returns {{ remove: () => boolean }}
     * @memberof FooterActionService
     */
    public registerCenterItem(fun: (h: CreateElement) => VNode, index?: number): { remove: () => boolean } {
        return this.addItem(this.centerItemsRenders, fun, index);
    }

    /**
     * 注册右侧项
     *
     * @param {(h: CreateElement) => VNode} fun
     * @param {number} [index] 插入指定位置
     * @returns {{ remove: () => boolean }}
     * @memberof FooterActionService
     */
    public registerRightItem(fun: (h: CreateElement) => VNode, index?: number): { remove: () => boolean } {
        return this.addItem(this.rightItemsRenders, fun, index);
    }

    /**
     * 插入项
     *
     * @protected
     * @param {any[]} items
     * @param {*} fun
     * @param {number} [index]
     * @returns {{ remove: () => boolean }}
     * @memberof FooterItemsService
     */
    protected addItem(items: any[], fun: any, index?: number): { remove: () => boolean } {
        let i: number = -1;
        if (index !== undefined && index > -1 && index < items.length) {
            items.splice(index, 0, fun);
            i = index;
        } else if (fun) {
            items.push(fun);
            i = items.length - 1;
        }
        return {
            remove: () => {
                return this.remove(items, i);
            },
        };
    }

    /**
     * 删除指定位置数据
     *
     * @protected
     * @param {any[]} items
     * @param {number} index
     * @returns {boolean}
     * @memberof FooterItemsService
     */
    protected remove(items: any[], index: number): boolean {
        if (items && items.length > index) {
            items.splice(index, 1);
            this.tick();
            return true;
        }
        return false;
    }

    /**
     * 手动触发脏检查
     *
     * @memberof FooterActionService
     */
    public tick(): void {
        this.tickSubject.next();
    }

    /**
     * 订阅脏检查触发流
     *
     * @returns {Observable<void>}
     * @memberof FooterItemsService
     */
    public tickTrigger(): Observable<void> {
        return this.tickSubject.asObservable();
    }
}
