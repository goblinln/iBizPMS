import Dexie, { Version } from 'dexie';

/**
 * 库实例基类
 *
 * @author chitanda
 * @date 2021-07-22 21:07:00
 * @export
 * @class DBBaseService
 * @extends {Dexie}
 */
export class DBBaseService extends Dexie {
    protected v: Version;

    /**
     * Creates an instance of DBService.
     * @author chitanda
     * @date 2021-07-22 21:07:04
     */
    constructor() {
        super(window.IBzDynamicConfig.dbName!);
        this.v = this.version(window.IBzDynamicConfig.dbVersion!);
        this.init();
    }

    /**
     * 数据库初始化
     *
     * @author chitanda
     * @date 2021-07-23 11:07:33
     * @protected
     */
    protected init(): void {
        throw new Error('数据库实例化需要重写!');
    }
}
