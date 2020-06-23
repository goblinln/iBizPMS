import { Vue, Component, Prop, Provide, Emit, Watch, Model } from 'vue-property-decorator';
import { CreateElement } from 'vue';
import { Subject, Subscription } from 'rxjs';
import { CtrlBase } from '@/studio-core';
import ProjectService from '@/service/project/project-service';
import ProjectExpViewService from './project-exp-view-portlet-service';

import { Environment } from '@/environments/environment';


/**
 * db_projectexpportlet部件基类
 *
 * @export
 * @class CtrlBase
 * @extends {ProjectExpViewBase}
 */
export class ProjectProjectExpViewBase extends CtrlBase {

    /**
     * 名称
     *
     * @type {string}
     * @memberof ProjectExpView
     */
    @Prop() public name?: string;

    /**
     * 视图通讯对象
     *
     * @type {Subject<ViewState>}
     * @memberof ProjectExpView
     */
    @Prop() public viewState!: Subject<ViewState>;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof ProjectExpView
     */
    @Prop() public context: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof ProjectExpView
     */
    @Prop() public viewparams: any;

    /**
     * 视图状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof ProjectExpView
     */
    public viewStateEvent: Subscription | undefined;

    /**
     * 获取部件类型
     *
     * @returns {string}
     * @memberof ProjectExpView
     */
    public getControlType(): string {
        return 'PORTLET'
    }



    /**
     * 计数器服务对象集合
     *
     * @type {Array<*>}
     * @memberof ProjectExpView
     */    
    public counterServiceArray:Array<any> = [];

    /**
     * 建构部件服务对象
     *
     * @type {ProjectExpViewService}
     * @memberof ProjectExpView
     */
    public service: ProjectExpViewService = new ProjectExpViewService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProjectService}
     * @memberof ProjectExpView
     */
    public appEntityService: ProjectService = new ProjectService({ $store: this.$store });
    


    /**
     * 关闭视图
     *
     * @param {any} args
     * @memberof ProjectExpView
     */
    public closeView(args: any): void {
        let _this: any = this;
        _this.$emit('closeview', [args]);
    }

    /**
     *  计数器刷新
     *
     * @memberof ProjectExpView
     */
    public counterRefresh(){
        const _this:any =this;
        if(_this.counterServiceArray && _this.counterServiceArray.length >0){
            _this.counterServiceArray.forEach((item:any) =>{
                if(item.refreshData && item.refreshData instanceof Function){
                    item.refreshData();
                }
            })
        }
    }


    /**
     * 长度
     *
     * @type {number}
     * @memberof ProjectExpView
     */
    @Prop() public height?: number;

    /**
     * 宽度
     *
     * @type {number}
     * @memberof ProjectExpView
     */
    @Prop() public width?: number;



    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof ProjectExpViewBase
     */
    @Prop({default: false})public isAdaptiveSize!: boolean;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof ProjectExpViewBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof ProjectExpViewBase
     */
    public getData(): any {
        return {};
    }

    /**
     * vue 生命周期
     *
     * @memberof ProjectExpViewBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof ProjectExpViewBase
     */    
    public afterCreated(){
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                const refs: any = this.$refs;
                Object.keys(refs).forEach((_name: string) => {
                    this.viewState.next({ tag: _name, action: action, data: data });
                });
            });
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof ProjectExpViewBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof ProjectExpViewBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }


}
