import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, TabExpPanelControlBase } from '@/studio-core';
import TestTaskService from '@/service/test-task/test-task-service';
import MainTabExpViewtabexppanelService from './main-tab-exp-viewtabexppanel-tabexppanel-service';
import TestTaskUIService from '@/uiservice/test-task/test-task-ui-service';


/**
 * tabexppanel部件基类
 *
 * @export
 * @class TabExpPanelControlBase
 * @extends {MainTabExpViewtabexppanelTabexppanelBase}
 */
export class MainTabExpViewtabexppanelTabexppanelBase extends TabExpPanelControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MainTabExpViewtabexppanelTabexppanelBase
     */
    protected controlType: string = 'TABEXPPANEL';

    /**
     * 建构部件服务对象
     *
     * @type {MainTabExpViewtabexppanelService}
     * @memberof MainTabExpViewtabexppanelTabexppanelBase
     */
    public service: MainTabExpViewtabexppanelService = new MainTabExpViewtabexppanelService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {TestTaskService}
     * @memberof MainTabExpViewtabexppanelTabexppanelBase
     */
    public appEntityService: TestTaskService = new TestTaskService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MainTabExpViewtabexppanelTabexppanelBase
     */
    protected appDeName: string = 'testtask';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MainTabExpViewtabexppanelTabexppanelBase
     */
    protected appDeLogicName: string = '测试版本';

    /**
     * 界面UI服务对象
     *
     * @type {TestTaskUIService}
     * @memberof MainTabExpViewtabexppanelBase
     */  
    public appUIService:TestTaskUIService = new TestTaskUIService(this.$store);

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
        if (this.context.testtask) {
            Object.assign(this.context, { srfparentdename: 'TestTask', srfparentkey: this.context.testtask });
        }
        super.ctrlCreated();
    }
}