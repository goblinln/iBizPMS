import { isEmpty, isNil } from 'ramda';

/**
 * 搜索过滤
 *
 * @export
 * @class SearchFilter
 */
export class SearchFilter {
    /**
     * 上下文
     *
     * @type {*}
     * @memberof SearchFilter
     */
    readonly context: any;

    /**
     * 父关系实体
     *
     * @type {string}
     * @memberof SearchFilter
     */
    readonly srfparentdename?: string;

    /**
     * 指定父数据主键
     *
     * @type {string}
     * @memberof SearchFilter
     */
    readonly srfparentkey?: string;

    /**
     * 分页
     *
     * @type {number}
     * @memberof SearchFilter
     */
    readonly page = 0;

    /**
     * 分页数据量
     *
     * @type {number}
     * @memberof SearchFilter
     */
    readonly size = 1000;

    /**
     * 快速搜索值
     *
     * @type {string}
     * @memberof SearchFilter
     */
    readonly query!: string;

    /**
     * 数据
     *
     * @type {*}
     * @memberof SearchFilter
     */
    readonly data: any;

    /**
     * 排序属性
     *
     * @type {string}
     * @memberof SearchFilter
     */
    readonly sortField = 'srfordervalue';

    /**
     * 排序模式
     *
     * @type {('ASC' | 'DESC')}
     * @memberof SearchFilter
     */
    readonly sortMode: 'ASC' | 'DESC' = 'ASC';

    /**
     * Creates an instance of SearchFilter.
     * @param {*} context
     * @param {*} [data]
     * @memberof SearchFilter
     */
    constructor(context: any, data?: any) {
        this.context = context;
        if (data) {
            if (!isNil(data.page) && !isEmpty(data.page)) {
                this.page = data.page;
            }
            if (!isNil(data.size) && !isEmpty(data.size)) {
                this.size = data.size;
            }
            if (!isNil(data.query) && !isEmpty(data.query)) {
                this.query = data.query;
            }
            if (!isNil(data.sort) && !isEmpty(data.sort)) {
                const arr = data.sort.split(',');
                if (arr.length >= 1) {
                    this.sortField = arr[0];
                }
                if (arr.length >= 2) {
                    this.sortMode = arr[1].toUpperCase();
                }
            }
            if (!isNil(data.srfparentkey) && !isEmpty(data.srfparentkey)) {
                this.srfparentkey = data.srfparentkey;
            }
            if (!isNil(data.srfparentdename) && !isEmpty(data.srfparentdename)) {
                this.srfparentdename = data.srfparentdename;
            }
            this.data = { ...data };
            delete this.data.page;
            delete this.data.size;
            delete this.data.query;
            delete this.data.sort;
            delete this.data.srfparentkey;
            delete this.data.srfparentdename;
        }
        if (this.data == null) {
            this.data = {};
        }
    }

    /**
     * 获取条件值
     *
     * @param {string} key
     * @return {*}  {*}
     * @memberof SearchFilter
     */
    getValue(key: string): any {
        if (this.data[key]) {
            return this.data[key];
        }
        return this.context[key];
    }
}
