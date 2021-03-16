import IBZProStoryModule_zh_CN_Base from './ibzpro-story-module_zh_CN_base';

function getLocaleResource(){
    const IBZProStoryModule_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProStoryModule_zh_CN_Base(), IBZProStoryModule_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;