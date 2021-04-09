import { DocLibModuleBase } from './doc-lib-module-base';

/**
 * 文档库分类
 *
 * @export
 * @class DocLibModule
 * @extends {DocLibModuleBase}
 * @implements {IDocLibModule}
 */
export class DocLibModule extends DocLibModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof DocLibModule
     */
    clone(): DocLibModule {
        return new DocLibModule(this);
    }
}
export default DocLibModule;
