import { Prop, Provide, Emit, Watch, Model } from 'vue-property-decorator';
import { CreateElement } from 'vue';
import { Subject, Subscription } from 'rxjs';
import { TabExpPanel } from '@/studio-core';
import ReleaseService from '@/service/release/release-service';
import MainTabExpViewtabexppanelService from './main-tab-exp-viewtabexppanel-tabexppanel-service';


/**
 * tabexppanel部件基类
 *
 * @export
 * @class TabExpPanel
 * @extends {MainTabExpViewtabexppanelBase}
 */
export class MainTabExpViewtabexppanelBase extends TabExpPanel {

    /**
     * 建构部件服务对象
     *
     * @type {MainTabExpViewtabexppanelService}
     * @memberof MainTabExpViewtabexppanel
     */
    public service: MainTabExpViewtabexppanelService = new MainTabExpViewtabexppanelService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ReleaseService}
     * @memberof MainTabExpViewtabexppanel
     */
    public appEntityService: ReleaseService = new ReleaseService({ $store: this.$store });
    /**
     * 是否初始化
     *
     * @protected
     * @returns {any}
     * @memberof MainTabExpViewtabexppanel
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
     * @memberof MainTabExpViewtabexppanel
     */
    protected activatedTabViewPanel: string = 'tabviewpanel';

    /**
     * 组件创建完毕
     *
     * @protected
     * @memberof MainTabExpViewtabexppanel
     */
    protected ctrlCreated(): void {
        //设置分页导航srfparentdename和srfparentkey
        if (this.context.release) {
            Object.assign(this.context, { srfparentdename: 'Release', srfparentkey: this.context.release });
        }
        super.ctrlCreated();
    }

    /**
     * 分页面板选中
     *
     * @protected
     * @param {*} e
     * @returns
     * @memberof MainTabExpViewtabexppanel
     */
    protected tabPanelClick(e: any): void {
        super.tabPanelClick(e);
    }
}