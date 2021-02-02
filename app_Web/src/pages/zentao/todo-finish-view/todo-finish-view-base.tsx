import { Subject } from 'rxjs';
import { UIActionTool, ViewTool, Util } from '@/utils';
import { OptionViewBase } from '@/studio-core';
import TodoService from '@/service/todo/todo-service';
import TodoAuthService from '@/authservice/todo/todo-auth-service';
import OptionViewEngine from '@engine/view/option-view-engine';
import TodoUIService from '@/uiservice/todo/todo-ui-service';

/**
 * 完成视图基类
 *
 * @export
 * @class TodoFinishViewBase
 * @extends {OptionViewBase}
 */
export class TodoFinishViewBase extends OptionViewBase {
    /**
     * 视图对应应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof TodoFinishViewBase
     */
    protected appDeName: string = 'todo';

    /**
     * 应用实体主键
     *
     * @protected
     * @type {string}
     * @memberof TodoFinishViewBase
     */
    protected appDeKey: string = 'id';

    /**
     * 应用实体主信息
     *
     * @protected
     * @type {string}
     * @memberof TodoFinishViewBase
     */
    protected appDeMajor: string = 'name';

    /**
     * 数据部件名称
     *
     * @protected
     * @type {string}
     * @memberof TodoFinishViewBase
     */ 
    protected dataControl: string = 'form';

    /**
     * 实体服务对象
     *
     * @type {TodoService}
     * @memberof TodoFinishViewBase
     */
    protected appEntityService: TodoService = new TodoService;

    /**
     * 实体权限服务对象
     *
     * @type TodoUIService
     * @memberof TodoFinishViewBase
     */
    public appUIService: TodoUIService = new TodoUIService(this.$store);

    /**
     * 是否显示信息栏
     *
     * @memberof TodoFinishViewBase
     */
    isShowDataInfoBar: boolean = true;

    /**
     * 视图模型数据
     *
     * @protected
     * @type {*}
     * @memberof TodoFinishViewBase
     */
    protected model: any = {
        srfCaption: 'entities.todo.views.finishview.caption',
        srfTitle: 'entities.todo.views.finishview.title',
        srfSubTitle: 'entities.todo.views.finishview.subtitle',
        dataInfo: '',
    };

    /**
     * 容器模型
     *
     * @protected
     * @type {*}
     * @memberof TodoFinishViewBase
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
     * @memberof TodoFinishViewBase
     */
	protected viewtag: string = '807a419a917fbfdfa3378d2fac6a2785';

    /**
     * 视图名称
     *
     * @protected
     * @type {string}
     * @memberof TodoFinishViewBase
     */ 
    protected viewName: string = 'TodoFinishView';


    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TodoFinishViewBase
     */
    public engine: OptionViewEngine = new OptionViewEngine();


    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof TodoFinishViewBase
     */    
    public counterServiceArray: Array<any> = [
        
    ];

    /**
     * 引擎初始化
     *
     * @public
     * @memberof TodoFinishViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            form: this.$refs.form,
            p2k: '0',
            keyPSDEField: 'todo',
            majorPSDEField: 'name',
            isLoadDefault: true,
        });
    }

    /**
     * form 部件 save 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TodoFinishViewBase
     */
    public form_save($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'save', $event);
    }

    /**
     * form 部件 remove 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TodoFinishViewBase
     */
    public form_remove($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'remove', $event);
    }

    /**
     * form 部件 load 事件
     *
     * @param {*} [args={}]
     * @param {*} $event
     * @memberof TodoFinishViewBase
     */
    public form_load($event: any, $event2?: any): void {
        this.engine.onCtrlEvent('form', 'load', $event);
    }


}