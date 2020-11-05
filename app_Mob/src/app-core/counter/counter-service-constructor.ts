import { ServiceConstructorBase } from '@/ibiz-core/service/service-constructor-base';

/**
 * 计数器服务注册中心
 *
 * @export
 * @class CounterServiceConstructor
 * @extends {ServiceConstructorBase}
 */
export class CounterServiceConstructor extends ServiceConstructorBase {

    /**
     * 初始化
     *
     * @protected
     * @memberof CounterServiceConstructor
     */
    protected init(): void {
        this.allService.set('projectcounter', () => import('@/app-core/counter/project-counter/project-counter-counter'));
        this.allService.set('mobmenucounter', () => import('@/app-core/counter/mob-menu-counter/mob-menu-counter-counter'));
        this.allService.set('myfavoritemobcounter', () => import('@/app-core/counter/my-favorite-mob-counter/my-favorite-mob-counter-counter'));
        this.allService.set('productmobcounter', () => import('@/app-core/counter/product-mob-counter/product-mob-counter-counter'));
        this.allService.set('mobproductplancounter', () => import('@/app-core/counter/mob-product-plan-counter/mob-product-plan-counter-counter'));
        this.allService.set('producttestmobcounter', () => import('@/app-core/counter/product-test-mob-counter/product-test-mob-counter-counter'));
        this.allService.set('mymobcounter', () => import('@/app-core/counter/my-mob-counter/my-mob-counter-counter'));
    }

}
/**
 * 计数器服务构造器
 */
export const counterServiceConstructor: CounterServiceConstructor = new CounterServiceConstructor();