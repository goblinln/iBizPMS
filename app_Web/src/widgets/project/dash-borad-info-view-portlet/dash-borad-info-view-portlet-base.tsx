import { Prop, Provide, Emit, Watch, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { CtrlBase } from '@/studio-core';
import ProjectService from '@/service/project/project-service';
import DashBoradInfoViewService from './dash-borad-info-view-portlet-service';
import { Environment } from '@/environments/environment';


/**
 * dashboard_sysportlet3部件基类
 *
 * @export
 * @class CtrlBase
 * @extends {DashBoradInfoViewPortletBase}
 */
export class DashBoradInfoViewPortletBase extends CtrlBase {

    /**
     * 建构部件服务对象
     *
     * @type {DashBoradInfoViewService}
     * @memberof DashBoradInfoViewPortletBase
     */
    public service: DashBoradInfoViewService = new DashBoradInfoViewService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProjectService}
     * @memberof DashBoradInfoViewPortletBase
     */
    public appEntityService: ProjectService = new ProjectService({ $store: this.$store });

    /**
     * 长度
     *
     * @type {number}
     * @memberof DashBoradInfoView
     */
    @Prop() public height?: number;

    /**
     * 宽度
     *
     * @type {number}
     * @memberof DashBoradInfoView
     */
    @Prop() public width?: number;



    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof DashBoradInfoViewBase
     */
    @Prop({default: false})public isAdaptiveSize!: boolean;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof DashBoradInfoViewBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof DashBoradInfoViewBase
     */
    public getData(): any {
        return {};
    }

    /**
     * vue 生命周期
     *
     * @memberof DashBoradInfoViewBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof DashBoradInfoViewBase
     */    
    public afterCreated(){
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                const refs: any = this.$refs;
                Object.keys(refs).forEach((_name: string) => {
                    this.viewState.next({ tag: _name, action: action, data: data });
                });
            });
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof DashBoradInfoViewBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof DashBoradInfoViewBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }


}
