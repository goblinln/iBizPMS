import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzMyTerritory, IbzMyTerritory } from '../../entities';
import keys from '../../entities/ibz-my-territory/ibz-my-territory-keys';

/**
 * 我的地盘服务对象基类
 *
 * @export
 * @class IbzMyTerritoryBaseService
 * @extends {EntityBaseService}
 */
export class IbzMyTerritoryBaseService extends EntityBaseService<IIbzMyTerritory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzMyTerritory';
    protected APPDENAMEPLURAL = 'IbzMyTerritories';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/IbzMyTerritory.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'realname';
    protected quickSearchFields = ['realname',];
    protected selectContextParam = {
    };

    newEntity(data: IIbzMyTerritory): IbzMyTerritory {
        return new IbzMyTerritory(data);
    }

    async addLocal(context: IContext, entity: IIbzMyTerritory): Promise<IIbzMyTerritory | null> {
        return this.cache.add(context, new IbzMyTerritory(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzMyTerritory): Promise<IIbzMyTerritory | null> {
        return super.createLocal(context, new IbzMyTerritory(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzMyTerritory> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzMyTerritory): Promise<IIbzMyTerritory> {
        return super.updateLocal(context, new IbzMyTerritory(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzMyTerritory = {}): Promise<IIbzMyTerritory> {
        return new IbzMyTerritory(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzmyterritories`, _data);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.get(`/ibzmyterritories/${_context.ibzmyterritory}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzmyterritories/getdraft`, _data);
        return res;
    }
    /**
     * MobMenuCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async MobMenuCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/ibzmyterritories/mobmenucount`);
        return res;
    }
    /**
     * MyFavoriteCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async MyFavoriteCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/ibzmyterritories/myfavoritecount`);
        return res;
    }
    /**
     * MyTerritoryCount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async MyTerritoryCount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const res = await this.http.post(`/ibzmyterritories/myterritorycount`);
        return res;
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.delete(`/ibzmyterritories/${_context.ibzmyterritory}`);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzmyterritories/${_context.ibzmyterritory}`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmyterritories/fetchdefault`, _data);
    }
    /**
     * FetchMyWork
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async FetchMyWork(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmyterritories/fetchmywork`, _data);
    }
    /**
     * FetchMyWorkMob
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async FetchMyWorkMob(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmyterritories/fetchmyworkmob`, _data);
    }
    /**
     * FetchMyWorkPm
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async FetchMyWorkPm(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmyterritories/fetchmyworkpm`, _data);
    }
    /**
     * FetchPersonInfo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async FetchPersonInfo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmyterritories/fetchpersoninfo`, _data);
    }
    /**
     * FetchWelcome
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async FetchWelcome(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.post(`/ibzmyterritories/fetchwelcome`, _data);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzMyTerritoryService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        return this.http.get(`/ibzmyterritories/${_context.ibzmyterritory}/select`);
    }
}
