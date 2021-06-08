import { Vue } from 'vue-property-decorator';
import { Subject, Observable } from 'rxjs';
import { AppServiceBase } from 'ibiz-core';
import { Util } from 'ibiz-core';
import { AppTopDrawer } from './app-top-drawer';

/**
 * 模态
 *
 * @export
 * @class AppTopDrawerContainer
 */
export class AppTopDrawerContainer {
    /**
     * 唯一实例
     *
     * @protected
     * @static
     * @type {AppTopDrawerContainer}
     * @memberof AppTopDrawerContainer
     */
    protected static readonly instance: AppTopDrawerContainer = new AppTopDrawerContainer();

    /**
     * 模态承载容器
     *
     * @protected
     * @type {HTMLDivElement}
     * @memberof AppTopDrawerContainer
     */
    protected modalContainer: HTMLDivElement;

    /**
     * store对象
     *
     * @private
     * @memberof AppTopDrawerContainer
     */
    private store: any;

    /**
     * i18n对象
     *
     * @private
     * @memberof AppTopDrawerContainer
     */
    private i18n: any;

    /**
     * 路由对象
     *
     * @private
     * @memberof AppTopDrawerContainer
     */
    private router: any;

    /**
     * Vue实例
     *
     * @protected
     * @type {*}
     * @memberof AppTopDrawerContainer
     */
    protected vueInstance!: any;

    /**
     * Creates an instance of AppTopDrawerContainer.
     * @memberof AppTopDrawerContainer
     */
    constructor() {
        this.modalContainer = document.createElement('div');
        document.body.appendChild(this.modalContainer);
    }

    /**
     * 初始化基础数据
     * 
     * @memberof AppTopDrawerContainer
     */
    private initBasicData() {
        const appService = AppServiceBase.getInstance();
        this.store = appService.getAppStore();
        this.i18n = appService.getI18n();
        this.router = appService.getRouter();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof AppTopDrawerContainer
     */
    protected init(): void {
        this.vueInstance = new Vue({
            store: this.store,
            router: this.router,
            i18n: this.i18n,
            render: (h: any) => h(AppTopDrawer, { ref: 'AppTopDrawer' }),
        }).$mount(this.modalContainer);
    }

    /**
     * 顶部抽屉模式打开视图
     *
     * @param {{ viewname: string, title: string, width?: number, height?: number }} view 视图
     * @param {*} [viewParam={}] 视图参数
     * @param {any[]} deResParameters 关系实体参数对象
     * @param {any[]} parameters 当前应用视图参数对象
     * @param {any[]} args 多项数据
     * @param {*} [data={}] 行为参数
     * @returns {Observable<any>}
     * @memberof AppTopDrawerContainer
     */
    public openDrawer(
        view: { viewname: string; title: string; width?: number; height?: number; placement?: string },
        dynamicProps: any = {},
        staticProps: any = {}
    ): Observable<any> {
        view.viewname = 'app-view-shell';
        const subject: Subject<any> = new Subject();
        this.initBasicData();
        this.getVueInstance()
            .$refs.AppTopDrawer.openModal({ ...view, dynamicProps: dynamicProps, staticProps: staticProps })
            .then((data: any) => {
                subject.next(data);
                subject.complete();
                subject.unsubscribe();
            });
        return subject.asObservable();
    }

    /**
     * 获取Vue容器实例
     *
     * @protected
     * @returns {*}
     * @memberof AppTopDrawerContainer
     */
    protected getVueInstance(): any {
        if (!this.vueInstance) {
            this.init();
        }
        return this.vueInstance;
    }

}

// 模态服务控制器实例
export const appTopDrawerContainer: AppTopDrawerContainer = new AppTopDrawerContainer();