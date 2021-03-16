import IBZProStory_zh_CN_Base from './ibzpro-story_zh_CN_base';

function getLocaleResource(){
    const IBZProStory_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProStory_zh_CN_Base(), IBZProStory_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;