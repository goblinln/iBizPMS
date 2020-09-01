import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, PanelControlBase } from '@/studio-core';
import IbzMyTerritoryService from '@/service/ibz-my-territory/ibz-my-territory-service';
import MyWork2Service from './my-work2-panel-service';
import IbzMyTerritoryUIService from '@/uiservice/ibz-my-territory/ibz-my-territory-ui-service';
import { PanelDetailModel,PanelRawitemModel,PanelTabPanelModel,PanelTabPageModel,PanelFieldModel,PanelContainerModel,PanelControlModel,PanelUserControlModel,PanelButtonModel } from '@/model/panel-detail';
import MyWork2Model from './my-work2-panel-model';
import CodeListService from "@service/app/codelist-service";
import { ViewTool } from '@/utils';


/**
 * list_itempanel部件基类
 *
 * @export
 * @class PanelControlBase
 * @extends {MyWork2PanelBase}
 */
export class MyWork2PanelBase extends PanelControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MyWork2PanelBase
     */
    protected controlType: string = 'PANEL';

    /**
     * 建构部件服务对象
     *
     * @type {MyWork2Service}
     * @memberof MyWork2PanelBase
     */
    public service: MyWork2Service = new MyWork2Service({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {IbzMyTerritoryService}
     * @memberof MyWork2PanelBase
     */
    public appEntityService: IbzMyTerritoryService = new IbzMyTerritoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MyWork2PanelBase
     */
    protected appDeName: string = 'ibzmyterritory';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MyWork2PanelBase
     */
    protected appDeLogicName: string = '我的地盘';

    /**
     * 界面UI服务对象
     *
     * @type {IbzMyTerritoryUIService}
     * @memberof MyWork2Base
     */  
    public appUIService:IbzMyTerritoryUIService = new IbzMyTerritoryUIService(this.$store);

    /**
     * 详情模型集合
     *
     * @type {*}
     * @memberof MyWork2
     */
    public detailsModel: any = {
        realname: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container2: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem1: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container9: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem2: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        mytasks: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        myetasks: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container4: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem3: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        mybugs: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        myebugs: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container5: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem4: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        mystorys: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem7: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container6: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem5: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        projects: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        eprojects: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container7: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem6: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        products: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        rawitem8: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container8: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container10: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container3: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
        container1: new FormItemModel({ visible: true, disabled: false, enableCond: 3 }), 
    };

    /**
     * 面板逻辑
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof MyWork2
     */
    public panelLogic({ name, newVal, oldVal }: { name: string, newVal: any, oldVal: any }): void {
                



























    }

    /**
     * 数据模型对象
     *
     * @type {MyWork2Model}
     * @memberof MyWork2
     */
    public dataModel: MyWork2Model = new MyWork2Model();

    /**
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof MyWork2
     */
    public uiAction(row: any, tag: any, $event: any) {
    }
}