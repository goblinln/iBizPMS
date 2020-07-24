import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, ListControlBase } from '@/studio-core';
import ProjectService from '@/service/project/project-service';
import ExpService from './exp-list-service';
import ProjectUIService from '@/uiservice/project/project-ui-service';


/**
 * listexpbar_list部件基类
 *
 * @export
 * @class ListControlBase
 * @extends {ExpListBase}
 */
export class ExpListBase extends ListControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof ExpListBase
     */
    protected controlType: string = 'LIST';

    /**
     * 建构部件服务对象
     *
     * @type {ExpService}
     * @memberof ExpListBase
     */
    public service: ExpService = new ExpService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProjectService}
     * @memberof ExpListBase
     */
    public appEntityService: ProjectService = new ProjectService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof ExpListBase
     */
    protected appDeName: string = 'project';

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof ExpListBase
     */
    public minorSortDir: string = 'DESC';

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof ExpListBase
     */
    public minorSortPSDEF: string = 'id';

    /**
     * this引用
     *
     * @type {number}
     * @memberof ExpBase
     */
    public thisRef: any = this;
}