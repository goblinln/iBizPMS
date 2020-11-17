import { Subject } from 'rxjs';
import { UIActionTool, ViewTool } from '@/utils';
import { PortalViewBase } from '@/studio-core';

/**
 * 项目主页视图基类
 *
 * @export
 * @class ProjectPortalViewBase
 * @extends {PortalViewBase}
 */
export class ProjectPortalViewBase extends PortalViewBase {

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof ProjectPortalViewBase
     */
    protected model: any = {
        srfCaption: 'app.views.projectportalview.caption',
        srfTitle: 'app.views.projectportalview.title',
        srfSubTitle: 'app.views.projectportalview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof ProjectPortalViewBase
     */
    protected containerModel: any = {
        view_dashboard: {
            name: 'dashboard',
            type: 'DASHBOARD',
        },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof ProjectPortalViewBase
     */
	protected viewtag: string = 'CC49500F-030F-458F-82DF-F88F6973DF64';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof ProjectPortalViewBase
     */ 
    protected viewName: string = 'ProjectPortalView';



    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ProjectPortalViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof ProjectPortalViewBase
     */
    public engineInit(): void {
    }




}