import { IBZProStoryModuleBase } from './ibzpro-story-module-base';

/**
 * 需求模块
 *
 * @export
 * @class IBZProStoryModule
 * @extends {IBZProStoryModuleBase}
 * @implements {IIBZProStoryModule}
 */
export class IBZProStoryModule extends IBZProStoryModuleBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProStoryModule
     */
    clone(): IBZProStoryModule {
        return new IBZProStoryModule(this);
    }
}
export default IBZProStoryModule;
