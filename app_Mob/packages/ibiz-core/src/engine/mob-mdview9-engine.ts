import { MobMDViewEngine } from './mob-mdview-engine';

/**
 * 实体移动端多数据视图（部件视图）界面引擎
 *
 * @export
 * @class MobMDView9Engine
 * @extends {MobMDViewEngine}
 */
export class MobMDView9Engine extends MobMDViewEngine {

    /**
     * load加载
     * @memberof GridViewEngine
     */
    public load(opts: any = {}) {
        if (!this.view.formDruipart) {
            super.load(opts);
        }
    }
}
