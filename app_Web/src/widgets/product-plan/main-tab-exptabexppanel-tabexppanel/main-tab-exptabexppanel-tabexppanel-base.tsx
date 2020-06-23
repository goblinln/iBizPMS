import { Prop, Provide, Emit, Watch, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { TabExpPanel } from '@/studio-core';
import ProductPlanService from '@/service/product-plan/product-plan-service';
import MainTabExptabexppanelService from './main-tab-exptabexppanel-tabexppanel-service';


/**
 * tabexppanel部件基类
 *
 * @export
 * @class TabExpPanel
 * @extends {MainTabExptabexppanelBase}
 */
export class MainTabExptabexppanelBase extends TabExpPanel {

    /**
     * 建构部件服务对象
     *
     * @type {MainTabExptabexppanelService}
     * @memberof MainTabExptabexppanel
     */
    public service: MainTabExptabexppanelService = new MainTabExptabexppanelService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductPlanService}
     * @memberof MainTabExptabexppanel
     */
    public appEntityService: ProductPlanService = new ProductPlanService({ $store: this.$store });
    /**
     * 是否初始化
     *
     * @protected
     * @returns {any}
     * @memberof MainTabExptabexppanel
     */
    protected isInit: any = {
        tabviewpanel:  true ,
        tabviewpanel2:  false ,
        tabviewpanel3:  false ,
        tabviewpanel4:  false ,
    }

    /**
     * 被激活的分页面板
     *
     * @protected
     * @type {string}
     * @memberof MainTabExptabexppanel
     */
    protected activatedTabViewPanel: string = 'tabviewpanel';

    /**
     * 组件创建完毕
     *
     * @protected
     * @memberof MainTabExptabexppanel
     */
    protected ctrlCreated(): void {
        //设置分页导航srfparentdename和srfparentkey
        if (this.context.productplan) {
            Object.assign(this.context, { srfparentdename: 'ProductPlan', srfparentkey: this.context.productplan });
        }
        super.ctrlCreated();
    }

    /**
     * 分页面板选中
     *
     * @protected
     * @param {*} e
     * @returns
     * @memberof MainTabExptabexppanel
     */
    protected tabPanelClick(e: any): void {
        super.tabPanelClick(e);
    }
}