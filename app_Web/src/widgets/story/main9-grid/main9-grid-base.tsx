import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, GridControllerBase } from '@/studio-core';
import StoryService from '@/service/story/story-service';
import Main9Service from './main9-grid-service';
import { FormItemModel } from '@/model/form-detail';


/**
 * grid部件基类
 *
 * @export
 * @class GridControllerBase
 * @extends {Main9GridBase}
 */
export class Main9GridBase extends GridControllerBase {

    /**
     * 建构部件服务对象
     *
     * @type {Main9Service}
     * @memberof Main9GridBase
     */
    public service: Main9Service = new Main9Service({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {StoryService}
     * @memberof Main9GridBase
     */
    public appEntityService: StoryService = new StoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof Main9GridBase
     */
    protected appDeName: string = 'story';

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof GridControllerBase
     */
    protected localStorageTag: string = 'zt_story_main9_grid';

    /**
     * 是否支持分页
     *
     * @type {boolean}
     * @memberof Main9GridBase
     */
    public isEnablePagingBar: boolean = false;

    /**
     * 排序方向
     *
     * @type {string}
     * @memberof Main9GridBase
     */
    public minorSortDir: string = '';

    /**
     * 分页条数
     *
     * @type {number}
     * @memberof Main9GridBase
     */
    public limit: number = 10;

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof Main9GridBase
     */
    public allColumns: any[] = [
        {
            name: 'pri',
            label: 'P',
            langtag: 'entities.story.main9_grid.columns.pri',
            show: true,
            util: 'PX'
        },
        {
            name: 'title',
            label: '需求名称',
            langtag: 'entities.story.main9_grid.columns.title',
            show: true,
            util: 'STAR'
        },
        {
            name: 'status',
            label: '状态',
            langtag: 'entities.story.main9_grid.columns.status',
            show: true,
            util: 'PX'
        },
    ]


    /**
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof Main9GridBase
     */
	public uiAction(row: any, tag: any, $event: any) {
        $event.stopPropagation();
    }
}