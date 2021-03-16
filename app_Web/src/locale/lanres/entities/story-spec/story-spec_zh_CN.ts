import StorySpec_zh_CN_Base from './story-spec_zh_CN_base';

function getLocaleResource(){
    const StorySpec_zh_CN_OwnData = {};
    const targetData = Object.assign(StorySpec_zh_CN_Base(), StorySpec_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;