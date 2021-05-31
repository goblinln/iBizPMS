import { MobMDViewEngine } from './mob-mdview-engine';

/**
 * 实体移动端工作流多数据视图界面引擎
 *
 * @export
 * @class MobWFMDViewEngine
 * @extends {MobMDViewEngine}
 */
export class MobWFMDViewEngine extends MobMDViewEngine {

    /**
     * 编辑数据
     *
     * @param {*} arg
     * @memberof MobWFMDViewEngine
     */
    public onEditData(arg: any): void {
        const loadParam: any = {};
        Object.assign(loadParam, { taskid: arg.data.srfkey, srfkey: arg.data.businesskey });
        if (this.openData && this.isFunc(this.openData)) {
            this.openData([loadParam], {}, {});
        }
    }

}