import { DynamicService } from '../../../service';
import { IBizMainControlModel } from '../ibiz-main-control-model';

/**
 * 门户部件模型
 *
 * @export
 * @class IBizPortletModel
 */
export class IBizPortletModel extends IBizMainControlModel{
    
    /**
     * 加载模型数据(视图和部件)
     *
     * @memberof IBizMainControlModel
     */
    public async loaded() {
        await super.loaded();
        // 加载视图
        if(this.portletType == 'VIEW' && this.portletAppView?.modelref && this.portletAppView?.path ){
            const targetView: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.portletAppView?.path);
            Object.assign(this.portletAppView, targetView);
            delete this.portletAppView.modelref;
        }
    }

    /**
     * 门户部件类型
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get portletType(){
        return this.controlModelData.portletType;
    }
    
    /**
     * 是否显示标题
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get showTitleBar(){
        return this.controlModelData.showTitleBar;
    }
    
    /**
     * 标题
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get title(){
        return this.controlModelData.title;
    }
    
    /**
     * 直接内容高度
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get rawItemHeight(){
        return this.controlModelData.rawItemHeight;
    }
    
    /**
     * 直接内容宽度
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get rawItemWidth(){
        return this.controlModelData.rawItemWidth;
    }
        
    /**
     * 直接内容类型
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get contentType(){
        return this.controlModelData.contentType;
    }
        
    /**
     * 直接内容
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get rawContent(){
        return this.controlModelData.rawContent;
    }
        
    /**
     * 直接内容(HTML)
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get htmlContent(){
        return this.controlModelData.htmlContent;
    }

    /**
     * 网页地址
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get pageUrl(){
        return this.controlModelData.pageUrl;
    }

    /**
     * 获取布局位置
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get layoutPos(){
        return this.controlModelData.getPSLayoutPos;
    }

    /**
     * 获取布局设置
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get layout(){
        return this.controlModelData.getPSLayout;
    }

    /**
     * 获取门户部件关联视图
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get portletAppView(){
        return this.controlModelData.getPortletPSAppView;
    }

    /**
     * 获取门户部件系统图片资源
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get sysImage(){
        return this.controlModelData.getPSSysImage;
    }

    /**
     * 获取绘制插件
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get render(){
        return this.controlModelData.render;
    }

    /**
     * 获取界面行为组
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get actionGroupDetails(){
        return this.controlModelData.getPSUIActionGroup?.getPSUIActionGroupDetails;
    }

    /**
     * 获取门户部件分组
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get appPortletCat(){
        return this.controlModelData.getPSAppPortletCat;
    }

    /**
     * 获取门户部件图标
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get getPSSysImage(){
        return this.controlModelData.getPSSysImage;
    }
}