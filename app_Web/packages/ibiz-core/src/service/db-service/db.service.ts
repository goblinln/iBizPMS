import { DBBaseService } from '../db-base-service/db-base.service';

/**
 * 库实例
 *
 * @author chitanda
 * @date 2021-07-22 21:07:00
 * @export
 * @class DBService
 * @extends {DBBaseService}
 */
export class DBService extends DBBaseService {
    /**
     * 唯一实例
     *
     * @author chitanda
     * @date 2021-07-22 21:07:31
     * @protected
     * @static
     */
    protected static instance: any;

    /**
     * Creates an instance of DBService.
     *
     * @author chitanda
     * @date 2021-07-22 21:07:32
     */
    constructor() {
        if (DBService.instance) {
            return DBService.instance;
        }
        super();
    }

    /**
     * 数据库初始化
     *
     * @author chitanda
     * @date 2021-07-23 11:07:33
     * @protected
     */
    protected init(): void {
        this.v = this.v.stores({
        });
    }

    /**
     * 获取实例
     *
     * @author chitanda
     * @date 2021-07-22 21:07:37
     * @static
     * @return {*}  {DBService}
     */
    static getInstance(): DBService {
        if (!this.instance) {
            this.instance = new DBService();
        }
        return this.instance;
    }
}
