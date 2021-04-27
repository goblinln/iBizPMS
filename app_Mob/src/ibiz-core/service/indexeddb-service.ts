import { IndexedDBServiceBase } from './indexeddb-service-base';

/**
 * IndexedDB数据库服务
 *
 * @export
 * @class IndexedDBService
 * @extends {IndexedDBServiceBase}
 */
export class IndexedDBService extends IndexedDBServiceBase {

    /**
     * 唯一实例
     *
     * @private
     * @static
     * @type {IndexedDBService}
     * @memberof IndexedDBService
     */
    private static readonly instance: IndexedDBService = new IndexedDBService();

    /**
     * Creates an instance of IndexedDBService.
     * @memberof IndexedDBService
     */
    protected constructor() {
        if (IndexedDBService.instance) {
            return IndexedDBService.instance;
        }
        super('2021-4-27');
    }

    /**
     * 实体配置初始化
     *
     * @protected
     * @memberof IndexedDBServiceBase
     */
    protected entityConfigInit(): void {
        this.entityConfigs.push({ name: 'ibzmonthly', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'sysemployee', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'taskteam', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzmyterritory', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'action', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'systeam', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'taskestimate', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzfavorites', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'syspost', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'sysdepartment', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'casestep', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzreportly', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'doccontent', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'testmodule', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'projectstats', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibztaskteam', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibztaskestimate', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'project', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'systeammember', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'testsuite', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'projectteam', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'file', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'storyspec', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'branch', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'release', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'case', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'projectmodule', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzdaily', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'doclib', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'module', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'build', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'bug', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'product', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'productplan', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'doc', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'sysupdatelog', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'user', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'dynadashboard', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'productmodule', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzreport', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'productstats', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzdoc', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'usercontact', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'sysupdatefeatures', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'task', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'todo', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'sysorganization', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'doclibmodule', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'testtask', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzweekly', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'ibzprojectteam', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'story', keyPath: 'srfsessionkey' });
        this.entityConfigs.push({ name: 'productline', keyPath: 'srfsessionkey' });
    }

    /**
     * 获取实例
     *
     * @returns {IndexedDBService}
     * @memberof IndexedDBService
     */
    public static getInstance(): IndexedDBService {
        return IndexedDBService.instance;
    }

}