import { IBizMainControlModel } from '../ibiz-main-control-model';
import { AppServiceBase, DynamicService } from '../../../service';
import { IBizEntityModel } from '../../entity';
import { IBizToolBarItemModel } from '../../control';

/**
 * 列表
 */
export class IBizListModel extends IBizMainControlModel {

	/**
	 * 操作项
	 */
	private $actionModel: any = {}

	/**
	 * 初始化IBizListModel对象
	 * 
	 * @param opts 额外参数
	 * @memberof IBizListModel
	 */
	public constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
		super(opts, viewRef, parentRef, runtimeData);
		this.initActionModel();

	}

	/**
	 * 初始化操作项
	 */
	public initActionModel() {
		this.controlModelData?.getPSDEListItems?.forEach((listItem: any) => {
			if (listItem.getPSDEUIActionGroup) {
				let UIActionGrop = listItem.getPSDEUIActionGroup;
				if (UIActionGrop.getPSUIActionGroupDetails) {
					UIActionGrop.getPSUIActionGroupDetails.forEach((UIAction: any) => {
						let tempData: any = {};
						tempData.name = UIAction?.name;
						tempData.icon = UIAction?.getPSSysImage?.cssClass;
						tempData.caption = UIAction?.caption;
						tempData.disabled = false;
						tempData.visibled = true;
						tempData.noprivdisplaymode = UIAction?.getNoPrivDisplayMode;
						tempData.dataaccaction = UIAction?.getPSUIAction;
						tempData.actiontarget = UIAction?.actionTarget;
						this.$actionModel[UIAction.name] = tempData;
					});
				}
			}
		});
	}

    /**
     * 加载模型数据
     * 
     * @memberof IBizListModel
     */
    public async loaded() {
        await super.loaded();
        //导航视图
        if (this.navAppView) {
            const targetNavAppView: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.navAppView.path);
            Object.assign(this.navAppView, targetNavAppView);
        }
        // 代码表
        if(this.allListDataItems?.length > 0){
            for(const dataItem of this.allListDataItems){
                if(dataItem?.getFrontPSCodeList?.modelref && dataItem?.getFrontPSCodeList.path){
                    const targetCodeList: any = await DynamicService.getInstance(this.context).getAppCodeListJsonData(dataItem?.getFrontPSCodeList?.path);
                    Object.assign(dataItem.getFrontPSCodeList, targetCodeList);
                }
            }
        }

        // 加载面板实体
        if(this.getItemPSLayoutPanel?.getPSAppDataEntity){
            if(this.getItemPSLayoutPanel.getPSAppDataEntity.modelref && this.getItemPSLayoutPanel.getPSAppDataEntity.path){
                let targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.getItemPSLayoutPanel.getPSAppDataEntity.path);
                Object.assign(this.getItemPSLayoutPanel.getPSAppDataEntity, targetAppEntity);
                delete this.getItemPSLayoutPanel.getPSAppDataEntity.modelref;
            }
            if(this.getItemPSLayoutPanel.getPSAppDataEntity){
                this.getItemPSLayoutPanel.$appDataEntity = new IBizEntityModel(this.getItemPSLayoutPanel.getPSAppDataEntity);
                this.getItemPSLayoutPanel.appDeCodeName = this.getItemPSLayoutPanel.$appDataEntity.codeName.toLowerCase();
            }
        }
    }

    /**
     * 导航视图
     * 
     * @memberof IBizGridModel
     */
    get navAppView() {
      return this.controlModelData.getNavPSAppView;
    }

	/**
	 * 操作项模型
	 *
	 * @memberof IBizListModel
	 */
	get actionModel() {
		return this.$actionModel;
	}

	/**
	 * 是否分组
	 *
	 * @memberof IBizListModel
	 */
	get enableGroup() {
		return this.controlModelData.enableGroup;
	}

	/**
	 * 获取列表项集合
	 *
	 * @memberof IBizListModel
	 */
	get getPSDEListItems() {
		if (this.controlModelData.getPSDEListItems && this.controlModelData.getPSDEListItems.length > 0) {
			return this.controlModelData.getPSDEListItems;
		} else {
			return undefined;
		}
	}

	/**
	 * 所有列表数据项集合
	 *
	 * @memberof IBizListModel
	 */
	get allListDataItems() {
		if (this.controlModelData?.getPSDEListDataItems.length > 0) {
			return this.controlModelData.getPSDEListDataItems;
		} else {
			return undefined;
		}
	}

    /**
     * 通过名称获取列表数据项
     *
     * @param {string} name
     * @memberof IBizListModel
     */
    public getListDataItemByName(name: string){
        return this.allListDataItems?.find((item:any)=>{
            return item.name == name
        });
    }

	/**
	 * 代码名称
	 *
	 * @memberof IBizListModel
	 */
	get codeName() {
		return this.controlModelData.codeName;
	}

	/**
	 * 是否显示处理提示
	 *
	 * @memberof IBizListModel
	 */
	get showBusyIndicator() {
		return this.controlModelData.showBusyIndicator;
	}

	/**
	 * 列表项布局面板
	 *
	 * @memberof IBizListModel
	 */
	get getItemPSLayoutPanel() {
		let panel: any = this.controlModelData.getItemPSLayoutPanel;
		if(panel) {
			panel.controlStyle = panel.controlStyle ? panel.controlStyle : "DEFAULT";
			return panel;
		}
	}

    /**
     * 获取部件工具栏项
     * 
     * @memberof IBizListModel
     */
    public controlToolBarItems(ctrlName: string){
        let targetItems:any[] = [];
        const tool: any = this.getControlDataByName(ctrlName);
        if(tool?.getPSDEToolbarItems){
            tool.getPSDEToolbarItems.forEach((element:any) => {
                targetItems.push(new IBizToolBarItemModel(element));
            });
        }
        return targetItems;
    }

    /**
     * 快速工具栏部件
     * 
     * @memberof IBizListModel
     */
	get getQuickPSDEToolbar() {
		return this.getControlDataByName('list_quicktoolbar');
    }

    /**
     * 批操作工具栏部件
     * 
     * @memberof IBizListModel
     */
    get getBatchPSDEToolbar() {
		return this.getControlDataByName('list_batchtoolbar');
	}
    
    /**
     * 获取项绘制插件
     * 
     * @readonly
     * @memberof IBizListModel
     */
    get getItemPSSysPFPlugin(){
        return this.controlModelData.getItemPSSysPFPlugin;
	}
	
	/**
	 * 用户标识
	 * 
	 * @memberof IBizListModel
	 */
	get userTag(){
		return this.controlModelData.userTag;
	}

}