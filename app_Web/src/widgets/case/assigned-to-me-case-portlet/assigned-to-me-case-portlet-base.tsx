import { Prop, Provide, Emit, Watch, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { CtrlBase } from '@/studio-core';
import CaseService from '@/service/case/case-service';
import AssignedToMeCaseService from './assigned-to-me-case-portlet-service';
import { Environment } from '@/environments/environment';


/**
 * db_assigntomecaseportlet1部件基类
 *
 * @export
 * @class CtrlBase
 * @extends {AssignedToMeCaseBase}
 */
export class AssignedToMeCaseBase extends CtrlBase {

    /**
     * 建构部件服务对象
     *
     * @type {AssignedToMeCaseService}
     * @memberof AssignedToMeCase
     */
    public service: AssignedToMeCaseService = new AssignedToMeCaseService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {CaseService}
     * @memberof AssignedToMeCase
     */
    public appEntityService: CaseService = new CaseService({ $store: this.$store });

    /**
     * 长度
     *
     * @type {number}
     * @memberof AssignedToMeCase
     */
    @Prop() public height?: number;

    /**
     * 宽度
     *
     * @type {number}
     * @memberof AssignedToMeCase
     */
    @Prop() public width?: number;



    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof AssignedToMeCaseBase
     */
    @Prop({default: false})public isAdaptiveSize!: boolean;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof AssignedToMeCaseBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof AssignedToMeCaseBase
     */
    public getData(): any {
        return {};
    }

    /**
     * vue 生命周期
     *
     * @memberof AssignedToMeCaseBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof AssignedToMeCaseBase
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
     * @memberof AssignedToMeCaseBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof AssignedToMeCaseBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }


}
