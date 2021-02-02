import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { OptionViewBase } from '@/studio-core';
import StoryService from '@/service/story/story-service';
import StoryAuthService from '@/authservice/story/story-auth-service';
import OptionViewEngine from '@engine/view/option-view-engine';
import StoryUIService from '@/uiservice/story/story-ui-service';

/**
 * 指派视图基类
 *
 * @export
 * @class StoryAssignToViewBase
 * @extends {OptionViewBase}
 */
export class StoryAssignToViewBase extends OptionViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof StoryAssignToViewBase
     */
    protected appDeName: string = 'story';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof StoryAssignToViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof StoryAssignToViewBase
     */
    protected appDeMajor: string = 'title';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof StoryAssignToViewBase
     */ 
    protected dataControl: string = 'form';

    /**
     * 实体服务对象
     *
     * @type {StoryService}
     * @memberof StoryAssignToViewBase
     */
    protected appEntityService: StoryService = new StoryService;

    /**
     * 实体权限服务对象
     *
     * @type StoryUIService
     * @memberof StoryAssignToViewBase
     */
    public appUIService: StoryUIService = new StoryUIService(this.$store);

	/**
	 * 自定义视图导航上下文集合
	 *
     * @protected
	 * @type {*}
	 * @memberof StoryAssignToViewBase
	 */
    protected customViewNavContexts: any = {
        'OBJECTTYPE': {
            isRawValue: true,
            value: 'story',
        },
        'SRFPARENTKEY': {
            isRawValue: false,
            value: 'story',
        }
    };

    /**
     * 是否显示信息栏
     *
     * @memberof StoryAssignToViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof StoryAssignToViewBase
     */
    protected model: any = {
        srfCaption: 'entities.story.views.assigntoview.caption',
        srfTitle: 'entities.story.views.assigntoview.title',
        srfSubTitle: 'entities.story.views.assigntoview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof StoryAssignToViewBase
     */
    protected containerModel: any = {
        view_form: {
            name: 'form',
            type: 'FORM',
        },
        view_okbtn: {
            name: 'okbtn',
            type: 'button',
            text: '确定',
            disabled: true,
        },
        view_cancelbtn: {
            name: 'cancelbtn',
            type: 'button',
            text: '取消',
            disabled: false,
        },
        view_leftbtn: {
            name: 'leftbtn',
            type: 'button',
            text: '左移',
            disabled: true,
        },
        view_rightbtn: {
            name: 'rightbtn',
            type: 'button',
            text: '右移',
            disabled: true,},
        view_allleftbtn: {
            name: 'allleftbtn',
            type: 'button',
            text: '全部左移',
            disabled: true,
        },
        view_allrightbtn: {
            name: 'allrightbtn',
            type: 'button',
            text: '全部右移',
            disabled: true,
        },
    };


	/**
     * 视图唯一标识
     *
     * @protected
     * @type {string}
     * @memberof StoryAssignToViewBase
     */
	protected viewtag: string = '90d6a9dc6032a7a7e19fee6ac22fccc0';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof StoryAssignToViewBase
     */ 
    protected viewName: string = 'StoryAssignToView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof StoryAssignToViewBase
     */
    public engine: OptionViewEngine = new OptionViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof StoryAssignToViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof StoryAssignToViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            form: this.$refs.form,
            p2k: '0',
            keyPSDEField: 'story',
            majorPSDEField: 'title',
            isLoadDefault: true,
        });
    }

    /**
     * form 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof StoryAssignToViewBase
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof StoryAssignToViewBase
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof StoryAssignToViewBase
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }


}