import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, TabExpPanelControlBase } from '@/studio-core';
import IbzMyTerritoryService from '@/service/ibz-my-territory/ibz-my-territory-service';
import StatsTabExpViewtabexppanelService from './stats-tab-exp-viewtabexppanel-tabexppanel-service';
import IbzMyTerritoryUIService from '@/uiservice/ibz-my-territory/ibz-my-territory-ui-service';


/**
 * tabexppanel部件基类
 *
 * @export
 * @class TabExpPanelControlBase
 * @extends {StatsTabExpViewtabexppanelTabexppanelBase}
 */
export class StatsTabExpViewtabexppanelTabexppanelBase extends TabExpPanelControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof StatsTabExpViewtabexppanelTabexppanelBase
     */
    protected controlType: string = 'TABEXPPANEL';

    /**
     * 建构部件服务对象
     *
     * @type {StatsTabExpViewtabexppanelService}
     * @memberof StatsTabExpViewtabexppanelTabexppanelBase
     */
    public service: StatsTabExpViewtabexppanelService = new StatsTabExpViewtabexppanelService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {IbzMyTerritoryService}
     * @memberof StatsTabExpViewtabexppanelTabexppanelBase
     */
    public appEntityService: IbzMyTerritoryService = new IbzMyTerritoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof StatsTabExpViewtabexppanelTabexppanelBase
     */
    protected appDeName: string = 'ibzmyterritory';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof StatsTabExpViewtabexppanelTabexppanelBase
     */
    protected appDeLogicName: string = '我的地盘';

    /**
     * 界面UI服务对象
     *
     * @type {IbzMyTerritoryUIService}
     * @memberof StatsTabExpViewtabexppanelBase
     */  
    public appUIService:IbzMyTerritoryUIService = new IbzMyTerritoryUIService(this.$store);
    /**
     * 是否初始化
     *
     * @protected
     * @returns {any}
     * @memberof StatsTabExpViewtabexppanel
     */
    protected isInit: any = {
        tabviewpanel3:  true ,
        tabviewpanel:  false ,
        tabviewpanel2:  false ,
    }

    /**
     * 被激活的分页面板
     *
     * @protected
     * @type {string}
     * @memberof StatsTabExpViewtabexppanel
     */
    protected activatedTabViewPanel: string = 'tabviewpanel3';

    /**
     * 组件创建完毕
     *
     * @protected
     * @memberof StatsTabExpViewtabexppanel
     */
    protected ctrlCreated(): void {
        //设置分页导航srfparentdename和srfparentkey
        if (this.context.ibzmyterritory) {
            Object.assign(this.context, { srfparentdename: 'IbzMyTerritory', srfparentkey: this.context.ibzmyterritory });
        }
        super.ctrlCreated();
    }
}